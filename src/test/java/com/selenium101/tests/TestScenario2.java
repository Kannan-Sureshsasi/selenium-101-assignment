package com.selenium101.tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.selenium101.config.TestMuDriverFactory;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestScenario2 {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = TestMuDriverFactory.createDriver(
                "Chrome",
                "latest",
                "Windows 11",
                "latest",
                "Scenario 2 - Drag and Drop Slider",
                "Selenium 101 Assignment"
        );
    }

    @Test
    public void dragAndDropSliderTest() {

        System.out.println("SCENARIO 2 STARTED");
        
        // STEP 1: Open Selenium Playground

        driver.get(
                "https://www.testmuai.com/selenium-playground/"
        );

        // STEP 2: Click "Drag & Drop Sliders"

        driver.findElement(
                By.linkText("Drag & Drop Sliders")
        ).click();

        WebDriverWait wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(10)
        );

        // STEP 3: Select ONLY the slider whose default value is 15

        WebElement slider = wait.until(
                d -> d.findElement(
                        By.xpath(
                                "//input[@type='range' and @value='15']"
                        )
                )
        );

        // STEP 4: Verify starting value is 15

        String initialValue =
                slider.getAttribute("value");

        System.out.println(
                "Initial slider value: " + initialValue
        );

        Assert.assertEquals(
                initialValue,
                "15",
                "The required Default value 15 slider was not selected."
        );

        // STEP 5: Read slider range

        int min = Integer.parseInt(
                slider.getAttribute("min")
        );

        int max = Integer.parseInt(
                slider.getAttribute("max")
        );

        int target = 95;

        System.out.println(
                "Minimum value: " + min
        );

        System.out.println(
                "Maximum value: " + max
        );

        System.out.println(
                "Target value: " + target
        );

        // STEP 6: Get the physical slider width

        int sliderWidth =
                slider.getSize().getWidth();

        System.out.println(
                "Slider width: " + sliderWidth
        );

        // STEP 7: Calculate target position.
        
        // IMPORTANT:
        // Selenium moveToElement() starts at the CENTER
        // of the slider. Therefore our offset must be calculated relative to the CENTER, not from the left edge.

        double targetPercentage =
                (double) (target - min)
                        / (max - min);

        int targetPosition =
                (int) Math.round(
                        sliderWidth * targetPercentage
                );

        int centerPosition =
                sliderWidth / 2;

        int targetOffset =
                targetPosition - centerPosition;

        System.out.println(
                "Target position from left: "
                        + targetPosition
        );

        System.out.println(
                "Target offset from center: "
                        + targetOffset
        );

        // STEP 8: Perform a REAL mouse drag on the SAME slider.
        
        // No generic range locator.
        // No second slider.

        new Actions(driver)
                .moveToElement(
                        slider,
                        targetOffset,
                        0
                )
                .clickAndHold()
                .moveByOffset(
                        1,
                        0
                )
                .release()
                .perform();

        // STEP 9:
        // Read the value after the actual mouse drag.

        String valueAfterDrag =
                slider.getAttribute("value");

        System.out.println(
                "Value after actual drag: "
                        + valueAfterDrag
        );

        // STEP 10: Browser pixel rounding can occasionally produce
        // 94 or 96 instead of exactly 95.
        // If that happens, correct the SAME slider using
        // keyboard arrows.
        // We DO NOT click the slider again because clicking
        // can change its value based on the mouse position.

        int currentValue =
                Integer.parseInt(
                        valueAfterDrag
                );

        if (currentValue < target) {

            while (currentValue < target) {

                slider.sendKeys(
                        org.openqa.selenium.Keys.ARROW_RIGHT
                );

                currentValue =
                        Integer.parseInt(
                                slider.getAttribute("value")
                        );
            }

        } else if (currentValue > target) {

            while (currentValue > target) {

                slider.sendKeys(
                        org.openqa.selenium.Keys.ARROW_LEFT
                );

                currentValue =
                        Integer.parseInt(
                                slider.getAttribute("value")
                        );
            }
        }

        // STEP 11: Verify the SAME slider is exactly 95

        String finalSliderValue =
                slider.getAttribute("value");

        System.out.println(
                "Final slider value: "
                        + finalSliderValue
        );

        Assert.assertEquals(
                finalSliderValue,
                "95",
                "The Default value 15 slider was not moved to 95."
        );

        // STEP 12: Validate displayed range value

        String displayedRangeValue =
                wait.until(
                        d -> d.findElement(
                                By.id("rangeSuccess")
                        ).getText()
                );

        System.out.println(
                "Displayed range value: "
                        + displayedRangeValue
        );

        Assert.assertEquals(
                displayedRangeValue,
                "95",
                "The displayed range value is not 95."
        );

        // SUCCESS

        System.out.println(
                "SCENARIO 2 RESULT: PASSED"
        );
    }

    @AfterMethod
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }
}