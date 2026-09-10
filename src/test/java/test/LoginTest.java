package test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import page.LoginPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger(LoginTest.class);


    @Test
    void testLogin() {
        logger.info("=== Тест: вход в систему ===");
        logger.info("Используем пользователя: {}", username);

        LoginPage page = new LoginPage(driver);
        page.openLoginPage();

        logger.info("Выполняю вход...");
        page.login(username, password);

        String url = driver.getCurrentUrl();
        logger.info("Текущий URL: {}", url);
        assertTrue(url.contains("wishlist"),
                "Ожидался редирект на страницу списков желаний, но URL: " + url);

        logger.info("=== Тест входа пройден успешно ===");
    }
}