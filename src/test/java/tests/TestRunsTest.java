package tests;

import lombok.extern.log4j.Log4j2;
import models.TestCase;
import models.TestCaseFactory;
import models.TestRun;
import models.TestRunFactory;
import org.checkerframework.checker.units.qual.A;
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
    public void createNewTestRun() throws InterruptedException {
        log.info("Run createNewTestRun. Open the DashboardPage");
        log.info("Start BeforeMethod: login to account, open the DashboardPage");

        log.info("Click on the name of the project on DashboardPage");
        dashboardPage.openProject("Graduation project");
        TestRun testRun = TestRunFactory.get();
        boolean isNewTestRunCreated = overviewProjectPage.addTestRun()
                .createNewTestRun(testRun)
                .isPageOpen();
        AllureUtils.takeScreenshot(driver);
        Assert.assertTrue(isNewTestRunCreated, "Test Run not created");
        Assert.assertEquals(administrationPage.popUpResultMessage(), "Successfully added the new test run.", "Test Run not created");
    }

}
