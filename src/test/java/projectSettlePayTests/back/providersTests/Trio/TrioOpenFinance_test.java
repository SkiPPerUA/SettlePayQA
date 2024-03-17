package projectSettlePayTests.back.providersTests.Trio;

import org.testng.annotations.Test;
import projectSettlePay.BaseTest;
import projectSettlePay.back.providers.TrioOpenFinance;

@Test
public class TrioOpenFinance_test extends BaseTest {

    TrioOpenFinance trioOpenFinance;

    public void positive_test(){
        trioOpenFinance = new TrioOpenFinance(TrioOpenFinance.TrioOpenFinanceBody.defaultBody());
        trioOpenFinance.pay_in();
        showAgoraURL(trioOpenFinance.getId());
        trioOpenFinance.callback();
    }
}
