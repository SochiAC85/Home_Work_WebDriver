package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends AbsBasePage {

    private static final By USERNAME_INPUT      = By.xpath("//input[@type='text']");
    private static final By EMAIL_INPUT         = By.xpath("//input[@type='email']");
    private static final By PASSWORD_INPUT      = By.xpath("//input[@type='password']");
    private static final By LOGIN_SUBMIT_BUTTON  = By.xpath("//button[contains(., 'Войти') or contains(., 'Login')]");
    private static final By REG_SUBMIT_BUTTON    = By.xpath("//button[contains(., 'Зарегистр')]");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void openLoginPage() {
        open("/login");
    }

    public void openRegisterPage() {
        open("/register");
    }

    public void login(String username, String password) {
        logger.info("Вход: username={}", username);

        WebElement userField = wait.until(ExpectedConditions.visibilityOfElementLocated(USERNAME_INPUT));
        userField.clear();
        userField.sendKeys(username);

        WebElement passField = wait.until(ExpectedConditions.visibilityOfElementLocated(PASSWORD_INPUT));
        passField.clear();
        passField.sendKeys(password);

        logger.info("Заполнил поля, нажимаю кнопку входа");
        clickNative(LOGIN_SUBMIT_BUTTON);

        wait.until(ExpectedConditions.not(ExpectedConditions.urlContains("/login")));
        logger.info("Вход выполнен, текущий URL: {}", driver.getCurrentUrl());
    }

    public void register(String username, String email, String password) {
        logger.info("Регистрация: username={}, email={}", username, email);

        WebElement userField = wait.until(ExpectedConditions.visibilityOfElementLocated(USERNAME_INPUT));
        userField.clear();
        userField.sendKeys(username);

        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(EMAIL_INPUT));
        emailField.clear();
        emailField.sendKeys(email);

        WebElement passField = wait.until(ExpectedConditions.visibilityOfElementLocated(PASSWORD_INPUT));
        passField.clear();
        passField.sendKeys(password);

        logger.info("Заполнил поля, нажимаю кнопку регистрации (нативный клик)");
        clickNative(REG_SUBMIT_BUTTON);
    }
}