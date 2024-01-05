import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class WO_004_CF_01 extends Hooks {
    @Test
    void testProcessOfCalculating() throws InterruptedException {
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

        //5- Navigate to orderpage
        WebElement orderTabLink = driver.findElement(By.cssSelector("#order-tab>a"));
        orderTabLink.click();

        //6-Select HomeDecor from Product dropdown
        WebElement productDropDownElement = driver.findElement(By.id("productSelect"));
        Select productDropDown = new Select(productDropDownElement);
        productDropDown.selectByVisibleText("HomeDecor");
        
        //7-Enter 5 as quantity number

        WebElement quantityInputField= driver.findElement(By.id("quantityInput"));
        quantityInputField.sendKeys("5");


        // 8-) Enter "15" as discount percentage.
        WebElement discountInputField= driver.findElement(By.id("discountInput"));
        discountInputField.sendKeys("15");

    //////   /// Hocaya sor burayı...
        JavascriptExecutor js=(JavascriptExecutor)driver;
        js.executeScript("window.scroll(0,100)");
        Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("button[text()='Calculate']")));

        // 9-) Click on the "Calculate" button.
        WebElement calculateButton= driver.findElement(By.xpath("button[text()='Calculate']"));
        calculateButton.click();

        // 10-) Verify that the total amount is successfully displayed.
        WebElement totalField=driver.findElement(By.id("totalInput"));
        String expectedResult=totalField.getText();
        Assertions.assertEquals(expectedResult,"425","Correct result");

    }
}
