package elements;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
public class DropDownModal {

    String label;
    WebDriver driver;

    String dropdownLocator = "//label[contains(text(), '%s')]/ancestor::td//a[@class='chzn-single']";
//    String optionLocator = "//a[contains(@class, 'chzn-single-with-drop')]/span[text()= %s]";
//   String optionLocator = "//a[contains(@class, 'chzn-single-with-drop')]/ancestor::td/select/option[contains(text(), '%s')]";

//   String optionLocator = "//select/option[contains(text(), '%s')]";
String optionLocator = "//option[text()='%s']";


    public DropDownModal(WebDriver driver, String label) {
        this.driver = driver;
        this.label = label;
    }

    public void selectOption(String option) {
        log.info("Select option from the list by name when creating new Test Case");
        driver.findElement(By.xpath(String.format(dropdownLocator, this.label))).click();
        driver.findElement(By.xpath(String.format(optionLocator, option))).click();

    }

}


//    public void selectOption(String option) throws InterruptedException {
//        log.info("Selecting an option from the list by name ");
//        $(By.xpath(String.format(dropDownLocator, this.label))).click();
//        Thread.sleep(1000);
//        $(byText(option)).click();