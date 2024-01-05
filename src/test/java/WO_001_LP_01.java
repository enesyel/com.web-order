import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class WO_001_LP_01 extends Hooks {

    @Test
    void testLoginProcessWithValidCredentials() {

        //2.Click on weborder link.
        WebElement webOrderLink = driver.findElement(By.xpath("//a[@href:'/weborder']"));

        webOrderLink.click();

      //  Thread.sleep(3000);
        //  wait.until(ExpectedConditions.presenceOfElementLocated(By.id("login-username-input")));
        //3.Enter the "Inar" as username and "Academy" password.
        WebElement userNameInputField = driver.findElement(By.id("login-username-input"));
        WebElement passwordInputField = driver.findElement(By.id("login-password-input"));

        userNameInputField.sendKeys("Inar");
        passwordInputField.sendKeys("Academy");

        //4. Click on the "Login" button.
        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();

        //wait.until(ExpectedConditions.presenceOfElementLocated(By.id("welcome-heading")));

        //5.Verify that the user is successfully logged in.
        WebElement heading = driver.findElement(By.id("welcome-heading"));
        String headingText = heading.getText();
        Assertions.assertEquals("Welcome, Inar!", headingText, "Heading Text is wrong!");


    }



}
