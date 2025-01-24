package test.ui;

import main.apiUI.uipages.EbayPage;
import main.automationframework.DriverIntialize;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class EbayUI extends DriverIntialize {
@BeforeClass
public static void initializeTheBrowser()
{
    initializeDriver("chrome");
}
    @Test
    public void testValidLogin() {
        driver.get("https://ebay.com/");
        EbayPage ebayPage = new EbayPage(driver);
        ebayPage.addBookIntoCart("book");
        Assert.assertTrue(ebayPage.isCartCountMatch("1"),"no of item  match");
    }

    @AfterClass
    public static void terminateInstance()
    {
        driver.quit();
    }


}
