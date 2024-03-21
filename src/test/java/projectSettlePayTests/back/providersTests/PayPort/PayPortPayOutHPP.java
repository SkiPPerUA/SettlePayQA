package projectSettlePayTests.back.providersTests.PayPort;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import projectSettlePay.BaseTest;
import projectSettlePay.back.Callback;
import projectSettlePay.back.providers.PayPortHPP;
import projectSettlePay.core.GUIButton;
import projectSettlePay.core.GUITextBox;
import projectSettlePay.core.Locator;
import projectSettlePay.core.Session;
import projectSettlePay.helper.UuidGenerate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Test
public class PayPortPayOutHPP extends BaseTest {

    PayPortHPP payPort;
    String body;
    public void successPayOut() {
        try {
            payPort = new PayPortHPP(PayPortHPP.PayPortHPPBody.defaultBody(false));
            payPort.pay_out();
            Session.getDriver().get(payPort.getPayURL());
            payPort.frame.positiveStepsPayOut();
            payPort.callback("1");
        }finally {
            showAgoraURL(payPort.getId());
        }
    }

    public void two_callbacks(){
        payPort = new PayPortHPP(PayPortHPP.PayPortHPPBody.defaultBody(false));
        payPort.pay_out();
        Session.getDriver().get(payPort.getPayURL());
        payPort.frame.positiveStepsPayOut();
        payPort.callback("1");
        payPort.callback("1", 550f);
        System.out.print("Из успеха в успех - сумма другая ");
        showAgoraURL(payPort.getId());

        payPort = new PayPortHPP(PayPortHPP.PayPortHPPBody.defaultBody(false));
        payPort.pay_out();
        Session.getDriver().get(payPort.getPayURL());
        payPort.frame.positiveStepsPayOut();
        payPort.callback("1");
        payPort.callback("-1", 550f);
        System.out.print("Из успеха в фейл - сумма другая ");
        showAgoraURL(payPort.getId());

        payPort = new PayPortHPP(PayPortHPP.PayPortHPPBody.defaultBody(false));
        payPort.pay_out();
        Session.getDriver().get(payPort.getPayURL());
        payPort.frame.positiveStepsPayOut();
        payPort.callback("1");
        payPort.callback("-1");
        System.out.print("Из успеха в фейл - сумма таже ");
        showAgoraURL(payPort.getId());

        payPort = new PayPortHPP(PayPortHPP.PayPortHPPBody.defaultBody(false));
        payPort.pay_out();
        Session.getDriver().get(payPort.getPayURL());
        payPort.frame.positiveStepsPayOut();
        payPort.callback("-1");
        payPort.callback("1", 550f);
        System.out.print("Из фейла в успех - сумма другая ");
        showAgoraURL(payPort.getId());

        payPort = new PayPortHPP(PayPortHPP.PayPortHPPBody.defaultBody(false));
        payPort.pay_out();
        Session.getDriver().get(payPort.getPayURL());
        payPort.frame.positiveStepsPayOut();
        payPort.callback("-1");
        payPort.callback("-1", 550f);
        System.out.print("Из фейла в фейл - сумма другая ");
        showAgoraURL(payPort.getId());

        payPort = new PayPortHPP(PayPortHPP.PayPortHPPBody.defaultBody(false));
        payPort.pay_out();
        Session.getDriver().get(payPort.getPayURL());
        payPort.frame.positiveStepsPayOut();
        payPort.callback("-1");
        payPort.callback("1");
        System.out.print("Из фейла в успех - сумма таже ");
        showAgoraURL(payPort.getId());
    }

    public void negativePayOut() {
        payPort = new PayPortHPP(PayPortHPP.PayPortHPPBody.defaultBody(false));
        payPort.pay_out();
        Session.getDriver().get(payPort.getPayURL());
        payPort.frame.positiveStepsPayOut();
        payPort.callback("-1");
        showAgoraURL(payPort.getId());
    }

