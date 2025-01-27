package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {

    @Test(groups={"Regression","Master"})
    public void verify_account_registration()
    {
        try {
            log.info("***String Testcases****");
            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();
            log.info("Clicked on MyAccount link");
            hp.clickRegister();
            log.info("Clicked on Register link");
            AccountRegistrationPage regpage = new AccountRegistrationPage(driver);


            log.info("Providing customer details.......");
            regpage.setFirstName(randomeString().toUpperCase());
            regpage.setLastName(randomeString().toUpperCase());
            regpage.setEmail(randomeString() + "@gmail.com");// randomly generated the email
            regpage.setTelephone(randomeNumber());

            String password = randomAlphaNumeric();

            regpage.setPassword(password);
            regpage.setConfirmPassword(password);

            regpage.setPrivacyPolicy();
            regpage.clickContinue();

            String confmsg = regpage.getConfirmationMsg();

            log.info("Validating expected message......");
            if(confmsg.equals("Your Account Has Been Created!"))
            {
                Assert.assertTrue(true);
            }
            else{
                log.info("Test Failed");
                log.info("Debug logs..");
                Assert.assertTrue(false);
            }
            log.info("Finished account registration........");
        }
        catch (Exception e)
        {
            Assert.fail();
        }


    }
}
