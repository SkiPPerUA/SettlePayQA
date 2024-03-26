package projectSettlePayTests.back.providersTests.P2Pay;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import projectSettlePay.BaseTest;
import projectSettlePay.back.providers.P2Pay;
import projectSettlePay.helper.BodyHelper;

@Test
public class P2PayH2H_test extends BaseTest {

    P2Pay p2Pay;
    String body;
    JSONObject json;
    String id;
    int sleep = 10000;

    public void positive_h2h_confirm() throws InterruptedException {
        p2Pay = new P2Pay(P2Pay.P2PayBody.defaultBody(true));
        p2Pay.pay_in();
        id = p2Pay.getId();
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+"\n" +
                "}";
        p2Pay.get_methods_list(body);
        json = (JSONObject) new JSONObject(p2Pay.getResponse()).getJSONObject("response").getJSONArray("p2p_methods").get(0);
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+",\n" +
                "\"p2p_method_id\" : \""+json.getString("id")+"\"\n" +
                "}";
        p2Pay.get_recipient_card(body);
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
                "        \"confirmation_amount\": \"30377\",\n" +
                "        \"sender_card_number\": \"4149785236985410\",\n" +
                "      \"first_name\": \"sender_name\",\n" +
                "      \"last_name\": \"sender_surname\"\n" +
                "\n" +
                "    }\n" +
                "}";
        p2Pay.confirm(body);
        System.out.println("positive_h2h_confirm"+info(id));
    }

    public void positive_h2h_cancel() throws InterruptedException {
        p2Pay = new P2Pay(P2Pay.P2PayBody.defaultBody(true));
        p2Pay.pay_in();
        id = p2Pay.getId();
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+"\n" +
                "}";
        p2Pay.get_methods_list(body);
        json = (JSONObject) new JSONObject(p2Pay.getResponse()).getJSONObject("response").getJSONArray("p2p_methods").get(0);
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+",\n" +
                "\"p2p_method_id\" : \""+json.getString("id")+"\"\n" +
                "}";
        p2Pay.get_recipient_card(body);
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
        p2Pay.cancel(body);
        System.out.println("positive_h2h_cancel"+info(id));
    }

