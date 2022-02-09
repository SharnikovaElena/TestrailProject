package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OverviewProjectPage extends BasePage{

    private final static By CHART = By.id("chart-line-fc");
    public final static By OVERVIEW_TAB = By.id("navigation-projects");

    public OverviewProjectPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageOpen() throws InterruptedException {
        return isExit(CHART);
    }

    @Step("Go to the Overview tab on the project page")
    public OverviewProjectPage open(){
        driver.findElement(OVERVIEW_TAB).click();
        return new OverviewProjectPage(driver);
    }
}
