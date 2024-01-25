package projectSettlePayTests.back;

import org.testng.annotations.Test;
import projectSettlePay.BaseTest;
import projectSettlePay.back.Providers.PUMB;
import projectSettlePay.back.Providers.Pay_out;

import java.util.List;

@Test
public class Pay_out_Tests extends BaseTest {

    List<Pay_out> providers = List.of(new PUMB(PUMB.PUMBBody.defaultBody()));
    int testConn = 8;
    public void pay_out_test(){
        for (Pay_out prov : providers){
            if (prov.getConn() == testConn){
                System.out.print(prov.getClass().getName().replace("projectSettlePay.back.Providers.",""));
                prov.pay_out();
                System.out.println(": trans - (parent) "+prov.getId()+"  (child) - "+Long.valueOf(prov.getId())+1);
                System.out.println("https://preprod-agora2.backofficeweb.info/transactions/transactions/"+Long.valueOf(prov.getId())+1);
            }
        }
    }
}
