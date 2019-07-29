package com.automationpractice;

//Task
//Имплементировать домашку с 9 го урока с возможностю запуска с 3х сбаузеров
// используя Duilder, Page Factory, WebDriver factory и WebDriver manager

import com.automationpractice.WebDriverFactory.engine.DriverTypes;
import com.automationpractice.WebDriverFactory.engine.WebDriverFactory;
import com.automationpractice.pages.OrderPage;
import com.automationpractice.pages.Page;
import com.automationpractice.pages.SearchPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Task02Test {

    public WebDriver driver;
    private final Properties config = Config.loadProperties("src/main/resources/test.properties");
    private Page mainPage;
    private SearchPage searchPage;
    private OrderPage orderPage;

//    @BeforeMethod
    @BeforeTest
    public void beforeMethod(){
        driver = WebDriverFactory.getDriver(DriverTypes.CHROME);
        driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        //driver.get(config.getProperty("baseurl")); 
    }

    @Test
    public void testLesson10(){

        String searchInputText = "Blouse";
        String firstItemTotalProductPriceExpected = "$54.00";
        String totalProductExpected = "$54.00";
        String totalShippingExpected = "$2.00";
        String totalPriceWithoutTaxExpected = "$56.00";
        String totalPriceExpected = "$56.00";

        SoftAssert as = new SoftAssert();

        //driver.navigate().to(MAIN_PAGE_URL);
        driver.get(config.getProperty("baseurl"));
        mainPage = new Page(driver);
        searchPage =
                mainPage.inputTextToSearchInput(searchInputText)
                        .clickSearchSubmit();
        orderPage =
                searchPage.clickViewListA()
                        .clickAddToCartA()
                        .clickProceedToCheckoutA();
        orderPage.clickFirstItemAddA();

        as.assertTrue(orderPage.isFirstItemTotalProductPriceEqualsExpected(firstItemTotalProductPriceExpected));
        as.assertTrue(orderPage.isTotalProductEqualsExpected(totalProductExpected));
        as.assertTrue(orderPage.isTotalShippingEqualsExpected(totalShippingExpected));
        as.assertTrue(orderPage.isTotalPriceWithoutTaxEqualsExpected(totalPriceWithoutTaxExpected));
        as.assertTrue(orderPage.isTotalPriceEqualsExpected(totalPriceExpected));

        orderPage.clickFirstItemCartDeleteA();

        as.assertTrue(orderPage.isAlertWarning());
        as.assertAll();
    }

//    @AfterMethod
    @AfterTest
    public void cleanup() {
        driver.manage().deleteAllCookies();
        //TestHelper.sleep5Seconds();
        try {Thread.sleep(5000);} catch (InterruptedException e) {e.printStackTrace();}
        driver.close();
    }

}
