package projectSettlePay.back.providers;

import projectSettlePay.front.P2PFrame;
import projectSettlePay.front.IFrame;

public class PayPort extends ProvidersMethods implements Pay_in, Pay_out {

    private int core = 4;
    private int conn = 4;
    private String body = "";

    public P2PFrame frame = new P2PFrame();

    public PayPort(String body){
        this.body = body;
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

    public static class PayPortBody{
        public static String defaultBody(boolean pay_in){
            return null;
        }
    }

}
