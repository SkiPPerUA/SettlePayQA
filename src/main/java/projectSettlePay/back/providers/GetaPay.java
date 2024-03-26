package projectSettlePay.back.providers;

import projectSettlePay.back.Callback;
import projectSettlePay.front.IFrame;
import projectSettlePay.front.P2PFrame;

public class GetaPay extends ProvidersMethods implements Pay_in, Pay_out {

    private int core = 7;
    private int conn = 7;
    private String body = "";

    private P2PFrame frame = new P2PFrame();

    public GetaPay(String body){
        this.body = body;
    }

    public void pay_in(){
        create(this);
    }

    @Override
    public void setBody(String body) {
        this.body = body;
    }
    @Override
    public IFrame getFrame() {
        return frame;
    }

    @Override
    public int getCore() {
        return core;
    }

    public void callback(String status){
        Callback callback = new Callback();
        callback.makeCallback(callback.getResult_url(this),"{\"order_id\":\""+id+"\",\"payout_id\":\"1711375911205_YWQVPnsGRBfESstYyyWCJOyB\",\"payout_status\":\""+status+"\",\"amount\":\"500.00\",\"full_amount\":\"526.00\",\"amount_gate\":\"500.00\",\"status_code\":null,\"status_description\":null,\"currency_gate\":\"INR\",\"success\":true}");
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

    @Override
    public void pay_out() {
        pay(this);
    }

    public static class GetaPayBody{
        public static String defaultBody(boolean pay_in) {
            if (pay_in) {
                return "{\n" +
                        "    \"auth\": {\n" +
                        "        \"debug\": true,\n" +
                        "        \"point\": 2759,\n" +
                        "        \"key\": 1,\n" +
                        "        \"hash\": \"e5a095b16b8a2106380686b23c5570ac\"\n" +
                        "    },\n" +
                        "    \"locale\": \"ua\",\n" +
                        "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                        "    \"account_id\": 2765,\n" +
                        "    \"wallet_id\": 2561,\n" +
                        "    \"service_id\": 3859,\n" +
                        "    \"amount\": 10500,\n" +
                        "    \"amount_currency\": \"INR\",\n" +
                        "    \"fields\": {\n" +
                        "        \"cust_mobile\": \"0664187456\",\n" +
                        "        \"cust_email\": \"user@gmail.com\",\n" +
                        "        \"first_name\": \"Andromeda\",\n" +
                        "        \"last_name\": \"Galaxy\"\n" +
                        "    }\n" +
                        "}";
            }else {
                return "{\n" +
                        "    \"auth\": {\n" +
                        "        \"debug\": true,\n" +
                        "        \"point\": 2759,\n" +
                        "        \"key\": 1,\n" +
                        "        \"hash\": \"e5a095b16b8a2106380686b23c5570ac\"\n" +
                        "    },\n" +
                        "    \"locale\": \"en\",\n" +
                        "    \"external_order_id\": \"954345387\",\n" +
                        "    \"external_customer_id\": \"5d8aac94-7784-45cf-a212-84bae39600ed\",\n" +
                        "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                        "    \"account_id\": 2765,\n" +
                        "    \"wallet_id\": 3234,\n" +
                        "    \"service_id\": 4461,\n" +
                        "    \"amount\": 50000,\n" +
                        "    \"amount_currency\": \"INR\",\n" +
                        "\t  \"fields\": {\n" +
                        "        \"AccountNo\": \"398602010875308\",\n" +
                        "        \"Remark\": \"description\",\n" +
                        "        \"BeneficiaryMobile\": \"+917042088572\",\n" +
                        "        \"BeneficiaryEmail\": \"AyushTyagi89@gmail.com\",\n" +
                        "        \"first_name\": \"Ayush\",\n" +
                        "        \"last_name\": \"Tyagi\",\n" +
                        "        \"IFSC\": \"UBIN0539864\"\n" +
                        "    } \n" +
                        "}";
            }
        }
    }

}
