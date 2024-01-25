package projectSettlePay.front;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import projectSettlePay.core.GUIButton;
import projectSettlePay.core.GUITextBox;
import projectSettlePay.core.Locator;
import projectSettlePay.core.Session;

public class FourBillFrame implements IFrame {
    private JavascriptExecutor js;
    @Override
    public void positiveSteps(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        setCardNumber("4438231558582693")
                .setExpire("1028")
                .setCvv("111")
                .setEmail("sdfsfd@fs.df")
                .setLast_name("Testovich")
                .setFirst_name("Test")
                .setAddress("Street 10")
                .setCountry("Ukraine")
                .setPhone("0933333333")
                .setCity("Kyiv")
                .setZip_code("123466")
                .submit();
    }

    public FourBillFrame setCardNumber(String pan){
        GUITextBox box = new GUITextBox(new Locator().xpath("//input[@id=\"card_number\"]"));
        for (char c:pan.toCharArray()) {
            box.addText(String.valueOf(c),false);
        }
        return this;
    }

    public FourBillFrame setExpire(String exire){
        GUITextBox box = new GUITextBox(new Locator().xpath("//input[@id=\"cc-exp\"]"));
        for (char c:exire.toCharArray()) {
            box.addText(String.valueOf(c),false);
        }
        return this;
    }

    public FourBillFrame setCvv(String cvv){
        GUITextBox box = new GUITextBox(new Locator().xpath("//input[@name=\"card_cvv\"]"));
        for (char c:cvv.toCharArray()) {
            box.addText(String.valueOf(c),false);
        }
        return this;
    }

    public FourBillFrame setEmail(String email){
        GUITextBox box = new GUITextBox(new Locator().xpath("//input[@id=\"fields.email\"]"));
        for (char c:email.toCharArray()) {
            box.addText(String.valueOf(c),false);
        }
        return this;
    }

    public FourBillFrame setLast_name(String last_name){
        GUITextBox box = new GUITextBox(new Locator().xpath("//input[@id=\"fields.last_name\"]"));
        for (char c:last_name.toCharArray()) {
            box.addText(String.valueOf(c),false);
        }
        return this;
    }

    public FourBillFrame setFirst_name(String first_name){
        GUITextBox box = new GUITextBox(new Locator().xpath("//input[@id=\"fields.first_name\"]"));
        for (char c:first_name.toCharArray()) {
            box.addText(String.valueOf(c),false);
        }
        return this;
    }

    public FourBillFrame setAddress(String address){
        GUITextBox box = new GUITextBox(new Locator().xpath("//input[@id=\"fields.address\"]"));
        for (char c:address.toCharArray()) {
            box.addText(String.valueOf(c),false);
        }
        return this;
    }

    public FourBillFrame setCountry(String country){
        GUITextBox box = new GUITextBox(new Locator().xpath("//input[@id=\"fields.country\"]"));
        for (char c:country.toCharArray()) {
            box.addText(String.valueOf(c),false);
        }
        return this;
    }

    public FourBillFrame setPhone(String phone){
        GUITextBox box = new GUITextBox(new Locator().xpath("//input[@id=\"fields.phone\"]"));
        for (char c:phone.toCharArray()) {
            box.addText(String.valueOf(c),false);
        }
        return this;
    }
    public FourBillFrame setCity(String city){
        GUITextBox box = new GUITextBox(new Locator().xpath("//input[@id=\"fields.city\"]"));
        for (char c:city.toCharArray()) {
            box.addText(String.valueOf(c),false);
        }
        return this;
    }
    public FourBillFrame setZip_code(String zip_code){
        GUITextBox box = new GUITextBox(new Locator().xpath("//input[@id=\"fields.zip_code\"]"));
        for (char c:zip_code.toCharArray()) {
            box.addText(String.valueOf(c),false);
        }
        return this;
    }

    public void submit(){
        GUIButton button = new GUIButton(new Locator().xpath("//button[@type=\"submit\"]"));
        button.click();
    }

}
