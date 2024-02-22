package projectSettlePayTests.back.providersTests.TwoPayller;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import projectSettlePay.BaseTest;
import projectSettlePay.back.providers.PayPort;
import projectSettlePay.back.providers.TwoPayler;
import projectSettlePay.helper.UuidGenerate;

@Test
public class TwoPayllerH2H_test extends BaseTest {

    TwoPayler twoPayler;
    String body;
    JSONObject json;
    String id;
    int sleep = 10000;

    public void positive_h2h_confirm() throws InterruptedException {
        twoPayler = new TwoPayler(TwoPayler.TwoPaylerBody.defaultBody(true));
        twoPayler.pay_in();
        id = twoPayler.getId();
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+"\n" +
                "}";
        twoPayler.get_methods_list(body);
        json = (JSONObject) new JSONObject(twoPayler.getResponse()).getJSONObject("response").getJSONArray("p2p_methods").get(0);
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
        twoPayler.get_recipient_card(body);
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
                "        \"confirmation_amount\": \"35000\",\n" +
                "        \"sender_card_number\": \"4149785236985410\"\n" +
                "    }" +
                "}";
        twoPayler.confirm(body);
        System.out.println("positive_h2h_confirm"+info(id));
    }

    public void positive_h2h_cancel() throws InterruptedException {
        twoPayler = new TwoPayler(TwoPayler.TwoPaylerBody.defaultBody(true));
        twoPayler.pay_in();
        id = twoPayler.getId();
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+"\n" +
                "}";
        twoPayler.get_methods_list(body);
        json = (JSONObject) new JSONObject(twoPayler.getResponse()).getJSONObject("response").getJSONArray("p2p_methods").get(0);
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
        twoPayler.get_recipient_card(body);
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
        twoPayler.cancel(body);
        System.out.println("positive_h2h_cancel"+info(id));
    }

    public void negative_h2h_confirm_after_cancel() throws InterruptedException {
        twoPayler = new TwoPayler(TwoPayler.TwoPaylerBody.defaultBody(true));
        twoPayler.pay_in();
        id = twoPayler.getId();
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+"\n" +
                "}";
        twoPayler.get_methods_list(body);
        json = (JSONObject) new JSONObject(twoPayler.getResponse()).getJSONObject("response").getJSONArray("p2p_methods").get(0);
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
        twoPayler.get_recipient_card(body);
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
        twoPayler.cancel(body);
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "    \"id\": "+id+",\n" +
                "    \"confirmation_fields\": {\n" +
                "        \"confirmation_amount\": \"35000\",\n" +
                "        \"sender_card_number\": \"4149785236985410\"\n" +
                "    }" +
                "}";
        twoPayler.confirm(body);
        System.out.println("negative_h2h_confirm_after_cancel"+info(id));
    }

    public void test_create_without_first_name() throws InterruptedException {
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 1,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"c9194644d4316e008a7afb0d50bf056b\"\n" +
                "    },\n" +
                "    \"locale\": \"ru\",\n" +
                "    \"external_order_id\": \"100\",\n" +
                "    \"external_customer_id\": \"1213\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"account_id\": 4,\n" +
                "    \"wallet_id\": 10,\n" +
                "    \"service_id\": 3304,\n" +
                "    \"amount\": 20000,\n" +
                "    \"amount_currency\": \"UAH\",\n" +
                "    \"fields\" : {\n" +
                "    \"last_name\" : \"malanchuk\"\n" +
                "    }\n" +
                "}";
        twoPayler = new TwoPayler(body);
        twoPayler.pay_in();
        id = twoPayler.getId();
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+"\n" +
                "}";
        twoPayler.get_methods_list(body);
        json = (JSONObject) new JSONObject(twoPayler.getResponse()).getJSONObject("response").getJSONArray("p2p_methods").get(0);
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
        twoPayler.get_recipient_card(body);
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
                "        \"confirmation_amount\": \"35000\",\n" +
                "        \"sender_card_number\": \"4149785236985410\"\n" +
                "    }" +
                "}";
        twoPayler.confirm(body);
        System.out.println("test_create_without_first_name"+info(id));
    }

