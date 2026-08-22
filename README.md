# Selenium 101 Assignment

A Selenium WebDriver automation project implementing the three Selenium 101 assignment scenarios using Java, TestNG, Maven, and LambdaTest cloud execution.

## Project Overview

This project automates three scenarios from the Selenium 101 assignment and validates the expected behavior using Selenium WebDriver and TestNG.

The tests are executed on LambdaTest across Google Chrome and Microsoft Edge on Windows 11.

## Test Scenarios

### Scenario 1 - Simple Form Demo

- Navigate to the Selenium Playground.
- Open the Simple Form Demo.
- Validate the page URL.
- Enter a message in the input field.
- Click the Show Message button.
- Validate the displayed message.

### Scenario 2 - Drag and Drop Slider

- Navigate to the Drag & Drop Sliders page.
- Locate the slider with an initial value of 15.
- Move the slider toward the target value.
- Use keyboard interaction to reach the target value of 95.
- Validate the slider value.
- Validate the displayed range value.

### Scenario 3 - Input Form Submit

- Navigate to the Input Form Submit page.
- Submit the form without entering required information.
- Validate the browser validation behavior.
- Fill in the required form fields.
- Select United States from the country dropdown.
- Submit the form.
- Validate the successful submission message.

## Browser and Platform Coverage

| Browser | Operating System | Execution |
|---|---|---|
| Google Chrome | Windows 11 | LambdaTest |
| Microsoft Edge | Windows 11 | LambdaTest |

## Test Execution

The TestNG suite is configured to execute the six browser/scenario combinations.

### TestNG Suite

The suite contains:

- Chrome - Scenario 1
- Chrome - Scenario 2
- Chrome - Scenario 3
- Edge - Scenario 1
- Edge - Scenario 2
- Edge - Scenario 3

The tests are configured for parallel execution using TestNG.

## Technologies Used

- Java
- Selenium WebDriver
- TestNG
- Maven
- LambdaTest
- Git
- GitHub

## Project Structure

```text
selenium-101-assignment/
│
├── src/
│   └── test/
│       └── java/
│           └── com/
│               └── selenium101/
│                   ├── config/
│                   │   └── TestMuDriverFactory.java
│                   │
│                   └── tests/
│                       ├── TestScenario1Cloud.java
│                       ├── TestScenario1EdgeCloud.java
│                       ├── TestScenario2Cloud.java
│                       ├── TestScenario2EdgeCloud.java
│                       ├── TestScenario3Cloud.java
│                       └── TestScenario3EdgeCloud.java
│
├── pom.xml
├── testng.xml
├── .gitignore
└── README.md
