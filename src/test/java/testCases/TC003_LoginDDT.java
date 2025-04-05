package testCases;

import Utilities.DataProviders;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC003_LoginDDT extends BaseClass {

    @Test(dataProvider = "LoginData",dataProviderClass = DataProviders.class,groups="Datadriven")
    public void verify_loginddt(String email, String pwd,String exp) {

        log.info("****Starting TC003_LoginDDT****** ");
        try {
            HomePage hm = new HomePage(driver);
            hm.clickMyAccount();
            hm.clickLogin();

            LoginPage loginPage = new LoginPage(driver);
            loginPage.setPassword(pwd);
            loginPage.setEmail(email);
            loginPage.clickLogin();

            MyAccountPage myAccountPage = new MyAccountPage(driver);
            boolean target = myAccountPage.isMyAccountPageExists();



            if (exp.equalsIgnoreCase("Valid")) {
                if (target == true) {
                    myAccountPage.clickLogout();
                    Assert.assertTrue(true);

                } else {
                    Assert.assertTrue(false);
                }
            }

            if (exp.equalsIgnoreCase("Invalid")) {
                if (target == true) {
                    myAccountPage.clickLogout();
                    Assert.assertTrue(false);

                } else {
                    Assert.assertTrue(true);
                }
            }
        }
        catch (Exception e)
        {
            Assert.fail();
        }
        finally {
            log.info("****Finished TC003_LoginDDT****** ");
        }

    }
}
