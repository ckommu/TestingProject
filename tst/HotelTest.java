import org.junit.BeforeClass;
import org.junit.Ignore;
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
import java.time.LocalDate;
import java.time.Month;
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
        driver.get("https://www.booking.com/");

        // clicking the Stays tab
        WebElement stays = driver.findElement(By.cssSelector("div.pRB0-nav-items:nth-child(3) > nav:nth-child(1) > ul:nth-child(1) > li:nth-child(2)"));
        stays.click();

        // inputting "Tokyo"
        WebElement cityInput = driver.findElement(By.xpath("/html/body/div[2]/div/div/div/main/div[1]/div/div[2]/div/div/div/div/div[2]/div[1]/div/div/input"));
        cityInput.sendKeys(city);

        // selecting "Tokyo" from drop down
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
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

        // unchecking Compare vs. KAYAK box
        WebElement compare = driver.findElement(By.id("BDC-PRE_US_HFDCMP2"));
        compare.click();

        // resizing window bc on firefox, a popup obscures the search button and makes it unclickable
        // i've TRIED to get rid of the pop-up but NOTHINGNGIFJOWEJ was working
        // resizing the window pushes the button to the bottom where it's visible (for me anyway)
        driver.manage().window().setSize(new Dimension(831, 1011));

        // clicking apply for # of guests & rooms
        WebElement guestApply = driver.findElement(By.xpath("/html/body/div[7]/div/div[2]/div/div/div[1]/div/span/button/div/div"));
        guestApply.click();

        // selecting search button
        // THIS DOES NOT WORK
        //WebElement search = driver.findElement(By.tagName("button"));
        //WebElement search = driver.findElement(By.cssSelector("button[aria-label='Search']"));
        //search.click();
        WebDriverWait searchWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement search = searchWait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div/div/div/main/div[1]/div/div[2]/div/div/div/div/div[2]/span/span")));
        search.click();
    }
    @Test
    @Ignore
    @Parameters({"tokyo four seasons"})
    public void connectionTest2(String city) throws Exception{
        driver.get("https://www.booking.com/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement closeSignIn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[21]/div/div/div/div[1]/div[1]/div/button")));
        closeSignIn.click();

        WebElement openDateMenu = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/form/div[1]/div[2]/div"));
        openDateMenu.click();

        WebElement startDate = driver.findElement(By.cssSelector("span[data-date='2024-05-01']"));
        startDate.click();

        WebElement endDate = driver.findElement(By.cssSelector("span[data-date='2024-05-02']"));
        endDate.click();

        WebElement cityInput = driver.findElement(By.cssSelector("input[placeholder='Where are you going?']"));
        cityInput.sendKeys(city);
        Thread.sleep(2000);
        cityInput.sendKeys(Keys.DOWN);
        Thread.sleep(2000);
        cityInput.sendKeys(Keys.ENTER);
        Thread.sleep(1000);
        cityInput.sendKeys(Keys.ENTER);


//        //input[value='Las Vegas']
//        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(15));
//        WebElement searchField = wait2.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='Where are you going?']")));
//        searchField.clear();
//        searchField.sendKeys(city);
//        searchField.sendKeys(Keys.ENTER);
    }

    //FINAL TEST
    @Test
    @Parameters({"tokyo hilton"})
    public void searchHotel(String city) throws Exception{
        driver.get("https://www.booking.com/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement closeSignIn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[21]/div/div/div/div[1]/div[1]/div/button")));
        closeSignIn.click();

        WebElement openDateMenu = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/form/div[1]/div[2]/div"));
        openDateMenu.click();

        WebElement startDate = driver.findElement(By.cssSelector("span[data-date='2024-05-01']"));
        startDate.click();

        WebElement endDate = driver.findElement(By.cssSelector("span[data-date='2024-05-02']"));
        endDate.click();

        String startDateText = "2024-05-01";
        String endDateText = "2024-05-02";

        WebElement cityInput = driver.findElement(By.cssSelector("input[placeholder='Where are you going?']"));
        cityInput.sendKeys(city);
        Thread.sleep(2000);
        cityInput.sendKeys(Keys.DOWN);
        Thread.sleep(2000);
        cityInput.sendKeys(Keys.ENTER);
        Thread.sleep(1000);
        cityInput.sendKeys(Keys.ENTER);

        Thread.sleep(2000);
        WebElement hotelNameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[data-testid='title']")));
        WebElement priceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span[data-testid='price-and-discounted-price']")));

        // Get the text content of the hotel name and price elements
        String hotelName = hotelNameElement.getText();
        String priceText = priceElement.getText();

        // Extract the price as a double value
        double price = Double.parseDouble(priceText.replaceAll("[^0-9.]", ""));

        // Output the hotel name and price
        System.out.println("Hotel Name: " + hotelName);
        System.out.println("Price: $" + price);
        System.out.println("Start Date: " + startDateText);
        System.out.println("End Date: " + endDateText);
        System.out.println();

        LocalDate startDateObj = LocalDate.of(2024, Month.MAY, 2);
        LocalDate endDateObj = LocalDate.of(2024, Month.MAY, 3);

        int currentMonth = startDateObj.getMonthValue();

        Thread.sleep(500);
        while (!(startDateObj.getYear() == 2025 && startDateObj.getMonthValue() == Month.JULY.getValue())) {

            Thread.sleep(1000);
            WebElement openDateMenu2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"bodyconstraint-inner\"]/div[2]/div/div[1]/div/form/div[1]/div[2]/div")));
            openDateMenu2.click();

            if (startDateObj.getMonthValue() != currentMonth && !(startDateObj.getYear() == 2025 && startDateObj.getMonthValue() == 6)) {
                // Click on the arrow
                Thread.sleep(500);
                WebElement nextMonthArrow = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@aria-label='Next month']")));
                nextMonthArrow.click();

                currentMonth = startDateObj.getMonthValue();
            }

            Thread.sleep(500);
            startDate = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span[data-date='" + startDateObj.toString() + "']")));
            //startDate = driver.findElement(By.cssSelector("span[data-date='" + startDateObj.toString() + "']"));
            startDate.click();

            Thread.sleep(500);
            endDate = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span[data-date='" + endDateObj.toString() + "']")));
            //endDate = driver.findElement(By.cssSelector("span[data-date='" + endDateObj.toString() + "']"));
            endDate.click();

            Thread.sleep(500);
            WebElement searchButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(., 'Search')]")));
            //WebElement searchButton = driver.findElement(By.xpath("//button[contains(., 'Search')]"));
            searchButton.click();

            Thread.sleep(1000);
            hotelNameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[data-testid='title']")));
            priceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span[data-testid='price-and-discounted-price']")));

            hotelName = hotelNameElement.getText();
            priceText = priceElement.getText();
            price = Double.parseDouble(priceText.replaceAll("[^0-9.]", ""));

            System.out.println("Hotel Name: " + hotelName);
            System.out.println("Price: $" + price);
            System.out.println("Start Date: " + startDateObj.toString());
            System.out.println("End Date: " + endDateObj.toString());
            System.out.println();

            startDateObj = startDateObj.plusDays(1);
            endDateObj = endDateObj.plusDays(1);

        }
    }

}