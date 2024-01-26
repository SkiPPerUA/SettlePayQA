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
        String urlRequest = getEnvinronment(provider.getCore())+"/transaction/create";
        logger.info("URL - "+urlRequest);
        response = given()
                .contentType(ContentType.JSON)
                .body(provider.getBody())
                .when()
                .post(urlRequest);
        logger.info("Create - "+getResponce());
    }

    void pay(IProviders provider){
        logerName(provider);
        String urlRequest = getEnvinronment(provider.getCore())+"/transaction/pay";
        logger.info("URL - "+urlRequest);
        response = given()
                .contentType(ContentType.JSON)
                .body(provider.getBody())
                .when()
                .post(urlRequest);
        logger.info("Pay - "+getResponce());
    }

    abstract String getResponce();

    private void logerName(IProviders providers){
        logger.info("Provider = "+providers.toString());
    }

    private String getEnvinronment(int core){
        if (core == 1){
            return "https://api-new.backofficeweb.info/";
        }else {
            return String.format("https://api-stage-%s.backofficeweb.info",core);
        }
    }
}