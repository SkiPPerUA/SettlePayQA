package projectSettlePayTests.back.providersTests.AstroPay;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import projectSettlePay.BaseTest;
import projectSettlePay.back.Callback;
import projectSettlePay.back.TransInfoConn;
import projectSettlePay.back.TransInfoCore;
import projectSettlePay.back.providers.AstroPay;
import projectSettlePay.helper.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Test
public class AstroPayPayIn extends BaseTest {

    AstroPay astroPay;

    public void positive_payin_APPROVED(){
        astroPay = new AstroPay(AstroPay.AstroPayBody.defaultBody());
        astroPay.pay_in();
        makeCallback("APPROVED");
        Assert.assertEquals(new TransInfoConn(Long.valueOf(astroPay.getId())+1).getStatus(),1);
        Assert.assertEquals(new TransInfoCore(Long.valueOf(astroPay.getId())).getStatus(),1);
        Assert.assertEquals(new TransInfoCore(Long.valueOf(astroPay.getId())+1).getStatus(),1);
        Assert.assertTrue(new TransInfoConn(Long.valueOf(astroPay.getId())+1).getProvider_data().contains("deposit_user_id"));
        System.out.print("positive_payin_APPROVED ");
        showAgoraURL(astroPay.getId());
    }

    public void positive_payin_PENDING(){
        astroPay = new AstroPay(AstroPay.AstroPayBody.defaultBody());
        astroPay.pay_in();
        makeCallback("PENDING");
        Assert.assertEquals(new TransInfoConn(Long.valueOf(astroPay.getId())+1).getStatus(),10);
        Assert.assertEquals(new TransInfoCore(Long.valueOf(astroPay.getId())).getStatus(),10);
        Assert.assertEquals(new TransInfoCore(Long.valueOf(astroPay.getId())+1).getStatus(),10);
        System.out.print("positive_payin_PENDING ");
        showAgoraURL(astroPay.getId());
    }

    public void positive_payin_CANCELED(){
        astroPay = new AstroPay(AstroPay.AstroPayBody.defaultBody());
        astroPay.pay_in();
        makeCallback("CANCELED");
        Assert.assertEquals(new TransInfoConn(Long.valueOf(astroPay.getId())+1).getStatus(),10);
        Assert.assertEquals(new TransInfoCore(Long.valueOf(astroPay.getId())).getStatus(),10);
        Assert.assertEquals(new TransInfoCore(Long.valueOf(astroPay.getId())+1).getStatus(),10);
        System.out.print("positive_payin_CANCELED ");
        showAgoraURL(astroPay.getId());
    }

    public void positive_payin_APPROVED_after_CANCELED(){
        astroPay = new AstroPay(AstroPay.AstroPayBody.defaultBody());
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

    public void positive_payin_CANCELED_after_fiveDays(){
        astroPay = new AstroPay(AstroPay.AstroPayBody.defaultBody());
        astroPay.pay_in();
        makeCallback("CANCELED");
        Assert.assertEquals(new TransInfoConn(Long.valueOf(astroPay.getId())+1).getStatus(),10);
        Assert.assertEquals(new TransInfoCore(Long.valueOf(astroPay.getId())).getStatus(),10);
        Assert.assertEquals(new TransInfoCore(Long.valueOf(astroPay.getId())+1).getStatus(),10);
        long child = Long.valueOf(astroPay.getId()) + 1;
        getDataBase(CONN_STAGE_1).updateSql("UPDATE public.transactions SET created_at = '"+ Data.getMinusDays(5) +"', updated_at = '"+Data.getMinusDays(1)+"' , WHERE external_transaction_id = '"+child+"'");
        getDataBase(APIPAY_STAGE_1).updateSql("UPDATE public.transactions SET created_at = '"+ Data.getMinusDays(5) +"', updated_at = '"+Data.getMinusDays(1)+"' , WHERE id = "+child+"");
        getDataBase(APIPAY_STAGE_1).updateSql("UPDATE public.transactions SET created_at = '"+ Data.getMinusDays(5) +"', updated_at = '"+Data.getMinusDays(1)+"' , WHERE id = "+astroPay.getId()+"");
        makeCallback("CANCELED");
        Assert.assertEquals(new TransInfoConn(Long.valueOf(astroPay.getId())+1).getStatus(),2);
        Assert.assertEquals(new TransInfoCore(Long.valueOf(astroPay.getId())).getStatus(),2);
        Assert.assertEquals(new TransInfoCore(Long.valueOf(astroPay.getId())+1).getStatus(),2);
        System.out.print("positive_payin_CANCELED_after_fiveDays ");
        showAgoraURL(astroPay.getId());
    }

    public void test_sum_MIN(){
        astroPay = new AstroPay("");
        astroPay.pay_in();
        System.out.print("test_sum_MIN ");
        showAgoraURL(astroPay.getId());
    }

    public void test_sum_MAX(){
        astroPay = new AstroPay("");
        astroPay.pay_in();
        System.out.print("test_sum_MIN ");
        showAgoraURL(astroPay.getId());
    }

    public void negative_without_country(){
        astroPay = new AstroPay("");
        astroPay.pay_in();
        System.out.print("negative_without_country ");
        showAgoraURL(astroPay.getId());
    }

    public void negative_country_null(){
        astroPay = new AstroPay("");
        astroPay.pay_in();
        System.out.print("negative_country_null ");
        showAgoraURL(astroPay.getId());
    }

    public void negative_country_empty(){
        astroPay = new AstroPay("");
        astroPay.pay_in();
        System.out.print("negative_country_empty ");
        showAgoraURL(astroPay.getId());
    }
    public void test_all_currency(){
        List<String> curr_name = List.of("USD", "ARS", "BRL", "CLP", "COP", "CRC", "MXN", "PEN", "UYU");
        Map<String, String> currency_wallet = new HashMap<>();
        currency_wallet.put("USD","3554");
        String body = "";
        for (String curr: curr_name) {
            astroPay = new AstroPay(String.format(body, curr, currency_wallet.get(curr)));
            astroPay.pay_in();
            makeCallback("APPROVED");
            Assert.assertEquals(new TransInfoConn(Long.valueOf(astroPay.getId())+1).getStatus(),1);
            Assert.assertEquals(new TransInfoCore(Long.valueOf(astroPay.getId())).getStatus(),1);
            Assert.assertEquals(new TransInfoCore(Long.valueOf(astroPay.getId())+1).getStatus(),1);
            Assert.assertTrue(new TransInfoConn(Long.valueOf(astroPay.getId())+1).getProvider_data().contains("deposit_user_id"));
            System.out.print("test_all_currency ("+curr+") ");
            showAgoraURL(astroPay.getId());
        }
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

    @AfterTest
    void close(){
        closeCon();
    }
}
