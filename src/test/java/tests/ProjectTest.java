package tests;

import com.github.javafaker.Faker;
import lombok.extern.log4j.Log4j2;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import pages.AdministrationPage;
import utils.AllureUtils;


@Log4j2
public class ProjectTest extends BaseTest {

    static Faker faker = new Faker();

    @Test(description = "From the DashboardPage go to the project page")
    public void openProjectTest() throws InterruptedException {
        log.info("Run test projectOpenTest. Open the DashboardPage");
        loginPage
                .open()
                .login(userEmail, userPassword);
        dashboardPage.openProject("Graduation project");
        boolean isProjectOpened = projectPage.isPageOpen();
        AllureUtils.takeScreenshot(driver);
        log.debug("Checking that the ProjectPage is open");
        Assert.assertTrue(isProjectOpened, "ProjectPage is not open");
        log.info("Completion test projectOpenTest");
    }

    @Test(description = "Checking the ability to edit project data")
    public void editProjectTest() throws InterruptedException {
        log.info("editProjectTest. Open the DashboardPage");
        loginPage
                .open()
                .login(userEmail, userPassword);
        log.info("Click on the name of the project on DashboardPage");
        dashboardPage.openProject("Graduation project");
        log.info("Click on the Edit button and make changes to the project");
        overviewProjectPage.editProject(faker.backToTheFuture().character());
        log.debug("Verify that changes to the project have been successfully saved");
        Assert.assertEquals(administrationPage.popUpResultMessage(), "Successfully updated the project.", "Failed to edit project");
        log.info("Completion editProjectTest");
    }


    @Test(description = "Deleting a project", enabled = false)
    public void deleteProjectTest() {
        log.info("Run test deleteProjectTest. Open the DashboardPage");
         loginPage
                .open()
                .login(userEmail, userPassword);
        administrationPage
                .open()
                .clickOnTheNavigationItem();
        administrationPage.deleteProjectTest("Graduation project");
        AllureUtils.takeScreenshot(driver);
        confirmationModalPage.approveToDeleteProject();
        AllureUtils.takeScreenshot(driver);
        Assert.assertEquals(administrationPage.popUpResultMessage(), "Successfully deleted the project.", "Failed to delete project");
        log.info("Completion test deleteProjectTest");
    }
}
