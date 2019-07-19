package com.automationpractice.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderPage {

    private WebDriver driver;
//    private Wait<WebDriver> wait = new WebDriverWait(driver, 5, 5000);
    private String sss = "sss";
    private Wait<WebDriver> wait;

    @FindBy(css = "#cart_summary tr.first_item *[title='Add']")
//    @FindBy(css = "#cart_summary tr.first_item a.cart_quantity_up")
    private WebElement firstItemAddA;

    @FindBy(xpath = "//*[@id='cart_summary']//tr[contains(@class,'first_item')]//*[contains(@id,'total_product_price')]")
    private WebElement firstItemTotalProductPrice;

    @FindBy(id = "total_product")
    private WebElement totalProduct;

    @FindBy(id = "total_shipping")
    private WebElement totalShipping;

    @FindBy(id = "total_price_without_tax")
    private WebElement totalPriceWithoutTax;

    @FindBy(id = "total_price")
    private WebElement totalPrice;

    //    Constructor
    public OrderPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 5, 5000);
    }

    public OrderPage clickFirstItemAddA(){
        firstItemAddA.click();
        return this;
    }

    public String getFirstItemTotalProductPriceByExpected(String expected) {
        wait.until(ExpectedConditions.textToBePresentInElement( firstItemTotalProductPrice ,  expected)); //need to replace condition
        //try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}
        return firstItemTotalProductPrice.getText();
    }

    public String getTotalProductByExpected(String expected) {
        wait.until(ExpectedConditions.textToBePresentInElement( totalProduct ,  expected)); //need to replace condition
        //try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}
        return totalProduct.getText();
    }

    public String getTotalShippingByExpected(String expected) {
        wait.until(ExpectedConditions.textToBePresentInElement( totalShipping ,  expected)); //need to replace condition
        //try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}
        return totalShipping.getText();
    }

    public String getTotalPriceWithoutTaxByExpected(String expected) {
        wait.until(ExpectedConditions.textToBePresentInElement( totalPriceWithoutTax ,  expected)); //need to replace condition
        //try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}
        return totalPriceWithoutTax.getText();
    }

    public String getTotalPriceByExpected(String expected) {
        wait.until(ExpectedConditions.textToBePresentInElement( totalPrice ,  expected)); //need to replace condition
        //try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}
        return totalPrice.getText();
    }

}
