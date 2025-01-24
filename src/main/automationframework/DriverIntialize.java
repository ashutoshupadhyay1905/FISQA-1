package main.automationframework;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverIntialize {

    public static WebDriver driver;

    /**
     * Initializes the WebDriver based on the browser type.
     *
     * @param browserType The type of browser ("chrome", "firefox", "edge").
     * @return The initialized WebDriver instance.
     */
    public static WebDriver initializeDriver(String browserType) {
        switch (browserType.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;

            case "firefox":

                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;

            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;

            default:
                throw new IllegalArgumentException("Unsupported browser type: " + browserType);
        }

        driver.manage().window().maximize();
        return driver;
    }

    /**
     * Quits the WebDriver instance.
     */
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    /**
     * Gets the current WebDriver instance.
     *
     * @return The current WebDriver instance, or null if not initialized.
     */
    public static WebDriver getDriver() {
        return driver;
    }
}

