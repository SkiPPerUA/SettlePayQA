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
import projectSettlePay.helper.BodyHelper;

@Test
public class PayPortPayIn extends BaseTest {

    PayPort payPort;

    public void successPayIn_filterNull(){
        setBD("null");
        payPort = new PayPort(getBody(true));
        payPort.pay_in();
        Session.getDriver().get(payPort.getPayURL());
        payPort.frame.choiceBank("Bank of Georgia");
        payPort.frame.next();
        payPort.frame.positiveSteps();
    }

    public void negativePayIn_filterNull(){
        setBD("null");
        payPort = new PayPort(getBody(true));
        payPort.pay_in();
        Session.getDriver().get(payPort.getPayURL());
        payPort.frame.choiceBank("Bank of Georgia");
        payPort.frame.next();
        payPort.frame.negativeSteps();
    }

    public void successPayIn_filterBankofGeorgia() throws InterruptedException {
        setBD("{\"filter_payment_systems\": {\"GEL\": \"Bank of Georgia\"}}");
        payPort = new PayPort(getBody(true));
        payPort.pay_in();
        Session.getDriver().get(payPort.getPayURL());
        Thread.sleep(5000);
        payPort.frame.positiveSteps();
    }

    public void successPayIn_filterTBCBank() throws InterruptedException {
        setBD("{\"filter_payment_systems\": {\"GEL\": \"TBC Bank\"}}");
        payPort = new PayPort(getBody(true));
        payPort.pay_in();
        Session.getDriver().get(payPort.getPayURL());
        Thread.sleep(5000);
        payPort.frame.positiveSteps();
    }

    public void successPayIn_filterTBCBank_withCent() throws InterruptedException {
        setBD("{\"filter_payment_systems\": {\"GEL\": \"TBC Bank\"}}");
        payPort = new PayPort("{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "    \"locale\": \"ua\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"external_customer_id\": \""+BodyHelper.generateUUID() +"\",\n" +
                "    \"account_id\": 2761,\n" +
                "    \"wallet_id\": 3433, \n" +
                "    \"service_id\": 3966,\n" +
                "    \"amount\": 3099,\n" +
                "    \"amount_currency\": \"GEL\",\n" +
                "    \"fields\": {\n" +
                "        \"first_name\": \"Andromeda\",\n" +
                "        \"last_name\": \"Galazy\",\n" +
                "        \"email\": \"andromeda@gmail.com\"\n" +
                "    }\n" +
                "}\n");
        payPort.pay_in();
        Session.getDriver().get(payPort.getPayURL());
        Thread.sleep(5000);
        payPort.frame.positiveSteps();
    }

    public void successPayIn_filterNull_withCent(){
        setBD("null");
        payPort = new PayPort("{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "    \"locale\": \"ua\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"external_customer_id\": \""+BodyHelper.generateUUID()+"\",\n" +
                "    \"account_id\": 2761,\n" +
                "    \"wallet_id\": 3433,\n" +
                "    \"service_id\": 3966,\n" +
                "    \"amount\": 3099,\n" +
                "    \"amount_currency\": \"GEL\",\n" +
                "    \"fields\": {\n" +
                "        \"first_name\": \"Andromeda\",\n" +
                "        \"last_name\": \"Galazy\",\n" +
                "        \"email\": \"andromeda@gmail.com\"\n" +
                "    }\n" +
                "}\n");
        payPort.pay_in();
        Session.getDriver().get(payPort.getPayURL());
        payPort.frame.choiceBank("Bank of Georgia");
        payPort.frame.next();
        payPort.frame.positiveSteps();
    }

    public void cancel_on_FIO_page(){
        setBD("null");
        payPort = new PayPort(String.format(getBody(false),"\"fields\": {\n" +
                "        \"email\": \"andromeda@gmail.com\"\n" +
                "    }"));
        payPort.pay_in();
        Session.getDriver().get(payPort.getPayURL());
        payPort.frame.choiceBank("Bank of Georgia");
        payPort.frame.next();
        payPort.frame.cancel();
        payPort.frame.confirmCancel();
    }

    public void negativePayIn_filterNull_not_send_otp(){
        setBD("null");
        payPort = new PayPort(getBody(true));
        payPort.pay_in();
        Session.getDriver().get(payPort.getPayURL());
        payPort.frame.choiceBank("Bank of Georgia");
        payPort.frame.next();
        payPort.frame.confirm();
        payPort.frame.next();
    }

    public void expiredPayIn_not_open_payment_utl(){
        payPort = new PayPort(getBody(true));
        payPort.pay_in();
    }

    public void expiredPayIn_not_confirm(){
        setBD("null");
        payPort = new PayPort(getBody(true));
        payPort.pay_in();
        Session.getDriver().get(payPort.getPayURL());
        payPort.frame.choiceBank("Bank of Georgia");
        payPort.frame.next();
    }

