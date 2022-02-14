package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
public class LoginPage extends BasePage {

    private static final By PASSWORD_INPUT = By.id("password");
    private final By LOGIN_BUTTON = By.id("button_primary");
    private static final By ERROR_MASSAGE_FOR_PASSWORD_FIELD = By.xpath("//div/input[@id='password']/ancestor::div[@class='form-group']/ancestor::form/div[@class='display-flex']//div[@class='loginpage-message-image loginpage-message ']");


    public LoginPage(WebDriver driver) {
        super(driver);
    }


    @Override
    public boolean isPageOpen() throws InterruptedException {
        return isExit(LOGIN_BUTTON);
    }

    @Step("Get the title of the page")
    public String getTitlePageValue() {
        return driver.findElement(LOGIN_BUTTON).getText();
    }

    @Step("Opening the LoginPage")
    public LoginPage open() {
        log.info("Waiting for the LoginPage to open");
        driver.get(baseUrl);
        return this;
    }

    @Step("Filling in the 'Email' and 'Password' fields in the authorization form, clicking on the 'Log In' button and going to the DashboardPage")
    public DashboardPage login(String email, String password) {
        log.debug("Starting the login method on the LoginPage");
        driver.findElement(EMAIL_INPUT).sendKeys(email);
        driver.findElement(PASSWORD_INPUT).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();
        log.debug("Completing the login method on the LoginPage");
        return new DashboardPage(driver);
    }

    @Step("Error message text in case of missing data in the 'Email' field")
    public String getErrorMassageForEmailField() {
        log.info("Waiting for The error message 'Email/Login is required.'");
        return driver.findElement(ERROR_MASSAGE_FOR_EMAIL_FIELD).getText();
    }

    @Step("Error message text in case of missing data in the 'Password' field")
    public String getErrorMassageForPasswordField() {
        log.info("Waiting for The error message 'Password is required.'");
        return driver.findElement(ERROR_MASSAGE_FOR_PASSWORD_FIELD).getText();
    }

    @Step("Message text in Alert in case using incorrect data in the Email and/or Password fields, as well as in case of a successful password reset")
    public String getErrorMassageForAlert() {
        return driver.findElement(ERROR_MASSAGE_FOR_ALERT).getText();
    }

}
