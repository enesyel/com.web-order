import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class WO_006_OP_01 {
    WebDriver driver = new ChromeDriver();
    List<String> orderInformation = new ArrayList<>();

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));


    @BeforeEach
    void setUp() {
        //1.Open the URL.
        driver.get("https://InarAcademy:Fk160621.@test.inar-academy.com");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    void testSuccessfulOrderPlacement() throws InterruptedException {
        //Name
        orderInformation.add("Inar Academy");
        //Prod name
        orderInformation.add("MyMoney");
        //quantity
        orderInformation.add("8");
        //date
        orderInformation.add(DateTimeFormatter.ofPattern("MM/dd/yyyy").format(LocalDate.now()));
        //Street
        orderInformation.add("1100 Congress Ave.");
        //City
        orderInformation.add("Austin");
        //State
        orderInformation.add("TX");
        //Valid Zip Code
        orderInformation.add("78701");
        // Valid Card Type
        orderInformation.add("Visa");
        // Valid Card Number(Visa starts with 4, Mastercard 5, American express 34,37.
        orderInformation.add("4938281746192845");
        //Valid Card expire date ( format must be mm/yy)
        orderInformation.add("11/28");
        //2.Click on weborder link.
        WebElement webOrderLink = driver.findElement(By.xpath("//a[@href:'/weborder']"));
        webOrderLink.click();


        //3.Enter the "Inar" as username and "Academy" password.
        WebElement userNameInputField = driver.findElement(By.id("login-username-input"));
        WebElement passwordInputField = driver.findElement(By.id("login-password-input"));

        userNameInputField.sendKeys("Inar");
        passwordInputField.sendKeys("Academy");

        // Click on the "Login" button.
        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();


        //Verify that the user is successfully logged in.
        WebElement heading = driver.findElement(By.id("welcome-heading"));
        String headingText = heading.getText();
        Assertions.assertEquals("Welcome, Inar!", headingText, "Heading Text is wrong!");
        //4.Navigate to the OrderPage
        WebElement orderTabLink = driver.findElement(By.cssSelector("#order-tab>a"));
        orderTabLink.click();
        //select product as MyMoney
        WebElement productDropdownElement = driver.findElement(By.id("productSelect"));
        Select productDropdown = new Select(productDropdownElement);
        productDropdown.selectByVisibleText(orderInformation.get(1));
        //Enter "8" a quantity number
        WebElement quantityInputField = driver.findElement(By.id("quantityInput"));
        quantityInputField.sendKeys(orderInformation.get(2));
        //Enter 20 as discount percentage.
        WebElement discountInputField = driver.findElement(By.id("discountInput"));
        discountInputField.sendKeys("20");
        //Click calculate button
        WebElement calculateButton = driver.findElement(By.xpath("button[text()='Calculate']"));
        calculateButton.click();
        //Enter name
        WebElement nameField = driver.findElement(By.id("name"));
        WebElement streetField = driver.findElement(By.id("street"));
        WebElement cityField = driver.findElement(By.id("city"));
        WebElement stateField = driver.findElement(By.id("state"));
        WebElement zipCodeField = driver.findElement(By.id("zip"));

        nameField.sendKeys(orderInformation.get(0));
        streetField.sendKeys(orderInformation.get(4));
        cityField.sendKeys(orderInformation.get(5));
        stateField.sendKeys(orderInformation.get(6));
        zipCodeField.sendKeys(orderInformation.get(7));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scroll(0,1000)");
        Thread.sleep(1000);

        wait.until(ExpectedConditions.elementToBeClickable(By.id("visa")));

        //Enter payment info
        WebElement visaCheckbox = driver.findElement(By.id("visa"));
        visaCheckbox.click();

        WebElement cardNumberField = driver.findElement(By.id("cardNumber"));
        cardNumberField.sendKeys(orderInformation.get(9));

        WebElement expiryDateField = driver.findElement(By.id("expiryDate"));
        expiryDateField.sendKeys(orderInformation.get(10));

        //click on process button
        WebElement processButton = driver.findElement(By.xpath("//button[text()='Process']"));
        processButton.click();
        //verify confirmation message
        WebElement confirmationElement = driver.findElement(By.cssSelector("div[role='alert]"));
        String message = confirmationElement.getText();
        String expectedMsg = "New order has been successfully added.";
        Assertions.assertEquals(expectedMsg, message, "Confirmation message is wrong!");

        js.executeScript("window.scroll(0,-1000)");
        Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#view-orders-tab > a")));

        //Navigate to view all orders page.
        WebElement viewAllOrderLink = driver.findElement(By.cssSelector("#view-orders-tab > a"));
        viewAllOrderLink.click();
        //Verify the order is successfully placed.
        List<WebElement> tableRows = driver.findElements(By.cssSelector("tbody > tr"));

        List<WebElement> columnValuesInLastRow = tableRows.get(tableRows.size() - 1).findElements(By.xpath("td"));

        for (int i = 1; orderInformation.size() > 1; i++) {
            String expectedValue = orderInformation.get(i);
            String actualValue = columnValuesInLastRow.get(i + 1).getText();


            Assertions.assertEquals(expectedValue, actualValue, "Wrong Order Information");

        }
        Thread.sleep(5000);


    }

}
