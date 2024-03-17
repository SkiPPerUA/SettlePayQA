package projectSettlePay.front;

import projectSettlePay.core.GUIButton;
import projectSettlePay.core.GUITextBox;
import projectSettlePay.core.Locator;

public class BanjoFrame extends FrameActivity implements IFrame{

    @Override
    public void positiveSteps() {
        new GUIButton(new Locator().xpath("//div[text()='UPI']/../../div[@class='pay-method-card']")).click();
        new GUIButton(new Locator().xpath("//button[@class='pay-button move-next-btn']")).click();
        new GUITextBox(new Locator().xpath("//div[@id='#tab-2']//input[@id='brnNumber']")).addText("034023402304");
        new GUIButton(new Locator().xpath("//div[@id='#tab-2']//button[@class='pay-button']")).click();
    }
}

