package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage extends BasePage {

   private static final By HEADER_DASHBOARD = By.id("navigation-dashboard");

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageOpen() throws InterruptedException {
        return isExit(HEADER_DASHBOARD);
    }
}
