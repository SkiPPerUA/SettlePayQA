package projectSettlePayTests.back.providersTests.PayCord;

import org.testng.Assert;
import org.testng.annotations.Test;
import projectSettlePay.BaseTest;
import projectSettlePay.back.TransInfoConn;
import projectSettlePay.back.TransInfoCore;
import projectSettlePay.back.providers.Paycord;
import projectSettlePay.core.Session;

@Test
public class PayCordPayIn extends BaseTest {

    Paycord paycord;

    public void positive_payin(){
        paycord = new Paycord(Paycord.PaycordBody.defaultBody(true));
        paycord.pay_in();
        //paycord.frame.positiveSteps();
        Session.getDriver().get(paycord.getPayURL());
        Assert.assertEquals(new TransInfoConn(Long.valueOf(paycord.getId())+1).getStatus(),1);
        Assert.assertEquals(new TransInfoCore(Long.valueOf(paycord.getId())).getStatus(),1);
        Assert.assertEquals(new TransInfoCore(Long.valueOf(paycord.getId())+1).getStatus(),1);
        showAgoraURL(paycord.getId());
    }

    public void negative_invalid_phone(){
        paycord = new Paycord("");
        paycord.pay_in();
        Assert.assertEquals(new TransInfoConn(Long.valueOf(paycord.getId())+1).getStatus(),2);
        Assert.assertEquals(new TransInfoCore(Long.valueOf(paycord.getId())).getStatus(),2);
        Assert.assertEquals(new TransInfoCore(Long.valueOf(paycord.getId())+1).getStatus(),2);
        showAgoraURL(paycord.getId());
    }

    public void negative_invalid_bank_account(){
        paycord = new Paycord("");
        paycord.pay_in();
        showAgoraURL(paycord.getId());
        Assert.assertEquals(new TransInfoConn(Long.valueOf(paycord.getId())+1).getStatus(),2);
        Assert.assertEquals(new TransInfoCore(Long.valueOf(paycord.getId())).getStatus(),2);
        Assert.assertEquals(new TransInfoCore(Long.valueOf(paycord.getId())+1).getStatus(),2);
        showAgoraURL(paycord.getId());
    }
}
