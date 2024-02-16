package projectSettlePay.back.providers;

import projectSettlePay.front.IFrame;

public class AstroPay extends ProvidersMethods implements Pay_in {

    private int core = 7;
    private int conn = 7;
    private String body = "";

    private IFrame frame;

    public AstroPay(String body){
        this.body = body;
    }

    public void pay_in(){
        create(this);
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

    public static class AstroPayBody{
        public static String defaultBody(){
                return "{\n" +
                        "    \"auth\": {\n" +
                        "        \"debug\": true,\n" +
                        "        \"point\": 1,\n" +
                        "        \"key\": 1,\n" +
                        "        \"hash\": \"c9194644d4316e008a7afb0d50bf056b\"\n" +
                        "    },\n" +
                        "    \"locale\": \"ua\",\n" +
                        "    \"external_order_id\": \"100\",\n" +
                        "    \"external_customer_id\": \"437651278052846769\",\n" +
                        "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                        "    \"account_id\": 4,\n" +
                        "    \"wallet_id\": 10,\n" +
                        "    \"service_id\": 3075,\n" +
                        "    \"amount\": 10000,\n" +
                        "    \"description\" : \"test roman crypto\",\n" +
                        "    \"amount_currency\": \"UAH\" ,\n" +
                        "    \"point\" : {\n" +
                        "        \"success_url\" : \"https://google.com\"\n" +
                        "    }\n" +
                        "}";
        }
    }

}