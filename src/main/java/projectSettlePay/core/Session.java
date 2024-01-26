package projectSettlePay.core;

import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Session {
    private static Logger logger = Logger.getLogger(Session.class);
    private static WebDriverWait driverWait;
    private static WebDriver driver;
    public static WebDriver getDriver(){
        if (driverWait == null){
            ChromeDriverManager.getInstance().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            options.addArguments("start-maximized");
            driver = new ChromeDriver(options);
            driverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
            logger.info("Драйвер открыт");
        }
        return driver;
    }

    public static void closeSession(){
        if (driver != null){
            driver.close();
            driver.quit();
            driver = null;
            logger.info("Драйвер закрыт");
        }
    }

    public static WebDriverWait getDriverWait(){
        return driverWait;
    }
}
