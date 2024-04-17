import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chromium.ChromiumDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import junitparams.Parameters;
import junitparams.JUnitParamsRunner;
import java.time.Duration;
import java.util.List;

@RunWith(JUnitParamsRunner.class)
public class HotelTest {
    private static WebDriver driver;

    @BeforeClass
    public static void setUp() {
        System.setProperty("webdriver.gecko.driver", "geckodriver");
        //System.setProperty("webdriver.chrome.driver", "chromedriver");
        driver = new FirefoxDriver();
    }

    @Test
    @Parameters({"tokyo"})
    public void connectionTest(String city) {
        driver.get("https://www.kayak.com/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));

        // clicking the Stays tab
        WebElement stays = driver.findElement(By.cssSelector("div.pRB0-nav-items:nth-child(3) > nav:nth-child(1) > ul:nth-child(1) > li:nth-child(2) > a:nth-child(1)"));
        stays.click();

        // inputting "Tokyo"
        WebElement cityInput = driver.findElement(By.xpath("/html/body/div[2]/div/div/div/main/div[1]/div/div[2]/div/div/div/div/div[2]/div[1]/div/div/input"));
        cityInput.sendKeys(city);

        // selecting "Tokyo" from drop down
        WebElement citySelect = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#\\32 1033_city_Tokyo-Japan > div")));
        citySelect.click();

        // selecting arrival
        WebElement firstDate = driver.findElement(By.xpath("/html/body/div[2]/div/div/div/main/div[1]/div/div[2]/div/div/div/div/div[2]/div[2]/div/div/div/div[1]/div/div/span"));
        firstDate.click();
        WebElement firstDateSelect = driver.findElement(By.xpath("/html/body/div[7]/div/div[2]/div[1]/div/div/div/div[3]/table/tbody/tr[1]/td[4]/div"));
        firstDateSelect.click();

        // selecting departure
        WebElement secondDateSelect = driver.findElement(By.xpath("/html/body/div[7]/div/div[2]/div[1]/div/div[1]/div/div[3]/table/tbody/tr[1]/td[5]/div"));
        secondDateSelect.click();

        // resizing window bc on firefox, a popup obscures the search button and makes it unclickable
        // i've TRIED to get rid of the pop-up but NOTHINGNGIFJOWEJ was working
        // resizing the window pushes the button to the bottom where it's visible (for me anyway)
        driver.manage().window().setSize(new Dimension(831, 1011));

        // clicking apply for # of guests & rooms
        WebElement guestApply = driver.findElement(By.xpath("/html/body/div[7]/div/div[2]/div/div/div[1]/div/span/button/div/div"));
        guestApply.click();

        // selecting search button
        // THIS DOES NOT WORK
        WebElement search = driver.findElement(By.tagName("button"));
        search.click();
    }

}
