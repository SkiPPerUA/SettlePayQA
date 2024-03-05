package projectSettlePayTests.back.providersTests.DCM;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import projectSettlePay.BaseTest;
import projectSettlePay.back.providers.DCM;
import projectSettlePay.core.Session;

import static io.restassured.RestAssured.given;

@Test
public class DCMtests extends BaseTest {

    int initAmount = 200;
    int callbackAmount = 1;
    DCM provider;
    long id;

    public void pay_in() {
            String bodyPayIn = "{\n" +
                    "    \"auth\": {\n" +
                    "        \"debug\": true,\n" +
                    "        \"point\": 2758,\n" +
                    "        \"key\": 1,\n" +
                    "        \"hash\": \"c2fdd7fbcab864d08c58ae7e7ca8b063\"\n" +
                    "    },\n" +
                    "    \"locale\": \"ua\",\n" +
                    "    \"external_order_id\": \"100\",\n" +
                    "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                    "    \"account_id\": 2764,\n" +
                    "    \"wallet_id\": 2537,\n" +
                    "    \"service_id\": 3515,\n" +
                    "    \"amount\": " + initAmount + ",\n" +
                    "    \"amount_currency\": \"UAH\" ,\n" +
                    "    \"fields\": {\n" +
                    "        \"account_number\": \"UA903052992990004149123456789\",\n" +
                    "        \"payment_purpose\": \"Testing purpose\" \n" +
                    "    }\n" +
                    "}";
            provider = new DCM(bodyPayIn);
            provider.pay_in();
            Session.getDriver().get(provider.getPayURL());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Session.closeSession();
            id = Long.valueOf(provider.getId()) + 1;
            callback(callbackAmount);
    }

    public void call(){
        id = 110100000475827l;
        callback(callbackAmount);
    }

    private void callback(int sum){

        String guid = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer ory_st_vOzRtvRKg16RkEgnlkXgUxtf7RQ6pH8X")
                .body("{\n" +
                        "    \"amount\": "+sum+",\n" +
                        "    \"asset_code\": \"eUAH\",\n" +
                        "    \"asset_issuer\": \"GCAPKTTNHNG5JWH6TDF2XCLVY2YIFML3EZUTWPWLJLIAUE4HL2DJ3EQW\",\n" +
                        "    \"sender_id\": \"fce4c71b-b551-11ee-b5e9-06df109e107d\",\n" +
                        "    \"receiver_id\": \"439150e1-b546-11ee-b5e9-06df109e107d\",\n" +
                        "    \"purpose\": \""+id+" Testing purpose\"\n" +
                        "}")
                .when()
                .post("https://api.bank1.processing-stage.dcm.systems/api/v1/counterparty/5944ca6f-93b4-4fe0-85e0-4f3eae1bf32e/branch/23d13b8f-85d7-4fe1-89f5-99cd5eadbf27/payments")
                .then().extract().response().jsonPath().get("guid");
        logger.info("guid - "+guid);
        logger.info("id - "+id);

        Response paymentConfirm = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer ory_st_vOzRtvRKg16RkEgnlkXgUxtf7RQ6pH8X")
                .body("{\n" +
                        "  \"guid\": \""+guid+"\"\n" +
                        "}")
                .when()
                .post("https://api.bank1.processing-stage.dcm.systems/api/v1/counterparty/5944ca6f-93b4-4fe0-85e0-4f3eae1bf32e/branch/23d13b8f-85d7-4fe1-89f5-99cd5eadbf27/payments/confirm");

        System.out.print("InitAmount = "+initAmount+"   callbackAmount = "+sum+"  =>  ");
        System.out.println("https://preprod-agora2.backofficeweb.info/transactions/transactions/"+id);
    }

}
