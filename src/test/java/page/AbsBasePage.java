package page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class AbsBasePage {

    protected final Logger logger = LogManager.getLogger(getClass());
    protected final WebDriver driver;
    protected final WebDriverWait wait;
    private final String baseUrl = System.getProperty("base.url");

    public AbsBasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    protected void open(String path) {
        String url = baseUrl + path;
        logger.info("Открываю: {}", url);
        driver.get(url);
    }

    protected void clickNative(By by) {
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(by));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});", el);
        logger.info("Нативный клик: {}", by);
        el.click();
    }

    protected void clickJS(By by) {
        WebElement el = wait.until(ExpectedConditions.presenceOfElementLocated(by));
        logger.info("JS-клик: {}", by);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
    }
}