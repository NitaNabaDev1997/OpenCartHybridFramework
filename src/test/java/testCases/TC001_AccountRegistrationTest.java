package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class TC001_AccountRegistrationTest extends BaseClass {

    @Test(groups={"Regression","Master"})
    public void verify_account_registration() throws IOException {
            try {
                    log.info("***String Testcases****");
                    HomePage hp = new HomePage(driver);
                    hp.clickMyAccount();
                    log.info("Clicked on MyAccount link");
                    hp.clickRegister();
                    log.info("Clicked on Register link");
                    AccountRegistrationPage regpage = new AccountRegistrationPage(driver);


                    log.info("Providing customer details.......");
                    String emailvalue = randomeString() + "@gmail.com";
                    regpage.setFirstName(randomeString().toUpperCase());
                    regpage.setLastName(randomeString().toUpperCase());
                    regpage.setEmail(emailvalue);
                    regpage.setTelephone(randomeNumber());
                    String passwordval = randomAlphaNumeric();

                    regpage.setPassword(passwordval);
                    regpage.setConfirmPassword(passwordval);

                    regpage.setPrivacyPolicy();
                    regpage.clickContinue();


                    //updating properties values
                    prop.setProperty("email", emailvalue);
                    prop.setProperty("password", passwordval);
                    FileOutputStream output = new FileOutputStream("./src//test//resources//dev.properties");
                    prop.store(output, "Updated properties file");
                    output.close();
                    System.out.println("Property updated successfully!");


                    String confmsg = regpage.getConfirmationMsg();

                    log.info("Validating expected message......");
                    if (confmsg.equals("Your Account Has Been Created!")) {
                            Assert.assertTrue(true,"Confirmation message mismatch!");
                    }
                    else {
                            log.error("Test Failed");
                            log.debug("Debug logs..");
                            Assert.assertTrue(false);
                    }
                    log.info("Finished account registration........");
            }
        catch (Exception e)
        {
            Assert.fail("Registration failed",e.getCause());
        }


    }
}
