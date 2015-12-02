
package Gourmet.dal;

import Gourmet.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;


public class DeliveryRestaurantDao extends RestaurantsDao {
	protected ConnectionManager connectionManager;
	
	private static DeliveryRestaurantDao instance = null;
	protected DeliveryRestaurantDao() {
		connectionManager = new ConnectionManager();
	}
	public static DeliveryRestaurantDao getInstance() {
		if(instance == null) {
			instance = new DeliveryRestaurantDao();
		}
		return instance;
	}

	/**
	 * Save the DeliveryRestaurant instance by storing it in your MySQL instance.
	 * This runs a INSERT statement.
	 */
	public DeliveryRestaurant create(DeliveryRestaurant deliveryRestaurant) throws SQLException {
		Restaurants newRestaurant = create(new Restaurants(deliveryRestaurant.getRestaurantId(), 
				deliveryRestaurant.getName(), deliveryRestaurant.getAcceptsCreditCard(), deliveryRestaurant.getWIFI(),
				deliveryRestaurant.getPriceRange(), deliveryRestaurant.getOpen(), deliveryRestaurant.getClose(), 
				deliveryRestaurant.getNoiseLevel(), deliveryRestaurant.getNeighborhood(), deliveryRestaurant.getStar(),
				deliveryRestaurant.getParking(), deliveryRestaurant.getStreet(), deliveryRestaurant.getCity(),
				deliveryRestaurant.getState(), deliveryRestaurant.getZipCode()));
		
		String insertDeliveryRestaurant = "INSERT INTO DeliveryRestaurant(RestaurantId,MaxDeliverTime) VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertDeliveryRestaurant);		
			insertStmt.setString(1, newRestaurant.getRestaurantId());
			insertStmt.setInt(2, deliveryRestaurant.getMaxDeliverTime());
			insertStmt.executeUpdate();
			
			return deliveryRestaurant;
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
	
	// update max deliver time
	public DeliveryRestaurant updateMaxDeliverTime(DeliveryRestaurant deliveryRestaurant, int newMaxDeliverTime ) throws SQLException {
		String updateDeliveryRestaurant = "UPDATE DeliveryRestaurant SET MaxDeliverTime=? WHERE RestaurantId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateDeliveryRestaurant);
			updateStmt.setInt(1, newMaxDeliverTime);
			updateStmt.setString(2, deliveryRestaurant.getRestaurantId());
			updateStmt.executeUpdate();
			
			
			deliveryRestaurant.setMaxDeliverTime(newMaxDeliverTime);
			return deliveryRestaurant;
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
	 * Delete the DeliveryRestaurant instance.
	 * This runs a DELETE statement.
	 */
	public DeliveryRestaurant delete(DeliveryRestaurant deliveryRestaurant) throws SQLException {
		String deleteDeliveryRestaurant = "DELETE FROM DeliveryRestaurant WHERE RestaurantId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteDeliveryRestaurant);
			deleteStmt.setString(1, deliveryRestaurant.getRestaurantId());
			int affectedRows = deleteStmt.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException("No records available to delete for RestaurantId=" + deliveryRestaurant.getRestaurantId());
			}
			super.delete(deliveryRestaurant);

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

	
	public DeliveryRestaurant getDeliveryRestaurantById(String deliveryRestaurantId) throws SQLException {
		String selectDeliveryRestaurantById = "SELECT DeliveryRestaurant.RestaurantId AS RestaurantId,RestaurantName,"
				+ "AcceptsCreditCard,WIFI,PriceRange,OpenTime,"
				+ "CloseTime,NoiseLevel,Neighborhood,Star,Parking,Street,City,State,ZipCode,MaxDeliverTime " +
				"FROM Restaurants INNER JOIN DeliveryRestaurant " +
				"  ON DeliveryRestaurant.RestaurantId = Restaurants.RestaurantId "
				+ "WHERE DeliveryRestaurant.RestaurantId = ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectDeliveryRestaurantById);
			selectStmt.setString(1, deliveryRestaurantId);
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
				int maxDeliverTime = results.getInt("MaxDeliverTime");
				
				DeliveryRestaurant deliveryRestaurant = new DeliveryRestaurant (resultRestaurantId, name,
						acceptcreditcard, wifi, pricerange, open, close, noiselevel, neighborhood, star,
						parking, street, city, state, zip, maxDeliverTime);
				return deliveryRestaurant;
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
	public List<DeliveryRestaurant> getDeliveryRestaurantsByMaxDeliveryTime(int maxdelivertime) throws SQLException {
		List<DeliveryRestaurant> deliveryRestaurants = new ArrayList<DeliveryRestaurant>();
		String selectDeliveryRestaurants =
						"SELECT DeliveryRestaurant.RestaurantId AS RestaurantId,"
						+ "RestaurantName,AcceptsCreditCard,WIFI,PriceRange,OpenTime,"
				+ "CloseTime,NoiseLevel,Neighborhood,Star,Parking,Street,City,State,ZipCode,MaxDeliverTime " +
						"FROM Restaurants INNER JOIN DeliveryRestaurant " +
						"  ON DeliveryRestaurant.RestaurantId = Restaurants.RestaurantId " +
						"WHERE MaxDeliverTime<=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectDeliveryRestaurants);
			selectStmt.setInt(1, maxdelivertime);
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
				int maxDeliverTime = results.getInt("MaxDeliverTime");
				
				DeliveryRestaurant restaurant = new DeliveryRestaurant(resultRestaurantId, name, acceptcreditcard,wifi,pricerange,open,close,
						noiselevel,neighborhood,star,parking,street,city,state,zip,maxDeliverTime);
					deliveryRestaurants.add(restaurant);
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
		return deliveryRestaurants;
	}
}