    public void test_create_first_name_empty() throws InterruptedException {
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 1,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"c9194644d4316e008a7afb0d50bf056b\"\n" +
                "    },\n" +
                "    \"locale\": \"ru\",\n" +
                "    \"external_order_id\": \"100\",\n" +
                "    \"external_customer_id\": \"1213\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"account_id\": 4,\n" +
                "    \"wallet_id\": 10,\n" +
                "    \"service_id\": 3304,\n" +
                "    \"amount\": 20000,\n" +
                "    \"amount_currency\": \"UAH\",\n" +
                "    \"fields\" : {\n" +
                "    \"first_name\" : \"\",\n" +
                "    \"last_name\" : \"malanchuk\"\n" +
                "    }\n" +
                "}";
        twoPayler = new TwoPayler(body);
        twoPayler.pay_in();
        id = twoPayler.getId();
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+"\n" +
                "}";
        twoPayler.get_methods_list(body);
        json = (JSONObject) new JSONObject(twoPayler.getResponse()).getJSONObject("response").getJSONArray("p2p_methods").get(0);
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
        twoPayler.get_recipient_card(body);
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
                "        \"confirmation_amount\": \"35000\",\n" +
                "        \"sender_card_number\": \"4149785236985410\"\n" +
                "    }" +
                "}";
        twoPayler.confirm(body);
        System.out.println("test_create_first_name_empty"+info(id));
    }

    public void test_create_first_name_null() {
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 1,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"c9194644d4316e008a7afb0d50bf056b\"\n" +
                "    },\n" +
                "    \"locale\": \"ru\",\n" +
                "    \"external_order_id\": \"100\",\n" +
                "    \"external_customer_id\": \"1213\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"account_id\": 4,\n" +
                "    \"wallet_id\": 10,\n" +
                "    \"service_id\": 3304,\n" +
                "    \"amount\": 20000,\n" +
                "    \"amount_currency\": \"UAH\",\n" +
                "    \"fields\" : {\n" +
                "    \"first_name\" : null,\n" +
                "    \"last_name\" : \"malanchuk\"\n" +
                "    }\n" +
                "}";
        twoPayler = new TwoPayler(body);
        twoPayler.pay_in();
        Assert.assertTrue(twoPayler.getResponse().contains("@fields[first_name] = None value type is not str"));
        System.out.println("test_create_first_name_null");
    }

    public void test_create_without_last_name() throws InterruptedException {
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 1,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"c9194644d4316e008a7afb0d50bf056b\"\n" +
                "    },\n" +
                "    \"locale\": \"ru\",\n" +
                "    \"external_order_id\": \"100\",\n" +
                "    \"external_customer_id\": \"1213\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"account_id\": 4,\n" +
                "    \"wallet_id\": 10,\n" +
                "    \"service_id\": 3304,\n" +
                "    \"amount\": 20000,\n" +
                "    \"amount_currency\": \"UAH\",\n" +
                "    \"fields\" : {\n" +
                "    \"first_name\" : \"roman\"\n" +
                "    }\n" +
                "}";
        twoPayler = new TwoPayler(body);
        twoPayler.pay_in();
        id = twoPayler.getId();
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+"\n" +
                "}";
        twoPayler.get_methods_list(body);
        json = (JSONObject) new JSONObject(twoPayler.getResponse()).getJSONObject("response").getJSONArray("p2p_methods").get(0);
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
        twoPayler.get_recipient_card(body);
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
                "        \"confirmation_amount\": \"35000\",\n" +
                "        \"sender_card_number\": \"4149785236985410\"\n" +
                "    }" +
                "}";
        twoPayler.confirm(body);
        System.out.println("test_create_without_last_name"+info(id));
    }

