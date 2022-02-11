package tests;

import lombok.extern.log4j.Log4j2;
import models.TestCase;
import models.TestCaseFactory;
import org.testng.annotations.Test;

@Log4j2
public class TestCaseTest extends BaseTest{

    @Test
    public void createNewTestCase(){
        log.info("Run createNewTestCase. Open the DashboardPage");
        loginPage
                .open()
                .login(userEmail, userPassword);
        log.info("Click on the name of the project on DashboardPage");
        dashboardPage.openProject("Graduation project");

        TestCase testCase = TestCaseFactory.get();
//        boolean isTestCaseOpen =
                overviewProjectPage.addTestCases()
                .createNewTestCase(testCase);
//        .isPageOpen();
//        AllureUtils.takeScreenshot(driver);
//
//        log.error("New Test Case creation completed");
    }
}

