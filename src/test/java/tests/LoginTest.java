package tests;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.AllureUtils;

import static org.testng.Assert.assertTrue;


@Log4j2
public class LoginTest extends BaseTest {

    @Test(description = "For Login use email:' ' and password:'Lenor4ik'")
    public void emailShouldBeRequired() {
        log.info("Run test emailShouldBeRequired");
        loginPage.open().login("", "Lenor4ik");
        log.debug("Checking the Error Message Text");
        AllureUtils.takeScreenshot(driver);
        Assert.assertEquals(loginPage.getErrorMassageForEmailField(), "Email/Login is required.", "Error message is not correct");
        log.info("Completion test emailShouldBeRequired");
    }


    @Test(description = "For Login use email:'ev.sharnikova@gmail.com' and password:' '")
    public void passwordShouldBeRequired() {
        log.info("Run test passwordShouldBeRequired");
        loginPage.open().login("ev.sharnikova@gmail.com", " ");
        log.debug("Checking the Error Message Text");
        AllureUtils.takeScreenshot(driver);
        Assert.assertEquals(loginPage.getErrorMassageForPasswordField(), "Password is required.", "Error message is not correct");
        log.info("Completion test emailShouldBeRequired");
    }


    @Test(description = "Authorization with invalid data. Email: '1234' and Password 'QWE12'")
    public void invalidLoginData() {
        log.info("Run test invalidLoginData");
        loginPage.open().login("1234", "QWE12");
        log.debug("Checking the Error Message Text");
        AllureUtils.takeScreenshot(driver);
        Assert.assertEquals(loginPage.getErrorMassageForInvalidData(), "Email/Login or Password is incorrect. Please try again.", "Error message is not correct");
        log.info("Completion test invalidLoginData");
    }


    @Test(description = "Checking user authorization on the LoginPage using valid data in the fields 'Email' and 'Password'")
    @Step("Fill in the 'Email' and 'Password' fields with valid data")
    public void login() throws InterruptedException {
        log.info("Run login test using correct data");
        log.info("Open the LoginPage");
        boolean isDashboardPageOpened = loginPage
                .open()
                .login("ev.sharnikova@gmail.com", "Lenor4ik")
                .isPageOpen();
        log.debug("Checking that the DashboardPage is open");
        AllureUtils.takeScreenshot(driver);
        assertTrue(isDashboardPageOpened, "DashboardPage is not open");
        log.info("Completion test login");
    }
}

