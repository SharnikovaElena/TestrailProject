package tests;

import lombok.extern.log4j.Log4j2;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.AllureUtils;


@Log4j2
public class ProjectTest extends BaseTest {

    @Test(description = "From the DashboardPage go to the project page")
    public void projectOpenTest() throws InterruptedException {
        log.info("Run test projectOpenTest. Open the DashboardPage");
        loginPage
                .open()
                .login(userEmail, userPassword);
        dashboardPage.openProject("Graduation project");
        projectPage.isPageOpen();
        AllureUtils.takeScreenshot(driver);
        log.debug("Checking that the ProjectPage is open");
        Assert.assertEquals(projectPage.getTitlePageValue(), "Milestones", "ProjectPage is not open");
        log.info("Completion test projectOpenTest");

    }
}
