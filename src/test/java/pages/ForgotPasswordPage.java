package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
public class ForgotPasswordPage extends BasePage {

    private static final By RESET_PASSWORD_BUTTON = By.xpath("//button[contains(@class, 'forgot')]");
    private static final By RESET_PASSWORD_TEXT = By.xpath("//h1[@class='loginpage-login-text']");
    private static final By CANCEL_BUTTON = By.xpath("//a/span[contains(@class, 'forgot')]");
    private static final By LOGINPAGE_FORGOTPASSWORD = By.xpath("//a[@class='loginpage-forgotpassword']");


    public ForgotPasswordPage(WebDriver driver) {
        super(driver);
    }


    @Step("Opening the ForgotPasswordPage")
    public ForgotPasswordPage open() {
        log.info("Waiting for the ForgotPasswordPage to open");
        driver.get(BASE_URL);
        driver.findElement(LOGINPAGE_FORGOTPASSWORD).click();
        log.info("The ForgotPasswordPage is open");
        return this;
    }

    @Override
    public boolean isPageOpen() throws InterruptedException {
        return isExit(RESET_PASSWORD_TEXT);
    }

    @Step("Fill in the 'Email' field and click on the 'Reset Password' button")
    public LoginPage resetPassword(String email) {
        log.info("Enter 'ev.sharnikova@gmail.com' in the Email field");
        driver.findElement(EMAIL_INPUT).sendKeys(email);
        driver.findElement(RESET_PASSWORD_BUTTON).click();
        log.debug("Completing the resetPassword method on the ForgotPasswordPage");
        return new LoginPage(driver);
    }

    @Step("On ForgotPasswordPage click on the 'Cancel' button to return to the LoginPage")
    public LoginPage resetPasswordCancel() {
        log.debug("Click on the 'Cancel' button");
        driver.findElement(CANCEL_BUTTON).click();
        log.debug("Completing the resetPasswordCancel method and Go to LoginPage");
        return new LoginPage(driver);
    }
}
