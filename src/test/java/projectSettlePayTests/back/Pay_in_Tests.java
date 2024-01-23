package projectSettlePayTests.back;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import projectSettlePay.BaseTest;
import projectSettlePay.back.Providers.*;
import projectSettlePay.core.Session;
import projectSettlePay.helper.UuidGenerate;

import java.util.ArrayList;
import java.util.List;

@Test
public class Pay_in_Tests extends BaseTest {

    public void test() {
        for (IProviders provider: IProviders.getAllPayIn()) {
            if (provider.getConn() == 7) {
                provider.pay_in();
            }
        }
    }

    @AfterTest
    void close(){
        Session.closeSession();
    }
}
