package pageobjetcs;

import io.cucumber.java.eo.Do;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OverviewCheckoutPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final By labelPrice = By.className("summary_subtotal_label");
    private final By buttonFinish = By.id("finish");

    public OverviewCheckoutPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(this.driver,5);
    }

    public double getTotalPrice(){
        String text = wait.until(ExpectedConditions.visibilityOfElementLocated(labelPrice)).getText();
        return Double.parseDouble(text.substring(13));

    }

    public void finishCheckout(){
        driver.findElement(buttonFinish).click();
    }
}
