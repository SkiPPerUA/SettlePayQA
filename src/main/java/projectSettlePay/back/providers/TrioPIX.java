package projectSettlePay.back.providers;

import projectSettlePay.back.Callback;
import projectSettlePay.front.IFrame;
import projectSettlePay.front.P2PFrame;
import projectSettlePay.helper.BodyHelper;

public class TrioPIX extends ProvidersMethods implements Pay_in, Pay_out {

    private int core = 1;
    private int conn = 1;
    private String body = "";

    private P2PFrame frame = new P2PFrame();

    public TrioPIX(String body){
        this.body = body;
    }

    @Override
    public void setBody(String body) {
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

    public void callback(){
        Callback callback = new Callback();
        String bodyCallback = "{\"external_id\": \""+getChildId()+"\", \"category\": \"collecting_document\", \"type\": \"settled\"}";
        callback.makeCallback("https://pay-forms-stage.backofficeweb.info/api/public/connector-stage-"+conn+"/callback/static-callback/trio",bodyCallback);
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
                        "    \"external_customer_id\": \""+BodyHelper.generateUUID()+"\",\n" +
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
