package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class LoginPageTests extends BaseTest {

    LoginPage page;
    HomePage home_page;

    public LoginPageTests(){
        super();
    }

    @BeforeMethod
    public void setup(){
    initialization();
    page = new LoginPage();
    }

    @Test(enabled = false)
    public void verifyTitle(){
        Assert.assertEquals(page.getTitle(),"#1 Free CRM Business Software - Free Forever");
    }

    @Test
    public void verifyLoginPageLink(){
        Assert.assertTrue(page.verifyLoginLink());
    }

    @Test
    public void loginToCRM(){
        home_page = page.loginToFreeCRM(prop.getProperty("username"), prop.getProperty("password"));
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
