package pages;

import org.openqa.selenium.WebDriver;

public class TestCasesProjectPage extends BasePage{
    public TestCasesProjectPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageOpen() throws InterruptedException {
        return false;
    }
}
