package Gourmet.dal;

import Gourmet.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class GoodForDao {
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static GoodForDao instance = null;
	protected GoodForDao() {
		connectionManager = new ConnectionManager();
	}
	public static GoodForDao getInstance() {
		if(instance == null) {
			instance = new GoodForDao();
		}
		return instance;
	}

//-Brunch : Boolean -Dinner : Boolean -Breakfast : Boolean -Lunch : Boolean -Dessert : Boolean -LateNight : Boolean -Kid : Boolean -Groups : Boolean
	
	public GoodFor create(GoodFor goodfor) throws SQLException {
		String insertGoodFor = "INSERT INTO GoodFor(RestaurantId,GoodForBrunch,GoodForDinner,GoodForBreakfast,"
				+ "GoodForLunch,GoodForDessert,GoodForLateNight,GoodForKids,GoodForGroups) "
							   + "VALUES(?,?,?,?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertGoodFor);
			
			insertStmt.setString(1, goodfor.getRestaurantId());
			insertStmt.setBoolean(2, goodfor.getBrunch());
			insertStmt.setBoolean(3, goodfor.getDinner());
			insertStmt.setBoolean(4, goodfor.getBreakfast());
			insertStmt.setBoolean(5, goodfor.getLunch());
			insertStmt.setBoolean(6, goodfor.getDessert());
			insertStmt.setBoolean(7, goodfor.getLateNight());
			insertStmt.setBoolean(8, goodfor.getKid());
			insertStmt.setBoolean(9, goodfor.getGroups());
			
			insertStmt.executeUpdate();
			
			return goodfor;
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

	
	public GoodFor delete(GoodFor goodfor) throws SQLException {
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


	
	public GoodFor getGoodForByRestaurantId(String companyName) throws SQLException {
		String selectGoodFor = "SELECT RestaurantId,GoodForBrunch,GoodForDinner,GoodForBreakfast,"
				+ "GoodForLunch,GoodForDessert, GoodForLateNight,GoodForKids,GoodForGroups "
							  + "FROM GoodFor"
							  +"WHERE RestaurantId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectGoodFor);
			selectStmt.setString(1, companyName);
			
			results = selectStmt.executeQuery();
			
			if(results.next()) {
				String resultRestaurantId = results.getString("RestaurantId");
				boolean brunch = results.getBoolean("GoodForBrunch");
				boolean dinner = results.getBoolean("GoodForDinner");
				boolean breakfast = results.getBoolean("GoodForBreakfast");
				boolean lunch = results.getBoolean("GoodForLunch");
				boolean dessert = results.getBoolean("GoodForDessert");
				boolean latenight = results.getBoolean("GoodForLateNight");
				boolean kid = results.getBoolean("GoodForKids");
				boolean groups = results.getBoolean("GoodForGroups");
				GoodFor goodfor = new GoodFor(resultRestaurantId, brunch,dinner,breakfast,lunch,dessert,
											latenight,kid,groups);
				return goodfor;
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

}
