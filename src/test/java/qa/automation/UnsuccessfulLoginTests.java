package qa.automation;

import base.TestUtil;
import com.opencsv.exceptions.CsvException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.CsvHelper;

import java.io.IOException;

public class UnsuccessfulLoginTests extends TestUtil {
    @DataProvider(name = "csvWrongCredentials")
    public static Object [][] readUsersFromCsvFile() throws IOException, CsvException {
        return CsvHelper.readCsvFile("src/test/resources/wrong-credentials.csv");
    }

    @Test (dataProvider = "csvWrongCredentials")
    public void UnsuccessfulLoginTest(String userName, String password){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.tryToLogin(userName, password);

        String errorMessage = "Username and password do not match any user in this service";
        if (userName.length() == 0) {
            errorMessage = "Username is required";
        } else if (password.length() == 0) {
            errorMessage = "Password is required";
        }

        WebElement errorLogin = driver.findElement(By.xpath("//*[text()='Epic sadface: "+errorMessage+"']"));
        Assert.assertTrue(errorLogin.isDisplayed());
    }
}
