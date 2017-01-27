package webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Context {
    // get path to the browser drivers
    public static final String BROWSER_CHROME = System.getProperty("user.dir") + "/src/main/resources/chromedriver.exe";
    public static final String BROWSER_PHANTOMJS = System.getProperty("user.dir") + "/src/main/resources/phantomjs.exe";

    private static Context context;

    private WebDriver driver;
    private WebDriverWait wait;

    private Context() {
    }

    public static void initInstance(String browserType, String siteURL) {
        context = new Context();
        if (browserType.equals(BROWSER_CHROME)) {
            System.setProperty("webdriver.chrome.driver", BROWSER_CHROME);
            context.setDriver(new ChromeDriver());
        } else if (browserType.equals(BROWSER_PHANTOMJS)) {
            System.setProperty("phantomjs.binary.path", BROWSER_PHANTOMJS);
            context.setDriver(new PhantomJSDriver());
        } else {
            throw new IllegalStateException("The type of browser cannot be found");
        }
        context.setWait(new WebDriverWait(context.getDriver(), 10));
        context.getDriver().navigate().to(siteURL);
    }

    public static Context getInstance() {
        if (context == null) {
            throw new IllegalStateException("Context is not initialized");
        }
        return context;
    }

    public static void browserClose() {
        context.getDriver().quit();
    }

    public WebDriver getDriver() {
        if (driver != null) {
            return driver;
        }
        throw new IllegalStateException("WebDriver is not initialized");
    }

    private void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriverWait getWait() {
        if (wait != null) {
            return wait;
        }
        throw new IllegalStateException("WebDriverWait is not initialized");
    }

    private void setWait(WebDriverWait wait) {
        this.wait = wait;
    }
}
