package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.Contacts;
import pages.HomePage;
import pages.LoginPage;
import utils.TestUtils;

public class ContactsPageTest extends BaseTest {

    HomePage homepage;
    LoginPage page;
    Contacts contactPage;

    public ContactsPageTest(){
        super();
    }

    @BeforeMethod
    public void setup(){
        initialization();
        page = new LoginPage();
        homepage = page.loginToFreeCRM(prop.getProperty("username"), prop.getProperty("password"));
        contactPage = homepage.goToContactsPage();
    }

    @Test
    public void checkIfLogoPresentTest(){
        Assert.assertTrue(contactPage.verifyContactsLogo());
    }

    @DataProvider
    public Object[][] getUserData(){
        return new Object[][]{
                {"Varad","Sune","Active"},
                {"Teertha","Prasade","Inactive"},
                {"Parv","Prasade","Active"}
        };
    }

    @DataProvider
    public Object[][] getExcelData(){
        return TestUtils.getContactsDetails("contacts");
    }

    //@Test(dataProvider="getUserData")
    @Test(dataProvider="getExcelData")
    public void createNewContactTest(String firstname, String lastname, String status){
        contactPage.createNewContact(firstname, lastname, status);
    }

    @AfterMethod
    public void tearDown(){
        if(driver != null)
            driver.quit();
    }
}
