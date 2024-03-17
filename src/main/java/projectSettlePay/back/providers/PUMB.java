package projectSettlePay.back.providers;

public class PUMB extends ProvidersMethods implements Pay_out{

    private int core = 8;
    private int conn = 8;
    private String body = "";


    public PUMB(String body){
        this.body = body;
    }
    public void pay_out(){
        pay(this);
    }
    @Override
    public int getCore() {
        return core;
    }

    @Override
    public void setBody(String body) {
        this.body = body;
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

    public static class PUMBBody{
        public static String defaultBody(){
                return "{\n" +
                        "    \"auth\": {\n" +
                        "        \"debug\": true,\n" +
                        "        \"point\": 2758,\n" +
                        "        \"key\": 1,\n" +
                        "        \"hash\": \"c2fdd7fbcab864d08c58ae7e7ca8b063\"\n" +
                        "    },\n" +
                        "    \"locale\": \"ua\",\n" +
                        "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                        "    \"account_id\": 2764,\n" +
                        "    \"wallet_id\": 2537,\n" +
                        "    \"service_id\": 3364,\n" +
                        "    \"amount\": 1003,\n" +
                        "    \"amount_currency\": \"UAH\",\n" +
                        "    \"fields\": {\n" +
                        "        \"first_name\": \"Udutha\", \n" +
                        "        \"last_name\": \"Vamshi\",\n" +
                        "        \"card_number\": \"5355280207502822\" \n" +
                        "    \n" +
                        "    }\n" +
                        "}";
        }
    }
}
