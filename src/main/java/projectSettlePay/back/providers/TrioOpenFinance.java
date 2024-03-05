package projectSettlePay.back.providers;

import projectSettlePay.back.Callback;
import projectSettlePay.front.IFrame;
import projectSettlePay.front.P2PFrame;
import projectSettlePay.helper.UuidGenerate;

public class TrioOpenFinance extends ProvidersMethods implements Pay_in {

    private int core = 1;
    private int conn = 1;
    private String body = "";

    private P2PFrame frame = new P2PFrame();

    public TrioOpenFinance(String body){
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

    public static class TrioOpenFinanceBody{
        public static String defaultBody() {
                return "{\n" +
                        "    \"auth\": {\n" +
                        "        \"debug\": true,\n" +
                        "        \"point\": 1,\n" +
                        "        \"key\": 1,\n" +
                        "        \"hash\": \"c9194644d4316e008a7afb0d50bf056b\"\n" +
                        "    },\n" +
                        "    \"locale\": \"en\",\n" +
                        "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                        "    \"account_id\": 4,\n" +
                        "    \"wallet_id\": 1867,\n" +
                        "    \"service_id\": 3078,\n" +
                        "    \"amount\": 100,\n" +
                        "    \"external_customer_id\": \"1263\",\n" +
                        "    \"amount_currency\": \"BRL\",\n" +
                        "    \"fields\": {\n" +
                        "        \"itn\": \"341.245.588-13\",\n" +
                        "        \"first_name\": \"rodrigo\",\n" +
                        "        \"last_name\": \"pacheco\"\n" +
                        "    },\n" +
                        "    \"point\": {}\n" +
                        "}";
        }
    }

}
