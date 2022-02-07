package tests;

import com.github.javafaker.Faker;
import lombok.extern.log4j.Log4j2;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.AllureUtils;


@Log4j2
public class ForgotPasswordTest extends BaseTest {

    static Faker faker = new Faker();

    @Test(description = "Checking the operation of the system by resetting the password in case the user has forgotten his password")
    public void forgotPasswordTest() throws InterruptedException {
        log.info("Run test forgotPassword");
        forgotPasswordPage
                .open()
                .resetPassword(userEmail)
                .isPageOpen();
        log.debug("Checking that the 'Log into Your Account' is open");
        AllureUtils.takeScreenshot(driver);
        Assert.assertEquals(loginPage.getErrorMassageForAlert(), "Email sent successfully. Please check your email inbox for the reset password instructions.", "Error message is not correct");
        log.info("Completion test forgotPassword");
    }


    @Test(description = "Checking the system operation by resetting the password in case the user forgot his password and entered an incorrect 'Email'")
    public void forgotPasswordInvalidEmailTest() throws InterruptedException {
        log.info("Run test forgotPasswordInvalidEmail");
        forgotPasswordPage
                .open()
                .resetPasswordInvalidEmail(faker.internet().emailAddress())
                .isPageOpen();
        log.debug("Checking the text of the error message 'Unknown email address.'");
        AllureUtils.takeScreenshot(driver);
        Assert.assertEquals(loginPage.getErrorMassageForAlert(), "Unknown email address.", "Error message is not correct");
        log.info("Completion test forgotPassword");
    }

    @Test(description = "Checking the operation of the system by resetting the password in case the user forgot his password and left the 'Email' field empty")
    public void forgotPasswordEmailShouldBeRequiredTest() throws InterruptedException {
        log.info("Run test forgotPasswordEmailShouldBeRequired");
        forgotPasswordPage
                .open()
                .resetPasswordInvalidEmail("")
                .isPageOpen();
        log.debug("Checking the text of the error message 'Email Address is required.'");
        AllureUtils.takeScreenshot(driver);
        Assert.assertEquals(loginPage.getErrorMassageForEmailField(), "Email Address is required.", "Error message is not correct");
        log.info("Completion test forgotPassword");
    }


    @Test(description = "Checking if ForgotPasswordPage closes when 'Cancel' button is clicked")
    public void resetPasswordCancelTest() throws InterruptedException {
        log.info("Run test resetPasswordCancel");
        boolean isLoginPageOpened = forgotPasswordPage
                .open()
                .resetPasswordCancel()
                .isPageOpen();
        log.debug("Checking if ForgotPasswordPage is closed when the 'Cancel' button is clicked and redirected to LoginPage");
        AllureUtils.takeScreenshot(driver);
        Assert.assertEquals(forgotPasswordPage.getTitlePageValue(), "Reset Your Password", "LoginPage is not open");
        log.info("Completion test resetPasswordCancel");
    }
}
