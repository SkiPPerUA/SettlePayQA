package projectSettlePay.back;

import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import projectSettlePay.back.providers.IProviders;
import projectSettlePay.core.DataBase;
import java.sql.ResultSet;

import static io.restassured.RestAssured.given;
import static projectSettlePay.core.DataBase.DataBaseName.CONN_STAGE_1;

public class Callback {

    private static Logger logger = Logger.getLogger(Callback.class);
    private DataBase dataBase;

    public String getResult_url(IProviders provider){
        String result_url = "";
        long childTrans = Long.valueOf(provider.getId()) + 1;
        try {
            dataBase = new DataBase(CONN_STAGE_1);
            ResultSet resultSet = dataBase.selectSql("SELECT x.transaction_uuid FROM public.transactions x WHERE external_transaction_id = '"+childTrans+"'");
            resultSet.next();
            result_url = "https://pay-stage-"+provider.getCore()+".backofficeweb.info/api/public/connector-stage-"+provider.getConn()+"/callback/dynamic-callback/"+resultSet.getString("transaction_uuid");
        }catch (Throwable e){
            logger.error(e);
        }finally {
            dataBase.closeConn();
        }
        return result_url;
    }

    public void makeCallback(String url, String body){
        given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post(url);
    }
}
