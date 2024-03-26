package projectSettlePay.back.providers;

import projectSettlePay.front.AcquiringFrame;
import projectSettlePay.front.IFrame;
import projectSettlePay.helper.BodyHelper;

public class P2Pay extends ProvidersMethods implements Pay_in, Pay_out {

    private int core = 4;
    private int conn = 4;
    private String body = "";

    public AcquiringFrame frame = new AcquiringFrame();

    public P2Pay(String body){
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

    public void pay_out(){
        pay(this);
    }

    public void get_methods_list(String body){
        this.body = body;
        super.get_methods_list(this);
    }

    public void get_recipient_card(String body){
        this.body = body;
        super.get_recipient_card(this);
    }

    public void confirm(String body){
        this.body = body;
        super.confirm(this);
    }

    public void cancel(String body){
        this.body = body;
        super.cancel(this);
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

    public static class P2PayBody{
        public static String defaultBody(boolean pay_in){
            if (pay_in){
                return "{\n" +
                        "    \"auth\": {\n" +
                        "        \"debug\": true,\n" +
                        "        \"point\": 2755,\n" +
                        "        \"key\": 1,\n" +
                        "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                        "    },\n" +
                        "    \"locale\": \"ua\",\n" +
                        "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                        "    \"external_customer_id\": \""+ BodyHelper.generateUUID()+"\",\n" +
                        "    \"account_id\": 2761,\n" +
                        "    \"wallet_id\": 2529,\n" +
                        "    \"service_id\": 3516,\n" +
                        "    \"amount\": 30377,\n" +
                        "    \"amount_currency\": \"UAH\",\n" +
                        "    \"fields\" : {\n" +
                        "        \"first_name\": \"sender_name\",\n" +
                        "        \"last_name\": \"sender_surname\"\n" +
                        "    }"+
                        "}";
            }else {
                return "{\n" +
                        "    \"auth\": {\n" +
                        "        \"debug\": true,\n" +
                        "        \"point\": 2755,\n" +
                        "        \"key\": 1,\n" +
                        "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                        "    },\n" +
                        "    \"locale\": \"ua\",\n" +
                        "    \"external_order_id\": \"954345387\",\n" +
                        "    \"external_customer_id\":  \""+BodyHelper.generateUUID()+"\",\n" +
                        "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                        "    \"account_id\": 2761,\n" +
                        "    \"wallet_id\": 2529,\n" +
                        "    \"service_id\": 3772,\n" +
                        "    \"amount\": 30000,\n" +
                        "    \"amount_currency\": \"UAH\",\n" +
                        "    \"fields\": {\n" +
                        "    \"card_number\": \"4000000011112222\"\n" +
                        "    }\n" +
                        "}";
            }
        }
    }

}

