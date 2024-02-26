package projectSettlePay.front;

import projectSettlePay.core.GUIButton;
import projectSettlePay.core.Locator;

public class MoneyGramm extends FrameActivity implements IFrame{
    @Override
    public void positiveSteps() {

    }

    public void next(){
        GUIButton button = null;
        try {
            button = new GUIButton(new Locator().xpath("//span[contains(text(),'Далі')]"));
        } catch (Throwable e){
            button = new GUIButton(new Locator().xpath("//span[contains(text(),'Підтвердити')]"));
        }
        button.click();
    }
}
