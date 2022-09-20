package io.cucumber.tiketa;

import static org.junit.jupiter.api.Assertions.assertTrue;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StepDefinitions
{

    By cookies_accept = By.id("cookiescript_buttons");
    By notification   = By.xpath("//*[@class='covid-notification']//a");
    By suggestions    = By.className("tt-suggestions");
    By suggestion     = By.className("tt-suggestion");

    private WebDriver driver;

    @Before
    public void setUp()
    {
        System.setProperty("webdriver.chrome.driver",
                           Paths.get("src/test/resources/chromedriver_mac64_m1/chromedriver").toString());
        if (driver == null)
        {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--window-size=1280,800");
            driver = new ChromeDriver(options);
        }
    }

    String BASE_URL = "https://www.tiketa.lt";

    @Given("I am on the Tiketa {string} page")
    public void I_am_on_the_Tiketa_page(String string)
    {
        driver.get(BASE_URL + "/EN/" + string);

        boolean notificationDisplayed = driver.findElement(notification).isDisplayed();
        if (notificationDisplayed)
        {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
            wait.until(ExpectedConditions.elementToBeClickable(notification)).click();
        }
        boolean cookiesDisplayed = driver.findElement(cookies_accept).isDisplayed();
        if (cookiesDisplayed)
        {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
            wait.until(ExpectedConditions.elementToBeClickable(cookies_accept)).click();
        }
    }

    @When("I search for {string} in Caption field")
    public void i_search_for_caption_in_caption_field(String caption)
    {
        WebElement captionSearch = driver.findElement(By.name("sf_TextFilter"));
        captionSearch.sendKeys(caption);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(suggestions));

        List<WebElement> suggestionsList = driver.findElements(suggestion);
        for (WebElement suggest : suggestionsList)
        {
            assertTrue(StringUtils.containsIgnoreCase(suggest.getText(), caption));
        }

        captionSearch.sendKeys(Keys.ENTER);
    }

    @When("I select {string} as event place")
    public void i_select_as_event_place(String string)
    {
        WebElement eventPlace      = driver.findElement(By.id("dropdownMenu4"));
        WebElement eventPlaceInput = driver.findElement(By.xpath("//*[@id='dropdownMenu4']//following-sibling::input"));
        WebElement elementToSelect = driver.findElement(By.xpath("//a[text()='" + string + "']"));

        eventPlace.click();
        eventPlaceInput.sendKeys(string);
        elementToSelect.click();
    }

    @When("I choose start {string} and end {string} dates")
    public void i_choose_start_and_end_dates(String dateFrom, String dateTo)
    {
        WebElement startDate = driver.findElement(By.name("sf_DateFrom"));
        WebElement endDate   = driver.findElement(By.name("sf_DateTo"));
        startDate.sendKeys(dateFrom);
        endDate.sendKeys(dateTo);
    }

    @When("I click Search button")
    public void i_click_search_button()
    {
        WebElement searchButton = driver.findElement(By.xpath("//button[text()='Search']"));
        searchButton.click();
    }

    @When("I press Buy button on {string} event")
    public void i_press_buy_button_on_cirque_du_soleil_corteo_event(String eventName)
    {
        WebElement buyButton = driver.findElement(By.xpath(
            "//p[text()='" + eventName + "']//parent::div//parent::div//child::div[3]//child::div[2]//child::div//button"));
        buyButton.click();
    }

    @Then("I can verify the {int} options that are available")
    public void i_can_verify_the_options_that_are_available(Integer expectedOptions)
    {
        int xpathCount = driver.findElements(By.xpath("//a[contains(text(),'Buy')]")).size();
        Assertions.assertEquals(expectedOptions, xpathCount);
    }

    @After
    public void tearDown()
    {
        if (driver != null)
        {
            driver.close();
            driver.quit();
        }
    }

}
