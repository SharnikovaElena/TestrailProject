package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OverviewProjectPage extends BasePage {

    private final static By CHART = By.id("chart-line-fc");
    private final static By OVERVIEW_TAB = By.id("navigation-projects");
    private final static By EDIT_BUTTON = By.xpath("//a[contains(@class, 'button-edit')]");
    private final static By ADD_SUITES = By.id("sidebar-suites-add");
    private final static By ADD_TEST_CASE_LINK = By.id("sidebar-cases-add");
    private final static By VIEW_ALL_CASE_LINK = By.id("sidebar-suites-viewall");
    private final static By ADD_TEST_RUN_LINK = By.id("sidebar-runs-add");
    private final static By VIEW_ALL_RUNS_LINK = By.id("sidebar-runs-viewall");
    private final static By ADD_MILESTONES_LINK = By.id("sidebar-milestones-add");
    private final static By VIEW_ALL_MILESTONES_LINK = By.id("sidebar-milestones-viewall");

    public OverviewProjectPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageOpen() throws InterruptedException {
        return isExit(CHART);
    }

    @Step("Go to the Overview tab on the project page")
    public OverviewProjectPage open() {
        driver.findElement(OVERVIEW_TAB).click();
        return new OverviewProjectPage(driver);
    }

    @Step("Project editing")
    public OverviewProjectPage editProject(String announcement) {
        driver.findElement(EDIT_BUTTON).click();
        driver.findElement(By.id("announcement")).sendKeys(announcement);
        driver.findElement(SAVE_ADD_PROJECT_OR_TESTCASE_OR_TESTRUN).click();
        return new OverviewProjectPage(driver);
    }
    @Step("Click on the Add Test Case link and go to the page TestCasesPage")
    public TestCasesPage addTestCases() {
        driver.findElement(ADD_TEST_CASE_LINK).click();
        return new TestCasesPage(driver);
    }


    @Step("Click on the Add Test Run link and go to the page TestRunsPage")
    public TestRunDetailsPage addTestRun() {
        driver.findElement(ADD_TEST_RUN_LINK).click();
        return new TestRunDetailsPage(driver);
    }
}
