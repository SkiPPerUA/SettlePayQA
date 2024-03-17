package projectSettlePayTests.back.providersTests.Banjo;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import projectSettlePay.BaseTest;
import projectSettlePay.back.providers.Banjo;
import projectSettlePay.core.Session;

@Test
public class Banjo_payin extends BaseTest {

    Banjo banjo;

    public void positive_test(){
        banjo = new Banjo(Banjo.BanjoBody.defaultBody("hpp"));
        banjo.pay_in();
        Session.getDriver().get(banjo.getPayURL());
        banjo.frame.positiveSteps();
        banjo.callback("APPROVED");
        showAgoraURL(banjo.getId());
    }

    @AfterTest
    void close(){
        Session.closeSession();
    }
}
