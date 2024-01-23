package projectSettlePay.back.Providers;

public class AllianceECom extends ProvidersMethods implements IProviders {

    private int core = 8;
    private int conn = 8;
    private String body = "";

    public AllianceECom(String body){
        this.body = body;
    }

    public void pay_in(){
        create(this);
    }

    public void pay_out(){
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

    public static class AllianceEComBody{
        public static String defaultBody() {
            return "{\n" +
                    "    \"auth\": {\n" +
                    "        \"debug\": true,\n" +
                    "        \"point\": 2758,\n" +
                    "        \"key\": 1,\n" +
                    "        \"hash\": \"c2fdd7fbcab864d08c58ae7e7ca8b063\"\n" +
                    "    },\n" +
                    "    \"locale\": \"en\",\n" +
                    "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                    "    \"account_id\": 2764,\n" +
                    "    \"wallet_id\": 2537,\n" +
                    "    \"service_id\": 3343,\n" +
                    "    \"amount\": 104, \n" +
                    "    \"amount_currency\": \"UAH\",\n" +
                    "    \"description\": \"Термінова послуга, що запроваджена отриманням данних по договору за номером КРН42235678987654456789987678 від 01.11.2023\", \n" +
                    "    \"fields\": {\n" +
                    "        \"card_number\": \"1111111111111111\",\n" +
                    "        \"expire_month\": \"00\", \n" +
                    "        \"expire_year\": \"2030\", \n" +
                    "        \"cvv\": \"111\", \n" +
                    "        \"purpose\": \"по договору за номером КРН42235678987654456789987678 від 01.11.2023\"\n" +
                    "        }\n" +
                    "}";
        }
    }

}
