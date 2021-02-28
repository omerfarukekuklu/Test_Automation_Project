package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
/*    public WebElement loginButton(){
        return driver.findElement()
    }*/

    public static WebElement searchBox(WebDriver driver){
        return driver.findElement(By.xpath("//input[@type = 'text' and @data-cy = 'header-search-input']"));
    }

    public static WebElement searchButton(WebDriver driver){
        return driver.findElement(By.xpath("//button[@data-cy = 'search-find-button' and @type = 'submit']"));
    }

    public static WebElement loginCheckElement(WebDriver driver){
        return driver.findElement(By.xpath("//*[@id='main-header']/div[3]/div/div/div/div[3]/div/div[1]/div"));
    }
}
