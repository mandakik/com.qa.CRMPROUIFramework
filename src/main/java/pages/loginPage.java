package pages;

import base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class loginPage extends BaseTest {

    @FindBy(xpath="//a/span[text()='Log In']")
    WebElement loginPageLink;

    @FindBy(name="email")
    WebElement email;

    @FindBy(name="password")
    WebElement password;

    @FindBy(xpath="//div[text()='Login']")
    WebElement loginBtn;

    public loginPage(){
        PageFactory.initElements(driver, this);
    }

    public String getTitle(){
        return driver.getTitle();
    }

    public boolean verifyLoginLink(){
        return loginPageLink.isDisplayed();
    }

    public HomePage loginToFreeCRM(String emailID, String password_value){
        loginPageLink.click();
        email.sendKeys(emailID);
        password.sendKeys(password_value);
        loginBtn.click();
        return new HomePage();
    }
}
