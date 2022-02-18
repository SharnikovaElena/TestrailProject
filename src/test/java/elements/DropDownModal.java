package elements;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


@Log4j2
public class DropDownModal {

    String label;
    WebDriver driver;

    String dropdownLocator = "//label[contains(text(), '%s')]/ancestor::td//div[contains(@class, 'chzn-container')]";
    String optionLocator = "//div[contains(@class, 'chzn-container-active')]//ul/li[contains(text(), '%s')]";
    String dropdownLocatorForTestRun = "//label[contains(text(), '%s')]/ancestor::div[contains(@class, 'form-group')]//div[contains(@class, 'chzn-container')]";

    public DropDownModal(WebDriver driver, String label) {
        this.driver = driver;
        this.label = label;
    }

    public void selectOptionForTestCase(String option) {
        log.info("Select option from the list by name when creating new Test Case");
        driver.findElement(By.xpath(String.format(dropdownLocator, this.label))).click();
        driver.findElement(By.xpath(String.format(optionLocator, option))).click();

    }

    public void selectOptionForTestRun(String option) {
        log.info("Select option from the list by name when creating new Test Run");
        driver.findElement(By.xpath(String.format(dropdownLocatorForTestRun, this.label))).click();
        driver.findElement(By.xpath(String.format(optionLocator, option))).click();

    }
}

