package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;


public class MySettingsPage extends BasePage{

    public MySettingsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageOpen() throws InterruptedException {
        return isExit(CONTENT_HEADER_TITLE);
    }

    @Step("Get the title of the page")
    public String getTitlePageValue() {
        return driver.findElement(CONTENT_HEADER_TITLE).getText();
    }
}
