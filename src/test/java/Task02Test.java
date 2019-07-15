import org.openqa.selenium.WebDriver;
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
//        orderPage =
                orderPage.clickFirstItemAddA();
//        System.out.println(orderPage.getFirstItemTotalProductPriceByExpected("54"));

    }








}
