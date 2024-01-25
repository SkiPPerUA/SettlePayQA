package projectSettlePay.front;

import projectSettlePay.core.GUIButton;
import projectSettlePay.core.Locator;

public class MoneyGramm extends FrameActivity implements IFrame{
    @Override
    public void positiveSteps() {

    }

    public void next(){
        GUIButton button = null;
        if (step == 1) {
            button = new GUIButton(new Locator().xpath("//button[@type=\"submit\"]"));
            step++;
        } else if (step == 2) {
            button = new GUIButton(new Locator().xpath("//div[@class='registration-button-row']/button[@type=\"submit\"]"));
        }
        button.click();
    }
}
