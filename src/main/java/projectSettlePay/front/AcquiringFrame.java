package projectSettlePay.front;

import org.apache.log4j.Logger;
import projectSettlePay.core.GUIButton;
import projectSettlePay.core.GUITextBox;
import projectSettlePay.core.Locator;

public class AcquiringFrame implements IFrame{
    
    private int step = 1;
    public AcquiringFrame choiceBank(String bankName){
        GUIButton button = new GUIButton(new Locator().xpath("//div[text()='"+bankName+"']"));
        button.click();
        return this;
    }

    public AcquiringFrame choiceBank(int bankName){
        GUIButton button = new GUIButton(new Locator().xpath(""));
        button.click();
        return this;
    }

    public AcquiringFrame next(){
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
        return this;
    }

    public AcquiringFrame confirm(){
        GUIButton button = new GUIButton(new Locator().xpath("//label[@for='confirm']"));
        button.click();
        return this;
    }

    public AcquiringFrame otpCode(String otp){
        GUITextBox button = new GUITextBox(new Locator().xpath("//input[@type='text']"));
        button.addText(otp);
        return this;}

    @Override
    public void positiveSteps() {
        step = 2;
        confirm();
        next();
    }
}

