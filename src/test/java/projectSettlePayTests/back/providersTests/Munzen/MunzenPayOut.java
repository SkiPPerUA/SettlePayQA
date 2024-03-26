package projectSettlePayTests.back.providersTests.Munzen;

import org.testng.Assert;
import org.testng.annotations.Test;
import projectSettlePay.BaseTest;
import projectSettlePay.back.TransInfoConn;
import projectSettlePay.back.TransInfoCore;
import projectSettlePay.back.providers.Munzen;

@Test
public class MunzenPayOut extends BaseTest {

    Munzen munzen;
    String body;

    public void positive_payout() {
        munzen = new Munzen(Munzen.MunzenBody.defaultBody());
        munzen.pay_out();
        showAgoraURL(munzen.getId());
    }

    public void positive_payout_cents() throws InterruptedException {
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2754,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"01607df2a07b633ea35a909152ba6061\"\n" +
                "    },\n" +
                "    \"locale\": \"ua\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"external_customer_id\": \"123134\",\n" +
                "    \"account_id\": 2760,\n" +
                "    \"wallet_id\": 3689,\n" +
                "    \"service_id\": 4233,\n" +
                "    \"amount\": 10000001,\n" +
                "    \"amount_currency\": \"UZS\",\n" +
                "    \"description\": \"Test deposit\",\n" +
                "    \"fields\": {\n" +
                "        \"card_number\":\"4232618181101636\",\n" +
                "        \"expire_year\":\"2099\",\n" +
                "        \"expire_month\":\"12\",\n" +
                "        \"first_name\":\"Jane\",\n" +
                "        \"last_name\":\"Doe\"\n" +
                "    }\n" +
                "}";
        munzen = new Munzen(body);
        munzen.pay_out();
        Thread.sleep(3000);
        Assert.assertEquals(new TransInfoConn(Long.valueOf(munzen.getId())+1).getAmount(),10000001);
        showAgoraURL(munzen.getId());
    }

    @Test(dataProvider = "string_cases")
    public void test_city(String data){
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2754,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"01607df2a07b633ea35a909152ba6061\"\n" +
                "    },\n" +
                "    \"locale\": \"ua\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"external_customer_id\": \"123134\",\n" +
                "    \"account_id\": 2760,\n" +
                "    \"wallet_id\": 3689,\n" +
                "    \"service_id\": 4233,\n" +
                "    \"amount\": 10000001,\n" +
                "    \"amount_currency\": \"UZS\",\n" +
                "    \"description\": \"Test deposit\",\n" +
                "    \"fields\": {\n" +
                "        \"card_number\":\"4232618181101636\",\n" +
                "        \"expire_year\":\"2099\",\n" +
                "        \"expire_month\":\"12\",\n" +
                "        \"city\":%s,\n" +
                "        \"country\":\"US\",\n" +
                "        \"first_name\":\"Jane\",\n" +
                "        \"last_name\":\"Doe\"\n" +
                "    }\n" +
                "}";
        munzen = new Munzen(String.format(body,data));
        munzen.pay_out();
        System.out.println(String.format("[%s]",data));
        showAgoraURL(munzen.getId());
    }

    public void positive_city_long(){
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2754,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"01607df2a07b633ea35a909152ba6061\"\n" +
                "    },\n" +
                "    \"locale\": \"ua\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"external_customer_id\": \"123134\",\n" +
                "    \"account_id\": 2760,\n" +
                "    \"wallet_id\": 3689,\n" +
                "    \"service_id\": 4233,\n" +
                "    \"amount\": 10000001,\n" +
                "    \"amount_currency\": \"UZS\",\n" +
                "    \"description\": \"Test deposit\",\n" +
                "    \"fields\": {\n" +
                "        \"card_number\":\"4232618181101636\",\n" +
                "        \"expire_year\":\"2099\",\n" +
                "        \"expire_month\":\"12\",\n" +
                "        \"city\":\"dfsadfasdfsddfsadfasdfsddfsadfasdfsddfsadfasdfsddfsadfasdfsddfsadfasdfsddfsadfasdfsddfsadfasdfsddfsadfasdfsddfsadfasdfsdafsfaff\",\n" +
                "        \"country\":\"US\",\n" +
                "        \"first_name\":\"Jane\",\n" +
                "        \"last_name\":\"Doe\"\n" +
                "    }\n" +
                "}";
        munzen = new Munzen(body);
        munzen.pay_out();
        showAgoraURL(munzen.getId());
    }

