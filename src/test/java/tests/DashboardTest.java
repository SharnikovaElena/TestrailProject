package tests;

import com.github.javafaker.Faker;
import lombok.extern.log4j.Log4j2;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.DashboardPage;
import utils.AllureUtils;


@Log4j2
public class DashboardTest extends BaseTest {

    static Faker faker = new Faker();

    @BeforeMethod (description = "Login to account, open the DashboardPage")
    public DashboardPage loginToAccount() {
        loginPage
                .open()
                .login(userEmail, userPassword);
        AllureUtils.takeScreenshot(driver);
        return new DashboardPage(driver);
    }


    @Test(description = "Сhecking the transition to the MySettingsPage", priority = 1)
    public void selectMenuUserMySettingsTest() throws InterruptedException {
        log.info("Run test checkingTransitionToMySettingsPage");
        log.info("Start BeforeMethod: login to account, open the DashboardPage");

        boolean isMySettingsPageOpened = dashboardPage
                .selectMenuUserMySettings()
                .isPageOpen();
        AllureUtils.takeScreenshot(driver);
        Assert.assertEquals(mysettingsPage.getTitlePageValue(), "My Settings", "MySettingsPage is not open");
        log.info("Completion test checkingTransitionToMySettingsPage");
    }


    @Test(description = "Log out of the account", priority = 2)
    public void selectMenuUserLogoutTest() throws InterruptedException {
        log.info("Run test checkingNavigationUserLogout");
        log.info("Start BeforeMethod: login to account, open the DashboardPage");
        boolean isLoginPageOpened = dashboardPage
                .selectMenuUserLogout()
                .isPageOpen();
        AllureUtils.takeScreenshot(driver);
        Assert.assertEquals(loginPage.getTitlePageValue(), "Log In", "LoginPage is not open");
        log.info("Completion test checkingNavigationUserLogout");
    }


    @Test(description = "Add Example Project", priority = 3) //enabled = false
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


    @Test(description = "Creation of a working project for a graduation project", priority = 4)
    public void addProjectTest() throws InterruptedException {
        log.info("Run test addNewProjectTest. Open the DashboardPage");
        log.info("Start BeforeMethod: login to account, open the DashboardPage");

        dashboardPage.addNewProject("Graduation project " + faker.number().randomDigit(), faker.book().publisher());
        AllureUtils.takeScreenshot(driver);
        log.debug("Сheck that the New Project has been created");
        Assert.assertEquals(administrationPage.popUpResultMessage(), "Successfully added the new project.", "Failed to add the new project.");
        log.info("Completion test addNewProjectTest");
    }
}
