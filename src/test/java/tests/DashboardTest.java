package tests;

import com.github.javafaker.Faker;
import lombok.extern.log4j.Log4j2;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.AllureUtils;


@Log4j2
public class DashboardTest extends BaseTest {

    static Faker faker = new Faker();


    @Test(description = "Opening the DashboardPage", priority = 1)
    public void isDashboardPageOpened() throws InterruptedException {
        log.info("Run test isDashboardPageOpened");
        boolean isDashboardPageOpened = loginPage
                .open()
                .login(userEmail, userPassword)
                .isPageOpen();
        AllureUtils.takeScreenshot(driver);
        log.debug("Checking that the DashboardPage is open");
        log.info("Completion test isDashboardPageOpened");
    }

    @Test(description = "Сhecking the transition to the MysettingsPage")
    public void transitionToMysettingsPageTest() throws InterruptedException {
        log.info("Run test checkingTransitionToMysettingsPage. Open the DashboardPage");
        isDashboardPageOpened();
        log.info("Let's go to the MysettingsPage");
        boolean isMysettingsPageOpened = dashboardPage
                .navigationUserSettings()
                .isPageOpen();
        AllureUtils.takeScreenshot(driver);
        Assert.assertEquals(mysettingsPage.getTitlePageValue(), "My Settings", "MySettingsPage is not open");
        log.info("Completion test checkingTransitionToMysettingsPage");
    }

    @Test(description = "Log out of the account")
    public void navigationUserLogoutTest() throws InterruptedException {
        log.info("Run test checkingNavigationUserLogout. Open the DashboardPage");
        isDashboardPageOpened();
        boolean isLoginPageOpened = dashboardPage
                .navigationUserLogout()
                .isPageOpen();
        AllureUtils.takeScreenshot(driver);
        Assert.assertEquals(loginPage.getTitlePageValue(), "Log In", "LoginPage is not open");
        log.info("Completion test checkingNavigationUserLogout");
    }


    @Test(description = "Add Example Project", priority = 2) //enabled = false
    public void addExampleProjectTest() throws InterruptedException {
        log.info("Run test addExampleProjectTest. Open the DashboardPage");
        isDashboardPageOpened();
        dashboardPage.addExampleProject(faker.animal().name());
        boolean isExampleProjectCreate = overviewProjectPage.isPageOpen();
        AllureUtils.takeScreenshot(driver);
        log.debug("Сheck that the Example Project has been created");
        Assert.assertTrue(isExampleProjectCreate, "ExampleProject is not created");
        log.info("Completion addExampleProjectTest");
    }


    @Test(description = "Creating a new project on a DashboardPage", priority = 3)
    public void addNewProjectTest() throws InterruptedException {
        log.info("Run test addNewProjectTest. Open the DashboardPage");
        isDashboardPageOpened();
        dashboardPage.addNewProject(faker.funnyName().name(), faker.book().publisher());
        AllureUtils.takeScreenshot(driver);
        log.debug("Сheck that the New Project has been created");
        Assert.assertEquals(administrationPage.popUpResultMessage(), "Successfully added the new project.", "Failed to add the new project.");
        log.info("Completion test addNewProjectTest");
    }

    @Test(description = "Creation of a working project for a graduation project", priority = 4)
    public void addTestProject() throws InterruptedException {
        log.info("Run test addNewProjectTest. Open the DashboardPage");
        isDashboardPageOpened();
        dashboardPage.addNewProject("Graduation project", faker.book().publisher());
        AllureUtils.takeScreenshot(driver);
        log.debug("Сheck that the New Project has been created");
        Assert.assertEquals(administrationPage.popUpResultMessage(), "Successfully added the new project.", "Failed to add the new project.");
        log.info("Completion test addNewProjectTest");
    }

}
