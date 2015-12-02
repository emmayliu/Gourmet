package Gourmet.dal;

import Gourmet.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;



public class CheckInDao {
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static CheckInDao instance = null;
	protected CheckInDao() {
		connectionManager = new ConnectionManager();
	}
	public static CheckInDao getInstance() {
		if(instance == null) {
			instance = new CheckInDao();
		}
		return instance;
	}

//-Brunch : Boolean -Dinner : Boolean -Breakfast : Boolean -Lunch : Boolean -Dessert : Boolean -LateNight : Boolean -Kid : Boolean -Groups : Boolean
	
	public CheckIn create(CheckIn checkIn) throws SQLException {
		String insertCheckIn =
				"INSERT INTO CheckIn(RestaurantId,SundaySum,MondaySum,TuesdaySum,WednesdaySum,"
				+ "ThursdaySum,FridaySum,SaturdaySum) " +
						"VALUES(?,?,?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertCheckIn, Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, checkIn.getRestaurantId());
			insertStmt.setInt(2, checkIn.getSunday());
			insertStmt.setInt(3, checkIn.getMonday());
			insertStmt.setInt(4, checkIn.getTuesday());
			insertStmt.setInt(5, checkIn.getWednesday());
			insertStmt.setInt(6, checkIn.getThursday());
			insertStmt.setInt(7, checkIn.getFriday());
			insertStmt.setInt(8, checkIn.getSaturday());
			
			insertStmt.executeUpdate();
			
			resultKey = insertStmt.getGeneratedKeys();
			int checkInId = -1;
			if(resultKey.next()) {
				checkInId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			checkIn.setCheckInId(checkInId);
			return checkIn;
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
   
	public CheckIn getCheckInByRestaurantId(String restaurantId) throws SQLException {
		String selectCheckIn =
			"SELECT CheckInId,RestaurantId,SundaySum,MondaySum,TuesdaySum,"
			+ "WednesdaySum,ThursdaySum,FridaySum,SaturdaySum" +
			"FROM CheckIn " +
			"WHERE RestaurantId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCheckIn);
			selectStmt.setString(1, restaurantId);
			results = selectStmt.executeQuery();
			
			if(results.next()) {
				int checkInId = results.getInt("CheckInId");
		        String resultRestaurantId = results.getString("RestaurantId");
		        int sundaySum = results.getInt("SundaySum");
		        int mondaySum = results.getInt("MondaySum");
		        int tuesdaySum = results.getInt("TuesdaySum");
		        int wednesdaySum = results.getInt("WednesdaySum");
		        int thursdaySum = results.getInt("ThursdaySum");
		        int fridaySum = results.getInt("FridaySum");
		        int saturdaySum = results.getInt("SaturdaySum");
		        

				
				CheckIn checkIn = new CheckIn(checkInId, resultRestaurantId, sundaySum, 
						mondaySum, tuesdaySum,wednesdaySum, thursdaySum, fridaySum, saturdaySum);
		
				return checkIn;
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
	
	public CheckIn delete(CheckIn goodfor) throws SQLException {
		String deleteGoodFor = "DELETE FROM GoodFor WHERE RestaurantId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteGoodFor);
			deleteStmt.setString(1, goodfor.getRestaurantId());
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the Persons instance.
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
