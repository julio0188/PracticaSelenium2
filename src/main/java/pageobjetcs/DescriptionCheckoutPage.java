package pageobjetcs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DescriptionCheckoutPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final By buttonCheckout = By.id("checkout");


    public DescriptionCheckoutPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(this.driver,5);
    }

    public void continueCheckout(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(buttonCheckout)).click();

    }


}
