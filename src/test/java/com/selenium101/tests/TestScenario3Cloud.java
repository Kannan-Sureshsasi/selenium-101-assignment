package com.selenium101.tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.selenium101.config.TestMuDriverFactory;

public class TestScenario3Cloud {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
    	driver = TestMuDriverFactory.createDriver(
    	        "Chrome",
    	        "latest",
    	        "Windows 11",
                 "latest",
    	        "Scenario 3 - Input Form Submit",
    	        "selenium-101-assignment"
    	);
    }

    @Test
    public void inputFormSubmitTest() {

        System.out.println("CLOUD SCENARIO 3 STARTED");

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(15));

        driver.get("https://www.testmuai.com/selenium-playground/");

        driver.findElement(
                By.linkText("Input Form Submit")
        ).click();

        driver.findElement(
                By.xpath("//button[normalize-space()='Submit']")
        ).click();

        WebElement nameField =
                driver.findElement(By.id("name"));

        String validation =
                nameField.getAttribute("validationMessage");

        System.out.println(
                "Validation message: " + validation
        );

        Assert.assertTrue(
                validation != null &&
                validation.toLowerCase().contains("fill")
        );

        nameField.sendKeys("Test User");

        driver.findElement(
                By.id("inputEmail4")
        ).sendKeys("testuser@example.com");

        driver.findElement(
                By.id("inputPassword4")
        ).sendKeys("Test@12345");

        driver.findElement(
                By.id("company")
        ).sendKeys("Test Company");

        driver.findElement(
                By.id("websitename")
        ).sendKeys("https://example.com");

        Select country =
                new Select(
                        driver.findElement(
                                By.xpath("//select[@name='country']")
                        )
                );

        country.selectByVisibleText("United States");

        driver.findElement(
                By.id("inputCity")
        ).sendKeys("Chennai");

        driver.findElement(
                By.id("inputAddress1")
        ).sendKeys("400 Test Street");

        driver.findElement(
                By.id("inputAddress2")
        ).sendKeys("Second Floor");

        driver.findElement(
                By.id("inputState")
        ).sendKeys("Tamil Nadu");

        driver.findElement(
                By.id("inputZip")
        ).sendKeys("600001");

        driver.findElement(
                By.xpath("//button[normalize-space()='Submit']")
        ).click();

        WebElement success =
                wait.until(
                        d -> d.findElement(
                                By.xpath(
                                        "//p[contains(@class,'success-msg')]"
                                )
                        )
                );

        String message = success.getText();

        System.out.println(
                "Success message: " + message
        );

        Assert.assertEquals(
                message,
                "Thanks for contacting us, we will get back to you shortly."
        );

        System.out.println("CLOUD SCENARIO 3 RESULT: PASSED");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
