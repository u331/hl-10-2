package com.automationpractice.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class Page {

    private WebDriver driver;

    @FindBy(id = "search_query_top")
    private WebElement searchInput;

    @FindBy(css = "#searchbox *[type='submit']")
    private WebElement searchSubmit;

//    Constructor
    public Page(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public Page inputTextToSearchInput(String value){
        searchInput.sendKeys(value);
        return this;
    }

    public SearchPage clickSearchSubmit(){
        searchSubmit.click();
        return new SearchPage(driver);
    }

}

