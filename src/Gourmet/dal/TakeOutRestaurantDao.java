
package Gourmet.dal;

import Gourmet.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;


public class TakeOutRestaurantDao extends RestaurantsDao {
	protected ConnectionManager connectionManager;
	
	private static TakeOutRestaurantDao instance = null;
	protected TakeOutRestaurantDao() {
		connectionManager = new ConnectionManager();
	}
	public static TakeOutRestaurantDao getInstance() {
		if(instance == null) {
			instance = new TakeOutRestaurantDao();
		}
		return instance;
	}

	/**
	 * Save the DeliveryRestaurant instance by storing it in your MySQL instance.
	 * This runs a INSERT statement.
	 */
	public TakeOutRestaurant create(TakeOutRestaurant takeOutRestaurant) throws SQLException {
		Restaurants newRestaurant = create(new Restaurants(takeOutRestaurant.getRestaurantId(), 
				takeOutRestaurant.getName(), takeOutRestaurant.getAcceptsCreditCard(), takeOutRestaurant.getWIFI(),
				takeOutRestaurant.getPriceRange(), takeOutRestaurant.getOpen(), takeOutRestaurant.getClose(), 
				takeOutRestaurant.getNoiseLevel(), takeOutRestaurant.getNeighborhood(), takeOutRestaurant.getStar(),
				takeOutRestaurant.getParking(), takeOutRestaurant.getStreet(), takeOutRestaurant.getCity(),
				takeOutRestaurant.getState(), takeOutRestaurant.getZipCode()));
		
		String insertTakeOutRestaurant = "INSERT INTO TakeOutRestaurant(RestaurantId,MaxWaitTime) VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertTakeOutRestaurant);		
			insertStmt.setString(1, newRestaurant.getRestaurantId());
			insertStmt.setInt(2, takeOutRestaurant.getMaxWaitTime());
			insertStmt.executeUpdate();
			
			return takeOutRestaurant;
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
	
	// update max wait time
	public TakeOutRestaurant updateMaxWaitTime(TakeOutRestaurant takeoutRestaurant, int newMaxWaitTime ) throws SQLException {
		String updateTakeOutRestaurant = "UPDATE TakeOutRestaurant SET MaxWaitTime=? WHERE RestaurantId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateTakeOutRestaurant);
			updateStmt.setInt(1, newMaxWaitTime);
			updateStmt.setString(2, takeoutRestaurant.getRestaurantId());
			updateStmt.executeUpdate();
			
			
			takeoutRestaurant.setMaxWaitTime(newMaxWaitTime);
			return takeoutRestaurant;
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
	 * Delete the TakeOutRestaurant instance.
	 * This runs a DELETE statement.
	 */
	public TakeOutRestaurant delete(TakeOutRestaurant takeoutRestaurant) throws SQLException {
		String deleteTakeOutRestaurant = "DELETE FROM TakeOutRestaurant WHERE RestaurantId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteTakeOutRestaurant);
			deleteStmt.setString(1, takeoutRestaurant.getRestaurantId());
			int affectedRows = deleteStmt.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException("No records available to delete for RestaurantId=" + takeoutRestaurant.getRestaurantId());
			}
			super.delete(takeoutRestaurant);

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
	
    public TakeOutRestaurant getTakeOutRestaurantById(String takeOutRestaurantId) throws SQLException {
        String selectTakeOutRestaurantById = "SELECT TakeOutRestaurant.RestaurantId AS RestaurantId,RestaurantName,"
                + "AcceptsCreditCard,WIFI,PriceRange,OpenTime,"
                + "CloseTime,NoiseLevel,Neighborhood,Star,Parking,Street,City,State,ZipCode,MaxWaitTime " +
                "FROM Restaurants INNER JOIN TakeOutRestaurant " +
                "  ON TakeOutRestaurant.RestaurantId = Restaurants.RestaurantId "
                + "WHERE TakeOutRestaurant.RestaurantId = ?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectTakeOutRestaurantById);
            selectStmt.setString(1, takeOutRestaurantId);
            results = selectStmt.executeQuery();
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
                int zip = results.getInt("ZipCode");
                int maxWaitTime = results.getInt("MaxWaitTime");
                
                TakeOutRestaurant takeOutRestaurant = new TakeOutRestaurant (resultRestaurantId, name,
                        acceptcreditcard, wifi, pricerange, open, close, noiselevel, neighborhood, star,
                        parking, street, city, state, zip, maxWaitTime);
                return takeOutRestaurant;
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

    /**
     * Get the matching DeliveryRestaurant records by fetching from your MySQL instance.
     * This runs a SELECT statement and returns a list of matching Persons.
     */
    public List<TakeOutRestaurant> getTakeOutRestaurantsByMaxWaitTime(int maxWaitTime) throws SQLException {
        List<TakeOutRestaurant> takeOutRestaurants = new ArrayList<TakeOutRestaurant>();
        String selectTakeOutRestaurants =
                        "SELECT TakeOutRestaurant.RestaurantId AS RestaurantId,"
                        + "RestaurantName,AcceptsCreditCard,WIFI,PriceRange,OpenTime,"
                + "CloseTime,NoiseLevel,Neighborhood,Star,Parking,Street,City,State,ZipCode,MaxWaitTime " +
                        "FROM Restaurants INNER JOIN TakeOutRestaurant " +
                        "  ON TakeOutRestaurant.RestaurantId = Restaurants.RestaurantId " +
                        "WHERE MaxWaitTime<=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectTakeOutRestaurants);
            selectStmt.setInt(1, maxWaitTime);
            results = selectStmt.executeQuery();
            while(results.next()) {
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
                int zip = results.getInt("ZipCode");
                int resultMaxWaitTime = results.getInt("MaxWaitTime");
                
                TakeOutRestaurant restaurant = new TakeOutRestaurant(resultRestaurantId, name, acceptcreditcard,wifi,pricerange,open,close,
                        noiselevel,neighborhood,star,parking,street,city,state,zip,resultMaxWaitTime);
                    takeOutRestaurants.add(restaurant);
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
        return takeOutRestaurants;
    }
}
