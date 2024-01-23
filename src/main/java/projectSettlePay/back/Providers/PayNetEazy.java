package projectSettlePay.back.Providers;

public class PayNetEazy extends ProvidersMethods implements IProviders {

    private int core = 3;
    private int conn = 3;
    private String body = "";

    public PayNetEazy(String body){
        this.body = body;
    }

    public void pay_in(){
        create(this);
    }

    @Override
    public void pay_out() {
        //
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

    public String getResponce(){
        return response.then().extract().response().asString();
    }

    public String getPayURL(){
        return response.then().extract().response().jsonPath().get("response.result.pay_url");
    }

    public String getId(){
        return String.valueOf(response.then().extract().response().jsonPath().getLong("response.id"));
    }
}
