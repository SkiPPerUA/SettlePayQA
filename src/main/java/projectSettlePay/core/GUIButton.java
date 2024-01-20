package projectSettlePay.core;

public class GUIButton extends Widget{

    public GUIButton(Locator locator){
        widgetLocator = locator;
    }
    public void click(){
        getElement().click();
        logger.info("Клик на кнопку - "+widgetLocator.getPath());

    }
}
