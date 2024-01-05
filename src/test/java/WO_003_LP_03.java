import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class WO_003_LP_03 extends Hooks {
    @Test
    void testLogoutProcessWithProperly() {

        //2-) Click weborder button.
        WebElement webOrderLink = driver.findElement(By.xpath("//a[@href:'/weborder']"));
        webOrderLink.click();


        //3.Enter the "Inar" as username and "Academy" password.
        WebElement userNameInputField = driver.findElement(By.id("login-username-input"));
        WebElement passwordInputField = driver.findElement(By.id("login-password-input"));

        userNameInputField.sendKeys("Inar");
        passwordInputField.sendKeys("Academy");

        //4. Click on the "Login" button.
        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();
        //5-) Click logout button.
        WebElement logoutButton=driver.findElement(By.id("logout-button"));
        logoutButton.click();

        String expectedMsg="Successfully logout";
        WebElement actualErrorMsg = driver.findElement(By.xpath("//h1[normalize-space()='404 Not Found']"));
        String actualMsg = actualErrorMsg.getText();
        Assertions.assertEquals(expectedMsg,actualMsg,"Error Message !");
    }


}