    public void test_create_last_name_empty() throws InterruptedException {
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 1,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"c9194644d4316e008a7afb0d50bf056b\"\n" +
                "    },\n" +
                "    \"locale\": \"ru\",\n" +
                "    \"external_order_id\": \"100\",\n" +
                "    \"external_customer_id\": \"1213\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"account_id\": 4,\n" +
                "    \"wallet_id\": 10,\n" +
                "    \"service_id\": 3304,\n" +
                "    \"amount\": 20000,\n" +
                "    \"amount_currency\": \"UAH\",\n" +
                "    \"fields\" : {\n" +
                "    \"first_name\" : \"roman\",\n" +
                "    \"last_name\" : \"\"\n" +
                "    }\n" +
                "}";
        twoPayler = new TwoPayler(body);
        twoPayler.pay_in();
        id = twoPayler.getId();
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+"\n" +
                "}";
        twoPayler.get_methods_list(body);
        json = (JSONObject) new JSONObject(twoPayler.getResponse()).getJSONObject("response").getJSONArray("p2p_methods").get(0);
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
        twoPayler.get_recipient_card(body);
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
                "        \"confirmation_amount\": \"35000\",\n" +
                "        \"sender_card_number\": \"4149785236985410\"\n" +
                "    }" +
                "}";
        twoPayler.confirm(body);
        System.out.println("test_create_last_name_empty"+info(id));
    }

    public void test_create_last_name_null() {
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 1,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"c9194644d4316e008a7afb0d50bf056b\"\n" +
                "    },\n" +
                "    \"locale\": \"ru\",\n" +
                "    \"external_order_id\": \"100\",\n" +
                "    \"external_customer_id\": \"1213\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"account_id\": 4,\n" +
                "    \"wallet_id\": 10,\n" +
                "    \"service_id\": 3304,\n" +
                "    \"amount\": 20000,\n" +
                "    \"amount_currency\": \"UAH\",\n" +
                "    \"fields\" : {\n" +
                "    \"first_name\" : \"roman\",\n" +
                "    \"last_name\" : null\n" +
                "    }\n" +
                "}";
        twoPayler = new TwoPayler(body);
        twoPayler.pay_in();
        Assert.assertTrue(twoPayler.getResponse().contains("@fields[last_name] = None value type is not str"));
        System.out.println("test_create_last_name_null");
    }

