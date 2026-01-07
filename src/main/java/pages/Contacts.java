package pages;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Contacts extends BaseTest {

    @FindBy(xpath="//div[@id='main-content']/descendant::span[text()='Contacts']")
    WebElement contacts;

    @FindBy(xpath="//div[@name='view']")
    WebElement viewDropdown;

    @FindBy(xpath="//button[text()='Show Filters']")
    WebElement showFiltersBtn;

    @FindBy(name="first_name")
    WebElement firstName;

    @FindBy(name="last_name")
    WebElement lastName;

    @FindBy(name="status")
    WebElement status;

    @FindBy(xpath="//button[text()='Save']")
    WebElement saveBtn;

    public Contacts(){
        PageFactory.initElements(driver, this);
    }

    public boolean verifyContactsLogo(){
        return contacts.isDisplayed();
    }

    public void createNewContact(String fName, String lName, String s){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement newContact = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href,'contacts/new')]")));
        newContact.click();

        WebElement form = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form")));

        firstName.sendKeys(fName);
        lastName.sendKeys(lName);
        status.sendKeys(s);
        //saveBtn.submit();
        saveBtn.click();
    }
}
