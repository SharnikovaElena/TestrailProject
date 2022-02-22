package tests;

import com.github.javafaker.Faker;
import lombok.extern.log4j.Log4j2;
import models.TestCase;
import models.TestCaseFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.DashboardPage;
import utils.AllureUtils;

@Log4j2
public class TestCaseTest extends BaseTest {


    @BeforeMethod(description = "Login to account, open the DashboardPage")
    public DashboardPage loginToAccount() {
        loginPage
                .open()
                .login(userEmail, userPassword);
        AllureUtils.takeScreenshot(driver);
        return new DashboardPage(driver);
    }


    @Test(description = "Creating a new Test Case in a project.", priority = 1)
    public void createNewTestCase() {
        log.info("Run createNewTestCase. Open the DashboardPage");
        log.info("Start BeforeMethod: login to account, open the DashboardPage");

        log.info("Click on the name of the project on DashboardPage");
        dashboardPage.openProject("Graduation project");

        log.info("Opening a form for describing a new test case. Creating a new test case");
        TestCase testCase = TestCaseFactory.get();
        overviewProjectPage
                .addTestCases()
                .createNewTestCase(testCase);
        AllureUtils.takeScreenshot(driver);
        Assert.assertEquals(administrationPage.popUpResultMessage(), "Successfully added the new test case. Add another", "Test case not created");
        log.info("Completion test createNewTestCase");
    }

    @Test(description = "Create a test case named 'Training Test Case'", priority = 2)
    public void createTrainingTestCase() {
        log.info("Run createTrainingTestCase. Open the DashboardPage");
        log.info("Start BeforeMethod: login to account, open the DashboardPage");

        log.info("Click on the name of the project on DashboardPage");
        dashboardPage.openProject("Graduation project");

        log.info("Opening a form for describing a new test case. Creating a new test case");
        TestCase testCase = new TestCase("Training Test Case",
                "Test Cases",
                "Test Case (Text)",
                "Functional",
                "Critical",
                "2 hours",
                "https://jira.elsharnikova.com/",
                "None",
                "We go to the TestRail website, create a test project called 'Graduation project'",
                "On the right sidebar, click on the Add button in the Test Cases section.\n" +
                        "Fill in all the fields with the necessary information.\n" +
                        "Click on the 'Add Test Case' button",
                "We check that the test case has been created and the content of all fields corresponds to the information that was entered when creating the project");
        overviewProjectPage
                .addTestCases()
                .createNewTestCase(testCase);
        AllureUtils.takeScreenshot(driver);
        Assert.assertEquals(administrationPage.popUpResultMessage(), "Successfully added the new test case. Add another", "Test case not created");
        Assert.assertEquals(testCaseDetailsPage.getContainedTextInTheField("Type"), "Type\n" + testCase.getType(), "Type does not match");
        Assert.assertEquals(testCaseDetailsPage.getContainedTextInTheField("Priority"), "Priority\n" + testCase.getPriority(), "Priority does not match");
        Assert.assertEquals(testCaseDetailsPage.getContainedTextInTheField("Estimate"), "Estimate\n" + testCase.getEstimate(), "Estimate does not match");
        Assert.assertEquals(testCaseDetailsPage.getContainedTextInTheField("References"), "References\n" + testCase.getReferences(), "References does not match");
        Assert.assertEquals(testCaseDetailsPage.getContainedTextInTheField("Automation Type"), "Automation Type\n" + testCase.getAutomationType(), "Automation Type does not match");
        Assert.assertEquals(testCaseDetailsPage.getContainedTextInTheField("Estimate"), "Estimate\n" + testCase.getEstimate(), "Estimate does not match");
        Assert.assertEquals(testCaseDetailsPage.getContainedTextInThePreconditionsField(), testCase.getPreconditions(), "Preconditions does not match");
        Assert.assertEquals(testCaseDetailsPage.getContainedTextInTheExpectedResultField(), testCase.getExpectedResult(), "Expected Result does not match");
        Assert.assertEquals(testCaseDetailsPage.getContainedTextInTheStepsField(), testCase.getSteps(), "Steps does not match");
        log.info("Completion test createTrainingTestCase");

    }

    @Test(description = "Deleting a test case named 'Training Test Case'", priority = 3)
    public void deleteTestCaseByName() {
        log.info("Run deleteTestCaseByName. Open the DashboardPage");
        log.info("Start BeforeMethod: login to account, open the DashboardPage");

        log.info("Click on the name of the project on DashboardPage");
        dashboardPage.openProject("Graduation project");
        log.info("Go to the test cases section");
        projectPage.selectingSectionOnTheProjectPage("Test Cases");
        AllureUtils.takeScreenshot(driver);
        Assert.assertEquals(testCasesPage.getTitlePageValue(), "Test Cases", "Failed to open 'Test Cases' page");

        log.info("Go to the test cases section");
        testCasesPage.deleteTestCase("Training Test Case");
        AllureUtils.takeScreenshot(driver);

        Assert.assertTrue(testCasesPage.nameTestCaseNotExist("Training Test Case"), "Unable to remove test case from page TestCasesPage");
        log.info("Completion test deleteTestCaseByName");
    }

    @Test(description = "Deleting all test cases in a project", priority = 4)
    public void deleteAllTestsCases() {
        log.info("Run deleteAllTestsCases. Open the DashboardPage");
        log.info("Start BeforeMethod: login to account, open the DashboardPage");

        log.info("Click on the name of the project on DashboardPage");
        dashboardPage.openProject("Graduation project");

        log.info("Create a test case");
        TestCase testCase = TestCaseFactory.get();
        overviewProjectPage
                .addTestCases()
                .createNewTestCase(testCase);

        log.info("Go to the test cases section");
        projectPage.selectingSectionOnTheProjectPage("Test Cases");
        testCasesPage.deleteAllTestsCases();

        Assert.assertEquals(testCasesPage.getNumberOfTestCasesInTheProject(), "0", "Unable to delete all test cases in open project");
        AllureUtils.takeScreenshot(driver);
        log.info("Completion test deleteAllTestsCases");
    }
}
