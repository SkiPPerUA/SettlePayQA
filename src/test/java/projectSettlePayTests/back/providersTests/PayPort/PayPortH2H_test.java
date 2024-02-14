package projectSettlePayTests.back.providersTests.PayPort;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import projectSettlePay.BaseTest;
import projectSettlePay.back.providers.PayPort;
import projectSettlePay.helper.UuidGenerate;

@Test
public class PayPortH2H_test extends BaseTest {

    PayPort payPort;
    String body;
    JSONObject json;
    String id;
    int sleep = 10000;

    public void positive_h2h_confirm() throws InterruptedException {
        payPort = new PayPort(PayPort.PayPortBody.defaultBody(true));
        payPort.pay_in();
        id = payPort.getId();
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+"\n" +
                "}";
        payPort.get_methods_list(body);
        json = (JSONObject) new JSONObject(payPort.getResponse()).getJSONObject("response").getJSONArray("p2p_methods").get(2);
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+",\n" +
                "\"p2p_method_id\" : \""+json.getString("value")+"\"\n" +
                "}";
        payPort.get_recipient_card(body);
        Thread.sleep(sleep);
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "    \"id\": "+id+",\n" +
                "    \"confirmation_fields\": {\n" +
                "        \"sender_card_tail\": \"1234\"\n" +
                "    }\n" +
                "}";
        payPort.confirm(body);
        System.out.println("positive_h2h_confirm"+info(id));
    }

    public void positive_h2h_cancel() throws InterruptedException {
        payPort = new PayPort(PayPort.PayPortBody.defaultBody(true));
        payPort.pay_in();
        id = payPort.getId();
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+"\n" +
                "}";
        payPort.get_methods_list(body);
        json = (JSONObject) new JSONObject(payPort.getResponse()).getJSONObject("response").getJSONArray("p2p_methods").get(2);
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+",\n" +
                "\"p2p_method_id\" : \""+json.getString("value")+"\"\n" +
                "}";
        payPort.get_recipient_card(body);
        Thread.sleep(sleep);
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+"\n" +
                "}";
        payPort.cancel(body);
        System.out.println("positive_h2h_cancel"+info(id));
    }

    public void negative_h2h_confirm_after_cancel() throws InterruptedException {
        payPort = new PayPort(PayPort.PayPortBody.defaultBody(true));
        payPort.pay_in();
        id = payPort.getId();
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+"\n" +
                "}";
        payPort.get_methods_list(body);
        json = (JSONObject) new JSONObject(payPort.getResponse()).getJSONObject("response").getJSONArray("p2p_methods").get(2);
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+",\n" +
                "\"p2p_method_id\" : \""+json.getString("value")+"\"\n" +
                "}";
        payPort.get_recipient_card(body);
        Thread.sleep(sleep);
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+"\n" +
                "}";
        payPort.cancel(body);
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "    \"id\": "+id+",\n" +
                "    \"confirmation_fields\": {\n" +
                "        \"sender_card_tail\": \"1234\"\n" +
                "    }\n" +
                "}";
        payPort.confirm(body);
        System.out.println("negative_h2h_confirm_after_cancel"+info(id));
    }

    public void test_create_without_first_name() throws InterruptedException {
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "    \"locale\": \"ua\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"external_customer_id\": \""+UuidGenerate.generateUUID()+"\",\n" +
                "    \"account_id\": 2761,\n" +
                "    \"wallet_id\": 2529, \n" +
                "    \"service_id\": 3966,\n" +
                "    \"amount\": 50000,\n" +
                "    \"amount_currency\": \"UAH\",\n" +
                "    \"fields\": {\n" +
                "        \"last_name\": \"Galazy\",\n" +
                "        \"email\": \"andromeda@gmail.com\"\n" +
                "    }\n" +
                "}";
        payPort = new PayPort(body);
        payPort.pay_in();
        id = payPort.getId();
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+"\n" +
                "}";
        payPort.get_methods_list(body);
        json = (JSONObject) new JSONObject(payPort.getResponse()).getJSONObject("response").getJSONArray("p2p_methods").get(2);
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+",\n" +
                "\"p2p_method_id\" : \""+json.getString("value")+"\"\n" +
                "}";
        payPort.get_recipient_card(body);
        Thread.sleep(sleep);
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "    \"id\": "+id+",\n" +
                "    \"confirmation_fields\": {\n" +
                "        \"sender_card_tail\": \"1234\"\n" +
                "    }\n" +
                "}";
        payPort.confirm(body);
        System.out.println("Without first_name"+info(id));
    }

