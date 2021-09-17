package pageobjetcs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SuccessShoppingPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final By successTitle = By.className("complete-header");
    private final By buttonBackToHome = By.id("back-to-products");

    public SuccessShoppingPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(this.driver,5);
    }

    public boolean titleIsDisplay(){
        try{
            wait.until(ExpectedConditions.visibilityOfElementLocated(successTitle));
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

    public void backToHome(){
        driver.findElement(buttonBackToHome).click();

    }
}
