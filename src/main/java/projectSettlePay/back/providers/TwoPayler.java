package projectSettlePay.back.providers;

import projectSettlePay.front.IFrame;

public class TwoPayler extends ProvidersMethods implements Pay_out, Pay_in {

    private int core = 1;
    private int conn = 6;
    private String body = "";

    private IFrame frame;

    public TwoPayler(String body){
        this.body = body;
    }

    @Override
    public IFrame getFrame() {
        return frame;
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

    public void get_methods_list(String body){
        this.body = body;
        super.get_methods_list(this);
    }

    public void get_recipient_card(String body){
        this.body = body;
        super.get_recipient_card(this);
    }

    public void confirm(String body){
        this.body = body;
        super.confirm(this);
    }

    public void cancel(String body){
        this.body = body;
        super.cancel(this);
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

    public static class TwoPaylerBody{
        public static String defaultBody(boolean pay_in){
            if (pay_in){
                return "{\n" +
                        "    \"auth\": {\n" +
                        "        \"debug\": true,\n" +
                        "        \"point\": 1,\n" +
                        "        \"key\": 1,\n" +
                        "        \"hash\": \"c9194644d4316e008a7afb0d50bf056b\"\n" +
                        "    },\n" +
                        "    \"locale\": \"ru\",\n" +
                        "    \"external_order_id\": \"100\",\n" +
                        "    \"external_customer_id\": \"1213\",\n" +
                        "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                        "    \"account_id\": 4,\n" +
                        "    \"wallet_id\": 10,\n" +
                        "    \"service_id\": 3304,\n" +
                        "    \"amount\": 20000,\n" +
                        "    \"amount_currency\": \"UAH\",\n" +
                        "    \"fields\" : {\n" +
                        "    \"first_name\" : \"roman\",\n" +
                        "    \"last_name\" : \"malanchuk\"\n" +
                        "    }\n" +
                        "}";
            }else {
                return "{\n" +
                        "    \"auth\": {\n" +
                        "        \"debug\": true,\n" +
                        "        \"point\": 1,\n" +
                        "        \"key\": 1,\n" +
                        "        \"hash\": \"c9194644d4316e008a7afb0d50bf056b\"\n" +
                        "    },\n" +
                        "    \"locale\": \"ru\",\n" +
                        "    \"external_order_id\": \"100\",\n" +
                        "    \"external_customer_id\": \"1213\",\n" +
                        "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                        "    \"account_id\": 4,\n" +
                        "    \"wallet_id\": 10,\n" +
                        "    \"service_id\": 3305,\n" +
                        "    \"amount\": 100,\n" +
                        "    \"amount_currency\": \"UAH\",\n" +
                        "    \"fields\" : {\n" +
                        "    \"first_name\" : \"roman\",\n" +
                        "    \"last_name\" : \"malanchuk\",\n" +
                        "    \"card_number\" : \"5169360016854121\"\n" +
                        "    }\n" +
                        "}";
            }
        }
    }

}
