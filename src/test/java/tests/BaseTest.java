package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import pages.*;
import utils.PropertyReader;

import java.util.concurrent.TimeUnit;


@Listeners(TestListener.class)
public class BaseTest {

    WebDriver driver;
    LoginPage loginPage;
    ForgotPasswordPage forgotPasswordPage;
    MysettingsPage mysettingsPage;
    DashboardPage dashboardPage;
    ProjectPage projectPage;
    AdministrationPage administrationPage;
    СonfirmationModalPage confirmationModalPage;
    protected String userEmail = "sharnikovaev@gmail.com";
    protected String userPassword = "Lenor4ik";


    @BeforeMethod(description = "Setup and start browser")
    public void setup(ITestContext context) {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        loginPage = new LoginPage(driver);
        forgotPasswordPage = new ForgotPasswordPage(driver);
        mysettingsPage = new MysettingsPage(driver);
        dashboardPage = new DashboardPage(driver);
        projectPage = new ProjectPage(driver);
        administrationPage = new AdministrationPage(driver);
        confirmationModalPage = new СonfirmationModalPage(driver);

//        BasePage.baseUrl = System.getenv().getOrDefault("TESTRAIL_URL", PropertyReader.getProperty("testrail.url"));
//        userEmail = System.getenv().getOrDefault("TESTRAIL_EMAIL", PropertyReader.getProperty("testrail.email"));
//        userPassword = System.getenv().getOrDefault("TESTRAIL_PASSWORD", PropertyReader.getProperty("testrail.password"));
    }

    @AfterMethod(alwaysRun = true, description = "Close browser")
    public void tearDown() {
        driver.quit();
    }
}

