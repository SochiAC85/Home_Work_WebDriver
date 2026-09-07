package test;

import configDriver.ConfigReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import page.LoginPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger(LoginTest.class);

    private static final String USERNAME = ConfigReader.getUsername();
    private static final String PASSWORD = ConfigReader.getPassword();

    @Test
    void testLogin() {
        logger.info("=== Тест: вход в систему ===");
        logger.info("Используем пользователя: {}", USERNAME);

        LoginPage page = new LoginPage(driver);
        page.openLoginPage();

        logger.info("Выполняю вход...");
        page.login(USERNAME, PASSWORD);

        String url = driver.getCurrentUrl();
        logger.info("Текущий URL: {}", url);
        assertTrue(url.contains("wishlist"),
                "Ожидался редирект на страницу списков желаний, но URL: " + url);

        logger.info("=== Тест входа пройден успешно ===");
    }
}