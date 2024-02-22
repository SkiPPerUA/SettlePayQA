package projectSettlePayTests.back.providersTests.AstroPay;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import projectSettlePay.BaseTest;
import projectSettlePay.back.Callback;
import projectSettlePay.back.TransInfoConn;
import projectSettlePay.back.TransInfoCore;
import projectSettlePay.back.providers.AstroPay;
import projectSettlePay.core.Session;
import projectSettlePay.helper.Data;
import projectSettlePay.helper.UuidGenerate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Test
public class AstroPayPayIn extends BaseTest {

    AstroPay astroPay;

    public void positive_payin_APPROVED(){
        astroPay = new AstroPay(AstroPay.AstroPayBody.defaultBody(""));
        astroPay.pay_in();
        makeCallback("APPROVED");
        Assert.assertEquals(new TransInfoConn(Long.valueOf(astroPay.getId())+1).getStatus(),1);
        Assert.assertEquals(new TransInfoCore(Long.valueOf(astroPay.getId())).getStatus(),1);
        Assert.assertEquals(new TransInfoCore(Long.valueOf(astroPay.getId())+1).getStatus(),1);
        Assert.assertTrue(new TransInfoConn(Long.valueOf(astroPay.getId())+1).getProvider_data().contains("deposit_user_id"));
        Assert.assertEquals(new TransInfoConn(Long.valueOf(astroPay.getId())+1).getAmount(),1000);
        Assert.assertEquals(new TransInfoCore(Long.valueOf(astroPay.getId())).getAmount(),10.00);
        Assert.assertEquals(new TransInfoCore(Long.valueOf(astroPay.getId())+1).getAmount(),10.00);
        System.out.print("positive_payin_APPROVED ");
        showAgoraURL(astroPay.getId());
    }

    public void positive_payin_PENDING(){
        astroPay = new AstroPay(AstroPay.AstroPayBody.defaultBody(""));
        astroPay.pay_in();
        makeCallback("PENDING");
        Assert.assertEquals(new TransInfoConn(Long.valueOf(astroPay.getId())+1).getStatus(),10);
        Assert.assertEquals(new TransInfoCore(Long.valueOf(astroPay.getId())).getStatus(),10);
        Assert.assertEquals(new TransInfoCore(Long.valueOf(astroPay.getId())+1).getStatus(),10);
        System.out.print("positive_payin_PENDING ");
        showAgoraURL(astroPay.getId());
    }

    public void positive_payin_CANCELED(){
        astroPay = new AstroPay(AstroPay.AstroPayBody.defaultBody(""));
        astroPay.pay_in();
        makeCallback("CANCELED");
        Assert.assertEquals(new TransInfoConn(Long.valueOf(astroPay.getId())+1).getStatus(),10);
        Assert.assertEquals(new TransInfoCore(Long.valueOf(astroPay.getId())).getStatus(),10);
        Assert.assertEquals(new TransInfoCore(Long.valueOf(astroPay.getId())+1).getStatus(),10);
        System.out.print("positive_payin_CANCELED ");
        showAgoraURL(astroPay.getId());
    }

    public void positive_payin_APPROVED_after_CANCELED(){
        astroPay = new AstroPay(AstroPay.AstroPayBody.defaultBody(""));
        astroPay.pay_in();
        makeCallback("CANCELED");
        Assert.assertEquals(new TransInfoConn(Long.valueOf(astroPay.getId())+1).getStatus(),10);
        Assert.assertEquals(new TransInfoCore(Long.valueOf(astroPay.getId())).getStatus(),10);
        Assert.assertEquals(new TransInfoCore(Long.valueOf(astroPay.getId())+1).getStatus(),10);
        makeCallback("APPROVED");
        Assert.assertEquals(new TransInfoConn(Long.valueOf(astroPay.getId())+1).getStatus(),1);
        Assert.assertEquals(new TransInfoCore(Long.valueOf(astroPay.getId())).getStatus(),1);
        Assert.assertEquals(new TransInfoCore(Long.valueOf(astroPay.getId())+1).getStatus(),1);
        System.out.print("positive_payin_APPROVED_after_CANCELED ");
        showAgoraURL(astroPay.getId());
    }

    public void test_sum_MIN(){
        astroPay = new AstroPay("{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2759,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"e5a095b16b8a2106380686b23c5570ac\"\n" +
                "    },\n" +
                "    \"locale\": \"ua\",\n" +
                "    \"external_order_id\": \"1234567890\",\n" +
                "    \"external_customer_id\": \"321\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"account_id\": 2765,\n" +
                "    \"wallet_id\": 3554,\n" +
                "    \"service_id\": 4217,\n" +
                "    \"amount\": 1,\n" +
                "    \"amount_currency\": \"USD\",\n" +
                "    \"fields\": {\n" +
                "        \"country\": \"BR\"\n" +
                "    },\n" +
                "    \"point\": {\n" +
                "        \"redirect_url\": \"https://www.google.com/\"\n" +
                "    },\n" +
                "    \"description\": \"Test deposit\"\n" +
                "    }");
        astroPay.pay_in();
        Assert.assertTrue(astroPay.getResponse().contains("Check minimum amount"));
        System.out.print("test_sum_MIN ");
    }

