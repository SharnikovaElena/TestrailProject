package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProjectPage extends BasePage{

    private static final By MILESTONES_TITLE = By.xpath("//h1/a[@id='navigation-overview-viewmilestones']");


    public ProjectPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageOpen() throws InterruptedException {
        return isExit(MILESTONES_TITLE);
    }

    @Step("Get the title of the page")
    public String getTitlePageValue() {
        return driver.findElement(MILESTONES_TITLE).getText();
    }

}
