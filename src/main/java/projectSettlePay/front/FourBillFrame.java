package projectSettlePay.front;

public class FourBillFrame extends FrameActivity implements IFrame {
    @Override
    public void positiveSteps(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        setCardNumber("4438231558582693");
        setExpire("1028");
        setCvv("111");
        setEmail("sdfsfd@fs.df");
        setLast_name("Testovich");
        setFirst_name("Test");
        setAddress("Street 10");
        setCountry("Ukraine");
        setPhone("0933333333");
        setCity("Kyiv");
        setZip_code("123466");
        submit();
    }

}
