package pageobjetcs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DetailItemPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final By itemPrice = By.className("inventory_details_price");
    private final By backToProducts = By.id("back-to-products");

    public DetailItemPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(this.driver,5);
    }

    public double addToCart(String idButton){
        String text = driver.findElement(itemPrice).getText();
        double price = Double.parseDouble(text.substring(1));

        By buttonAddToCart = By.id(idButton);
        driver.findElement(buttonAddToCart).click();
        driver.findElement(backToProducts).click();
        return price;
    }
}
