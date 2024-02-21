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
public class AstroPayPayInPIX extends BaseTest {

    AstroPay astroPay;

    public void positive_payin_APPROVED(){
        astroPay = new AstroPay(AstroPay.AstroPayBody.defaultBody("pix"), true);
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
        astroPay = new AstroPay(AstroPay.AstroPayBody.defaultBody("pix"),true);
        astroPay.pay_in();
        makeCallback("PENDING");
        Assert.assertEquals(new TransInfoConn(Long.valueOf(astroPay.getId())+1).getStatus(),10);
        Assert.assertEquals(new TransInfoCore(Long.valueOf(astroPay.getId())).getStatus(),10);
        Assert.assertEquals(new TransInfoCore(Long.valueOf(astroPay.getId())+1).getStatus(),10);
        System.out.print("positive_payin_PENDING ");
        showAgoraURL(astroPay.getId());
    }

    public void positive_payin_CANCELED(){
        astroPay = new AstroPay(AstroPay.AstroPayBody.defaultBody("pix"),true);
        astroPay.pay_in();
        makeCallback("CANCELED");
        Assert.assertEquals(new TransInfoConn(Long.valueOf(astroPay.getId())+1).getStatus(),10);
        Assert.assertEquals(new TransInfoCore(Long.valueOf(astroPay.getId())).getStatus(),10);
        Assert.assertEquals(new TransInfoCore(Long.valueOf(astroPay.getId())+1).getStatus(),10);
        System.out.print("positive_payin_CANCELED ");
        showAgoraURL(astroPay.getId());
    }

