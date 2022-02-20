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

    @BeforeMethod(description = "Login to account, open the DashboardPage")
    public DashboardPage loginToAccount() {
        loginPage
                .open()
                .login(userEmail, userPassword);
        AllureUtils.takeScreenshot(driver);
        return new DashboardPage(driver);
    }


    @Test(description = "Add Example Project", enabled = false)
    public void addExampleProjectTest() throws InterruptedException {
        log.info("Run test addExampleProjectTest. Open the DashboardPage");
        log.info("Start BeforeMethod: login to account, open the DashboardPage");

        dashboardPage.addExampleProject(faker.animal().name());
        boolean isExampleProjectCreate = overviewProjectPage.isPageOpen();
        AllureUtils.takeScreenshot(driver);
        log.debug("Сheck that the Example Project has been created");
        Assert.assertTrue(isExampleProjectCreate, "ExampleProject is not created");
        log.info("Completion addExampleProjectTest");
    }


    @Test(description = "Creation of a working project for a graduation project", priority = 1)
    public void addProjectTest() throws InterruptedException {
        log.info("Run test addNewProjectTest. Open the DashboardPage");
        log.info("Start BeforeMethod: login to account, open the DashboardPage");

        dashboardPage.addNewProject("Graduation project " + faker.number().randomDigit(), faker.book().publisher());
        AllureUtils.takeScreenshot(driver);
        log.debug("Сheck that the New Project has been created");
        Assert.assertEquals(administrationPage.popUpResultMessage(), "Successfully added the new project.", "Failed to add the new project.");
        log.info("Completion test addNewProjectTest");
    }


    @Test(description = "From the DashboardPage go to the project page", priority = 2)
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


    @Test(description = "Checking the ability to edit project data", priority = 3)
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


    @Test(description = "Deleting a project", priority = 4)
    public void deleteProjectTest() {
        log.info("Run test deleteProjectTest. Open the DashboardPage");
        log.info("Start BeforeMethod: login to account, open the DashboardPage");
        log.info("Create a new TestProject");

        dashboardPage.addNewProject("TestProject", faker.backToTheFuture().character());
        log.info("Delete the TestProject project");
        administrationPage
                .open()
                .clickOnTheNavigationItem();
        administrationPage.deleteProjectTest("TestProject");
        AllureUtils.takeScreenshot(driver);
        confirmationModalPage.approveToDeleteProject();
        AllureUtils.takeScreenshot(driver);
        Assert.assertEquals(administrationPage.popUpResultMessage(), "Successfully deleted the project.", "Failed to delete project");
        log.info("Completion test deleteProjectTest");
    }
}
