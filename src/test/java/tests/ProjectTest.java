package tests;

import com.github.javafaker.Faker;
import lombok.extern.log4j.Log4j2;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.AdministrationPage;
import pages.DashboardPage;
import utils.AllureUtils;


@Log4j2
public class ProjectTest extends BaseTest {

    static Faker faker = new Faker();


    @BeforeMethod (description = "Login to account, open the DashboardPage")
    public DashboardPage loginToAccount() {
        loginPage
                .open()
                .login(userEmail, userPassword);
        return new DashboardPage(driver);
    }


    @Test(description = "From the DashboardPage go to the project page")
    public void openProjectTest() throws InterruptedException {
        log.info("Run test projectOpenTest. Open the DashboardPage");
        log.info("Start BeforeMethod: login to account, open the DashboardPage");

        dashboardPage.openProject("Graduation project");
        boolean isProjectOpened = projectPage.isPageOpen();
        AllureUtils.takeScreenshot(driver);
        log.debug("Checking that the ProjectPage is open");
        Assert.assertTrue(isProjectOpened, "ProjectPage is not open");
        log.info("Completion test projectOpenTest");
    }


    //перенести в создание проекта
    @Test(description = "Checking the ability to edit project data")
    public void editProjectTest() throws InterruptedException {
        log.info("Run test editProjectTest. Open the DashboardPage");
        log.info("Start BeforeMethod: login to account, open the DashboardPage");

        log.info("Click on the name of the project on DashboardPage");
        dashboardPage.openProject("Graduation project");
        log.info("Click on the Edit button and make changes to the project");
        overviewProjectPage.editProject(faker.backToTheFuture().character());
        log.debug("Verify that changes to the project have been successfully saved");
        Assert.assertEquals(administrationPage.popUpResultMessage(), "Successfully updated the project.", "Failed to edit project");
        log.info("Completion editProjectTest");
    }

// перенести в создание-редактироыание и удаление проекта
    @Test(description = "Deleting a project", enabled = false)
    public void deleteProjectTest() {
        log.info("Run test deleteProjectTest. Open the DashboardPage");
        log.info("Start BeforeMethod: login to account, open the DashboardPage");

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
