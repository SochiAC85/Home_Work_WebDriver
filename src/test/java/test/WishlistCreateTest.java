package test;

import configDriver.ConfigReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import page.LoginPage;
import page.WishlistPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class WishlistCreateTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger(WishlistCreateTest.class);

    private static final String USERNAME = ConfigReader.getUsername();
    private static final String PASSWORD = ConfigReader.getPassword();
    private static final String LIST_NAME = "Тест Желания3";

    @Test
    void testCreateWishlist() {
        logger.info("=== Тест: создание списка желаний ===");
        logger.info("Используем пользователя: {}", USERNAME);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.openLoginPage();

        logger.info("Выполняю вход...");

        loginPage.login(USERNAME, PASSWORD);

        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("wishlist"),
                "Ожидался редирект на страницу списков, но URL: " + currentUrl);

        WishlistPage wishlistPage = new WishlistPage(driver);
        wishlistPage.openWishlistPage();
        wishlistPage.createList(LIST_NAME);

        wishlistPage.assertListDisplayed(LIST_NAME);

        logger.info("=== Тест пройден: список '{}' успешно создан и отображается ===", LIST_NAME);
    }
}