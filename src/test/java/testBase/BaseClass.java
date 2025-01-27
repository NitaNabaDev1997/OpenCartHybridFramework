package testBase;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

public class BaseClass {

    public static WebDriver driver;
    public Logger log;
    public Properties prop;
    @BeforeClass(groups={"Regression","Master","Sanity","Datadriven"})
    @Parameters({"os","browser"})
        public void setUp(String os,String browser ) throws IOException {
        FileInputStream fis=new FileInputStream("./src//test//resources//config.properties");
        prop=new Properties();
        prop.load(fis);
        log= LogManager.getLogger(this.getClass());


        if(prop.getProperty("execution_environment").equalsIgnoreCase("remote")) {
            DesiredCapabilities cap = new DesiredCapabilities();

            if (os.equalsIgnoreCase("windows")) {
                cap.setPlatform(Platform.WIN11);
            } else if (os.equalsIgnoreCase("mac")) {
                cap.setPlatform(Platform.MAC);
            } else {
                System.out.println("No Matching OS");
                return;
            }

                switch (browser.toLowerCase()) {
                    case "chrome":
                        cap.setBrowserName("chrome");
                        break;
                    case "edge":
                        cap.setBrowserName("MicrosoftEdge");
                        break;
                    default:
                        System.out.println("No matching browser name....");
                        return;
                }

            driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cap);
        }

        if(prop.getProperty("execution_environment").equalsIgnoreCase("local")) {
            switch (browser.toLowerCase()) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
                case "edge":
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver();
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                default:
                    System.out.println("Invalid browser name....");
                    return;
            }
        }
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(prop.getProperty("appURL1"));
        driver.manage().window().maximize();

    }
    @AfterClass(groups={"Regression","Master","Sanity","Datadriven"})
    public void tearDown()
    {
        driver.quit();
    }

    public String randomeString()
    {
        String generatedString=RandomStringUtils.randomAlphabetic(5);
        return generatedString;
    }

    public String randomeNumber()
    {
        String generatedNum=RandomStringUtils.randomNumeric(10);
        return generatedNum;
    }

    public String randomAlphaNumeric()
    {
        String generatedstring=RandomStringUtils.randomAlphabetic(3);
        String generatednumber=RandomStringUtils.randomNumeric(3);
        return (generatedstring+"@"+generatednumber);
    }


    public String captureScreen(String tname) throws IOException {

        String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

        String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
        File targetFile=new File(targetFilePath);

        sourceFile.renameTo(targetFile);

        return targetFilePath;

    }

}
