package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class СonfirmationModalPage extends BasePage {

    private static final By TITLE_CONFIRMATION = By.id("ui-dialog-title-deleteDialog");
    private static final By DELETE_CHECKBOX = By.xpath("//strong[text()='Yes, delete this project (cannot be undone)']/ancestor::label//input[@name='deleteCheckbox']");
    private static final By OK_BUTTON = By.xpath("//div[@id='deleteDialog']/div/a[contains(text(), 'OK')]");



    public СonfirmationModalPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageOpen() throws InterruptedException {
        return isExit(TITLE_CONFIRMATION);
    }


    @Step("Get the title of the page")
    public String getTitlePageValue() {
        return driver.findElement(TITLE_CONFIRMATION).getText();
    }

    public void approveToDeleteProject() {
        driver.findElement(DELETE_CHECKBOX).click();
        driver.findElement(OK_BUTTON).click();
    }
}


