import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.Assert.assertEquals;

import javax.imageio.IIOException;
import java.io.File;
import java.io.IOException;
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

        try {
            File dbFile = new File("hotel_data.db");
            if (!dbFile.exists()) {
                dbFile.createNewFile();
            }
            connection = DriverManager.getConnection("jdbc:sqlite:hotel_data.db");
            Statement statement = connection.createStatement();
            String createDB = "CREATE TABLE IF NOT EXISTS hotel_data (" +
                    "id INTEGER PRIMARY KEY," +
                    "city TEXT," +
                    "hotel TEXT," +
                    "price REAL," +
                    "startDate TEXT," +
                    "endDate TEXT" +
                    ")";
            statement.executeUpdate(createDB);

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }




//    @Test
//    public void connectionTest() {
//        // Your existing test method implementation to navigate the website and extract data
//        // Extracted data: city, hotel, date, price
//        String city = "Tokyo";
//        String hotel = "Example Hotel";
//        double price = 100.0;
//        String startDate = "2024-05-01";
//        String endDate = "2024-05-02";
//
//        // Insert extracted data into the database
//        insertDataIntoDatabase(city, hotel, price, startDate, endDate);
//    }
//
//    @AfterClass
//    public static void tearDown() {
//        // Close the database connection
//        try {
//            if (connection != null) {
//                connection.close();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        // Close the browser
//        driver.quit();
//    }
//
//    private void insertDataIntoDatabase(String city, String hotel, double price, String startDate, String endDate) {
//        // Insert data into SQLite database
//        try {
//            String sql = "INSERT INTO hotel_data (city, hotel, price, startDate, endDate) VALUES (?, ?, ?, ?, ?)";
//            PreparedStatement pstmt = connection.prepareStatement(sql);
//            pstmt.setString(1, city);
//            pstmt.setString(2, hotel);
//            pstmt.setDouble(3, price);
//            pstmt.setString(4, startDate);
//            pstmt.setString(5, endDate);
//            pstmt.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
}
