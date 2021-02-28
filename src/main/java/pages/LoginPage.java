package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    public static WebElement usernameField(WebDriver driver){
        return  driver.findElement(By.id("L-UserNameField"));
    }

    public static WebElement passField(WebDriver driver){
        return driver.findElement(By.id("L-PasswordField"));
    }

    public static WebElement loginButton(WebDriver driver){
        return driver.findElement(By.xpath("//input[@value = 'Giriş Yap' and @type = 'submit']"));
    }
}
