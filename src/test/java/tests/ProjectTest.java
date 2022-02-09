package tests;

import lombok.extern.log4j.Log4j2;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import pages.AdministrationPage;
import utils.AllureUtils;


@Log4j2
public class ProjectTest extends BaseTest {

    @Test(description = "From the DashboardPage go to the project page")
    public void projectOpenTest() throws InterruptedException {
        log.info("Run test projectOpenTest. Open the DashboardPage");
        loginPage
                .open()
                .login(userEmail, userPassword);
        dashboardPage.openProject("Graduation project");
       boolean isOverviewProjectOpened = overviewProjectPage.isPageOpen();
        AllureUtils.takeScreenshot(driver);
        log.debug("Checking that the ProjectPage is open");
        Assert.assertTrue(isOverviewProjectOpened, "ProjectPage is not open");
        log.info("Completion test projectOpenTest");

    }


    @Test(description = "Deleting a project")
    public void deleteProjectTest() {
        log.info("Run test deleteProjectTest. Open the DashboardPage");
         loginPage
                .open()
                .login(userEmail, userPassword);
        administrationPage
                .open()
                .сlickOnTheNavigationItem(AdministrationPage.NAVIGATION_PROJECT);
        administrationPage.deleteProjectTest("Graduation project");
        confirmationModalPage.approveToDeleteProject();
        AllureUtils.takeScreenshot(driver);
        Assert.assertEquals(administrationPage.popUpResultMessage(), "Successfully deleted the project.", "Failed to delete project");
        log.info("Completion test deleteProjectTest");
    }
}
