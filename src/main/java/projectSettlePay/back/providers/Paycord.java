package projectSettlePay.back.providers;

import projectSettlePay.front.IFrame;

public class Paycord extends ProvidersMethods implements Pay_in, Pay_out {

    private int core = 7;
    private int conn = 6;
    private String body = "";

    private IFrame frame;

    public Paycord(String body){
        this.body = body;
    }

    public void pay_in(){
        create(this);
    }

    @Override
    public IFrame getFrame() {
        return frame;
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
        return String.valueOf(response.then().extract().response().jsonPath().getLong("response.id"));
    }

    @Override
    public int getConn() {
        return conn;
    }

    public static class PaycordBody{
        public static String defaultBody(boolean pay_in){
            if (pay_in){
                return "{\n" +
                        "    \"auth\": {\n" +
                        "        \"debug\": true,\n" +
                        "        \"point\": 2759,\n" +
                        "        \"key\": 1,\n" +
                        "        \"hash\": \"e5a095b16b8a2106380686b23c5570ac\"\n" +
                        "    },\n" +
                        "    \"locale\": \"en\",\n" +
                        "    \"external_order_id\": \"100\",\n" +
                        "    \"external_customer_id\": \"3\",\n" +
                        "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                        "    \"account_id\": 2765,\n" +
                        "    \"wallet_id\": 2561,\n" +
                        "    \"service_id\": 3354,\n" +
                        "    \"amount\": 10099,\n" +
                        "    \"amount_currency\": \"INR\",\n" +
                        "    \"point\":{\n" +
                        "        \"return_url\": \"https://google.com\"\n" +
                        "    },\n" +
                        "    \"fields\": {\n" +
                        "        \"TxnMode\": \"UPI\",\n" +
                        "        \"cust_email\": \"test@test.com\",\n" +
                        "        \"cust_mobile\": \"+919954321125\",\n" +
                        "        \"first_name\": \"John\",\n" +
                        "        \"last_name\": \"Doe\"\n" +
                        "    },\n" +
                        "    \"description\": \"\"\n" +
                        "}";
            }else {
                return "{\n" +
                        "    \"auth\": {\n" +
                        "        \"debug\": true,\n" +
                        "        \"point\": 2759,\n" +
                        "        \"key\": 1,\n" +
                        "        \"hash\": \"e5a095b16b8a2106380686b23c5570ac\"\n" +
                        "    },\n" +
                        "    \"locale\": \"ua\",\n" +
                        "    \"external_order_id\": \"100\",\n" +
                        "    \"external_customer_id\": \"3\",\n" +
                        "    \"customer_ip_address\": \"10.10.0.0\",\n" +
                        "    \"account_id\": 2765,\n" +
                        "    \"wallet_id\": 2561,\n" +
                        "    \"service_id\": 3355,\n" +
                        "    \"amount\": 30200,\n" +
                        "    \"amount_currency\": \"INR\",\n" +
                        "    \"fields\": {\n" +
                        "        \"TxnMode\": \"NB\",\n" +
                        "        \"BeneficiaryEmail\": \"test@test.com\",\n" +
                        "        \"BeneficiaryMobile\": \"+919954321125\",\n" +
                        "        \"first_name\": \"John\",\n" +
                        "        \"last_name\": \"Doe\",\n" +
                        "        \"IFSC\": \"ICIC0001818\",\n" +
                        "        \"AccountNo\": \"38678978935\"\n" +
                        "    },\n" +
                        "    \"description\": \"\"\n" +
                        "}";
            }
        }
    }

}


