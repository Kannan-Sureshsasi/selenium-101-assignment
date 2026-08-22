package com.selenium101.tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.selenium101.config.TestMuDriverFactory;

public class TestScenario2EdgeCloud {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = TestMuDriverFactory.createDriver(
                "MicrosoftEdge",
                "latest",
                "Windows 11",
                "latest",
                "Scenario 2 - Drag Drop Slider - Edge",
                "Selenium 101 Assignment"
        );
    }

    @Test
    public void dragAndDropSliderTest() {

        System.out.println("EDGE CLOUD SCENARIO 2 STARTED");

        driver.get("https://www.testmuai.com/selenium-playground/");

        driver.findElement(
                By.linkText("Drag & Drop Sliders")
        ).click();

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement slider = wait.until(
                d -> d.findElement(
                        By.xpath("//input[@type='range' and @value='15']")
                )
        );

        Assert.assertEquals(
                slider.getAttribute("value"),
                "15"
        );

        int min = Integer.parseInt(slider.getAttribute("min"));
        int max = Integer.parseInt(slider.getAttribute("max"));
        int target = 95;

        int width = slider.getSize().getWidth();

        double percentage =
                (double) (target - min) / (max - min);

        int targetPosition =
                (int) Math.round(width * percentage);

        int center = width / 2;

        int offset = targetPosition - center;

        new Actions(driver)
                .moveToElement(slider, offset, 0)
                .clickAndHold()
                .moveByOffset(1, 0)
                .release()
                .perform();

        int current =
                Integer.parseInt(slider.getAttribute("value"));

        while (current < target) {
            slider.sendKeys(Keys.ARROW_RIGHT);
            current =
                    Integer.parseInt(slider.getAttribute("value"));
        }

        while (current > target) {
            slider.sendKeys(Keys.ARROW_LEFT);
            current =
                    Integer.parseInt(slider.getAttribute("value"));
        }

        Assert.assertEquals(
                slider.getAttribute("value"),
                "95"
        );

        String displayed =
                wait.until(
                        d -> d.findElement(
                                By.id("rangeSuccess")
                        ).getText()
                );

        Assert.assertEquals(displayed, "95");

        System.out.println("EDGE CLOUD SCENARIO 2 RESULT: PASSED");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}