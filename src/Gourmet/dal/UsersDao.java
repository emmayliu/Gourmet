/******************************************************************************
*  CS5200 Milestone 4 Hello World
*  Bee: Bolun Hu, Enting Wu, Yujiao Liu
******************************************************************************/

package Gourmet.dal;

import Gourmet.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UsersDao {
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static UsersDao instance = null;
	protected UsersDao() {
		connectionManager = new ConnectionManager();
	}
	public static UsersDao getInstance() {
		if(instance == null) {
			instance = new UsersDao();
		}
		return instance;
	}

	/**
	 * Save the User instance by storing it in your MySQL instance.
	 * This runs a INSERT statement.
	 */
	public Users create(Users user) throws SQLException {
		String insertUser = "INSERT INTO Users(Name,Password,CreatedSince,ReviewCount,Gender) VALUES(?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertUser,Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, user.getName());
			insertStmt.setString(2, user.getPassword());
			insertStmt.setTimestamp(3,new Timestamp(user.getCreatedSince().getTime()));
			insertStmt.setInt(4, user.getReviewCount());
			insertStmt.setBoolean(5, user.getGender());
			insertStmt.executeUpdate();
			
			resultKey=insertStmt.getGeneratedKeys();
			int userId = -1;
			if(resultKey.next()) {
				userId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			user.setUserId(userId);
			
			return user;
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

	/** 2. Select Statement */	
	// userId
	public Users getUserFromUserId(int userId) throws SQLException {
		String selectUser = 
				"SELECT UserId,Name,Password,CreatedSince,ReviewCount,Gender FROM Users WHERE UserId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectUser);
			selectStmt.setInt(1, userId);
			results = selectStmt.executeQuery();
			if(results.next()) {
				int resultUserId = results.getInt("UserId");
				String name = results.getString("Name");
				String password = results.getString("Password");
				Date createdSince = new Date(results.getTimestamp("CreatedSince").getTime());
				int reviewCount = results.getInt("ReviewCount");
				boolean gender = results.getBoolean("Gender");
				Users user = new Users(resultUserId,name,password,createdSince,reviewCount,gender);
				return user;
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
			if(results != null){
				results.close();
			}
		}
		return null;		
	}
	
	// name
	public List<Users> getUsersFromUserName(String name) throws SQLException {
		List<Users> userList = new ArrayList<Users>();
		String selectUsers = 
		    "SELECT UserId, Name, Password, CreatedSince, ReviewCount, Gender FROM Users WHERE Name=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectUsers);
			selectStmt.setString(1, name);
			results = selectStmt.executeQuery();
			while(results.next()){
				int userId = results.getInt("UserId");
				String resultName = results.getString("Name");
				String password = results.getString("Password");
				Date createdSince = new Date(results.getTimestamp("CreatedSince").getTime());
				int reviewCount = results.getInt("ReviewCount");
				boolean gender = results.getBoolean("Gender");
				Users user = new Users(userId,resultName,password,createdSince,reviewCount,gender);
				userList.add(user);
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
			if(results != null){
				results.close();
			}
		}
		return userList;
	}
	
	// gender
	public List<Users> getUsersFromGender(boolean gender) throws SQLException {
		List<Users> userList = new ArrayList<Users>();
		String selectUsers = 
		    "SELECT UserId, Name, Password, CreatedSince, ReviewCount, Gender FROM Users WHERE Gender=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectUsers);
			selectStmt.setBoolean(1, gender);
			results = selectStmt.executeQuery();
			while(results.next()){
				int userId = results.getInt("UserId");
				String name = results.getString("Name");
				String password = results.getString("Password");
				Date createdSince = new Date(results.getTimestamp("CreatedSince").getTime());
				int reviewCount = results.getInt("ReviewCount");
				boolean resultGender = results.getBoolean("Gender");
				Users user = new Users(userId,name,password,createdSince,reviewCount,resultGender);
				userList.add(user);
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
			if(results != null){
				results.close();
			}
		}
		return userList;
	}
	
/** 3. Update Statement */
	
	//password
	public Users updateNameAndPassword(Users user, String newName, String newPassword) throws SQLException {
		String updateUser = "UPDATE Users SET Name=? , Password=?  WHERE UserId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateUser);
			updateStmt.setString(1, newName);
			updateStmt.setString(2, user.getPassword());
			updateStmt.setInt(3, user.getUserId());
			updateStmt.executeUpdate();
			
			user.setName(newName);
			user.setPassword(newPassword);
			return user;
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
	// name
	public Users updateName(Users user, String newName) throws SQLException {
		String updateUser = "UPDATE Users SET Name=?  WHERE UserId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateUser);
			updateStmt.setString(1, newName);
			updateStmt.setInt(2, user.getUserId());
			updateStmt.executeUpdate();
			
			user.setName(newName);
			return user;
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
	// password
	public Users updatePassword(Users user, String newPassword) throws SQLException {
		String updateUser = "UPDATE Users SET Password=?  WHERE UserId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateUser);
			updateStmt.setString(1, newPassword);
			updateStmt.setInt(2, user.getUserId());
			updateStmt.executeUpdate();
			
			user.setPassword(newPassword);
			return user;
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
	
/** 4. Delete Statement */
	public Users delete(Users user) throws SQLException {
		String deleteUser = "DELETE FROM Users WHERE UserId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteUser);
			deleteStmt.setInt(1,user.getUserId());
			deleteStmt.executeUpdate();
			
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null){
				connection.close();
			}
			if(deleteStmt != null){
				deleteStmt.close();
			}
		}
		
	}
	
}
	


