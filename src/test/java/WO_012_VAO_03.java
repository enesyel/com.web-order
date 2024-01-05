import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class WO_012_VAO_03 extends Hooks {
    @Test
    void testProcessForDeletesOrder() throws InterruptedException {
        //2.Click on weborder link.
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

        //Click viewAllOrder Page
        WebElement viewAllOrderPage = driver.findElement(By.xpath("//div[@id='view-orders-tab']//a[@id='react-aria2347320477-2-tab-null']"));
        viewAllOrderPage.click();

        //Click add more data
        WebElement addMoreDataButton = driver.findElement(By.xpath("//button[normalize-space()='Add More Data']"));
        addMoreDataButton.click();
        addMoreDataButton.click();
        addMoreDataButton.click();
        addMoreDataButton.click();
        addMoreDataButton.click();
        addMoreDataButton.click();
        addMoreDataButton.click();
        addMoreDataButton.click();

        //Click 1.checkbox
        WebElement firstCheckbox = driver.findElement(By.xpath("//tbody/tr[1]/td[1]/div[1]/input[1]"));
        firstCheckbox.click();

        //Click 3.checkbox
        WebElement thirdCheckbox = driver.findElement(By.xpath("//tbody/tr[3]/td[1]/div[1]/input[1]"));
        thirdCheckbox.click();

        //Click 5. checkbox
        WebElement fifthCheckbox = driver.findElement(By.xpath("//tbody/tr[5]/td[1]/div[1]/input[1]"));
        fifthCheckbox.click();

    }
}
