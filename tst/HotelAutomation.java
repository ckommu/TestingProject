import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class HotelAutomation {
    private static WebDriver driver;
    private static Connection connection;

    @BeforeClass
    public static void setUp() {
//        System.setProperty("webdriver.gecko.driver", "geckodriver");
        driver = new FirefoxDriver();

        // Connect to the SQLite database
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:HotelTesting.db");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void connectionTest() {
        // Your existing test method implementation to navigate the website and extract data
        // Extracted data: city, hotel, date, price
        String city = "Tokyo";
        String hotel = "Example Hotel";
        String date = "2024-05-01";
        double price = 100.0;

        // Insert extracted data into the database
        insertDataIntoDatabase(city, hotel, date, price);
    }

    @AfterClass
    public static void tearDown() {
        // Close the database connection
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Close the browser
        driver.quit();
    }

    private void insertDataIntoDatabase(String city, String hotel, String date, double price) {
        // Insert data into SQLite database
        try {
            String sql = "INSERT INTO hotel_prices (city, hotel, date, price) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, city);
            pstmt.setString(2, hotel);
            pstmt.setString(3, date);
            pstmt.setDouble(4, price);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
