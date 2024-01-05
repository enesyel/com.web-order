import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class WO_009_OP_04 extends Hooks {
    List<String> orderInformation = new ArrayList<>();

    @Test
    void testProcessToApplicationAccessible() throws InterruptedException {
        //prod name
        orderInformation.add("SportsEquipment");
        //quantity
        orderInformation.add("1");
        //discount
        orderInformation.add("10");
        //name
        orderInformation.add("Inar Academy");
        //street
        orderInformation.add("1100 Congress Ave");
        //City
        orderInformation.add("Austin");
        //State
        orderInformation.add("TX");
        //Zipcode
        orderInformation.add("78701");
        //Card Type
        orderInformation.add("American Express");
        //Card Number
        orderInformation.add("4938220192845");
        //expire date(format must mm/yy)
        orderInformation.add("09/26");

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

        //Select SportsEquipment
        WebElement productDropDownElement = driver.findElement(By.id("productSelect"));
        Select productDropDown = new Select(productDropDownElement);
        productDropDown.selectByVisibleText(orderInformation.get(0));


        //Enter 1 as quantity
        WebElement quantityInputField = driver.findElement(By.id("quantityInput"));
        quantityInputField.sendKeys(orderInformation.get(1));

        //Enter 10 as discount
        WebElement discountInputField = driver.findElement(By.id("discountInput"));
        discountInputField.sendKeys(orderInformation.get(2));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scroll(0,100)");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("button[text()='Calculate']")));

        //Click calculate button
        WebElement calculateButton = driver.findElement(By.xpath("button[text()='Calculate']"));
        calculateButton.click();

        //Enter name
        WebElement nameField = driver.findElement(By.id("name"));
        nameField.sendKeys(orderInformation.get(3));

        //Enter Street
        WebElement streetField = driver.findElement(By.id("street"));
        streetField.sendKeys(orderInformation.get(4));

        //Enter City
        WebElement cityField = driver.findElement(By.id("city"));
        cityField.sendKeys(orderInformation.get(5));

        //Enter State
        WebElement stateField = driver.findElement(By.id("state"));
        stateField.sendKeys(orderInformation.get(6));

        //Enter Zipcode
        WebElement zipCodeField = driver.findElement(By.id("zip"));
        zipCodeField.sendKeys(orderInformation.get(7));

        //Enter card number
        WebElement cardNumberField = driver.findElement(By.id("cardNumber"));
        cardNumberField.sendKeys(orderInformation.get(9));

        //Enter expire date
        WebElement expireDateField = driver.findElement(By.id("expiryDate"));
        expireDateField.sendKeys(orderInformation.get(10));

        js.executeScript("window.scroll(0,1000)");
        Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Process']")));

        //Click process button

        WebElement processButton = driver.findElement(By.xpath("//button[text()='Process']"));
        processButton.click();

        //Error Message
        WebElement errorMsg = driver.findElement(By.xpath("//em[normalize-space()='Card type cannot be empty']"));
        String expectedErrorMsg = errorMsg.getText();
        System.out.println("Expected message is : " + expectedErrorMsg);


    }
}