    public void test_sum_ZERO(){
        astroPay = new AstroPay("{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2759,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"e5a095b16b8a2106380686b23c5570ac\"\n" +
                "    },\n" +
                "    \"locale\": \"ua\",\n" +
                "    \"external_order_id\": \"1234567890\",\n" +
                "    \"external_customer_id\": \"321\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"account_id\": 2765,\n" +
                "    \"wallet_id\": 3554,\n" +
                "    \"service_id\": 4217,\n" +
                "    \"amount\": 0,\n" +
                "    \"amount_currency\": \"USD\",\n" +
                "    \"fields\": {\n" +
                "        \"country\": \"BR\"\n" +
                "    },\n" +
                "    \"point\": {\n" +
                "        \"redirect_url\": \"https://www.google.com/\"\n" +
                "    },\n" +
                "    \"description\": \"Test deposit\"\n" +
                "    }");
        astroPay.pay_in();
        Assert.assertTrue(astroPay.getResponse().contains("Check minimum amount"));
        System.out.print("test_sum_ZERO ");
    }

    public void test_sum_with_cents(){
        astroPay = new AstroPay("{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2759,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"e5a095b16b8a2106380686b23c5570ac\"\n" +
                "    },\n" +
                "    \"locale\": \"ua\",\n" +
                "    \"external_order_id\": \"1234567890\",\n" +
                "    \"external_customer_id\": \"321\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"account_id\": 2765,\n" +
                "    \"wallet_id\": 3554,\n" +
                "    \"service_id\": 4217,\n" +
                "    \"amount\": 1001,\n" +
                "    \"amount_currency\": \"USD\",\n" +
                "    \"fields\": {\n" +
                "        \"country\": \"BR\"\n" +
                "    },\n" +
                "    \"point\": {\n" +
                "        \"redirect_url\": \"https://www.google.com/\"\n" +
                "    },\n" +
                "    \"description\": \"Test deposit\"\n" +
                "    }");
        astroPay.pay_in();
        makeCallback("APPROVED");
        Assert.assertEquals(new TransInfoConn(Long.valueOf(astroPay.getId())+1).getStatus(),1);
        Assert.assertEquals(new TransInfoCore(Long.valueOf(astroPay.getId())).getStatus(),1);
        Assert.assertEquals(new TransInfoCore(Long.valueOf(astroPay.getId())+1).getStatus(),1);
        Assert.assertTrue(new TransInfoConn(Long.valueOf(astroPay.getId())+1).getProvider_data().contains("deposit_user_id"));
        Assert.assertEquals(new TransInfoConn(Long.valueOf(astroPay.getId())+1).getAmount(),1001);
        System.out.print("test_sum_ZERO ");
        showAgoraURL(astroPay.getId());
    }

    public void test_sum_MAX(){
        astroPay = new AstroPay("{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2759,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"e5a095b16b8a2106380686b23c5570ac\"\n" +
                "    },\n" +
                "    \"locale\": \"ua\",\n" +
                "    \"external_order_id\": \"1234567890\",\n" +
                "    \"external_customer_id\": \"321\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"account_id\": 2765,\n" +
                "    \"wallet_id\": 3554,\n" +
                "    \"service_id\": 4217,\n" +
                "    \"amount\": 1000001,\n" +
                "    \"amount_currency\": \"USD\",\n" +
                "    \"fields\": {\n" +
                "        \"country\": \"BR\"\n" +
                "    },\n" +
                "    \"point\": {\n" +
                "        \"redirect_url\": \"https://www.google.com/\"\n" +
                "    },\n" +
                "    \"description\": \"Test deposit\"\n" +
                "    }");
        astroPay.pay_in();
        Assert.assertTrue(astroPay.getResponse().contains("Check maximum amount"));
        System.out.print("test_sum_MAX ");
    }

    public void negative_without_country(){
        astroPay = new AstroPay("{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2759,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"e5a095b16b8a2106380686b23c5570ac\"\n" +
                "    },\n" +
                "    \"locale\": \"ua\",\n" +
                "    \"external_order_id\": \"1234567890\",\n" +
                "    \"external_customer_id\": \"321\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"account_id\": 2765,\n" +
                "    \"wallet_id\": 3554,\n" +
                "    \"service_id\": 4217,\n" +
                "    \"amount\": 1000,\n" +
                "    \"amount_currency\": \"USD\",\n" +
                "    \"fields\": {\n" +
                "    },\n" +
                "    \"point\": {\n" +
                "        \"redirect_url\": \"https://www.google.com/\"\n" +
                "    },\n" +
                "    \"description\": \"Test deposit\"\n" +
                "    }");
        astroPay.pay_in();
        System.out.print("negative_without_country ");
        showAgoraURL(astroPay.getId());
    }

