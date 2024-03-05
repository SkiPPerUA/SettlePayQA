package projectSettlePayTests.back.providersTests.PayzEasy;

import org.testng.annotations.Test;
import projectSettlePay.BaseTest;
import projectSettlePay.back.providers.PayzEasy;

@Test
public class PayzEasy_payout extends BaseTest {

    PayzEasy payzEasy;

    public void positive_test(){
        payzEasy = new PayzEasy(PayzEasy.PayzEasyBody.defaultBody(false));
        payzEasy.pay_out();
        payzEasy.callback(false,"Success");
        showAgoraURL(payzEasy.getId());
    }
}
