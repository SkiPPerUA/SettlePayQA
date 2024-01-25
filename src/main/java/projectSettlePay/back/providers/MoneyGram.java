package projectSettlePay.back.providers;

public class MoneyGram extends ProvidersMethods implements Pay_out{

    private int core = 4;
    private int conn = 4;
    private String body = "";

    public MoneyGram(String body){
        this.body = body;
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

    public String getId(){
        return String.valueOf(response.then().extract().response().jsonPath().getLong("response.id"));
    }

    @Override
    public int getConn() {
        return conn;
    }

    @Override
    public void pay_out() {
        create(this);
    }

    public static class MoneyGramBody{
        public static String defaultBody(){
            return "{\n" +
                    "    \"auth\": {\n" +
                    "        \"debug\": true,\n" +
                    "        \"point\": 2755,\n" +
                    "        \"key\": 1,\n" +
                    "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                    "    },\n" +
                    "    \"locale\": \"ru\",\n" +
                    "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                    "    \"external_customer_id\": \"65865403\",\n" +
                    "    \"account_id\": 2761,\n" +
                    "    \"wallet_id\": 2605,\n" +
                    "    \"service_id\": 3434,\n" +
                    "    \"amount\": 100,\n" +
                    "    \"amount_currency\": \"USDC\",\n" +
                    "    \"fields\": {\n" +
                    "        \"email\": \"o.naumenko@4bill.io\"\n" +
                    "    }\n" +
                    "}";
        }
    }

}