    public void negative_h2h_confirm_after_cancel() throws InterruptedException {
        p2Pay = new P2Pay(P2Pay.P2PayBody.defaultBody(true));
        p2Pay.pay_in();
        id = p2Pay.getId();
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+"\n" +
                "}";
        p2Pay.get_methods_list(body);
        json = (JSONObject) new JSONObject(p2Pay.getResponse()).getJSONObject("response").getJSONArray("p2p_methods").get(0);
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+",\n" +
                "\"p2p_method_id\" : \""+json.getString("id")+"\"\n" +
                "}";
        p2Pay.get_recipient_card(body);
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
        p2Pay.cancel(body);
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "    \"id\": "+id+",\n" +
                "    \"confirmation_fields\": {\n" +
                "        \"confirmation_amount\": \"30377\",\n" +
                "        \"sender_card_number\": \"4149785236985410\",\n" +
                "      \"first_name\": \"sender_name\",\n" +
                "      \"last_name\": \"sender_surname\"\n" +
                "\n" +
                "    }\n" +
                "}";
        p2Pay.confirm(body);
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
                "    \"external_customer_id\": \""+ BodyHelper.generateUUID()+"\",\n" +
                "    \"account_id\": 2761,\n" +
                "    \"wallet_id\": 2529,\n" +
                "    \"service_id\": 3516,\n" +
                "    \"amount\": 30377,\n" +
                "    \"amount_currency\": \"UAH\",\n" +
                "    \"fields\" : {\n" +
                "        \"last_name\": \"sender_surname\"\n" +
                "    }"+
                "}";
        p2Pay = new P2Pay(body);
        p2Pay.pay_in();
        id = p2Pay.getId();
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+"\n" +
                "}";
        p2Pay.get_methods_list(body);
        json = (JSONObject) new JSONObject(p2Pay.getResponse()).getJSONObject("response").getJSONArray("p2p_methods").get(0);
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+",\n" +
                "\"p2p_method_id\" : \""+json.getString("id")+"\"\n" +
                "}";
        p2Pay.get_recipient_card(body);
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
                "        \"confirmation_amount\": \"30377\",\n" +
                "        \"sender_card_number\": \"4149785236985410\",\n" +
                "      \"first_name\": \"sender_name\",\n" +
                "      \"last_name\": \"sender_surname\"\n" +
                "\n" +
                "    }\n" +
                "}";
        p2Pay.confirm(body);
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
                "    \"external_customer_id\": \""+ BodyHelper.generateUUID()+"\",\n" +
                "    \"account_id\": 2761,\n" +
                "    \"wallet_id\": 2529,\n" +
                "    \"service_id\": 3516,\n" +
                "    \"amount\": 30377,\n" +
                "    \"amount_currency\": \"UAH\",\n" +
                "    \"fields\" : {\n" +
                "        \"first_name\": \"\","+
                "        \"last_name\": \"sender_surname\"\n" +
                "    }"+
                "}";
        p2Pay = new P2Pay(body);
        p2Pay.pay_in();
        id = p2Pay.getId();
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+"\n" +
                "}";
        p2Pay.get_methods_list(body);
        json = (JSONObject) new JSONObject(p2Pay.getResponse()).getJSONObject("response").getJSONArray("p2p_methods").get(0);
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+",\n" +
                "\"p2p_method_id\" : \""+json.getString("id")+"\"\n" +
                "}";
        p2Pay.get_recipient_card(body);
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
                "        \"confirmation_amount\": \"30377\",\n" +
                "        \"sender_card_number\": \"4149785236985410\",\n" +
                "      \"first_name\": \"sender_name\",\n" +
                "      \"last_name\": \"sender_surname\"\n" +
                "\n" +
                "    }\n" +
                "}";
        p2Pay.confirm(body);
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
                "    \"external_customer_id\": \""+ BodyHelper.generateUUID()+"\",\n" +
                "    \"account_id\": 2761,\n" +
                "    \"wallet_id\": 2529,\n" +
                "    \"service_id\": 3516,\n" +
                "    \"amount\": 30377,\n" +
                "    \"amount_currency\": \"UAH\",\n" +
                "    \"fields\" : {\n" +
                "        \"first_name\": null,"+
                "        \"last_name\": \"sender_surname\"\n" +
                "    }"+
                "}";
        p2Pay = new P2Pay(body);
        p2Pay.pay_in();
        Assert.assertTrue(p2Pay.getResponse().contains("@fields[first_name] = None value type is not str"));
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
                "    \"external_customer_id\": \""+ BodyHelper.generateUUID()+"\",\n" +
                "    \"account_id\": 2761,\n" +
                "    \"wallet_id\": 2529,\n" +
                "    \"service_id\": 3516,\n" +
                "    \"amount\": 30377,\n" +
                "    \"amount_currency\": \"UAH\",\n" +
                "    \"fields\" : {\n" +
                "        \"first_name\": \"sender_surname\"\n" +
                "    }"+
                "}";
        p2Pay = new P2Pay(body);
        p2Pay.pay_in();
        id = p2Pay.getId();
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+"\n" +
                "}";
        p2Pay.get_methods_list(body);
        json = (JSONObject) new JSONObject(p2Pay.getResponse()).getJSONObject("response").getJSONArray("p2p_methods").get(0);
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+",\n" +
                "\"p2p_method_id\" : \""+json.getString("id")+"\"\n" +
                "}";
        p2Pay.get_recipient_card(body);
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
                "        \"confirmation_amount\": \"30377\",\n" +
                "        \"sender_card_number\": \"4149785236985410\",\n" +
                "      \"first_name\": \"sender_name\",\n" +
                "      \"last_name\": \"sender_surname\"\n" +
                "\n" +
                "    }\n" +
                "}";
        p2Pay.confirm(body);
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
                "    \"external_customer_id\": \""+ BodyHelper.generateUUID()+"\",\n" +
                "    \"account_id\": 2761,\n" +
                "    \"wallet_id\": 2529,\n" +
                "    \"service_id\": 3516,\n" +
                "    \"amount\": 30377,\n" +
                "    \"amount_currency\": \"UAH\",\n" +
                "    \"fields\" : {\n" +
                "        \"first_name\": \"sender_surname\","+
                "        \"last_name\": \"\"\n" +
                "    }"+
                "}";
        p2Pay = new P2Pay(body);
        p2Pay.pay_in();
        id = p2Pay.getId();
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+"\n" +
                "}";
        p2Pay.get_methods_list(body);
        json = (JSONObject) new JSONObject(p2Pay.getResponse()).getJSONObject("response").getJSONArray("p2p_methods").get(0);
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+",\n" +
                "\"p2p_method_id\" : \""+json.getString("id")+"\"\n" +
                "}";
        p2Pay.get_recipient_card(body);
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
                "        \"confirmation_amount\": \"30377\",\n" +
                "        \"sender_card_number\": \"4149785236985410\",\n" +
                "      \"first_name\": \"sender_name\",\n" +
                "      \"last_name\": \"sender_surname\"\n" +
                "\n" +
                "    }\n" +
                "}";
        p2Pay.confirm(body);
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
                "    \"external_customer_id\": \""+ BodyHelper.generateUUID()+"\",\n" +
                "    \"account_id\": 2761,\n" +
                "    \"wallet_id\": 2529,\n" +
                "    \"service_id\": 3516,\n" +
                "    \"amount\": 30377,\n" +
                "    \"amount_currency\": \"UAH\",\n" +
                "    \"fields\" : {\n" +
                "        \"first_name\": \"sender_surname\","+
                "        \"last_name\": null\n" +
                "    }"+
                "}";
        p2Pay = new P2Pay(body);
        p2Pay.pay_in();
        Assert.assertTrue(p2Pay.getResponse().contains("@fields[last_name] = None value type is not str"));
        System.out.println("test_create_last_name_null");
    }

