package stepDefinition;

import helpers.Driver;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import org.openqa.selenium.WebDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Hooks {
    WebDriver driver;
    Properties xpathProp = new Properties();
    @Before
    public void setUp(){
        driver = Driver.getInstance();
        try{
            xpathProp.load(new FileInputStream("src/test/java/properties/xpath.properties"));
        }catch (IOException ioe){
            System.out.println("error: " + ioe.getMessage());
        }
    }
    @After
    public void tearDown(){
        driver.quit();
    }
}
