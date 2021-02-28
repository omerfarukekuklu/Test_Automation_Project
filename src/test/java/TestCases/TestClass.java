package TestCases;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.HomePage;
import pages.LoginPage;
import pages.SearchResultsPage;

import java.util.List;
import java.util.Random;

import static java.util.concurrent.TimeUnit.SECONDS;

public class TestClass {
    private static WebDriver driver;
    private static String baseURL = "https://www.gittigidiyor.com/";
    private static String loginPageUrl = "https://www.gittigidiyor.com/uye-girisi";

    private static String userName = "seleniumtestt";
    private static String password = "selenium123";
    private static String expectedLoginTitle = "Hesabım";
    private static String searchText = "bilgisayar";
    private static String expectedControlClass = "current";
    private static int wantedPageNo = 2;

    @BeforeClass
    public static void initialize(){
        System.setProperty("webdriver.chrome.driver","C:/Users/Ömer/Documents/selenium web drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, SECONDS);
        driver.manage().timeouts().pageLoadTimeout(200, SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void testHomePage(){
        driver.get(baseURL);
        Assert.assertEquals(baseURL, driver.getCurrentUrl());
        System.out.println("After testHomePage: " + driver.getCurrentUrl());
    }

    @Test
    public void testLogin(){
        driver.get(loginPageUrl);
        System.out.println("testLogin: " + driver.getCurrentUrl());
        LoginPage.usernameField(driver).clear();
        LoginPage.usernameField(driver).sendKeys(userName);

        LoginPage.passField(driver).clear();
        LoginPage.passField(driver).sendKeys(password);

        LoginPage.loginButton(driver).click();

        Assert.assertEquals(expectedLoginTitle, HomePage.loginCheckElement(driver).getAttribute("title"));

        System.out.println("After testLogin: " + driver.getCurrentUrl());
    }

    @Test
    public void testSearch(){
        HomePage.searchBox(driver).clear();
        HomePage.searchBox(driver).sendKeys(searchText);
        HomePage.searchButton(driver).click();

        System.out.println("After first search: " + driver.getCurrentUrl());

        WebElement wantedPageLink = SearchResultsPage.wantedPageLink(wantedPageNo, driver);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", wantedPageLink);
        wantedPageLink.click();

        WebElement pageLinkControlElement = SearchResultsPage.pageLinkControlElement(wantedPageNo, driver);
        Assert.assertEquals(expectedControlClass, pageLinkControlElement.getAttribute("class"));

        System.out.println("After testSearch: " + driver.getCurrentUrl());
    }

    @Test
    public void testAddToCart(){
        System.out.println("Before testAddToCard: " + driver.getCurrentUrl());
        //WebElement productLink = SearchResultsPage.productLinks(driver);
        SearchResultsPage.productLinks(driver);
        //System.out.println(productLinks.size());
        //((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", productLinks);
        //productLink.click();
    }
}