    @Test(dataProvider = "string_cases")
    public void test_country(String data){
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2754,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"01607df2a07b633ea35a909152ba6061\"\n" +
                "    },\n" +
                "    \"locale\": \"ua\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"external_customer_id\": \"123134\",\n" +
                "    \"account_id\": 2760,\n" +
                "    \"wallet_id\": 3689,\n" +
                "    \"service_id\": 4233,\n" +
                "    \"amount\": 10000001,\n" +
                "    \"amount_currency\": \"UZS\",\n" +
                "    \"description\": \"Test deposit\",\n" +
                "    \"fields\": {\n" +
                "        \"card_number\":\"4232618181101636\",\n" +
                "        \"expire_year\":\"2099\",\n" +
                "        \"expire_month\":\"12\",\n" +
                "        \"city\":\"Kyiv\",\n" +
                "        \"country\":%s,\n" +
                "        \"first_name\":\"Jane\",\n" +
                "        \"last_name\":\"Doe\"\n" +
                "    }\n" +
                "}";
        munzen = new Munzen(String.format(body,data));
        munzen.pay_out();
        System.out.println(String.format("[%s]",data));
        showAgoraURL(munzen.getId());
    }

    //@Test(invocationCount = 1, enabled = true)
    private void pending() {
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2754,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"01607df2a07b633ea35a909152ba6061\"\n" +
                "    },\n" +
                "    \"locale\": \"ua\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"external_customer_id\": \"123134\",\n" +
                "    \"account_id\": 2760,\n" +
                "    \"wallet_id\": 3689,\n" +
                "    \"service_id\": 4233,\n" +
                "    \"amount\": 10000000,\n" +
                "    \"amount_currency\": \"UZS\",\n" +
                "    \"description\": \"Test deposit\",\n" +
                "    \"fields\": {\n" +
                "        \"card_number\":\"4111111111111111\",\n" +
                "        \"expire_year\":\"2099\",\n" +
                "        \"expire_month\":\"12\",\n" +
                "        \"first_name\":\"Jane\",\n" +
                "        \"city\":\"Kyiv\",\n" +
                "        \"country\":\"US\",\n" +
                "        \"last_name\":\"Doe\"\n" +
                "    }\n" +
                "}";
        munzen = new Munzen(body);
        munzen.pay_out();
        showAgoraURL(munzen.getId());
    }

    public void negative_currency_USD() {
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2754,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"01607df2a07b633ea35a909152ba6061\"\n" +
                "    },\n" +
                "    \"locale\": \"ua\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"external_customer_id\": \"123134\",\n" +
                "    \"account_id\": 2760,\n" +
                "    \"wallet_id\": 3400,\n" +
                "    \"service_id\": 4233,\n" +
                "    \"amount\": 10000000,\n" +
                "    \"amount_currency\": \"USD\",\n" +
                "    \"description\": \"Test deposit\",\n" +
                "    \"fields\": {\n" +
                "        \"card_number\":\"4232618181101636\",\n" +
                "        \"expire_year\":\"2099\",\n" +
                "        \"expire_month\":\"12\",\n" +
                "        \"city\":\"Kyiv\",\n" +
                "        \"country\":\"US\",\n" +
                "        \"first_name\":\"Jane\",\n" +
                "        \"last_name\":\"Doe\"\n" +
                "    }\n" +
                "}";
        munzen = new Munzen(body);
        munzen.pay_out();
        showAgoraURL(munzen.getId());
    }

    public void negative_amountMIN() {
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2754,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"01607df2a07b633ea35a909152ba6061\"\n" +
                "    },\n" +
                "    \"locale\": \"ua\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"external_customer_id\": \"123134\",\n" +
                "    \"account_id\": 2760,\n" +
                "    \"wallet_id\": 3689,\n" +
                "    \"service_id\": 4233,\n" +
                "    \"amount\": 9999999,\n" +
                "    \"amount_currency\": \"UZS\",\n" +
                "    \"description\": \"Test deposit\",\n" +
                "    \"fields\": {\n" +
                "        \"card_number\":\"4232618181101636\",\n" +
                "        \"expire_year\":\"2099\",\n" +
                "        \"expire_month\":\"12\",\n" +
                "        \"city\":\"Kyiv\",\n" +
                "        \"country\":\"US\",\n" +
                "        \"first_name\":\"Jane\",\n" +
                "        \"last_name\":\"Doe\"\n" +
                "    }\n" +
                "}";
        munzen = new Munzen(body);
        munzen.pay_out();
        showAgoraURL(munzen.getId());
    }