    public void negative_country_null(){
        astroPay = new AstroPay("{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2759,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"e5a095b16b8a2106380686b23c5570ac\"\n" +
                "    },\n" +
                "    \"locale\": \"ua\",\n" +
                "    \"external_order_id\": \"1234567890\",\n" +
                "    \"external_customer_id\": \"321\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"account_id\": 2765,\n" +
                "    \"wallet_id\": 3554,\n" +
                "    \"service_id\": 4217,\n" +
                "    \"amount\": 1000,\n" +
                "    \"amount_currency\": \"USD\",\n" +
                "    \"fields\": {\n" +
                "        \"country\": null" +
                "    },\n" +
                "    \"point\": {\n" +
                "        \"redirect_url\": \"https://www.google.com/\"\n" +
                "    },\n" +
                "    \"description\": \"Test deposit\"\n" +
                "    }");
        astroPay.pay_in();
        Assert.assertTrue(astroPay.getResponse().contains("@fields[country] = None value type is not str"));
        System.out.print("negative_country_null ");
    }

    public void negative_country_empty(){
        astroPay = new AstroPay("{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2759,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"e5a095b16b8a2106380686b23c5570ac\"\n" +
                "    },\n" +
                "    \"locale\": \"ua\",\n" +
                "    \"external_order_id\": \"1234567890\",\n" +
                "    \"external_customer_id\": \"321\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"account_id\": 2765,\n" +
                "    \"wallet_id\": 3554,\n" +
                "    \"service_id\": 4217,\n" +
                "    \"amount\": 1000,\n" +
                "    \"amount_currency\": \"USD\",\n" +
                "    \"fields\": {\n" +
                "        \"country\": \"\"\n" +
                "    },\n" +
                "    \"point\": {\n" +
                "        \"redirect_url\": \"https://www.google.com/\"\n" +
                "    },\n" +
                "    \"description\": \"Test deposit\"\n" +
                "    }");
        astroPay.pay_in();
        System.out.print("negative_country_empty ");
        showAgoraURL(astroPay.getId());
    }

    public void negative_country_not_correct(){
        astroPay = new AstroPay("{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2759,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"e5a095b16b8a2106380686b23c5570ac\"\n" +
                "    },\n" +
                "    \"locale\": \"ua\",\n" +
                "    \"external_order_id\": \"1234567890\",\n" +
                "    \"external_customer_id\": \"321\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"account_id\": 2765,\n" +
                "    \"wallet_id\": 3554,\n" +
                "    \"service_id\": 4217,\n" +
                "    \"amount\": 1000,\n" +
                "    \"amount_currency\": \"USD\",\n" +
                "    \"fields\": {\n" +
                "        \"country\": \"вавыап!`'\"\n" +
                "    },\n" +
                "    \"point\": {\n" +
                "        \"redirect_url\": \"https://www.google.com/\"\n" +
                "    },\n" +
                "    \"description\": \"Test deposit\"\n" +
                "    }");
        astroPay.pay_in();
        System.out.print("negative_country_not_correct ");
        showAgoraURL(astroPay.getId());
    }
    //@Test(enabled = false)
    public void test_all_currency() throws InterruptedException {
        List<String> curr_name = List.of("USD", "ARS", "BRL", "CLP", "COP", "CRC", "MXN", "PEN", "UYU"); //"USD", "ARS", "BRL", "CLP", "COP", "CRC", "MXN", "PEN", "UYU"
        List<String> country_list = List.of("MX"); //"AR", "BR", "CL", "CO", "CR", "MX", "PE", "UY"
        Map<String, String> currency_wallet = new HashMap<>();
        currency_wallet.put("USD","3554");
        currency_wallet.put("ARS","3231");
        currency_wallet.put("BRL","3016");
        currency_wallet.put("CLP","3349");
        currency_wallet.put("COP","3017");
        currency_wallet.put("CRC","3668");
        currency_wallet.put("MXN","3013");
        currency_wallet.put("PEN","3351");
        currency_wallet.put("UYU","3670");
        String body = "{\n" +
        "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2759,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"e5a095b16b8a2106380686b23c5570ac\"\n" +
                "    },\n" +
                "    \"locale\": \"ua\",\n" +
                "    \"external_order_id\": \""+ UuidGenerate.generateUUID() +"\",\n" +
                "    \"external_customer_id\": \""+ UuidGenerate.generateUUID() +"\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"account_id\": 2765,\n" +
                "    \"wallet_id\": %s,\n" +
                "    \"service_id\": 4217,\n" +
                "    \"amount\": 1000,\n" +
                "    \"amount_currency\": \"%s\",\n" +
                "    \"fields\": {\n" +
                "        \"country\": \"%s\"\n" +
                "    },\n" +
                "    \"point\": {\n" +
                "        \"redirect_url\": \"https://www.google.com/\"\n" +
                "    },\n" +
                "    \"description\": \"Test deposit\"\n" +
                "    }";
        for (String country: country_list) {
            for (String curr : curr_name) {
                astroPay = new AstroPay(String.format(body, currency_wallet.get(curr), curr, country));
                astroPay.pay_in();
                positiveUI();
                System.out.print("currency (" + curr + "), country ("+country+") ");
                showAgoraURL(astroPay.getId());
            }
        }
    }

