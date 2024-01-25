package projectSettlePay.back.providers;

import projectSettlePay.front.AcquiringFrame;
import projectSettlePay.front.IFrame;
import projectSettlePay.front.PayCashFrame;

public class PayCash extends ProvidersMethods implements Pay_in{

    private int core = 2;
    private int conn = 2;
    private String body = "";

    public PayCashFrame frame = new PayCashFrame();

    public PayCash(String body){
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

    public static class PayCashBody{
        public static String defaultBody(){
            return "{\n" +
                    "    \"auth\": {\n" +
                    "        \"debug\": true,\n" +
                    "        \"point\": 2758,\n" +
                    "        \"key\": 1,\n" +
                    "        \"hash\": \"c2fdd7fbcab864d08c58ae7e7ca8b063\"\n" +
                    "    },\n" +
                    "    \"locale\": \"ua\",\n" +
                    "    \"external_order_id\": \"100\",\n" +
                    "    \"external_customer_id\": \"1213\",\n" +
                    "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                    "    \"account_id\": 2764,\n" +
                    "    \"wallet_id\": 3404, \n" +
                    "    \"service_id\": 3895,\n" +
                    "    \"description\" : \"test\",\n" +
                    "    \"amount\": 1320000,\n" +
                    "    \"amount_currency\": \"MXN\",\n" +
                    "    \"fields\" : {\n" +
                    "        \"country\" : \"MX\"\n" +
                    "    }\n" +
                    "}";
        }
    }

}

