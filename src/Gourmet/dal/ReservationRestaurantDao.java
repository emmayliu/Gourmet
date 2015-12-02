
package Gourmet.dal;

import Gourmet.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;


public class ReservationRestaurantDao extends RestaurantsDao {
	protected ConnectionManager connectionManager;
	
	private static ReservationRestaurantDao instance = null;
	protected ReservationRestaurantDao() {
		connectionManager = new ConnectionManager();
	}
	public static ReservationRestaurantDao getInstance() {
		if(instance == null) {
			instance = new ReservationRestaurantDao();
		}
		return instance;
	}

	/**
	 * Save the DeliveryRestaurant instance by storing it in your MySQL instance.
	 * This runs a INSERT statement.
	 */
	public ReservationRestaurant create(ReservationRestaurant reservationRestaurant) throws SQLException {
		Restaurants newRestaurant = create(new Restaurants(reservationRestaurant.getRestaurantId(), 
				reservationRestaurant.getName(), reservationRestaurant.getAcceptsCreditCard(), reservationRestaurant.getWIFI(),
				reservationRestaurant.getPriceRange(), reservationRestaurant.getOpen(), reservationRestaurant.getClose(), 
				reservationRestaurant.getNoiseLevel(), reservationRestaurant.getNeighborhood(), reservationRestaurant.getStar(),
				reservationRestaurant.getParking(), reservationRestaurant.getStreet(), reservationRestaurant.getCity(),
				reservationRestaurant.getState(), reservationRestaurant.getZipCode()));
		
		String insertReservationRestaurant = "INSERT INTO ReservationRestaurant(RestaurantId,MaxRoomSize) VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertReservationRestaurant);		
			insertStmt.setString(1, newRestaurant.getRestaurantId());
			insertStmt.setInt(2, reservationRestaurant.getMaxRoomSize());
			insertStmt.executeUpdate();
			
			return reservationRestaurant;
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
	
	// update capacity 
	public ReservationRestaurant updateRoomSize(ReservationRestaurant reservationRestaurant, int newRoomSize ) throws SQLException {
		String updateReservationRestaurant = "UPDATE ReservationRestaurant SET MaxRoomSize=? WHERE RestaurantId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateReservationRestaurant);
			updateStmt.setInt(1, newRoomSize);
			updateStmt.setString(2, reservationRestaurant.getRestaurantId());
			updateStmt.executeUpdate();
			
			
			reservationRestaurant.setMaxRoomSize(newRoomSize);
			return reservationRestaurant;
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
	 * Delete the Restaurant instance.
	 * This runs a DELETE statement.
	 */
	public ReservationRestaurant delete(ReservationRestaurant reservationRestaurant) throws SQLException {
		String deleteReservationRestaurant = "DELETE FROM ReservationRestaurant WHERE RestaurantId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteReservationRestaurant);
			deleteStmt.setString(1, reservationRestaurant.getRestaurantId());
			int affectedRows = deleteStmt.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException("No records available to delete for RestaurantId=" + reservationRestaurant.getRestaurantId());
			}
			super.delete(reservationRestaurant);

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
	
    public ReservationRestaurant getReservationRestaurantById(String reservationRestaurantId) throws SQLException {
        String selectReservationRestaurantById = "SELECT ReservationRestaurant.RestaurantId AS RestaurantId,RestaurantName,"
                + "AcceptsCreditCard,WIFI,PriceRange,OpenTime,"
                + "CloseTime,NoiseLevel,Neighborhood,Star,Parking,Street,City,State,ZipCode,MaxRoomSize " +
                "FROM Restaurants INNER JOIN ReservationRestaurant " +
                "  ON ReservationRestaurant.RestaurantId = Restaurants.RestaurantId "
                + "WHERE ReservationRestaurant.RestaurantId = ?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectReservationRestaurantById);
            selectStmt.setString(1, reservationRestaurantId);
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
                int maxRoomSize = results.getInt("MaxRoomSize");
                
                ReservationRestaurant reservationRestaurant = new ReservationRestaurant (resultRestaurantId, name,
                        acceptcreditcard, wifi, pricerange, open, close, noiselevel, neighborhood, star,
                        parking, street, city, state, zip, maxRoomSize);
                return reservationRestaurant;
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
    public List<ReservationRestaurant> getReservationRestaurantsByMaxRoomSize(int maxRoomSize) throws SQLException {
        List<ReservationRestaurant> reservationRestaurants = new ArrayList<ReservationRestaurant>();
        String selectReservationRestaurants =
                        "SELECT ReservationRestaurant.RestaurantId AS RestaurantId,"
                        + "RestaurantName,AcceptsCreditCard,WIFI,PriceRange,OpenTime,"
                + "CloseTime,NoiseLevel,Neighborhood,Star,Parking,Street,City,State,ZipCode,MaxRoomSize " +
                        "FROM Restaurants INNER JOIN ReservationRestaurant " +
                        "  ON ReservationRestaurant.RestaurantId = Restaurants.RestaurantId " +
                        "WHERE MaxRoomSize>=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectReservationRestaurants);
            selectStmt.setInt(1, maxRoomSize);
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
                int resultMaxRoomSize = results.getInt("MaxRoomSize");
                
                ReservationRestaurant restaurant = new ReservationRestaurant(resultRestaurantId, name, acceptcreditcard,wifi,pricerange,open,close,
                        noiselevel,neighborhood,star,parking,street,city,state,zip,resultMaxRoomSize);
                    reservationRestaurants.add(restaurant);
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
        return reservationRestaurants;
    }
}
