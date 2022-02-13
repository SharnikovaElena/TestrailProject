package tests;

import com.github.javafaker.Faker;
import lombok.extern.log4j.Log4j2;
import org.testng.Assert;
import org.testng.annotations.Test;

@Log4j2
public class OverviewProjectTest extends BaseTest {

    static Faker faker = new Faker();

    @Test(description = "Open the 'Overview' tab on the project page")
    public void openOverviewTabTest() throws InterruptedException {
        log.info("Run test openOverviewTabTest. Open the DashboardPage");
        loginPage
                .open()
                .login(userEmail, userPassword);
        log.info("Click on the name of the project on DashboardPage");
        boolean isOverviewTabOpen = dashboardPage
                .openProject("Graduation project")
                .isPageOpen();
        log.debug("Сheck that the project page is open");
        Assert.assertTrue(isOverviewTabOpen, "Project did not open");
        log.info("Completion openOverviewTabTest");
    }


}
