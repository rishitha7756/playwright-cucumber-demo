# Playwright + Cucumber + JUnit 5 Test Framework (Java)

A UI test automation framework for [saucedemo.com](https://www.saucedemo.com), built with Playwright for Java, Cucumber (BDD), and JUnit 5, using the Page Object Model.

## What it covers
- Login: valid user, locked-out user, wrong password, empty fields
- Inventory: product count, add to cart, remove from cart, open the cart
- Data-driven login checks using a Cucumber Scenario Outline with an Examples table

## Tech stack
- Java 17+, Maven
- Playwright for Java
- Cucumber 7 with the JUnit Platform engine
- JUnit 5

## Project structure
- `src/test/resources/features`: Gherkin feature files
- `src/test/java/steps`: step definitions
- `src/test/java/pages`: page objects (locators and actions)
- `src/test/java/hooks`: browser setup and teardown, with a Playwright trace saved when a scenario fails
- `src/test/java/runners`: the Cucumber test runner
- `src/test/java/tests`: plain Playwright + JUnit tests

## How to run
Run the tests with Maven:

    mvn test

Choose a browser or run headless with system properties:

    mvn test -Dbrowser=firefox -Dheadless=true

The first run downloads the Playwright browsers automatically.

## Debugging failures
When a scenario fails, a trace is saved in `target/traces`. Open it with:

    mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="show-trace target/traces/<file>.zip"

Or drag the zip file onto trace.playwright.dev.
