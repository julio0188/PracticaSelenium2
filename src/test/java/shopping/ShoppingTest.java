package shopping;

import com.poiji.bind.Poiji;
import models.CredentialsModel;
import models.ShoppingItemModel;
import models.UserDataModel;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageobjetcs.*;
import utilities.Base;

import java.io.File;
import java.util.List;

public class ShoppingTest extends Base {
    private LoginPage loginPage;
    private ShoppingPage shoppingPage;
    private DetailItemPage detailItemPage;
    private DescriptionCheckoutPage descriptionCheckoutPage;
    private InformationCheckoutPage informationCheckoutPage;
    private OverviewCheckoutPage overviewCheckoutPage;
    private SuccessShoppingPage successShoppingPage;


    @BeforeMethod
    public void beforeMethod() {
        setup();
    }

    @Test(dataProvider = "credentials")
    public void shoppingEnd2EndTest(CredentialsModel credentialsModel,
                                    List<ShoppingItemModel> shoppingItemModelList,
                                    UserDataModel userDataModel) {
        loginPage = new LoginPage(driver);
        loginPage.login(credentialsModel.getUsername(), credentialsModel.getPassword());

        shoppingPage = new ShoppingPage(driver);
        detailItemPage = new DetailItemPage(driver);
        double sum = 0;
        double currentprice = 0;

        for (ShoppingItemModel item : shoppingItemModelList) {
            shoppingPage.goToDetail(item.getItemName());

            currentprice = detailItemPage.addToCart(item.getItemId());
            //Validacion
            Assert.assertEquals(item.getPrice(), currentprice, "monto de item no cuadra");
            sum += currentprice;
        }
        //Validacion
        Assert.assertEquals(shoppingPage.getItemCount(), shoppingItemModelList.size(), "El total de Items no cuadra");
        shoppingPage.goToCheckout();

        descriptionCheckoutPage = new DescriptionCheckoutPage(driver);
        descriptionCheckoutPage.continueCheckout();

        informationCheckoutPage = new InformationCheckoutPage(driver);
        informationCheckoutPage.fillForm(userDataModel.getFirstname(), userDataModel.getLastname(), userDataModel.getZipcode());

        overviewCheckoutPage = new OverviewCheckoutPage(driver);
        //Validacion
        Assert.assertEquals(overviewCheckoutPage.getTotalPrice(), sum, "Las sumas totales no cuadran");
        overviewCheckoutPage.finishCheckout();

        successShoppingPage = new SuccessShoppingPage(driver);
        //Validacion
        Assert.assertTrue(successShoppingPage.titleIsDisplay());

        successShoppingPage.backToHome();

        Assert.assertTrue(shoppingPage.titleIsDisplay(), "Pagina no esperada de final");





        /*for (ShoppingItemModel item: shoppingItemModelList){
            System.out.println(item.getItemName());
            System.out.println(item.getItemId());
            System.out.println(item.getPrice());
            System.out.println("------------------------------------");
        }
        System.out.println("UserDataModel:-------------------------");
        System.out.println(userDataModel.getFirstname());
        System.out.println(userDataModel.getLastname());
        System.out.println(userDataModel.getZipcode());*/

    }

    @AfterMethod
    public void afterMethod() {
        teardown();
    }

    @DataProvider(name = "credentials")
    public Object[][] dataProviderCredentials() {
        UserDataModel userDataModel = new UserDataModel();


        List<CredentialsModel> credentialsModelList =
                Poiji.fromExcel(new File("src/test/resources/dataexcel/testData.xlsx"),
                        CredentialsModel.class);

        List<ShoppingItemModel> shoppingItemModelList =
                Poiji.fromExcel(new File("src/test/resources/dataexcel/testData.xlsx"),
                        ShoppingItemModel.class);
        return new Object[][]{
                {credentialsModelList.get(0), shoppingItemModelList, userDataModel}
        };

    }
}
