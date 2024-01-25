package projectSettlePayTests.back;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import projectSettlePay.BaseTest;
import projectSettlePay.back.Providers.*;
import projectSettlePay.core.Session;
import java.util.List;

@Test
public class Pay_in_Tests extends BaseTest {

    List<Pay_in> providers = List.of(new PayNetEasy(PayNetEasy.PayNetEasyBody.defaultBody()),
            new OnePay(OnePay.OnePayBody.defaultBody(true)));
    int testConn = 3;
    public void pay_inTest() throws InterruptedException {
      for (Pay_in prov:providers){
          if (prov.getConn() == testConn){
              System.out.print(prov.getClass().getName().replace("projectSettlePay.back.Providers.",""));
              prov.pay_in();
              System.out.println(": trans - (parent) "+prov.getId()+"  (child) - "+Long.valueOf(prov.getId())+1);
              System.out.println("https://preprod-agora2.backofficeweb.info/transactions/transactions/"+Long.valueOf(prov.getId())+1);
              Session.getDriver().get(prov.getPayURL());
              prov.getFrame().positiveSteps();
              Thread.sleep(5000);
          }
      }
    }
    @AfterTest
    void close(){
        Session.closeSession();
    }
}
