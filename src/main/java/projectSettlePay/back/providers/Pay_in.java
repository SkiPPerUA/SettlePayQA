package projectSettlePay.back.providers;

import projectSettlePay.front.IFrame;

public interface Pay_in extends IProviders{

    void pay_in();

    String getPayURL();

    IFrame getFrame();
}
