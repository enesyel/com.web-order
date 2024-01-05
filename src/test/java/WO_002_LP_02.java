import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class WO_002_LP_02 extends Hooks {

    @Test
    void testLoginProcessWithInvalidCredentials() {
        //2-) Click on weborder link.
        WebElement webOrderLink = driver.findElement(By.xpath("//a[@href:'/weborder']"));
        webOrderLink.click();
        //3-) Enter invalid username "InvalidUserName" and/or password "InvalidPassword".
        WebElement userNameInputField = driver.findElement(By.id("login-username-input"));
        WebElement passwordInputField = driver.findElement(By.id("login-password-input"));

        userNameInputField.sendKeys("InvalidUserName");
        passwordInputField.sendKeys("InvalidPassword");

        //4-) Click on the "Login" button

        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();

        //5-)Verify that the appropriate error message is displayed.
        WebElement errorMessageForUsername = driver.findElement(By.id("username-error-alert"));
        String userMsg = errorMessageForUsername.getText();
        String expectedUserMsg = "Invalid Username";
        Assertions.assertEquals(expectedUserMsg, userMsg);

        WebElement errorMessageForPassword = driver.findElement(By.id("password-error-alert"));
        String passwordMsg = errorMessageForPassword.getText();
        String expectedPasswordMsg = "Invalid Password";
        Assertions.assertEquals(expectedPasswordMsg, passwordMsg);


    }

}