    public void test_create_first_name_empty() throws InterruptedException {
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "    \"locale\": \"ua\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"external_customer_id\": \""+UuidGenerate.generateUUID()+"\",\n" +
                "    \"account_id\": 2761,\n" +
                "    \"wallet_id\": 2529, \n" +
                "    \"service_id\": 3966,\n" +
                "    \"amount\": 50000,\n" +
                "    \"amount_currency\": \"UAH\",\n" +
                "    \"fields\": {\n" +
                "        \"first_name\": \"\",\n" +
                "        \"last_name\": \"Galazy\",\n" +
                "        \"email\": \"andromeda@gmail.com\"\n" +
                "    }\n" +
                "}";
        payPort = new PayPort(body);
        payPort.pay_in();
        id = payPort.getId();
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+"\n" +
                "}";
        payPort.get_methods_list(body);
        json = (JSONObject) new JSONObject(payPort.getResponse()).getJSONObject("response").getJSONArray("p2p_methods").get(2);
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+",\n" +
                "\"p2p_method_id\" : \""+json.getString("value")+"\"\n" +
                "}";
        payPort.get_recipient_card(body);
        Thread.sleep(sleep);
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "    \"id\": "+id+",\n" +
                "    \"confirmation_fields\": {\n" +
                "        \"sender_card_tail\": \"1234\"\n" +
                "    }" +
                "}";
        payPort.confirm(body);
        System.out.println("test_create_first_name_empty"+info(id));
    }

    public void test_create_first_name_null() {
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "    \"locale\": \"ua\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"external_customer_id\": \""+UuidGenerate.generateUUID()+"\",\n" +
                "    \"account_id\": 2761,\n" +
                "    \"wallet_id\": 2529, \n" +
                "    \"service_id\": 3966,\n" +
                "    \"amount\": 50000,\n" +
                "    \"amount_currency\": \"UAH\",\n" +
                "    \"fields\": {\n" +
                "        \"first_name\": null,\n" +
                "        \"last_name\": \"Galazy\",\n" +
                "        \"email\": \"andromeda@gmail.com\"\n" +
                "    }\n" +
                "}";
        payPort = new PayPort(body);
        payPort.pay_in();
        Assert.assertTrue(payPort.getResponse().contains("@fields[first_name] = None value type is not str"));
        System.out.println("test_create_first_name_null");
    }

    public void test_create_without_last_name() throws InterruptedException {
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "    \"locale\": \"ua\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"external_customer_id\": \""+UuidGenerate.generateUUID()+"\",\n" +
                "    \"account_id\": 2761,\n" +
                "    \"wallet_id\": 2529, \n" +
                "    \"service_id\": 3966,\n" +
                "    \"amount\": 50000,\n" +
                "    \"amount_currency\": \"UAH\",\n" +
                "    \"fields\": {\n" +
                "        \"first_name\": \"Andromeda\",\n" +
                "        \"email\": \"andromeda@gmail.com\"\n" +
                "    }\n" +
                "}";
        payPort = new PayPort(body);
        payPort.pay_in();
        id = payPort.getId();
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+"\n" +
                "}";
        payPort.get_methods_list(body);
        json = (JSONObject) new JSONObject(payPort.getResponse()).getJSONObject("response").getJSONArray("p2p_methods").get(2);
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+",\n" +
                "\"p2p_method_id\" : \""+json.getString("value")+"\"\n" +
                "}";
        payPort.get_recipient_card(body);
        Thread.sleep(sleep);
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "    \"id\": "+id+",\n" +
                "    \"confirmation_fields\": {\n" +
                "        \"sender_card_tail\": \"1234\"\n" +
                "    }" +
                "}";
        payPort.confirm(body);
        System.out.println("test_create_without_last_name"+info(id));
    }

