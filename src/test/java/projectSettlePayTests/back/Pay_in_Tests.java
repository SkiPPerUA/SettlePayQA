package projectSettlePayTests.back;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import projectSettlePay.BaseTest;
import projectSettlePay.back.providers.*;
import projectSettlePay.core.Session;
import projectSettlePay.front.IFrame;

import java.util.List;

@Test
public class Pay_in_Tests extends BaseTest {

    int testConn = 3;
    List<Pay_in> providers = List.of(
            new Paycord(Paycord.PaycordBody.defaultBody(true)),
            new PrimePay(PrimePay.PrimePayBody.defaultBody(true)),
            new PayECards(PayECards.PayECardsBody.defaultBody()),
            new PayCash(PayCash.PayCashBody.defaultBody()),
            new PayNetEasy(PayNetEasy.PayNetEasyBody.defaultBody()),
            new OnePay(OnePay.OnePayBody.defaultBody(true))
    );
    public void pay_inTest() throws InterruptedException {
      for (Pay_in prov:providers){
          if (prov.getConn() == testConn){
              System.out.print(prov.getClass().getName().replace("projectSettlePay.back.providers.",""));
              prov.pay_in();
              long child = Long.valueOf(prov.getId())+1;
              System.out.println(": trans - (parent) "+prov.getId()+"  (child) - "+child);
              System.out.println("https://preprod-agora2.backofficeweb.info/transactions/transactions/"+child);
              Session.getDriver().get(prov.getPayURL());
              IFrame frame = prov.getFrame();
              if (frame == null){
                  Thread.sleep(20000);
              }else {
                  frame.positiveSteps();
              }
              Thread.sleep(5000);
          }
      }
    }
    @AfterTest
    void close(){
        Session.closeSession();
    }
}
