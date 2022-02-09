package tests;

import lombok.extern.log4j.Log4j2;
import org.testng.Assert;
import org.testng.annotations.Test;

@Log4j2
public class OverviewProjectTest extends BaseTest{

    @Test(description = "Open the 'Overview' tab on the project page")
    public void openOverviewTabTest() throws InterruptedException {
        log.info("Run test openOverviewTabTest. Open the DashboardPage");
        loginPage
                .open()
                .login(userEmail, userPassword);
       boolean isOverviewTabOpen = dashboardPage
               .openProject("Graduation project")
               .open()
               .isPageOpen();
        Assert.assertTrue(isOverviewTabOpen, "Overview Tab did not open");
    }
}
