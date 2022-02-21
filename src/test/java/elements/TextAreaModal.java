package elements;


import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
public class TextAreaModal {
WebDriver driver;
String label;
String textAreaLocator = "//label[contains(., '%s')]/ancestor::div[@class='form-group']//div[contains(@class, 'form-control-full')]";

    public TextAreaModal(WebDriver driver, String label) {
        this.driver = driver;
        this.label = label;
    }

    public void write(String text) {
        log.info("Writing text into textarea with by the specified name");
        driver.findElement(By.xpath(String.format(textAreaLocator, this.label))).sendKeys(text);
    }
}
