package projectSettlePay.front;

import projectSettlePay.core.GUIButton;
import projectSettlePay.core.Locator;

public class MoneyGramm implements IFrame{

    int step = 1;
    @Override
    public void positiveSteps() {

    }

    public MoneyGramm next(){
        GUIButton button = null;
        if (step == 1) {
            button = new GUIButton(new Locator().xpath("//button[@type=\"submit\"]"));
            step++;
        } else if (step == 2) {
            button = new GUIButton(new Locator().xpath("//div[@class='registration-button-row']/button[@type=\"submit\"]"));
        }
        button.click();
        return this;
    }

    public MoneyGramm confirm(){
        GUIButton button = new GUIButton(new Locator().xpath("//span[text()='Підтвердити']/.."));
        button.click();
        return this;
    }
}
