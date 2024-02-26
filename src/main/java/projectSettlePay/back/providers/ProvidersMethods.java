package projectSettlePay.back.providers;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.log4j.Logger;

import static io.restassured.RestAssured.given;

abstract class ProvidersMethods {

    protected static Logger logger = Logger.getLogger(ProvidersMethods.class);
    protected Response response;
    protected String id;

    void create(IProviders provider){
        logerName(provider);
        String urlRequest = getEnvironment(provider.getCore())+"/transaction/create";
        //logger.info("URL - "+urlRequest);
        response = given()
                .contentType(ContentType.JSON)
                .body(provider.getBody())
                .when()
                .post(urlRequest);
        logger.info("Create - "+ getResponse());
        id = String.valueOf(response.then().extract().response().jsonPath().getLong("response.id"));
    }

    void get_methods_list(IProviders provider){
        String urlRequest = getEnvironment(provider.getCore())+"/transaction/p2p/get-methods-list";
        //logger.info("URL - "+urlRequest);
        response = given()
                .contentType(ContentType.JSON)
                .body(provider.getBody())
                .when()
                .post(urlRequest);
        logger.info("Get methods list - "+ getResponse());
    }

    void confirm(IProviders provider){
        String urlRequest = getEnvironment(provider.getCore())+"/transaction/p2p/confirm";
        //logger.info("URL - "+urlRequest);
        response = given()
                .contentType(ContentType.JSON)
                .body(provider.getBody())
                .when()
                .post(urlRequest);
        logger.info("Confirm - "+ getResponse());
    }

    void cancel(IProviders provider){
        String urlRequest = getEnvironment(provider.getCore())+"/transaction/p2p/cancel";
        //logger.info("URL - "+urlRequest);
        response = given()
                .contentType(ContentType.JSON)
                .body(provider.getBody())
                .when()
                .post(urlRequest);
        logger.info("Cancel - "+ getResponse());
    }

    void get_recipient_card(IProviders provider){
        String urlRequest = getEnvironment(provider.getCore())+"/transaction/p2p/get-recipient-card";
        //logger.info("URL - "+urlRequest);
        response = given()
                .contentType(ContentType.JSON)
                .body(provider.getBody())
                .when()
                .post(urlRequest);
        logger.info("Get recipient card - "+ getResponse());
    }

    void pay(IProviders provider){
        logerName(provider);
        String urlRequest = getEnvironment(provider.getCore())+"/transaction/pay";
        //logger.info("URL - "+urlRequest);
        response = given()
                .contentType(ContentType.JSON)
                .body(provider.getBody())
                .when()
                .post(urlRequest);
        logger.info("Pay - "+ getResponse());
        id = String.valueOf(response.then().extract().response().jsonPath().getLong("response.id"));
    }

    abstract String getResponse();

    private void logerName(IProviders providers){
        String name = providers.toString().replace("projectSettlePay.back.providers.","");
        logger.info("Provider = "+name.substring(0,name.indexOf("@")));
    }

    private String getEnvironment(int core){
        if (core == 1){
            return "https://api-new.backofficeweb.info/";
            //return "https://gateway-api-server-stage.backofficeweb.info";
        }else {
            return String.format("https://api-stage-%s.backofficeweb.info",core);
            //return String.format("https://gateway-api-server-stage%s.backofficeweb.info", core);
        }
    }
}
