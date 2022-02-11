package pages;

import elements.DropDownModal;
import elements.InputModal;
import io.qameta.allure.Step;
import models.TestCase;
import org.openqa.selenium.WebDriver;


public class TestCasesProjectPage extends BasePage {

    public TestCasesProjectPage(WebDriver driver) {
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


    public void createNewTestCase(TestCase testCase) {
        new InputModal(driver, "title").write(testCase.getTitle());
        new InputModal(driver, "estimate").write(testCase.getEstimate());
        new InputModal(driver, "refs").write(testCase.getReferences());
        new DropDownModal(driver, "Template").selectOption(testCase.getTemplate());
        new DropDownModal(driver, "Section").selectOption(testCase.getSection());
        new DropDownModal(driver, "Type").selectOption(testCase.getType());
        new DropDownModal(driver, "Priority").selectOption(testCase.getPriority());
        new DropDownModal(driver, "Automation Type").selectOption(testCase.getAutomationType());
    }
}

//    public AccountDetailsPage clickSave() {
//        driver.findElement(SAVE_BUTTON).click();
//        return new AccountDetailsPage(driver);
//    }


//new InputForAccountModal(driver, "Account Name").write(account.getAccountName());
//        new InputForAccountModal(driver, "Website").write(account.getWebSite());
//        new InputForAccountModal(driver, "Parent Account").write(account.getParentAccount());
//        new InputForAccountModal(driver, "Phone").write(account.getPhone());
//        new InputForAccountModal(driver, "Fax").write(account.getFax());
//        new DropDownForAccountModal (driver, "Type").selectOption(account.getType());
//        new DropDownForAccountModal (driver, "Industry").selectOption(account.getIndustry());
//        new InputForAccountModal(driver, "Employees").write(account.getEmployees());
//        new InputForAccountModal(driver, "Annual Revenue").write(account.getAnnualRevenue());
//        new TextAreaForAccountModal(driver, "Description").write(account.getDescription());
//        new TextAreaForAccountModal(driver, "Billing Street").write(account.getBillingStreet());