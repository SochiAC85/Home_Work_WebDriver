package configDriver;

import exceptions.BrowserNotFoundExceptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;
import java.util.Map;

public class WebDriveFactory {

    private static final Logger logger = LogManager.getLogger(WebDriveFactory.class);

    private final String browser = System.getProperty("browser", "chrome");

    private static final Map<BrowserMode, List<String>> ARGS = Map.of(
            BrowserMode.HEADLESS,   List.of("--headless=new", "--window-size=1920,1080", "--no-sandbox"),
            BrowserMode.KIOSK,      List.of("--kiosk"),
            BrowserMode.FULLSCREEN, List.of("--start-fullscreen")
    );

    public WebDriver create(BrowserMode mode) {
        switch (browser) {
            case "chrome": {
                logger.info("Создаю Chrome в режиме: {}", mode);
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                List<String> args = ARGS.get(mode);
                if (args != null) {
                    options.addArguments(args);
                    logger.info("Аргументы: {}", args);
                }
                return new ChromeDriver(options);
            }
        }
        throw new BrowserNotFoundExceptions(browser);
    }
}