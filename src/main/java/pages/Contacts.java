package pages;

import base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Contacts extends BaseTest {

    @FindBy(xpath="//div[@id='main-content']/descendant::span[text()='Contacts']")
    WebElement contacts;

    @FindBy(xpath="//div[@name='view']")
    WebElement viewDropdown;

    @FindBy(xpath="//button[text()='Show Filters']")
    WebElement showFiltersBtn;

    public Contacts(){
        PageFactory.initElements(driver, this);
    }
}