    public void test_amount_currency_empty(){
        astroPay = new AstroPay("{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2759,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"e5a095b16b8a2106380686b23c5570ac\"\n" +
                "    },\n" +
                "    \"locale\": \"ua\",\n" +
                "    \"external_order_id\": \"1234567890\",\n" +
                "    \"external_customer_id\": \"321\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"account_id\": 2765,\n" +
                "    \"wallet_id\": 3554,\n" +
                "    \"service_id\": 4217,\n" +
                "    \"amount\": 1000,\n" +
                "    \"amount_currency\": \"\",\n" +
                "    \"fields\": {\n" +
                "        \"country\": \"BR\"\n" +
                "    },\n" +
                "    \"point\": {\n" +
                "        \"redirect_url\": \"https://www.google.com/\"\n" +
                "    },\n" +
                "    \"description\": \"Test deposit\"\n" +
                "    }");
        astroPay.pay_in();
        System.out.print("test_amount_currency_empty ");
        showAgoraURL(astroPay.getId());
    }

    public void test_without_amount_currency(){
        astroPay = new AstroPay("{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2759,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"e5a095b16b8a2106380686b23c5570ac\"\n" +
                "    },\n" +
                "    \"locale\": \"ua\",\n" +
                "    \"external_order_id\": \"1234567890\",\n" +
                "    \"external_customer_id\": \"321\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"account_id\": 2765,\n" +
                "    \"wallet_id\": 3554,\n" +
                "    \"service_id\": 4217,\n" +
                "    \"amount\": 1000,\n" +
                "    \"fields\": {\n" +
                "        \"country\": \"BR\"\n" +
                "    },\n" +
                "    \"point\": {\n" +
                "        \"redirect_url\": \"https://www.google.com/\"\n" +
                "    },\n" +
                "    \"description\": \"Test deposit\"\n" +
                "    }");
        astroPay.pay_in();
        System.out.print("test_without_amount_currency ");
        showAgoraURL(astroPay.getId());
    }

    private void makeCallback(String status){
        String bodyCallback = "{\n" +
                "    \"deposit_external_id\": \"jRmiGaLPbE0eI4OoP9X8YkgtiTfewAkhqE1zgQDn\", \n" +
                "    \"merchant_deposit_id\": \"TD-001\",\n" +
                "    \"deposit_user_id\": \"Vx0H4pdAtPCz\",\n" +
                "    \"merchant_user_id\": \"TU-001\",\n" +
                "    \"status\": \"%s\",\n" +
                "    \"end_status_date\": \"2022-04-08T16:17:49\"\n" +
                "}";
        Callback callback = new Callback();
        callback.makeCallback(callback.getResult_url(astroPay),String.format(bodyCallback,status));
    }

    //@Test(enabled = false)
    public void call(){
        String bodyCallback = "{\n" +
                "    \"deposit_external_id\": \"jRmiGaLPbE0eI4OoP9X8YkgtiTfewAkhqE1zgQDn\", \n" +
                "    \"merchant_deposit_id\": \"TD-001\",\n" +
                "    \"deposit_user_id\": \"Vx0H4pdAtPCz\",\n" +
                "    \"merchant_user_id\": \"TU-001\",\n" +
                "    \"status\": \"APPROVED\",\n" +
                "    \"end_status_date\": \"2022-04-08T16:17:49\"\n" +
                "}";
        Callback callback = new Callback();
        callback.makeCallback(callback.getResult_url(astroPay),bodyCallback);
    }

    @AfterTest
    void close(){
        closeCon();
    }

    private void positiveUI(){
        if (astroPay.getPayURL() != null) {
            System.out.print("PayURL - есть -> ");
            try {
                Session.getDriver().get(astroPay.getPayURL());
                astroPay.frame.positiveSteps();
            } finally {
                Session.closeSession();
            }
        }else {
            System.out.print("PayURL - нету -> ");
        }
    }
}
