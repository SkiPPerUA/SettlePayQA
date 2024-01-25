package projectSettlePay.front;

import projectSettlePay.core.GUIButton;
import projectSettlePay.core.GUITextBox;
import projectSettlePay.core.Locator;

public class PrimePayFrame extends FrameActivity implements IFrame{

    @Override
    public void positiveSteps() {
        setCard("4111111111111111");
        setExpire("1028");
        setCvv("111");
        submit();
    }
}

