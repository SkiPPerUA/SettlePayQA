package projectSettlePay.back.providers;

import projectSettlePay.back.Callback;
import projectSettlePay.front.IFrame;
import projectSettlePay.front.P2PFrame;
import projectSettlePay.helper.UuidGenerate;

public class PayzEasy extends ProvidersMethods implements Pay_in, Pay_out {

    private int core = 6;
    private int conn = 6;
    private String body = "";

    private P2PFrame frame = new P2PFrame();

    public PayzEasy(String body){
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
        long id_child = Long.valueOf(id)+1;
        if (pay_in){
            bodyCallback = "{\n" +
                    "  \"webhook_response\": {\n" +
                    "    \"event_type\": \"TRANSACTION\",\n" +
                    "    \"data\": {\n" +
                    "      \"transaction_id\": \"1646338380\",\n" +
                    "      \"order_id\": \""+id_child+"\",\n" +
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
                    "      \"ref_id\": \""+id_child+"\",\n" +
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
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        callback.makeCallback("https://pay-stage-"+core+".backofficeweb.info/api/public/connector-stage-"+conn+"/callback/static-callback/payzeasy",String.format(bodyCallback,status));
    }

    public static class PayzEasyBody{
        public static String defaultBody(boolean pay_in) {
            if (pay_in) {
                return "{\n" +
                        "    \"auth\": {\n" +
                        "        \"debug\": true,\n" +
                        "        \"point\": 2757,\n" +
                        "        \"key\": 1,\n" +
                        "        \"hash\": \"73fa095174d9e9653f7f4b203d6fe122\"\n" +
                        "    },\n" +
                        "    \"locale\": \"en\",\n" +
                        "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                        "    \"external_customer_id\": \""+ UuidGenerate.generateUUID() +"\",\n" +
                        "    \"account_id\": 2763,\n" +
                        "    \"wallet_id\": 3019,\n" +
                        "    \"service_id\": 3801,\n" +
                        "    \"amount\": 10100,\n" +
                        "    \"amount_currency\": \"INR\",\n" +
                        "    \"fields\": {\n" +
                        "    }\n" +
                        "}";
            }else {
                return "{\n" +
                        "    \"auth\": {\n" +
                        "        \"debug\": true,\n" +
                        "        \"point\": 2757,\n" +
                        "        \"key\": 1,\n" +
                        "        \"hash\": \"73fa095174d9e9653f7f4b203d6fe122\"\n" +
                        "    },\n" +
                        "    \"locale\": \"en\",\n" +
                        "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                        "    \"external_customer_id\": \""+UuidGenerate.generateUUID()+"\",\n" +
                        "    \"account_id\": 2763,\n" +
                        "    \"wallet_id\": 3019,\n" +
                        "    \"service_id\": 3802,\n" +
                        "    \"amount\": 100,\n" +
                        "    \"amount_currency\": \"INR\",\n" +
                        "    \"fields\": {\n" +
                        "        \"IFSC\": \"RATN0000377\",\n" +
                        "        \"AccountNo\": \"409001907446\",\n" +
                        "        \"AccHolderName\":  \"AAYAN ENTERPRISES\"\n" +
                        "    }\n" +
                        "}";
            }
        }
    }

}
