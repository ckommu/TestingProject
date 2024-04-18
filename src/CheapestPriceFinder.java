import java.sql.*;

public class CheapestPriceFinder {
    public static void main(String[] args) {
        // JDBC connection parameters
        String url = "jdbc:sqlite:hotel_data.db";
        String query = "SELECT startDate, endDate, price FROM hotel_data ORDER BY price ASC LIMIT 10";

        // Establish JDBC connection
        try (Connection connection = DriverManager.getConnection(url);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            // process cheapest dates and prices
            System.out.println("10 Cheapest Prices with Dates:");
            int count = 0;
            while (resultSet.next()) {
                String startDate = resultSet.getString("startDate");
                String endDate = resultSet.getString("endDate");
                double price = resultSet.getDouble("price");

                System.out.println("Price " + ++count + ": Start Date: " + startDate + ", End Date: " + endDate + ", Price: $" + price);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


