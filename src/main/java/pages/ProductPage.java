package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductPage {
    public static String getproductID(WebDriver driver){
        WebElement element = driver.findElement(By.xpath("//input[@id = 'productId']"));
        String productID = element.getAttribute("value");
        return productID;
    }

    public static String getProductPrice(WebDriver driver){
        WebElement element = driver.findElement(By.xpath("//div[@id = 'sp-price-lowPrice']"));
        return element.getText();
    }

    public static WebElement addToBasketButton(WebDriver driver){
        return driver.findElement(By.xpath("//button[@id = 'add-to-basket']"));
    }
}
