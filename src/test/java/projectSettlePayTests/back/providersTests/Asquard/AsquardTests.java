package projectSettlePayTests.back.providersTests.Asquard;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import projectSettlePay.BaseTest;
import projectSettlePay.back.providers.Asquard;
import projectSettlePay.core.Session;

@Test
public class AsquardTests extends BaseTest {

    Asquard asquard;

    public void pay_in() throws InterruptedException {
        asquard = new Asquard(Asquard.AsquardBody.defaultBody("pay_in"),"pay_in");
        asquard.pay_in();
        Session.getDriver().get(asquard.getPayURL());
        Thread.sleep(3000);
    }

    public void pay_out(){
        asquard = new Asquard(Asquard.AsquardBody.defaultBody("pay_out"),"pay_out");
        asquard.pay_out();
    }

    public void boletto() {
        asquard = new Asquard(Asquard.AsquardBody.defaultBody("boletto"),"boletto");
        asquard.pay_in();
        Session.getDriver().get(asquard.getPayURL());
        asquard.frame.positiveSteps();
    }

    public void upi(){
        asquard = new Asquard(Asquard.AsquardBody.defaultBody("upi"),"upi");
        asquard.pay_out();
    }

    @AfterTest
    void close() {
        Session.closeSession();
    }

}
