package com.selenium101.config;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class TestMuDriverFactory {

    private TestMuDriverFactory() {
        // Utility class
    }

    public static WebDriver createDriver(
            String browser,
            String version,
            String os,
            String osVersion,
            String testName,
            String buildName) {

        String username = System.getenv("LT_USERNAME");
        String accessKey = System.getenv("LT_ACCESS_KEY");

        if (username == null || username.isBlank()) {
            throw new IllegalStateException(
                    "LT_USERNAME environment variable is not configured."
            );
        }

        if (accessKey == null || accessKey.isBlank()) {
            throw new IllegalStateException(
                    "LT_ACCESS_KEY environment variable is not configured."
            );
        }

        MutableCapabilities capabilities = new MutableCapabilities();

        capabilities.setCapability("browserName", browser);
        capabilities.setCapability("browserVersion", version);
        capabilities.setCapability("platformName", os);

        MutableCapabilities ltOptions = new MutableCapabilities();

        ltOptions.setCapability("username", username);
        ltOptions.setCapability("accessKey", accessKey);

        ltOptions.setCapability("platformName", os);
        ltOptions.setCapability("platformVersion", osVersion);

        ltOptions.setCapability("name", testName);
        ltOptions.setCapability("build", buildName);

        // Assignment observability
        ltOptions.setCapability("video", true);
        ltOptions.setCapability("visual", true);
        ltOptions.setCapability("network", true);
        ltOptions.setCapability("console", true);

        // W3C capability
        ltOptions.setCapability("w3c", true);

        capabilities.setCapability("LT:Options", ltOptions);

        try {

            return new RemoteWebDriver(
                    new URI("https://hub.lambdatest.com/wd/hub").toURL(),
                    capabilities
            );

        } catch (MalformedURLException | URISyntaxException e) {

            throw new RuntimeException(
                    "Unable to create TestMu AI RemoteWebDriver.",
                    e
            );
        }
    }
}