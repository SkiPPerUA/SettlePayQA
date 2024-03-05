package projectSettlePayTests.back.providersTests.MoneyGramm;

import org.testng.annotations.Test;
import projectSettlePay.BaseTest;
import projectSettlePay.back.providers.MoneyGram;

@Test
public class MoneyGramPayOut extends BaseTest {

    MoneyGram moneyGram;

    public void positive_test(){
        moneyGram = new MoneyGram(MoneyGram.MoneyGramBody.defaultBody(false));
        moneyGram.pay_out();
    }

    public void positive_test_viaSettle(){
        moneyGram = new MoneyGram(MoneyGram.MoneyGramBody.defaultBody(true));
        moneyGram.pay_out();
    }
}
