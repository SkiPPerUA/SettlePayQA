package projectSettlePayTests.back.providersTests.Munzen;

import org.testng.Assert;
import org.testng.annotations.Test;
import projectSettlePay.BaseTest;
import projectSettlePay.back.TransInfoConn;
import projectSettlePay.back.TransInfoCore;
import projectSettlePay.back.providers.Munzen;

@Test
public class MunzenPayOut extends BaseTest {

    Munzen munzen;

    public void positove_payout(){
        munzen = new Munzen(Munzen.MunzenBody.defaultBody());
        munzen.pay_out();
        munzen.makeCallback("");
        Assert.assertEquals(new TransInfoConn(Long.valueOf(munzen.getId())+1).getStatus(),1);
        Assert.assertEquals(new TransInfoCore(Long.valueOf(munzen.getId())).getStatus(),1);
        Assert.assertEquals(new TransInfoCore(Long.valueOf(munzen.getId())+1).getStatus(),1);
        Assert.assertEquals(new TransInfoConn(Long.valueOf(munzen.getId())+1).getAmount(),1000);
        Assert.assertEquals(new TransInfoCore(Long.valueOf(munzen.getId())).getAmount(),10.00);
        Assert.assertEquals(new TransInfoCore(Long.valueOf(munzen.getId())+1).getAmount(),10.00);
        showAgoraURL(munzen.getId());
    }

}
