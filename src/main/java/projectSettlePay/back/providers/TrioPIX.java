package projectSettlePay.back.providers;

import projectSettlePay.back.Callback;
import projectSettlePay.front.IFrame;
import projectSettlePay.front.P2PFrame;
import projectSettlePay.helper.UuidGenerate;

public class TrioPIX extends ProvidersMethods implements Pay_in, Pay_out {

    private int core = 1;
    private int conn = 1;
    private String body = "";

    private P2PFrame frame = new P2PFrame();

    public TrioPIX(String body){
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

    @Override
    public void pay_out() {
        pay(this);
    }

    public void callback(boolean pay_in, String status){
        Callback callback = new Callback();
        String bodyCallback;
        if (pay_in){
            bodyCallback = "{\n" +
                    "  \"webhook_response\": {\n" +
                    "    \"event_type\": \"TRANSACTION\",\n" +
                    "    \"data\": {\n" +
                    "      \"transaction_id\": \"1646338380\",\n" +
                    "      \"order_id\": \""+id+"\",\n" +
                    "      \"app_id\": \"APP_SAHSEAOCSItlax3148\",\n" +
                    "      \"transaction_amount\": \"99.0\",\n" +
                    "      \"status\": \"%s\",\n" +
                    "      \"bank_rrn\": \"326541980489\",\n" +
                    "      \"bank_res_msg\": \"Order Created\",\n" +
                    "      \"payment_method\": \"UPI\"\n" +
                    "    }\n" +
                    "  }\n" +
                    "}";
        }else {
            bodyCallback = "{\n" +
                    "  \"webhook_response\": {\n" +
                    "    \"event_type\": \"PAYOUT\",\n" +
                    "    \"data\": {\n" +
                    "      \"payout_id\": \"U97001742171\",\n" +
                    "      \"ref_id\": \""+id+"\",\n" +
                    "      \"app_id\": \"APP_SAHSEAOCSItlax3148\",\n" +
                    "      \"payout_amount\": \"1.00\",\n" +
                    "      \"status\": \"%s\",\n" +
                    "      \"bank_rrn\": \"326541980489\",\n" +
                    "      \"bank_res_msg\": \"Transaction SUCCESS\",\n" +
                    "      \"payment_method\": \"UPI\"\n" +
                    "    }\n" +
                    "  }\n" +
                    "}";
        }
        callback.makeCallback("https://pay-stage-"+core+".backofficeweb.info/api/public/connector-stage-"+conn+"/callback/static-callback/payzeasy",String.format(bodyCallback,status));
    }

    public static class TrioPIXBody{
        public static String defaultBody(boolean pay_in) {
            if (pay_in) {
                return "{\n" +
                        "    \"auth\": {\n" +
                        "        \"debug\": true,\n" +
                        "        \"point\": 2752,\n" +
                        "        \"key\": 1,\n" +
                        "        \"hash\": \"a271e8381665cdc6e5f251731b639cba\"\n" +
                        "    },\n" +
                        "    \"locale\": \"ua\",\n" +
                        "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                        "    \"account_id\": 2757,\n" +
                        "    \"wallet_id\": 3233,\n" +
                        "    \"service_id\": 4304,\n" +
                        "    \"amount\": 200,\n" +
                        "    \"external_customer_id\": \"12552263\",\n" +
                        "    \"amount_currency\": \"BRL\",\n" +
                        "    \"fields\": {\n" +
                        "        \"itn\": \"47.772.746/8055-09\"\n" +
                        "    },\n" +
                        "    \"point\": {}\n" +
                        "}";
            }else {
                return "{\n" +
                        "    \"auth\": {\n" +
                        "        \"debug\": true,\n" +
                        "        \"point\": 2752,\n" +
                        "        \"key\": 1,\n" +
                        "        \"hash\": \"a271e8381665cdc6e5f251731b639cba\"\n" +
                        "    },\n" +
                        "    \"locale\": \"ua\",\n" +
                        "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                        "    \"account_id\": 2757,\n" +
                        "    \"wallet_id\": 3621,\n" +
                        "    \"service_id\": 4305,\n" +
                        "    \"amount\": 5,\n" +
                        "    \"external_customer_id\": \""+UuidGenerate.generateUUID()+"\",\n" +
                        "    \"amount_currency\": \"BRL\",\n" +
                        "    \"fields\": {\n" +
                        "        \"pix_key\": \"dded141e-dee1-4eda-9f2b-ba24fa55f1c3\"\n" +
                        "    },\n" +
                        "    \"point\": {\n" +
                        "        \"callback_url\": \"https://httpstat.us/503\"\n" +
                        "    }\n" +
                        "}";
            }
        }
    }

}
