package projectSettlePayTests.back;

import io.restassured.http.ContentType;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import projectSettlePay.BaseTest;
import projectSettlePay.helper.BodyHelper;
import static io.restassured.RestAssured.given;

import io.restassured.response.*;
import projectSettlePay.core.DataBase;
import projectSettlePay.core.Session;
import projectSettlePay.front.P2PFrame;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
@Test
public class Con_371 extends BaseTest {

    String host = "api-stage-4";
    String block = "UAH";
    String [] currency = {"KZT"};//, "UAH", "UZS", "KGS", "AZN"};
    String [] amount = {"500100", "500200", "500301", "500401"};
    int count = 0;
    DataBase dataBase;
    Response responce;
    Map<String, Integer> wall_id = new HashMap<>();
    Map<String, String> bankName = new HashMap<>();
    String id = "";
    P2PFrame frame;

    private void makeTrans(String body){
        responce = given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("https://"+host+".backofficeweb.info/transaction/create");
        id = (String) Optional.ofNullable(responce.then().extract().response().jsonPath().get("response.id")).get();
    }

    void makePositive(String body, String curr, String am){
        makeTrans(body);
        logger.info(responce.then().extract().response().asString());
        System.out.print(count+ "  (Currency pay - "+curr+", Amount pay - "+am+") - ");
        System.out.print(Optional.ofNullable(responce.then().extract().response().jsonPath().get("response.id")).get()+" - ");
        System.out.println(Optional.ofNullable(responce.then().extract().response().jsonPath().get("response.result.pay_url")).get());
        System.out.println("Positive");
    }

    void makeNegative(String body, String curr, String am){
        makeTrans(body);
        logger.info(responce.then().extract().response().asString());
        System.out.print(count+ "  (Currency pay - "+curr+", Amount pay - "+am+") - ");
        System.out.print(id+" - ");
        Session.getDriver().get(id);
        System.out.println("Negative");
    }
    private void makeFail(String body, String curr, String am){
        makeTrans(body);
        logger.info(responce.then().extract().response().asString());
        System.out.print(count+ "  (Currency pay - "+curr+", Amount pay - "+am+") - ");
        System.out.print(id+" - ");
        try {
            Session.getDriver().get(id);
            System.out.println("ОШИБКААААА");
        }catch (Throwable e){
            System.out.println("Fail");
        }
    }

    private String makeBody(String curr, String am){
        String body = "{\n" +
                "        \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "    \"locale\": \"ua\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"external_customer_id\": \"%s\",\n" +
                "    \"account_id\": 2761,\n" +
                "    \"wallet_id\": " + wall_id.get(curr) + ",\n" +
                "    \"service_id\": 3966,\n" +
                "    \"amount\": %s,\n" +
                "    \"amount_currency\": \"%s\",\n" +
                "    \"fields\": {\n" +
                "        \"first_name\": \"Andro\",\n" +
                "        \"last_name\": \"Meda\",\n" +
                "        \"email\": \"andromeda@gmail.com\"\n" +
                "    }\n" +
                "}";
        int amm;
        if (curr == "UZS"){
            amm = Integer.getInteger(am) + 50000000;
        }else {
            amm = Integer.getInteger(am);
        }
        return String.format(body, BodyHelper.generateUUID(), amm, curr);
    }

    public void test(){
        wall_id.put("KZT",3389);
        wall_id.put("UAH",2529);
        wall_id.put("UZS",3398);
        wall_id.put("KGS",3406);
        wall_id.put("AZN",3431);
        bankName.put("KZT","Kaspi Bank");
        bankName.put("UAH","Monobank");
        bankName.put("UZS","Любой банк (Humo)");
        bankName.put("KGS","Optima24");
        bankName.put("AZN","Unibank");
        for (String curr: currency){
            dataBase.updateSql("update public.provider_services set currency = '"+currency+"' where id = 5163");
            for (int i = 0; i < amount.length; i++) {
                count++;
                if (block.contains(curr)){
                    if ((count % 2) == 0 && i < 2) {
                        makeNegative(makeBody(curr,amount[i]),curr,amount[i]);
                    } else if  ((count % 2) == 1 && i < 2){
                        makePositive(makeBody(curr,amount[i]),curr,amount[i]);
                    } else {
                        makeFail(makeBody(curr,amount[i]),curr,amount[i]);
                    }
                }else {
                    if ((count % 2) == 0) {
                        makeNegative(makeBody(curr, amount[i]),curr,amount[i]);
                    } else {
                        makePositive(makeBody(curr, amount[i]),curr,amount[i]);
                    }
                }
            }
        }
    }

    @BeforeTest
    void update(){
        dataBase = new DataBase("apipay_new_stage1");;
    }

    @AfterTest
    void close(){
        dataBase.closeConn();
    }
}
