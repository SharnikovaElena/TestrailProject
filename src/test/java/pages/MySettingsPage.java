package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;


public class MySettingsPage extends BasePage{

    public MySettingsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageOpen() throws InterruptedException {
        return isExit(TITLE_THE_SECTION_ON_THE_HEADER);
    }

    @Step("Get the title of the page")
    public String getTitlePageValue() {
        return driver.findElement(TITLE_THE_SECTION_ON_THE_HEADER).getText();
    }
}
