package projectSettlePay.back;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import projectSettlePay.back.providers.IProviders;
import projectSettlePay.core.DataBase;
import projectSettlePay.helper.TransInfoHelper;

import java.sql.ResultSet;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static projectSettlePay.core.DataBase.DataBaseName.CONN_STAGE_1;

public class Callback implements TransInfoHelper {

    private static Logger logger = Logger.getLogger(Callback.class);
    private DataBase dataBase;
    private Response response;
    private String env = "pay-forms-stage";

    public String getResult_url(IProviders provider){
        return getResult_url(provider.getCore(),provider.getConn(),provider.getId());
    }

    public String getResult_url(int core, int conn, String transId){
        String result_url = "";
        try {
            Thread.sleep(2000);
            dataBase = new DataBase(CONN_STAGE_1);
            ResultSet resultSet = dataBase.selectSql("SELECT x.transaction_uuid FROM public.transactions x WHERE external_transaction_id = '"+getChildId(transId)+"'");
            resultSet.next();
            result_url = "https://"+env+"-"+core+".backofficeweb.info/api/public/connector-stage-"+conn+"/callback/dynamic-callback/"+resultSet.getString("transaction_uuid");
        } catch (Throwable e) {
            logger.error(e);
        }
        return result_url;
    }

    public void makeCallback(String url, String body){
        response = given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post(url);
        logger.info("Callback - "+getResponse());
    }

    public void makeCallback(String url, Map body){
        response = given()
                .contentType(ContentType.URLENC)
                .params(body)
                .when()
                .post(url);
        logger.info("Callback - "+getResponse());
    }

    public String getResponse(){
        return response.then().extract().response().asString();
    }
}
