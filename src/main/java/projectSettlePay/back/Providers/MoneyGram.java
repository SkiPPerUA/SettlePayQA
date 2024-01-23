package projectSettlePay.back.Providers;

public class MoneyGram extends ProvidersMethods implements IProviders {

    private int core = 4;
    private int conn = 4;
    private String body = "";

    public MoneyGram(String body){
        this.body = body;
    }

    public void pay_in(){
    }

    public void pay_out(){
        pay(this);
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
