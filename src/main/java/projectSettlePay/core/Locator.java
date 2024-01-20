package projectSettlePay.core;

import org.openqa.selenium.By;

public class Locator {

    private By wdLocator;
    private String path;

    public Locator xpath(String locator){
        setPath(locator);
        wdLocator = By.xpath(locator);
        return this;
    }

    public Locator css(String locator){
        setPath(locator);
        wdLocator = By.cssSelector(locator);
        return this;
    }

    private void setPath(String path){
        this.path = path;
    }

    public By getLocator(){
        return wdLocator;
    }

    public String getPath(){
        return path;
    }
}
