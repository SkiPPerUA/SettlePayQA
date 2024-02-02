package projectSettlePay.back.providers;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.log4j.Logger;

import static io.restassured.RestAssured.given;

abstract class ProvidersMethods {

    protected static Logger logger = Logger.getLogger(ProvidersMethods.class);
    protected Response response;
    void create(IProviders provider){
        logerName(provider);
        String urlRequest = getEnvironment(provider.getCore())+"/transaction/create";
        logger.info("URL - "+urlRequest);
        response = given()
                .contentType(ContentType.JSON)
                .body(provider.getBody())
                .when()
                .post(urlRequest);
        logger.info("Create - "+ getResponse());
    }

    void pay(IProviders provider){
        logerName(provider);
        String urlRequest = getEnvironment(provider.getCore())+"/transaction/pay";
        logger.info("URL - "+urlRequest);
        response = given()
                .contentType(ContentType.JSON)
                .body(provider.getBody())
                .when()
                .post(urlRequest);
        logger.info("Pay - "+ getResponse());
    }

    abstract String getResponse();

    private void logerName(IProviders providers){
        String name = providers.toString().replace("projectSettlePay.back.providers.","");
        logger.info("Provider = "+name.substring(0,name.indexOf("@")));
    }

    private String getEnvironment(int core){
        if (core == 1){
            return "https://api-new.backofficeweb.info/";
        }else {
            return String.format("https://api-stage-%s.backofficeweb.info",core);
        }
    }
}
