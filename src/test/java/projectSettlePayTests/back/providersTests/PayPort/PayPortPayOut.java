package projectSettlePayTests.back.providersTests.PayPort;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import projectSettlePay.BaseTest;
import projectSettlePay.back.providers.PayPort;
import projectSettlePay.core.GUIButton;
import projectSettlePay.core.Locator;
import projectSettlePay.core.Session;
import projectSettlePay.helper.UuidGenerate;

@Test
public class PayPortPayOut extends BaseTest {

    PayPort payPort;

    public void successPayOut_filterNull(){
        setBD("null");
        payPort = new PayPort(getBody(true));
        payPort.pay_out();
    }

    public void successPayOut_filterNull_amountWithCents(){
        setBD("null");
        payPort = new PayPort("{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "    \"locale\": \"en\",\n" +
                "    \"external_order_id\": \"954345387\",\n" +
                "    \"external_customer_id\":  \""+UuidGenerate.generateUUID()+"\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"account_id\": 2761,\n" +
                "    \"wallet_id\": 3433,\n" +
                "    \"service_id\": 3967,\n" +
                "    \"amount\": 30044,\n" +
                "    \"amount_currency\": \"GEL\",\n" +
                "    \"fields\": {\n" +
                "    \"card_number\": \"4000000011112222\"\n" +
                "    }\n" +
                "}");
        payPort.pay_out();
    }

    public void successPayOut_filterBankofGeorgia(){
        setBD("{\"filter_payment_systems\": {\"GEL\": \"Bank of Georgia\"}}");
        payPort = new PayPort(getBody(true));
        payPort.pay_out();
    }

    public void successPayOut_filterTBCBank(){
        setBD("{\"filter_payment_systems\": {\"GEL\": \"TBC Bank\"}}");
        payPort = new PayPort(getBody(true));
        payPort.pay_out();
    }

    public void successPayOut_filterBankofGeorgia_amountWithCents(){
        setBD("{\"filter_payment_systems\": {\"GEL\": \"Bank of Georgia\"}}");
        payPort = new PayPort("{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "    \"locale\": \"en\",\n" +
                "    \"external_order_id\": \"954345387\",\n" +
                "    \"external_customer_id\":  \""+UuidGenerate.generateUUID()+"\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"account_id\": 2761,\n" +
                "    \"wallet_id\": 3433,\n" +
                "    \"service_id\": 3967,\n" +
                "    \"amount\": 30044,\n" +
                "    \"amount_currency\": \"GEL\",\n" +
                "    \"fields\": {\n" +
                "    \"card_number\": \"4000000011112222\"\n" +
                "    }\n" +
                "}");
        payPort.pay_out();
    }

    public void successPayOut_filterTBCBank_amountWithCents(){
        setBD("{\"filter_payment_systems\": {\"GEL\": \"TBC Bank\"}}");
        payPort = new PayPort("{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "    \"locale\": \"en\",\n" +
                "    \"external_order_id\": \"954345387\",\n" +
                "    \"external_customer_id\":  \""+UuidGenerate.generateUUID()+"\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"account_id\": 2761,\n" +
                "    \"wallet_id\": 3433,\n" +
                "    \"service_id\": 3967,\n" +
                "    \"amount\": 30044,\n" +
                "    \"amount_currency\": \"GEL\",\n" +
                "    \"fields\": {\n" +
                "    \"card_number\": \"4000000011112222\"\n" +
                "    }\n" +
                "}");
        payPort.pay_out();
    }

    public void negativePayOut_filterNegative_amountWithCents(){
        setBD("{\"filter_payment_systems\": {\"AZN\": \"Unibank\"}}");

        payPort = new PayPort("{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "    \"locale\": \"en\",\n" +
                "    \"external_order_id\": \"954345387\",\n" +
                "    \"external_customer_id\":  \""+UuidGenerate.generateUUID()+"\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"account_id\": 2761,\n" +
                "    \"wallet_id\": 3433,\n" +
                "    \"service_id\": 3967,\n" +
                "    \"amount\": 30100,\n" +
                "    \"amount_currency\": \"GEL\",\n" +
                "    \"fields\": {\n" +
                "    \"card_number\": \"4000000011112222\"\n" +
                "    }\n" +
                "}");
        payPort.pay_out();
    }

