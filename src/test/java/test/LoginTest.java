package test;

import configDriver.BrowserMode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import page.LoginPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger(LoginTest.class);

    public LoginTest() {
        browserMode = BrowserMode.KIOSK;
    }

    @Test
    void testLogin() {
        logger.info("Вход в режиме kiosk");

        LoginPage page = new LoginPage(driver);
        page.openLoginPage();
        page.login("Андрей", "qwerty123");

        String url = driver.getCurrentUrl();
        assertTrue(url.contains("wishlist"));
    }
}