    public void test_getMethod_without_id() {
        twoPayler = new TwoPayler(TwoPayler.TwoPaylerBody.defaultBody(true));
        twoPayler.pay_in();
        id = twoPayler.getId();
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    }\n" +
                "}";
        twoPayler.get_methods_list(body);
        Assert.assertTrue(twoPayler.getResponse().contains("@id:required field"));
        System.out.println("test_getMethod_without_id"+info(id));
    }

    public void test_getMethod_id_notReal() throws InterruptedException {
        twoPayler = new TwoPayler(TwoPayler.TwoPaylerBody.defaultBody(true));
        twoPayler.pay_in();
        id = twoPayler.getId();
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+"\n" +
                "}";
        twoPayler.get_methods_list(body);
        json = (JSONObject) new JSONObject(twoPayler.getResponse()).getJSONObject("response").getJSONArray("p2p_methods").get(0);
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
        twoPayler.get_recipient_card(body);
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
                "        \"confirmation_amount\": \"35000\",\n" +
                "        \"sender_card_number\": \"4149785236985410\"\n" +
                "    }" +
                "}";
        twoPayler.confirm(body);
        System.out.println("test_getMethod_id_notReal"+info(id));
    }

    public void test_getRecipientCard_without_id() throws InterruptedException {
        twoPayler = new TwoPayler(TwoPayler.TwoPaylerBody.defaultBody(true));
        twoPayler.pay_in();
        id = twoPayler.getId();
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+"\n" +
                "}";
        twoPayler.get_methods_list(body);
        json = (JSONObject) new JSONObject(twoPayler.getResponse()).getJSONObject("response").getJSONArray("p2p_methods").get(0);
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"p2p_method_id\" : \""+json.getString("value")+"\"\n" +
                "}";
        twoPayler.get_recipient_card(body);
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
                "        \"confirmation_amount\": \"35000\",\n" +
                "        \"sender_card_number\": \"4149785236985410\"\n" +
                "    }" +
                "}";
        twoPayler.confirm(body);
        System.out.println("test_getRecipientCard_without_id"+info(id));
    }

    public void test_getRecipientCard_without_p2p_method_id() throws InterruptedException {
        twoPayler = new TwoPayler(TwoPayler.TwoPaylerBody.defaultBody(true));
        twoPayler.pay_in();
        id = twoPayler.getId();
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+"\n" +
                "}";
        twoPayler.get_methods_list(body);
        json = (JSONObject) new JSONObject(twoPayler.getResponse()).getJSONObject("response").getJSONArray("p2p_methods").get(0);
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+"\n" +
                "}";
        twoPayler.get_recipient_card(body);
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
                "        \"confirmation_amount\": \"35000\",\n" +
                "        \"sender_card_number\": \"4149785236985410\"\n" +
                "    }" +
                "}";
        twoPayler.confirm(body);
        System.out.println("test_getRecipientCard_without_p2p_method_id"+info(id));
    }

    public void test_getRecipientCard_id_notReal() throws InterruptedException {
        twoPayler = new TwoPayler(TwoPayler.TwoPaylerBody.defaultBody(true));
        twoPayler.pay_in();
        id = twoPayler.getId();
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+"\n" +
                "}";
        twoPayler.get_methods_list(body);
        json = (JSONObject) new JSONObject(twoPayler.getResponse()).getJSONObject("response").getJSONArray("p2p_methods").get(0);
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
        twoPayler.get_recipient_card(body);
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
                "        \"confirmation_amount\": \"35000\",\n" +
                "        \"sender_card_number\": \"4149785236985410\"\n" +
                "    }" +
                "}";
        twoPayler.confirm(body);
        System.out.println("test_getRecipientCard_id_notReal"+info(id));
    }

    public void test_getRecipientCard_id_null() throws InterruptedException {
        twoPayler = new TwoPayler(TwoPayler.TwoPaylerBody.defaultBody(true));
        twoPayler.pay_in();
        id = twoPayler.getId();
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+"\n" +
                "}";
        twoPayler.get_methods_list(body);
        json = (JSONObject) new JSONObject(twoPayler.getResponse()).getJSONObject("response").getJSONArray("p2p_methods").get(0);
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
        twoPayler.get_recipient_card(body);
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
                "        \"confirmation_amount\": \"35000\",\n" +
                "        \"sender_card_number\": \"4149785236985410\"\n" +
                "    }" +
                "}";
        twoPayler.confirm(body);
        System.out.println("test_getRecipientCard_id_null"+info(id));
    }

    public void test_getRecipientCard_p2p_method_notReal() throws InterruptedException {
        twoPayler = new TwoPayler(TwoPayler.TwoPaylerBody.defaultBody(true));
        twoPayler.pay_in();
        id = twoPayler.getId();
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+"\n" +
                "}";
        twoPayler.get_methods_list(body);
        json = (JSONObject) new JSONObject(twoPayler.getResponse()).getJSONObject("response").getJSONArray("p2p_methods").get(0);
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
        twoPayler.get_recipient_card(body);
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
                "        \"confirmation_amount\": \"35000\",\n" +
                "        \"sender_card_number\": \"4149785236985410\"\n" +
                "    }" +
                "}";
        twoPayler.confirm(body);
        System.out.println("test_getRecipientCard_p2p_method_notReal"+info(id));
    }

    public void test_getRecipientCard_p2p_method_empty() throws InterruptedException {
        twoPayler = new TwoPayler(TwoPayler.TwoPaylerBody.defaultBody(true));
        twoPayler.pay_in();
        id = twoPayler.getId();
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+"\n" +
                "}";
        twoPayler.get_methods_list(body);
        json = (JSONObject) new JSONObject(twoPayler.getResponse()).getJSONObject("response").getJSONArray("p2p_methods").get(0);
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
        twoPayler.get_recipient_card(body);
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
                "        \"confirmation_amount\": \"35000\",\n" +
                "        \"sender_card_number\": \"4149785236985410\"\n" +
                "    }" +
                "}";
        twoPayler.confirm(body);
        System.out.println("test_getRecipientCard_p2p_method_empty"+info(id));
    }

    public void test_getRecipientCard_p2p_method_null() throws InterruptedException {
        twoPayler = new TwoPayler(TwoPayler.TwoPaylerBody.defaultBody(true));
        twoPayler.pay_in();
        id = twoPayler.getId();
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+"\n" +
                "}";
        twoPayler.get_methods_list(body);
        json = (JSONObject) new JSONObject(twoPayler.getResponse()).getJSONObject("response").getJSONArray("p2p_methods").get(0);
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
        twoPayler.get_recipient_card(body);
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
                "        \"confirmation_amount\": \"35000\",\n" +
                "        \"sender_card_number\": \"4149785236985410\"\n" +
                "    }" +
                "}";
        twoPayler.confirm(body);
        System.out.println("test_getRecipientCard_p2p_method_null"+info(id));
    }

    public void test_confirm_without_id() throws InterruptedException {
        twoPayler = new TwoPayler(TwoPayler.TwoPaylerBody.defaultBody(true));
        twoPayler.pay_in();
        id = twoPayler.getId();
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+"\n" +
                "}";
        twoPayler.get_methods_list(body);
        json = (JSONObject) new JSONObject(twoPayler.getResponse()).getJSONObject("response").getJSONArray("p2p_methods").get(0);
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
        twoPayler.get_recipient_card(body);
        Thread.sleep(sleep);
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "    \"confirmation_fields\": {\n" +
                "        \"confirmation_amount\": \"35000\",\n" +
                "        \"sender_card_number\": \"4149785236985410\"\n" +
                "    }" +
                "}";
        twoPayler.confirm(body);
        System.out.println("test_confirm_without_id"+info(id));
    }

    public void test_confirm_id_notReal() throws InterruptedException {
        twoPayler = new TwoPayler(TwoPayler.TwoPaylerBody.defaultBody(true));
        twoPayler.pay_in();
        id = twoPayler.getId();
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+"\n" +
                "}";
        twoPayler.get_methods_list(body);
        json = (JSONObject) new JSONObject(twoPayler.getResponse()).getJSONObject("response").getJSONArray("p2p_methods").get(0);
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
        twoPayler.get_recipient_card(body);
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
                "        \"confirmation_amount\": \"35000\",\n" +
                "        \"sender_card_number\": \"4149785236985410\"\n" +
                "    }" +
                "}";
        twoPayler.confirm(body);
        System.out.println("test_confirm_id_notReal"+info(id));
    }

    public void test_confirm_id_null() throws InterruptedException {
        twoPayler = new TwoPayler(TwoPayler.TwoPaylerBody.defaultBody(true));
        twoPayler.pay_in();
        id = twoPayler.getId();
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+"\n" +
                "}";
        twoPayler.get_methods_list(body);
        json = (JSONObject) new JSONObject(twoPayler.getResponse()).getJSONObject("response").getJSONArray("p2p_methods").get(0);
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
        twoPayler.get_recipient_card(body);
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
                "        \"confirmation_amount\": \"35000\",\n" +
                "        \"sender_card_number\": \"4149785236985410\"\n" +
                "    }" +
                "}";
        twoPayler.confirm(body);
        System.out.println("test_confirm_id_null"+info(id));
    }

    public void test_confirm_without_confirmation_amount() throws InterruptedException {
        twoPayler = new TwoPayler(TwoPayler.TwoPaylerBody.defaultBody(true));
        twoPayler.pay_in();
        id = twoPayler.getId();
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+"\n" +
                "}";
        twoPayler.get_methods_list(body);
        json = (JSONObject) new JSONObject(twoPayler.getResponse()).getJSONObject("response").getJSONArray("p2p_methods").get(0);
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
        twoPayler.get_recipient_card(body);
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
                "        \"sender_card_number\": \"4149785236985410\"\n" +
                "    }" +
                "}";
        twoPayler.confirm(body);
        System.out.println("test_confirm_without_confirmation_amount"+info(id));
    }

    public void test_confirm_confirmation_amount_null() throws InterruptedException {
        twoPayler = new TwoPayler(TwoPayler.TwoPaylerBody.defaultBody(true));
        twoPayler.pay_in();
        id = twoPayler.getId();
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+"\n" +
                "}";
        twoPayler.get_methods_list(body);
        json = (JSONObject) new JSONObject(twoPayler.getResponse()).getJSONObject("response").getJSONArray("p2p_methods").get(0);
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
        twoPayler.get_recipient_card(body);
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
                "        \"confirmation_amount\": null,\n" +
                "        \"sender_card_number\": \"4149785236985410\"\n" +
                "    }" +
                "}";
        twoPayler.confirm(body);
        System.out.println("test_confirm_confirmation_amount_null"+info(id));
    }

    public void test_confirm_confirmation_amount_empty() throws InterruptedException {
        twoPayler = new TwoPayler(TwoPayler.TwoPaylerBody.defaultBody(true));
        twoPayler.pay_in();
        id = twoPayler.getId();
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+"\n" +
                "}";
        twoPayler.get_methods_list(body);
        json = (JSONObject) new JSONObject(twoPayler.getResponse()).getJSONObject("response").getJSONArray("p2p_methods").get(0);
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
        twoPayler.get_recipient_card(body);
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
                "        \"confirmation_amount\": \"\",\n" +
                "        \"sender_card_number\": \"4149785236985410\"\n" +
                "    }" +
                "}";
        twoPayler.confirm(body);
        System.out.println("test_confirm_confirmation_amount_empty"+info(id));
    }

    public void test_confirm_confirmation_amount_bigger() throws InterruptedException {
        twoPayler = new TwoPayler(TwoPayler.TwoPaylerBody.defaultBody(true));
        twoPayler.pay_in();
        id = twoPayler.getId();
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+"\n" +
                "}";
        twoPayler.get_methods_list(body);
        json = (JSONObject) new JSONObject(twoPayler.getResponse()).getJSONObject("response").getJSONArray("p2p_methods").get(0);
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
        twoPayler.get_recipient_card(body);
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
                "        \"confirmation_amount\": \"9999999\",\n" +
                "        \"sender_card_number\": \"4149785236985410\"\n" +
                "    }" +
                "}";
        twoPayler.confirm(body);
        System.out.println("test_confirm_confirmation_amount_bigger"+info(id));
    }

    public void test_confirm_confirmation_amount_lessZero() throws InterruptedException {
        twoPayler = new TwoPayler(TwoPayler.TwoPaylerBody.defaultBody(true));
        twoPayler.pay_in();
        id = twoPayler.getId();
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+"\n" +
                "}";
        twoPayler.get_methods_list(body);
        json = (JSONObject) new JSONObject(twoPayler.getResponse()).getJSONObject("response").getJSONArray("p2p_methods").get(0);
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
        twoPayler.get_recipient_card(body);
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
                "        \"confirmation_amount\": \"-111\",\n" +
                "        \"sender_card_number\": \"4149785236985410\"\n" +
                "    }" +
                "}";
        twoPayler.confirm(body);
        System.out.println("test_confirm_confirmation_amount_lessZero"+info(id));
    }

    public void test_confirm_confirmation_amount_str() throws InterruptedException {
        twoPayler = new TwoPayler(TwoPayler.TwoPaylerBody.defaultBody(true));
        twoPayler.pay_in();
        id = twoPayler.getId();
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+"\n" +
                "}";
        twoPayler.get_methods_list(body);
        json = (JSONObject) new JSONObject(twoPayler.getResponse()).getJSONObject("response").getJSONArray("p2p_methods").get(0);
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
        twoPayler.get_recipient_card(body);
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
                "        \"confirmation_amount\": \"ten\",\n" +
                "        \"sender_card_number\": \"4149785236985410\"\n" +
                "    }" +
                "}";
        twoPayler.confirm(body);
        System.out.println("test_confirm_confirmation_amount_str"+info(id));
    }

    public void test_confirm_confirmation_amount_rusStr() throws InterruptedException {
        twoPayler = new TwoPayler(TwoPayler.TwoPaylerBody.defaultBody(true));
        twoPayler.pay_in();
        id = twoPayler.getId();
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+"\n" +
                "}";
        twoPayler.get_methods_list(body);
        json = (JSONObject) new JSONObject(twoPayler.getResponse()).getJSONObject("response").getJSONArray("p2p_methods").get(0);
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
        twoPayler.get_recipient_card(body);
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
                "        \"confirmation_amount\": \"десять\",\n" +
                "        \"sender_card_number\": \"4149785236985410\"\n" +
                "    }" +
                "}";
        twoPayler.confirm(body);
        System.out.println("test_confirm_confirmation_amount_rusStr"+info(id));
    }

    public void test_confirm_sender_card_number_null() throws InterruptedException {
        twoPayler = new TwoPayler(TwoPayler.TwoPaylerBody.defaultBody(true));
        twoPayler.pay_in();
        id = twoPayler.getId();
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+"\n" +
                "}";
        twoPayler.get_methods_list(body);
        json = (JSONObject) new JSONObject(twoPayler.getResponse()).getJSONObject("response").getJSONArray("p2p_methods").get(0);
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
        twoPayler.get_recipient_card(body);
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
                "        \"confirmation_amount\": \"35000\",\n" +
                "        \"sender_card_number\": null\n" +
                "    }" +
                "}";
        twoPayler.confirm(body);
        System.out.println("test_confirm_sender_card_number_null"+info(id));
    }

    public void test_confirm_sender_card_number_empty() throws InterruptedException {
        twoPayler = new TwoPayler(TwoPayler.TwoPaylerBody.defaultBody(true));
        twoPayler.pay_in();
        id = twoPayler.getId();
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+"\n" +
                "}";
        twoPayler.get_methods_list(body);
        json = (JSONObject) new JSONObject(twoPayler.getResponse()).getJSONObject("response").getJSONArray("p2p_methods").get(0);
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
        twoPayler.get_recipient_card(body);
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
                "        \"confirmation_amount\": \"35000\",\n" +
                "        \"sender_card_number\": \"\"\n" +
                "    }" +
                "}";
        twoPayler.confirm(body);
        System.out.println("test_confirm_sender_card_number_empty"+info(id));
    }

    public void test_confirm_without_sender_card_number() throws InterruptedException {
        twoPayler = new TwoPayler(TwoPayler.TwoPaylerBody.defaultBody(true));
        twoPayler.pay_in();
        id = twoPayler.getId();
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+"\n" +
                "}";
        twoPayler.get_methods_list(body);
        json = (JSONObject) new JSONObject(twoPayler.getResponse()).getJSONObject("response").getJSONArray("p2p_methods").get(0);
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
        twoPayler.get_recipient_card(body);
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
                "        \"confirmation_amount\": \"35000\"\n" +
                "    }" +
                "}";
        twoPayler.confirm(body);
        System.out.println("test_confirm_without_sender_card_number"+info(id));
    }

    public void test_confirm_sender_card_number_notLuhn() throws InterruptedException {
        twoPayler = new TwoPayler(TwoPayler.TwoPaylerBody.defaultBody(true));
        twoPayler.pay_in();
        id = twoPayler.getId();
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+"\n" +
                "}";
        twoPayler.get_methods_list(body);
        json = (JSONObject) new JSONObject(twoPayler.getResponse()).getJSONObject("response").getJSONArray("p2p_methods").get(0);
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
        twoPayler.get_recipient_card(body);
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
                "        \"confirmation_amount\": \"35000\",\n" +
                "        \"sender_card_number\": \"4149785236985411\"\n" +
                "    }" +
                "}";
        twoPayler.confirm(body);
        System.out.println("test_confirm_sender_card_number_notLuhn"+info(id));
    }

    public void test_confirm_sender_card_number_notCorrect() throws InterruptedException {
        twoPayler = new TwoPayler(TwoPayler.TwoPaylerBody.defaultBody(true));
        twoPayler.pay_in();
        id = twoPayler.getId();
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+"\n" +
                "}";
        twoPayler.get_methods_list(body);
        json = (JSONObject) new JSONObject(twoPayler.getResponse()).getJSONObject("response").getJSONArray("p2p_methods").get(0);
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
        twoPayler.get_recipient_card(body);
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
                "        \"confirmation_amount\": \"35000\",\n" +
                "        \"sender_card_number\": \"41497852369854\"\n" +
                "    }" +
                "}";
        twoPayler.confirm(body);
        System.out.println("test_confirm_sender_card_number_notCorrect"+info(id));
    }

    public void test_confirm_sender_card_number_str() throws InterruptedException {
        twoPayler = new TwoPayler(TwoPayler.TwoPaylerBody.defaultBody(true));
        twoPayler.pay_in();
        id = twoPayler.getId();
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+"\n" +
                "}";
        twoPayler.get_methods_list(body);
        json = (JSONObject) new JSONObject(twoPayler.getResponse()).getJSONObject("response").getJSONArray("p2p_methods").get(0);
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
        twoPayler.get_recipient_card(body);
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
                "        \"confirmation_amount\": \"35000\",\n" +
                "        \"sender_card_number\": \"dafadsf\"\n" +
                "    }" +
                "}";
        twoPayler.confirm(body);
        System.out.println("test_confirm_sender_card_number_str"+info(id));
    }

    public void test_confirm_sender_card_number_rusStr() throws InterruptedException {
        twoPayler = new TwoPayler(TwoPayler.TwoPaylerBody.defaultBody(true));
        twoPayler.pay_in();
        id = twoPayler.getId();
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+"\n" +
                "}";
        twoPayler.get_methods_list(body);
        json = (JSONObject) new JSONObject(twoPayler.getResponse()).getJSONObject("response").getJSONArray("p2p_methods").get(0);
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
        twoPayler.get_recipient_card(body);
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
                "        \"confirmation_amount\": \"35000\",\n" +
                "        \"sender_card_number\": \"ываув\"\n" +
                "    }" +
                "}";
        twoPayler.confirm(body);
        System.out.println("test_confirm_sender_card_number_rusStr"+info(id));
    }

    public void h2h_create_get_methods_list_confirm() throws InterruptedException {
        twoPayler = new TwoPayler(TwoPayler.TwoPaylerBody.defaultBody(true));
        twoPayler.pay_in();
        id = twoPayler.getId();
        logger.info(id);
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+"\n" +
                "}";
        twoPayler.get_methods_list(body);
        json = (JSONObject) new JSONObject(twoPayler.getResponse()).getJSONObject("response").getJSONArray("p2p_methods").get(0);
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
                "        \"confirmation_amount\": \"35000\",\n" +
                "        \"sender_card_number\": \"4149785236985410\"\n" +
                "    }" +
                "}";
        twoPayler.confirm(body);
        System.out.println("h2h_create_get_methods_list_confirm"+info(id));
    }

    public void h2h_create_get_recipient_card_confirm() throws InterruptedException {
        twoPayler = new TwoPayler(TwoPayler.TwoPaylerBody.defaultBody(true));
        twoPayler.pay_in();
        id = twoPayler.getId();
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
        twoPayler.get_recipient_card(body);
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
                "        \"confirmation_amount\": \"35000\",\n" +
                "        \"sender_card_number\": \"4149785236985410\"\n" +
                "    }" +
                "}";
        twoPayler.confirm(body);
        System.out.println("h2h_create_get_recipient_card_confirm"+info(id));
    }

    public void h2h_create_confirm() {
        twoPayler = new TwoPayler(TwoPayler.TwoPaylerBody.defaultBody(true));
        twoPayler.pay_in();
        id = twoPayler.getId();
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "    \"id\": "+id+",\n" +
                "    \"confirmation_fields\": {\n" +
                "        \"confirmation_amount\": \"35000\",\n" +
                "        \"sender_card_number\": \"4149785236985410\"\n" +
                "    }" +
                "}";
        twoPayler.confirm(body);
        System.out.println("h2h_create_confirm"+info(id));
    }

    public void h2h_create_cancel() {
        twoPayler = new TwoPayler(TwoPayler.TwoPaylerBody.defaultBody(true));
        twoPayler.pay_in();
        id = twoPayler.getId();
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+"\n" +
                "}";
        twoPayler.cancel(body);
        System.out.println("h2h_create_cancel"+info(id));
    }



    String info(String idTrs){
        long child = Long.valueOf(idTrs) + 1;
        return "  ->   https://preprod-agora2.backofficeweb.info/transactions/transactions/"+child;
    }
}