    public void positive_amountMIN() throws InterruptedException {
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2754,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"01607df2a07b633ea35a909152ba6061\"\n" +
                "    },\n" +
                "    \"locale\": \"ua\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"external_customer_id\": \"123134\",\n" +
                "    \"account_id\": 2760,\n" +
                "    \"wallet_id\": 3689,\n" +
                "    \"service_id\": 4233,\n" +
                "    \"amount\": 10000000,\n" +
                "    \"amount_currency\": \"UZS\",\n" +
                "    \"description\": \"Test deposit\",\n" +
                "    \"fields\": {\n" +
                "        \"card_number\":\"4232618181101636\",\n" +
                "        \"expire_year\":\"2099\",\n" +
                "        \"expire_month\":\"12\",\n" +
                "        \"city\":\"Kyiv\",\n" +
                "        \"country\":\"US\",\n" +
                "        \"first_name\":\"Jane\",\n" +
                "        \"last_name\":\"Doe\"\n" +
                "    }\n" +
                "}";
        munzen = new Munzen(body);
        munzen.pay_out();
        Thread.sleep(3000);
        Assert.assertEquals(new TransInfoConn(Long.valueOf(munzen.getId())+1).getAmount(),10000000);
        Assert.assertEquals(new TransInfoCore(Long.valueOf(munzen.getId())).getAmount(),100000.00);
        Assert.assertEquals(new TransInfoCore(Long.valueOf(munzen.getId())+1).getAmount(),100000.00);
        showAgoraURL(munzen.getId());
    }

    public void positive_amountMAX() throws InterruptedException {
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2754,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"01607df2a07b633ea35a909152ba6061\"\n" +
                "    },\n" +
                "    \"locale\": \"ua\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"external_customer_id\": \"123134\",\n" +
                "    \"account_id\": 2760,\n" +
                "    \"wallet_id\": 3689,\n" +
                "    \"service_id\": 4233,\n" +
                "    \"amount\": 1200000000,\n" +
                "    \"amount_currency\": \"UZS\",\n" +
                "    \"description\": \"Test deposit\",\n" +
                "    \"fields\": {\n" +
                "        \"card_number\":\"4232618181101636\",\n" +
                "        \"expire_year\":\"2099\",\n" +
                "        \"expire_month\":\"12\",\n" +
                "        \"city\":\"Kyiv\",\n" +
                "        \"country\":\"US\",\n" +
                "        \"first_name\":\"Jane\",\n" +
                "        \"last_name\":\"Doe\"\n" +
                "    }\n" +
                "}";
        munzen = new Munzen(body);
        munzen.pay_out();
        Thread.sleep(3000);
        Assert.assertEquals(new TransInfoConn(Long.valueOf(munzen.getId())+1).getAmount(),1200000000);
        Assert.assertEquals(new TransInfoCore(Long.valueOf(munzen.getId())).getAmount(),12000000.00);
        Assert.assertEquals(new TransInfoCore(Long.valueOf(munzen.getId())+1).getAmount(),12000000.00);
        showAgoraURL(munzen.getId());
    }

    public void negative_amountMAX() {
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2754,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"01607df2a07b633ea35a909152ba6061\"\n" +
                "    },\n" +
                "    \"locale\": \"ua\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"external_customer_id\": \"123134\",\n" +
                "    \"account_id\": 2760,\n" +
                "    \"wallet_id\": 3689,\n" +
                "    \"service_id\": 4233,\n" +
                "    \"amount\": 1200000001,\n" +
                "    \"amount_currency\": \"UZS\",\n" +
                "    \"description\": \"Test deposit\",\n" +
                "    \"fields\": {\n" +
                "        \"card_number\":\"4232618181101636\",\n" +
                "        \"expire_year\":\"2099\",\n" +
                "        \"expire_month\":\"12\",\n" +
                "        \"city\":\"Kyiv\",\n" +
                "        \"country\":\"US\",\n" +
                "        \"first_name\":\"Jane\",\n" +
                "        \"last_name\":\"Doe\"\n" +
                "    }\n" +
                "}";
        munzen = new Munzen(body);
        munzen.pay_out();
        showAgoraURL(munzen.getId());
    }

    public void negative_amountMAX_4bill() {
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2754,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"01607df2a07b633ea35a909152ba6061\"\n" +
                "    },\n" +
                "    \"locale\": \"ua\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"external_customer_id\": \"123134\",\n" +
                "    \"account_id\": 2760,\n" +
                "    \"wallet_id\": 3689,\n" +
                "    \"service_id\": 4233,\n" +
                "    \"amount\": 1700000000,\n" +
                "    \"amount_currency\": \"UZS\",\n" +
                "    \"description\": \"Test deposit\",\n" +
                "    \"fields\": {\n" +
                "        \"card_number\":\"4232618181101636\",\n" +
                "        \"expire_year\":\"2099\",\n" +
                "        \"expire_month\":\"12\",\n" +
                "        \"city\":\"Kyiv\",\n" +
                "        \"country\":\"US\",\n" +
                "        \"first_name\":\"Jane\",\n" +
                "        \"last_name\":\"Doe\"\n" +
                "    }\n" +
                "}";
        munzen = new Munzen(body);
        munzen.pay_out();
        showAgoraURL(munzen.getId());
    }

