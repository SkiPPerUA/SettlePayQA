package projectSettlePay.back.providers;

import projectSettlePay.back.Callback;
import projectSettlePay.back.TransInfoCore;
import projectSettlePay.front.IFrame;
import projectSettlePay.front.PayPortHPPframe;
import projectSettlePay.helper.UuidGenerate;

import java.util.HashMap;
import java.util.Map;

public class PayPortHPP extends ProvidersMethods implements Pay_in, Pay_out {

    private int core = 6;
    private int conn = 6;
    private String body = "";

    public PayPortHPPframe frame = new PayPortHPPframe();

    public PayPortHPP(String body){
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

    public void callback(String status){
        callback(status, new TransInfoCore(Long.valueOf(id)).getAmount());
    }

    public void callback(String status, float amount){
        Map<String, String> body = new HashMap<>();
        body.put("invoice_id", "90795");
        body.put("merchant_id", "663");
        body.put("order_id", getChildId());
        body.put("amount", "2.356712");
        body.put("amount_currency", String.valueOf(amount));
        body.put("currency", "BDT");
        body.put("order_desc", "Order"+getChildId());
        body.put("merchant_amount", "2.215309");
        body.put("status", status);
        body.put("account_info", "01204358565");
        body.put("fiat_currency", "BDT");
        body.put("fiat_amount", String.valueOf(amount));
        body.put("payment_info", "BDT Test");
        body.put("payment_system_type", "by_mobile");
        body.put("signature", "10a192e348fe4586106b47b28f7472d74aef1407");
        if (status.equals("-1")){
            body.put("cancellation_reason","INVALID_DETAILS");
        }
        Callback callback = new Callback();
        callback.makeCallback(callback.getResult_url(this),body);
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
        create(this);
    }

    public static class PayPortHPPBody{
        public static String defaultBody(boolean pay_in) {
            if (pay_in) {
                return "{\n" +
                        "    \"auth\": {\n" +
                        "        \"debug\": true,\n" +
                        "        \"point\": 2757,\n" +
                        "        \"key\": 1,\n" +
                        "        \"hash\": \"73fa095174d9e9653f7f4b203d6fe122\"\n" +
                        "    },\n" +
                        "    \"locale\": \"ua\",\n" +
                        "    \"external_order_id\": \"12345673489011\",\n" +
                        "    \"external_customer_id\": \"" + UuidGenerate.generateUUID() + "\",\n" +
                        "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                        "    \"account_id\": 2763,\n" +
                        "    \"wallet_id\": 3806,\n" +
                        "    \"service_id\": 4379,\n" +
                        "    \"amount\": 30000,\n" +
                        "    \"amount_currency\": \"BDT\",\n" +
                        "    \"fields\": {\n" +
                        "         \"email\":\"cadfa@fdas.das\",\n" +
                        "         \"first_name\":\"name\",\n" +
                        "         \"last_name\":\"last\"\n" +
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
                        "    \"external_order_id\": \"954345387\",\n" +
                        "    \"external_customer_id\": \""+UuidGenerate.generateUUID()+"\",\n" +
                        "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                        "    \"account_id\": 2763,\n" +
                        "    \"wallet_id\": 3806,\n" +
                        "    \"service_id\": 4412,\n" +
                        "    \"amount\": 50000,\n" +
                        "    \"amount_currency\": \"BDT\",\n" +
                        "     \"fields\": {\n" +
                        "        \"email\": \"cadfa@fdas.das\",\n" +
                        "        \"first_name\": \"name\",\n" +
                        "        \"last_name\": \"last\"\n" +
                        "    } \n" +
                        "}";
            }
        }
    }

}
