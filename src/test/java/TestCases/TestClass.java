package TestCases;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import pages.*;

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
        String path = System.getProperty("user.dir");
        System.out.println(path);
        System.setProperty("webdriver.chrome.driver",path + "/webdrivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, SECONDS);
        driver.manage().timeouts().pageLoadTimeout(200, SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void executeTests(){
        //Testing whether home page is opened
        driver.get(baseURL);
        Assert.assertEquals(baseURL, driver.getCurrentUrl());

        //Logging in and checking whether login successfull
        driver.get(loginPageUrl);
        System.out.println("testLogin: " + driver.getCurrentUrl());
        LoginPage.usernameField(driver).clear();
        LoginPage.usernameField(driver).sendKeys(userName);

        LoginPage.passField(driver).clear();
        LoginPage.passField(driver).sendKeys(password);

        LoginPage.loginButton(driver).click();

        Assert.assertEquals(expectedLoginTitle, HomePage.loginCheckElement(driver).getAttribute("title"));

        //Searching desired text in searchbox
        HomePage.searchBox(driver).clear();
        HomePage.searchBox(driver).sendKeys(searchText);
        HomePage.searchButton(driver).click();

        //Opening desired page of search results
        WebElement wantedPageLink = SearchResultsPage.wantedPageLink(wantedPageNo, driver);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", wantedPageLink);
        wantedPageLink.click();

        //Checking whether desired page is opened
        WebElement pageLinkControlElement = SearchResultsPage.pageLinkControlElement(wantedPageNo, driver);
        Assert.assertEquals(expectedControlClass, pageLinkControlElement.getAttribute("class"));

        //Selecting random product
        WebElement productPageLink = SearchResultsPage.productLink(driver);

        //Navigating selected product's page
        productPageLink.click();

        //Finding product id in product page
        String productID = ProductPage.getproductID(driver);
        System.out.println("Product ID: " + productID);

        //Getting product price in product page
        String priceAtProductPage = ProductPage.getProductPrice(driver);
        System.out.println("Product price at product page: " + priceAtProductPage);

        //Getting add to basket button and adding product to basket
        WebElement addtoBasketButton = ProductPage.addToBasketButton(driver);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addtoBasketButton);

        try{
            Thread.sleep(2000);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        //Checking product in cart
        driver.get("https://www.gittigidiyor.com/sepetim");
        List<WebElement> items = CartPage.cartItems(driver);
        WebElement product = CartPage.findProductInCart(driver, items, productID);
        Assert.assertTrue( product != null);
        System.out.println("Product ID: " + product.getAttribute("data-product"));

        //Getting product price in cart
        String priceInCart = CartPage.findPriceInCart(driver, product);
        System.out.println("Price in cart: " + priceInCart);

        //Checking product price at product page and price in cart
        Assert.assertEquals(priceAtProductPage, priceInCart);

    }
}
