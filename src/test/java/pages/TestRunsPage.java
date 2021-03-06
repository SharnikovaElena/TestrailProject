package pages;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


@Log4j2
public class TestRunsPage extends BasePage {

    private static final By RUN_REMOVAL_CONFIRMATION = By.id("confirm-check");
    private static final By DELETE_RUN_BUTTON = By.xpath("//*[@id='bulkDeleteDialog']//a[contains(text(), 'Delete')]");


    public TestRunsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageOpen() throws InterruptedException {
        return isExit(TITLE_THE_OPENED_PAGE);
    }

    @Step("Get the title of the page")
    public String getTitlePageValue() {
        return driver.findElement(TITLE_THE_OPENED_PAGE).getText();
    }


    @Step("Open the first TestRun")
    public TestRunDetailsPage openTestRun() {
        log.info("We mark the first TestRun in the checkbox");
        driver.findElement(By.xpath("(//div[@class='summary-title text-ppp']/a)[1]")).click();
        return new TestRunDetailsPage(driver);
    }


    @Step("Delete All TestRun")
    public TestRunsPage deleteAllRun() {
        log.info("We mark the All TestRun in the checkbox");
        driver.findElement(By.xpath("//input[@name='select_all']")).click();
        driver.findElement(By.xpath("//span[contains(text(), 'Delete selected ')]")).click();
        driver.findElement(RUN_REMOVAL_CONFIRMATION).click();
        driver.findElement(DELETE_RUN_BUTTON).click();
        return new TestRunsPage(driver);
    }
}
