package projectSettlePayTests.back.providersTests.TwoPayller;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import projectSettlePay.BaseTest;
import projectSettlePay.back.providers.TwoPayler;
import projectSettlePay.core.Session;

@Test
public class TwoPayllerQuazAquairing extends BaseTest {

    TwoPayler twoPayler;
    String body;

    public void testPanSuccessNo3DS(){
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2754,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"01607df2a07b633ea35a909152ba6061\"\n" +
                "    },\n" +
                "    \"locale\": \"pt\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"external_customer_id\": \"1263222\",\n" +
                "    \"account_id\": 2760,\n" +
                "    \"wallet_id\": 2530,\n" +
                "    \"service_id\": 4212,\n" +
                "    \"amount\": 50000,\n" +
                "    \"amount_currency\": \"UAH\",\n" +
                "    \"description\": \"ттестоВий Опис\",\n" +
                "    \"fields\": {\n" +
                "        \"first_name\": \"В'ячеслав\",\n" +
                "        \"last_name\": \"ййййй\"\n" +
                "    },\n" +
                "    \"point\": {\n" +
                "        \"callback_url\": \"https://google.com/\",\n" +
                "        \"return_url\": \"https://google.com/\"\n" +
                "    }\n" +
                "}";
        twoPayler = new TwoPayler(body);
        twoPayler.pay_in();
        Session.getDriver().get(twoPayler.getPayURL());
        twoPayler.frame.setCard("4011111111111112");
        twoPayler.frame.setExpire("1028");
        twoPayler.frame.setCvv("111");
        twoPayler.frame.submit();
        showAgoraURL(twoPayler.getId());
    }

    public void testPanSuccess3DS() throws InterruptedException {
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2754,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"01607df2a07b633ea35a909152ba6061\"\n" +
                "    },\n" +
                "    \"locale\": \"pt\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"external_customer_id\": \"1263222\",\n" +
                "    \"account_id\": 2760,\n" +
                "    \"wallet_id\": 2530,\n" +
                "    \"service_id\": 4212,\n" +
                "    \"amount\": 50000,\n" +
                "    \"amount_currency\": \"UAH\",\n" +
                "    \"description\": \"ттестоВий Опис\",\n" +
                "    \"fields\": {\n" +
                "        \"first_name\": \"В'ячеслав\",\n" +
                "        \"last_name\": \"ййййй\"\n" +
                "    },\n" +
                "    \"point\": {\n" +
                "        \"callback_url\": \"https://google.com/\",\n" +
                "        \"return_url\": \"https://google.com/\"\n" +
                "    }\n" +
                "}";
        twoPayler = new TwoPayler(body);
        twoPayler.pay_in();
        Session.getDriver().get(twoPayler.getPayURL());
        twoPayler.frame.setCard("4111111111111111");
        twoPayler.frame.setExpire("1028");
        twoPayler.frame.setCvv("111");
        twoPayler.frame.submit();
        Thread.sleep(60000);
        showAgoraURL(twoPayler.getId());
    }

    public void testPanError3DS() throws InterruptedException {
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2754,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"01607df2a07b633ea35a909152ba6061\"\n" +
                "    },\n" +
                "    \"locale\": \"pt\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"external_customer_id\": \"1263222\",\n" +
                "    \"account_id\": 2760,\n" +
                "    \"wallet_id\": 2530,\n" +
                "    \"service_id\": 4212,\n" +
                "    \"amount\": 50000,\n" +
                "    \"amount_currency\": \"UAH\",\n" +
                "    \"description\": \"ттестоВий Опис\",\n" +
                "    \"fields\": {\n" +
                "        \"first_name\": \"В'ячеслав\",\n" +
                "        \"last_name\": \"ййййй\"\n" +
                "    },\n" +
                "    \"point\": {\n" +
                "        \"callback_url\": \"https://google.com/\",\n" +
                "        \"return_url\": \"https://google.com/\"\n" +
                "    }\n" +
                "}";
        twoPayler = new TwoPayler(body);
        twoPayler.pay_in();
        Session.getDriver().get(twoPayler.getPayURL());
        twoPayler.frame.setCard("4811111111111114");
        twoPayler.frame.setExpire("1028");
        twoPayler.frame.setCvv("111");
        twoPayler.frame.submit();
        Thread.sleep(60000);
        showAgoraURL(twoPayler.getId());
    }

    public void testPanErrorNo3DS(){
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2754,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"01607df2a07b633ea35a909152ba6061\"\n" +
                "    },\n" +
                "    \"locale\": \"pt\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"external_customer_id\": \"1263222\",\n" +
                "    \"account_id\": 2760,\n" +
                "    \"wallet_id\": 2530,\n" +
                "    \"service_id\": 4212,\n" +
                "    \"amount\": 50000,\n" +
                "    \"amount_currency\": \"UAH\",\n" +
                "    \"description\": \"ттестоВий Опис\",\n" +
                "    \"fields\": {\n" +
                "        \"first_name\": \"В'ячеслав\",\n" +
                "        \"last_name\": \"ййййй\"\n" +
                "    },\n" +
                "    \"point\": {\n" +
                "        \"callback_url\": \"https://google.com/\",\n" +
                "        \"return_url\": \"https://google.com/\"\n" +
                "    }\n" +
                "}";
        twoPayler = new TwoPayler(body);
        twoPayler.pay_in();
        Session.getDriver().get(twoPayler.getPayURL());
        twoPayler.frame.setCard("5500000000000004");
        twoPayler.frame.setExpire("1028");
        twoPayler.frame.setCvv("111");
        twoPayler.frame.submit();
        showAgoraURL(twoPayler.getId());
    }



    @AfterTest
    void close(){
        Session.closeSession();
    }
}