    public void negativeTest_amount() {
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2754,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"01607df2a07b633ea35a909152ba6061\"\n" +
                "    },\n" +
                "    \"locale\": \"ua\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"external_customer_id\": \"123134\",\n" +
                "    \"account_id\": 2760,\n" +
                "    \"wallet_id\": 3689,\n" +
                "    \"service_id\": 4233,\n" +
                "    \"amount\": 0,\n" +
                "    \"amount_currency\": \"UZS\",\n" +
                "    \"description\": \"Test deposit\",\n" +
                "    \"fields\": {\n" +
                "        \"card_number\":\"4232618181101636\",\n" +
                "        \"expire_year\":\"2099\",\n" +
                "        \"expire_month\":\"12\",\n" +
                "        \"city\":\"Kyiv\",\n" +
                "        \"country\":\"US\",\n" +
                "        \"first_name\":\"Jane\",\n" +
                "        \"last_name\":\"Doe\"\n" +
                "    }\n" +
                "}";
        munzen = new Munzen(String.format(body));
        munzen.pay_out();
        if (munzen.getId() != null) {
            showAgoraURL(munzen.getId());
        }else {
            Assert.assertTrue(munzen.getResponse().contains("Check minimum amount"));
        }
    }

    @Test(dataProvider = "string_cases")
    public void test_fields_description(String description){
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2754,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"01607df2a07b633ea35a909152ba6061\"\n" +
                "    },\n" +
                "    \"locale\": \"ua\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"external_customer_id\": \"123134\",\n" +
                "    \"account_id\": 2760,\n" +
                "    \"wallet_id\": 3689,\n" +
                "    \"service_id\": 4233,\n" +
                "    \"amount\": 10000000,\n" +
                "    \"amount_currency\": \"UZS\",\n" +
                "    \"description\": %s,\n" +
                "    \"fields\": {\n" +
                "        \"card_number\":\"4232618181101636\",\n" +
                "        \"expire_year\":\"2099\",\n" +
                "        \"expire_month\":\"12\",\n" +
                "        \"city\":\"Kyiv\",\n" +
                "        \"country\":\"US\",\n" +
                "        \"first_name\":\"Jane\",\n" +
                "        \"last_name\":\"Doe\"\n" +
                "    }\n" +
                "}";
        munzen = new Munzen(String.format(body,description));
        munzen.pay_out();
        if (description != null) {
            showAgoraURL(munzen.getId());
        }else {
            Assert.assertTrue(munzen.getResponse().contains("{'description': ['null value not allowed']}"));
        }
    }

    @Test(dataProvider = "negativeCard_cases")
    public void negativeTest_cardNumber(String card){
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2754,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"01607df2a07b633ea35a909152ba6061\"\n" +
                "    },\n" +
                "    \"locale\": \"ua\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"external_customer_id\": \"123134\",\n" +
                "    \"account_id\": 2760,\n" +
                "    \"wallet_id\": 3689,\n" +
                "    \"service_id\": 4233,\n" +
                "    \"amount\": 10000000,\n" +
                "    \"amount_currency\": \"UZS\",\n" +
                "    \"description\": \"Test deposit\",\n" +
                "    \"fields\": {\n" +
                "        \"card_number\":\"%s\",\n" +
                "        \"expire_year\":\"2099\",\n" +
                "        \"expire_month\":\"12\",\n" +
                "        \"city\":\"Kyiv\",\n" +
                "        \"country\":\"US\",\n" +
                "        \"first_name\":\"Jane\",\n" +
                "        \"last_name\":\"Doe\"\n" +
                "    }\n" +
                "}";
        munzen = new Munzen(String.format(body,card));
        munzen.pay_out();
        showAgoraURL(munzen.getId());
    }

