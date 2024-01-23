package projectSettlePay.back.Providers;

public class Xpay365 extends ProvidersMethods implements IProviders {

    private int core = 3;
    private int conn = 2;
    private String body = "";

    public Xpay365(String body){
        this.body = body;
    }

    public void pay_in(){
        create(this);
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

    public static class Xpay365Body{
        public static String defaultBody(boolean pay_in){
            if (pay_in){
                return "{\n" +
                        "    \"auth\": {\n" +
                        "        \"debug\": true,\n" +
                        "        \"point\": 2754,\n" +
                        "        \"key\": 1,\n" +
                        "        \"hash\": \"01607df2a07b633ea35a909152ba6061\"\n" +
                        "    },\n" +
                        "    \"locale\": \"en\",\n" +
                        "    \"external_order_id\": \"100\",\n" +
                        "    \"external_customer_id\": \"1213\",\n" +
                        "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                        "    \"account_id\": 2760,\n" +
                        "    \"wallet_id\": 2536,\n" +
                        "    \"service_id\": 3341,\n" +
                        "    \"description\" : \"test\",\n" +
                        "    \"amount\": 10000,\n" +
                        "    \"amount_currency\": \"INR\",\n" +
                        "    \"fields\" : {\n" +
                        "       \"cust_mobile\" : \"+918327089341\",\n" +
                        "       \"cust_email\" : \"suprteswdhja18jhe@gmail.com\",\n" +
                        "       \"cust_name\" : \"Kumah Jayanta\"\n" +
                        "    }\n" +
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
                        "    \"external_order_id\": \"100\",\n" +
                        "    \"external_customer_id\": \"1213\",\n" +
                        "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                        "    \"account_id\": 2760,\n" +
                        "    \"wallet_id\": 2536,\n" +
                        "    \"service_id\": 3342,\n" +
                        "    \"description\" : \"test\",\n" +
                        "    \"amount\": 100000,\n" +
                        "    \"amount_currency\": \"INR\",\n" +
                        "    \"fields\" : {\n" +
                        "       \"BeneficiaryMobile\" : \"9040343043\",\n" +
                        "       \"BeneficiaryEmail\" : \"Jayanta9040343043@gmail.com\",\n" +
                        "       \"AccHolderName\" : \"Jayanta Kumar Singh\",\n" +
                        "       \"IFSC\" : \"UTKS0001587\",\n" +
                        "       \"card_number\" : \"1587010000000301\"\n" +
                        "    }\n" +
                        "\n" +
                        "}";
            }
        }
    }

}