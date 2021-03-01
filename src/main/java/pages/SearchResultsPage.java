package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Random;

public class SearchResultsPage {
    public static WebElement wantedPageLink(int wantedPageNo, WebDriver driver){
        String wantedPageString = Integer.toString(wantedPageNo);
        return driver.findElement(By.xpath("//*[@id='best-match-right']/div[5]/ul/li[" + wantedPageString + "]/a"));
    }

    public static WebElement pageLinkControlElement(int wantedPageNo, WebDriver driver){
        String wantedPageString = Integer.toString(wantedPageNo + 1);
        return driver.findElement(By.xpath("//*[@id='best-match-right']/div[4]/ul/li[" + wantedPageString + "]/a"));
    }

    public static WebElement productLink(WebDriver driver){
        System.out.println("Inside productLinks" + driver.getCurrentUrl());
        List<WebElement> productLinks = driver.findElements(By.xpath("//li[contains(@id, 'product_id_')]"));
        System.out.println("Number of products in page = " + productLinks.size());

        Random random = new Random();
        int randomInt = random.nextInt(productLinks.size());
        System.out.println("Product number: " + randomInt);
        return productLinks.get(randomInt);
    }
}
