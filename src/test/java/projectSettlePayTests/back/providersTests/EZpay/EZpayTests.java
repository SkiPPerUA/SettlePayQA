package projectSettlePayTests.back.providersTests.EZpay;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import projectSettlePay.BaseTest;
import projectSettlePay.back.providers.EZpay;
import projectSettlePay.core.Session;

@Test
public class EZpayTests extends BaseTest {

    EZpay eZpay;

    public void p2p(){
        eZpay = new EZpay(EZpay.EZpayBody.defaultBody("p2p"),"p2p");
        eZpay.pay_in();
        Session.getDriver().get(eZpay.getPayURL());
        eZpay.frame.positiveSteps();
    }

    public void acquiring() {
        eZpay = new EZpay(EZpay.EZpayBody.defaultBody("acquiring"),"acquiring");
        eZpay.pay_in();
        Session.getDriver().get(eZpay.getPayURL());
        eZpay.frame.positiveSteps();
    }

    public void pay_in_PAY() {
        eZpay = new EZpay(EZpay.EZpayBody.defaultBody("pay_in"),"pay_in");
        eZpay.pay_out();
    }

    public void pay_out() {
        eZpay = new EZpay(EZpay.EZpayBody.defaultBody("pay_out"),"pay_out");
        eZpay.pay_out();
    }


    @AfterTest
    void close() {
        Session.closeSession();
    }

}