    @Test(dataProvider = "string_cases")
    public void negativeTest_fields_card(String card){
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2754,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"01607df2a07b633ea35a909152ba6061\"\n" +
                "    },\n" +
                "    \"locale\": \"ua\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"external_customer_id\": \"123134\",\n" +
                "    \"account_id\": 2760,\n" +
                "    \"wallet_id\": 3689,\n" +
                "    \"service_id\": 4233,\n" +
                "    \"amount\": 10000000,\n" +
                "    \"amount_currency\": \"UZS\",\n" +
                "    \"description\": \"Test deposit\",\n" +
                "    \"fields\": {\n" +
                "        \"card_number\": %s,\n" +
                "        \"expire_year\":\"2099\",\n" +
                "        \"expire_month\":\"12\",\n" +
                "        \"city\":\"Kyiv\",\n" +
                "        \"country\":\"US\",\n" +
                "        \"first_name\":\"Jane\",\n" +
                "        \"last_name\":\"Doe\"\n" +
                "    }\n" +
                "}";
        munzen = new Munzen(String.format(body,card));
        munzen.pay_out();
        if (card != null) {
            showAgoraURL(munzen.getId());
        }else {
            Assert.assertTrue(munzen.getResponse().contains("@fields[card_number] = None value type is not str"));
        }
    }

    @Test(dataProvider = "string_cases")
    public void negativeTest_fields_expire_year(String data){
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2754,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"01607df2a07b633ea35a909152ba6061\"\n" +
                "    },\n" +
                "    \"locale\": \"ua\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"external_customer_id\": \"123134\",\n" +
                "    \"account_id\": 2760,\n" +
                "    \"wallet_id\": 3689,\n" +
                "    \"service_id\": 4233,\n" +
                "    \"amount\": 10000000,\n" +
                "    \"amount_currency\": \"UZS\",\n" +
                "    \"description\": \"Test deposit\",\n" +
                "    \"fields\": {\n" +
                "        \"card_number\": \"4232618181101636\",\n" +
                "        \"expire_year\": %s,\n" +
                "        \"expire_month\":\"12\",\n" +
                "        \"first_name\":\"Jane\",\n" +
                "        \"city\":\"Kyiv\",\n" +
                "        \"country\":\"US\",\n" +
                "        \"last_name\":\"Doe\"\n" +
                "    }\n" +
                "}";
        munzen = new Munzen(String.format(body,data));
        munzen.pay_out();
        if (data != null) {
            showAgoraURL(munzen.getId());
        }else {
            Assert.assertTrue(munzen.getResponse().contains("@fields[expire_year] = None value type is not str"));
        }
    }

    public void negativeTest_expire_year(){
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2754,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"01607df2a07b633ea35a909152ba6061\"\n" +
                "    },\n" +
                "    \"locale\": \"ua\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"external_customer_id\": \"123134\",\n" +
                "    \"account_id\": 2760,\n" +
                "    \"wallet_id\": 3689,\n" +
                "    \"service_id\": 4233,\n" +
                "    \"amount\": 10000000,\n" +
                "    \"amount_currency\": \"UZS\",\n" +
                "    \"description\": \"Test deposit\",\n" +
                "    \"fields\": {\n" +
                "        \"card_number\": \"4232618181101636\",\n" +
                "        \"expire_year\": \"2023\",\n" +
                "        \"expire_month\":\"12\",\n" +
                "        \"city\":\"Kyiv\",\n" +
                "        \"country\":\"US\",\n" +
                "        \"first_name\":\"Jane\",\n" +
                "        \"last_name\":\"Doe\"\n" +
                "    }\n" +
                "}";
        munzen = new Munzen(body);
        munzen.pay_out();
        showAgoraURL(munzen.getId());
    }

    @Test(dataProvider = "string_cases")
    public void negativeTest_fields_expire_month(String data){
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2754,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"01607df2a07b633ea35a909152ba6061\"\n" +
                "    },\n" +
                "    \"locale\": \"ua\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"external_customer_id\": \"123134\",\n" +
                "    \"account_id\": 2760,\n" +
                "    \"wallet_id\": 3689,\n" +
                "    \"service_id\": 4233,\n" +
                "    \"amount\": 10000000,\n" +
                "    \"amount_currency\": \"UZS\",\n" +
                "    \"description\": \"Test deposit\",\n" +
                "    \"fields\": {\n" +
                "        \"card_number\": \"4232618181101636\",\n" +
                "        \"expire_year\": \"2099\",\n" +
                "        \"expire_month\": %s,\n" +
                "        \"first_name\":\"Jane\",\n" +
                "        \"city\":\"Kyiv\",\n" +
                "        \"country\":\"US\",\n" +
                "        \"last_name\":\"Doe\"\n" +
                "    }\n" +
                "}";
        munzen = new Munzen(String.format(body,data));
        munzen.pay_out();
        if (data != null) {
            showAgoraURL(munzen.getId());
        }else {
            Assert.assertTrue(munzen.getResponse().contains("@fields[expire_month] = None value type is not str"));
        }
    }

