package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath="//span[text()='My Account']")
    WebElement linkMyAccount;

    @FindBy(xpath="//a[text()='Register']")
    WebElement registerAccount;

    @FindBy(xpath="//a[text()='Login']")
    WebElement loginAccount;

    public void clickMyAccount()
    {
        click(linkMyAccount);
        //test
    }

    public void clickRegister()
    {
        click(registerAccount);
    }

    public void clickLogin()
    {
        loginAccount.click();
    }
}
