package pages;

import org.openqa.selenium.WebDriver;

public class TestRunsProjectPage extends BasePage{
    public TestRunsProjectPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageOpen() throws InterruptedException {
        return false;
    }
}
