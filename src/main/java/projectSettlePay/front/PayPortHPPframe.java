package projectSettlePay.front;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import projectSettlePay.core.GUIButton;
import projectSettlePay.core.GUITextBox;
import projectSettlePay.core.Locator;
import projectSettlePay.core.Session;
import projectSettlePay.helper.UuidGenerate;

public class PayPortHPPframe extends FrameActivity implements IFrame{
    @Override
    public void positiveSteps() {
        steps();
        GUIButton confirm = new GUIButton(new Locator().xpath("//button[contains(@class,'btn-confirm')][@name]"));
        confirm.scrollToElement();
        confirm.click();
        new GUIButton(new Locator().xpath("//div[@class='blockFormBodyBtn d-flex justify-content-between']/button[contains(@class,'btn-confirm')]")).click();
    }

    public void positiveStepsPayOut() {
        new GUITextBox(new Locator().xpath("//input[@id=\"message\"]")).addText("80933994455");
        new GUITextBox(new Locator().xpath("//input[@id=\"payment_info\"]")).addText("Vlad test");
        submit();
    }

    public void cancelSteps() {
        steps();
        new GUIButton(new Locator().xpath("//button[contains(@class,'btn-cancel')]")).click();
    }

    private void steps(){
        new GUITextBox(new Locator().xpath("//input[@id=\"phone_number\"]")).addText("80933994455");
        submit();
        new GUITextBox(new Locator().xpath("//input[@id=\"essential_key\"]")).addText("80933994455");
        new GUITextBox(new Locator().xpath("//input[@id=\"additional_essential_key\"]")).addText(UuidGenerate.generateUUID());
    }
}