    public void test_create_last_name_empty() throws InterruptedException {
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "    \"locale\": \"ua\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"external_customer_id\": \""+UuidGenerate.generateUUID()+"\",\n" +
                "    \"account_id\": 2761,\n" +
                "    \"wallet_id\": 2529, \n" +
                "    \"service_id\": 3966,\n" +
                "    \"amount\": 50000,\n" +
                "    \"amount_currency\": \"UAH\",\n" +
                "    \"fields\": {\n" +
                "        \"first_name\": \"Andromeda\",\n" +
                "        \"last_name\": \"\",\n" +
                "        \"email\": \"andromeda@gmail.com\"\n" +
                "    }\n" +
                "}";
        payPort = new PayPort(body);
        payPort.pay_in();
        id = payPort.getId();
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+"\n" +
                "}";
        payPort.get_methods_list(body);
        json = (JSONObject) new JSONObject(payPort.getResponse()).getJSONObject("response").getJSONArray("p2p_methods").get(2);
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+",\n" +
                "\"p2p_method_id\" : \""+json.getString("value")+"\"\n" +
                "}";
        payPort.get_recipient_card(body);
        Thread.sleep(sleep);
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "    \"id\": "+id+",\n" +
                "    \"confirmation_fields\": {\n" +
                "        \"sender_card_tail\": \"1234\"\n" +
                "    }" +
                "}";
        payPort.confirm(body);
        System.out.println("test_create_last_name_empty"+info(id));
    }

    public void test_create_last_name_null() {
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "    \"locale\": \"ua\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"external_customer_id\": \""+UuidGenerate.generateUUID()+"\",\n" +
                "    \"account_id\": 2761,\n" +
                "    \"wallet_id\": 2529, \n" +
                "    \"service_id\": 3966,\n" +
                "    \"amount\": 50000,\n" +
                "    \"amount_currency\": \"UAH\",\n" +
                "    \"fields\": {\n" +
                "        \"first_name\": \"Andromeda\",\n" +
                "        \"last_name\": null,\n" +
                "        \"email\": \"andromeda@gmail.com\"\n" +
                "    }\n" +
                "}";
        payPort = new PayPort(body);
        payPort.pay_in();
        Assert.assertTrue(payPort.getResponse().contains("@fields[last_name] = None value type is not str"));
        System.out.println("test_create_last_name_null");
    }

    public void test_create_without_email() {
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "    \"locale\": \"ua\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"external_customer_id\": \""+UuidGenerate.generateUUID()+"\",\n" +
                "    \"account_id\": 2761,\n" +
                "    \"wallet_id\": 2529, \n" +
                "    \"service_id\": 3966,\n" +
                "    \"amount\": 50000,\n" +
                "    \"amount_currency\": \"UAH\",\n" +
                "    \"fields\": {\n" +
                "        \"first_name\": \"Andromeda\",\n" +
                "        \"last_name\": \"Galazy\"" +
                "    }\n" +
                "}";
        payPort = new PayPort(body);
        payPort.pay_in();
        id = payPort.getId();
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+"\n" +
                "}";
        payPort.get_methods_list(body);
        Assert.assertEquals(new JSONObject(payPort.getResponse()).getJSONObject("response").getJSONArray("p2p_methods").length(),0);
        System.out.println("test_create_without_email"+info(id));
    }

    public void test_create_email_empty() {
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "    \"locale\": \"ua\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"external_customer_id\": \""+UuidGenerate.generateUUID()+"\",\n" +
                "    \"account_id\": 2761,\n" +
                "    \"wallet_id\": 2529, \n" +
                "    \"service_id\": 3966,\n" +
                "    \"amount\": 50000,\n" +
                "    \"amount_currency\": \"UAH\",\n" +
                "    \"fields\": {\n" +
                "        \"first_name\": \"Andromeda\",\n" +
                "        \"last_name\": \"Galazy\",\n" +
                "        \"email\": \"\"\n" +
                "    }\n" +
                "}";
        payPort = new PayPort(body);
        payPort.pay_in();
        id = payPort.getId();
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+"\n" +
                "}";
        payPort.get_methods_list(body);
        Assert.assertEquals(new JSONObject(payPort.getResponse()).getJSONObject("response").getJSONArray("p2p_methods").length(),0);
        System.out.println("test_create_email_empty"+info(id));
    }

