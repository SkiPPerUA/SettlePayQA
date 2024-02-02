package projectSettlePay.front;

public class P2PFrame extends FrameActivity implements IFrame{

    @Override
    public void positiveSteps() {
        step = 2;
        confirm();
        next();
        otpCode("1234");
        next();
    }

    public void negativeSteps() {
        step = 2;
        confirm();
        next();
        otpCode("123");
        next();
    }
}

