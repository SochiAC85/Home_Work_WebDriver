package test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import page.LoginPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegistrationTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger(RegistrationTest.class);


    @Test
    void testRegistration() {

        String email = System.getProperty("email");
        if (email == null || email.isBlank()){
            throw new IllegalStateException("Требуется -Demail");
        }

        logger.info("=== Тест: регистрация и вход ===");
        logger.info("Используем данные: user={}, email={}", username, email);

        LoginPage page = new LoginPage(driver);
        page.openRegisterPage();

        logger.info("Заполняю форму регистрации...");

        page.register(username, password, email);

        logger.info("Проверяю редирект после регистрации...");
        String url = driver.getCurrentUrl();
        assertTrue(url.contains("wishlist"),
                "Ожидался редирект на страницу списков желаний после регистрации, но URL: " + url);

        logger.info("=== Тест регистрации пройден успешно ===");
    }
}