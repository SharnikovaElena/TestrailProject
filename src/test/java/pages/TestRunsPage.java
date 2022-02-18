package pages;

import elements.DropDownModal;
import elements.InputModal;
import elements.RadioButton;
import elements.TextAreaModal;
import io.qameta.allure.Step;
import models.TestRun;
import org.openqa.selenium.WebDriver;

public class TestRunsPage extends BasePage {
    public TestRunsPage(WebDriver driver) {
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

    @Step("Filling out the form for creating a new Test Run")
    public TestRunDetailsPage createNewTestRun(TestRun testRun) {
        new InputModal(driver, "name").write(testRun.getName());
        new InputModal(driver, "refs").write(testRun.getReferences());
        new DropDownModal(driver, "Milestone").selectOptionForTestRun(testRun.getMilestone());
        new DropDownModal(driver, "Assign To").selectOptionForTestRun(testRun.getAssignTo());
        new TextAreaModal(driver, "Description").write(testRun.getDescription());
        new RadioButton(driver, "Include all test cases").selectRadioButton();
        return clickSave();
    }

    @Step("Click on the save test run button")
    public TestRunDetailsPage clickSave() {
        driver.findElement(SAVE_ADD_PROJECT_OR_TESTCASE_OR_TESTRUN).click();
        return new TestRunDetailsPage(driver);
    }
}