    public void negative_check_cancellation_reason(){
        List<String> cancellation_reasons = List.of("NO_PAYMENT_RECEIVED", "DETAILS_OF_ANOTHER_BANK", "INVALID_DETAILS",
                "RECALCULATION", "CANCELED_BY_NO_AD_SELECTED", "CANCELED_BY_USER", "CANCELED_BY_SYSTEM");
        for (String cancel: cancellation_reasons) {
            payPort = new PayPortHPP(PayPortHPP.PayPortHPPBody.defaultBody(false));
            payPort.pay_out();
            Session.getDriver().get(payPort.getPayURL());
            payPort.frame.positiveStepsPayOut();
            Map<String, String> body = new HashMap<>();
            body.put("invoice_id", "90795");
            body.put("merchant_id", "663");
            body.put("order_id", payPort.getChildId());
            body.put("amount", "2.356712");
            body.put("amount_currency", "300");
            body.put("currency", "BDT");
            body.put("order_desc", "Order" + payPort.getChildId());
            body.put("merchant_amount", "2.215309");
            body.put("status", "-1");
            body.put("account_info", "01204358565");
            body.put("fiat_currency", "BDT");
            body.put("fiat_amount", "500");
            body.put("payment_info", "BDT Test");
            body.put("payment_system_type", "by_mobile");
            body.put("signature", "10a192e348fe4586106b47b28f7472d74aef1407");
            body.put("cancellation_reason", cancel);
            Callback callback = new Callback();
            callback.makeCallback(callback.getResult_url(payPort), body);
            System.out.print(cancel+" ");
            showAgoraURL(payPort.getId());
        }
    }

    public void tests_summ(){
        List<Long> amounts = amount_cases(new Long[]{29999l,2500001l,30001l,30000l,2500000l});
        for (Long x: amounts){
            body = String.format("{\n" +
                    "    \"auth\": {\n" +
                    "        \"debug\": true,\n" +
                    "        \"point\": 2757,\n" +
                    "        \"key\": 1,\n" +
                    "        \"hash\": \"73fa095174d9e9653f7f4b203d6fe122\"\n" +
                    "    },\n" +
                    "    \"locale\": \"ua\",\n" +
                    "    \"external_order_id\": \"12345673489011\",\n" +
                    "    \"external_customer_id\": \""+UuidGenerate.generateUUID()+"\",\n" +
                    "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                    "    \"account_id\": 2763,\n" +
                    "    \"wallet_id\": 3806,\n" +
                    "    \"service_id\": 4412,\n" +
                    "    \"amount\": %s,\n" +
                    "    \"amount_currency\": \"BDT\",\n" +
                    "    \"fields\": {\n" +
                    "         \"email\":\"cadfa@fdas.das\",\n" +
                    "         \"first_name\":\"name\",\n" +
                    "         \"last_name\":\"last\"\n" +
                    "    }\n" +
                    "}",x);
            try {
                System.out.print(String.format("Amount {%s} ",x));
                payPort = new PayPortHPP(body);
                payPort.pay_out();
                Session.getDriver().get(payPort.getPayURL());
                showAgoraURL(payPort.getId());
            }catch (Throwable e){
                //
            }
        }
    }

    public void successPayOut_anotherSum_in_callback(){
        payPort = new PayPortHPP(PayPortHPP.PayPortHPPBody.defaultBody(false));
        payPort.pay_out();
        Session.getDriver().get(payPort.getPayURL());
        payPort.frame.positiveStepsPayOut();
        payPort.callback("1", 550.0f);
        showAgoraURL(payPort.getId());
    }

    public void negativePayOut_anotherSum_in_callback(){
        payPort = new PayPortHPP(PayPortHPP.PayPortHPPBody.defaultBody(false));
        payPort.pay_out();
        Session.getDriver().get(payPort.getPayURL());
        payPort.frame.positiveStepsPayOut();
        payPort.callback("-1", 550.0f);
        showAgoraURL(payPort.getId());
    }

    public void test_locale() throws InterruptedException {
        List<String> locale = List.of("ua","ru","en");
        for (String loc : locale) {
            String body = "{\n" +
                    "    \"auth\": {\n" +
                    "        \"debug\": true,\n" +
                    "        \"point\": 2757,\n" +
                    "        \"key\": 1,\n" +
                    "        \"hash\": \"73fa095174d9e9653f7f4b203d6fe122\"\n" +
                    "    },\n" +
                    "    \"locale\": \"%s\",\n" +
                    "    \"external_order_id\": \"12345673489011\",\n" +
                    "    \"external_customer_id\": \"" + UuidGenerate.generateUUID() + "\",\n" +
                    "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                    "    \"account_id\": 2763,\n" +
                    "    \"wallet_id\": 3806,\n" +
                    "    \"service_id\": 4412,\n" +
                    "    \"amount\": 50001,\n" +
                    "    \"amount_currency\": \"BDT\",\n" +
                    "    \"fields\": {\n" +
                    "        \"email\":\"cadfa@fdas.das\",\n" +
                    "        \"first_name\":\"name\",\n" +
                    "        \"last_name\":\"last\"\n" +
                    "    }\n" +
                    "}";
            payPort = new PayPortHPP(String.format(body, loc));
            payPort.pay_out();
            Session.getDriver().get(payPort.getPayURL());
            Thread.sleep(10000);
            new GUITextBox(new Locator().xpath("//input[@id=\"message\"]")).addText("80933994455");
            new GUITextBox(new Locator().xpath("//input[@id=\"payment_info\"]")).addText("Vlad test");
            payPort.frame.submit();
            Thread.sleep(10000);
            System.out.print(loc+" ");
            showAgoraURL(payPort.getId());
        }
    }

