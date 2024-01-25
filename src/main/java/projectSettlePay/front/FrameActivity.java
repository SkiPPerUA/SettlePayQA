package projectSettlePay.front;

import projectSettlePay.core.GUIButton;
import projectSettlePay.core.GUITextBox;
import projectSettlePay.core.Locator;

abstract class FrameActivity {

    protected int step = 1;
    public void setCardNumber(String pan){
        GUITextBox box = new GUITextBox(new Locator().xpath("//input[@id=\"card_number\"]"));
        box.addText(pan.toCharArray());
    }

    public void setExpire(String exire){
        GUITextBox box = new GUITextBox(new Locator().xpath("//input[@id=\"cc-exp\"]"));
        box.addText(exire.toCharArray());
    }

    public void setCvv(String cvv){
        GUITextBox box = new GUITextBox(new Locator().xpath("//input[@name=\"card_cvv\"]"));
        box.addText(cvv.toCharArray());
    }

    public void setEmail(String email){
        GUITextBox box = new GUITextBox(new Locator().xpath("//input[@id=\"fields.email\"]"));
        box.addText(email.toCharArray());
    }

    public void setLast_name(String last_name){
        GUITextBox box = new GUITextBox(new Locator().xpath("//input[@id=\"fields.last_name\"]"));
        box.addText(last_name.toCharArray());
    }

    public void setFirst_name(String first_name){
        GUITextBox box = new GUITextBox(new Locator().xpath("//input[@id=\"fields.first_name\"]"));
        box.addText(first_name.toCharArray());
    }

    public void setAddress(String address){
        GUITextBox box = new GUITextBox(new Locator().xpath("//input[@id=\"fields.address\"]"));
        box.addText(address.toCharArray());
    }

    public void setCountry(String country){
        GUITextBox box = new GUITextBox(new Locator().xpath("//input[@id=\"fields.country\"]"));
        box.addText(country.toCharArray());
    }

    public void setPhone(String phone){
        GUITextBox box = new GUITextBox(new Locator().xpath("//input[@id=\"fields.phone\"]"));
        box.addText(phone.toCharArray());
    }
    public void setCity(String city){
        GUITextBox box = new GUITextBox(new Locator().xpath("//input[@id=\"fields.city\"]"));
        box.addText(city.toCharArray());
    }
    public void setZip_code(String zip_code){
        GUITextBox box = new GUITextBox(new Locator().xpath("//input[@id=\"fields.zip_code\"]"));
        box.addText(zip_code.toCharArray());
    }

    public void submit(){
        GUIButton button = new GUIButton(new Locator().xpath("//button[@type=\"submit\"]"));
        button.click();
    }

    public void confirm(){
        GUIButton button = new GUIButton(new Locator().xpath("//label[@for='confirm']"));
        button.click();
    }

    public void next(){
        GUIButton button = null;
        if (step == 1){
            button = new GUIButton(new Locator().xpath("//button[text()='Продовжити'][not(contains(@class,'Disabled'))]"));
        } else if (step == 2) {
            button = new GUIButton(new Locator().xpath("//button[text()='Підтверджую']"));
        }else if (step == 3){
            button = new GUIButton(new Locator().xpath("//div[1]/button[text()='Підтверджую']"));
        }
        button.click();
        step++;
    }

    public void setCard(String card){
        GUITextBox box = new GUITextBox(new Locator().xpath("//input[@name=\"card_number\"]"));
        box.addText(card.toCharArray());
    }

    public void choiceBank(String bankName){
        GUIButton button = new GUIButton(new Locator().xpath("//div[text()='"+bankName+"']"));
        button.click();
    }

    public void otpCode(String otp) {
        GUITextBox button = new GUITextBox(new Locator().xpath("//input[@type='text']"));
        button.addText(otp);
    }
}
