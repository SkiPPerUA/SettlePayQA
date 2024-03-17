package projectSettlePay.back.providers;

import projectSettlePay.front.IFrame;
import projectSettlePay.front.PayECardsFrame;

public class PayECards extends ProvidersMethods implements Pay_in {

    private int core = 6;
    private int conn = 2;
    private String body = "";

    public PayECardsFrame frame = new PayECardsFrame();

    public PayECards(String body){
        this.body = body;
    }

    public void pay_in(){
        create(this);
    }

    @Override
    public void setBody(String body) {
        this.body = body;
    }
    @Override
    public IFrame getFrame() {
        return frame;
    }

    @Override
    public int getCore() {
        return core;
    }

    @Override
    public void setCore(int core){
        this.core = core;
    }

    @Override
    public String getBody(){
        return body;
    }

    public String getResponse(){
        return response.then().extract().response().asString();
    }

    public String getPayURL(){
        return response.then().extract().response().jsonPath().get("response.result.pay_url");
    }

    public String getId(){
        return String.valueOf(response.then().extract().response().jsonPath().getLong("response.id"));
    }

    @Override
    public int getConn() {
        return conn;
    }

    public static class PayECardsBody{
        public static String defaultBody(){
            return "{\n" +
                    "    \"auth\": {\n" +
                    "        \"debug\": true,\n" +
                    "        \"point\": 2757,\n" +
                    "        \"key\": 1,\n" +
                    "        \"hash\": \"73fa095174d9e9653f7f4b203d6fe122\"\n" +
                    "    },\n" +
                    "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                    "    \"locale\": \"en\",\n" +
                    "    \"account_id\": 2763,\n" +
                    "    \"wallet_id\": 3066,\n" +
                    "    \"service_id\": 3700,\n" +
                    "    \"amount\": 200,\n" +
                    "    \"amount_currency\": \"USD\",\n" +
                    "    \"description\": \"Test22\",\n" +
                    "    \"fields\": {\n" +
                    "        \"card_number\": \"4111111111111111\", \n" +
                    "        \"email\": \"test@example.com\",\n" +
                    "        \"phone\": \"+999999999998\",\n" +
                    "        \"cvv\": \"000\",\n" +
                    "        \"expire_year\":\"2025\",\n" +
                    "        \"expire_month\":\"01\",\n" +
                    "        \"address\" : \"Big street\",\n" +
                    "        \"country\" : \"US\",\n" +
                    "        \"city\" : \"City\",\n" +
                    "        \"state\": \"CA\",\n" +
                    "        \"zip_code\" : \"123456\",\n" +
                    "        \"first_name\" : \"John\", \n" +
                    "        \"last_name\" : \"Doe\",\n" +
                    "        \"description\" : \"Testico\"\n" +
                    "        }\n" +
                    "}";
        }
    }

}