    public void negative_the_same_orderId(){
        String external_customer_id = UuidGenerate.generateUUID();
        for (int i = 0; i < 2; i++) {
            payPort = new PayPortHPP("{\n" +
                    "    \"auth\": {\n" +
                    "        \"debug\": true,\n" +
                    "        \"point\": 2757,\n" +
                    "        \"key\": 1,\n" +
                    "        \"hash\": \"73fa095174d9e9653f7f4b203d6fe122\"\n" +
                    "    },\n" +
                    "    \"locale\": \"ua\",\n" +
                    "    \"external_order_id\": \"12345673489011\",\n" +
                    "    \"external_customer_id\": \""+external_customer_id+"\",\n" +
                    "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                    "    \"account_id\": 2763,\n" +
                    "    \"wallet_id\": 3806,\n" +
                    "    \"service_id\": 4412,\n" +
                    "    \"amount\": 50000,\n" +
                    "    \"amount_currency\": \"BDT\",\n" +
                    "    \"fields\": {\n" +
                    "        \"email\":\"cadfa@fdas.das\",\n" +
                    "        \"first_name\":\"name\",\n" +
                    "        \"last_name\":\"last\"\n" +
                    "    }\n" +
                    "}");
            payPort.pay_out();
            if (i < 1) {
                Session.getDriver().get(payPort.getPayURL());
                payPort.frame.positiveStepsPayOut();
            }
            showAgoraURL(payPort.getId());
        }
    }

    public void negative_currency(){
        payPort = new PayPortHPP("{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2757,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"73fa095174d9e9653f7f4b203d6fe122\"\n" +
                "    },\n" +
                "    \"locale\": \"ua\",\n" +
                "    \"external_order_id\": \"12345673489011\",\n" +
                "    \"external_customer_id\": \""+UuidGenerate.generateUUID()+"\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"account_id\": 2763,\n" +
                "    \"wallet_id\": 3228,\n" +
                "    \"service_id\": 4412,\n" +
                "    \"amount\": 50000,\n" +
                "    \"amount_currency\": \"UAH\",\n" +
                "    \"fields\": {\n" +
                "        \"email\":\"cadfa@fdas.das\",\n" +
                "        \"first_name\":\"name\",\n" +
                "        \"last_name\":\"last\"\n" +
                "    }\n" +
                "}");
        payPort.pay_out();
        Session.getDriver().get(payPort.getPayURL());
        payPort.frame.positiveStepsPayOut();
        showAgoraURL(payPort.getId());
    }

    @Test(dataProvider = "string_cases")
    public void negativeTest_fields_last_name(String data){
        String body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2757,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"73fa095174d9e9653f7f4b203d6fe122\"\n" +
                "    },\n" +
                "    \"locale\": \"ua\",\n" +
                "    \"external_order_id\": \"12345673489011\",\n" +
                "    \"external_customer_id\": \""+UuidGenerate.generateUUID()+"\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"account_id\": 2763,\n" +
                "    \"wallet_id\": 3806,\n" +
                "    \"service_id\": 4412,\n" +
                "    \"amount\": 50001,\n" +
                "    \"amount_currency\": \"BDT\",\n" +
                "    \"fields\": {\n" +
                "        \"email\":\"cadfa@fdas.das\",\n" +
                "        \"first_name\":\"name\",\n" +
                "        \"last_name\": %s\n" +
                "    }\n" +
                "}";
        payPort = new PayPortHPP(String.format(body,data));
        payPort.pay_out();
        Session.getDriver().get(payPort.getPayURL());
        payPort.frame.positiveStepsPayOut();
        payPort.callback("1");
        System.out.print(data+" ");
        showAgoraURL(payPort.getId());
    }

