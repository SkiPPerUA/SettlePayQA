package projectSettlePay.back.providers;

import projectSettlePay.front.IFrame;

public class DCM extends ProvidersMethods implements Pay_in {

    private int core = 8;
    private int conn = 8;
    private String body = "";

    private IFrame frame;

    public DCM(String body){
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

    public static class DCMBody{
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
                    "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                    "    \"account_id\": 2764,\n" +
                    "    \"wallet_id\": 2537,\n" +
                    "    \"service_id\": 3515,\n" +
                    "    \"amount\": 10,\n" +
                    "    \"amount_currency\": \"UAH\" ,\n" +
                    "    \"fields\": {\n" +
                    "        \"account_number\": \"UA903052992990004149123456789\",\n" +
//                    "        \"DF903052992990004149123456789\", \n" +
//                    "        \"UA982891458787941817337825738\", \n" +
//                    "        \"UA903052992990004149123456789\", \n" +
//                    "        \"beneficiary_name\": \"ТОВ Digital Finance\", \n" +
//                    "        \"beneficiary_code\": \"43241185\", // workable\n" +
                    "        \"payment_purpose\": \"Testing purpose\" \n" +
                    "    }\n" +
                    "}";
        }
    }

}