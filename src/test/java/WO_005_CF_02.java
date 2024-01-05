import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class WO_005_CF_02 extends Hooks {

    @Test
    void testFailWithInvalidOrderDetails() throws InterruptedException {
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

        //6-Select ScreenSaver from product dropdown
        WebElement productDropDownElement = driver.findElement(By.id("productSelect"));
        Select productDropDown = new Select(productDropDownElement);
        productDropDown.selectByVisibleText("ScreenSaver");

        //8-Enter 20 as discount percentage
        WebElement discountInputField = driver.findElement(By.id("discountInput"));
        discountInputField.sendKeys("20");

        //9- Click on the Calculate button
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scroll(0,100)");
        Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("button[text()='Calculate']")));

        WebElement calculateButton = driver.findElement(By.xpath("button[text()='Calculate']"));
        calculateButton.click();

        // Error message
        WebElement quantityErrorMessage = driver.findElement(By.xpath("//div[@class='product-information-form']//form//em[1]"));

        String errorMsg = quantityErrorMessage.getText();
        System.out.println("Error message is : " + errorMsg);

    }
}
