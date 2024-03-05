package projectSettlePayTests.back.providersTests.Trio;

import org.testng.annotations.Test;
import projectSettlePay.BaseTest;
import projectSettlePay.back.providers.TrioPIX;

@Test
public class TrioPIX_payin extends BaseTest {

    TrioPIX trioPIX;

    public void positive_test(){
        trioPIX = new TrioPIX(TrioPIX.TrioPIXBody.defaultBody(true));
        trioPIX.pay_in();
        showAgoraURL(trioPIX.getId());
    }
}
