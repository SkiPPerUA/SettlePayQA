package projectSettlePay.front;

public class AcquiringFrame extends FrameActivity implements IFrame{

    @Override
    public void positiveSteps() {
        setCard("4111111111111111");
        setExpire("1028");
        setCvv("111");
        submit();
    }
}

