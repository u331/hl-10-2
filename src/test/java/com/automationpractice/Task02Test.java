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
        String firstItemTotalProductPriceActual;
        String firstItemTotalProductPriceExpected = "$54.00";
        String totalProductActual;
        String totalProductExpected = "$54.00";
        String totalShippingActual;
        String totalShippingExpected = "$2.00";
        String totalPriceWithoutTaxActual;
        String totalPriceWithoutTaxExpected = "$56.00";
        String totalPriceActual;
        String totalPriceExpected = "$56.00";

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

        firstItemTotalProductPriceActual = orderPage.getFirstItemTotalProductPriceByExpected(firstItemTotalProductPriceExpected);

        totalProductActual = orderPage.getTotalProductByExpected(totalProductExpected);
        totalShippingActual = orderPage.getTotalShippingByExpected(totalShippingExpected);
        totalPriceWithoutTaxActual = orderPage.getTotalPriceWithoutTaxByExpected(totalPriceWithoutTaxExpected);
        totalPriceActual = orderPage.getTotalPriceByExpected(totalPriceExpected);

        orderPage.clickFirstItemCartDeleteA();


        SoftAssert as = new SoftAssert();
        //as.assertEquals(54D, Double.parseDouble(orderPage.getFirstItemTotalProductPriceByExpected("$54.00").substring(1)), 0.0001);
        as.assertEquals(firstItemTotalProductPriceActual, firstItemTotalProductPriceExpected);
        //as.assertEquals(54D, Double.parseDouble(orderPage.getTotalProductByExpected("$54.00").substring(1)), 0.0001);
        as.assertEquals(totalProductActual, totalProductExpected);
        //as.assertEquals(2D, Double.parseDouble(orderPage.getTotalShippingByExpected("$2.00").substring(1)), 0.0001);
        as.assertEquals(totalShippingActual, totalShippingExpected);
        //as.assertEquals(56D, Double.parseDouble(orderPage.getTotalPriceWithoutTaxByExpected("$56.00").substring(1)), 0.0001);
        as.assertEquals(totalPriceWithoutTaxActual, totalPriceWithoutTaxExpected);
        //as.assertEquals(56D, Double.parseDouble(orderPage.getTotalPriceByExpected("$56.00").substring(1)), 0.0001);
        as.assertEquals(totalPriceActual, totalPriceExpected);
    //as.assertTrue(orderPage.isTotalPriceEqualsExpected());
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
