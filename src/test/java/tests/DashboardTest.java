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

    @BeforeMethod(description = "Login to account, open the DashboardPage")
    public DashboardPage loginToAccount() {
        loginPage
                .open()
                .login(userEmail, userPassword);
        AllureUtils.takeScreenshot(driver);
        return new DashboardPage(driver);
    }


    @Test(description = "Сhecking the transition to the MySettingsPage")
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


    @Test(description = "Log out of the account")
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
}
