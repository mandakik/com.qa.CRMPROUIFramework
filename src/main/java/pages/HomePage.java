package pages;

import base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BaseTest {

    @FindBy(xpath="//span[@class='user-display']")
    WebElement username;

    @FindBy(xpath="//div[@id='main-nav']/descendant::a/span[text()='Home']")
    WebElement homeLink;

    @FindBy(xpath="//div[@id='main-nav']/descendant::a/span[text()='Calendar']")
    WebElement CalendarLink;

    @FindBy(xpath="//div[@id='main-nav']/descendant::a/span[text()='Contacts']")
    WebElement ContactsLink;

    public HomePage(){
        PageFactory.initElements(driver, this);
    }

    public String getHomePageTitle(){
        return driver.getTitle();
    }

    public Contacts goToContactsPage(){
        ContactsLink.click();
        return new Contacts();
    }
}
