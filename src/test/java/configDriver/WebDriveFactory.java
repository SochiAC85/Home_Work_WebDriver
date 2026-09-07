package configDriver;

import exceptions.BrowserNotFoundExceptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.List;
import java.util.Map;

public class WebDriveFactory {

    private static final Logger logger = LogManager.getLogger(WebDriveFactory.class);

    private static final Map<BrowserMode, List<String>> ARGS = Map.of(
            BrowserMode.HEADLESS,   List.of("--headless=new", "--window-size=1920,1080", "--no-sandbox"),
            BrowserMode.KIOSK,      List.of("--kiosk"),
            BrowserMode.FULLSCREEN, List.of("--start-fullscreen")
    );

    public static WebDriver createNewDriver(String webDriverName) {
        return createNewDriver(webDriverName, null);
    }

    public static WebDriver createNewDriver(String webDriverName, Object options) {
        if (webDriverName == null || webDriverName.trim().isEmpty()) {
            throw new BrowserNotFoundExceptions("null");
        }

        String browser = webDriverName.trim().toLowerCase();
        BrowserMode mode = resolveMode();

        switch (browser) {
            case "chrome": {
                logger.info("Создаю Chrome в режиме: {}", mode);
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = (options instanceof ChromeOptions)
                        ? (ChromeOptions) options
                        : new ChromeOptions();
                applyModeArgs(chromeOptions, mode);
                return new ChromeDriver(chromeOptions);
            }
            case "firefox": {
                logger.info("Создаю Firefox в режиме: {}", mode);
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = (options instanceof FirefoxOptions)
                        ? (FirefoxOptions) options
                        : new FirefoxOptions();
                applyModeArgs(firefoxOptions, mode);
                return new FirefoxDriver(firefoxOptions);
            }
            default:
                throw new BrowserNotFoundExceptions(browser);
        }
    }

    private static BrowserMode resolveMode() {
        String modeStr = System.getProperty("browserMode");
        if (modeStr == null || modeStr.isBlank()) {
            return null;
        }
        try {
            return BrowserMode.valueOf(modeStr.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            logger.warn("Неизвестный browserMode: {}, игнорирую", modeStr);
            return null;
        }
    }

    private static void applyModeArgs(Object options, BrowserMode mode) {
        if (mode == null) {
            return;
        }
        List<String> args = ARGS.get(mode);
        if (args == null || args.isEmpty()) {
            return;
        }
        if (options instanceof ChromeOptions) {
            ((ChromeOptions) options).addArguments(args);
            logger.info("Аргументы Chrome: {}", args);
        } else {
            logger.info("Аргументы режима {} не применены — браузер не Chrome", mode);
        }
    }
}