package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RadioButton {

    WebDriver driver;
    String label;
    String radioButton = "//strong[contains(text(), '%s')]/ancestor::label/input";

    public RadioButton(WebDriver driver, String label) {
        this.driver = driver;
        this.label = label;
    }

    public void selectRadioButton(){
        driver.findElement(By.xpath(String.format(radioButton, this.label))).click();
    }
}
