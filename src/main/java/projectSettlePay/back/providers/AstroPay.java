package projectSettlePay.back.providers;

import projectSettlePay.front.AstroPayFrame;
import projectSettlePay.front.IFrame;
import projectSettlePay.helper.UuidGenerate;

public class AstroPay extends ProvidersMethods implements Pay_in {

    private int core = 7;
    private int conn = 7;
    private String body = "";

    public IFrame frame = new AstroPayFrame();

    public AstroPay(String body){
        this.body = body;
    }

    @Override
    public void setBody(String body) {
        this.body = body;
    }


    public AstroPay(String body, boolean pix){
        this.body = body;
        if (pix) {
            conn = 4;
        }
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

    public static class AstroPayBody{
        public static String defaultBody(String method) {
            if (method.equals("pix")) {
                return "{\n" +
                        "    \"auth\": {\n" +
                        "        \"debug\": true,\n" +
                        "        \"point\": 2759,\n" +
                        "        \"key\": 1,\n" +
                        "        \"hash\": \"e5a095b16b8a2106380686b23c5570ac\"\n" +
                        "    },\n" +
                        "    \"locale\": \"ua\",\n" +
                        "    \"external_order_id\": \""+UuidGenerate.generateUUID()+"\",\n" +
                        "    \"external_customer_id\": \""+UuidGenerate.generateUUID()+"\",\n" +
                        "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                        "    \"account_id\": 2765,\n" +
                        "    \"wallet_id\": 3015,\n" +
                        "    \"service_id\": 4221,\n" +
                        "    \"amount\": 1000,\n" +
                        "    \"amount_currency\": \"BRL\",\n" +
                        "    \"fields\": {\n" +
                        "        \"country\": \"BR\",\n" +
                        "        \"email\":\"cadfa@fdas.das\",\n" +
                        "        \"phone\":\"34234242323\",\n" +
                        "        \"itn\":\"26040271\",\n" +
                        "        \"first_name\":\"name\",\n" +
                        "        \"last_name\":\"last\"\n" +
                        "    },\n" +
                        "    \"point\": {\n" +
                        "        \"redirect_url\": \"https://www.google.com/\"\n" +
                        "    },\n" +
                        "    \"description\": \"Test deposit\"\n" +
                        "    }";
            } else {
                return "{\n" +
                        "    \"auth\": {\n" +
                        "        \"debug\": true,\n" +
                        "        \"point\": 2759,\n" +
                        "        \"key\": 1,\n" +
                        "        \"hash\": \"e5a095b16b8a2106380686b23c5570ac\"\n" +
                        "    },\n" +
                        "    \"locale\": \"ua\",\n" +
                        "    \"external_order_id\": \"" + UuidGenerate.generateUUID() + "\",\n" +
                        "    \"external_customer_id\": \"" + UuidGenerate.generateUUID() + "\",\n" +
                        "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                        "    \"account_id\": 2765,\n" +
                        "    \"wallet_id\": 3554,\n" +
                        "    \"service_id\": 4217,\n" +
                        "    \"amount\": 1000,\n" +
                        "    \"amount_currency\": \"USD\",\n" +
                        "    \"fields\": {\n" +
                        "        \"country\": \"BR\"\n" +
                        "    },\n" +
                        "    \"point\": {\n" +
                        "        \"redirect_url\": \"https://www.google.com/\"\n" +
                        "    },\n" +
                        "    \"description\": \"Test deposit\"\n" +
                        "    }";
            }
        }
    }

}