    public void test_create_email_notCorrect() {
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "    \"locale\": \"ua\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"external_customer_id\": \""+UuidGenerate.generateUUID()+"\",\n" +
                "    \"account_id\": 2761,\n" +
                "    \"wallet_id\": 2529, \n" +
                "    \"service_id\": 3966,\n" +
                "    \"amount\": 50000,\n" +
                "    \"amount_currency\": \"UAH\",\n" +
                "    \"fields\": {\n" +
                "        \"first_name\": \"Andromeda\",\n" +
                "        \"last_name\": \"Galazy\",\n" +
                "        \"email\": \"andromedagmailcom\"\n" +
                "    }\n" +
                "}";
        payPort = new PayPort(body);
        payPort.pay_in();
        id = payPort.getId();
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+"\n" +
                "}";
        payPort.get_methods_list(body);
        Assert.assertEquals(new JSONObject(payPort.getResponse()).getJSONObject("response").getJSONArray("p2p_methods").length(),0);
        System.out.println("test_create_email_notCorrect"+info(id));
    }

    public void test_create_email_null() {
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "    \"locale\": \"ua\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"external_customer_id\": \""+UuidGenerate.generateUUID()+"\",\n" +
                "    \"account_id\": 2761,\n" +
                "    \"wallet_id\": 2529, \n" +
                "    \"service_id\": 3966,\n" +
                "    \"amount\": 50000,\n" +
                "    \"amount_currency\": \"UAH\",\n" +
                "    \"fields\": {\n" +
                "        \"first_name\": \"Andromeda\",\n" +
                "        \"last_name\": \"Galazy\",\n" +
                "        \"email\": null\n" +
                "    }\n" +
                "}";
        payPort = new PayPort(body);
        payPort.pay_in();
        Assert.assertTrue(payPort.getResponse().contains("@fields[email] = None value type is not str"));
        System.out.println("test_create_email_null");
    }

