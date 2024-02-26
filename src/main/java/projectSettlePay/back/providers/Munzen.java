package projectSettlePay.back.providers;

import projectSettlePay.back.Callback;

public class Munzen extends ProvidersMethods implements Pay_out{
    private int core = 3;
    private int conn = 3;
    private String body = "";

    public Munzen(String body){
        this.body = body;
    }

    public void pay_out(){
        pay(this);
    }

    @Override
    public int getCore() {
        return core;
    }

    public void makeCallback(String body){
        Callback callback = new Callback();
        callback.makeCallback(callback.getResult_url(this),body);
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
        return id;
    }

    @Override
    public int getConn() {
        return conn;
    }

    public static class MunzenBody{
        public static String defaultBody(){
                return "";
        }
    }
}
