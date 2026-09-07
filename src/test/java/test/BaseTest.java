package test;

import configDriver.WebDriveFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

public abstract class BaseTest {

    private static final Logger logger = LogManager.getLogger(BaseTest.class);

    protected WebDriver driver;
    private final String browserName = System.getProperty("browser", "chrome");

    @BeforeEach
    void setUp() {
        logger.info("Старт теста, браузер: {}", browserName);
        driver = WebDriveFactory.createNewDriver(browserName);
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            logger.info("Закрываю браузер");
            driver.quit();
        }
    }
}