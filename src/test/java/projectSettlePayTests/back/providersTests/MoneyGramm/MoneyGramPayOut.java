package projectSettlePayTests.back.providersTests.MoneyGramm;

import org.testng.annotations.Test;
import projectSettlePay.BaseTest;
import projectSettlePay.back.providers.MoneyGram;

@Test
public class MoneyGramPayOut extends BaseTest {

    MoneyGram moneyGram;

    public void positive_test(){
        moneyGram = new MoneyGram(MoneyGram.MoneyGramBody.defaultBody());
        moneyGram.pay_out();
    }
}
