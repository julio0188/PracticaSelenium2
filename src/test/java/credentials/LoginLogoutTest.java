package credentials;

import com.poiji.bind.Poiji;
import models.CredentialsModel;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageobjetcs.LoginPage;
import pageobjetcs.ShoppingPage;
import utilities.Base;

import java.io.File;
import java.util.List;

public class LoginLogoutTest extends Base {
    private LoginPage loginPage;
    private ShoppingPage shoppingPage;

    @BeforeMethod
    public void beforeMethod(){
        setup();
    }

    @Test (dataProvider = "credentials")
    public void logoutTest(CredentialsModel credentialsModel){
        loginPage = new LoginPage(driver);
        loginPage.login(credentialsModel.getUsername(),credentialsModel.getPassword());

        shoppingPage = new ShoppingPage(driver);
        Assert.assertTrue(shoppingPage.titleIsDisplay());
        shoppingPage.logout();
        //Se realiza la validacion para todo el set
        Assert.assertTrue(loginPage.buttonLoginIsDisplay());

    }

    @AfterMethod
    public void afterMethod(){
        teardown();
    }

    @DataProvider(name="credentials")
    public Object[][] dataProviderCredentials(){
        List<CredentialsModel> credentialsModelList=
                Poiji.fromExcel(new File("src/test/resources/dataexcel/testData.xlsx"),
                CredentialsModel.class);
        return new Object[][]{
                {credentialsModelList.get(0)}
        };

    }

}
