import helpers.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class DemoTest {
    WebDriver driver = null;
    Properties xpathProp = new Properties();
    @BeforeTest
    void Setup() {
        driver = Driver.getInstance();
        try{
            xpathProp.load(new FileInputStream("src/test/java/properties/xpath.properties"));
        }catch (IOException ioe){
            System.out.println("error: " + ioe.getMessage());
        }
    }

    @Test
    void Test1(){
        driver.get("https://www.google.com/");
        driver.findElement(By.xpath(xpathProp.getProperty("Google.searchbar")));
        driver.findElement(By.xpath(xpathProp.getProperty("Google.signin.button"))).click();
        new WebDriverWait(driver, Duration.ofMillis(20000)).until(d -> driver.findElement(By.xpath(xpathProp.getProperty("Google.emailOrPhone.text"))).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath(xpathProp.getProperty("Forgot.Password.Button"))).getText().equalsIgnoreCase("Forgot email?"));
    }
    @AfterTest
    void CleanUp(){
        driver.quit();
    }
}
