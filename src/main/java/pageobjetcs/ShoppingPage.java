package pageobjetcs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ShoppingPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final By tittle = By.className("title");
    private final By menuBurger = By.id("react-burger-menu-btn");
    private final By buttonLogout = By.id("logout_sidebar_link");
    private final By buttonAbout = By.id("about_sidebar_link");
    private final By itemCount = By.className("shopping_cart_badge");
    private final By buttonCheckout = By.id("shopping_cart_container");


    public ShoppingPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(this.driver,5);
    }

    public void logout(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(tittle));
        driver.findElement(menuBurger).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(buttonLogout)).click();
        //driver.findElement(buttonLogout).click();

    }

    public boolean titleIsDisplay() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(tittle));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public String getHrefFromAbout(){
        driver.findElement(menuBurger).click();
        return wait.until(ExpectedConditions.visibilityOfElementLocated(buttonAbout)).getAttribute("href");
    }

    public void goToDetail(String productName){
        String xpathGeneric="//div[text()='PRODUCT_NAME']";
        String xpathItemName = xpathGeneric.replace("PRODUCT_NAME",productName);
        By itemName = By.xpath(xpathItemName);
        driver.findElement(itemName).click();
    }

    public int getItemCount(){
        String text = wait.until(ExpectedConditions.visibilityOfElementLocated(itemCount)).getText();
        return Integer.parseInt(text);
    }

    public void goToCheckout(){
        driver.findElement(buttonCheckout).click();
    }
}