    public void negativeTest_expire_month(){
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2754,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"01607df2a07b633ea35a909152ba6061\"\n" +
                "    },\n" +
                "    \"locale\": \"ua\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"external_customer_id\": \"123134\",\n" +
                "    \"account_id\": 2760,\n" +
                "    \"wallet_id\": 3689,\n" +
                "    \"service_id\": 4233,\n" +
                "    \"amount\": 10000000,\n" +
                "    \"amount_currency\": \"UZS\",\n" +
                "    \"description\": \"Test deposit\",\n" +
                "    \"fields\": {\n" +
                "        \"card_number\": \"4232618181101636\",\n" +
                "        \"expire_year\": \"2024\",\n" +
                "        \"expire_month\":\"01\",\n" +
                "        \"first_name\":\"Jane\",\n" +
                "        \"city\":\"Kyiv\",\n" +
                "        \"country\":\"US\",\n" +
                "        \"last_name\":\"Doe\"\n" +
                "    }\n" +
                "}";
        munzen = new Munzen(body);
        munzen.pay_out();
        showAgoraURL(munzen.getId());
    }

    @Test(dataProvider = "string_cases")
    public void negativeTest_fields_first_name(String data){
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2754,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"01607df2a07b633ea35a909152ba6061\"\n" +
                "    },\n" +
                "    \"locale\": \"ua\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"external_customer_id\": \"123134\",\n" +
                "    \"account_id\": 2760,\n" +
                "    \"wallet_id\": 3689,\n" +
                "    \"service_id\": 4233,\n" +
                "    \"amount\": 10000000,\n" +
                "    \"amount_currency\": \"UZS\",\n" +
                "    \"description\": \"Test deposit\",\n" +
                "    \"fields\": {\n" +
                "        \"card_number\": \"4232618181101636\",\n" +
                "        \"expire_year\": \"2099\",\n" +
                "        \"expire_month\":\"12\",\n" +
                "        \"first_name\": %s,\n" +
                "        \"city\":\"Kyiv\",\n" +
                "        \"country\":\"US\",\n" +
                "        \"last_name\":\"Doe\"\n" +
                "    }\n" +
                "}";
        munzen = new Munzen(String.format(body,data));
        munzen.pay_out();
        if (data != null) {
            showAgoraURL(munzen.getId());
        }else {
            Assert.assertTrue(munzen.getResponse().contains("@fields[first_name] = None value type is not str"));
        }
    }

    @Test(dataProvider = "string_cases")
    public void negativeTest_fields_last_name(String data){
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2754,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"01607df2a07b633ea35a909152ba6061\"\n" +
                "    },\n" +
                "    \"locale\": \"ua\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"external_customer_id\": \"123134\",\n" +
                "    \"account_id\": 2760,\n" +
                "    \"wallet_id\": 3689,\n" +
                "    \"service_id\": 4233,\n" +
                "    \"amount\": 10000000,\n" +
                "    \"amount_currency\": \"UZS\",\n" +
                "    \"description\": \"Test deposit\",\n" +
                "    \"fields\": {\n" +
                "        \"card_number\": \"4232618181101636\",\n" +
                "        \"expire_year\": \"2099\",\n" +
                "        \"expire_month\":\"12\",\n" +
                "        \"city\":\"Kyiv\",\n" +
                "        \"country\":\"US\",\n" +
                "        \"first_name\": \"Jane\",\n" +
                "        \"last_name\": %s\n" +
                "    }\n" +
                "}";
        munzen = new Munzen(String.format(body,data));
        munzen.pay_out();
        if (data != null) {
            showAgoraURL(munzen.getId());
        }else {
            Assert.assertTrue(munzen.getResponse().contains("@fields[last_name] = None value type is not str"));
        }
    }

    public void negativeTest_fields_first_name_long(){
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2754,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"01607df2a07b633ea35a909152ba6061\"\n" +
                "    },\n" +
                "    \"locale\": \"ua\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"external_customer_id\": \"123134\",\n" +
                "    \"account_id\": 2760,\n" +
                "    \"wallet_id\": 3689,\n" +
                "    \"service_id\": 4233,\n" +
                "    \"amount\": 10000000,\n" +
                "    \"amount_currency\": \"UZS\",\n" +
                "    \"description\": \"Test deposit\",\n" +
                "    \"fields\": {\n" +
                "        \"card_number\": \"4232618181101636\",\n" +
                "        \"expire_year\": \"2099\",\n" +
                "        \"city\":\"Kyiv\",\n" +
                "        \"country\":\"US\",\n" +
                "        \"expire_month\":\"12\",\n" +
                "        \"first_name\": \"sfdsfgsdgfssfdsfgsdgfssfdsfgsdgfssfdsfgsdgfssfdsfgsdgfsdfgfdgdfdd\",\n" +
                "        \"last_name\":\"sfdsfgsdgfssfdsfgsdgfssfdsfgsdgfssfdsfgsdgfssfdsfgsdgfsdfgfdgdfd\"\n" +
                "    }\n" +
                "}";
        munzen = new Munzen(body);
        munzen.pay_out();
        showAgoraURL(munzen.getId());
    }

