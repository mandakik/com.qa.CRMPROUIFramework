package base;

import Listeners.WebEventListener;
import configManager.ConfigReader;
import constants.CRMPROConstants;
import frameworkException.CRMException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.events.EventFiringDecorator;

import java.io.File;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {

    protected static WebDriver driver;
    protected static Properties prop;
    protected static EventFiringDecorator e_driver;


    public BaseTest() {
        prop = ConfigReader.readConfigFile();
    }

    public static void initialization(){
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

        WebEventListener listener = new WebEventListener();
        e_driver = new EventFiringDecorator(listener);
        driver = e_driver.decorate(driver);
        driver.get(prop.getProperty(CRMPROConstants.BASEURI));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(CRMPROConstants.IMPLICITLY_WAIT));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(CRMPROConstants.PAGE_LOAD_TIMEOUTS));
        driver.manage().deleteAllCookies();
    }

    public static void takeScreenShotAtEndOfTest(){

        try {
            File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destDir = new File(System.getProperty("user.dir")+File.separator+"src"+File.separator+"test"+File.separator+"resources"+File.separator+"screenshots");
            if(!destDir.exists()) destDir.mkdirs();
            FileHandler.copy(file, new File(destDir+File.separator+System.currentTimeMillis()+".png"));
        }catch(Exception e){
            throw new CRMException(e.getMessage());
        }
    }
}
