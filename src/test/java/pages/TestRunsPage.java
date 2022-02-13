package pages;

import org.openqa.selenium.WebDriver;

public class TestRunsPage extends BasePage{
    public TestRunsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageOpen() throws InterruptedException {
        return false;
    }
}
