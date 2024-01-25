package projectSettlePay.front;

import projectSettlePay.core.GUIButton;
import projectSettlePay.core.GUITextBox;
import projectSettlePay.core.Locator;

public class PayCashFrame extends FrameActivity implements IFrame{
    @Override
    public void positiveSteps() {
        choiceBank("BBVA");
        submit();
    }
}

