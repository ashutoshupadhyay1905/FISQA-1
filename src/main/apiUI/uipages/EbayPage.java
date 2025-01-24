package main.apiUI.uipages;

import main.automationframework.UIPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.Set;

public class EbayPage {
    private static final Logger log = LoggerFactory.getLogger(EbayPage.class);
    private WebDriver driver;
    UIPage page=new UIPage(this.driver,30);
    @FindBy(xpath = "//input[@placeholder='Search for anything']")
    private WebElement searchItem;

    @FindBy(id = "gh-search-btn")
    private WebElement getSearchItem;

    @FindBy(xpath = "//span[@class='s-item__watchheart on-image s-item__watchheart--watch']/parent::div/parent::div/div[@class='s-item__info clearfix']/a")
    private WebElement firstBook;

    @FindBy(id = "atcBtn_btn_1")
    private WebElement addToCartButton;

    @FindBy(xpath = "//span[@class='gh-cart__icon']/span")
    private WebElement numberOfItemInCart;

    public EbayPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void addBookIntoCart(String item) {
        log.info("wait for search icon to be present");
        page.waitForElementToBeClickable(searchItem);
        page.sendKeys(searchItem,item);
        page.click(getSearchItem);
        String eBayWindow=driver.getWindowHandle();
        page.click(firstBook);
        log.info("navigate to next window");

        Set<String> allWindow=driver.getWindowHandles();
        Iterator<String> iterator = allWindow.iterator();
        while(iterator.hasNext())
        {
            String childWindow=iterator.next();
            if(!eBayWindow.equalsIgnoreCase(childWindow))
            {
                log.info("navigate to next window to add into cart");
                driver.switchTo().window(childWindow);
                log.info(driver.getTitle());
                log.info(driver.getCurrentUrl());
                page.click(addToCartButton);

            }
            else {
                log.info("unable to navigate");

            }
        }

    }

    public boolean isCartCountMatch(String count)
    {
        String cartCount=numberOfItemInCart.getText();
        if(count.equalsIgnoreCase(cartCount))
        {
            return true;
        }
        return false;
    }
}
