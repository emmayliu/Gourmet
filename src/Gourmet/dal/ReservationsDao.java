
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

public class ReservationsDao {
	protected ConnectionManager connectionManager;

	private static ReservationsDao instance = null;
	protected ReservationsDao() {
		connectionManager = new ConnectionManager();
	}
	public static ReservationsDao getInstance() {
		if(instance == null) {
			instance = new ReservationsDao();
		}
		return instance;
	}

	public Reservations create(Reservations reservation) throws SQLException {
		String insertReservation =
			"INSERT INTO Reservations(userid, restaurantid, start_time, end_time, size ) " +
			"VALUES(?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertReservation, Statement.RETURN_GENERATED_KEYS);
			insertStmt.setInt(1, reservation.getUser().getUserId());
			insertStmt.setString(2, reservation.getRestaurant().getRestaurantId());
			insertStmt.setTimestamp(3, new Timestamp(reservation.getStart().getTime()));
			insertStmt.setTimestamp(4, new Timestamp(reservation.getEnd().getTime()));
			insertStmt.setInt(5, reservation.getSize());
			insertStmt.executeUpdate();
			
			// Retrieve the auto-generated key and set it, so it can be used by the caller.
			resultKey = insertStmt.getGeneratedKeys();
			int reservationId = -1;
			if(resultKey.next()) {
				reservationId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			reservation.setReservationId(reservationId);
			return reservation;
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
	
	public Reservations getReservationById(int reservationId) throws SQLException {
		String selectReservation =
			"SELECT reservationId, userid, restaurantid, start_time, end_time, size " +
			"FROM Reservations " +
			"WHERE reservationId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReservation);
			selectStmt.setInt(1, reservationId);
			results = selectStmt.executeQuery();
			UsersDao usersDao = UsersDao.getInstance();
			RestaurantsDao restaurantsDao = RestaurantsDao.getInstance();
			if(results.next()) {
				int resultReservationId = results.getInt("ReservationId");
				int userId = results.getInt("userid");
				String restaurantid = results.getString("restaurantid");		
			    Date start_time =  new Date(results.getTimestamp("start_time").getTime());
			    Date end_time =  new Date(results.getTimestamp("end_time").getTime());
				int size = results.getInt("Size");
				
				Users user = usersDao.getUserFromUserId(userId);
				Restaurants restaurant = restaurantsDao.getRestaurantById(restaurantid);
				Reservations reservation = new Reservations(resultReservationId, user, restaurant, start_time, end_time, size);
				return reservation;
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

	
	public List<Reservations> getReservationsByUserId(int userId) throws SQLException {
		List<Reservations> reservations = new ArrayList<Reservations>();
		String selectReservations =
				"SELECT reservationId, userid, restaurantid, start_time, end_time, size " +
		        "FROM Reservations " +
				"WHERE userid=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReservations);
			selectStmt.setInt(1,userId);
			results = selectStmt.executeQuery();
			UsersDao usersDao = UsersDao.getInstance();
			RestaurantsDao restaurantsDao = RestaurantsDao.getInstance();
			while(results.next()) {
				int resultReservationId = results.getInt("ReservationId");
				int resultuserId = results.getInt("userid");
				String restaurantid = results.getString("restaurantid");		
			    Date start_time =  new Date(results.getTimestamp("start_time").getTime());
			    Date end_time =  new Date(results.getTimestamp("end_time").getTime());
				int size = results.getInt("Size");
				
				Users user = usersDao.getUserFromUserId(resultuserId);
				Restaurants restaurant = restaurantsDao.getRestaurantById(restaurantid);
				Reservations reservation = new Reservations(resultReservationId, user, restaurant, start_time, end_time, size);
				reservations.add(reservation);
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
		return reservations;
	}
	
	public List<Reservations> getReservationsByReservationRestaurantId(String reservationRestaurantId) throws SQLException {
		List<Reservations> reservations = new ArrayList<Reservations>();
		String selectReservations =
				"SELECT reservationId, userid, restaurantid, start_time, end_time, size " +
				"FROM Reservations " +
			    "WHERE restaurantid=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReservations);
			selectStmt.setString(1,reservationRestaurantId);
			results = selectStmt.executeQuery();
			UsersDao usersDao = UsersDao.getInstance();
			RestaurantsDao restaurantsDao = RestaurantsDao.getInstance();
			while(results.next()) {
				int resultReservationId = results.getInt("ReservationId");
				int resultuserId = results.getInt("userid");
				String restaurantid = results.getString("restaurantid");		
			    Date start_time =  new Date(results.getTimestamp("start_time").getTime());
			    Date end_time =  new Date(results.getTimestamp("end_time").getTime());
				int size = results.getInt("Size");
				
				Users user = usersDao.getUserFromUserId(resultuserId);
				Restaurants restaurant = restaurantsDao.getRestaurantById(restaurantid);
				Reservations reservation = new Reservations(resultReservationId, user, restaurant, 
						start_time, end_time, size);
				reservations.add(reservation);
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
		return reservations;
	}
	
	/**
	 * Delete the Reservation instance.
	 * This runs a DELETE statement.
	 */
	public Reservations delete(Reservations reservation) throws SQLException {
		String deleteReservation = "DELETE FROM Reservations WHERE ReservationId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteReservation);
			deleteStmt.setInt(1, reservation.getReservationId());
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
