package pages;

import com.github.javafaker.Faker;
import elements.DropDownModal;
import elements.InputModal;
import elements.RadioButton;
import elements.TextAreaModal;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import models.TestRun;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.AllureUtils;

@Log4j2
public class TestRunDetailsPage extends BasePage {

    static Faker faker = new Faker();
    public static final By RERUN_BUTTON = By.xpath("//a/span[contains(text(), 'Rerun')]");
    public static final By BUTTON_OK = By.xpath("//div[contains(text(), 'Select Tests')]/ancestor::div//button[contains(text(), 'OK')]");
    public static final By BUTTON_ADD_RESULT = By.xpath("//a[@id='sidebar-tests-addresult']/span");
    public static final By BUTTON_CONFIRMING_ADD_RESULT = By.xpath("//button[@id='addResultSubmit']/span[contains(text(), 'Add Result')]");

    public TestRunDetailsPage(WebDriver driver) {
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


    @Step("Click on the 'Rerun' button")
    public TestRunDetailsPage runTestRun() {
        log.info("Click on the 'Rerun' button");
        driver.findElement(RERUN_BUTTON).click();
        driver.findElement(BUTTON_OK).click();
        return clickSave();
    }


    @Step("Adding the result of a Test Case run")
    public TestRunDetailsPage addTestCaseResult(String status) {
        driver.findElement(By.xpath("(//td[@class='id']/a)[1]")).click();
        driver.findElement(BUTTON_ADD_RESULT).click();
        new DropDownModal(driver, "Status").selectOptionForTestRun(status);
        new DropDownModal(driver, "Assign To").selectOptionForTestRun("Me");
        new TextAreaModal(driver, "Comment").write(faker.backToTheFuture().character());
        AllureUtils.takeScreenshot(driver);
        driver.findElement(BUTTON_CONFIRMING_ADD_RESULT).click();
        driver.navigate().refresh();
        return new TestRunDetailsPage(driver);
    }


    @Step("Take the value of the latest status TestCase")
    public String getValueLatestStatusTestCase() {
        AllureUtils.takeScreenshot(driver);
        return driver.findElement(By.xpath("(//span[@class='status'])[1]")).getText();
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
