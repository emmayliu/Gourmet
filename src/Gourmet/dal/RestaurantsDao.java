package Gourmet.dal;

import Gourmet.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RestaurantsDao {
    protected ConnectionManager connectionManager;
    
    // Single pattern: instantiation is limited to one object.
    private static RestaurantsDao instance = null;
    protected RestaurantsDao() {
        connectionManager = new ConnectionManager();
    }
    public static RestaurantsDao getInstance() {
        if(instance == null) {
            instance = new RestaurantsDao();
        }
        return instance;
    }

    /**
     * Save the Persons instance by storing it in your MySQL instance.
     * This runs a INSERT statement.
     */
    
    public Restaurants create(Restaurants restaurant) throws SQLException {
        String insertRestaurant = "INSERT INTO Restaurants(RestaurantId,RestaurantName,AcceptsCreditCard,WIFI,PriceRange,OpenTime,"
                + "CloseTime,NoiseLevel,Neighborhood,Star,Parking,Street,City,State,ZipCode) "
                + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertRestaurant);

            insertStmt.setString(1, restaurant.getRestaurantId());
            insertStmt.setString(2, restaurant.getName());
            insertStmt.setBoolean(3, restaurant.getAcceptsCreditCard());
            insertStmt.setBoolean(4, restaurant.getWIFI());
            insertStmt.setInt(5, restaurant.getPriceRange());
            insertStmt.setTime(6, new Time(restaurant.getOpen().getTime()));
            insertStmt.setTime(7, new Time(restaurant.getClose().getTime()));
            insertStmt.setInt(8, restaurant.getNoiseLevel());
            insertStmt.setString(9, restaurant.getNeighborhood());
            insertStmt.setDouble(10, restaurant.getStar());
            insertStmt.setInt(11, restaurant.getParking());
            insertStmt.setString(12, restaurant.getStreet());
            insertStmt.setString(13, restaurant.getCity());
            insertStmt.setString(14, restaurant.getState());
            insertStmt.setInt(15, restaurant.getZipCode());
            insertStmt.executeUpdate();
            
            return restaurant;
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
        }
    }



    /**
     * Delete the Restaurant instance.
     * This runs a DELETE statement.
     */
    public Restaurants delete(Restaurants restaurant) throws SQLException {
        String deleteRestaurant = "DELETE FROM Restaurants WHERE RestaurantId=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteRestaurant);
            deleteStmt.setString(1, restaurant.getRestaurantId());
            deleteStmt.executeUpdate();

            // Return null so the caller can no longer operate on the Restaurant instance.
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

    /**
     * Get the Restaurant record by fetching it from your MySQL instance.
     * This runs a SELECT statement and returns a single Restaurant instance.
     */
    public Restaurants getRestaurantById(String restaurantId) throws SQLException {
        String selectRestaurant = "SELECT RestaurantId,RestaurantName,AcceptsCreditCard,WIFI,PriceRange,OpenTime,CloseTime,NoiseLevel,"
                + "Neighborhood, Star, Parking,Street,City,State,Zipcode "
                + "FROM Restaurants WHERE RestaurantId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectRestaurant);
            selectStmt.setString(1, restaurantId);
            // Note that we call executeQuery(). This is used for a SELECT statement
            // because it returns a result set. For more information, see:
            // http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
            // http://docs.oracle.com/javase/7/docs/api/java/sql/ResultSet.html
             results = selectStmt.executeQuery();
            // You can iterate the result set (although the example below only retrieves 
            // the first record). The cursor is initially positioned before the row.
            // Furthermore, you can retrieve fields by name and by type.
            if(results.next()) {
                String resultRestaurantId = results.getString("RestaurantId");
                String name = results.getString("RestaurantName");
                boolean acceptcreditcard = results.getBoolean("AcceptsCreditCard");
                boolean wifi = results.getBoolean("WIFI");
                int pricerange = results.getInt("PriceRange");
                Time open = results.getTime("OpenTime");
                Time close = results.getTime("CloseTime");
                int noiselevel = results.getInt("NoiseLevel");
                String neighborhood = results.getString("Neighborhood");
                double star = results.getDouble("Star");
                int parking = results.getInt("Parking");            
                String street = results.getString("Street");
                String city = results.getString("City");
                String state = results.getString("State");
                int zip = results.getInt("Zipcode");
                Restaurants restaurant = new Restaurants(resultRestaurantId, name, acceptcreditcard,wifi,pricerange,open,close,
                        noiselevel,neighborhood,star,parking,street,city,state,zip);
                return restaurant;
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
    
    public List<Restaurants> getRestaurantByCriteria(Map<String, String> criteria) throws SQLException {
    	 List<Restaurants> restaurantList = new ArrayList<Restaurants>();
    	 String base = "SELECT Restaurants.RestaurantId,RestaurantName,AcceptsCreditCard,WIFI,PriceRange,OpenTime,CloseTime,NoiseLevel,"
                 + "Neighborhood, Star, Parking,Street,City,State,Zipcode "
                 + "FROM ((Restaurants inner join Categories on Restaurants.RestaurantId = Categories.RestaurantId) "
                 + " inner join GoodFor on Restaurants.RestaurantId = GoodFor.RestaurantId)"
                 + " WHERE ";
    	 String restrictionsSubQuery = "";
    	 boolean isValid = false;
    	 for (String col : criteria.keySet()) {
    		 if(criteria.get(col) != null && !criteria.get(col).isEmpty()) {
    			 isValid = true;
    			 String value = criteria.get(col);
    			 if (!col.equals("priceRange")) {
    				 value = "'" + value + "'";
    			 }
    			 
    			 if(col.equals("cuisineType") || col.equals("goodFor")) {
    				 restrictionsSubQuery += criteria.get(col) + "=1 and ";
    			 } else {
    				 restrictionsSubQuery += col + "=" +  value + " and ";
    			 }
    		 }
    	 }
    	 if (isValid = false) {
    		 return null;
    	 }
    	 restrictionsSubQuery = restrictionsSubQuery.substring(0, restrictionsSubQuery.length() - 4);
    	 String query = base + restrictionsSubQuery + ';';
    	 System.out.print(query);
    	 

    	 Connection connection = null;
         PreparedStatement selectStmt = null;
         ResultSet results = null;
         try {
             connection = connectionManager.getConnection();
             selectStmt = connection.prepareStatement(query);   
             results = selectStmt.executeQuery();
         
             while(results.next()) {
                 String restaurantId = results.getString("RestaurantId");
                 String resultRestaurantName = results.getString("RestaurantName");
                 boolean acceptcreditcard = results.getBoolean("AcceptsCreditCard");
                 boolean wifi = results.getBoolean("WIFI");
                 int pricerange = results.getInt("PriceRange");
                 Time open = results.getTime("OpenTime");
                 Time close = results.getTime("CloseTime");
                 int noiselevel = results.getInt("NoiseLevel");
                 String neighborhood = results.getString("Neighborhood");
                 double star = results.getDouble("Star");
                 int parking = results.getInt("Parking");            
                 String street = results.getString("Street");
                 String city = results.getString("City");
                 String state = results.getString("State");
                 int zip = results.getInt("Zipcode");
                 Restaurants restaurant = new Restaurants(restaurantId, resultRestaurantName, acceptcreditcard,wifi,pricerange,
                         open,close,noiselevel,neighborhood,star,parking,street,city,state,zip);
                 restaurantList.add(restaurant);
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
         return restaurantList;
    }
    
    public List<Restaurants> getRestaurantByName(String restaurantName) throws SQLException {
        List<Restaurants> restaurantList = new ArrayList<Restaurants>();
        String selectRestaurant = "SELECT RestaurantId,RestaurantName,AcceptsCreditCard,WIFI,PriceRange,OpenTime,CloseTime,NoiseLevel,"
                + "Neighborhood, Star, Parking,Street,City,State,Zipcode "
                + "FROM Restaurants WHERE RestaurantName=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectRestaurant);
            selectStmt.setString(1, restaurantName);
        
            results = selectStmt.executeQuery();
        
            while(results.next()) {
                String restaurantId = results.getString("RestaurantId");
                String resultRestaurantName = results.getString("RestaurantName");
                boolean acceptcreditcard = results.getBoolean("AcceptsCreditCard");
                boolean wifi = results.getBoolean("WIFI");
                int pricerange = results.getInt("PriceRange");
                Time open = results.getTime("OpenTime");
                Time close = results.getTime("CloseTime");
                int noiselevel = results.getInt("NoiseLevel");
                String neighborhood = results.getString("Neighborhood");
                double star = results.getDouble("Star");
                int parking = results.getInt("Parking");            
                String street = results.getString("Street");
                String city = results.getString("City");
                String state = results.getString("State");
                int zip = results.getInt("Zipcode");
                Restaurants restaurant = new Restaurants(restaurantId, resultRestaurantName, acceptcreditcard,wifi,pricerange,
                        open,close,noiselevel,neighborhood,star,parking,street,city,state,zip);
                restaurantList.add(restaurant);
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
        return restaurantList;
    }    

    /**
     * Get the matching Restaurants records by fetching from your MySQL instance.
     * This runs a SELECT statement and returns a list of matching Persons.
     */
    public List<Restaurants> getRestaurantsByPriceRange(int pr) throws SQLException {
        List<Restaurants> restaurants = new ArrayList<Restaurants>();
        String selectRestaurants = "SELECT RestaurantId,RestaurantName,AcceptsCreditCard,WIFI,PriceRange,OpenTime,"
        		+ "CloseTime,NoiseLevel, Neighborhood,Star,Parking,Street,City,State,Zipcode FROM Restaurants WHERE PriceRange = ?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectRestaurants);
            selectStmt.setInt(1, pr);
            results = selectStmt.executeQuery();
            System.out.println(results);
            while(results.next()){
                String resultRestaurantId = results.getString("RestaurantId");
                String name = results.getString("RestaurantName");
                boolean acceptcreditcard = results.getBoolean("AcceptsCreditCard");
                boolean wifi = results.getBoolean("WIFI");
                int pricerange = results.getInt("PriceRange");
                Time open = results.getTime("OpenTime");
                Time close = results.getTime("CloseTime");
                int noiselevel = results.getInt("NoiseLevel");
                String neighborhood = results.getString("Neighborhood");
                double star = results.getDouble("Star");
                int parking = results.getInt("Parking");            
                String street = results.getString("Street");
                String city = results.getString("City");
                String state = results.getString("State");
                int zip = results.getInt("Zipcode");
                Restaurants restaurant = new Restaurants(resultRestaurantId, name, acceptcreditcard,wifi,pricerange,open,close,
                        noiselevel,neighborhood,star,parking,street,city,state,zip);
                    restaurants.add(restaurant);
            }
        }
        catch (SQLException e) {
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
        return restaurants;
    }

    public List<Restaurants> getRestaurantsByCity(String ct) throws SQLException {
        List<Restaurants> restaurants = new ArrayList<Restaurants>();
        String selectRestaurants = "SELECT RestaurantId,RestaurantName,AcceptsCreditCard,WIFI,PriceRange,OpenTime,CloseTime,NoiseLevel,"
                + "Neighborhood,Star, Parking,Street,City,State,Zipcode "
                + "FROM Restaurants WHERE city=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectRestaurants);
            selectStmt.setString(1, ct);
            results = selectStmt.executeQuery();
            while(results.next()){
            String resultRestaurantId = results.getString("RestaurantId");
            String name = results.getString("RestaurantName");
            boolean acceptcreditcard = results.getBoolean("AcceptsCreditCard");
            boolean wifi = results.getBoolean("WIFI");
            int pricerange = results.getInt("PriceRange");
            Time open = results.getTime("OpenTime");
            Time close = results.getTime("CloseTime");
            int noiselevel = results.getInt("NoiseLevel");
            String neighborhood = results.getString("Neighborhood");
            double star = results.getDouble("Star");
            int parking = results.getInt("Parking");            
            String street = results.getString("Street");
            String city = results.getString("City");
            String state = results.getString("State");
            int zip = results.getInt("Zipcode");
            Restaurants restaurant = new Restaurants(resultRestaurantId, name, acceptcreditcard,wifi,pricerange,open,close,
                    noiselevel,neighborhood,star,parking,street,city,state,zip);
                restaurants.add(restaurant);
            }
        }
        catch (SQLException e) {
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
        return restaurants;
    }
    
    public List<Restaurants> getRestaurantsByStar(double selectedstar) throws SQLException {
        List<Restaurants> restaurants = new ArrayList<Restaurants>();
        String selectRestaurants = "SELECT RestaurantId,RestaurantName,AcceptsCreditCard,WIFI,PriceRange,OpenTime,CloseTime,NoiseLevel,"
                + "Neighborhood,Star, Parking,Street,City,State,Zipcode "
                + "FROM Restaurants WHERE Star >= ?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectRestaurants);
            selectStmt.setDouble(1, selectedstar);
            results = selectStmt.executeQuery();
            while(results.next()){
            String resultRestaurantId = results.getString("RestaurantId");
            String name = results.getString("RestaurantName");
            boolean acceptcreditcard = results.getBoolean("AcceptsCreditCard");
            boolean wifi = results.getBoolean("WIFI");
            int pricerange = results.getInt("PriceRange");
            Time open = results.getTime("OpenTime");
            Time close = results.getTime("CloseTime");
            int noiselevel = results.getInt("NoiseLevel");
            String neighborhood = results.getString("Neighborhood");
            double star = results.getDouble("Star");
            int parking = results.getInt("Parking");            
            String street = results.getString("Street");
            String city = results.getString("City");
            String state = results.getString("State");
            int zip = results.getInt("Zipcode");
            Restaurants restaurant = new Restaurants(resultRestaurantId, name, acceptcreditcard,wifi,pricerange,open,close,
                    noiselevel,neighborhood,star,parking,street,city,state,zip);
                restaurants.add(restaurant);
            }
        }
        catch (SQLException e) {
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
        return restaurants;
    }
    
    // Cuisine Type
    public List<Restaurants> getRestaurantsByCuisine(String cuisine) throws SQLException {
        List<Restaurants> restaurants = new ArrayList<Restaurants>();
        String selectRestaurants = "SELECT Restaurants.RestaurantId,RestaurantName,AcceptsCreditCard,WIFI,PriceRange,OpenTime,CloseTime,NoiseLevel,"
                + "Neighborhood,Star, Parking,Street,City,State,Zipcode "
                + "FROM Restaurants INNER JOIN Categories ON Restaurants.RestaurantId = Categories.RestaurantId"
                + " WHERE " + cuisine + " = 1;"; 
        System.out.print(selectRestaurants);

        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectRestaurants);
            results = selectStmt.executeQuery();
            while(results.next()){
            String resultRestaurantId = results.getString("RestaurantId");
            String name = results.getString("RestaurantName");
            boolean acceptcreditcard = results.getBoolean("AcceptsCreditCard");
            boolean wifi = results.getBoolean("WIFI");
            int pricerange = results.getInt("PriceRange");
            Time open = results.getTime("OpenTime");
            Time close = results.getTime("CloseTime");
            int noiselevel = results.getInt("NoiseLevel");
            String neighborhood = results.getString("Neighborhood");
            double star = results.getDouble("Star");
            int parking = results.getInt("Parking");            
            String street = results.getString("Street");
            String city = results.getString("City");
            String state = results.getString("State");
            int zip = results.getInt("Zipcode");
            Restaurants restaurant = new Restaurants(resultRestaurantId, name, acceptcreditcard,wifi,pricerange,open,close,
                    noiselevel,neighborhood,star,parking,street,city,state,zip);
                restaurants.add(restaurant);
            }
        }
        catch (SQLException e) {
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
        return restaurants;
    } 
    
 // Good For
    public List<Restaurants> getRestaurantsByGoodFor(String goodFor) throws SQLException {
        List<Restaurants> restaurants = new ArrayList<Restaurants>();
        String selectRestaurants = "SELECT Restaurants.RestaurantId,RestaurantName,AcceptsCreditCard,WIFI,PriceRange,OpenTime,CloseTime,NoiseLevel,"
                + "Neighborhood,Star, Parking,Street,City,State,Zipcode "
                + "FROM Restaurants INNER JOIN GoodFor ON Restaurants.RestaurantId = GoodFor.RestaurantId"
                + " WHERE " + goodFor + " = 1;"; 
        System.out.print(selectRestaurants);

        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectRestaurants);
            results = selectStmt.executeQuery();
            while(results.next()){
            String resultRestaurantId = results.getString("RestaurantId");
            String name = results.getString("RestaurantName");
            boolean acceptcreditcard = results.getBoolean("AcceptsCreditCard");
            boolean wifi = results.getBoolean("WIFI");
            int pricerange = results.getInt("PriceRange");
            Time open = results.getTime("OpenTime");
            Time close = results.getTime("CloseTime");
            int noiselevel = results.getInt("NoiseLevel");
            String neighborhood = results.getString("Neighborhood");
            double star = results.getDouble("Star");
            int parking = results.getInt("Parking");            
            String street = results.getString("Street");
            String city = results.getString("City");
            String state = results.getString("State");
            int zip = results.getInt("Zipcode");
            Restaurants restaurant = new Restaurants(resultRestaurantId, name, acceptcreditcard,wifi,pricerange,open,close,
                    noiselevel,neighborhood,star,parking,street,city,state,zip);
                restaurants.add(restaurant);
            }
        }
        catch (SQLException e) {
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
        return restaurants;
    }    
    
    // update star
public Restaurants updateStar(Restaurants restaurant, Double newStar) throws SQLException {
    String updateStar = "UPDATE Restaurants SET Star=?  WHERE RestaurantId=?;";
    Connection connection = null;
    PreparedStatement updateStmt = null;
    try {
        connection = connectionManager.getConnection();
        updateStmt = connection.prepareStatement(updateStar);
        updateStmt.setDouble(1, newStar);
        updateStmt.setString(2, restaurant.getRestaurantId());
        updateStmt.executeUpdate();
        
        restaurant.setStar(newStar);
        return restaurant;
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

// update price range 
public Restaurants updatePriceRange(Restaurants restaurant, int newPriceRange) throws SQLException {
    String updateStar = "UPDATE Restaurants SET PriceRange=?  WHERE RestaurantId=?;";
    Connection connection = null;
    PreparedStatement updateStmt = null;
    try {
        connection = connectionManager.getConnection();
        updateStmt = connection.prepareStatement(updateStar);
        updateStmt.setDouble(1, newPriceRange);
        updateStmt.setString(2, restaurant.getRestaurantId());
        updateStmt.executeUpdate();
        
        restaurant.setPriceRange(newPriceRange);
        return restaurant;
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

    // update noise level
    public Restaurants updateNoiseLevel(Restaurants restaurant, int newNoiseLevel) throws SQLException {
        String updateStar = "UPDATE Restaurants SET NoiseLevel=?  WHERE RestaurantId=?;";
        Connection connection = null;
        PreparedStatement updateStmt = null;
        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updateStar);
            updateStmt.setDouble(1, newNoiseLevel);
            updateStmt.setString(2, restaurant.getRestaurantId());
            updateStmt.executeUpdate();
            
            restaurant.setNoiseLevel(newNoiseLevel);
            return restaurant;
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
    
    // update open time
    public Restaurants updateOpenTime(Restaurants restaurant, Time newOpenTime) throws SQLException {
        String updateStar = "UPDATE Restaurants SET OpenTime=?  WHERE RestaurantId=?;";
        Connection connection = null;
        PreparedStatement updateStmt = null;
        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updateStar);
            updateStmt.setTime(1, newOpenTime);
            updateStmt.setString(2, restaurant.getRestaurantId());
            updateStmt.executeUpdate();
            
            restaurant.setOpen(newOpenTime);
            return restaurant;
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
    
    // update close time
    public Restaurants updateCloseTime(Restaurants restaurant, Time newCloseTime) throws SQLException {
        String updateStar = "UPDATE Restaurants SET CloseTime=?  WHERE RestaurantId=?;";
        Connection connection = null;
        PreparedStatement updateStmt = null;
        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updateStar);
            updateStmt.setTime(1, newCloseTime);
            updateStmt.setString(2, restaurant.getRestaurantId());
            updateStmt.executeUpdate();
            
            restaurant.setClose(newCloseTime);
            return restaurant;
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
    
    // update wifi
    public Restaurants updateWifi(Restaurants restaurant, Boolean newWifi) throws SQLException {
        String updateStar = "UPDATE Restaurants SET WIFI=?  WHERE RestaurantId=?;";
        Connection connection = null;
        PreparedStatement updateStmt = null;
        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updateStar);
            updateStmt.setBoolean(1, newWifi);
            updateStmt.setString(2, restaurant.getRestaurantId());
            updateStmt.executeUpdate();
            
            restaurant.setWIFI(newWifi);
            return restaurant;
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
    
    // update whether accept credit card
    public Restaurants updateAcceptsCreditCard(Restaurants restaurant, Boolean newaccept) throws SQLException {
        String updateStar = "UPDATE Restaurants SET AcceptsCreditCard=?  WHERE RestaurantId=?;";
        Connection connection = null;
        PreparedStatement updateStmt = null;
        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updateStar);
            updateStmt.setBoolean(1, newaccept);
            updateStmt.setString(2, restaurant.getRestaurantId());
            updateStmt.executeUpdate();
            
            restaurant.setAcceptsCreditCard(newaccept);
            return restaurant;
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
}
