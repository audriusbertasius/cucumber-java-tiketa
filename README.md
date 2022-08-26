# Cucumber-Java Tiketa

This is the test automation project with build script setup for Cucumber using Java.

## Tools

    Java 8 and above
    Gradle

## Plugins

    Cucumber+ for IDE

## Get the code

Git:

    git clone https://github.com/audriusbertasius/cucumber-java-tiketa.git
    cd cucumber-java-tiketa

Or [download a zip](https://github.com/audriusbertasius/cucumber-java-tiketa/archive/main.zip) file.

## Use Gradle

Open a command window and run:

    cd gradle
    ./gradlew test --rerun-tasks --info

This runs Cucumber features using Cucumber's JUnit Platform Engine. The `Suite`
annotation on the `RunCucumberTest` class tells JUnit to kick off Cucumber.

## Overriding options

The Cucumber runtime uses configuration parameters to know what features to run,
where the glue code lives, what plugins to use etc. When using JUnit, these
configuration parameters are provided through the `@ConfigurationParameter`
annotation on your test.

### Run a subset of or Scenarios

You can tell Cucumber to only run scenarios with a particular tag:

    @CucumberOptions(tags = "@smoke and @regression")
    public class RunCucumberTest {}

Ignoring a subset of scenarios:

    @CucumberOptions(tags = "not @smoke")
    public class RunCucumberTest {}

./gradlew test

## Reports

    View your Cucumber Report at provided link after test execution:                                            │
    https://reports.cucumber.io/reports/... 

    Local report:                                            │
    ..\cucumber-java-tiketa\gradle\build\reports\tests\test\index.html
