package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage {
    public static WebElement productsContainer(WebDriver driver){
        return driver.findElement(By.xpath("//*[@id='cart-items-container']/div[2]"));
    }

    public static List<WebElement> cartItems(WebDriver driver){
        List<WebElement> items = driver.findElements(By.xpath("//div[contains(@id, 'cart-item-')]"));
        System.out.println("No of items in cart: " + items.size());
        return  items;
    }

    public static WebElement findProductInCart(WebDriver driver, List<WebElement> cart, String productID){
        int found = 0, i = 0;
        WebElement cartElement = null;
        while (found == 0 && i < cart.size()){
            cartElement = cart.get(i);
            if(cartElement.getAttribute("data-product").startsWith(productID)){
                found = 1;
            }
            else{
                i++;
            }
        }

        if(i<cart.size()){
            return cartElement;
        }

        return null;
    }

    public static String findPriceInCart(WebDriver driver, WebElement product){
        String priceInCart = product.findElement(By.xpath("//div[(@class = 'total-price')]/strong")).getText();
        return  priceInCart;
    }

}
