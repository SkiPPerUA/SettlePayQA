package projectSettlePay.back.Providers;

import projectSettlePay.helper.UuidGenerate;

public class PayNetEasy extends ProvidersMethods implements IProviders {

    private int core = 3;
    private int conn = 3;
    private String body = "";

    public PayNetEasy(String body){
        this.body = body;
    }

    public void pay_in(){
        create(this);
    }

    @Override
    public void pay_out() {
        //
    }

    @Override
    public int getCore() {
        return core;
    }

    @Override
    public int getConn() {
        return conn;
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

    public static class PayNetEasyBody{
        public static String defaultBody(){
            return "{\n" +
                    "    \"auth\": {\n" +
                    "        \"debug\": true,\n" +
                    "        \"point\": 1,\n" +
                    "        \"key\": 1,\n" +
                    "        \"hash\": \"c9194644d4316e008a7afb0d50bf056b\"\n" +
                    "    },\n" +
                    "    \"locale\": \"ua\",\n" +
                    "    \"external_order_id\": \""+ UuidGenerate.generateUUID() +"\",\n" +
                    "    \"external_customer_id\": \""+UuidGenerate.generateUUID()+"\",\n" +
                    "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                    "    \"account_id\": 4,\n" +
                    "    \"wallet_id\": 118,\n" +
                    "    \"service_id\": 3337,\n" +
                    "    \"amount\": 1000,\n" +
                    "    \"amount_currency\": \"EUR\" \n" +
                    "}";
        }
    }
}
