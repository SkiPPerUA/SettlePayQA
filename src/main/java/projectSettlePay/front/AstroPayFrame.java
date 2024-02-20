package projectSettlePay.front;

import projectSettlePay.core.GUIButton;
import projectSettlePay.core.GUITextBox;
import projectSettlePay.core.Locator;

public class AstroPayFrame implements IFrame{
    @Override
    public void positiveSteps() {
        GUITextBox phone = new GUITextBox(new Locator().xpath("//input[@name=\"phone\"]"));
        phone.addText("43233431235");
        new GUIButton(new Locator().xpath("//button[@id=\"btn-auth-continue\"]")).click();
        for (int i = 0; i < 6; i++){
            new GUITextBox(new Locator().xpath("//input[@data-id=\""+i+"\"]")).addText("0");
        }
        new GUIButton(new Locator().xpath("//button[@id=\"ap-deposit-wallet-pay\"]")).click();
        new GUIButton(new Locator().xpath("//img[@icon=\"success\"]")).getElement();
    }
}
