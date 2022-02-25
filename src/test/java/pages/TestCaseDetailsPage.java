package pages;


import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TestCaseDetailsPage extends BasePage {

    private static final By MESSAGE_TO_ADMIN = By.xpath("//div[@class='sidebar-inner']/p");
    String projectTitleLocator = "//span[contains(text(), '%s')]";
    String fieldLocator = "//label[text()='%s']/ancestor::td";


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


    @Step("Open TestCaseDetails Page")
    public void openTestCaseDetails(String projectTitle) {
        driver.findElement(By.xpath(String.format(projectTitleLocator, projectTitle))).click();
    }


    @Step("Get contained text in the field")
    public String getContainedTextInTheField(String fieldName) {
        return driver.findElement(By.xpath(String.format(fieldLocator, fieldName))).getText();
    }


    @Step("Get contained text in the Preconditions field")
    public String getContainedTextInThePreconditionsField(){
        return driver.findElement(By.xpath("(//div[@class='markdown'])[1]")).getAttribute("innerText");
    }


    @Step("Get contained text in the ExpectedResult field")
    public String getContainedTextInTheExpectedResultField(){
        return driver.findElement(By.xpath("(//div[@class='markdown'])[3]")).getAttribute("innerText");
    }


    @Step("Get contained text in the Steps field")
    public String getContainedTextInTheStepsField(){
        return driver.findElement(By.xpath("(//div[@class='markdown'])[2]")).getAttribute("innerText");
    }
}