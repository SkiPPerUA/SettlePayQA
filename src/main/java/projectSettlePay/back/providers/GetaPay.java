package projectSettlePay.back.providers;

import projectSettlePay.front.IFrame;
import projectSettlePay.front.P2PFrame;

public class GetaPay extends ProvidersMethods implements Pay_in {

    private int core = 7;
    private int conn = 7;
    private String body = "";

    private P2PFrame frame = new P2PFrame();

    public GetaPay(String body){
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

    public static class GetaPayBody{
        public static String defaultBody(){
            return "{\n" +
                    "    \"auth\": {\n" +
                    "        \"debug\": true,\n" +
                    "        \"point\": 2759,\n" +
                    "        \"key\": 1,\n" +
                    "        \"hash\": \"e5a095b16b8a2106380686b23c5570ac\"\n" +
                    "    },\n" +
                    "    \"locale\": \"ua\",\n" +
                    "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                    "    \"account_id\": 2765,\n" +
                    "    \"wallet_id\": 2561,\n" +
                    "    \"service_id\": 3859,\n" +
                    "    \"amount\": 10500,\n" +
                    "    \"amount_currency\": \"INR\",\n" +
                    "    \"fields\": {\n" +
                    "        \"cust_mobile\": \"0664187456\",\n" +
                    "        \"cust_email\": \"user@gmail.com\",\n" +
                    "        \"first_name\": \"Andromeda\",\n" +
                    "        \"last_name\": \"Galaxy\"\n" +
                    "    }\n" +
                    "}";
        }
    }

}
