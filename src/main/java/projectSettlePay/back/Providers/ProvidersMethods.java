package projectSettlePay.back.Providers;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.log4j.Logger;

import static io.restassured.RestAssured.given;

abstract class ProvidersMethods {

    protected static Logger logger = Logger.getLogger(ProvidersMethods.class);
    protected Response response;
    void create(IProviders provider){
        logerName(provider);
        String urlRequest = String.format("https://api-stage-%s.backofficeweb.info/transaction/create",provider.getCore());
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
        String urlRequest = String.format("https://api-stage-%s.backofficeweb.info/transaction/pay",provider.getCore());
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
}
