package tests;

import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.AllureUtils;



@Log4j2
public class LoginTest extends BaseTest {

    static Faker faker = new Faker();

    @Test(description = "For Login use email:' ' and password:'Lenor4ik'")
    public void emailShouldBeRequiredTest() {
        log.info("Run test emailShouldBeRequired");
        loginPage.open().login("", userPassword);
        log.debug("Checking the Error Message Text");
        AllureUtils.takeScreenshot(driver);
        Assert.assertEquals(loginPage.getErrorMassageForEmailField(), "Email/Login is required.", "Error message is not correct");
        log.info("Completion test emailShouldBeRequired");
    }


    @Test(description = "For Login use email:'ev.sharnikova@gmail.com' and password:' '")
    public void passwordShouldBeRequiredTest() {
        log.info("Run test passwordShouldBeRequired");
        loginPage.open().login(userEmail, " ");
        log.debug("Checking the Error Message Text");
        AllureUtils.takeScreenshot(driver);
        Assert.assertEquals(loginPage.getErrorMassageForPasswordField(), "Password is required.", "Error message is not correct");
        log.info("Completion test emailShouldBeRequired");
    }


    @Test(description = "Authorization with invalid data. Email: '1234' and Password 'QWE12'")
    public void invalidLoginDataTest() {
        log.info("Run test invalidLoginData");
        loginPage.open().login(faker.internet().emailAddress(), faker.internet().password(2, 8));
        log.debug("Checking the Error Message Text");
        AllureUtils.takeScreenshot(driver);
        Assert.assertEquals(loginPage.getErrorMassageForAlert(), "Email/Login or Password is incorrect. Please try again.", "Error message is not correct");
        log.info("Completion test invalidLoginData");
    }


    @Test(description = "Checking user authorization on the LoginPage using valid data in the fields 'Email' and 'Password'")
    @Step("Fill in the 'Email' and 'Password' fields with valid data")
    public void loginAccountTest() throws InterruptedException {
        log.info("Run login test using correct data");
        log.info("Open the LoginPage");
        boolean isDashboardPageOpened = loginPage
                .open()
                .login(userEmail, userPassword)
                .isPageOpen();
        log.debug("Checking that the DashboardPage is open");
        AllureUtils.takeScreenshot(driver);
        Assert.assertEquals(dashboardPage.getTitlePageValue(), "Dashboard", "DashboardPage is not open");
        log.info("Completion test login");
    }
}

