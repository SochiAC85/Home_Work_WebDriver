package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class WishlistPage extends AbsBasePage {

    private static final By CREATE_NEW_LIST_BTN = By.xpath("//button[contains(., 'Создать новый')]");
    private static final By NAME_INPUT = By.xpath("//div[contains(@class,'modal')]//input");

    public WishlistPage(WebDriver driver) {
        super(driver);
    }

    public void openWishlistPage() {
        open("/wishlists");
        wait.until(ExpectedConditions.urlContains("wishlists"));
    }

    public void createList(String name) {
        logger.info("Нажимаю кнопку 'Создать новый список' (нативный клик)");
        wait.until(ExpectedConditions.elementToBeClickable(CREATE_NEW_LIST_BTN));
        clickNative(CREATE_NEW_LIST_BTN);

        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(NAME_INPUT));
        input.clear();
        input.sendKeys(name);
        logger.info("Ввёл название списка: {}", name);

        logger.info("Отправляю форму через клавишу ENTER в поле ввода...");
        input.sendKeys(org.openqa.selenium.Keys.ENTER);

        wait.until(ExpectedConditions.invisibilityOfElementLocated(NAME_INPUT));
        logger.info("Модалка успешно закрылась!");
    }
}