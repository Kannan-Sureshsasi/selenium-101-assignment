package com.selenium101.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.selenium101.config.TestMuDriverFactory;

public class TestScenario1Cloud {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {

    	driver = TestMuDriverFactory.createDriver(
    	        "Chrome",
    	        "latest",
    	        "Windows 11",
    	        "latest",
    	        "Scenario 1 - Simple Form Demo",
    	        "Selenium 101 Assignment"
    	);
    	
        driver.manage().window().maximize();
    }

    @Test
    public void simpleFormDemoCloudTest() {

        System.out.println("CLOUD SCENARIO 1 STARTED");

        // 1. Open Selenium Playground
        driver.get(
                "https://www.testmuai.com/selenium-playground/"
        );

        // 2. Click Simple Form Demo
        driver.findElement(
                By.linkText("Simple Form Demo")
        ).click();

        // 3. Validate URL
        Assert.assertTrue(
                driver.getCurrentUrl().contains("simple-form-demo"),
                "URL does not contain 'simple-form-demo'"
        );

        // 4. Create String variable
        String message = "Welcome to TestMu AI";

        // 5. Enter message
        driver.findElement(
                By.id("user-message")
        ).sendKeys(message);

        // 6. Click Get Checked Value
        driver.findElement(
                By.id("showInput")
        ).click();

        // 7. Get displayed message
        String displayedMessage = driver.findElement(
                By.id("message")
        ).getText();

        // 8. Validate displayed message
        Assert.assertEquals(
                displayedMessage,
                message,
                "Displayed message does not match entered message"
        );

        System.out.println(
                "CLOUD SCENARIO 1 RESULT: PASSED"
        );
    }

    @AfterMethod
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }
}