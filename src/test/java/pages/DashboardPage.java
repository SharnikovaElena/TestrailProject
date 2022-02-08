package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
public class DashboardPage extends BasePage {

    private static final By NAVIGATION_USERNAME = By.className("navigation-username");
    private static final By NAVIGATION_USER_SETTING = By.id("navigation-user-settings");
    private static final By LOGOUT = By.id("navigation-user-logout");
    private static final By ADD_EXAMPLE_PROJECT_BUTTON = By.id("navigation-empty-addexampleproject");
    private static final By ADD_PROJECT_BUTTON = By.id("sidebar-projects-add");
    private static final By EXAMPLE_PROJECT_INPUT = By.xpath("//input[@id='addProjectName']");
    private static final By ADD_EXAMPLE_PROJECT_SUBMIT = By.xpath("//button[@id='addProjectSubmit']");
    private static final By ADD_PROJECT_SUBMIT = By.id("accept");

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageOpen() throws InterruptedException {
        return isExit(CONTENT_HEADER_TITLE);
    }

    @Step("Get the title of the page")
    public String getTitlePageValue() {
        return driver.findElement(CONTENT_HEADER_TITLE).getText();
    }

    @Step("Go to MysettingsPage")
    public MysettingsPage navigationUserSettings() {
        log.info("Find the Navigation Username element and click on it");
        driver.findElement(NAVIGATION_USERNAME).click();
        log.debug("Click on an item from the My Settings list");
        driver.findElement(NAVIGATION_USER_SETTING).click();
        return new MysettingsPage(driver);
    }

    @Step("Log out of the account")
    public LoginPage navigationUserLogout() {
        log.info("Find the Navigation Username element and click on it");
        driver.findElement(NAVIGATION_USERNAME).click();
        log.debug("Click on an item from the Logout list");
        driver.findElement(LOGOUT).click();
        return new LoginPage(driver);
    }

    public DashboardPage addExampleProject(String projectName) {
        driver.findElement(ADD_EXAMPLE_PROJECT_BUTTON).click();
        driver.findElement(EXAMPLE_PROJECT_INPUT).sendKeys(projectName);
        driver.findElement(ADD_EXAMPLE_PROJECT_SUBMIT).click();
        return new DashboardPage(driver);
    }

    public AdministrationPage addNewProject(String newProjectName, String announcement) {
        driver.findElement(ADD_PROJECT_BUTTON).click();
        driver.findElement(By.id("name")).sendKeys(newProjectName);
        driver.findElement(By.id("announcement")).sendKeys(announcement);
        driver.findElement(ADD_PROJECT_SUBMIT).click();
        return new AdministrationPage(driver);
    }

}
