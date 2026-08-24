package test;

import configDriver.BrowserMode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import page.LoginPage;
import page.WishlistPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class WishlistCreateTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger(WishlistCreateTest.class);

    public WishlistCreateTest() {
        browserMode = BrowserMode.FULLSCREEN;
    }

    @Test
    void testCreateWishlist() {
        logger.info("Создание списка в режиме fullscreen");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.openLoginPage();
        loginPage.login("Андрей", "qwerty123");
        WishlistPage wishlistPage = new WishlistPage(driver);
        wishlistPage.openWishlistPage();
        wishlistPage.createList("Тест Желания3");
        String url = driver.getCurrentUrl();
        assertTrue(url.contains("wishlist"));
    }
}