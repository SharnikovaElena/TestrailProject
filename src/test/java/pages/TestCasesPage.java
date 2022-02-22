package pages;

import elements.DropDownModal;
import elements.InputModal;
import elements.TextAreaModal;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import models.TestCase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@Log4j2
public class TestCasesPage extends BasePage {

    String checkboxForProjectSelection = "//span[contains(text(), '%s')]/ancestor::tr//input";
    String testCaseDeletionConfirmation = "//div[@id='casesDeletionDialog']//a[contains(text(), 'Mark as Deleted')]";
    String selectNameTestCase = "//a/span[@class='title'][contains(text(), '%s')]";
    private final static By DELETE_TEST_CASE_BUTTON = By.xpath("//a[@id='deleteCases']/span[text()='Delete']");
    private final static By SELECT_ALL_TESTS_CASES = By.xpath("//input[@onclick='App.Cases.onToggleAllClick(this)']");

    public TestCasesPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageOpen() throws InterruptedException {
        return isExit(TITLE_THE_OPENED_PAGE);
    }

    @Step("Get the title of the page")
    public String getTitlePageValue() {
        return driver.findElement(TITLE_THE_OPENED_PAGE).getText();
    }

    @Step("Filling out the form for creating a new Test Case")
    public TestCaseDetailsPage createNewTestCase(TestCase testCase) {
        new InputModal(driver, "title").write(testCase.getTitle());
        new TextAreaModal(driver, "Preconditions").write(testCase.getPreconditions());
        new TextAreaModal(driver, "Steps").write(testCase.getSteps());
        new TextAreaModal(driver, "Expected Result").write(testCase.getExpectedResult());
        new InputModal(driver, "estimate").write(testCase.getEstimate());
        new InputModal(driver, "refs").write(testCase.getReferences());
        new DropDownModal(driver, "Template").selectOptionForTestCase(testCase.getTemplate());
        new DropDownModal(driver, "Type").selectOptionForTestCase(testCase.getType());
        new DropDownModal(driver, "Section").selectOptionForTestCase(testCase.getSection());
        new DropDownModal(driver, "Priority").selectOptionForTestCase(testCase.getPriority());
        new DropDownModal(driver, "Automation Type").selectOptionForTestCase(testCase.getAutomationType());
        return clickSave();

    }

    @Step("Click on the save test case button")
    public TestCaseDetailsPage clickSave() {
        driver.findElement(SAVE_ADD_PROJECT_OR_TESTCASE_OR_TESTRUN).click();
        return new TestCaseDetailsPage(driver);
    }

    @Step("Checking that the test case name does not exist on the TestCasesPage.")
    public boolean nameTestCaseNotExist(String nameTestCase) {
        return driver.findElements(By.xpath(String.format(selectNameTestCase, nameTestCase))).isEmpty();
    }

    @Step("Find out the number of test cases in an open project")
    public String getNumberOfTestCasesInTheProject() {
        log.info("Determine the number of test cases on the project");
        return driver.findElement(By.xpath("//span[@class='text-secondary section-count']")).getText();
    }

    @Step("Delete one Test Case in the project by its name")
    public TestCasesPage deleteTestCase(String nameTestCase) {
        log.info("Check the checkbox named Test Case to be deleted");
        driver.findElement(By.xpath(String.format(checkboxForProjectSelection, nameTestCase))).click();
        log.info("Click on the 'Delete' button");
        driver.findElement(DELETE_TEST_CASE_BUTTON).click();
        log.info("Click on the 'Mark as Deleted' button in the Confirmation Modal");
        driver.findElement(By.xpath(testCaseDeletionConfirmation)).click();

        WebDriverWait wait = new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.not(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//a/span[@class='title'][contains(text(), 'Training Test Case')]"))));

        return new TestCasesPage(driver);
    }


    @Step("Delete all Test Cases in the project")
    public TestCasesPage deleteAllTestsCases() {
        log.info("Select all test cases of the project in the checkbox");
        driver.findElement(SELECT_ALL_TESTS_CASES).click();
        log.info("Click on the 'Delete' button");
        driver.findElement(DELETE_TEST_CASE_BUTTON).click();
        log.info("Click on the 'Mark as Deleted' button in the Confirmation Modal");
        driver.findElement(By.xpath(testCaseDeletionConfirmation)).click();
        driver.findElement(SELECT_ALL_TESTS_CASES).click();
        driver.navigate().refresh();
        return new TestCasesPage(driver);
    }
}