    public void test_getMethod_without_id() {
        p2Pay = new P2Pay(P2Pay.P2PayBody.defaultBody(true));
        p2Pay.pay_in();
        id = p2Pay.getId();
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    }\n" +
                "}";
        p2Pay.get_methods_list(body);
        Assert.assertTrue(p2Pay.getResponse().contains("@id:required field"));
        System.out.println("test_getMethod_without_id");
    }

    public void test_getMethod_id_notReal() throws InterruptedException {
        p2Pay = new P2Pay(P2Pay.P2PayBody.defaultBody(true));
        p2Pay.pay_in();
        id = p2Pay.getId();
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+"\n" +
                "}";
        p2Pay.get_methods_list(body);
        json = (JSONObject) new JSONObject(p2Pay.getResponse()).getJSONObject("response").getJSONArray("p2p_methods").get(0);
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": 910100000478619,\n" +
                "\"p2p_method_id\" : \""+json.getString("id")+"\"\n" +
                "}";
        p2Pay.get_recipient_card(body);
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
                "        \"confirmation_amount\": \"30377\",\n" +
                "        \"sender_card_number\": \"4149785236985410\",\n" +
                "      \"first_name\": \"sender_name\",\n" +
                "      \"last_name\": \"sender_surname\"\n" +
                "\n" +
                "    }\n" +
                "}";
        p2Pay.confirm(body);
        System.out.println("test_getMethod_id_notReal"+info(id));
    }

    public void test_getRecipientCard_without_id() throws InterruptedException {
        p2Pay = new P2Pay(P2Pay.P2PayBody.defaultBody(true));
        p2Pay.pay_in();
        id = p2Pay.getId();
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+"\n" +
                "}";
        p2Pay.get_methods_list(body);
        json = (JSONObject) new JSONObject(p2Pay.getResponse()).getJSONObject("response").getJSONArray("p2p_methods").get(0);
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"p2p_method_id\" : \""+json.getString("id")+"\"\n" +
                "}";
        p2Pay.get_recipient_card(body);
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
                "        \"confirmation_amount\": \"30377\",\n" +
                "        \"sender_card_number\": \"4149785236985410\",\n" +
                "      \"first_name\": \"sender_name\",\n" +
                "      \"last_name\": \"sender_surname\"\n" +
                "\n" +
                "    }\n" +
                "}";
        p2Pay.confirm(body);
        System.out.println("test_getRecipientCard_without_id"+info(id));
    }

    public void test_getRecipientCard_without_p2p_method_id() throws InterruptedException {
        p2Pay = new P2Pay(P2Pay.P2PayBody.defaultBody(true));
        p2Pay.pay_in();
        id = p2Pay.getId();
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+"\n" +
                "}";
        p2Pay.get_methods_list(body);
        json = (JSONObject) new JSONObject(p2Pay.getResponse()).getJSONObject("response").getJSONArray("p2p_methods").get(0);
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+"\n" +
                "}";
        p2Pay.get_recipient_card(body);
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
                "        \"confirmation_amount\": \"30377\",\n" +
                "        \"sender_card_number\": \"4149785236985410\",\n" +
                "      \"first_name\": \"sender_name\",\n" +
                "      \"last_name\": \"sender_surname\"\n" +
                "\n" +
                "    }\n" +
                "}";
        p2Pay.confirm(body);
        System.out.println("test_getRecipientCard_without_p2p_method_id"+info(id));
    }

    public void test_getRecipientCard_id_notReal() throws InterruptedException {
        p2Pay = new P2Pay(P2Pay.P2PayBody.defaultBody(true));
        p2Pay.pay_in();
        id = p2Pay.getId();
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+"\n" +
                "}";
        p2Pay.get_methods_list(body);
        json = (JSONObject) new JSONObject(p2Pay.getResponse()).getJSONObject("response").getJSONArray("p2p_methods").get(0);
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": 910100000478619,\n" +
                "\"p2p_method_id\" : \""+json.getString("id")+"\"\n" +
                "}";
        p2Pay.get_recipient_card(body);
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
                "        \"confirmation_amount\": \"30377\",\n" +
                "        \"sender_card_number\": \"4149785236985410\",\n" +
                "      \"first_name\": \"sender_name\",\n" +
                "      \"last_name\": \"sender_surname\"\n" +
                "\n" +
                "    }\n" +
                "}";
        p2Pay.confirm(body);
        System.out.println("test_getRecipientCard_id_notReal"+info(id));
    }

    public void test_getRecipientCard_id_null() throws InterruptedException {
        p2Pay = new P2Pay(P2Pay.P2PayBody.defaultBody(true));
        p2Pay.pay_in();
        id = p2Pay.getId();
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+"\n" +
                "}";
        p2Pay.get_methods_list(body);
        json = (JSONObject) new JSONObject(p2Pay.getResponse()).getJSONObject("response").getJSONArray("p2p_methods").get(0);
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": null,\n" +
                "\"p2p_method_id\" : \""+json.getString("id")+"\"\n" +
                "}";
        p2Pay.get_recipient_card(body);
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
                "        \"confirmation_amount\": \"30377\",\n" +
                "        \"sender_card_number\": \"4149785236985410\",\n" +
                "      \"first_name\": \"sender_name\",\n" +
                "      \"last_name\": \"sender_surname\"\n" +
                "\n" +
                "    }\n" +
                "}";
        p2Pay.confirm(body);
        System.out.println("test_getRecipientCard_id_null"+info(id));
    }

    public void test_getRecipientCard_p2p_method_notReal() throws InterruptedException {
        p2Pay = new P2Pay(P2Pay.P2PayBody.defaultBody(true));
        p2Pay.pay_in();
        id = p2Pay.getId();
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+"\n" +
                "}";
        p2Pay.get_methods_list(body);
        json = (JSONObject) new JSONObject(p2Pay.getResponse()).getJSONObject("response").getJSONArray("p2p_methods").get(0);
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
        p2Pay.get_recipient_card(body);
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
                "        \"confirmation_amount\": \"30377\",\n" +
                "        \"sender_card_number\": \"4149785236985410\",\n" +
                "      \"first_name\": \"sender_name\",\n" +
                "      \"last_name\": \"sender_surname\"\n" +
                "\n" +
                "    }\n" +
                "}";
        p2Pay.confirm(body);
        System.out.println("test_getRecipientCard_p2p_method_notReal"+info(id));
    }

    public void test_getRecipientCard_p2p_method_empty() throws InterruptedException {
        p2Pay = new P2Pay(P2Pay.P2PayBody.defaultBody(true));
        p2Pay.pay_in();
        id = p2Pay.getId();
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+"\n" +
                "}";
        p2Pay.get_methods_list(body);
        json = (JSONObject) new JSONObject(p2Pay.getResponse()).getJSONObject("response").getJSONArray("p2p_methods").get(0);
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
        p2Pay.get_recipient_card(body);
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
                "        \"confirmation_amount\": \"30377\",\n" +
                "        \"sender_card_number\": \"4149785236985410\",\n" +
                "      \"first_name\": \"sender_name\",\n" +
                "      \"last_name\": \"sender_surname\"\n" +
                "\n" +
                "    }\n" +
                "}";
        p2Pay.confirm(body);
        System.out.println("test_getRecipientCard_p2p_method_empty"+info(id));
    }

    public void test_getRecipientCard_p2p_method_null() throws InterruptedException {
        p2Pay = new P2Pay(P2Pay.P2PayBody.defaultBody(true));
        p2Pay.pay_in();
        id = p2Pay.getId();
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+"\n" +
                "}";
        p2Pay.get_methods_list(body);
        json = (JSONObject) new JSONObject(p2Pay.getResponse()).getJSONObject("response").getJSONArray("p2p_methods").get(0);
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
        p2Pay.get_recipient_card(body);
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
                "        \"confirmation_amount\": \"30377\",\n" +
                "        \"sender_card_number\": \"4149785236985410\",\n" +
                "      \"first_name\": \"sender_name\",\n" +
                "      \"last_name\": \"sender_surname\"\n" +
                "\n" +
                "    }\n" +
                "}";
        p2Pay.confirm(body);
        System.out.println("test_getRecipientCard_p2p_method_null"+info(id));
    }

    public void test_confirm_without_id() throws InterruptedException {
        p2Pay = new P2Pay(P2Pay.P2PayBody.defaultBody(true));
        p2Pay.pay_in();
        id = p2Pay.getId();
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+"\n" +
                "}";
        p2Pay.get_methods_list(body);
        json = (JSONObject) new JSONObject(p2Pay.getResponse()).getJSONObject("response").getJSONArray("p2p_methods").get(0);
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+",\n" +
                "\"p2p_method_id\" : \""+json.getString("id")+"\"\n" +
                "}";
        p2Pay.get_recipient_card(body);
        Thread.sleep(sleep);
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "    \"confirmation_fields\": {\n" +
                "        \"confirmation_amount\": \"30377\",\n" +
                "        \"sender_card_number\": \"4149785236985410\",\n" +
                "      \"first_name\": \"sender_name\",\n" +
                "      \"last_name\": \"sender_surname\"\n" +
                "\n" +
                "    }\n" +
                "}";
        p2Pay.confirm(body);
        System.out.println("test_confirm_without_id"+info(id));
    }

    public void test_confirm_id_notReal() throws InterruptedException {
        p2Pay = new P2Pay(P2Pay.P2PayBody.defaultBody(true));
        p2Pay.pay_in();
        id = p2Pay.getId();
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+"\n" +
                "}";
        p2Pay.get_methods_list(body);
        json = (JSONObject) new JSONObject(p2Pay.getResponse()).getJSONObject("response").getJSONArray("p2p_methods").get(0);
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+",\n" +
                "\"p2p_method_id\" : \""+json.getString("id")+"\"\n" +
                "}";
        p2Pay.get_recipient_card(body);
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
                "        \"confirmation_amount\": \"30377\",\n" +
                "        \"sender_card_number\": \"4149785236985410\",\n" +
                "      \"first_name\": \"sender_name\",\n" +
                "      \"last_name\": \"sender_surname\"\n" +
                "\n" +
                "    }\n" +
                "}";
        p2Pay.confirm(body);
        System.out.println("test_confirm_id_notReal"+info(id));
    }

    public void test_confirm_id_null() throws InterruptedException {
        p2Pay = new P2Pay(P2Pay.P2PayBody.defaultBody(true));
        p2Pay.pay_in();
        id = p2Pay.getId();
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+"\n" +
                "}";
        p2Pay.get_methods_list(body);
        json = (JSONObject) new JSONObject(p2Pay.getResponse()).getJSONObject("response").getJSONArray("p2p_methods").get(0);
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+",\n" +
                "\"p2p_method_id\" : \""+json.getString("id")+"\"\n" +
                "}";
        p2Pay.get_recipient_card(body);
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
                "        \"confirmation_amount\": \"30377\",\n" +
                "        \"sender_card_number\": \"4149785236985410\",\n" +
                "      \"first_name\": \"sender_name\",\n" +
                "      \"last_name\": \"sender_surname\"\n" +
                "\n" +
                "    }\n" +
                "}";
        p2Pay.confirm(body);
        System.out.println("test_confirm_id_null"+info(id));
    }

    public void test_confirm_without_confirmation_amount() throws InterruptedException {
        p2Pay = new P2Pay(P2Pay.P2PayBody.defaultBody(true));
        p2Pay.pay_in();
        id = p2Pay.getId();
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+"\n" +
                "}";
        p2Pay.get_methods_list(body);
        json = (JSONObject) new JSONObject(p2Pay.getResponse()).getJSONObject("response").getJSONArray("p2p_methods").get(0);
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+",\n" +
                "\"p2p_method_id\" : \""+json.getString("id")+"\"\n" +
                "}";
        p2Pay.get_recipient_card(body);
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
                "        \"sender_card_number\": \"4149785236985410\",\n" +
                "      \"first_name\": \"sender_name\",\n" +
                "      \"last_name\": \"sender_surname\"\n" +
                "\n" +
                "    }\n" +
                "}";
        p2Pay.confirm(body);
        System.out.println("test_confirm_without_confirmation_amount"+info(id));
    }

    public void test_confirm_confirmation_amount_null() throws InterruptedException {
        p2Pay = new P2Pay(P2Pay.P2PayBody.defaultBody(true));
        p2Pay.pay_in();
        id = p2Pay.getId();
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+"\n" +
                "}";
        p2Pay.get_methods_list(body);
        json = (JSONObject) new JSONObject(p2Pay.getResponse()).getJSONObject("response").getJSONArray("p2p_methods").get(0);
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+",\n" +
                "\"p2p_method_id\" : \""+json.getString("id")+"\"\n" +
                "}";
        p2Pay.get_recipient_card(body);
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
                "        \"sender_card_number\": \"4149785236985410\",\n" +
                "      \"first_name\": \"sender_name\",\n" +
                "      \"last_name\": \"sender_surname\"\n" +
                "\n" +
                "    }\n" +
                "}";
        p2Pay.confirm(body);
        System.out.println("test_confirm_confirmation_amount_null"+info(id));
    }

    public void test_confirm_confirmation_amount_empty() throws InterruptedException {
        p2Pay = new P2Pay(P2Pay.P2PayBody.defaultBody(true));
        p2Pay.pay_in();
        id = p2Pay.getId();
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+"\n" +
                "}";
        p2Pay.get_methods_list(body);
        json = (JSONObject) new JSONObject(p2Pay.getResponse()).getJSONObject("response").getJSONArray("p2p_methods").get(0);
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+",\n" +
                "\"p2p_method_id\" : \""+json.getString("id")+"\"\n" +
                "}";
        p2Pay.get_recipient_card(body);
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
                "        \"sender_card_number\": \"4149785236985410\",\n" +
                "      \"first_name\": \"sender_name\",\n" +
                "      \"last_name\": \"sender_surname\"\n" +
                "\n" +
                "    }\n" +
                "}";
        p2Pay.confirm(body);
        System.out.println("test_confirm_confirmation_amount_empty"+info(id));
    }

    public void test_confirm_confirmation_amount_MIN() throws InterruptedException {
        p2Pay = new P2Pay(P2Pay.P2PayBody.defaultBody(true));
        p2Pay.pay_in();
        id = p2Pay.getId();
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+"\n" +
                "}";
        p2Pay.get_methods_list(body);
        json = (JSONObject) new JSONObject(p2Pay.getResponse()).getJSONObject("response").getJSONArray("p2p_methods").get(0);
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+",\n" +
                "\"p2p_method_id\" : \""+json.getString("id")+"\"\n" +
                "}";
        p2Pay.get_recipient_card(body);
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
                "        \"confirmation_amount\": \"1\",\n" +
                "        \"sender_card_number\": \"4149785236985410\",\n" +
                "      \"first_name\": \"sender_name\",\n" +
                "      \"last_name\": \"sender_surname\"\n" +
                "\n" +
                "    }\n" +
                "}";
        p2Pay.confirm(body);
        System.out.println("test_confirm_confirmation_amount_MIN"+info(id));
    }

    public void test_confirm_confirmation_amount_MAX() throws InterruptedException {
        p2Pay = new P2Pay(P2Pay.P2PayBody.defaultBody(true));
        p2Pay.pay_in();
        id = p2Pay.getId();
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+"\n" +
                "}";
        p2Pay.get_methods_list(body);
        json = (JSONObject) new JSONObject(p2Pay.getResponse()).getJSONObject("response").getJSONArray("p2p_methods").get(0);
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+",\n" +
                "\"p2p_method_id\" : \""+json.getString("id")+"\"\n" +
                "}";
        p2Pay.get_recipient_card(body);
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
                "        \"confirmation_amount\": \"99999999999999\",\n" +
                "        \"sender_card_number\": \"4149785236985410\",\n" +
                "      \"first_name\": \"sender_name\",\n" +
                "      \"last_name\": \"sender_surname\"\n" +
                "\n" +
                "    }\n" +
                "}";
        p2Pay.confirm(body);
        System.out.println("test_confirm_confirmation_amount_MAX"+info(id));
    }

    public void test_confirm_confirmation_amount_less() throws InterruptedException {
        p2Pay = new P2Pay(P2Pay.P2PayBody.defaultBody(true));
        p2Pay.pay_in();
        id = p2Pay.getId();
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+"\n" +
                "}";
        p2Pay.get_methods_list(body);
        json = (JSONObject) new JSONObject(p2Pay.getResponse()).getJSONObject("response").getJSONArray("p2p_methods").get(0);
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+",\n" +
                "\"p2p_method_id\" : \""+json.getString("id")+"\"\n" +
                "}";
        p2Pay.get_recipient_card(body);
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
                "        \"confirmation_amount\": \"30000\",\n" +
                "        \"sender_card_number\": \"4149785236985410\",\n" +
                "      \"first_name\": \"sender_name\",\n" +
                "      \"last_name\": \"sender_surname\"\n" +
                "\n" +
                "    }\n" +
                "}";
        p2Pay.confirm(body);
        System.out.println("test_confirm_confirmation_amount_less"+info(id));
    }

    public void test_confirm_confirmation_amount_bigger() throws InterruptedException {
        p2Pay = new P2Pay(P2Pay.P2PayBody.defaultBody(true));
        p2Pay.pay_in();
        id = p2Pay.getId();
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+"\n" +
                "}";
        p2Pay.get_methods_list(body);
        json = (JSONObject) new JSONObject(p2Pay.getResponse()).getJSONObject("response").getJSONArray("p2p_methods").get(0);
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+",\n" +
                "\"p2p_method_id\" : \""+json.getString("id")+"\"\n" +
                "}";
        p2Pay.get_recipient_card(body);
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
                "        \"confirmation_amount\": \"50000\",\n" +
                "        \"sender_card_number\": \"4149785236985410\",\n" +
                "      \"first_name\": \"sender_name\",\n" +
                "      \"last_name\": \"sender_surname\"\n" +
                "\n" +
                "    }\n" +
                "}";
        p2Pay.confirm(body);
        System.out.println("test_confirm_confirmation_amount_bigger"+info(id));
    }

    public void test_confirm_without_sender_card_number() throws InterruptedException {
        p2Pay = new P2Pay(P2Pay.P2PayBody.defaultBody(true));
        p2Pay.pay_in();
        id = p2Pay.getId();
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+"\n" +
                "}";
        p2Pay.get_methods_list(body);
        json = (JSONObject) new JSONObject(p2Pay.getResponse()).getJSONObject("response").getJSONArray("p2p_methods").get(0);
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+",\n" +
                "\"p2p_method_id\" : \""+json.getString("id")+"\"\n" +
                "}";
        p2Pay.get_recipient_card(body);
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
                "        \"confirmation_amount\": \"30377\",\n" +
                "      \"first_name\": \"sender_name\",\n" +
                "      \"last_name\": \"sender_surname\"\n" +
                "\n" +
                "    }\n" +
                "}";
        p2Pay.confirm(body);
        System.out.println("test_confirm_without_sender_card_number"+info(id));
    }

    public void test_confirm_sender_card_number_empty() throws InterruptedException {
        p2Pay = new P2Pay(P2Pay.P2PayBody.defaultBody(true));
        p2Pay.pay_in();
        id = p2Pay.getId();
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+"\n" +
                "}";
        p2Pay.get_methods_list(body);
        json = (JSONObject) new JSONObject(p2Pay.getResponse()).getJSONObject("response").getJSONArray("p2p_methods").get(0);
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+",\n" +
                "\"p2p_method_id\" : \""+json.getString("id")+"\"\n" +
                "}";
        p2Pay.get_recipient_card(body);
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
                "        \"confirmation_amount\": \"30377\",\n" +
                "        \"sender_card_number\": \"\",\n" +
                "      \"first_name\": \"sender_name\",\n" +
                "      \"last_name\": \"sender_surname\"\n" +
                "\n" +
                "    }\n" +
                "}";
        p2Pay.confirm(body);
        System.out.println("test_confirm_sender_card_number_empty"+info(id));
    }

    public void test_confirm_sender_card_number_null() throws InterruptedException {
        p2Pay = new P2Pay(P2Pay.P2PayBody.defaultBody(true));
        p2Pay.pay_in();
        id = p2Pay.getId();
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+"\n" +
                "}";
        p2Pay.get_methods_list(body);
        json = (JSONObject) new JSONObject(p2Pay.getResponse()).getJSONObject("response").getJSONArray("p2p_methods").get(0);
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+",\n" +
                "\"p2p_method_id\" : \""+json.getString("id")+"\"\n" +
                "}";
        p2Pay.get_recipient_card(body);
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
                "        \"confirmation_amount\": \"30377\",\n" +
                "        \"sender_card_number\": null,\n" +
                "      \"first_name\": \"sender_name\",\n" +
                "      \"last_name\": \"sender_surname\"\n" +
                "\n" +
                "    }\n" +
                "}";
        p2Pay.confirm(body);
        System.out.println("test_confirm_sender_card_number_null"+info(id));
    }

    public void test_confirm_sender_card_number_notLuhn() throws InterruptedException {
        p2Pay = new P2Pay(P2Pay.P2PayBody.defaultBody(true));
        p2Pay.pay_in();
        id = p2Pay.getId();
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+"\n" +
                "}";
        p2Pay.get_methods_list(body);
        json = (JSONObject) new JSONObject(p2Pay.getResponse()).getJSONObject("response").getJSONArray("p2p_methods").get(0);
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+",\n" +
                "\"p2p_method_id\" : \""+json.getString("id")+"\"\n" +
                "}";
        p2Pay.get_recipient_card(body);
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
                "        \"confirmation_amount\": \"30377\",\n" +
                "        \"sender_card_number\": \"4438231558582694\",\n" +
                "      \"first_name\": \"sender_name\",\n" +
                "      \"last_name\": \"sender_surname\"\n" +
                "\n" +
                "    }\n" +
                "}";
        p2Pay.confirm(body);
        System.out.println("test_confirm_sender_card_number_notLuhn"+info(id));
    }

    public void test_confirm_without_first_name() throws InterruptedException {
        p2Pay = new P2Pay(P2Pay.P2PayBody.defaultBody(true));
        p2Pay.pay_in();
        id = p2Pay.getId();
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+"\n" +
                "}";
        p2Pay.get_methods_list(body);
        json = (JSONObject) new JSONObject(p2Pay.getResponse()).getJSONObject("response").getJSONArray("p2p_methods").get(0);
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+",\n" +
                "\"p2p_method_id\" : \""+json.getString("id")+"\"\n" +
                "}";
        p2Pay.get_recipient_card(body);
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
                "        \"confirmation_amount\": \"30377\",\n" +
                "        \"sender_card_number\": \"4149785236985410\",\n" +
                "      \"last_name\": \"sender_surname\"\n" +
                "\n" +
                "    }\n" +
                "}";
        p2Pay.confirm(body);
        System.out.println("test_confirm_without_first_name"+info(id));
    }

    public void test_confirm_first_name_empty() throws InterruptedException {
        p2Pay = new P2Pay(P2Pay.P2PayBody.defaultBody(true));
        p2Pay.pay_in();
        id = p2Pay.getId();
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+"\n" +
                "}";
        p2Pay.get_methods_list(body);
        json = (JSONObject) new JSONObject(p2Pay.getResponse()).getJSONObject("response").getJSONArray("p2p_methods").get(0);
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+",\n" +
                "\"p2p_method_id\" : \""+json.getString("id")+"\"\n" +
                "}";
        p2Pay.get_recipient_card(body);
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
                "        \"confirmation_amount\": \"30377\",\n" +
                "        \"sender_card_number\": \"4149785236985410\",\n" +
                "      \"first_name\": \"\",\n" +
                "      \"last_name\": \"sender_surname\"\n" +
                "\n" +
                "    }\n" +
                "}";
        p2Pay.confirm(body);
        System.out.println("test_confirm_first_name_empty"+info(id));
    }

    public void test_confirm_first_name_null() throws InterruptedException {
        p2Pay = new P2Pay(P2Pay.P2PayBody.defaultBody(true));
        p2Pay.pay_in();
        id = p2Pay.getId();
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+"\n" +
                "}";
        p2Pay.get_methods_list(body);
        json = (JSONObject) new JSONObject(p2Pay.getResponse()).getJSONObject("response").getJSONArray("p2p_methods").get(0);
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+",\n" +
                "\"p2p_method_id\" : \""+json.getString("id")+"\"\n" +
                "}";
        p2Pay.get_recipient_card(body);
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
                "        \"confirmation_amount\": \"30377\",\n" +
                "        \"sender_card_number\": \"4149785236985410\",\n" +
                "      \"first_name\": null,\n" +
                "      \"last_name\": \"sender_surname\"\n" +
                "\n" +
                "    }\n" +
                "}";
        p2Pay.confirm(body);
        System.out.println("test_confirm_first_name_null"+info(id));
    }

    public void test_confirm_without_last_name() throws InterruptedException {
        p2Pay = new P2Pay(P2Pay.P2PayBody.defaultBody(true));
        p2Pay.pay_in();
        id = p2Pay.getId();
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+"\n" +
                "}";
        p2Pay.get_methods_list(body);
        json = (JSONObject) new JSONObject(p2Pay.getResponse()).getJSONObject("response").getJSONArray("p2p_methods").get(0);
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+",\n" +
                "\"p2p_method_id\" : \""+json.getString("id")+"\"\n" +
                "}";
        p2Pay.get_recipient_card(body);
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
                "        \"confirmation_amount\": \"30377\",\n" +
                "        \"sender_card_number\": \"4149785236985410\",\n" +
                "      \"first_name\": \"sender_surname\"\n" +
                "\n" +
                "    }\n" +
                "}";
        p2Pay.confirm(body);
        System.out.println("test_confirm_without_last_name"+info(id));
    }

    public void test_confirm_last_name_empty() throws InterruptedException {
        p2Pay = new P2Pay(P2Pay.P2PayBody.defaultBody(true));
        p2Pay.pay_in();
        id = p2Pay.getId();
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+"\n" +
                "}";
        p2Pay.get_methods_list(body);
        json = (JSONObject) new JSONObject(p2Pay.getResponse()).getJSONObject("response").getJSONArray("p2p_methods").get(0);
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+",\n" +
                "\"p2p_method_id\" : \""+json.getString("id")+"\"\n" +
                "}";
        p2Pay.get_recipient_card(body);
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
                "        \"confirmation_amount\": \"30377\",\n" +
                "        \"sender_card_number\": \"4149785236985410\",\n" +
                "      \"first_name\": \"sender_surname\",\n" +
                "      \"last_name\": \"\"\n" +
                "\n" +
                "    }\n" +
                "}";
        p2Pay.confirm(body);
        System.out.println("test_confirm_last_name_empty"+info(id));
    }

    public void test_confirm_last_name_null() throws InterruptedException {
        p2Pay = new P2Pay(P2Pay.P2PayBody.defaultBody(true));
        p2Pay.pay_in();
        id = p2Pay.getId();
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+"\n" +
                "}";
        p2Pay.get_methods_list(body);
        json = (JSONObject) new JSONObject(p2Pay.getResponse()).getJSONObject("response").getJSONArray("p2p_methods").get(0);
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+",\n" +
                "\"p2p_method_id\" : \""+json.getString("id")+"\"\n" +
                "}";
        p2Pay.get_recipient_card(body);
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
                "        \"confirmation_amount\": \"30377\",\n" +
                "        \"sender_card_number\": \"4149785236985410\",\n" +
                "      \"first_name\": \"sender_surname\",\n" +
                "      \"last_name\": null\n" +
                "\n" +
                "    }\n" +
                "}";
        p2Pay.confirm(body);
        System.out.println("test_confirm_last_name_null"+info(id));
    }

    public void h2h_create_get_methods_list_confirm() throws InterruptedException {
        p2Pay = new P2Pay(P2Pay.P2PayBody.defaultBody(true));
        p2Pay.pay_in();
        id = p2Pay.getId();
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+"\n" +
                "}";
        p2Pay.get_methods_list(body);
        json = (JSONObject) new JSONObject(p2Pay.getResponse()).getJSONObject("response").getJSONArray("p2p_methods").get(0);
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
                "        \"confirmation_amount\": \"30377\",\n" +
                "        \"sender_card_number\": \"4149785236985410\",\n" +
                "      \"first_name\": \"sender_name\",\n" +
                "      \"last_name\": \"sender_surname\"\n" +
                "\n" +
                "    }\n" +
                "}";
        p2Pay.confirm(body);
        System.out.println("h2h_create_get_methods_list_confirm"+info(id));
    }

    public void h2h_create_get_recipient_card_confirm() throws InterruptedException {
        p2Pay = new P2Pay(P2Pay.P2PayBody.defaultBody(true));
        p2Pay.pay_in();
        id = p2Pay.getId();
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
        p2Pay.get_recipient_card(body);
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
                "        \"confirmation_amount\": \"30377\",\n" +
                "        \"sender_card_number\": \"4149785236985410\",\n" +
                "      \"first_name\": \"sender_name\",\n" +
                "      \"last_name\": \"sender_surname\"\n" +
                "\n" +
                "    }\n" +
                "}";
        p2Pay.confirm(body);
        System.out.println("h2h_create_get_recipient_card_confirm"+info(id));
    }

    public void h2h_create_confirm() {
        p2Pay = new P2Pay(P2Pay.P2PayBody.defaultBody(true));
        p2Pay.pay_in();
        id = p2Pay.getId();
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "    \"id\": "+id+",\n" +
                "    \"confirmation_fields\": {\n" +
                "        \"confirmation_amount\": \"30377\",\n" +
                "        \"sender_card_number\": \"4149785236985410\",\n" +
                "      \"first_name\": \"sender_name\",\n" +
                "      \"last_name\": \"sender_surname\"\n" +
                "\n" +
                "    }\n" +
                "}";
        p2Pay.confirm(body);
        System.out.println("h2h_create_confirm"+info(id));
    }

    public void h2h_create_cancel() {
        p2Pay = new P2Pay(P2Pay.P2PayBody.defaultBody(true));
        p2Pay.pay_in();
        id = p2Pay.getId();
        body = "{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "\"id\": "+id+"\n" +
                "}";
        p2Pay.cancel(body);
        System.out.println("h2h_create_cancel"+info(id));
    }



    String info(String idTrs){
        long child = Long.valueOf(idTrs) + 1;
        return "  ->   https://preprod-agora2.backofficeweb.info/transactions/transactions/"+child;
    }
}
