package projectSettlePay.back.providers;

import projectSettlePay.front.AcquiringFrame1;
import projectSettlePay.front.IFrame;

public class OnePay extends ProvidersMethods implements Pay_in, Pay_out {

    private int core = 3;
    private int conn = 3;
    private String body = "";

    private AcquiringFrame1 frame = new AcquiringFrame1();

    public OnePay(String body){
        this.body = body;
    }

    public void pay_in(){
        create(this);
    }

    public void pay_out(){
        pay(this);
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

    public String getResponce(){
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

    public static class OnePayBody{
        public static String defaultBody(boolean pay_in){
            if (pay_in){
            return "{\n" +
                    "    \"auth\": {\n" +
                    "        \"debug\": true,\n" +
                    "        \"point\": 2754,\n" +
                    "        \"key\": 1,\n" +
                    "        \"hash\": \"01607df2a07b633ea35a909152ba6061\"\n" +
                    "    },\n" +
                    "    \"locale\": \"ua\",\n" +
                    "    \"external_order_id\": \"124458\",\n" +
                    "    \"external_customer_id\": \"21465125111852852525525\",\n" +
                    "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                    "    \"account_id\": 2760,\n" +
                    "    \"wallet_id\": 2530,\n" +
                    "    \"service_id\": 3456,\n" +
                    "    \"description\" : \"test\",\n" +
                    "    \"amount\": 200,\n" +
                    "    \"amount_currency\": \"UAH\"\n" +
                    "}";
        }else {
                return "{\n" +
                        "    \"auth\": {\n" +
                        "        \"debug\": true,\n" +
                        "        \"point\": 2754,\n" +
                        "        \"key\": 1,\n" +
                        "        \"hash\": \"01607df2a07b633ea35a909152ba6061\"\n" +
                        "    },\n" +
                        "    \"locale\": \"ua\",\n" +
                        "    \"external_order_id\": \"1258\",\n" +
                        "    \"external_customer_id\": \"21465125852852525525\",\n" +
                        "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                        "    \"account_id\": 2760,\n" +
                        "    \"wallet_id\": 2530,\n" +
                        "    \"service_id\": 3457,\n" +
                        "    \"description\" : \"test\",\n" +
                        "    \"amount\": 50099,\n" +
                        "    \"amount_currency\": \"UAH\",\n" +
                        "    \"fields\": {\n" +
                        "        \"card_number\": \"4000000000000010\"\n" +
                        "    }\n" +
                        "}";
            }
        }
    }

}
