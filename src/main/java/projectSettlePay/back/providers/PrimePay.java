package projectSettlePay.back.providers;

import projectSettlePay.front.IFrame;
import projectSettlePay.front.AcquiringFrame;

public class PrimePay extends ProvidersMethods implements Pay_in, Pay_out {

    private int core = 7;
    private int conn = 7;
    private String body = "";

    private AcquiringFrame frame = new AcquiringFrame();

    public PrimePay(String body){
        this.body = body;
    }

    public void pay_in(){
        create(this);
    }

    @Override
    public void setBody(String body) {
        this.body = body;
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

    public static class PrimePayBody{
        public static String defaultBody(boolean pay_in){
            if (pay_in){
            return "{\n" +
                    "    \"auth\": {\n" +
                    "        \"debug\": true,\n" +
                    "        \"point\": 2759,\n" +
                    "        \"key\": 1,\n" +
                    "        \"hash\": \"e5a095b16b8a2106380686b23c5570ac\"\n" +
                    "    },\n" +
                    "    \"locale\": \"ru\",\n" +
                    "    \"external_order_id\": \"100\",\n" +
                    "    \"external_customer_id\": \"1233\",\n" +
                    "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                    "    \"account_id\": 2765,\n" +
                    "    \"wallet_id\": 2954,\n" +
                    "    \"service_id\": 3860,\n" +
                    "    \"amount\": 100,\n" +
                    "    \"amount_currency\": \"EUR\"\n" +
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
                        "    \"external_order_id\": \"987\",\n" +
                        "    \"external_customer_id\": \"01257456\",\n" +
                        "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                        "    \"account_id\": 2765,\n" +
                        "    \"wallet_id\": 2954,\n" +
                        "    \"service_id\": 3861,\n" +
                        "    \"amount\": 200,\n" +
                        "    \"amount_currency\": \"EUR\",\n" +
                        "    \"fields\": {\n" +
                        "    \"card_number\": \"4012000000003010\",\n" +
                        "    \"card_exp_month\": \"03\",\n" +
                        "    \"card_exp_year\": \"26\",\n" +
                        "    \"card_cvv\": \"123\"\n" +
                        "    }\n" +
                        "}";
            }
        }
    }

}
