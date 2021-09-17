package pageobjetcs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class InformationCheckoutPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final By inputFirstname = By.id("first-name");
    private final By inputLastname = By.id("last-name");
    private final By inputZipcode = By.id("postal-code");
    private final By buttonContinue = By.id("continue");


    public InformationCheckoutPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(this.driver,5);
    }

    public void fillForm(String firstname,String lastname, String zipcode){

        wait.until(ExpectedConditions.visibilityOfElementLocated(inputFirstname)).sendKeys(firstname);
        driver.findElement(inputLastname).sendKeys(lastname);
        driver.findElement(inputZipcode).sendKeys(zipcode);
        driver.findElement(buttonContinue).click();
    }

}
