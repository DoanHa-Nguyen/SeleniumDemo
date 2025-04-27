package stepDefinition;

import helpers.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BasicSteps {

    WebDriver driver = Driver.getInstance();
    Properties xpathProp = new Properties();
    @Given("User Navigate To Google Website")
    public void GoToWebSite(){
        setUp();
        driver.get("https://www.google.com/");
    }

    private void setUp() {
        try{
            xpathProp.load(new FileInputStream("src/test/java/properties/xpath.properties"));
        }catch (IOException ioe){
            System.out.println("error: " + ioe.getMessage());
        }
    }

    @When("User Click On Sign In Button")
    public void userClickOnSignInButton() {
        driver.findElement(By.xpath(xpathProp.getProperty("Google.signin.button"))).click();
        new WebDriverWait(driver, Duration.ofMillis(20000)).until(d -> driver.findElement(By.xpath(xpathProp.getProperty("Google.emailOrPhone.text"))).isDisplayed());
    }

    @Then("User see {string}")
    public void verifyElementText(String element){
        Assert.assertTrue(driver.findElement(By.xpath(xpathProp.getProperty(element))).getText().equalsIgnoreCase("Forgot email?"));
    }
}
