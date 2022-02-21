package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestContext;
import org.testng.annotations.*;
import pages.*;
import utils.PropertyReader;

import java.util.concurrent.TimeUnit;


@Log4j2
@Listeners(TestListener.class)
public abstract class BaseTest {

    WebDriver driver;
    LoginPage loginPage;
    ForgotPasswordPage forgotPasswordPage;
    MySettingsPage mysettingsPage;
    DashboardPage dashboardPage;
    AdministrationPage administrationPage;
    СonfirmationModalPage confirmationModalPage;
    OverviewProjectPage overviewProjectPage;
    TestCasesPage testCasesPage;
    TestRunsPage testRunsPage;
    TestCaseDetailsPage testCaseDetailsPage;
    ProjectPage projectPage;
    TestRunDetailsPage testRunDetailsPage;


    protected String userEmail;
    protected String userPassword;

    @Parameters({"browser"})
    @BeforeMethod(description = "Setup and start browser")
    public void setup(ITestContext context, @Optional("chrome") String browser) {

        log.info("browser: " + browser);

        if (browser.contains("chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--window-size=1920,1080");
            options.addArguments("--start-maximized");
            options.addArguments("--disable-popup-blocking");
            driver = new ChromeDriver(options);

        } else if (browser.contains("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--window-size=1920,1080");
            options.addArguments("--start-maximized");
            options.addArguments("--disable-popup-blocking");
            driver = new FirefoxDriver(options);

        } else if (browser.contains("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }

        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        context.setAttribute("driver", driver);

        BasePage.baseUrl = System.getenv().getOrDefault("TESTRAIL_URL", PropertyReader.getProperty("testrail.url"));
        userEmail = System.getenv().getOrDefault("TESTRAIL_EMAIL", PropertyReader.getProperty("testrail.email"));
        userPassword = System.getenv().getOrDefault("TESTRAIL_PASSWORD", PropertyReader.getProperty("testrail.password"));

        loginPage = new LoginPage(driver);
        forgotPasswordPage = new ForgotPasswordPage(driver);
        mysettingsPage = new MySettingsPage(driver);
        dashboardPage = new DashboardPage(driver);
        administrationPage = new AdministrationPage(driver);
        confirmationModalPage = new СonfirmationModalPage(driver);
        overviewProjectPage = new OverviewProjectPage(driver);
        testCasesPage = new TestCasesPage(driver);
        testRunsPage = new TestRunsPage(driver);
        testCaseDetailsPage = new TestCaseDetailsPage(driver);
        projectPage = new ProjectPage(driver);
        testRunDetailsPage = new TestRunDetailsPage(driver);

    }

    @AfterMethod(alwaysRun = true, description = "Close browser")
    public void tearDown() {
        driver.quit();
    }
}

