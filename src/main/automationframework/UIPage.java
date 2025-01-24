package main.automationframework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class UIPage {

    private WebDriver driver;
    private WebDriverWait wait;

    /**
     * Constructor to initialize the WebDriver and WebDriverWait.
     *
     * @param driver The WebDriver instance.
     * @param timeoutInSeconds The timeout duration for explicit waits.
     */
    public UIPage(WebDriver driver, int timeoutInSeconds) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
    }

    /**
     * Clicks on a WebElement located by the specified locator.
     *
     * @param locator The By locator of the element.
     */
    public void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    public void click(WebElement element)
    {
        waitForElementToBeClickable(element);
        element.click();
    }

    /**
     * Performs a mouse hover action on a WebElement located by the specified locator.
     *
     * @param locator The By locator of the element.
     */
    public void mouseHover(By locator) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

    /**
     * Sends text input to a WebElement located by the specified locator.
     *
     * @param locator The By locator of the element.
     * @param text The text to input.
     */
    public void sendKeys(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }

    /**
     * Retrieves the text of a WebElement located by the specified locator.
     *
     * @param locator The By locator of the element.
     * @return The text of the element.
     */
    public String getText(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
    }

    /**
     * Checks if a WebElement located by the specified locator is visible.
     *
     * @param locator The By locator of the element.
     * @return True if the element is visible, false otherwise.
     */
    public boolean isElementVisible(By locator) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Waits for a WebElement to be clickable and returns the element.
     *
     * @param locator The By locator of the element.
     * @return The clickable WebElement.
     */
    public WebElement waitForElementToBeClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public WebElement waitForElementToBeClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Waits for a WebElement to be visible and returns the element.
     *
     * @param locator The By locator of the element.
     * @return The visible WebElement.
     */
    public WebElement waitForElementToBeVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}

