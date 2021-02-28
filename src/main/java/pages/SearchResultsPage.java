package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class SearchResultsPage {
    public static WebElement wantedPageLink(int wantedPageNo, WebDriver driver){
        String wantedPageString = Integer.toString(wantedPageNo);
        return driver.findElement(By.xpath("//*[@id='best-match-right']/div[5]/ul/li[" + wantedPageString + "]/a"));
        //*[@id="best-match-right"]/div[4]/ul/li[3]/a
    }

    public static WebElement pageLinkControlElement(int wantedPageNo, WebDriver driver){
        String wantedPageString = Integer.toString(wantedPageNo + 1);
        return driver.findElement(By.xpath("//*[@id='best-match-right']/div[4]/ul/li[" + wantedPageString + "]/a"));
    }

    public static WebElement productLinks(WebDriver driver){
        System.out.println("Inside productLinks" + driver.getCurrentUrl());
        //WebDriverWait wait = new WebDriverWait(webDriver, 10);
        //WebElement productLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@class, 'product-link']")));
        //return productLink;
        //return driver.findElement(By.xpath("//*[@id='item-info-block-652682846']/p/img"));
        return null;
    }
}