    public void positive_payin_APPROVED_after_CANCELED(){
        astroPay = new AstroPay(AstroPay.AstroPayBody.defaultBody("pix"));
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
                "    \"external_order_id\": \"123456789011\",\n" +
                "    \"external_customer_id\": \"321123\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"account_id\": 2765,\n" +
                "    \"wallet_id\": 3015,\n" +
                "    \"service_id\": 4221,\n" +
                "    \"amount\": 99,\n" +
                "    \"amount_currency\": \"BRL\",\n" +
                "    \"fields\": {\n" +
                "        \"country\": \"BR\",\n" +
                "\t\t\t\t\"email\":\"cadfa@fdas.das\",\n" +
                "\t\t\t\t\"phone\":\"34234242323\",\n" +
                "\t\t\t\t\"itn\":\"26040271272\",\n" +
                "\t\t\t\t\"first_name\":\"name\",\n" +
                "\t\t\t\t\"last_name\":\"last\"\n" +
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
                "    \"external_order_id\": \"123456789011\",\n" +
                "    \"external_customer_id\": \"321123\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"account_id\": 2765,\n" +
                "    \"wallet_id\": 3015,\n" +
                "    \"service_id\": 4221,\n" +
                "    \"amount\": 0,\n" +
                "    \"amount_currency\": \"BRL\",\n" +
                "    \"fields\": {\n" +
                "        \"country\": \"BR\",\n" +
                "\t\t\t\t\"email\":\"cadfa@fdas.das\",\n" +
                "\t\t\t\t\"phone\":\"34234242323\",\n" +
                "\t\t\t\t\"itn\":\"26040271272\",\n" +
                "\t\t\t\t\"first_name\":\"name\",\n" +
                "\t\t\t\t\"last_name\":\"last\"\n" +
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
                "    \"external_order_id\": \"123456789011\",\n" +
                "    \"external_customer_id\": \"321123\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"account_id\": 2765,\n" +
                "    \"wallet_id\": 3015,\n" +
                "    \"service_id\": 4221,\n" +
                "    \"amount\": 1001,\n" +
                "    \"amount_currency\": \"BRL\",\n" +
                "    \"fields\": {\n" +
                "        \"country\": \"BR\",\n" +
                "\t\t\t\t\"email\":\"cadfa@fdas.das\",\n" +
                "\t\t\t\t\"phone\":\"34234242323\",\n" +
                "\t\t\t\t\"itn\":\"26040271272\",\n" +
                "\t\t\t\t\"first_name\":\"name\",\n" +
                "\t\t\t\t\"last_name\":\"last\"\n" +
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
        System.out.print("test_sum_with_cents ");
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
                "    \"external_order_id\": \"123456789011\",\n" +
                "    \"external_customer_id\": \"321123\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"account_id\": 2765,\n" +
                "    \"wallet_id\": 3015,\n" +
                "    \"service_id\": 4221,\n" +
                "    \"amount\": 5000001,\n" +
                "    \"amount_currency\": \"BRL\",\n" +
                "    \"fields\": {\n" +
                "        \"country\": \"BR\",\n" +
                "\t\t\t\t\"email\":\"cadfa@fdas.das\",\n" +
                "\t\t\t\t\"phone\":\"34234242323\",\n" +
                "\t\t\t\t\"itn\":\"26040271272\",\n" +
                "\t\t\t\t\"first_name\":\"name\",\n" +
                "\t\t\t\t\"last_name\":\"last\"\n" +
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
                "    \"external_order_id\": \"123456789011\",\n" +
                "    \"external_customer_id\": \"321123\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"account_id\": 2765,\n" +
                "    \"wallet_id\": 3015,\n" +
                "    \"service_id\": 4221,\n" +
                "    \"amount\": 1000,\n" +
                "    \"amount_currency\": \"BRL\",\n" +
                "    \"fields\": {\n" +
                "\t\t\t\t\"email\":\"cadfa@fdas.das\",\n" +
                "\t\t\t\t\"phone\":\"34234242323\",\n" +
                "\t\t\t\t\"itn\":\"26040271272\",\n" +
                "\t\t\t\t\"first_name\":\"name\",\n" +
                "\t\t\t\t\"last_name\":\"last\"\n" +
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
                "    \"external_order_id\": \"123456789011\",\n" +
                "    \"external_customer_id\": \"321123\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"account_id\": 2765,\n" +
                "    \"wallet_id\": 3015,\n" +
                "    \"service_id\": 4221,\n" +
                "    \"amount\": 1000,\n" +
                "    \"amount_currency\": \"BRL\",\n" +
                "    \"fields\": {\n" +
                "        \"country\": null,\n" +
                "\t\t\t\t\"email\":\"cadfa@fdas.das\",\n" +
                "\t\t\t\t\"phone\":\"34234242323\",\n" +
                "\t\t\t\t\"itn\":\"26040271272\",\n" +
                "\t\t\t\t\"first_name\":\"name\",\n" +
                "\t\t\t\t\"last_name\":\"last\"\n" +
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
                "    \"external_order_id\": \"123456789011\",\n" +
                "    \"external_customer_id\": \"321123\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"account_id\": 2765,\n" +
                "    \"wallet_id\": 3015,\n" +
                "    \"service_id\": 4221,\n" +
                "    \"amount\": 1000,\n" +
                "    \"amount_currency\": \"BRL\",\n" +
                "    \"fields\": {\n" +
                "        \"country\": \"\",\n" +
                "\t\t\t\t\"email\":\"cadfa@fdas.das\",\n" +
                "\t\t\t\t\"phone\":\"34234242323\",\n" +
                "\t\t\t\t\"itn\":\"26040271272\",\n" +
                "\t\t\t\t\"first_name\":\"name\",\n" +
                "\t\t\t\t\"last_name\":\"last\"\n" +
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

    public void negative_country_blocked(){
        astroPay = new AstroPay("{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2759,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"e5a095b16b8a2106380686b23c5570ac\"\n" +
                "    },\n" +
                "    \"locale\": \"ua\",\n" +
                "    \"external_order_id\": \"123456789011\",\n" +
                "    \"external_customer_id\": \"321123\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"account_id\": 2765,\n" +
                "    \"wallet_id\": 3015,\n" +
                "    \"service_id\": 4221,\n" +
                "    \"amount\": 1000,\n" +
                "    \"amount_currency\": \"BRL\",\n" +
                "    \"fields\": {\n" +
                "        \"country\": \"AF\",\n" +
                "\t\t\t\t\"email\":\"cadfa@fdas.das\",\n" +
                "\t\t\t\t\"phone\":\"34234242323\",\n" +
                "\t\t\t\t\"itn\":\"26040271272\",\n" +
                "\t\t\t\t\"first_name\":\"name\",\n" +
                "\t\t\t\t\"last_name\":\"last\"\n" +
                "    },\n" +
                "    \"point\": {\n" +
                "        \"redirect_url\": \"https://www.google.com/\"\n" +
                "    },\n" +
                "    \"description\": \"Test deposit\"\n" +
                "    }");
        astroPay.pay_in();
        System.out.print("negative_country_blocked ");
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
                "    \"external_order_id\": \"123456789011\",\n" +
                "    \"external_customer_id\": \"321123\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"account_id\": 2765,\n" +
                "    \"wallet_id\": 3015,\n" +
                "    \"service_id\": 4221,\n" +
                "    \"amount\": 1000,\n" +
                "    \"amount_currency\": \"BRL\",\n" +
                "    \"fields\": {\n" +
                "        \"country\": \"UKR\",\n" +
                "\t\t\t\t\"email\":\"cadfa@fdas.das\",\n" +
                "\t\t\t\t\"phone\":\"34234242323\",\n" +
                "\t\t\t\t\"itn\":\"26040271272\",\n" +
                "\t\t\t\t\"first_name\":\"name\",\n" +
                "\t\t\t\t\"last_name\":\"last\"\n" +
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
        List<String> curr_name = List.of("USD","BRL");
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
                "    \"external_order_id\": \"123456789011\",\n" +
                "    \"external_customer_id\": \"321123\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"account_id\": 2765,\n" +
                "    \"wallet_id\": %s,\n" +
                "    \"service_id\": 4221,\n" +
                "    \"amount\": 1000,\n" +
                "    \"amount_currency\": \"%s\",\n" +
                "    \"fields\": {\n" +
                "        \"country\": \"%s\",\n" +
                "\t\t\t\t\"email\":\"cadfa@fdas.das\",\n" +
                "\t\t\t\t\"phone\":\"34234242323\",\n" +
                "\t\t\t\t\"itn\":\"26040271272\",\n" +
                "\t\t\t\t\"first_name\":\"name\",\n" +
                "\t\t\t\t\"last_name\":\"last\"\n" +
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
                "    \"external_order_id\": \"123456789011\",\n" +
                "    \"external_customer_id\": \"321123\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"account_id\": 2765,\n" +
                "    \"wallet_id\": 3015,\n" +
                "    \"service_id\": 4221,\n" +
                "    \"amount\": 1000,\n" +
                "    \"amount_currency\": \"\",\n" +
                "    \"fields\": {\n" +
                "        \"country\": \"BR\",\n" +
                "\t\t\t\t\"email\":\"cadfa@fdas.das\",\n" +
                "\t\t\t\t\"phone\":\"34234242323\",\n" +
                "\t\t\t\t\"itn\":\"26040271272\",\n" +
                "\t\t\t\t\"first_name\":\"name\",\n" +
                "\t\t\t\t\"last_name\":\"last\"\n" +
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

    public void test_amount_currency_null(){
        astroPay = new AstroPay("{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2759,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"e5a095b16b8a2106380686b23c5570ac\"\n" +
                "    },\n" +
                "    \"locale\": \"ua\",\n" +
                "    \"external_order_id\": \"123456789011\",\n" +
                "    \"external_customer_id\": \"321123\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"account_id\": 2765,\n" +
                "    \"wallet_id\": 3015,\n" +
                "    \"service_id\": 4221,\n" +
                "    \"amount\": 1000,\n" +
                "    \"amount_currency\": null,\n" +
                "    \"fields\": {\n" +
                "        \"country\": \"BR\",\n" +
                "\t\t\t\t\"email\":\"cadfa@fdas.das\",\n" +
                "\t\t\t\t\"phone\":\"34234242323\",\n" +
                "\t\t\t\t\"itn\":\"26040271272\",\n" +
                "\t\t\t\t\"first_name\":\"name\",\n" +
                "\t\t\t\t\"last_name\":\"last\"\n" +
                "    },\n" +
                "    \"point\": {\n" +
                "        \"redirect_url\": \"https://www.google.com/\"\n" +
                "    },\n" +
                "    \"description\": \"Test deposit\"\n" +
                "    }");
        astroPay.pay_in();
        System.out.print("test_amount_currency_null ");
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
                "    \"external_order_id\": \"123456789011\",\n" +
                "    \"external_customer_id\": \"321123\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"account_id\": 2765,\n" +
                "    \"wallet_id\": 3015,\n" +
                "    \"service_id\": 4221,\n" +
                "    \"amount\": 1000,\n" +
                "    \"fields\": {\n" +
                "        \"country\": \"BR\",\n" +
                "\t\t\t\t\"email\":\"cadfa@fdas.das\",\n" +
                "\t\t\t\t\"phone\":\"34234242323\",\n" +
                "\t\t\t\t\"itn\":\"26040271272\",\n" +
                "\t\t\t\t\"first_name\":\"name\",\n" +
                "\t\t\t\t\"last_name\":\"last\"\n" +
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

    public void test_amount_null(){
        astroPay = new AstroPay("{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2759,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"e5a095b16b8a2106380686b23c5570ac\"\n" +
                "    },\n" +
                "    \"locale\": \"ua\",\n" +
                "    \"external_order_id\": \"123456789011\",\n" +
                "    \"external_customer_id\": \"321123\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"account_id\": 2765,\n" +
                "    \"wallet_id\": 3015,\n" +
                "    \"service_id\": 4221,\n" +
                "    \"amount\": null,\n" +
                "    \"amount_currency\": \"BRL\",\n" +
                "    \"fields\": {\n" +
                "        \"country\": \"BR\",\n" +
                "\t\t\t\t\"email\":\"cadfa@fdas.das\",\n" +
                "\t\t\t\t\"phone\":\"34234242323\",\n" +
                "\t\t\t\t\"itn\":\"26040271272\",\n" +
                "\t\t\t\t\"first_name\":\"name\",\n" +
                "\t\t\t\t\"last_name\":\"last\"\n" +
                "    },\n" +
                "    \"point\": {\n" +
                "        \"redirect_url\": \"https://www.google.com/\"\n" +
                "    },\n" +
                "    \"description\": \"Test deposit\"\n" +
                "    }");
        astroPay.pay_in();
        System.out.print("test_amount_null ");
        showAgoraURL(astroPay.getId());
    }

    public void test_without_amount(){
        astroPay = new AstroPay("{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2759,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"e5a095b16b8a2106380686b23c5570ac\"\n" +
                "    },\n" +
                "    \"locale\": \"ua\",\n" +
                "    \"external_order_id\": \"123456789011\",\n" +
                "    \"external_customer_id\": \"321123\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"account_id\": 2765,\n" +
                "    \"wallet_id\": 3015,\n" +
                "    \"service_id\": 4221,\n" +
                "    \"amount_currency\": \"BRL\",\n" +
                "    \"fields\": {\n" +
                "        \"country\": \"BR\",\n" +
                "\t\t\t\t\"email\":\"cadfa@fdas.das\",\n" +
                "\t\t\t\t\"phone\":\"34234242323\",\n" +
                "\t\t\t\t\"itn\":\"26040271272\",\n" +
                "\t\t\t\t\"first_name\":\"name\",\n" +
                "\t\t\t\t\"last_name\":\"last\"\n" +
                "    },\n" +
                "    \"point\": {\n" +
                "        \"redirect_url\": \"https://www.google.com/\"\n" +
                "    },\n" +
                "    \"description\": \"Test deposit\"\n" +
                "    }");
        astroPay.pay_in();
        System.out.print("test_without_amount ");
        showAgoraURL(astroPay.getId());
    }

    public void test_email_empty(){
        astroPay = new AstroPay("{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2759,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"e5a095b16b8a2106380686b23c5570ac\"\n" +
                "    },\n" +
                "    \"locale\": \"ua\",\n" +
                "    \"external_order_id\": \"123456789011\",\n" +
                "    \"external_customer_id\": \"321123\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"account_id\": 2765,\n" +
                "    \"wallet_id\": 3015,\n" +
                "    \"service_id\": 4221,\n" +
                "    \"amount\": 1000,\n" +
                "    \"amount_currency\": \"BRL\",\n" +
                "    \"fields\": {\n" +
                "        \"country\": \"BR\",\n" +
                "\t\t\t\t\"email\":\"\",\n" +
                "\t\t\t\t\"phone\":\"34234242323\",\n" +
                "\t\t\t\t\"itn\":\"26040271272\",\n" +
                "\t\t\t\t\"first_name\":\"name\",\n" +
                "\t\t\t\t\"last_name\":\"last\"\n" +
                "    },\n" +
                "    \"point\": {\n" +
                "        \"redirect_url\": \"https://www.google.com/\"\n" +
                "    },\n" +
                "    \"description\": \"Test deposit\"\n" +
                "    }");
        astroPay.pay_in();
        System.out.print("test_email_empty ");
        showAgoraURL(astroPay.getId());
    }

    public void test_email_null(){
        astroPay = new AstroPay("{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2759,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"e5a095b16b8a2106380686b23c5570ac\"\n" +
                "    },\n" +
                "    \"locale\": \"ua\",\n" +
                "    \"external_order_id\": \"123456789011\",\n" +
                "    \"external_customer_id\": \"321123\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"account_id\": 2765,\n" +
                "    \"wallet_id\": 3015,\n" +
                "    \"service_id\": 4221,\n" +
                "    \"amount\": 1000,\n" +
                "    \"amount_currency\": \"BRL\",\n" +
                "    \"fields\": {\n" +
                "        \"country\": \"BR\",\n" +
                "\t\t\t\t\"email\":null,\n" +
                "\t\t\t\t\"phone\":\"34234242323\",\n" +
                "\t\t\t\t\"itn\":\"26040271272\",\n" +
                "\t\t\t\t\"first_name\":\"name\",\n" +
                "\t\t\t\t\"last_name\":\"last\"\n" +
                "    },\n" +
                "    \"point\": {\n" +
                "        \"redirect_url\": \"https://www.google.com/\"\n" +
                "    },\n" +
                "    \"description\": \"Test deposit\"\n" +
                "    }");
        astroPay.pay_in();
        System.out.print("test_email_null ");
        showAgoraURL(astroPay.getId());
    }

    public void test_email_notCorrect(){
        astroPay = new AstroPay("{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2759,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"e5a095b16b8a2106380686b23c5570ac\"\n" +
                "    },\n" +
                "    \"locale\": \"ua\",\n" +
                "    \"external_order_id\": \"123456789011\",\n" +
                "    \"external_customer_id\": \"321123\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"account_id\": 2765,\n" +
                "    \"wallet_id\": 3015,\n" +
                "    \"service_id\": 4221,\n" +
                "    \"amount\": 1000,\n" +
                "    \"amount_currency\": \"BRL\",\n" +
                "    \"fields\": {\n" +
                "        \"country\": \"BR\",\n" +
                "\t\t\t\t\"email\":\"cadfafdasdas\",\n" +
                "\t\t\t\t\"phone\":\"34234242323\",\n" +
                "\t\t\t\t\"itn\":\"26040271272\",\n" +
                "\t\t\t\t\"first_name\":\"name\",\n" +
                "\t\t\t\t\"last_name\":\"last\"\n" +
                "    },\n" +
                "    \"point\": {\n" +
                "        \"redirect_url\": \"https://www.google.com/\"\n" +
                "    },\n" +
                "    \"description\": \"Test deposit\"\n" +
                "    }");
        astroPay.pay_in();
        System.out.print("test_email_notCorrect ");
        showAgoraURL(astroPay.getId());
    }

    public void test_without_email(){
        astroPay = new AstroPay("{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2759,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"e5a095b16b8a2106380686b23c5570ac\"\n" +
                "    },\n" +
                "    \"locale\": \"ua\",\n" +
                "    \"external_order_id\": \"123456789011\",\n" +
                "    \"external_customer_id\": \"321123\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"account_id\": 2765,\n" +
                "    \"wallet_id\": 3015,\n" +
                "    \"service_id\": 4221,\n" +
                "    \"amount\": 1000,\n" +
                "    \"amount_currency\": \"BRL\",\n" +
                "    \"fields\": {\n" +
                "        \"country\": \"BR\",\n" +
                "\t\t\t\t\"phone\":\"34234242323\",\n" +
                "\t\t\t\t\"itn\":\"26040271272\",\n" +
                "\t\t\t\t\"first_name\":\"name\",\n" +
                "\t\t\t\t\"last_name\":\"last\"\n" +
                "    },\n" +
                "    \"point\": {\n" +
                "        \"redirect_url\": \"https://www.google.com/\"\n" +
                "    },\n" +
                "    \"description\": \"Test deposit\"\n" +
                "    }");
        astroPay.pay_in();
        System.out.print("test_without_email ");
        showAgoraURL(astroPay.getId());
    }

    public void test_description_empty(){
        astroPay = new AstroPay("{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2759,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"e5a095b16b8a2106380686b23c5570ac\"\n" +
                "    },\n" +
                "    \"locale\": \"ua\",\n" +
                "    \"external_order_id\": \"123456789011\",\n" +
                "    \"external_customer_id\": \"321123\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"account_id\": 2765,\n" +
                "    \"wallet_id\": 3015,\n" +
                "    \"service_id\": 4221,\n" +
                "    \"amount\": 1000,\n" +
                "    \"amount_currency\": \"BRL\",\n" +
                "    \"fields\": {\n" +
                "        \"country\": \"BR\",\n" +
                "\t\t\t\t\"email\":\"\",\n" +
                "\t\t\t\t\"phone\":\"34234242323\",\n" +
                "\t\t\t\t\"itn\":\"26040271272\",\n" +
                "\t\t\t\t\"first_name\":\"name\",\n" +
                "\t\t\t\t\"last_name\":\"last\"\n" +
                "    },\n" +
                "    \"point\": {\n" +
                "        \"redirect_url\": \"https://www.google.com/\"\n" +
                "    },\n" +
                "    \"description\": \"\"\n" +
                "    }");
        astroPay.pay_in();
        System.out.print("test_description_empty ");
        showAgoraURL(astroPay.getId());
    }

    public void test_description_null(){
        astroPay = new AstroPay("{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2759,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"e5a095b16b8a2106380686b23c5570ac\"\n" +
                "    },\n" +
                "    \"locale\": \"ua\",\n" +
                "    \"external_order_id\": \"123456789011\",\n" +
                "    \"external_customer_id\": \"321123\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"account_id\": 2765,\n" +
                "    \"wallet_id\": 3015,\n" +
                "    \"service_id\": 4221,\n" +
                "    \"amount\": 1000,\n" +
                "    \"amount_currency\": \"BRL\",\n" +
                "    \"fields\": {\n" +
                "        \"country\": \"BR\",\n" +
                "\t\t\t\t\"email\":null,\n" +
                "\t\t\t\t\"phone\":\"34234242323\",\n" +
                "\t\t\t\t\"itn\":\"26040271272\",\n" +
                "\t\t\t\t\"first_name\":\"name\",\n" +
                "\t\t\t\t\"last_name\":\"last\"\n" +
                "    },\n" +
                "    \"point\": {\n" +
                "        \"redirect_url\": \"https://www.google.com/\"\n" +
                "    },\n" +
                "    \"description\": null\n" +
                "    }");
        astroPay.pay_in();
        System.out.print("test_description_null ");
        showAgoraURL(astroPay.getId());
    }

    public void test_description_rusSymbol(){
        astroPay = new AstroPay("{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2759,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"e5a095b16b8a2106380686b23c5570ac\"\n" +
                "    },\n" +
                "    \"locale\": \"ua\",\n" +
                "    \"external_order_id\": \"123456789011\",\n" +
                "    \"external_customer_id\": \"321123\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"account_id\": 2765,\n" +
                "    \"wallet_id\": 3015,\n" +
                "    \"service_id\": 4221,\n" +
                "    \"amount\": 1000,\n" +
                "    \"amount_currency\": \"BRL\",\n" +
                "    \"fields\": {\n" +
                "        \"country\": \"BR\",\n" +
                "\t\t\t\t\"email\":\"cadfafdasdas\",\n" +
                "\t\t\t\t\"phone\":\"34234242323\",\n" +
                "\t\t\t\t\"itn\":\"26040271272\",\n" +
                "\t\t\t\t\"first_name\":\"name\",\n" +
                "\t\t\t\t\"last_name\":\"last\"\n" +
                "    },\n" +
                "    \"point\": {\n" +
                "        \"redirect_url\": \"https://www.google.com/\"\n" +
                "    },\n" +
                "    \"description\": \"вуффс\"\n" +
                "    }");
        astroPay.pay_in();
        System.out.print("test_description_rusSymbol ");
        showAgoraURL(astroPay.getId());
    }

    public void test_without_description(){
        astroPay = new AstroPay("{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2759,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"e5a095b16b8a2106380686b23c5570ac\"\n" +
                "    },\n" +
                "    \"locale\": \"ua\",\n" +
                "    \"external_order_id\": \"123456789011\",\n" +
                "    \"external_customer_id\": \"321123\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"account_id\": 2765,\n" +
                "    \"wallet_id\": 3015,\n" +
                "    \"service_id\": 4221,\n" +
                "    \"amount\": 1000,\n" +
                "    \"amount_currency\": \"BRL\",\n" +
                "    \"fields\": {\n" +
                "        \"country\": \"BR\",\n" +
                "\t\t\t\t\"phone\":\"34234242323\",\n" +
                "\t\t\t\t\"itn\":\"26040271272\",\n" +
                "\t\t\t\t\"first_name\":\"name\",\n" +
                "\t\t\t\t\"last_name\":\"last\"\n" +
                "    },\n" +
                "    \"point\": {\n" +
                "        \"redirect_url\": \"https://www.google.com/\"\n" +
                "    }\n" +
                "    }");
        astroPay.pay_in();
        System.out.print("test_without_description ");
        showAgoraURL(astroPay.getId());
    }

    public void test_phone_empty(){
        astroPay = new AstroPay("{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2759,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"e5a095b16b8a2106380686b23c5570ac\"\n" +
                "    },\n" +
                "    \"locale\": \"ua\",\n" +
                "    \"external_order_id\": \"123456789011\",\n" +
                "    \"external_customer_id\": \"321123\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"account_id\": 2765,\n" +
                "    \"wallet_id\": 3015,\n" +
                "    \"service_id\": 4221,\n" +
                "    \"amount\": 1000,\n" +
                "    \"amount_currency\": \"BRL\",\n" +
                "    \"fields\": {\n" +
                "        \"country\": \"BR\",\n" +
                "\t\t\t\t\"email\":\"cadfa@fdas.das\",\n" +
                "\t\t\t\t\"phone\":\"\",\n" +
                "\t\t\t\t\"itn\":\"26040271272\",\n" +
                "\t\t\t\t\"first_name\":\"name\",\n" +
                "\t\t\t\t\"last_name\":\"last\"\n" +
                "    },\n" +
                "    \"point\": {\n" +
                "        \"redirect_url\": \"https://www.google.com/\"\n" +
                "    },\n" +
                "    \"description\": \"Test deposit\"\n" +
                "    }");
        astroPay.pay_in();
        System.out.print("test_phone_empty ");
        showAgoraURL(astroPay.getId());
    }

    public void test_phone_null(){
        astroPay = new AstroPay("{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2759,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"e5a095b16b8a2106380686b23c5570ac\"\n" +
                "    },\n" +
                "    \"locale\": \"ua\",\n" +
                "    \"external_order_id\": \"123456789011\",\n" +
                "    \"external_customer_id\": \"321123\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"account_id\": 2765,\n" +
                "    \"wallet_id\": 3015,\n" +
                "    \"service_id\": 4221,\n" +
                "    \"amount\": 1000,\n" +
                "    \"amount_currency\": \"BRL\",\n" +
                "    \"fields\": {\n" +
                "        \"country\": \"BR\",\n" +
                "\t\t\t\t\"email\":\"cadfa@fdas.das\",\n" +
                "\t\t\t\t\"phone\":null,\n" +
                "\t\t\t\t\"itn\":\"26040271272\",\n" +
                "\t\t\t\t\"first_name\":\"name\",\n" +
                "\t\t\t\t\"last_name\":\"last\"\n" +
                "    },\n" +
                "    \"point\": {\n" +
                "        \"redirect_url\": \"https://www.google.com/\"\n" +
                "    },\n" +
                "    \"description\": \"Test deposit\"\n" +
                "    }");
        astroPay.pay_in();
        System.out.print("test_phone_null ");
        showAgoraURL(astroPay.getId());
    }

    public void test_phone_notCorrect(){
        astroPay = new AstroPay("{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2759,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"e5a095b16b8a2106380686b23c5570ac\"\n" +
                "    },\n" +
                "    \"locale\": \"ua\",\n" +
                "    \"external_order_id\": \"123456789011\",\n" +
                "    \"external_customer_id\": \"321123\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"account_id\": 2765,\n" +
                "    \"wallet_id\": 3015,\n" +
                "    \"service_id\": 4221,\n" +
                "    \"amount\": 1000,\n" +
                "    \"amount_currency\": \"BRL\",\n" +
                "    \"fields\": {\n" +
                "        \"country\": \"BR\",\n" +
                "\t\t\t\t\"email\":\"cadfa@fdas.das\",\n" +
                "\t\t\t\t\"phone\":\"33\",\n" +
                "\t\t\t\t\"itn\":\"26040271272\",\n" +
                "\t\t\t\t\"first_name\":\"name\",\n" +
                "\t\t\t\t\"last_name\":\"last\"\n" +
                "    },\n" +
                "    \"point\": {\n" +
                "        \"redirect_url\": \"https://www.google.com/\"\n" +
                "    },\n" +
                "    \"description\": \"Test deposit\"\n" +
                "    }");
        astroPay.pay_in();
        System.out.print("test_phone_notCorrect ");
        showAgoraURL(astroPay.getId());
    }

    public void test_phone_words(){
        astroPay = new AstroPay("{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2759,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"e5a095b16b8a2106380686b23c5570ac\"\n" +
                "    },\n" +
                "    \"locale\": \"ua\",\n" +
                "    \"external_order_id\": \"123456789011\",\n" +
                "    \"external_customer_id\": \"321123\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"account_id\": 2765,\n" +
                "    \"wallet_id\": 3015,\n" +
                "    \"service_id\": 4221,\n" +
                "    \"amount\": 1000,\n" +
                "    \"amount_currency\": \"BRL\",\n" +
                "    \"fields\": {\n" +
                "        \"country\": \"BR\",\n" +
                "\t\t\t\t\"email\":\"cadfa@fdas.das\",\n" +
                "\t\t\t\t\"phone\":\"fdfsdfsd\",\n" +
                "\t\t\t\t\"itn\":\"26040271272\",\n" +
                "\t\t\t\t\"first_name\":\"name\",\n" +
                "\t\t\t\t\"last_name\":\"last\"\n" +
                "    },\n" +
                "    \"point\": {\n" +
                "        \"redirect_url\": \"https://www.google.com/\"\n" +
                "    },\n" +
                "    \"description\": \"Test deposit\"\n" +
                "    }");
        astroPay.pay_in();
        System.out.print("test_phone_words ");
        showAgoraURL(astroPay.getId());
    }

    public void test_without_phone(){
        astroPay = new AstroPay("{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2759,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"e5a095b16b8a2106380686b23c5570ac\"\n" +
                "    },\n" +
                "    \"locale\": \"ua\",\n" +
                "    \"external_order_id\": \"123456789011\",\n" +
                "    \"external_customer_id\": \"321123\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"account_id\": 2765,\n" +
                "    \"wallet_id\": 3015,\n" +
                "    \"service_id\": 4221,\n" +
                "    \"amount\": 1000,\n" +
                "    \"amount_currency\": \"BRL\",\n" +
                "    \"fields\": {\n" +
                "        \"country\": \"BR\",\n" +
                "\t\t\t\t\"email\":\"cadfa@fdas.das\",\n" +
                "\t\t\t\t\"itn\":\"26040271272\",\n" +
                "\t\t\t\t\"first_name\":\"name\",\n" +
                "\t\t\t\t\"last_name\":\"last\"\n" +
                "    },\n" +
                "    \"point\": {\n" +
                "        \"redirect_url\": \"https://www.google.com/\"\n" +
                "    },\n" +
                "    \"description\": \"Test deposit\"\n" +
                "    }");
        astroPay.pay_in();
        System.out.print("test_without_phone ");
        showAgoraURL(astroPay.getId());
    }

    public void test_itn_empty(){
        astroPay = new AstroPay("{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2759,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"e5a095b16b8a2106380686b23c5570ac\"\n" +
                "    },\n" +
                "    \"locale\": \"ua\",\n" +
                "    \"external_order_id\": \"123456789011\",\n" +
                "    \"external_customer_id\": \"321123\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"account_id\": 2765,\n" +
                "    \"wallet_id\": 3015,\n" +
                "    \"service_id\": 4221,\n" +
                "    \"amount\": 1000,\n" +
                "    \"amount_currency\": \"BRL\",\n" +
                "    \"fields\": {\n" +
                "        \"country\": \"BR\",\n" +
                "\t\t\t\t\"email\":\"cadfa@fdas.das\",\n" +
                "\t\t\t\t\"phone\":\"34234242323\",\n" +
                "\t\t\t\t\"itn\":\"\",\n" +
                "\t\t\t\t\"first_name\":\"name\",\n" +
                "\t\t\t\t\"last_name\":\"last\"\n" +
                "    },\n" +
                "    \"point\": {\n" +
                "        \"redirect_url\": \"https://www.google.com/\"\n" +
                "    },\n" +
                "    \"description\": \"Test deposit\"\n" +
                "    }");
        astroPay.pay_in();
        System.out.print("test_itn_empty ");
        showAgoraURL(astroPay.getId());
    }

    public void test_itn_more_than_11(){
        astroPay = new AstroPay("{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2759,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"e5a095b16b8a2106380686b23c5570ac\"\n" +
                "    },\n" +
                "    \"locale\": \"ua\",\n" +
                "    \"external_order_id\": \"123456789011\",\n" +
                "    \"external_customer_id\": \"321123\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"account_id\": 2765,\n" +
                "    \"wallet_id\": 3015,\n" +
                "    \"service_id\": 4221,\n" +
                "    \"amount\": 1000,\n" +
                "    \"amount_currency\": \"BRL\",\n" +
                "    \"fields\": {\n" +
                "        \"country\": \"BR\",\n" +
                "\t\t\t\t\"email\":\"cadfa@fdas.das\",\n" +
                "\t\t\t\t\"phone\":\"34234242323\",\n" +
                "\t\t\t\t\"itn\":\"260402712721\",\n" +
                "\t\t\t\t\"first_name\":\"name\",\n" +
                "\t\t\t\t\"last_name\":\"last\"\n" +
                "    },\n" +
                "    \"point\": {\n" +
                "        \"redirect_url\": \"https://www.google.com/\"\n" +
                "    },\n" +
                "    \"description\": \"Test deposit\"\n" +
                "    }");
        astroPay.pay_in();
        System.out.print("test_itn_empty ");
        showAgoraURL(astroPay.getId());
    }

    public void test_itn_null(){
        astroPay = new AstroPay("{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2759,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"e5a095b16b8a2106380686b23c5570ac\"\n" +
                "    },\n" +
                "    \"locale\": \"ua\",\n" +
                "    \"external_order_id\": \"123456789011\",\n" +
                "    \"external_customer_id\": \"321123\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"account_id\": 2765,\n" +
                "    \"wallet_id\": 3015,\n" +
                "    \"service_id\": 4221,\n" +
                "    \"amount\": 1000,\n" +
                "    \"amount_currency\": \"BRL\",\n" +
                "    \"fields\": {\n" +
                "        \"country\": \"BR\",\n" +
                "\t\t\t\t\"email\":\"cadfa@fdas.das\",\n" +
                "\t\t\t\t\"phone\":\"34234242323\",\n" +
                "\t\t\t\t\"itn\":\"null\",\n" +
                "\t\t\t\t\"first_name\":\"name\",\n" +
                "\t\t\t\t\"last_name\":\"last\"\n" +
                "    },\n" +
                "    \"point\": {\n" +
                "        \"redirect_url\": \"https://www.google.com/\"\n" +
                "    },\n" +
                "    \"description\": \"Test deposit\"\n" +
                "    }");
        astroPay.pay_in();
        System.out.print("test_itn_null ");
        showAgoraURL(astroPay.getId());
    }

    public void test_itn_notCorrect(){
        astroPay = new AstroPay("{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2759,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"e5a095b16b8a2106380686b23c5570ac\"\n" +
                "    },\n" +
                "    \"locale\": \"ua\",\n" +
                "    \"external_order_id\": \"123456789011\",\n" +
                "    \"external_customer_id\": \"321123\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"account_id\": 2765,\n" +
                "    \"wallet_id\": 3015,\n" +
                "    \"service_id\": 4221,\n" +
                "    \"amount\": 1000,\n" +
                "    \"amount_currency\": \"BRL\",\n" +
                "    \"fields\": {\n" +
                "        \"country\": \"BR\",\n" +
                "\t\t\t\t\"email\":\"cadfa@fdas.das\",\n" +
                "\t\t\t\t\"phone\":\"34234242323\",\n" +
                "\t\t\t\t\"itn\":\"72\",\n" +
                "\t\t\t\t\"first_name\":\"name\",\n" +
                "\t\t\t\t\"last_name\":\"last\"\n" +
                "    },\n" +
                "    \"point\": {\n" +
                "        \"redirect_url\": \"https://www.google.com/\"\n" +
                "    },\n" +
                "    \"description\": \"Test deposit\"\n" +
                "    }");
        astroPay.pay_in();
        System.out.print("test_itn_notCorrect ");
        showAgoraURL(astroPay.getId());
    }

    public void test_without_itn(){
        astroPay = new AstroPay("{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2759,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"e5a095b16b8a2106380686b23c5570ac\"\n" +
                "    },\n" +
                "    \"locale\": \"ua\",\n" +
                "    \"external_order_id\": \"123456789011\",\n" +
                "    \"external_customer_id\": \"321123\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"account_id\": 2765,\n" +
                "    \"wallet_id\": 3015,\n" +
                "    \"service_id\": 4221,\n" +
                "    \"amount\": 1000,\n" +
                "    \"amount_currency\": \"BRL\",\n" +
                "    \"fields\": {\n" +
                "        \"country\": \"BR\",\n" +
                "\t\t\t\t\"email\":\"cadfa@fdas.das\",\n" +
                "\t\t\t\t\"phone\":\"34234242323\",\n" +
                "\t\t\t\t\"first_name\":\"name\",\n" +
                "\t\t\t\t\"last_name\":\"last\"\n" +
                "    },\n" +
                "    \"point\": {\n" +
                "        \"redirect_url\": \"https://www.google.com/\"\n" +
                "    },\n" +
                "    \"description\": \"Test deposit\"\n" +
                "    }");
        astroPay.pay_in();
        System.out.print("test_without_itn ");
        showAgoraURL(astroPay.getId());
    }

    public void test_first_name_empty(){
        astroPay = new AstroPay("{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2759,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"e5a095b16b8a2106380686b23c5570ac\"\n" +
                "    },\n" +
                "    \"locale\": \"ua\",\n" +
                "    \"external_order_id\": \"123456789011\",\n" +
                "    \"external_customer_id\": \"321123\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"account_id\": 2765,\n" +
                "    \"wallet_id\": 3015,\n" +
                "    \"service_id\": 4221,\n" +
                "    \"amount\": 1000,\n" +
                "    \"amount_currency\": \"BRL\",\n" +
                "    \"fields\": {\n" +
                "        \"country\": \"BR\",\n" +
                "\t\t\t\t\"email\":\"cadfa@fdas.das\",\n" +
                "\t\t\t\t\"phone\":\"34234242323\",\n" +
                "\t\t\t\t\"itn\":\"26040271272\",\n" +
                "\t\t\t\t\"first_name\":\"\",\n" +
                "\t\t\t\t\"last_name\":\"last\"\n" +
                "    },\n" +
                "    \"point\": {\n" +
                "        \"redirect_url\": \"https://www.google.com/\"\n" +
                "    },\n" +
                "    \"description\": \"Test deposit\"\n" +
                "    }");
        astroPay.pay_in();
        System.out.print("test_first_name_empty ");
        showAgoraURL(astroPay.getId());
    }

    public void test_first_name_big_size(){
        astroPay = new AstroPay("{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2759,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"e5a095b16b8a2106380686b23c5570ac\"\n" +
                "    },\n" +
                "    \"locale\": \"ua\",\n" +
                "    \"external_order_id\": \"123456789011\",\n" +
                "    \"external_customer_id\": \"321123\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"account_id\": 2765,\n" +
                "    \"wallet_id\": 3015,\n" +
                "    \"service_id\": 4221,\n" +
                "    \"amount\": 1000,\n" +
                "    \"amount_currency\": \"BRL\",\n" +
                "    \"fields\": {\n" +
                "        \"country\": \"BR\",\n" +
                "\t\t\t\t\"email\":\"cadfa@fdas.das\",\n" +
                "\t\t\t\t\"phone\":\"34234242323\",\n" +
                "\t\t\t\t\"itn\":\"26040271272\",\n" +
                "\t\t\t\t\"first_name\":\"dsfsdlknflksdmnfklsddsfsdlknflksdmnfklsddsfsdlknflksdmnfklsddsfsdlknflksdmnfklsddsfsdlknflksdmnfklsdd\",\n" +
                "\t\t\t\t\"last_name\":\"last\"\n" +
                "    },\n" +
                "    \"point\": {\n" +
                "        \"redirect_url\": \"https://www.google.com/\"\n" +
                "    },\n" +
                "    \"description\": \"Test deposit\"\n" +
                "    }");
        astroPay.pay_in();
        System.out.print("test_first_name_big_size ");
        showAgoraURL(astroPay.getId());
    }

    public void test_last_name_big_size(){
        astroPay = new AstroPay("{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2759,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"e5a095b16b8a2106380686b23c5570ac\"\n" +
                "    },\n" +
                "    \"locale\": \"ua\",\n" +
                "    \"external_order_id\": \"123456789011\",\n" +
                "    \"external_customer_id\": \"321123\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"account_id\": 2765,\n" +
                "    \"wallet_id\": 3015,\n" +
                "    \"service_id\": 4221,\n" +
                "    \"amount\": 1000,\n" +
                "    \"amount_currency\": \"BRL\",\n" +
                "    \"fields\": {\n" +
                "        \"country\": \"BR\",\n" +
                "\t\t\t\t\"email\":\"cadfa@fdas.das\",\n" +
                "\t\t\t\t\"phone\":\"34234242323\",\n" +
                "\t\t\t\t\"itn\":\"26040271272\",\n" +
                "\t\t\t\t\"first_name\":\"first\",\n" +
                "\t\t\t\t\"last_name\":\"dsfsdlknflksdmnfklsddsfsdlknflksdmnfklsddsfsdlknflksdmnfklsddsfsdlknflksdmnfklsddsfsdlknflksdmnfklsdd\"\n" +
                "    },\n" +
                "    \"point\": {\n" +
                "        \"redirect_url\": \"https://www.google.com/\"\n" +
                "    },\n" +
                "    \"description\": \"Test deposit\"\n" +
                "    }");
        astroPay.pay_in();
        System.out.print("test_last_name_big_size ");
        showAgoraURL(astroPay.getId());
    }

    public void test_first_name_null(){
        astroPay = new AstroPay("{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2759,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"e5a095b16b8a2106380686b23c5570ac\"\n" +
                "    },\n" +
                "    \"locale\": \"ua\",\n" +
                "    \"external_order_id\": \"123456789011\",\n" +
                "    \"external_customer_id\": \"321123\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"account_id\": 2765,\n" +
                "    \"wallet_id\": 3015,\n" +
                "    \"service_id\": 4221,\n" +
                "    \"amount\": 1000,\n" +
                "    \"amount_currency\": \"BRL\",\n" +
                "    \"fields\": {\n" +
                "        \"country\": \"BR\",\n" +
                "\t\t\t\t\"email\":\"cadfa@fdas.das\",\n" +
                "\t\t\t\t\"phone\":\"34234242323\",\n" +
                "\t\t\t\t\"itn\":\"26040271272\",\n" +
                "\t\t\t\t\"first_name\":null,\n" +
                "\t\t\t\t\"last_name\":\"last\"\n" +
                "    },\n" +
                "    \"point\": {\n" +
                "        \"redirect_url\": \"https://www.google.com/\"\n" +
                "    },\n" +
                "    \"description\": \"Test deposit\"\n" +
                "    }");
        astroPay.pay_in();
        System.out.print("test_first_name_null ");
        showAgoraURL(astroPay.getId());
    }

    public void test_without_first_name(){
        astroPay = new AstroPay("{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2759,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"e5a095b16b8a2106380686b23c5570ac\"\n" +
                "    },\n" +
                "    \"locale\": \"ua\",\n" +
                "    \"external_order_id\": \"123456789011\",\n" +
                "    \"external_customer_id\": \"321123\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"account_id\": 2765,\n" +
                "    \"wallet_id\": 3015,\n" +
                "    \"service_id\": 4221,\n" +
                "    \"amount\": 1000,\n" +
                "    \"amount_currency\": \"BRL\",\n" +
                "    \"fields\": {\n" +
                "        \"country\": \"BR\",\n" +
                "\t\t\t\t\"email\":\"cadfa@fdas.das\",\n" +
                "\t\t\t\t\"phone\":\"34234242323\",\n" +
                "\t\t\t\t\"itn\":\"26040271272\",\n" +
                "\t\t\t\t\"last_name\":\"last\"\n" +
                "    },\n" +
                "    \"point\": {\n" +
                "        \"redirect_url\": \"https://www.google.com/\"\n" +
                "    },\n" +
                "    \"description\": \"Test deposit\"\n" +
                "    }");
        astroPay.pay_in();
        System.out.print("test_without_first_name ");
        showAgoraURL(astroPay.getId());
    }

    public void test_last_name_empty(){
        astroPay = new AstroPay("{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2759,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"e5a095b16b8a2106380686b23c5570ac\"\n" +
                "    },\n" +
                "    \"locale\": \"ua\",\n" +
                "    \"external_order_id\": \"123456789011\",\n" +
                "    \"external_customer_id\": \"321123\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"account_id\": 2765,\n" +
                "    \"wallet_id\": 3015,\n" +
                "    \"service_id\": 4221,\n" +
                "    \"amount\": 1000,\n" +
                "    \"amount_currency\": \"BRL\",\n" +
                "    \"fields\": {\n" +
                "        \"country\": \"BR\",\n" +
                "\t\t\t\t\"email\":\"cadfa@fdas.das\",\n" +
                "\t\t\t\t\"phone\":\"34234242323\",\n" +
                "\t\t\t\t\"itn\":\"26040271272\",\n" +
                "\t\t\t\t\"first_name\":\"name\",\n" +
                "\t\t\t\t\"last_name\":\"\"\n" +
                "    },\n" +
                "    \"point\": {\n" +
                "        \"redirect_url\": \"https://www.google.com/\"\n" +
                "    },\n" +
                "    \"description\": \"Test deposit\"\n" +
                "    }");
        astroPay.pay_in();
        System.out.print("test_last_name_empty ");
        showAgoraURL(astroPay.getId());
    }

    public void test_last_name_notCorrect(){
        astroPay = new AstroPay("{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2759,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"e5a095b16b8a2106380686b23c5570ac\"\n" +
                "    },\n" +
                "    \"locale\": \"ua\",\n" +
                "    \"external_order_id\": \"123456789011\",\n" +
                "    \"external_customer_id\": \"321123\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"account_id\": 2765,\n" +
                "    \"wallet_id\": 3015,\n" +
                "    \"service_id\": 4221,\n" +
                "    \"amount\": 1000,\n" +
                "    \"amount_currency\": \"BRL\",\n" +
                "    \"fields\": {\n" +
                "        \"country\": \"BR\",\n" +
                "\t\t\t\t\"email\":\"cadfa@fdas.das\",\n" +
                "\t\t\t\t\"phone\":\"34234242323\",\n" +
                "\t\t\t\t\"itn\":\"26040271272\",\n" +
                "\t\t\t\t\"first_name\":\"name\",\n" +
                "\t\t\t\t\"last_name\":\"423dffd !@#$$%cx\"\n" +
                "    },\n" +
                "    \"point\": {\n" +
                "        \"redirect_url\": \"https://www.google.com/\"\n" +
                "    },\n" +
                "    \"description\": \"Test deposit\"\n" +
                "    }");
        astroPay.pay_in();
        System.out.print("test_last_name_notCorrect ");
        showAgoraURL(astroPay.getId());
    }

    public void test_first_name_notCorrect(){
        astroPay = new AstroPay("{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2759,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"e5a095b16b8a2106380686b23c5570ac\"\n" +
                "    },\n" +
                "    \"locale\": \"ua\",\n" +
                "    \"external_order_id\": \"123456789011\",\n" +
                "    \"external_customer_id\": \"321123\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"account_id\": 2765,\n" +
                "    \"wallet_id\": 3015,\n" +
                "    \"service_id\": 4221,\n" +
                "    \"amount\": 1000,\n" +
                "    \"amount_currency\": \"BRL\",\n" +
                "    \"fields\": {\n" +
                "        \"country\": \"BR\",\n" +
                "\t\t\t\t\"email\":\"cadfa@fdas.das\",\n" +
                "\t\t\t\t\"phone\":\"34234242323\",\n" +
                "\t\t\t\t\"itn\":\"26040271272\",\n" +
                "\t\t\t\t\"first_name\":\"423dffd !@#$$%cx\",\n" +
                "\t\t\t\t\"last_name\":\"last\"\n" +
                "    },\n" +
                "    \"point\": {\n" +
                "        \"redirect_url\": \"https://www.google.com/\"\n" +
                "    },\n" +
                "    \"description\": \"Test deposit\"\n" +
                "    }");
        astroPay.pay_in();
        System.out.print("test_first_name_notCorrect ");
        showAgoraURL(astroPay.getId());
    }

    public void test_last_name_rusSymbol(){
        astroPay = new AstroPay("{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2759,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"e5a095b16b8a2106380686b23c5570ac\"\n" +
                "    },\n" +
                "    \"locale\": \"ua\",\n" +
                "    \"external_order_id\": \"123456789011\",\n" +
                "    \"external_customer_id\": \"321123\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"account_id\": 2765,\n" +
                "    \"wallet_id\": 3015,\n" +
                "    \"service_id\": 4221,\n" +
                "    \"amount\": 1000,\n" +
                "    \"amount_currency\": \"BRL\",\n" +
                "    \"fields\": {\n" +
                "        \"country\": \"BR\",\n" +
                "\t\t\t\t\"email\":\"cadfa@fdas.das\",\n" +
                "\t\t\t\t\"phone\":\"34234242323\",\n" +
                "\t\t\t\t\"itn\":\"26040271272\",\n" +
                "\t\t\t\t\"first_name\":\"name\",\n" +
                "\t\t\t\t\"last_name\":\"ваысавыф\"\n" +
                "    },\n" +
                "    \"point\": {\n" +
                "        \"redirect_url\": \"https://www.google.com/\"\n" +
                "    },\n" +
                "    \"description\": \"Test deposit\"\n" +
                "    }");
        astroPay.pay_in();
        System.out.print("test_last_name_rusSymbol ");
        showAgoraURL(astroPay.getId());
    }

    public void test_first_name_rusSymbol(){
        astroPay = new AstroPay("{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2759,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"e5a095b16b8a2106380686b23c5570ac\"\n" +
                "    },\n" +
                "    \"locale\": \"ua\",\n" +
                "    \"external_order_id\": \"123456789011\",\n" +
                "    \"external_customer_id\": \"321123\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"account_id\": 2765,\n" +
                "    \"wallet_id\": 3015,\n" +
                "    \"service_id\": 4221,\n" +
                "    \"amount\": 1000,\n" +
                "    \"amount_currency\": \"BRL\",\n" +
                "    \"fields\": {\n" +
                "        \"country\": \"BR\",\n" +
                "\t\t\t\t\"email\":\"cadfa@fdas.das\",\n" +
                "\t\t\t\t\"phone\":\"34234242323\",\n" +
                "\t\t\t\t\"itn\":\"26040271272\",\n" +
                "\t\t\t\t\"first_name\":\"ываывац\",\n" +
                "\t\t\t\t\"last_name\":\"last\"\n" +
                "    },\n" +
                "    \"point\": {\n" +
                "        \"redirect_url\": \"https://www.google.com/\"\n" +
                "    },\n" +
                "    \"description\": \"Test deposit\"\n" +
                "    }");
        astroPay.pay_in();
        System.out.print("test_first_name_rusSymbol ");
        showAgoraURL(astroPay.getId());
    }

    public void test_last_name_null(){
        astroPay = new AstroPay("{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2759,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"e5a095b16b8a2106380686b23c5570ac\"\n" +
                "    },\n" +
                "    \"locale\": \"ua\",\n" +
                "    \"external_order_id\": \"123456789011\",\n" +
                "    \"external_customer_id\": \"321123\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"account_id\": 2765,\n" +
                "    \"wallet_id\": 3015,\n" +
                "    \"service_id\": 4221,\n" +
                "    \"amount\": 1000,\n" +
                "    \"amount_currency\": \"BRL\",\n" +
                "    \"fields\": {\n" +
                "        \"country\": \"BR\",\n" +
                "\t\t\t\t\"email\":\"cadfa@fdas.das\",\n" +
                "\t\t\t\t\"phone\":\"34234242323\",\n" +
                "\t\t\t\t\"itn\":\"26040271272\",\n" +
                "\t\t\t\t\"first_name\":\"name\",\n" +
                "\t\t\t\t\"last_name\":null\n" +
                "    },\n" +
                "    \"point\": {\n" +
                "        \"redirect_url\": \"https://www.google.com/\"\n" +
                "    },\n" +
                "    \"description\": \"Test deposit\"\n" +
                "    }");
        astroPay.pay_in();
        System.out.print("test_last_name_null ");
        showAgoraURL(astroPay.getId());
    }

    public void test_without_last_name(){
        astroPay = new AstroPay("{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2759,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"e5a095b16b8a2106380686b23c5570ac\"\n" +
                "    },\n" +
                "    \"locale\": \"ua\",\n" +
                "    \"external_order_id\": \"123456789011\",\n" +
                "    \"external_customer_id\": \"321123\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"account_id\": 2765,\n" +
                "    \"wallet_id\": 3015,\n" +
                "    \"service_id\": 4221,\n" +
                "    \"amount\": 1000,\n" +
                "    \"amount_currency\": \"BRL\",\n" +
                "    \"fields\": {\n" +
                "        \"country\": \"BR\",\n" +
                "\t\t\t\t\"email\":\"cadfa@fdas.das\",\n" +
                "\t\t\t\t\"phone\":\"34234242323\",\n" +
                "\t\t\t\t\"itn\":\"26040271272\",\n" +
                "\t\t\t\t\"first_name\":\"name\"\n" +
                "    },\n" +
                "    \"point\": {\n" +
                "        \"redirect_url\": \"https://www.google.com/\"\n" +
                "    },\n" +
                "    \"description\": \"Test deposit\"\n" +
                "    }");
        astroPay.pay_in();
        System.out.print("test_without_last_name ");
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

    @Test(enabled = false)
    public void call(){
        String bodyCallback = "{\n" +
                "    \"deposit_external_id\": \"jRmiGaLPbE0eI4OoP9X8YkgtiTfewAkhqE1zgQDn\", \n" +
                "    \"merchant_deposit_id\": \"TD-001\",\n" +
                "    \"deposit_user_id\": \"Vx0H4pdAtPCz\",\n" +
                "    \"merchant_user_id\": \"TU-001\",\n" +
                "    \"status\": \"APPROVED\",\n" +
                "    \"end_status_date\": \"2022-04-08T16:17:49\"\n" +
                "}";
        new Callback().makeCallback(new TransInfoConn(110100000481178l).getTransaction_uuid(),bodyCallback);
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