    @Test(dataProvider = "amountNegative")
    public void checkAmount(String amount){
        setBD("null");
        payPort = new PayPort("{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "    \"locale\": \"en\",\n" +
                "    \"external_order_id\": \"954345387\",\n" +
                "    \"external_customer_id\":  \""+UuidGenerate.generateUUID()+"\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"account_id\": 2761,\n" +
                "    \"wallet_id\": 3433,\n" +
                "    \"service_id\": 3967,\n" +
                "    \"amount\": "+amount+",\n" +
                "    \"amount_currency\": \"GEL\",\n" +
                "    \"fields\": {\n" +
                "    \"card_number\": \"4000000011112222\"\n" +
                "    }\n" +
                "}");
        payPort.pay_out();
    }

    @Test(dataProvider = "card_Negative")
    public void checkCard(String card){
        setBD("null");
        payPort = new PayPort("{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "    \"locale\": \"en\",\n" +
                "    \"external_order_id\": \"954345387\",\n" +
                "    \"external_customer_id\":  \""+UuidGenerate.generateUUID()+"\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"account_id\": 2761,\n" +
                "    \"wallet_id\": 3433,\n" +
                "    \"service_id\": 3967,\n" +
                "    \"amount\": 3000,\n" +
                "    \"amount_currency\": \"GEL\",\n" +
                "    \"fields\": {\n" +
                "    \"card_number\": \""+card+"\"\n" +
                "    }\n" +
                "}");
        payPort.pay_out();
    }

    public void without_Card(){
        setBD("null");
        payPort = new PayPort("{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "    \"locale\": \"en\",\n" +
                "    \"external_order_id\": \"954345387\",\n" +
                "    \"external_customer_id\":  \""+UuidGenerate.generateUUID()+"\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"account_id\": 2761,\n" +
                "    \"wallet_id\": 3433,\n" +
                "    \"service_id\": 3967,\n" +
                "    \"amount\": 3000,\n" +
                "    \"amount_currency\": \"GEL\"\n" +
                "}");
        payPort.pay_out();
    }

    private void setBD(String settings){
        getDataBase(CONN_STAGE_1).updateSql("update providers set settings = '"+settings+"' where id = 1006");
    }

    private String getBody(boolean withFields){
        if (withFields){
            return "{\n" +
                    "    \"auth\": {\n" +
                    "        \"debug\": true,\n" +
                    "        \"point\": 2755,\n" +
                    "        \"key\": 1,\n" +
                    "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                    "    },\n" +
                    "    \"locale\": \"en\",\n" +
                    "    \"external_order_id\": \"954345387\",\n" +
                    "    \"external_customer_id\":  \""+UuidGenerate.generateUUID()+"\",\n" +
                    "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                    "    \"account_id\": 2761,\n" +
                    "    \"wallet_id\": 3433,\n" +
                    "    \"service_id\": 3967,\n" +
                    "    \"amount\": 30000,\n" +
                    "    \"amount_currency\": \"GEL\",\n" +
                    "    \"fields\": {\n" +
                    "    \"card_number\": \"4000000011112222\"\n" +
                    "    }\n" +
                    "}";
        }else {
            return "{\n" +
                    "    \"auth\": {\n" +
                    "        \"debug\": true,\n" +
                    "        \"point\": 2755,\n" +
                    "        \"key\": 1,\n" +
                    "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                    "    },\n" +
                    "    \"locale\": \"en\",\n" +
                    "    \"external_order_id\": \"954345387\",\n" +
                    "    \"external_customer_id\":  \""+UuidGenerate.generateUUID()+"\",\n" +
                    "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                    "    \"account_id\": 2761,\n" +
                    "    \"wallet_id\": 3433,\n" +
                    "    \"service_id\": 3967,\n" +
                    "    \"amount\": 30000,\n" +
                    "    \"amount_currency\": \"GEL\",\n" +
                    "}";
        }
    }
    @DataProvider
    private Object [][] card_Negative(){return new Object[][] {{""},{"fsdfsdf"}};}
    @DataProvider
    private Object [][] amountNegative(){return new Object[][] {{"1"},{"3400000000000"},{"20000000"}};}
    @AfterMethod
    void close(){
        try {
            System.out.println(getAgoraURL(payPort.getId()));
        }catch (Throwable e){}
    }
}
