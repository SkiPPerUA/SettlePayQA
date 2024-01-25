package projectSettlePay.core;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

abstract public class Widget {

    protected static Logger logger = Logger.getLogger(Widget.class);
    protected Locator widgetLocator;
    protected WebElement element;
    protected WebElement getElement(){
        if(element == null){
            try {
                element = Session.getDriverWait().until(ExpectedConditions.visibilityOfElementLocated(widgetLocator.getLocator()));
            }catch (Throwable e){
                logger.error("Елемент не найден - "+widgetLocator.getPath());
            }
        }
        return element;
    }
}
