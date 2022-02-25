package tests;
import lombok.extern.log4j.Log4j2;
import models.TestCase;
import models.TestCaseFactory;
import models.TestRun;
import models.TestRunFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.DashboardPage;
import utils.AllureUtils;


@Log4j2
public class TestRunsTest extends BaseTest {


    @BeforeMethod(description = "Login to account, open the DashboardPage")
    public DashboardPage loginToAccount() {
        loginPage
                .open()
                .login(userEmail, userPassword);
        AllureUtils.takeScreenshot(driver);
        return new DashboardPage(driver);
    }


    @Test(description = "Creating a new Test Run in a project.", priority = 1)
    public void createTestRunTest() throws InterruptedException {
        log.info("Run createTestRunTest. Open the DashboardPage");
        log.info("Start BeforeMethod: login to account, open the DashboardPage");

        log.info("Click on the name of the project on DashboardPage");
        dashboardPage.openProject("Graduation project");
        log.info("Create new TestCase");
        TestCase testCase = TestCaseFactory.get();
        overviewProjectPage
                .addTestCases()
                .createNewTestCase(testCase);
        AllureUtils.takeScreenshot(driver);
        projectPage.selectingSectionOnTheProjectPage("Overview");
        log.info("Create new TestRun");
        TestRun testRun = TestRunFactory.get();
        boolean isNewTestRunCreated = overviewProjectPage
                .addTestRun()
                .createNewTestRun(testRun)
                .isPageOpen();
        AllureUtils.takeScreenshot(driver);

        Assert.assertTrue(isNewTestRunCreated, "Test Run not created");
        Assert.assertEquals(administrationPage.popUpResultMessage(), "Successfully added the new test run.", "Test Run not created");

        log.info("Completion test createTestRunTest");
    }


    @Test(description = "Checking TestRun launch via 'Rerun' button.", priority = 2)
    public void rerunTest() {
        log.info("Run rerunTest. Open the DashboardPage");
        log.info("Start BeforeMethod: login to account, open the DashboardPage");

        log.info("Click on the name of the project on DashboardPage");
        dashboardPage.openProject("Graduation project");
        projectPage.selectingSectionOnTheProjectPage("Test Runs & Results");
        testRunsPage
                .openTestRun()
                .runTestRun();
        AllureUtils.takeScreenshot(driver);
        Assert.assertEquals(administrationPage.popUpResultMessage(), "Successfully added the new test run.", "Rerun not created");
        log.info("Completion test rerunTest");
    }


    @Test(description = "Adding the result of a Test Case run", priority = 3)
    public void addResultTestCaseAfterRun() {
        log.info("Run rerunTest. Open the DashboardPage");
        log.info("Start BeforeMethod: login to account, open the DashboardPage");

        log.info("Click on the name of the project on DashboardPage");
        dashboardPage.openProject("Graduation project");
        projectPage.selectingSectionOnTheProjectPage("Test Runs & Results");
        testRunsPage.openTestRun()
                .addTestCaseResult("Blocked");
        AllureUtils.takeScreenshot(driver);

        Assert.assertEquals(testRunDetailsPage.getValueLatestStatusTestCase(), "Blocked", "New TestCase status does not match");
    }


    @Test(description = "TestRun Uninstall Check", priority = 3)
    public void deleteAllRunTest() {
        log.info("Run deleteAllRunTest. Open the DashboardPage");
        log.info("Start BeforeMethod: login to account, open the DashboardPage");

        log.info("Click on the name of the project on DashboardPage");
        dashboardPage.openProject("Graduation project");

        projectPage.selectingSectionOnTheProjectPage("Test Runs & Results");
        testRunsPage.deleteAllRun();
        AllureUtils.takeScreenshot(driver);
        Assert.assertEquals(administrationPage.popUpResultMessage(), "Successfully deleted the test runs.", "Unable to delete selected TestRun");
        log.info("Completion test deleteAllRunTest");
    }
}
