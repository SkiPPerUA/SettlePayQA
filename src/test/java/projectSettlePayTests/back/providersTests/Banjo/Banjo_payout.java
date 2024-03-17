package projectSettlePayTests.back.providersTests.Banjo;

import org.testng.annotations.Test;
import projectSettlePay.BaseTest;
import projectSettlePay.back.providers.Banjo;

@Test
public class Banjo_payout extends BaseTest {

    Banjo banjo;

    public void positive_test(){
        banjo = new Banjo(Banjo.BanjoBody.defaultBody("payout"));
        banjo.pay_out();
        banjo.callback("APPROVED");
        showAgoraURL(banjo.getId());
    }
}
