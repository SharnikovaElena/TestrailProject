package pages;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class ProjectPage extends BasePage{
    public ProjectPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageOpen() throws InterruptedException {
        return isExit(TITLE_THE_OPENED_PAGE);
    }


    @Step("Selecting a section on the Project page")
    public void selectingSectionOnTheProjectPage(String sectionName){
        driver.findElement(By.xpath(String.format(titleTheSectionOnTheHeader,sectionName))).click();
    }
}