    public void negativeTest_fields_last_name_long(){
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2754,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"01607df2a07b633ea35a909152ba6061\"\n" +
                "    },\n" +
                "    \"locale\": \"ua\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"external_customer_id\": \"123134\",\n" +
                "    \"account_id\": 2760,\n" +
                "    \"wallet_id\": 3689,\n" +
                "    \"service_id\": 4233,\n" +
                "    \"amount\": 10000000,\n" +
                "    \"amount_currency\": \"UZS\",\n" +
                "    \"description\": \"Test deposit\",\n" +
                "    \"fields\": {\n" +
                "        \"card_number\": \"4232618181101636\",\n" +
                "        \"expire_year\": \"2099\",\n" +
                "        \"city\":\"Kyiv\",\n" +
                "        \"country\":\"US\",\n" +
                "        \"expire_month\":\"12\",\n" +
                "        \"first_name\": \"sfdsfgsdgfssfdsfgsdgfssfdsfgsdgfssfdsfgsdgfssfdsfgsdgfsdfgfdgdfd\",\n" +
                "        \"last_name\":\"sfdsfgsdgfssfdsfgsdgfssfdsfgsdgfssfdsfgsdgfssfdsfgsdgfsdfgfdgdfdd\"\n" +
                "    }\n" +
                "}";
        munzen = new Munzen(body);
        munzen.pay_out();
        showAgoraURL(munzen.getId());
    }

