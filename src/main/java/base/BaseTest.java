package base;

import configManager.ConfigReader;
import constants.CRMPROConstants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.time.Duration;
import java.util.Properties;

public class BaseTest {

    protected WebDriver driver;
    protected Properties prop;


    public BaseTest(){
        prop = ConfigReader.readConfigFile();
        String browserName = prop.getProperty("browser");

        switch(browserName.toLowerCase()){
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "ie":
                driver = new InternetExplorerDriver();
                break;
            default:
                driver = new ChromeDriver();
        }

        driver.get(prop.getProperty(CRMPROConstants.BASEURI));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(CRMPROConstants.IMPLICITLY_WAIT));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(CRMPROConstants.PAGE_LOAD_TIMEOUTS));
        driver.manage().deleteAllCookies();
    }
}
