import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// POJO for Order details (Assignment 1)
class OrderInfo {
    private int orderId;
    private Timestamp orderDate;
    private double orderTotal;
    
    public OrderInfo(int orderId, Timestamp orderDate, double orderTotal) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.orderTotal = orderTotal;
    }
    
    public int getOrderId() {
        return orderId;
    }
    
    public Timestamp getOrderDate() {
        return orderDate;
    }
    
    public double getOrderTotal() {
        return orderTotal;
    }
    
    @Override
    public String toString() {
        return "OrderInfo [orderId=" + orderId + ", orderDate=" + orderDate + ", orderTotal=" + orderTotal + "]";
    }
}

// POJO for Category result (Assignment 4)
class CategoryInfo {
    private String categoryTitle;
    private int childCount;
    
    public CategoryInfo(String categoryTitle, int childCount) {
        this.categoryTitle = categoryTitle;
        this.childCount = childCount;
    }
    
    public String getCategoryTitle() {
        return categoryTitle;
    }
    
    public int getChildCount() {
        return childCount;
    }
    
    @Override
    public String toString() {
        return "CategoryInfo [categoryTitle=" + categoryTitle + ", childCount=" + childCount + "]";
    }
}

// Helper class to manage DB connection and queries
class DBHelper {
    // Change these values as per your database configuration.
    private static final String URL = "jdbc:mysql://localhost:3306/Storefront";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    
    // Closes resources safely.
    public static void close(AutoCloseable ac) {
        if (ac != null) {
            try {
                ac.close();
            } catch (Exception e) {
                System.err.println("Error closing resource: " + e.getMessage());
            }
        }
    }
}

public class StoreFrontJDBCApp {
    
    // Assignment 1:
    // Given a user id, fetch all orders (Id, Order Date, Order Total) of that user 
    // which are in shipped state, sorted in chronological order.
    public static List<OrderInfo> getShippedOrdersByUser(int userId) {
        List<OrderInfo> orders = new ArrayList<>();
        String query = "SELECT O.OrderID, O.OrderDate, SUM(P.Price * OI.Quantity) AS OrderTotal " +
                     "FROM OrderTable O " +
                     "JOIN OrderItem OI ON O.OrderID = OI.OrderID " +
                     "JOIN Product P ON OI.ProductID = P.ProductID " +
                     "WHERE O.ShopperID = ? AND OI.Status = 'Shipped' " +
                     "GROUP BY O.OrderID, O.OrderDate " +
                     "ORDER BY O.OrderDate ASC";
        try (Connection conn = DBHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int orderId = rs.getInt("OrderID");
                    Timestamp orderDate = rs.getTimestamp("OrderDate");
                    double orderTotal = rs.getDouble("OrderTotal");
                    orders.add(new OrderInfo(orderId, orderDate, orderTotal));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching shipped orders: " + e.getMessage());
        }
        return orders;
    }
    
    // Assignment 2:
    // Insert five or more images for a product using batch insert technique.
    public static void insertProductImages(int productId, List<String> imageUrls) {
        String sql = "INSERT INTO Image (URL, ProductID) VALUES (?, ?)";
        try (Connection conn = DBHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            conn.setAutoCommit(false);
            for (String url : imageUrls) {
                ps.setString(1, url);
                ps.setInt(2, productId);
                ps.addBatch();
            }
            int[] counts = ps.executeBatch();
            conn.commit();
            System.out.println("Batch insert completed. Rows inserted: " + counts.length);
        } catch (SQLException e) {
            System.err.println("Error in batch insert for images: " + e.getMessage());
        }
    }
    
    // Assignment 3:
    // Delete all products which were not ordered by any shopper in last 1 year.
    // Return the number of products deleted.
    public static int deleteProductsNotOrderedInLastYear() {
        int deletedCount = 0;
        // Subquery to fetch ProductIDs that were ordered in the last 1 year.
        String subQuery = "SELECT DISTINCT OI.ProductID FROM OrderItem OI " +
                          "JOIN OrderTable O ON OI.OrderID = O.OrderID " +
                          "WHERE O.OrderDate >= DATE_SUB(CURRENT_DATE, INTERVAL 1 YEAR)";
        // Delete products not in the subquery.
        String sql = "DELETE FROM Product WHERE ProductID NOT IN (" + subQuery + ")";
        try (Connection conn = DBHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            deletedCount = ps.executeUpdate();
            System.out.println("Products deleted: " + deletedCount);
        } catch (SQLException e) {
            System.err.println("Error deleting products: " + e.getMessage());
        }
        return deletedCount;
    }
    
    // Assignment 4:
    // Select and display the category title of all top parent categories (ParentCategoryID IS NULL)
    // sorted alphabetically and the count of their child categories.
    public static List<CategoryInfo> getTopParentCategoriesWithChildCount() {
        List<CategoryInfo> categories = new ArrayList<>();
        String sql = "SELECT C.Name AS CategoryTitle, COUNT(Child.CategoryID) AS ChildCount " +
                     "FROM Category C " +
                     "LEFT JOIN Category Child ON C.CategoryID = Child.ParentCategoryID " +
                     "WHERE C.ParentCategoryID IS NULL " +
                     "GROUP BY C.CategoryID, C.Name " +
                     "ORDER BY C.Name ASC";
        try (Connection conn = DBHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String title = rs.getString("CategoryTitle");
                int count = rs.getInt("ChildCount");
                categories.add(new CategoryInfo(title, count));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching top parent categories: " + e.getMessage());
        }
        return categories;
    }
    
    public static void main(String[] args) {
        // Example usage for Assignment 1:
        int sampleUserId = 1;  // Change to a valid user id from your database.
        List<OrderInfo> orders = getShippedOrdersByUser(sampleUserId);
        System.out.println("Shipped orders for user " + sampleUserId + ":");
        for (OrderInfo order : orders) {
            System.out.println(order);
        }
        
        // Example usage for Assignment 2:
        int sampleProductId = 1;  // Change to a valid product id.
        List<String> imageUrls = new ArrayList<>();
        imageUrls.add("http://example.com/images/prod1_img1.jpg");
        imageUrls.add("http://example.com/images/prod1_img2.jpg");
        imageUrls.add("http://example.com/images/prod1_img3.jpg");
        imageUrls.add("http://example.com/images/prod1_img4.jpg");
        imageUrls.add("http://example.com/images/prod1_img5.jpg");
        insertProductImages(sampleProductId, imageUrls);
        
        // Example usage for Assignment 3:
        int productsDeleted = deleteProductsNotOrderedInLastYear();
        System.out.println("Number of products deleted: " + productsDeleted);
        
        // Example usage for Assignment 4:
        List<CategoryInfo> categoryInfos = getTopParentCategoriesWithChildCount();
        System.out.println("Top parent categories with child count:");
        for (CategoryInfo ci : categoryInfos) {
            System.out.println(ci);
        }
    }
}
