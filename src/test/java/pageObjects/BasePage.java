package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    WebDriver driver;
    WebDriverWait wait;
    public BasePage(WebDriver driver)
    {
        this.driver=driver;
        wait= new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver,this);
    }
//test
    public void click(WebElement element)
    {
        try
        {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
        }catch (Exception e)
        {
            throw e;
        }
    }
}
