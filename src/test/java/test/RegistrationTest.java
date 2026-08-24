package test;

import configDriver.BrowserMode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import page.LoginPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegistrationTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger(RegistrationTest.class);

    public RegistrationTest() {
        browserMode = BrowserMode.HEADLESS;
    }

    @Test
    void testRegistration() {
        logger.info("Регистрация в режиме headless");

        LoginPage page = new LoginPage(driver);
        page.openRegisterPage();
        page.register("Андрей", "sochiac@gmail.com", "qwerty123");

        String url = driver.getCurrentUrl();
        assertTrue(url.contains("wishlist") || url.contains("login"));
    }
}