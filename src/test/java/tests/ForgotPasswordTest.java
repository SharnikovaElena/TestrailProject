package tests;

import lombok.extern.log4j.Log4j2;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.AllureUtils;

import static org.testng.Assert.assertTrue;

@Log4j2
public class ForgotPasswordTest extends BaseTest {

    @Test(description = "Checking the operation of the system by resetting the password in case the user has forgotten his password")
    public void forgotPassword() throws InterruptedException {
        log.info("Run test forgotPassword");
        forgotPasswordPage
                .open()
                .resetPassword("ev.sharnikova@gmail.com")
                .isPageOpen();
        log.debug("Checking that the 'Log into Your Account' is open");
        AllureUtils.takeScreenshot(driver);
        Assert.assertEquals(loginPage.errorText(), "Email sent successfully. Please check your email inbox for the reset password instructions.", "Error message is not correct");
        log.info("Completion test forgotPassword");
    }

    @Test(description = "Checking if ForgotPasswordPage closes when 'Cancel' button is clicked")
    public void resetPasswordCancel() throws InterruptedException {
        log.info("Run test resetPasswordCancel");
        boolean isLoginPageOpened = forgotPasswordPage
                .open()
                .resetPasswordCancel()
                .isPageOpen();
        log.debug("Checking if ForgotPasswordPage is closed when the 'Cancel' button is clicked and redirected to LoginPage");
        AllureUtils.takeScreenshot(driver);
        assertTrue(isLoginPageOpened, "LoginPage is not open");
        log.info("Completion test resetPasswordCancel");
    }
}
