package projectSettlePayTests.back.providersTests.PayCash;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import projectSettlePay.BaseTest;
import projectSettlePay.back.providers.PayCash;
import projectSettlePay.core.Session;

@Test
public class PayCash_payin extends BaseTest {

    PayCash payCash;

    public void positive_test(){
        payCash = new PayCash(PayCash.PayCashBody.defaultBody());
        payCash.pay_in();
        Session.getDriver().get(payCash.getPayURL());
        payCash.frame.positiveSteps();
        showAgoraURL(payCash.getId());
    }

    @AfterTest
    void close(){
        Session.closeSession();
    }
}
