package Listeners;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static base.BaseTest.takeScreenShotAtEndOfTest;

public class WebEventListener implements WebDriverListener {

    @Override
    public void beforeFindElement(WebDriver driver, By locator){
        System.out.println("finding element");
    }

    @Override
    public void afterFindElement(WebDriver driver, By locator, WebElement result){
        System.out.println("after finding element");
    }

    @Override
    public void onError(Object target, Method method, Object[] args, InvocationTargetException e){
        System.out.println("Exception occured !!");
        takeScreenShotAtEndOfTest();
    }

}
