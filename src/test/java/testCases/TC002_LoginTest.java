package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {
    @Test(groups={"Sanity","Master"})
    public void verify_login()
    {

        log.info("****Starting TC002_LoginTest*******");
        try {
            HomePage hm = new HomePage(driver);
            hm.clickMyAccount();
            hm.clickLogin();

            LoginPage loginPage = new LoginPage(driver);
            loginPage.setPassword(prop.getProperty("password"));
            loginPage.setEmail(prop.getProperty("email"));

            log.info("****Setting property values****");

            loginPage.clickLogin();

            MyAccountPage myAccountPage = new MyAccountPage(driver);
            boolean target = myAccountPage.isMyAccountPageExists();

            Assert.assertEquals(target, true, "Login Failed");
            log.info("****Finished TC002_LoginTest*******");
        }
        catch (Exception e)
        {
            Assert.fail();
        }

    }
}
