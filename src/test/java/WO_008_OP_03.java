import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class WO_008_OP_03 extends Hooks {

    List<String> orderInformation = new ArrayList<>();
    @Test
    void testProcessWithInvalidZipCode() throws InterruptedException{

        //prod name
        orderInformation.add("MyMoney");
        //quantity
        orderInformation.add("8");
        //discount
        orderInformation.add("20");
        //name
        orderInformation.add("Inar Academy");
        //street
        orderInformation.add("1100 Congress Ave");
        //City
        orderInformation.add("Austin");
        //State
        orderInformation.add("TX");
        //Zipcode
        orderInformation.add("92@#83");
        //Card Type
        orderInformation.add("American Express");
        //Card Number
        orderInformation.add("342738261027163");
        //expire date(format must mm/yy)
        orderInformation.add("01/28");

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

        //Select Mymoney
        WebElement productDropDownElement= driver.findElement(By.id("productSelect"));
        Select productDropDown=new Select(productDropDownElement);
        productDropDown.selectByVisibleText(orderInformation.get(0));

        //Enter 8 as quantity
        WebElement quantityInputField=driver.findElement(By.id("quantityInput"));
        quantityInputField.sendKeys(orderInformation.get(1));

        //Enter 20 as discount
        WebElement discountInputField=driver.findElement(By.id("discountInput"));
        discountInputField.sendKeys(orderInformation.get(2));

        JavascriptExecutor js=(JavascriptExecutor) driver;
        js.executeScript("window.scroll(0,100)");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("button[text()='Calculate']")));

        //Click calculate button
        WebElement calculateButton=driver.findElement(By.xpath("button[text()='Calculate']"));
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

        js.executeScript("window.scroll(0,1000)");
        Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("amex")));

        //Select American express type
        WebElement amexCheckbox = driver.findElement(By.id("amex"));
        amexCheckbox.click();

        //Enter card number
        WebElement cardNumberField = driver.findElement(By.id("cardNumber"));
        cardNumberField.sendKeys(orderInformation.get(9));

        //Enter expire date
        WebElement expireDateField = driver.findElement(By.id("expiryDate"));
        expireDateField.sendKeys(orderInformation.get(10));

        //Click process button

        WebElement processButton = driver.findElement(By.xpath("//button[text()='Process']"));
        processButton.click();

        //Error message
        String expectedMsg="Invalid Zıpcode!";
        WebElement actualResult=driver.findElement(By.xpath("//div[@role='alert']"));
        String actualMsg=actualResult.getText();

        Assertions.assertEquals(expectedMsg,actualMsg,"Wrong message!");



    }
}
