package qa.automation;

import base.TestUtil;
import com.opencsv.exceptions.CsvException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.*;
import utils.CsvHelper;

import java.io.IOException;

public class SuccessfulCheckout extends TestUtil {
    @DataProvider(name = "csvCheckout")
    public static Object [][] readUsersFromCsvFile() throws IOException, CsvException {
        return CsvHelper.readCsvFile("src/test/resources/checkout.csv");
    }

    @Test(dataProvider = "csvCheckout")
    public void successfulCheckout(String userName, String password, String productOne, String productTwo, String firstName, String lastName, String postalCode){
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = loginPage.login(userName, password);

        productsPage.addItemToTheCart(productOne);
        productsPage.addItemToTheCart(productTwo);

        CartPage cartPage = productsPage.openCartPage();

        Assert.assertEquals(cartPage.getItemsInTheCart(),2, "Because we have two item so far");

        CheckoutInfoPage checkoutinfoPage = cartPage.openCheckoutPage();
        checkoutinfoPage.fillInfo(firstName, lastName, postalCode);
        CheckoutOverviewPage checkoutOverviewPage = checkoutinfoPage.continuePage();

        checkoutOverviewPage.successPage();

        WebElement successMessage = driver.findElement(By.xpath("//*[text()='THANK YOU FOR YOUR ORDER']"));
        Assert.assertTrue(successMessage.isDisplayed());
    }

    @Test(dataProvider = "csvCheckout")
    public void successfulCheckoutWithRemove(String userName, String password, String productOne, String productTwo, String firstName, String lastName, String postalCode){
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = loginPage.login(userName, password);

        productsPage.addItemToTheCart(productOne);
        productsPage.addItemToTheCart(productTwo);

        productsPage.removeItemFromTheCart(productTwo);

        CartPage cartPage = productsPage.openCartPage();

        Assert.assertEquals(cartPage.getItemsInTheCart(),1, "Because we have removed one item on previous page");

        CheckoutInfoPage checkoutinfoPage = cartPage.openCheckoutPage();
        checkoutinfoPage.fillInfo(firstName, lastName, postalCode);
        CheckoutOverviewPage checkoutOverviewPage = checkoutinfoPage.continuePage();

        checkoutOverviewPage.successPage();

        WebElement successMessage = driver.findElement(By.xpath("//*[text()='THANK YOU FOR YOUR ORDER']"));
        Assert.assertTrue(successMessage.isDisplayed());
    }

}
