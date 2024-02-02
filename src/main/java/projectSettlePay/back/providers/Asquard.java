package projectSettlePay.back.providers;

import org.testng.Assert;
import projectSettlePay.front.AcquiringFrame;
import projectSettlePay.front.IFrame;
import projectSettlePay.helper.UuidGenerate;

public class Asquard extends ProvidersMethods implements Pay_in, Pay_out {

    private int core;
    private int conn;
    private String body = "";

    public AcquiringFrame frame = new AcquiringFrame();

    public Asquard(String body, String method){
        this.body = body;
        if (method.equals("pay_in")){
            core = 1;
            conn = 1;
        }else if (method.equals("pay_out")){
            core = 2;
            conn = 7;
        }else if (method.equals("boletto")){
            core = 4;
            conn = 5;
        }else if (method.equals("upi")){
            core = 4;
            conn = 4;
        }else {
            Assert.fail(method+ " не корректен");
        }
    }

    public void pay_in(){
        create(this);
    }

    public void pay_out(){
        pay(this);
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

    public static class AsquardBody{
        public static String defaultBody(String method){
            if (method.equals("pay_in")){
            return "{\n" +
                    "    \"auth\": {\n" +
                    "        \"debug\": true,\n" +
                    "        \"point\": 1,\n" +
                    "        \"key\": 1,\n" +
                    "        \"hash\": \"c9194644d4316e008a7afb0d50bf056b\"\n" +
                    "    },\n" +
                    "    \"locale\": \"ua\",\n" +
                    "    \"external_order_id\": \"100\",\n" +
                    "    \"external_customer_id\": \""+UuidGenerate.generateUUID()+"\",\n" +
                    "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                    "    \"account_id\": 4,\n" +
                    "    \"wallet_id\": 184,\n" +
                    "    \"service_id\": 3336,\n" +
                    "    \"amount\": 100,\n" +
                    "    \"description\" : \"test roman crypto\",\n" +
                    "    \"amount_currency\": \"USD\" ,\n" +
                    "    \"point\" : {\n" +
                    "        \"success_url\" : \"https://google.com\"\n" +
                    "    }\n" +
                    "}";
        }else if (method.equals("pay_out")){
                return "{\n" +
                        "    \"auth\": {\n" +
                        "        \"debug\": true,\n" +
                        "        \"point\": 1,\n" +
                        "        \"key\": 1,\n" +
                        "        \"hash\": \"c9194644d4316e008a7afb0d50bf056b\"\n" +
                        "    },\n" +
                        "    \"locale\": \"ua\",\n" +
                        "    \"external_order_id\": \"1234567890\",\n" +
                        "    \"external_customer_id\": \"178a122e-11bb-17jy-33c9-jjra2709c66s\",\n" +
                        "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                        "    \"account_id\": 4,\n" +
                        "    \"wallet_id\": 151,\n" +
                        "    \"service_id\": 1257,\n" +
                        "    \"amount\": 500,\n" +
                        "    \"amount_currency\": \"USDT\",\n" +
                        "    \"fields\": {\n" +
                        "        \"account\": \"TCHQ4t3QXBUMeHsGvJFJULrV5fPPe1xdMH\"\n" +
                        "    }\n" +
                        "}";
            }else if (method.equals("boletto")){
                return "{\n" +
                        "    \"auth\": {\n" +
                        "        \"debug\": true,\n" +
                        "        \"point\": 2755,\n" +
                        "        \"key\": 1,\n" +
                        "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                        "    },\n" +
                        "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                        "    \"external_customer_id\": \""+ UuidGenerate.generateUUID() +"\",\n" +
                        "    \"locale\": \"en\",\n" +
                        "    \"account_id\": 2761,\n" +
                        "    \"wallet_id\": 2595,\n" +
                        "    \"service_id\": 3451,\n" +
                        "    \"amount\": 1000,\n" +
                        "    \"amount_currency\": \"BRL\",\n" +
                        "    \"fields\": {\n" +
                        "        \"cust_itn\": \"40084082666\",\n" +
                        "        \"cust_name\":\"Andromeda Galazy\",\n" +
                        "        \"cust_email\": \"andro@gmail.com\"\n" +
                        "    }\n" +
                        "}";
            }else if(method.equals("upi")){
                return "{\n" +
                        "    \"auth\": {\n" +
                        "        \"debug\": true,\n" +
                        "        \"point\": 2755,\n" +
                        "        \"key\": 1,\n" +
                        "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                        "    },\n" +
                        "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                        "    \"account_id\": 2761,\n" +
                        "    \"wallet_id\": 2598,\n" +
                        "    \"service_id\": 3478,\n" +
                        "    \"amount\": 10000,\n" +
                        "    \"amount_currency\": \"INR\",\n" +
                        "    \"fields\": {\n" +
                        "        \"cust_email\": \"yvamshi995@gmail.com\",\n" +
                        "        \"vpa\": \"test@upi\", \n" +
                        "        \"cust_name\": \"Test test\"\n" +
                        "        }\n" +
                        "}";
            }else {
                Assert.fail(method+ " не корректен");
                return "";
            }
        }
    }

}
