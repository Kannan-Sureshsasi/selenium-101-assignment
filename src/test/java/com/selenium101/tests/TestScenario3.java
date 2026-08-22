package com.selenium101.tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.selenium101.config.TestMuDriverFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestScenario3 {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = TestMuDriverFactory.createDriver(
                "Chrome",
                "latest",
                "Windows 11",
                "latest",
                "Scenario 3 - Input Form Submit",
                "Selenium 101 Assignment"
        );
    }

    @Test
    public void inputFormSubmitTest() {

        System.out.println("SCENARIO 3 STARTED");

        WebDriverWait wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(10)
        );

        // 1. Open Selenium Playground
        driver.get("https://www.testmuai.com/selenium-playground/");

        // 2. Click Input Form Submit
        driver.findElement(
                By.linkText("Input Form Submit")
        ).click();

        // 3. Click Submit without filling the form
        driver.findElement(
                By.xpath("//button[normalize-space()='Submit']")
        ).click();

        // 4. Get browser validation message
        WebElement nameField = driver.findElement(
                By.id("name")
        );

        String validationMessage =
                nameField.getAttribute("validationMessage");

        System.out.println(
                "Validation message: " + validationMessage
        );

        // The exact browser wording can differ between browser versions.
        // Verify that the browser reports the required-field validation.
        Assert.assertTrue(
                validationMessage != null
                        && validationMessage.toLowerCase().contains("fill"),
                "Required-field validation message was not displayed"
        );

        // 5. Fill Name
        nameField.sendKeys("Test User");

        // 6. Fill Email
        driver.findElement(
                By.id("inputEmail4")
        ).sendKeys("testuser@example.com");

        // 7. Fill Password
        driver.findElement(
                By.id("inputPassword4")
        ).sendKeys("Test@12345");

        // 8. Fill Company
        driver.findElement(
                By.id("company")
        ).sendKeys("Test Company");

        // 9. Fill Website
        driver.findElement(
                By.id("websitename")
        ).sendKeys("https://example.com");

        // 10. Select Country using visible text
        WebElement countryDropdown = driver.findElement(
                By.xpath("//select[@name='country']")
        );

        Select country = new Select(countryDropdown);

        country.selectByVisibleText("United States");

        // 11. Fill City
        driver.findElement(
                By.id("inputCity")
        ).sendKeys("Chennai");

        // 12. Fill Address 1
        driver.findElement(
                By.id("inputAddress1")
        ).sendKeys("400 Test Street");

        // 13. Fill Address 2
        driver.findElement(
                By.id("inputAddress2")
        ).sendKeys("Second Floor");

        // 14. Fill State
        driver.findElement(
                By.id("inputState")
        ).sendKeys("Tamil Nadu");

        // 15. Fill Zip
        driver.findElement(
                By.id("inputZip")
        ).sendKeys("600001");

        // 16. Submit form
        driver.findElement(
                By.xpath("//button[normalize-space()='Submit']")
        ).click();

        // 17. Get success message
        WebElement successMessage = wait.until(driver ->
                driver.findElement(
                        By.xpath(
                                "//p[contains(@class,'success-msg')]"
                        )
                )
        );

        String actualSuccessMessage =
                successMessage.getText();

        System.out.println(
                "Success message: " + actualSuccessMessage
        );

        // 18. Validate success message
        Assert.assertEquals(
                actualSuccessMessage,
                "Thanks for contacting us, we will get back to you shortly.",
                "Success message does not match the assignment requirement"
        );

        System.out.println("SCENARIO 3 RESULT: PASSED");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}