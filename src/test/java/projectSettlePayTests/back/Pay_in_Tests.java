package projectSettlePayTests.back;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import projectSettlePay.BaseTest;
import projectSettlePay.back.Providers.IProviders;
import projectSettlePay.back.Providers.OnePay;
import projectSettlePay.back.Providers.PayNetEazy;
import projectSettlePay.core.Session;
import projectSettlePay.helper.UuidGenerate;

import java.util.ArrayList;
import java.util.List;

@Test
public class Pay_in_Tests extends BaseTest {

    List<IProviders> providers = new ArrayList<>();

    public void test() throws InterruptedException {
        providers.add(new PayNetEazy("{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 1,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"c9194644d4316e008a7afb0d50bf056b\"\n" +
                "    },\n" +
                "    \"locale\": \"ua\",\n" +
                "    \"external_order_id\": \""+ UuidGenerate.generateUUID() +"\",\n" +
                "    \"external_customer_id\": \""+ UuidGenerate.generateUUID() +"\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"account_id\": 4,\n" +
                "    \"wallet_id\": 118,\n" +
                "    \"service_id\": 3337,\n" +
                "    \"amount\": 1000,\n" +
                "    \"amount_currency\": \"EUR\" \n" +
                "}"));
        providers.add(new OnePay("{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2754,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"01607df2a07b633ea35a909152ba6061\"\n" +
                "    },\n" +
                "    \"locale\": \"ua\",\n" +
                "    \"external_order_id\": \"124458\",\n" +
                "    \"external_customer_id\": \"21465125111852852525525\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"account_id\": 2760,\n" +
                "    \"wallet_id\": 2530,\n" +
                "    \"service_id\": 3456,\n" +
                "    \"description\" : \"test\",\n" +
                "    \"amount\": 200,\n" +
                "    \"amount_currency\": \"UAH\"\n" +
                "}"));

        for (IProviders provider: providers) {
            provider.pay_in();
            Session.getDriver().get(provider.getPayURL());
            Thread.sleep(3000);
        }
    }

    @AfterTest
    void close(){
        Session.closeSession();
    }
}
