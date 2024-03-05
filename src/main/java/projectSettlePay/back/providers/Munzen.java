package projectSettlePay.back.providers;


public class Munzen extends ProvidersMethods implements Pay_out{
    private int core = 3;
    private int conn = 3;
    private String body = "";

//    connector_Id = 1127
//    schema id = 39
//    айдшка кред 1208
//
//    providers_id = 5182
//    Services_id = 4233
//    Point = 2754
//    01607df2a07b633ea35a909152ba6061
//    Account_id = 2760
//    Wallets_id = 3690
//    provider_services$id = 5414
//    service_commission_lines$id = 5165
//    service_commission_lines$id = 5166

    public Munzen(String body){
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
        return id;
    }

    @Override
    public int getConn() {
        return conn;
    }

    public static class MunzenBody{
        public static String defaultBody(){
                return "{\n" +
                        "    \"auth\": {\n" +
                        "        \"debug\": true,\n" +
                        "        \"point\": 2754,\n" +
                        "        \"key\": 1,\n" +
                        "        \"hash\": \"01607df2a07b633ea35a909152ba6061\"\n" +
                        "    },\n" +
                        "    \"locale\": \"ua\",\n" +
                        "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                        "    \"external_customer_id\": \"123134\",\n" +
                        "    \"account_id\": 2760,\n" +
                        "    \"wallet_id\": 3689,\n" +
                        "    \"service_id\": 4233,\n" +
                        "    \"amount\": 10000000,\n" +
                        "    \"amount_currency\": \"UZS\",\n" +
                        "    \"description\": \"Test deposit\",\n" +
                        "    \"fields\": {\n" +
                        "        \"card_number\":\"4232618181101636\",\n" +
                        "        \"expire_year\":\"2099\",\n" +
                        "        \"expire_month\":\"12\",\n" +
                        "        \"first_name\":\"Jane\",\n" +
                        "        \"city\":\"Kyiv\",\n" +
                        "        \"country\":\"US\",\n" +
                        "        \"last_name\":\"Doe\"\n" +
                        "    }\n" +
                        "}";
        }
    }
}
