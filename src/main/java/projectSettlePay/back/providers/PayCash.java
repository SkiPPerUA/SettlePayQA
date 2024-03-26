package projectSettlePay.back.providers;

import projectSettlePay.back.Callback;
import projectSettlePay.front.IFrame;
import projectSettlePay.front.PayCashFrame;

public class PayCash extends ProvidersMethods implements Pay_in{

    private int core = 2;
    private int conn = 8;
    private String body = "";

    public PayCashFrame frame = new PayCashFrame();

    public PayCash(String body){
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

    @Override
    public void setCore(int core){
        this.core = core;
    }

    @Override
    public String getBody(){
        return body;
    }

    public void callback(int status){
        Callback callback = new Callback();
        callback.makeCallback("https://pay-stage-"+core+".backofficeweb.info/api/public/connector-stage-"+conn+"/callback/static-callback/paycash",
                "{\n" +
                        "    \"payment\": {\n" +
                        "        \"FechaCreacion\": \"2024-03-18T06:33:34\",\n" +
                        "        \"FechaConfirmation\": \"2024-03-19T10:57:21\",\n" +
                        "        \"FechaVencimiento\": \"2024-03-21T00:00:00.0000000-06:00\",\n" +
                        "        \"Folio\": 12615499,\n" +
                        "        \"Resultado\": "+status+",\n" + //0
                        "        \"Tipo\": 1,\n" +
                        "        \"Emisor\": 402,\n" +
                        "        \"Secuencia\": 1,\n" +
                        "        \"Monto\": 13200,\n" +
                        "        \"Fecha\": \"2024-03-19\",\n" +
                        "        \"Hora\": \"10:57:21\",\n" +
                        "        \"Autorizacion\": \"1710867406\",\n" +
                        "        \"Referencia\": \"1402263300011\",\n" +
                        "        \"Value\": \""+id+"\"\n" +
                        "    }\n" +
                        "}");
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

    public static class PayCashBody{
        public static String defaultBody(){
            return "{\n" +
                    "    \"auth\": {\n" +
                    "        \"debug\": true,\n" +
                    "        \"point\": 2758,\n" +
                    "        \"key\": 1,\n" +
                    "        \"hash\": \"c2fdd7fbcab864d08c58ae7e7ca8b063\"\n" +
                    "    },\n" +
                    "    \"locale\": \"ua\",\n" +
                    "    \"external_order_id\": \"100\",\n" +
                    "    \"external_customer_id\": \"1213\",\n" +
                    "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                    "    \"account_id\": 2764,\n" +
                    "    \"wallet_id\": 3404, \n" +
                    "    \"service_id\": 3895,\n" +
                    "    \"description\" : \"test\",\n" +
                    "    \"amount\": 1320000,\n" +
                    "    \"amount_currency\": \"MXN\",\n" +
                    "    \"fields\" : {\n" +
                    "        \"country\" : \"MX\"\n" +
                    "    }\n" +
                    "}";
        }
    }

}

