package Utilities;

import org.openqa.selenium.WebDriver;

public class WebDriverUtility {

    private static WebDriverUtility instance;

    private static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();

    private  WebDriverUtility()
    {

    }

    public synchronized static WebDriverUtility getSingletonInstance()
    {
        if(instance==null)
        {
            instance= new WebDriverUtility();
        }
        return instance;
    }



    public synchronized WebDriver getDriver() {
        return threadLocalDriver.get();
    }

    public synchronized void removeDriver()
    {
        threadLocalDriver.remove();
    }
    public synchronized void setDriver(WebDriver driver) {
        threadLocalDriver.set(driver);
    }

}
