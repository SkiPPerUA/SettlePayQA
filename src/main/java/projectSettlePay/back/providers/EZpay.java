package projectSettlePay.back.providers;

import org.testng.Assert;
import projectSettlePay.front.AcquiringFrame;
import projectSettlePay.front.IFrame;

public class EZpay extends ProvidersMethods implements Pay_in, Pay_out {

    private int core = 4;
    private int conn = 4;
    private String body = "";

    public AcquiringFrame frame = new AcquiringFrame();

    public EZpay(String body, String method){
        this.body = body;
        if (method.equals("p2p")){
            frame = null;
        }
    }

    public void pay_in(){
        create(this);
    }

    public void pay_out(){
        pay(this);
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

    public static class EZpayBody{
        public static String defaultBody(String method){
            if (method.equals("p2p")){
                return "{\n" +
                        "    \"auth\": {\n" +
                        "        \"debug\": true,\n" +
                        "        \"point\": 2755,\n" +
                        "        \"key\": 1,\n" +
                        "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                        "    },\n" +
                        "    \"locale\": \"ru\",\n" +
                        "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                        "    \"external_customer_id\": \"2176127617646\",\n" +
                        "    \"account_id\": 2761,\n" +
                        "    \"wallet_id\": 2529,\n" +
                        "    \"service_id\": 3488,\n" +
                        "    \"amount\": 1000,\n" +
                        "    \"amount_currency\": \"UAH\",\n" +
                        "    \"fields\": {\n" +
                        "        \"sender_account\": \"User test\"\n" +
                        "    }\n" +
                        "}";
            }else if (method.equals("acquiring")){
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
                        "    \"wallet_id\": 2529,\n" +
                        "    \"service_id\": 3489,\n" +
                        "    \"amount\": 10100,\n" +
                        "    \"amount_currency\": \"UAH\"\n" +
                        "}";
            }else if (method.equals("pay_in")){
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
                        "    \"wallet_id\": 2529,\n" +
                        "    \"service_id\": 3489,\n" +
                        "    \"amount\": 10100,\n" +
                        "    \"amount_currency\": \"UAH\",\n" +
                        "    \"fields\": {\n" +
                        "    \"card_number\": \"4441111151836545\",\n" +
                        "    \"expire_month\": \"02\",\n" +
                        "    \"expire_year\": \"2028\",\n" +
                        "    \"cvv\": \"401\",\n" +
                        "    \"first_name\": \"\",\n" +
                        "    \"last_name\": \"\"\n" +
                        "    }\n" +
                        "}";
            }else if(method.equals("pay_out")){
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
                        "    \"wallet_id\": 2529,\n" +
                        "    \"service_id\": 3487,\n" +
                        "    \"amount\": 2000,\n" +
                        "    \"amount_currency\": \"UAH\",\n" +
                        "    \"fields\": {\n" +
                        "        \"recipient_bank\": \"bank\",\n" +
                        "        \"card_number\": \"4000000000000010\",\n" +
                        "        \"recipient_name\": \"test\"\n" +
                        "    }\n" +
                        "}";
            } else if (method.equals("h2h")) {
                return "{\n" +
                        "    \"auth\": {\n" +
                        "        \"debug\": true,\n" +
                        "        \"point\": 2755,\n" +
                        "        \"key\": 1,\n" +
                        "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                        "    },\n" +
                        "    \"locale\": \"ua\",\n" +
                        "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                        "    \"external_customer_id\": \"65865403\",\n" +
                        "    \"account_id\": 2761,\n" +
                        "    \"wallet_id\": 2529,\n" +
                        "    \"service_id\": 3472,\n" +
                        "    \"amount\": 10000,\n" +
                        "    \"amount_currency\": \"UAH\",\n" +
                        "    \"fields\" : {\n" +
                        "      \"first_name\": \"Proxima\",\n" +
                        "      \"last_name\": \"Centauris\"\n" +
                        "    }\n" +
                        "}";
            } else {
                Assert.fail(method+ " не корректен");
                return "";
            }
        }
    }

}
