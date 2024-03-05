package projectSettlePayTests.back.providersTests.Trio;

import org.testng.Assert;
import org.testng.annotations.Test;
import projectSettlePay.BaseTest;
import projectSettlePay.back.TransInfoConn;
import projectSettlePay.back.TransInfoCore;
import projectSettlePay.back.providers.TrioPIX;

@Test
public class TrioPIX_payout extends BaseTest {

    TrioPIX trioPIX;

    public void positive_test() throws InterruptedException {
        trioPIX = new TrioPIX(TrioPIX.TrioPIXBody.defaultBody(false));
        trioPIX.pay_out();
        long child = Long.valueOf(trioPIX.getId()) + 1;
        Thread.sleep(5000);
        Assert.assertEquals(new TransInfoCore(Long.valueOf(trioPIX.getId())).getStatus(),1);
        Assert.assertEquals(new TransInfoCore(child).getStatus(),1);
        Assert.assertEquals(new TransInfoConn(child).getStatus(),1);
        showAgoraURL(trioPIX.getId());
    }
}
