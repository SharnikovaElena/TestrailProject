package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;

public class AdministrationPage extends BasePage {


    String deleteProject = "//a[contains(text(),'%s')]/ancestor::tr//div[@class='icon-small-delete']";
    private static final By POP_UP_RESULT_MESSAGE = By.xpath("//div[@class ='message message-success']");
    public static final By NAVIGATION_PROJECT = By.id("navigation-sub-projects");
    protected static final By MESSAGE_TO_ADMIN = By.xpath("//p[text()='Manage projects, users and global settings.']");
    protected static final By NAVIGATION_OVERWIEW = By.id("navigation-sub-overview");
    protected static final By NAVIGATION_USERS_ROLES = By.id("navigation-sub-users");
    protected static final By NAVIGATION_CUSTOMIZATION = By.id("navigation-sub-customization");
    protected static final By NAVIGATION_DATA_MANAGEMENT = By.id("navigation-sub-subscription");
    protected static final By NAVIGATION_INTEGRATION = By.id("navigation-sub-integration");
    protected static final By NAVIGATION_SITE_SETTINGS = By.id("navigation-sub-sitesettings");


    public AdministrationPage(WebDriver driver) {
        super(driver);
    }


    @Override
    public boolean isPageOpen() throws InterruptedException {
        return isExit(MESSAGE_TO_ADMIN);
    }

//    @Step("Get the title of the page")
//    public String getMessageToAdmin() {
//        return driver.findElement(MESSAGE_TO_ADMIN).getText();
//    }

    @Step("Open sidebar Administration menu")
    public AdministrationPage open() {
        driver.findElement(ADMINISTRATION_TITLE).click();
        return new AdministrationPage(driver);
    }


    @Step("Сlick on the navigation elements in the Administration menu")
    public void clickOnTheNavigationItem() {
        driver.findElement(NAVIGATION_PROJECT).click();
    }


    @Step("Delete a project by its name")
    public AdministrationPage deleteProjectTest(String projectName) {
        driver.findElement(By.xpath(String.format(deleteProject, projectName))).click();
        return new AdministrationPage(driver);
    }


    @Step("We receive a notification about the results of an action from the project")
    public String popUpResultMessage() {
        return driver.findElement(POP_UP_RESULT_MESSAGE).getText();
    }
}
