package testCases;

import Utilities.DataProviders;
import Utilities.ExcelUtility;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

import java.io.IOException;

public class TC003_LoginDDT extends BaseClass {



    public int getCurrentRow(String email) {
        try {
            String path = ".\\testData\\Opencart_LoginData.xlsx";
            ExcelUtility xlutil = new ExcelUtility(path);
            int totalRows = xlutil.getRowCount("Sheet1");
            for (int i = 1; i <= totalRows; i++) {
                String cellData = xlutil.getCellData("Sheet1", i, 0); // 0 = username column
                if (cellData.equalsIgnoreCase(email)) {
                    return i;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }


    @Test(dataProvider = "LoginData",dataProviderClass = DataProviders.class,groups="Datadriven")
    public void verify_loginddt(String email, String pwd,String exp) throws IOException {

        String path=".\\testData\\Opencart_LoginData.xlsx";//taking xl file from testData
        String result="";
        ExcelUtility xlutil=new ExcelUtility(path);
        int rowNum = getCurrentRow(email);
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
                    result = "Pass";
                } else {
                    result = "Fail";
                    Assert.assertTrue(false);

                }
            }

            if (exp.equalsIgnoreCase("Invalid")) {
                if (target == true) {
                    myAccountPage.clickLogout();
                    result = "Fail";
                    Assert.assertTrue(false);

                } else {
                    Assert.assertTrue(true);
                    result = "Pass";
                }
            }


        }
        catch (Exception e)
        {
            try {
                xlutil.setCellData("Sheet1", rowNum, 3, "Error");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            Assert.fail();
        }
        finally {
            xlutil.setCellData("Sheet1", rowNum, 3, result);
            log.info("****Finished TC003_LoginDDT****** ");
        }





    }
}
