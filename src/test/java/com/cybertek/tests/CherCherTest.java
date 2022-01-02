package com.cybertek.tests;

import com.cybertek.utilities.WebDriverFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/* Task1:
    1. Go to https://chercher.tech/practice/explicit-wait-sample-selenium-webdriver
    2. Click on "Click me, to Open an alert after 5 seconds"
    3. Explicitly wait until alert is present
    4. Then handle the Javascript alert

      Task2:
    1. Go to https://chercher.tech/practice/explicit-wait-sample-selenium-webdriver
    2. Click on "Enable button after 10 seconds"
    3. Explicitly wait until the button is enabled
    4. Then verify the button is enabled
     */
public class CherCherTest {

    WebDriver driver;
    WebDriverWait wait;
    @BeforeMethod
    public void setUp(){
        driver = WebDriverFactory.getDriver("chrome");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // TÜM TEST CASE VE ELEMETLER İÇİN BEKLER
        driver.get("https://chercher.tech/practice/explicit-wait-sample-selenium-webdriver");
    }
     @AfterMethod
    public void tearDown() throws InterruptedException {
         Thread.sleep(3000);
         driver.quit();
     }

     @Test
    public void alertPresentTest() {
      WebElement alertButton = driver.findElement(By.id("alert"));
      alertButton.click();

       wait =  new WebDriverWait(driver,6);
       wait.until(ExpectedConditions.alertIsPresent());
         Alert alert = driver.switchTo().alert();
         alert.accept();



     }

     @Test
    public void disableButtonTest() {
        WebElement button = driver.findElement(By.id("disable"));
         System.out.println(button.isEnabled());//false

         WebElement enabledButton = driver.findElement(By.id("enable-button"));
         enabledButton.click();

         wait = new WebDriverWait(driver,10);
         wait.until(ExpectedConditions.elementToBeClickable(button));

         System.out.println("button.isEnabled() = " + button.isEnabled()); // true
         Assert.assertTrue(button.isEnabled(),"Verify the Button is enabled");
     }
}
