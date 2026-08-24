package test;

import configDriver.BrowserMode;
import configDriver.WebDriveFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

public abstract class BaseTest {

    private static final Logger logger = LogManager.getLogger(BaseTest.class);

    protected WebDriver driver;
    protected BrowserMode browserMode;

    @BeforeEach
    void setUp() {
        logger.info("Старт теста, режим: {}", browserMode);
        driver = new WebDriveFactory().create(browserMode);
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            logger.info("Закрываю браузер");
            driver.quit();
        }
    }
}