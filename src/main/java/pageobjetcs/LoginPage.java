package pageobjetcs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final String url = "https://www.saucedemo.com/";
    private final By inputUsername = By.id("user-name");
    private final By inputPassword = By.id("password");
    private final By buttonLogin = By.id("login-button");
    private final By imageBot = By.className("bot_column");
    private final By errorMessage = By.cssSelector("H3[data-test='error']");


    public LoginPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(this.driver,5);
    }

    public void login(String username, String password){
        driver.get(url);
        //Se crea una espera del input
        wait.until(ExpectedConditions.visibilityOfElementLocated(inputUsername)).sendKeys(username);
        //ya no es necesario ya que se ingresa en el wait
        //driver.findElement(inputUsername).sendKeys(username);
        driver.findElement(inputPassword).sendKeys(password);
        driver.findElement(buttonLogin).click();



    }

    public boolean buttonLoginIsDisplay(){

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(imageBot));
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }

    }

    public boolean erroMassageIsDisplay(){

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }

    }


}
