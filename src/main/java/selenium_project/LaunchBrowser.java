package selenium_project;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class LaunchBrowser {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver","C:/Users/Ömer/Documents/selenium web drivers/chromedriver.exe");
        String expectedUrl = "https://www.gittigidiyor.com/";
        WebDriver driver = new ChromeDriver();
        driver.get(expectedUrl);
        driver.manage().window().maximize();

        try {
            Assert.assertEquals(expectedUrl, driver.getCurrentUrl());
            System.out.println("Navigated to correct webpage");
        }
        catch (Exception pageNavigatonError){
            System.out.println("Didn't navigate to correct webpage");
        }

        try{
            Thread.sleep(2000);
        }
        catch (Exception sleepError){
            sleepError.printStackTrace();
        }

        driver.get("https://www.gittigidiyor.com/uye-girisi");

        try{
            Thread.sleep(3000);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        WebElement emailField = driver.findElement(By.id("L-UserNameField"));
        emailField.clear();
        emailField.sendKeys("seleniumtestt");

        WebElement passwordField = driver.findElement(By.id("L-PasswordField"));
        passwordField.clear();
        passwordField.sendKeys("selenium123");

        WebElement loginBtn = driver.findElement(By.xpath("//input[@value = 'Giriş Yap' and @type = 'submit']"));
        loginBtn.click();

        try {
            Thread.sleep(3000);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        String expectedLoginTitle = "Hesabım";

        String loginCheckTitle = null;

        try {
            WebElement loginCheckElement = driver.findElement(By.xpath("//div[@data-cy = 'header-user-menu']"));
            loginCheckTitle = loginCheckElement.findElement(By.cssSelector("div[class= 'gekhq4-6 hnYHyk']")).getAttribute("title");
            Assert.assertEquals(expectedLoginTitle, loginCheckTitle);
            System.out.println("Logged in successfully");
        }
        catch (Exception loginException){
            System.out.println("Login failed");
        }

        //Finding Search Box and Searching

        String searchText = "bilgisayar";

        WebElement searchBox = driver.findElement(By.xpath("//input[@type = 'text' and @data-cy = 'header-search-input']"));
        searchBox.clear();
        searchBox.sendKeys(searchText);

        WebElement searchButton = driver.findElement(By.xpath("//button[@data-cy = 'search-find-button' and @type = 'submit']"));
        searchButton.click();

        //Clicking Second Page of Search Results

        int wantedPageNumber = 2;
        String wantedPageString = Integer.toString(wantedPageNumber);
        System.out.println(wantedPageString);
        //*[@id="best-match-right"]/div[5]/ul/li['" + ]/a
        //WebDriverWait wait = new WebDriverWait(driver, 5);
        //WebElement wantedPageLink = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='best-match-right']/div[5]/ul/li['" + wantedPageString + "']")));
        WebElement wantedPageLink = driver.findElement(By.xpath("//*[@id='best-match-right']/div[5]/ul/li[" + wantedPageString + "]/a"));
        //WebElement wantedPageLink = driver.findElement(By.xpath("//*[@id='best-match-right']/div[5]/ul/li['" + wantedPageString + "']/a"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", wantedPageLink);
        wantedPageLink.click();
        //wantedPageLink.click();
        //WebElement pageListHolder = driver.findElement(By.cssSelector("//div[class = 'pager pt30 hidden-m gg-d-24]/ul"));
        //List<WebElement> pageList = pageListHolder.findElements(By.tagName("li/a"));

        //WebElement wantedLink = pageList.get(wantedPageNumber);
        //wantedLink.click();

        //Checking Whether the Second Pape Opened

        String expectedClass = "current";
        String currentClass = wantedPageLink.getAttribute("class");
        /*try {
            Assert.assertEquals(expectedClass, currentClass);
            System.out.println("Page "+ wantedPageString +" opened successfully");
        }
        catch (Exception wrongPageException){
            System.out.println("Error opening page " + wantedPageString);
        }*/

    }
}
