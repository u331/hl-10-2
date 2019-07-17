import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Task02Test {

    public WebDriver driver;
    private final Properties config = Config.loadProperties("src/main/resources/test.properties");
    private Page mainPage;
    private SearchPage searchPage;
    private OrderPage orderPage;
    String aaa = new String("27");

    //final Wait<WebDriver> wait = new WebDriverWait(driver, 5, 5000);
    //private Wait<WebDriver> wait;


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

        orderPage =
                orderPage.clickFirstItemAddA();
        System.out.println(orderPage.getFirstItemTotalProductPriceByExpected("$54.00"));
//        Wait<WebDriver> wait = new WebDriverWait(driver, 5, 5000);
        //wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("#cart_summary tr.first_item td.cart_total span"),"54"));
//        System.out.println(orderPage.getFirstItemTotalProductPriceByExpected("54"));

    }








}
