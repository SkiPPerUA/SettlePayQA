package projectSettlePay.front;

import org.apache.log4j.Logger;
import projectSettlePay.core.GUIButton;
import projectSettlePay.core.GUITextBox;
import projectSettlePay.core.Locator;

public class AcquiringFrame extends FrameActivity implements IFrame{

    @Override
    public void positiveSteps() {
        step = 2;
        confirm();
        next();
    }
}