    public void expiredPayIn_not_confirm_with_cents(){
        setBD("null");
        payPort = new PayPort("{\n" +
                "    \"auth\": {\n" +
                "        \"debug\": true,\n" +
                "        \"point\": 2755,\n" +
                "        \"key\": 1,\n" +
                "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                "    },\n" +
                "    \"locale\": \"ua\",\n" +
                "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                "    \"external_customer_id\": \""+BodyHelper.generateUUID()+"\",\n" +
                "    \"account_id\": 2761,\n" +
                "    \"wallet_id\": 3433,\n" +
                "    \"service_id\": 3966,\n" +
                "    \"amount\": 3099,\n" +
                "    \"amount_currency\": \"GEL\",\n" +
                "    \"fields\": {\n" +
                "        \"first_name\": \"Andromeda\",\n" +
                "        \"last_name\": \"Galazy\",\n" +
                "        \"email\": \"andromeda@gmail.com\"\n" +
                "    }\n" +
                "}\n");
        payPort.pay_in();
        Session.getDriver().get(payPort.getPayURL());
        payPort.frame.choiceBank("Bank of Georgia");
        payPort.frame.next();
    }

    public void expiredPayIn_close_on_confirm_page(){
        setBD("null");
        payPort = new PayPort(getBody(true));
        payPort.pay_in();
        Session.getDriver().get(payPort.getPayURL());
        payPort.frame.choiceBank("Bank of Georgia");
        payPort.frame.next();
        payPort.frame.confirm();
        payPort.frame.next();
        payPort.frame.otpCode("1234");
    }

    public void expiredPayIn_after_open_payment_url(){
        setBD("null");
        payPort = new PayPort(getBody(true));
        payPort.pay_in();
        Session.getDriver().get(payPort.getPayURL());
        payPort.frame.choiceBank("Bank of Georgia");
    }

    @Test(dataProvider = "amountNegative")
    public void checkAmount(String amount){
            payPort = new PayPort("{\\n\" +\n" +
                    "                    \"    \\\"auth\\\": {\\n\" +\n" +
                    "                    \"        \\\"debug\\\": true,\\n\" +\n" +
                    "                    \"        \\\"point\\\": 2755,\\n\" +\n" +
                    "                    \"        \\\"key\\\": 1,\\n\" +\n" +
                    "                    \"        \\\"hash\\\": \\\"23890751a791b6936b547972fc0cde42\\\"\\n\" +\n" +
                    "                    \"    },\\n\" +\n" +
                    "                    \"    \\\"locale\\\": \\\"ua\\\",\\n\" +\n" +
                    "                    \"    \\\"customer_ip_address\\\": \\\"0.0.0.0\\\",\\n\" +\n" +
                    "                    \"    \\\"external_customer_id\\\": \\\""+ BodyHelper.generateUUID() +"\\\",\\n\" +\n" +
                    "                    \"    \\\"account_id\\\": 2761,\\n\" +\n" +
                    "                    \"    \\\"wallet_id\\\": 3434,\\n\" +\n" +
                    "                    \"    \\\"service_id\\\": 3966,\\n\" +\n" +
                    "                    \"    \\\"amount\\\": "+amount+",\\n\" +\n" +
                    "                    \"    \\\"amount_currency\\\": \\\"GEL\\\",\\n\" +\n" +
                    "                    \"    \\\"fields\\\": {\\n\" +\n" +
                    "                    \"        \\\"first_name\\\": \\\"Andromeda\\\",\\n\" +\n" +
                    "                    \"        \\\"last_name\\\": \\\"Galazy\\\",\\n\" +\n" +
                    "                    \"        \\\"email\\\": \\\"andromeda@gmail.com\\\"\\n\" +\n" +
                    "                    \"    }\\n\" +\n" +
                    "                    \"}");
            payPort.pay_in();
            Assert.assertTrue(payPort.getResponse().contains("\"Bad request\""));
    }

    @Test(dataProvider = "name_Negative")
    public void checkFirst_name(String first_name){
        try {
            setBD("null");
            payPort = new PayPort(String.format(getBody(false),"   \"fields\": {\n" +
                    "        \"first_name\": \""+first_name+"\",\n" +
                    "        \"last_name\": \"Galazy\",\n" +
                    "        \"email\": \"andromeda@gmail.com\"\n" +
                    "    }"));
            payPort.pay_in();
            Session.getDriver().get(payPort.getPayURL());
            payPort.frame.choiceBank("Bank of Georgia");
            payPort.frame.next();
            if (first_name.toCharArray().length < 1) {
                payPort.frame.setFirst_name("FirstName");
                new GUIButton(new Locator().xpath("//div[2]/button[text()='Продовжити'][not(contains(@class,'Disabled'))]")).click();
            }
            payPort.frame.positiveSteps();
        }finally {
            Session.closeSession();
        }

    }

    @Test(dataProvider = "name_Negative")
    public void checkLast_name(String name){
        try {
            payPort = new PayPort(String.format(getBody(false),"   \"fields\": {\n" +
                    "        \"first_name\": \"Andromeda\",\n" +
                    "        \"last_name\": \""+name+"\",\n" +
                    "        \"email\": \"andromeda@gmail.com\"\n" +
                    "    }"));
            payPort.pay_in();
            Session.getDriver().get(payPort.getPayURL());
            payPort.frame.choiceBank("Bank of Georgia");
            payPort.frame.next();
            if (name.toCharArray().length < 1) {
                payPort.frame.setLast_name("LastName");
                new GUIButton(new Locator().xpath("//div[2]/button[text()='Продовжити'][not(contains(@class,'Disabled'))]")).click();
            }
            payPort.frame.positiveSteps();
    }finally {
        Session.closeSession();
    }
    }

    public void without_firstName(){
            setBD("null");
            payPort = new PayPort(String.format(getBody(false),"   \"fields\": {\n" +
                    "        \"last_name\": \"Galazy\",\n" +
                    "        \"email\": \"andromeda@gmail.com\"\n" +
                    "    }"));
            payPort.pay_in();
            Session.getDriver().get(payPort.getPayURL());
            payPort.frame.choiceBank("Bank of Georgia");
            payPort.frame.next();
            payPort.frame.setFirst_name("FirstName");
            new GUIButton(new Locator().xpath("//div[2]/button[text()='Продовжити'][not(contains(@class,'Disabled'))]")).click();
            payPort.frame.positiveSteps();
    }

    public void without_lastName(){
            setBD("null");
            payPort = new PayPort(String.format(getBody(false),"   \"fields\": {\n" +
                    "        \"first_name\": \"Galazy\",\n" +
                    "        \"email\": \"andromeda@gmail.com\"\n" +
                    "    }"));
            payPort.pay_in();
            Session.getDriver().get(payPort.getPayURL());
            payPort.frame.choiceBank("Bank of Georgia");
            payPort.frame.next();
            payPort.frame.setLast_name("LastName");
            new GUIButton(new Locator().xpath("//div[2]/button[text()='Продовжити'][not(contains(@class,'Disabled'))]")).click();
            payPort.frame.positiveSteps();
    }

    public void without_firstName_and_lastName(){
            setBD("null");
            payPort = new PayPort(String.format(getBody(false),"   \"fields\": {\n" +
                    "        \"email\": \"andromeda@gmail.com\"\n" +
                    "    }"));
            payPort.pay_in();
            Session.getDriver().get(payPort.getPayURL());
            payPort.frame.choiceBank("Bank of Georgia");
            payPort.frame.next();
            payPort.frame.setFirst_name("FirstName");
            payPort.frame.setLast_name("LastName");
            new GUIButton(new Locator().xpath("//div[2]/button[text()='Продовжити'][not(contains(@class,'Disabled'))]")).click();
            payPort.frame.positiveSteps();
    }

    public void without_email(){
            setBD("null");
            payPort = new PayPort(String.format(getBody(false),"   \"fields\": {\n" +
                    "        \"first_name\": \"Andromeda\",\n" +
                    "        \"last_name\": \"Galazy\"\n" +
                    "    }"));
            payPort.pay_in();
            Session.getDriver().get(payPort.getPayURL());
            payPort.frame.choiceBank("Bank of Georgia");
            payPort.frame.next();
            payPort.frame.setEmail("fsdf@df.da");
            new GUIButton(new Locator().xpath("//div[2]/button[text()='Продовжити'][not(contains(@class,'Disabled'))]")).click();
            payPort.frame.positiveSteps();
    }

    public void without_fields(){
            setBD("null");
            payPort = new PayPort("{\n" +
                    "    \"auth\": {\n" +
                    "        \"debug\": true,\n" +
                    "        \"point\": 2755,\n" +
                    "        \"key\": 1,\n" +
                    "        \"hash\": \"23890751a791b6936b547972fc0cde42\"\n" +
                    "    },\n" +
                    "    \"locale\": \"ua\",\n" +
                    "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                    "    \"external_customer_id\": \""+BodyHelper.generateUUID()+"\",\n" +
                    "    \"account_id\": 2761,\n" +
                    "    \"wallet_id\": 3433,\n" +
                    "    \"service_id\": 3966,\n" +
                    "    \"amount\": 3099,\n" +
                    "    \"amount_currency\": \"GEL\"\n" +
                    "}");
            payPort.pay_in();
            Session.getDriver().get(payPort.getPayURL());
            payPort.frame.setFirst_name("FirstName");
            payPort.frame.setLast_name("LastName");
            new GUIButton(new Locator().xpath("//div[2]/button[text()='Продовжити'][not(contains(@class,'Disabled'))]")).click();
            payPort.frame.positiveSteps();
    }

    public void fields_is_empty(){
        setBD("null");
        payPort = new PayPort(String.format(getBody(false),"    \"fields\": {}"));
        payPort.pay_in();
        Session.getDriver().get(payPort.getPayURL());
        payPort.frame.setLast_name("LastName");
        payPort.frame.setFirst_name("FirstName");
        new GUIButton(new Locator().xpath("//div[2]/button[text()='Продовжити'][not(contains(@class,'Disabled'))]")).click();
        payPort.frame.positiveSteps();
    }
    @Test(dataProvider = "emailNegative")
    public void email_check(String email){
        try {
            payPort = new PayPort(String.format(getBody(false),"   \"fields\": {\n" +
                    "        \"first_name\": \"Andromeda\",\n" +
                    "        \"last_name\": \"Galazy\",\n" +
                    "        \"email\": \""+email+"\"\n" +
                    "    }"));
            payPort.pay_in();
            Session.getDriver().get(payPort.getPayURL());
            payPort.frame.choiceBank("Bank of Georgia");
            payPort.frame.next();
            if (email.toCharArray().length < 1) {
                payPort.frame.setEmail("email@gmail.com");
                new GUIButton(new Locator().xpath("//div[2]/button[text()='Продовжити'][not(contains(@class,'Disabled'))]")).click();
            }
            payPort.frame.positiveSteps();
        }finally {
            Session.closeSession();
        }
    }

    @Test(dataProvider = "otp")
    public void checkOtp(String opt){
        try {
            setBD("null");
            payPort = new PayPort(getBody(true));
            payPort.pay_in();
            Session.getDriver().get(payPort.getPayURL());
            payPort.frame.choiceBank("Bank of Georgia");
            payPort.frame.next();
            payPort.frame.confirm();
            payPort.frame.next();
            payPort.frame.otpCode(opt);
            payPort.frame.next();
            Session.getDriverWait().until(ExpectedConditions.visibilityOfElementLocated(new Locator().xpath("//div[text()='Неправильний формат']").getLocator()));
        }catch (Throwable e){
            System.out.println(getAgoraURL(payPort.getId()));
        }finally {
            Session.closeSession();
        }

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
                    "    \"locale\": \"ua\",\n" +
                    "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                    "    \"external_customer_id\": \""+ BodyHelper.generateUUID() +"\",\n" +
                    "    \"account_id\": 2761,\n" +
                    "    \"wallet_id\": 3434,\n" +
                    "    \"service_id\": 3966,\n" +
                    "    \"amount\": 3000,\n" +
                    "    \"amount_currency\": \"GEL\",\n" +
                    "    \"fields\": {\n" +
                    "        \"first_name\": \"Andromeda\",\n" +
                    "        \"last_name\": \"Galazy\",\n" +
                    "        \"email\": \"andromeda@gmail.com\"\n" +
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
                    "    \"locale\": \"ua\",\n" +
                    "    \"customer_ip_address\": \"0.0.0.0\",\n" +
                    "    \"external_customer_id\": \""+ BodyHelper.generateUUID() +"\",\n" +
                    "    \"account_id\": 2761,\n" +
                    "    \"wallet_id\": 3434,\n" +
                    "    \"service_id\": 3966,\n" +
                    "    \"amount\": 3000,\n" +
                    "    \"amount_currency\": \"GEL\",\n" +
                    "    %s \n"+
                    "}";
        }
    }

    private void setBD(String settings){
        getDataBase(CONN_STAGE_1).updateSql("update providers set settings = '"+settings+"' where id = 1005");
    }

    @DataProvider
    private Object [][] emailNegative(){return new Object[][] {{""},{"fdsd@fdsd"},{"fsdsfd.fsd"},{"fsdsfd@f.d"},{"fsd fsd@ffsd.fsd"},{"fsd!#$@#fsd@ffsd.fsd"}};}

    @DataProvider
    private Object [][] otp(){return new Object[][] {{"    "},{"dfvf"},{"23423"},{"23"}};}

    @DataProvider
    private Object [][] name_Negative(){return new Object[][] {{""},{"Nfsdd12432423"},{"Nfsdd#%@#$^#%&"}};}

    @DataProvider
    private Object [][] amountNegative(){return new Object[][] {{"1"},{"3400000000000"}};}
    @AfterMethod
    void close(){
        try {
            System.out.println(getAgoraURL(payPort.getId()));
        }catch (Throwable e){}finally {
            Session.closeSession();
        }
    }
}
