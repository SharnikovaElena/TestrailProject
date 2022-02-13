package pages;

import elements.DropDownModal;
import elements.InputModal;
import elements.TextAreaModal;
import io.qameta.allure.Step;
import models.TestCase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;



public class TestCasesProjectPage extends BasePage {

    String checkboxForProjectSelection = "//span[contains(text(), '%s')]/ancestor::tr//input";
    String testCaseDeletionConfirmation = "//div[@id='casesDeletionDialog']//a[contains(text(), 'Mark as Deleted')]";
    private final static By DELETE_TEST_CASE_BUTTON = By.xpath("//a[@id='deleteCases']/span[text()='Delete']");

    public TestCasesProjectPage(WebDriver driver) {
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
    public TestCaseDetailsPage createNewTestCase(TestCase testCase){
        new InputModal(driver, "title").write(testCase.getTitle());
        new TextAreaModal(driver, "Preconditions").write(testCase.getPreconditions());
        new TextAreaModal(driver, "Steps").write(testCase.getSteps());
        new TextAreaModal(driver, "Expected Result").write(testCase.getExpectedResult());
        new InputModal(driver, "estimate").write(testCase.getEstimate());
        new InputModal(driver, "refs").write(testCase.getReferences());
        new DropDownModal(driver, "Template").selectOption(testCase.getTemplate());
        new DropDownModal(driver, "Type").selectOption(testCase.getType());
        new DropDownModal(driver, "Section").selectOption(testCase.getSection());
        new DropDownModal(driver, "Priority").selectOption(testCase.getPriority());
        new DropDownModal(driver, "Automation Type").selectOption(testCase.getAutomationType());
        return clickSave();

    }

    public TestCaseDetailsPage clickSave() {
        driver.findElement(ADD_PROJECT_SUBMIT).click();
        return new TestCaseDetailsPage(driver);
    }


    @Step("")
    public TestCasesProjectPage deleteTestCase(String nameTestCase) {
        driver.findElement(By.xpath(String.format(checkboxForProjectSelection, nameTestCase))).click();
        driver.findElement(DELETE_TEST_CASE_BUTTON).click();
        driver.findElement(By.xpath(testCaseDeletionConfirmation)).click();
        return new TestCasesProjectPage(driver);
    }

    // удалить все тест кейсы
}
