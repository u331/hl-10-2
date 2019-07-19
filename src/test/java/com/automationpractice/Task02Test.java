package com.automationpractice;

import com.automationpractice.WebDriverFactory.engine.DriverTypes;
import com.automationpractice.WebDriverFactory.engine.WebDriverFactory;
import com.automationpractice.pages.OrderPage;
import com.automationpractice.pages.Page;
import com.automationpractice.pages.SearchPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Task02Test {

    public WebDriver driver;
    private final Properties config = Config.loadProperties("src/main/resources/test.properties");
    private Page mainPage;
    private SearchPage searchPage;
    private OrderPage orderPage;

    @BeforeMethod
    public void beforeMethod(){
        driver = WebDriverFactory.getDriver(DriverTypes.CHROME);
        driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        //driver.get(config.getProperty("baseurl"));
    }

    @Test
    public void testLesson10(){
        //driver.navigate().to(MAIN_PAGE_URL);
        driver.get(config.getProperty("baseurl"));
        mainPage = new Page(driver);
        searchPage =
                mainPage.inputTextToSearchInput("Blouse")
                        .clickSearchSubmit();
        orderPage =
                searchPage.clickViewListA()
                        .clickAddToCartA()
                        .clickProceedToCheckoutA();
        orderPage.clickFirstItemAddA();
        SoftAssert as = new SoftAssert();
        as.assertEquals(54D, Double.parseDouble(orderPage.getFirstItemTotalProductPriceByExpected("$54.00").substring(1)), 0.0001);
        as.assertEquals(54D, Double.parseDouble(orderPage.getTotalProductByExpected("$54.00").substring(1)), 0.0001);
        as.assertEquals(2D, Double.parseDouble(orderPage.getTotalShippingByExpected("$2.00").substring(1)), 0.0001);
        as.assertEquals(56D, Double.parseDouble(orderPage.getTotalPriceWithoutTaxByExpected("$56.00").substring(1)), 0.0001);
        as.assertEquals(56D, Double.parseDouble(orderPage.getTotalPriceByExpected("$56.00").substring(1)), 0.0001);
        as.assertAll();
    }








}
