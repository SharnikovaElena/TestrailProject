package elements;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
public class InputModal {

    WebDriver driver;
    String nameInput;


    String inputLocator = "//input[@name='%s'][contains(@class, 'form-control form-control-full form-fields')]";

    public InputModal(WebDriver driver, String nameInput) {
        this.driver = driver;
        this.nameInput = nameInput;
    }

    @Step("Filling in the required fields to create a Project")
    public void write(String text) {
        log.info("Writing text in the input field by the specified name when creating a Test Case");
        driver.findElement(By.xpath(String.format(inputLocator, this.nameInput))).sendKeys(text);
    }

}