    public void test_getMethod_without_id() {
        payPort = new PayPort(PayPort.PayPortBody.defaultBody(true));
        payPort.pay_in();
        id = payPort.getId();
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    }\n" +
                "}";
        payPort.get_methods_list(body);
        Assert.assertTrue(payPort.getResponse().contains("@id:required field"));
        System.out.println("test_getMethod_without_id"+info(id));
    }

    public void test_getMethod_id_notReal() throws InterruptedException {
        payPort = new PayPort(PayPort.PayPortBody.defaultBody(true));
        payPort.pay_in();
        id = payPort.getId();
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+"\n" +
                "}";
        payPort.get_methods_list(body);
        json = (JSONObject) new JSONObject(payPort.getResponse()).getJSONObject("response").getJSONArray("p2p_methods").get(2);
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": 910100000478619,\n" +
                "\"p2p_method_id\" : \""+json.getString("value")+"\"\n" +
                "}";
        payPort.get_recipient_card(body);
        Thread.sleep(sleep);
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "    \"id\": "+id+",\n" +
                "    \"confirmation_fields\": {\n" +
                "        \"sender_card_tail\": \"1234\"\n" +
                "    }" +
                "}";
        payPort.confirm(body);
        System.out.println("test_getMethod_id_notReal"+info(id));
    }

    public void test_getRecipientCard_without_id() throws InterruptedException {
        payPort = new PayPort(PayPort.PayPortBody.defaultBody(true));
        payPort.pay_in();
        id = payPort.getId();
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+"\n" +
                "}";
        payPort.get_methods_list(body);
        json = (JSONObject) new JSONObject(payPort.getResponse()).getJSONObject("response").getJSONArray("p2p_methods").get(2);
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"p2p_method_id\" : \""+json.getString("value")+"\"\n" +
                "}";
        payPort.get_recipient_card(body);
        Thread.sleep(sleep);
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "    \"id\": "+id+",\n" +
                "    \"confirmation_fields\": {\n" +
                "        \"sender_card_tail\": \"1234\"\n" +
                "    }" +
                "}";
        payPort.confirm(body);
        System.out.println("test_getRecipientCard_without_id"+info(id));
    }

    public void test_getRecipientCard_without_p2p_method_id() throws InterruptedException {
        payPort = new PayPort(PayPort.PayPortBody.defaultBody(true));
        payPort.pay_in();
        id = payPort.getId();
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+"\n" +
                "}";
        payPort.get_methods_list(body);
        json = (JSONObject) new JSONObject(payPort.getResponse()).getJSONObject("response").getJSONArray("p2p_methods").get(2);
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+"\n" +
                "}";
        payPort.get_recipient_card(body);
        Thread.sleep(sleep);
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "    \"id\": "+id+",\n" +
                "    \"confirmation_fields\": {\n" +
                "        \"sender_card_tail\": \"1234\"\n" +
                "    }" +
                "}";
        payPort.confirm(body);
        System.out.println("test_getRecipientCard_without_p2p_method_id"+info(id));
    }

    public void test_getRecipientCard_id_notReal() throws InterruptedException {
        payPort = new PayPort(PayPort.PayPortBody.defaultBody(true));
        payPort.pay_in();
        id = payPort.getId();
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+"\n" +
                "}";
        payPort.get_methods_list(body);
        json = (JSONObject) new JSONObject(payPort.getResponse()).getJSONObject("response").getJSONArray("p2p_methods").get(2);
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": 910100000478619,\n" +
                "\"p2p_method_id\" : \""+json.getString("value")+"\"\n" +
                "}";
        payPort.get_recipient_card(body);
        Thread.sleep(sleep);
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "    \"id\": "+id+",\n" +
                "    \"confirmation_fields\": {\n" +
                "        \"sender_card_tail\": \"1234\"\n" +
                "    }" +
                "}";
        payPort.confirm(body);
        System.out.println("test_getRecipientCard_id_notReal"+info(id));
    }

    public void test_getRecipientCard_id_null() throws InterruptedException {
        payPort = new PayPort(PayPort.PayPortBody.defaultBody(true));
        payPort.pay_in();
        id = payPort.getId();
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+"\n" +
                "}";
        payPort.get_methods_list(body);
        json = (JSONObject) new JSONObject(payPort.getResponse()).getJSONObject("response").getJSONArray("p2p_methods").get(2);
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": null,\n" +
                "\"p2p_method_id\" : \""+json.getString("value")+"\"\n" +
                "}";
        payPort.get_recipient_card(body);
        Thread.sleep(sleep);
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "    \"id\": "+id+",\n" +
                "    \"confirmation_fields\": {\n" +
                "        \"sender_card_tail\": \"1234\"\n" +
                "    }" +
                "}";
        payPort.confirm(body);
        System.out.println("test_getRecipientCard_id_null"+info(id));
    }

    public void test_getRecipientCard_p2p_method_notReal() throws InterruptedException {
        payPort = new PayPort(PayPort.PayPortBody.defaultBody(true));
        payPort.pay_in();
        id = payPort.getId();
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+"\n" +
                "}";
        payPort.get_methods_list(body);
        json = (JSONObject) new JSONObject(payPort.getResponse()).getJSONObject("response").getJSONArray("p2p_methods").get(2);
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+",\n" +
                "\"p2p_method_id\" : \"81dfb438-9482-4f4b-8c9c-f7b6800e5345\"\n" +
                "}";
        payPort.get_recipient_card(body);
        Thread.sleep(sleep);
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "    \"id\": "+id+",\n" +
                "    \"confirmation_fields\": {\n" +
                "        \"sender_card_tail\": \"1234\"\n" +
                "    }" +
                "}";
        payPort.confirm(body);
        System.out.println("test_getRecipientCard_p2p_method_notReal"+info(id));
    }

    public void test_getRecipientCard_p2p_method_empty() throws InterruptedException {
        payPort = new PayPort(PayPort.PayPortBody.defaultBody(true));
        payPort.pay_in();
        id = payPort.getId();
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+"\n" +
                "}";
        payPort.get_methods_list(body);
        json = (JSONObject) new JSONObject(payPort.getResponse()).getJSONObject("response").getJSONArray("p2p_methods").get(2);
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+",\n" +
                "\"p2p_method_id\" : \"\"\n" +
                "}";
        payPort.get_recipient_card(body);
        Thread.sleep(sleep);
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "    \"id\": "+id+",\n" +
                "    \"confirmation_fields\": {\n" +
                "        \"sender_card_tail\": \"1234\"\n" +
                "    }" +
                "}";
        payPort.confirm(body);
        System.out.println("test_getRecipientCard_p2p_method_empty"+info(id));
    }

    public void test_getRecipientCard_p2p_method_null() throws InterruptedException {
        payPort = new PayPort(PayPort.PayPortBody.defaultBody(true));
        payPort.pay_in();
        id = payPort.getId();
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+"\n" +
                "}";
        payPort.get_methods_list(body);
        json = (JSONObject) new JSONObject(payPort.getResponse()).getJSONObject("response").getJSONArray("p2p_methods").get(2);
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+",\n" +
                "\"p2p_method_id\" : null\n" +
                "}";
        payPort.get_recipient_card(body);
        Thread.sleep(sleep);
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "    \"id\": "+id+",\n" +
                "    \"confirmation_fields\": {\n" +
                "        \"sender_card_tail\": \"1234\"\n" +
                "    }" +
                "}";
        payPort.confirm(body);
        System.out.println("test_getRecipientCard_p2p_method_null"+info(id));
    }

    public void test_confirm_without_id() throws InterruptedException {
        payPort = new PayPort(PayPort.PayPortBody.defaultBody(true));
        payPort.pay_in();
        id = payPort.getId();
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+"\n" +
                "}";
        payPort.get_methods_list(body);
        json = (JSONObject) new JSONObject(payPort.getResponse()).getJSONObject("response").getJSONArray("p2p_methods").get(2);
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+",\n" +
                "\"p2p_method_id\" : \""+json.getString("value")+"\"\n" +
                "}";
        payPort.get_recipient_card(body);
        Thread.sleep(sleep);
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "    \"confirmation_fields\": {\n" +
                "        \"sender_card_tail\": \"1234\"\n" +
                "    }" +
                "}";
        payPort.confirm(body);
        System.out.println("test_confirm_without_id"+info(id));
    }

    public void test_confirm_id_notReal() throws InterruptedException {
        payPort = new PayPort(PayPort.PayPortBody.defaultBody(true));
        payPort.pay_in();
        id = payPort.getId();
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+"\n" +
                "}";
        payPort.get_methods_list(body);
        json = (JSONObject) new JSONObject(payPort.getResponse()).getJSONObject("response").getJSONArray("p2p_methods").get(2);
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+",\n" +
                "\"p2p_method_id\" : \""+json.getString("value")+"\"\n" +
                "}";
        payPort.get_recipient_card(body);
        Thread.sleep(sleep);
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "    \"id\": 910100000478619,\n" +
                "    \"confirmation_fields\": {\n" +
                "        \"sender_card_tail\": \"1234\"\n" +
                "    }" +
                "}";
        payPort.confirm(body);
        System.out.println("test_confirm_id_notReal"+info(id));
    }

    public void test_confirm_id_null() throws InterruptedException {
        payPort = new PayPort(PayPort.PayPortBody.defaultBody(true));
        payPort.pay_in();
        id = payPort.getId();
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+"\n" +
                "}";
        payPort.get_methods_list(body);
        json = (JSONObject) new JSONObject(payPort.getResponse()).getJSONObject("response").getJSONArray("p2p_methods").get(2);
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+",\n" +
                "\"p2p_method_id\" : \""+json.getString("value")+"\"\n" +
                "}";
        payPort.get_recipient_card(body);
        Thread.sleep(sleep);
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "    \"id\": null,\n" +
                "    \"confirmation_fields\": {\n" +
                "        \"sender_card_tail\": \"1234\"\n" +
                "    }" +
                "}";
        payPort.confirm(body);
        System.out.println("test_confirm_id_null"+info(id));
    }

    public void test_confirm_without_sender_card_tail() throws InterruptedException {
        payPort = new PayPort(PayPort.PayPortBody.defaultBody(true));
        payPort.pay_in();
        id = payPort.getId();
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+"\n" +
                "}";
        payPort.get_methods_list(body);
        json = (JSONObject) new JSONObject(payPort.getResponse()).getJSONObject("response").getJSONArray("p2p_methods").get(2);
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+",\n" +
                "\"p2p_method_id\" : \""+json.getString("value")+"\"\n" +
                "}";
        payPort.get_recipient_card(body);
        Thread.sleep(sleep);
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "    \"id\": "+id+",\n" +
                "    \"confirmation_fields\": {\n" +
                "    }" +
                "}";
        payPort.confirm(body);
        System.out.println("test_confirm_without_sender_card_tail"+info(id));
    }

    public void test_confirm_sender_card_tail_null() throws InterruptedException {
        payPort = new PayPort(PayPort.PayPortBody.defaultBody(true));
        payPort.pay_in();
        id = payPort.getId();
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+"\n" +
                "}";
        payPort.get_methods_list(body);
        json = (JSONObject) new JSONObject(payPort.getResponse()).getJSONObject("response").getJSONArray("p2p_methods").get(2);
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+",\n" +
                "\"p2p_method_id\" : \""+json.getString("value")+"\"\n" +
                "}";
        payPort.get_recipient_card(body);
        Thread.sleep(sleep);
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "    \"id\": "+id+",\n" +
                "    \"confirmation_fields\": {\n" +
                "        \"sender_card_tail\": null\n" +
                "    }" +
                "}";
        payPort.confirm(body);
        System.out.println("test_confirm_sender_card_tail_null"+info(id));
    }

    public void test_confirm_sender_card_tail_empty() throws InterruptedException {
        payPort = new PayPort(PayPort.PayPortBody.defaultBody(true));
        payPort.pay_in();
        id = payPort.getId();
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+"\n" +
                "}";
        payPort.get_methods_list(body);
        json = (JSONObject) new JSONObject(payPort.getResponse()).getJSONObject("response").getJSONArray("p2p_methods").get(2);
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+",\n" +
                "\"p2p_method_id\" : \""+json.getString("value")+"\"\n" +
                "}";
        payPort.get_recipient_card(body);
        Thread.sleep(sleep);
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "    \"id\": "+id+",\n" +
                "    \"confirmation_fields\": {\n" +
                "        \"sender_card_tail\": \"\"\n" +
                "    }" +
                "}";
        payPort.confirm(body);
        System.out.println("test_confirm_sender_card_tail_empty"+info(id));
    }

    public void h2h_create_get_methods_list_confirm() throws InterruptedException {
        payPort = new PayPort(PayPort.PayPortBody.defaultBody(true));
        payPort.pay_in();
        id = payPort.getId();
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+"\n" +
                "}";
        payPort.get_methods_list(body);
        json = (JSONObject) new JSONObject(payPort.getResponse()).getJSONObject("response").getJSONArray("p2p_methods").get(2);
        Thread.sleep(sleep);
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "    \"id\": "+id+",\n" +
                "    \"confirmation_fields\": {\n" +
                "        \"sender_card_tail\": \"1234\"\n" +
                "    }" +
                "}";
        payPort.confirm(body);
        System.out.println("h2h_create_get_methods_list_confirm"+info(id));
    }

    public void h2h_create_get_recipient_card_confirm() throws InterruptedException {
        payPort = new PayPort(PayPort.PayPortBody.defaultBody(true));
        payPort.pay_in();
        id = payPort.getId();
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+",\n" +
                "\"p2p_method_id\" : \"null\"\n" +
                "}";
        payPort.get_recipient_card(body);
        Thread.sleep(sleep);
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "    \"id\": "+id+",\n" +
                "    \"confirmation_fields\": {\n" +
                "        \"sender_card_tail\": \"1234\"\n" +
                "    }" +
                "}";
        payPort.confirm(body);
        System.out.println("h2h_create_get_recipient_card_confirm"+info(id));
    }

    public void h2h_create_confirm() {
        payPort = new PayPort(PayPort.PayPortBody.defaultBody(true));
        payPort.pay_in();
        id = payPort.getId();
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "    \"id\": "+id+",\n" +
                "    \"confirmation_fields\": {\n" +
                "        \"sender_card_tail\": \"1234\"\n" +
                "    }" +
                "}";
        payPort.confirm(body);
        System.out.println("h2h_create_confirm"+info(id));
    }

    public void h2h_create_cancel() {
        payPort = new PayPort(PayPort.PayPortBody.defaultBody(true));
        payPort.pay_in();
        id = payPort.getId();
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+"\n" +
                "}";
        payPort.cancel(body);
        System.out.println("h2h_create_cancel"+info(id));
    }



    String info(String idTrs){
        long child = Long.valueOf(idTrs) + 1;
        return "  ->   https://preprod-agora2.backofficeweb.info/transactions/transactions/"+child;
    }
}