    public void test_without_fields(){
        Map<String, String> fields = new HashMap<>();
        fields.put("email","cadfa@fdas.das");
        fields.put("first_name","name");
        fields.put("last_name","last");
        List<String> cases = without_fields_cases(fields);
        for (String x: cases){
            body = String.format("{\n" +
                    "    \"auth\": {\n" +
                    "        \"debug\": true,\n" +
                    "        \"point\": 2757,\n" +
                    "        \"key\": 1,\n" +
                    "        \"hash\": \"73fa095174d9e9653f7f4b203d6fe122\"\n" +
                    "    },\n" +
                    "    \"locale\": \"ua\",\n" +
                    "    \"external_order_id\": \"12345673489011\",\n" +
                    "    \"external_customer_id\": \""+UuidGenerate.generateUUID()+"\",\n" +
                    "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                    "    \"account_id\": 2763,\n" +
                    "    \"wallet_id\": 3806,\n" +
                    "    \"service_id\": 4412,\n" +
                    "    \"amount\": 50000,\n" +
                    "    \"amount_currency\": \"BDT\",\n" +
                    "    \"fields\": %s"+
                    "}",x);
            System.out.print(String.format("Fields %s ",x));
            payPort = new PayPortHPP(body);
            payPort.pay_out();
            Session.getDriver().get(payPort.getPayURL());
            showAgoraURL(payPort.getId());
        }
    }

    @Test(dataProvider = "string_cases")
    public void negativeTest_fields_first_name(String data){
        String body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2757,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"73fa095174d9e9653f7f4b203d6fe122\"\n" +
                "    },\n" +
                "    \"locale\": \"ua\",\n" +
                "    \"external_order_id\": \"12345673489011\",\n" +
                "    \"external_customer_id\": \""+UuidGenerate.generateUUID()+"\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"account_id\": 2763,\n" +
                "    \"wallet_id\": 3806,\n" +
                "    \"service_id\": 4412,\n" +
                "    \"amount\": 50001,\n" +
                "    \"amount_currency\": \"BDT\",\n" +
                "    \"fields\": {\n" +
                "        \"email\":\"cadfa@fdas.das\",\n" +
                "        \"first_name\":%s,\n" +
                "        \"last_name\": \"name\" \n" +
                "    }\n" +
                "}";
        payPort = new PayPortHPP(String.format(body,data));
        payPort.pay_out();
        Session.getDriver().get(payPort.getPayURL());
        payPort.frame.positiveStepsPayOut();
        payPort.callback("1");
        System.out.print(data+" ");
        showAgoraURL(payPort.getId());
    }

    @Test(dataProvider = "emailNegative_cases")
    public void negativeTest_fields_email(String data){
        String body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2757,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"73fa095174d9e9653f7f4b203d6fe122\"\n" +
                "    },\n" +
                "    \"locale\": \"ua\",\n" +
                "    \"external_order_id\": \"12345673489011\",\n" +
                "    \"external_customer_id\": \""+UuidGenerate.generateUUID()+"\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"account_id\": 2763,\n" +
                "    \"wallet_id\": 3806,\n" +
                "    \"service_id\": 4412,\n" +
                "    \"amount\": 50001,\n" +
                "    \"amount_currency\": \"BDT\",\n" +
                "    \"fields\": {\n" +
                "        \"email\":\"%s\",\n" +
                "        \"first_name\": \"namezxc\",\n" +
                "        \"last_name\": \"name\" \n" +
                "    }\n" +
                "}";
        payPort = new PayPortHPP(String.format(body,data));
        payPort.pay_out();
        Session.getDriver().get(payPort.getPayURL());
        payPort.frame.positiveStepsPayOut();
        payPort.callback("1");
        System.out.print(data+" ");
        showAgoraURL(payPort.getId());
    }

    @Test(dataProvider = "string_cases")
    public void test_description(String data){
        String body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2757,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"73fa095174d9e9653f7f4b203d6fe122\"\n" +
                "    },\n" +
                "    \"locale\": \"ua\",\n" +
                "    \"external_order_id\": \"12345673489011\",\n" +
                "    \"external_customer_id\": \""+UuidGenerate.generateUUID()+"\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"account_id\": 2763,\n" +
                "    \"wallet_id\": 3806,\n" +
                "    \"service_id\": 4412,\n" +
                "    \"amount\": 50001,\n" +
                "    \"description\": %s,\n" +
                "    \"amount_currency\": \"BDT\",\n" +
                "    \"fields\": {\n" +
                "        \"email\":\"cadfa@fdas.das\",\n" +
                "        \"first_name\": \"first\",\n" +
                "        \"last_name\": \"name\" \n" +
                "    }\n" +
                "}";
        payPort = new PayPortHPP(String.format(body,data));
        payPort.pay_out();
        Session.getDriver().get(payPort.getPayURL());
        payPort.frame.positiveStepsPayOut();
        payPort.callback("1");
        System.out.print(data+" ");
        showAgoraURL(payPort.getId());
    }

    @AfterTest
    void close(){
        Session.closeSession();
    }
}
