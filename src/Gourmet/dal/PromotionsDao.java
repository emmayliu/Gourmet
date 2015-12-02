
package Gourmet.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Gourmet.model.*;
public class PromotionsDao {
    protected ConnectionManager connectionManager;

    private static PromotionsDao instance = null;
    protected PromotionsDao() {
        connectionManager = new ConnectionManager();
    }
    public static PromotionsDao getInstance() {
        if(instance == null) {
            instance = new PromotionsDao();
        }
        return instance;
    }

    public Promotions create(Promotions promotion) throws SQLException {
        String insertPromotions =
            "INSERT INTO Promotions(RestaurantId,StartTime,EndTime,Discount) " +
            "VALUES(?,?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        ResultSet resultKey = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertPromotions, Statement.RETURN_GENERATED_KEYS);
            
            insertStmt.setString(1, promotion.getRestaurant().getRestaurantId());
            insertStmt.setTimestamp(2, new Timestamp(promotion.getStartTime().getTime()));
            insertStmt.setTimestamp(3, new Timestamp(promotion.getEndTime().getTime()));
            insertStmt.setDouble(4, promotion.getDiscount());
        
            insertStmt.executeUpdate();
            
            // Retrieve the auto-generated key and set it, so it can be used by the caller.
            resultKey = insertStmt.getGeneratedKeys();
            int promotionId = -1;
            if(resultKey.next()) {
                promotionId = resultKey.getInt(1);
            } else {
                throw new SQLException("Unable to retrieve auto-generated key.");
            }
            promotion.setPromotionId(promotionId);
            return promotion;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
            if(insertStmt != null) {
                insertStmt.close();
            }
            if(resultKey != null) {
                resultKey.close();
            }
        }
    }
    
    public Promotions getPromtionById(int promotionId) throws SQLException {
        String selectPromotion =
            "SELECT PromotionId,RestaurantId,StartTime,EndTime,Discount" +
            "FROM Promotions " +
            "WHERE PromotionId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectPromotion);
            selectStmt.setInt(1, promotionId);
            results = selectStmt.executeQuery();
            RestaurantsDao restaurantsDao = RestaurantsDao.getInstance();
            if(results.next()) {
                int resultpromotionId = results.getInt("PromotionId");
                String restaurantId = results.getString("RestaurantId");
                Date startTime =  new Date(results.getTimestamp("StartTime").getTime());
                Date endTime =  new Date(results.getTimestamp("EndTime").getTime());
                Double discount = results.getDouble("Discount");
                
                
                Restaurants restaurant = restaurantsDao.getRestaurantById(restaurantId);
                Promotions promotion = new Promotions(resultpromotionId, restaurant, startTime, endTime, discount);
                return promotion;
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
            if(selectStmt != null) {
                selectStmt.close();
            }
            if(results != null) {
                results.close();
            }
        }
        return null;
    }
    
    public Promotions getPromtionByRestaurntId(String restaurantId) throws SQLException {
        String selectPromotion =
            "SELECT PromotionId,RestaurantId,StartTime,EndTime,Discount" +
            "FROM Promotions " +
            "WHERE RestaurantId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectPromotion);
            selectStmt.setString(1, restaurantId);
            results = selectStmt.executeQuery();
            RestaurantsDao restaurantsDao = RestaurantsDao.getInstance();
            if(results.next()) {
                int resultpromotionId = results.getInt("PromotionId");
                String resultrestaurantId = results.getString("RestaurantId");
                Date startTime =  new Date(results.getTimestamp("StartTime").getTime());
                Date endTime =  new Date(results.getTimestamp("EndTime").getTime());
                Double discount = results.getDouble("Discount");
                
                
                Restaurants restaurant = restaurantsDao.getRestaurantById(resultrestaurantId);
                Promotions promotion = new Promotions(resultpromotionId, restaurant, startTime, endTime, discount);
                return promotion;
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
            if(selectStmt != null) {
                selectStmt.close();
            }
            if(results != null) {
                results.close();
            }
        }
        return null;
    }
    

    public List<Promotions> getPromotionsByDiscount(Double discount) throws SQLException {
        List<Promotions> promotions = new ArrayList<Promotions>();
        String selectPromotions =
                "SELECT PromotionId,RestaurantId,StartTime,EndTime,Discount" +
                "FROM Promotions " +
                "WHERE Discount=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectPromotions);
            selectStmt.setDouble(1,discount);
            results = selectStmt.executeQuery();
            RestaurantsDao restaurantsDao = RestaurantsDao.getInstance();
            while(results.next()) {
                int resultpromotionId = results.getInt("PromotionId");
                String resultrestaurantId = results.getString("RestaurantId");
                Date startTime =  new Date(results.getTimestamp("StartTime").getTime());
                Date endTime =  new Date(results.getTimestamp("EndTime").getTime());
                Double resultdiscount = results.getDouble("Discount");

                Restaurants restaurant = restaurantsDao.getRestaurantById(resultrestaurantId);
                Promotions promotion = new Promotions(resultpromotionId, restaurant, startTime, endTime, resultdiscount);
                promotions.add(promotion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
            if(selectStmt != null) {
                selectStmt.close();
            }
            if(results != null) {
                results.close();
            }
        }
        return promotions;
    }
    
    public List<Promotions> getPromotionsByStartTime(java.sql.Date startTime) throws SQLException {
        List<Promotions> promotions = new ArrayList<Promotions>();
        String selectPromotions =
                "SELECT PromotionId,RestaurantId,StartTime,EndTime,Discount" +
                "FROM Promotions " +
                "WHERE StartTime=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectPromotions);
            selectStmt.setDate(1,startTime);
            results = selectStmt.executeQuery();
            RestaurantsDao restaurantsDao = RestaurantsDao.getInstance();
            while(results.next()) {
                int resultpromotionId = results.getInt("PromotionId");
                String resultrestaurantId = results.getString("RestaurantId");
                Date resultstartTime =  new Date(results.getTimestamp("StartTime").getTime());
                Date endTime =  new Date(results.getTimestamp("EndTime").getTime());
                Double resultdiscount = results.getDouble("Discount");

                Restaurants restaurant = restaurantsDao.getRestaurantById(resultrestaurantId);
                Promotions promotion = new Promotions(resultpromotionId, restaurant, resultstartTime, endTime, resultdiscount);
                promotions.add(promotion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
            if(selectStmt != null) {
                selectStmt.close();
            }
            if(results != null) {
                results.close();
            }
        }
        return promotions;
    }
    
    public List<Promotions> getPromotionsByEndTime(java.sql.Date endTime) throws SQLException {
        List<Promotions> promotions = new ArrayList<Promotions>();
        String selectPromotions =
                "SELECT PromotionId,RestaurantId,StartTime,EndTime,Discount" +
                "FROM Promotions " +
                "WHERE EndTime=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectPromotions);
            selectStmt.setDate(1,endTime);
            results = selectStmt.executeQuery();
            RestaurantsDao restaurantsDao = RestaurantsDao.getInstance();
            while(results.next()) {
                int resultpromotionId = results.getInt("PromotionId");
                String resultrestaurantId = results.getString("RestaurantId");
                Date resultstartTime =  new Date(results.getTimestamp("StartTime").getTime());
                Date resultendTime =  new Date(results.getTimestamp("EndTime").getTime());
                Double resultdiscount = results.getDouble("Discount");

                Restaurants restaurant = restaurantsDao.getRestaurantById(resultrestaurantId);
                Promotions promotion = new Promotions(resultpromotionId, restaurant, resultstartTime, resultendTime, resultdiscount);
                promotions.add(promotion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
            if(selectStmt != null) {
                selectStmt.close();
            }
            if(results != null) {
                results.close();
            }
        }
        return promotions;
    }
    
    // update discount
            public Promotions updateDiscount(Promotions promotion , Double newDiscount) throws SQLException {
                String updateDiscount = "UPDATE Promotions SET Discount=?  WHERE PromotionId=?;";
                Connection connection = null;
                PreparedStatement updateStmt = null;
                try {
                    connection = connectionManager.getConnection();
                    updateStmt = connection.prepareStatement(updateDiscount);
                    updateStmt.setDouble(1, newDiscount);
                    updateStmt.setInt(2, promotion.getPromotionId());
                    updateStmt.executeUpdate();
                    
                   promotion.setDiscount(newDiscount);
                    return promotion;
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw e;
                } finally {
                    if(connection != null) {
                        connection.close();
                    }
                    if(updateStmt != null) {
                        updateStmt.close();
                    }
                }
            }
    
    
    /**
     * Delete the Reviews instance.
     * This runs a DELETE statement.
     */
    public Promotions delete(Promotions promotion) throws SQLException {
        String deleteReview = "DELETE FROM Promotions WHERE PromotionId=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteReview);
            deleteStmt.setInt(1, promotion.getPromotionId());
            deleteStmt.executeUpdate();

            // Return null so the caller can no longer operate on the BlogComments instance.
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(connection != null) {
                connection.close();
            }
            if(deleteStmt != null) {
                deleteStmt.close();
            }
        }
    }
    
}
