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

    public void addText(char [] text){
        getElement().clear();
        for (char x: text){
            getElement().sendKeys(String.valueOf(x));
        }
        logger.info("Введен текст ("+String.valueOf(text)+") - "+widgetLocator.getPath());
    }
}
