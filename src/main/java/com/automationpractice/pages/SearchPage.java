package com.automationpractice.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {

    private WebDriver driver;

    @FindBy(css = "#list *[title='List']")
    private WebElement viewListA;

    @FindBy(css = "#center_column .ajax_add_to_cart_button")
    private WebElement addToCartA;

    @FindBy(css = "#layer_cart *[title='Proceed to checkout']")
    private WebElement proceedToCheckoutA;

//    Constructor
    public SearchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public SearchPage clickViewListA(){
        viewListA.click();
        return this;
    }

    public SearchPage clickAddToCartA(){
        addToCartA.click();
        return this;
    }

    public OrderPage clickProceedToCheckoutA(){
        proceedToCheckoutA.click();
        return new OrderPage(driver);
    }



}
