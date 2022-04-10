package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

public class SuccessPage {

    protected WebDriver driver;
        private static final String BACK_TO_HOME_LOCATOR = "//*[@id='back-to-products']";
//    private static final String FINISH_LOCATOR = "//*[@id='finish']";
//    private static final String REMOVE_FROM_CART_LOCATOR = "//button[@id='remove-sauce-labs-%s']";
//
//    @FindBy (className = "shopping_cart_link")
//    private WebElement shoppingCartLink;
//
//    @FindBy (className = "shopping_cart_badge")
//    private WebElement shoppingCartCounter;

    public SuccessPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void backToHomePage(){
        WebElement continueShoppingLink = driver.findElement(By.xpath(BACK_TO_HOME_LOCATOR));
        continueShoppingLink.click();

//        return new SuccessPage(driver);
    }

//    public void openCheckoutPage(){
//        WebElement checkoutLink = driver.findElement(By.xpath(CHECKOUT_LOCATOR));
//        checkoutLink.click();
//    }

//    public boolean removeItemFromTheCart(String productName){
//        String xpathOfElementToBeRemoved = String.format(REMOVE_FROM_CART_LOCATOR, productName);
//        FluentWait fluentWait = new FluentWait(driver)
//                .withTimeout(Duration.ofSeconds(3));
//
//        WebElement removeButton = driver.findElement(By.xpath(xpathOfElementToBeRemoved));
//        fluentWait.until(ExpectedConditions.elementToBeClickable(removeButton));
//
//        if (removeButton.getText().equals("REMOVE")) {
//            removeButton.click();
//            return true;
//        }
//
//        return false;
//    }
//
//    public int getItemsInTheCart(){
//        if(driver.findElements(By.className("shopping_cart_badge")).isEmpty()){
//            return 0;
//        }else{
//            return Integer.parseInt(shoppingCartCounter.getText());
//        }
//    }
}
