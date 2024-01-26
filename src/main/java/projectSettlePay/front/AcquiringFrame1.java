package projectSettlePay.front;

public class AcquiringFrame1 extends FrameActivity implements IFrame{

    @Override
    public void positiveSteps() {
        step = 2;
        confirm();
        next();
    }
}

