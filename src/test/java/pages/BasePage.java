package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


public abstract class BasePage {


    protected static final By EMAIL_INPUT = By.id("name");
    protected static final By ERROR_MASSAGE_FOR_EMAIL_FIELD = By.xpath("//div[contains(@class, 'loginpage-message-image')]");
    protected static final By ERROR_MASSAGE_FOR_ALERT = By.xpath("//div[@class='error-text']");
    protected static final By CONTENT_HEADER_TITLE = By.xpath("//div[contains(@class, 'content-header-title')]");
    protected static final By ADMINISTRATION_TITLE = By.id("navigation-admin");

    public String baseUrl = "https://sharnikova.testrail.io/";

    WebDriver driver;
    WebDriverWait wait;


    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 20);
    }


    public abstract boolean isPageOpen() throws InterruptedException;


    public boolean isExit(By locator) {
        try {
            return driver.findElement(locator).isDisplayed();

        } catch (NoSuchElementException ex) {
            return false;
        }
    }
}
