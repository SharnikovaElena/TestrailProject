package pages;


import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TestCaseDetailsPage extends BasePage {

    private static final By MESSAGE_TO_ADMIN = By.xpath("//div[@class='sidebar-inner']/p");
    String projectTitleLocator = "//span[contains(text(), '%s')]";
    String fieldLocator = "//label[text()='%s']/ancestor::td";
    String textInThePreconditionsFieldLocator = "//*[@id='content-inner']/div[6]/div/p";
    String textInTheExpectedResultFieldLocator = "//*[@id='content-inner']/div[10]/div/p";
    String textInTheStepsFieldLocator = "//*[@id='content-inner']/div[8]/div//ol";
    private String fieldName;

    public TestCaseDetailsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageOpen() throws InterruptedException {
        return isExit(MESSAGE_TO_ADMIN);
    }

    @Step("Get the title of the page")
    public String getMessageToAdmin() {
        return driver.findElement(MESSAGE_TO_ADMIN).getText();
    }


    public void openTestCaseDetails(String projectTitle) {
        driver.findElement(By.xpath(String.format(projectTitleLocator, projectTitle))).click();
    }

    public String getContainedTextInTheField(String fieldName) {
        return driver.findElement(By.xpath(String.format(fieldLocator, fieldName))).getText();
    }

    public String getContainedTextInThePreconditionsField(){
        return driver.findElement(By.xpath(textInThePreconditionsFieldLocator)).getText();
    }

    public String getContainedTextInTheExpectedResultField(){
        return driver.findElement(By.xpath(textInTheExpectedResultFieldLocator)).getText();
    }

    public String getContainedTextInTheStepsField(){
        return driver.findElement(By.xpath(textInTheStepsFieldLocator)).getText();
    }

}