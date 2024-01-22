package projectSettlePay.core;

public class GUITextBox extends Widget{

    public GUITextBox(Locator locator){
        widgetLocator = locator;
    }
    public void addText(String text){
        getElement().clear();
        getElement().sendKeys(text);
        logger.info("Введен текст ("+text+") - "+widgetLocator.getPath());

    }
}
