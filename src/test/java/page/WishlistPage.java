package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class WishlistPage extends AbsBasePage {

    private static final By CREATE_NEW_LIST_BTN = By.xpath("//button[contains(., 'Создать новый') or contains(., 'Create')]");
    private static final By NAME_INPUT = By.xpath("//div[contains(@class, 'modal')]//input");
    private static final By PAGE_BODY = By.tagName("body");

    public WishlistPage(WebDriver driver) {
        super(driver);
    }

    public void openWishlistPage() {
        open("/wishlists");
        wait.until(ExpectedConditions.urlContains("wishlists"));
        logger.info("Страница списков желаний открыта");
    }

    public void createList(String name) {
        logger.info("Создание списка: {}", name);
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(CREATE_NEW_LIST_BTN));
        btn.click();

        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(NAME_INPUT));
        input.clear();
        input.sendKeys(name);
        input.sendKeys(org.openqa.selenium.Keys.ENTER);

        wait.until(ExpectedConditions.invisibilityOfElementLocated(NAME_INPUT));
        logger.info("Список '{}' создан, модальное окно закрыто", name);
    }

    public void assertListDisplayed(String listName) {
        logger.info("Проверяю отображение списка: {}", listName);
        WebElement body = wait.until(ExpectedConditions.visibilityOfElementLocated(PAGE_BODY));
        String pageText = body.getText();

        assertTrue(pageText.contains(listName),
                "Список '" + listName + "' не найден на странице.\n" +
                        "Текст страницы (фрагмент): " + pageText.substring(0, Math.min(pageText.length(), 300)));

        logger.info("Список '{}' успешно найден на странице ✓", listName);
    }
}