package projectSettlePayTests.back.providersTests.PayCord;

import org.testng.Assert;
import org.testng.annotations.Test;
import projectSettlePay.BaseTest;
import projectSettlePay.back.TransInfoConn;
import projectSettlePay.back.TransInfoCore;
import projectSettlePay.back.providers.Paycord;
import projectSettlePay.core.Session;

@Test
public class PayCordPayOut extends BaseTest {

    Paycord paycord;

    private void positive_payin() throws InterruptedException {
        paycord = new Paycord(Paycord.PaycordBody.defaultBody(false));
        paycord.pay_out();
        Thread.sleep(3000);
        Assert.assertEquals(new TransInfoConn(Long.valueOf(paycord.getId())+1).getStatus(),1);
        Assert.assertEquals(new TransInfoCore(Long.valueOf(paycord.getId())).getStatus(),1);
        Assert.assertEquals(new TransInfoCore(Long.valueOf(paycord.getId())+1).getStatus(),1);
        showAgoraURL(paycord.getId());
    }

    public void negative_invalid_phone() throws InterruptedException {
        paycord = new Paycord("{\n" +
                "    \"auth\": {\n" +
                        "        \"debug\": true,\n" +
                        "        \"point\": 2759,\n" +
                        "        \"key\": 1,\n" +
                        "        \"hash\": \"e5a095b16b8a2106380686b23c5570ac\"\n" +
                        "    },\n" +
                        "    \"locale\": \"ua\",\n" +
                        "    \"external_order_id\": \"100\",\n" +
                        "    \"external_customer_id\": \"3\",\n" +
                        "    \"customer_ip_address\": \"10.10.0.0\",\n" +
                        "    \"account_id\": 2765,\n" +
                        "    \"wallet_id\": 2561,\n" +
                        "    \"service_id\": 3355,\n" +
                        "    \"amount\": 30200,\n" +
                        "    \"amount_currency\": \"INR\",\n" +
                        "    \"fields\": {\n" +
                        "        \"TxnMode\": \"NB\",\n" +
                        "        \"BeneficiaryEmail\": \"test@test.com\",\n" +
                        "        \"BeneficiaryMobile\": \"+9199543211251111111111111\",\n" +
                        "        \"first_name\": \"John\",\n" +
                        "        \"last_name\": \"Doe\",\n" +
                        "        \"IFSC\": \"ICIC0001818\",\n" +
                        "        \"AccountNo\": \"38678978935\"\n" +
                        "    },\n" +
                        "    \"description\": \"\"\n" +
                        "}");
        paycord.pay_out();
        Thread.sleep(3000);
        Assert.assertEquals(new TransInfoConn(Long.valueOf(paycord.getId())+1).getStatus(),2);
        Assert.assertEquals(new TransInfoCore(Long.valueOf(paycord.getId())).getStatus(),2);
        Assert.assertEquals(new TransInfoCore(Long.valueOf(paycord.getId())+1).getStatus(),2);
        showAgoraURL(paycord.getId());
    }

    public void negative_invalid_bank_account() throws InterruptedException {
        paycord = new Paycord("{\n" +
                "    \"auth\": {\n" +
                        "        \"debug\": true,\n" +
                        "        \"point\": 2759,\n" +
                        "        \"key\": 1,\n" +
                        "        \"hash\": \"e5a095b16b8a2106380686b23c5570ac\"\n" +
                        "    },\n" +
                        "    \"locale\": \"ua\",\n" +
                        "    \"external_order_id\": \"100\",\n" +
                        "    \"external_customer_id\": \"3\",\n" +
                        "    \"customer_ip_address\": \"10.10.0.0\",\n" +
                        "    \"account_id\": 2765,\n" +
                        "    \"wallet_id\": 2561,\n" +
                        "    \"service_id\": 3355,\n" +
                        "    \"amount\": 30200,\n" +
                        "    \"amount_currency\": \"INR\",\n" +
                        "    \"fields\": {\n" +
                        "        \"TxnMode\": \"NB\",\n" +
                        "        \"BeneficiaryEmail\": \"test@test.com\",\n" +
                        "        \"BeneficiaryMobile\": \"+919954321125\",\n" +
                        "        \"first_name\": \"John\",\n" +
                        "        \"last_name\": \"Doe\",\n" +
                        "        \"IFSC\": \"ICIC0001818\",\n" +
                        "        \"AccountNo\": \"386789789351111111111111111111111111111111111111\"\n" +
                        "    },\n" +
                        "    \"description\": \"\"\n" +
                        "}");
        paycord.pay_out();
        Thread.sleep(3000);
        Assert.assertEquals(new TransInfoConn(Long.valueOf(paycord.getId())+1).getStatus(),2);
        Assert.assertEquals(new TransInfoCore(Long.valueOf(paycord.getId())).getStatus(),2);
        Assert.assertEquals(new TransInfoCore(Long.valueOf(paycord.getId())+1).getStatus(),2);
        showAgoraURL(paycord.getId());
    }
}
