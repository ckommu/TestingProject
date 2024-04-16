import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class HotelTest {
    private static WebDriver driver;

    @BeforeClass
    public static void setUp() {
        System.setProperty("webdriver.gecko.driver", "geckodriver");
        driver = new FirefoxDriver();
    }

    @Test
    public void connectionTest() {
        driver.get("https://www.kayak.com/");
        WebElement stays = driver.findElement(By.cssSelector("div.pRB0-nav-items:nth-child(3) > nav:nth-child(1) > ul:nth-child(1) > li:nth-child(2) > a:nth-child(1)"));
        stays.click();

        WebElement cityInput = driver.findElement(By.xpath("/html/body/div[2]/div/div/div/main/div[1]/div/div[2]/div/div/div/div/div[2]/div[1]/div/div/input"));
        cityInput.sendKeys("tokyo");
        //might have to wait a sec before doing arrow down + enter
        cityInput.sendKeys(Keys.DOWN);
        cityInput.sendKeys(Keys.RETURN);


        WebElement firstDate = driver.findElement(By.xpath("/html/body/div[2]/div/div/div/main/div[1]/div/div[2]/div/div/div/div/div[2]/div[2]/div/div/div/div[1]/div/div/span"));
        firstDate.click();
        WebElement firstDateSelect = driver.findElement(By.xpath("/html/body/div[7]/div/div[2]/div[1]/div/div/div/div[3]/table/tbody/tr[1]/td[4]/div"));
        firstDateSelect.click();

        WebElement secondDate = driver.findElement(By.xpath("/html/body/div[2]/div/div/div/main/div[1]/div/div[2]/div/div/div/div/div[2]/div[2]/div/div/div/div[3]/div/div/span"));
        secondDate.click();
        WebElement secondDateSelect = driver.findElement(By.xpath("/html/body/div[6]/div/div[2]/div[1]/div/div/div/div[3]/table/tbody/tr[1]/td[5]/div"));
        secondDateSelect.click();

        WebElement search = driver.findElement(By.xpath("/html/body/div[2]/div/div/div/main/div[1]/div/div[2]/div/div/div/div/div[2]/span/span/button/div"));
        search.click();
    }

}
