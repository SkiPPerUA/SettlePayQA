package projectSettlePay.back.providers;

import projectSettlePay.back.Callback;
import projectSettlePay.front.BanjoFrame;
import projectSettlePay.front.IFrame;

public class Banjo extends ProvidersMethods implements Pay_in, Pay_out {

    private int core = 5;
    private int conn = 5;
    private String body = "";

    public BanjoFrame frame = new BanjoFrame();

    public Banjo(String body){
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

    public void callback(String status){
        Callback callback = new Callback();
        long child = Long.valueOf(id) + 1;
        String bodyCallback = "{\"data\": \n" +
                "    {\"payload\": {\n" +
                "        \"amount\": 1,\n" +
                "        \"currency\": \"INR\", \n" +
                "        \"description\": \"Payin\", \n" +
                "        \"order_id\": \""+child+"\", \n" +
                "        \"status\": \"%s\", \n" + //REJECT  APPROVED
                "        \"transaction_id\": \"dd128dfd-41ba-4413-9e89-e3e7f7ef9831\"\n" +
                "        }, \n" +
                "    \"time\": \"2024-01-22T13:28:07.052Z\", \n" +
                "    \"type\": \"COLLECTION_UPDATES\"}, \n" +
                "    \"header\": {\n" +
                "        \"sign\": \"bzFOo1JDyM+shD5gKFxQg//a5rebhyjRu+MDPW95Ke1Oh/cHqGk5i2WAtO1XTZLS0BTWBoIKFkd4k9ukEbeow2YIVMEdLO+GCRMQ9L4BgRLy9jqj7ZYrYfgiXWOPI9KjxDCbB1Kyr4VwDRHfVZYP+i6UXQni232MR6dofW49OEI6e64lQh7u+n89LzlL3/UMoRGKVlrWnOyTnEtZO/VZU3dQjkykyAQHZPIVMtZ2ZIxiOz1oXE0xeZ5YpdRa3hjFiKl4Z7Hgd4TtRhExvjuNNJ/mVt0HV/alryfvAk1VI4a1CtMMugtAkg2xTJX6WS0i/7kUoR/nInXijRaaValjrA==\"}\n" +
                "}";
        callback.makeCallback("https://pay-stage-"+core+".backofficeweb.info/api/public/connector-stage-"+conn+"/callback/static-callback/payzeasy",String.format(bodyCallback,status));
    }

    public static class BanjoBody{
        public static String defaultBody(String type) {
            if (type.equals("h2h")) {
                return "{\n" +
                        "    \"auth\": {\n" +
                        "        \"debug\": true,\n" +
                        "        \"point\": 2756,\n" +
                        "        \"key\": 1,\n" +
                        "        \"hash\": \"339d4065e9b2c2c8a8647fb31cb7d225\"\n" +
                        "    },\n" +
                        "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                        "    \"account_id\": 2762,\n" +
                        "    \"wallet_id\": 3569,\n" +
                        "    \"service_id\": 3629,\n" +
                        "    \"amount\": 100,\n" +
                        "    \"amount_currency\": \"INR\",\n" +
                        "    \"fields\": {\n" +
                        "        \"vpa\": \"jaiodisha@ybl\", \n" +
                        "        \"TxnMode\": \"UPI\",\n" +
                        "        \"cust_name\": \"Kumar Ajay\",\n" +
                        "        \"cust_email\": \"KumarTest231891111@gmail.com\",\n" +
                        "        \"cust_mobile\": \"91 90111114991\"\n" +
                        "        }\n" +
                        "}";
            } else if (type.equals("payout")) {
                return "{\n" +
                        "    \"auth\": {\n" +
                        "        \"debug\": true,\n" +
                        "        \"point\": 2756,\n" +
                        "        \"key\": 1,\n" +
                        "        \"hash\": \"339d4065e9b2c2c8a8647fb31cb7d225\"\n" +
                        "    },\n" +
                        "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                        "    \"account_id\": 2762,\n" +
                        "    \"wallet_id\": 3569,\n" +
                        "    \"service_id\": 3627,\n" +
                        "    \"amount\": 100,\n" +
                        "    \"amount_currency\": \"INR\",\n" +
                        "     \"fields\": {\n" +
                        "        \"TxnMode\": \"IMPS\",\n" +
                        "        \"BeneficiaryEmail\": \"ansarali88@gmail.com\",\n" +
                        "        \"BeneficiaryMobile\": \"917042088572\",\n" +
                        "        \"AccHolderName\": \"Ayush Tyagi\",\n" +
                        "        \"Remark\": \"testремарЇХ є ₴ЄмЯ\",\n" +
                        "        \"country_code\":\"91\",\n" +
                        "        \"AccountNo\": \"398602010875308\",\n" +
                        "        \"IFSC\": \"UBIN0539864\",\n" +
                        "        \"BankName\": \"paytm\"\n" +
                        "     }\n" +
                        "}";
            } else if (type.equals("h2h UTR")) {
                return "{\n" +
                        "    \"auth\": {\n" +
                        "        \"debug\": true,\n" +
                        "        \"point\": 2756,\n" +
                        "        \"key\": 1,\n" +
                        "        \"hash\": \"339d4065e9b2c2c8a8647fb31cb7d225\"\n" +
                        "    },\n" +
                        "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                        "    \"locale\": \"en\",\n" +
                        "    \"account_id\": 2762,\n" +
                        "    \"external_customer_id\": \"1245364576\",\n" +
                        "    \"wallet_id\": 3569,\n" +
                        "    \"service_id\": 3630,\n" +
                        "    \"amount\": 100,\n" +
                        "    \"amount_currency\": \"INR\",\n" +
                        "    \"fields\": {\n" +
                        "        \"vpa\": \"jaiodisha@ybl\", \n" +
                        "        \"TxnMode\": \"UPI\",\n" +
                        "        \"cust_name\": \"Kumar Ajay\",\n" +
                        "        \"cust_email\": \"KumarTest@gmail.com\",\n" +
                        "        \"cust_mobile\": \"91 90111114991\"\n" +
                        "        }\n" +
                        "}";
            } else if (type.equals("hpp")){
                return "{\n" +
                        "    \"auth\": {\n" +
                        "        \"debug\": true,\n" +
                        "        \"point\": 2756,\n" +
                        "        \"key\": 1,\n" +
                        "        \"hash\": \"339d4065e9b2c2c8a8647fb31cb7d225\"\n" +
                        "    },\n" +
                        "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                        "    \"account_id\": 2762,\n" +
                        "    \"wallet_id\": 3569,\n" +
                        "    \"service_id\": 4185,\n" +
                        "    \"amount\": 2000000,\n" +
                        "    \"amount_currency\": \"INR\",\n" +
                        "    \"fields\": {\n" +
                        "        \"TxnMode\": \"UPI\",\n" +
                        "        \"cust_name\": \"Ayush Tyagi\",\n" +
                        "        \"cust_email\": \"ansarali88@gmail.com\",\n" +
                        "        \"cust_mobile\": \"91 7042088572\"\n" +
                        "        }\n" +
                        "}";
            }else {
                return "false";
            }
        }
    }

}
