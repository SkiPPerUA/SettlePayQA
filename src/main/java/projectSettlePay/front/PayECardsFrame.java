package projectSettlePay.front;

import projectSettlePay.core.GUIButton;
import projectSettlePay.core.GUITextBox;
import projectSettlePay.core.Locator;

public class PayECardsFrame extends FrameActivity implements IFrame{
    @Override
    public void positiveSteps() {
        setCvv("111");
        submit();
    }
}

