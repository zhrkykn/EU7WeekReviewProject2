package com.cybertek.tests;

import com.cybertek.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

// Task3:
//         1. Go to:  http://secure.smartbearsoftware.com/samples/testcomplete12/WebOrders/login.aspx
//         2. Login with username: Tester, password: test
//         3. Click  Order button
//         4. Verify under Product Information, selected option is “MyMoney”
//         5. Then select FamilyAlbum, make quantity 2, and click Calculate,
//         6. Then verify Total is equal to Quantity*PricePerUnit
public class DropdownTest {


    WebDriver driver;
    WebDriverWait wait;
    @BeforeMethod
    public void setUp(){
        driver = WebDriverFactory.getDriver("chrome");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // TÜM TEST CASE VE ELEMETLER İÇİN BEKLER
        driver.get("http://secure.smartbearsoftware.com/samples/testcomplete12/WebOrders/login.aspx");
    }
    @AfterMethod
    public void tearDown() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }
    @Test
    public void test() {
        WebElement userInputbox = driver.findElement(By.id("ctl00_MainContent_username"));
        userInputbox.sendKeys("Tester");

        WebElement passwordBox = driver.findElement(By.id("ctl00_MainContent_password"));
        passwordBox.sendKeys("test");// "test" + Keys.ENTER yaparsak direk giriş yapar click gerek yok

        WebElement loginButton = driver.findElement(By.id("ctl00_MainContent_login_button"));
        loginButton.click();

        WebElement order = driver.findElement(By.linkText("Order"));
        order.click();
        String expectedSelectedOption = "MyMoney";
        WebElement select = driver.findElement(By.id("ctl00_MainContent_fmwOrder_ddlProduct"));
        Select orderSelect= new Select(select);
        String actualSelectedOption = orderSelect.getFirstSelectedOption().getText();
        Assert.assertEquals(actualSelectedOption,expectedSelectedOption,"First option selected is not as expected");

        //adım 4
        orderSelect.selectByVisibleText("FamilyAlbum");
        WebElement quantityBox = driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtQuantity"));
        quantityBox.clear();
        quantityBox.sendKeys("2");

        WebElement calculate= driver.findElement(By.cssSelector("input[type='submit']"));
        calculate.click();

        //adım 5
        int expectedPrice = 160;

        WebElement totalPrice = driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtTotal"));

        WebElement pricePerUnit = driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtUnitPrice"));


        int actualPrice = Integer.parseInt(quantityBox.getAttribute("value")) * Integer.parseInt(pricePerUnit.getAttribute("value"));

        Assert.assertEquals(actualPrice,expectedPrice,"failed");


    }
}