    public void test_mandatory_fields() throws InterruptedException {
        int status = 2;
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2754,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"01607df2a07b633ea35a909152ba6061\"\n" +
                "    },\n" +
                "    \"locale\": \"ua\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"external_customer_id\": \"123134\",\n" +
                "    \"account_id\": 2760,\n" +
                "    \"wallet_id\": 3689,\n" +
                "    \"service_id\": 4233,\n" +
                "    \"amount\": 10000000,\n" +
                "    \"amount_currency\": \"UZS\",\n" +
                "    \"description\": \"Test deposit\",\n" +
                "    \"fields\": {\n" +
                "        \"expire_year\":\"2099\",\n" +
                "        \"expire_month\":\"12\",\n" +
                "        \"first_name\":\"Jane\",\n" +
                "        \"city\":\"Kyiv\",\n" +
                "        \"country\":\"US\",\n" +
                "        \"last_name\":\"Doe\"\n" +
                "    }\n" +
                "}";
        System.out.print("Without card_number ");
        munzen = new Munzen(body);
        munzen.pay_out();
        Thread.sleep(2000);
        Assert.assertEquals(new TransInfoConn(Long.valueOf(munzen.getId())+1).getStatus(),status);
        Assert.assertEquals(new TransInfoCore(Long.valueOf(munzen.getId())).getStatus(),status);
        Assert.assertEquals(new TransInfoCore(Long.valueOf(munzen.getId())+1).getStatus(),status);
        showAgoraURL(munzen.getId());

        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2754,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"01607df2a07b633ea35a909152ba6061\"\n" +
                "    },\n" +
                "    \"locale\": \"ua\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"external_customer_id\": \"123134\",\n" +
                "    \"account_id\": 2760,\n" +
                "    \"wallet_id\": 3689,\n" +
                "    \"service_id\": 4233,\n" +
                "    \"amount\": 10000000,\n" +
                "    \"amount_currency\": \"UZS\",\n" +
                "    \"description\": \"Test deposit\",\n" +
                "    \"fields\": {\n" +
                "        \"card_number\":\"4232618181101636\",\n" +
                "        \"expire_year\":\"2099\",\n" +
                "        \"expire_month\":\"12\",\n" +
                "        \"city\":\"Kyiv\",\n" +
                "        \"country\":\"US\",\n" +
                "        \"last_name\":\"Doe\"\n" +
                "    }\n" +
                "}";
        System.out.print("Without first_name ");
        munzen = new Munzen(body);
        munzen.pay_out();
        Thread.sleep(2000);
        Assert.assertEquals(new TransInfoConn(Long.valueOf(munzen.getId())+1).getStatus(),status);
        Assert.assertEquals(new TransInfoCore(Long.valueOf(munzen.getId())).getStatus(),status);
        Assert.assertEquals(new TransInfoCore(Long.valueOf(munzen.getId())+1).getStatus(),status);
        showAgoraURL(munzen.getId());

        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2754,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"01607df2a07b633ea35a909152ba6061\"\n" +
                "    },\n" +
                "    \"locale\": \"ua\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"external_customer_id\": \"123134\",\n" +
                "    \"account_id\": 2760,\n" +
                "    \"wallet_id\": 3689,\n" +
                "    \"service_id\": 4233,\n" +
                "    \"amount\": 10000000,\n" +
                "    \"amount_currency\": \"UZS\",\n" +
                "    \"description\": \"Test deposit\",\n" +
                "    \"fields\": {\n" +
                "        \"card_number\":\"4232618181101636\",\n" +
                "        \"expire_year\":\"2099\",\n" +
                "        \"expire_month\":\"12\",\n" +
                "        \"city\":\"Kyiv\",\n" +
                "        \"country\":\"US\",\n" +
                "        \"first_name\":\"Doe\"\n" +
                "    }\n" +
                "}";
        System.out.print("Without last_name ");
        munzen = new Munzen(body);
        munzen.pay_out();
        Thread.sleep(2000);
        Assert.assertEquals(new TransInfoConn(Long.valueOf(munzen.getId())+1).getStatus(),status);
        Assert.assertEquals(new TransInfoCore(Long.valueOf(munzen.getId())).getStatus(),status);
        Assert.assertEquals(new TransInfoCore(Long.valueOf(munzen.getId())+1).getStatus(),status);
        showAgoraURL(munzen.getId());

        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2754,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"01607df2a07b633ea35a909152ba6061\"\n" +
                "    },\n" +
                "    \"locale\": \"ua\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"external_customer_id\": \"123134\",\n" +
                "    \"account_id\": 2760,\n" +
                "    \"wallet_id\": 3689,\n" +
                "    \"service_id\": 4233,\n" +
                "    \"amount\": 10000000,\n" +
                "    \"amount_currency\": \"UZS\",\n" +
                "    \"description\": \"Test deposit\",\n" +
                "    \"fields\": {\n" +
                "        \"card_number\":\"4232618181101636\",\n" +
                "        \"expire_year\":\"2099\",\n" +
                "        \"expire_month\":\"12\",\n" +
                "        \"country\":\"US\",\n" +
                "        \"last_name\":\"Joe\",\n" +
                "        \"first_name\":\"Doe\"\n" +
                "    }\n" +
                "}";
        System.out.print("Without city ");
        munzen = new Munzen(body);
        munzen.pay_out();
        Thread.sleep(2000);
        Assert.assertEquals(new TransInfoConn(Long.valueOf(munzen.getId())+1).getStatus(),status);
        Assert.assertEquals(new TransInfoCore(Long.valueOf(munzen.getId())).getStatus(),status);
        Assert.assertEquals(new TransInfoCore(Long.valueOf(munzen.getId())+1).getStatus(),status);
        showAgoraURL(munzen.getId());

        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2754,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"01607df2a07b633ea35a909152ba6061\"\n" +
                "    },\n" +
                "    \"locale\": \"ua\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"external_customer_id\": \"123134\",\n" +
                "    \"account_id\": 2760,\n" +
                "    \"wallet_id\": 3689,\n" +
                "    \"service_id\": 4233,\n" +
                "    \"amount\": 10000000,\n" +
                "    \"amount_currency\": \"UZS\",\n" +
                "    \"description\": \"Test deposit\",\n" +
                "    \"fields\": {\n" +
                "        \"card_number\":\"4232618181101636\",\n" +
                "        \"expire_year\":\"2099\",\n" +
                "        \"expire_month\":\"12\",\n" +
                "        \"city\":\"Kyiv\",\n" +
                "        \"last_name\":\"Joe\",\n" +
                "        \"first_name\":\"Doe\"\n" +
                "    }\n" +
                "}";
        System.out.print("Without country ");
        munzen = new Munzen(body);
        munzen.pay_out();
        Thread.sleep(2000);
        Assert.assertEquals(new TransInfoConn(Long.valueOf(munzen.getId())+1).getStatus(),status);
        Assert.assertEquals(new TransInfoCore(Long.valueOf(munzen.getId())).getStatus(),status);
        Assert.assertEquals(new TransInfoCore(Long.valueOf(munzen.getId())+1).getStatus(),status);
        showAgoraURL(munzen.getId());
    }

}
