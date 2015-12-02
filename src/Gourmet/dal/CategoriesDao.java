package Gourmet.dal;

import Gourmet.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class CategoriesDao {
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static CategoriesDao instance = null;
	protected CategoriesDao() {
		connectionManager = new ConnectionManager();
	}
	public static CategoriesDao getInstance() {
		if(instance == null) {
			instance = new CategoriesDao();
		}
		return instance;
	}


	
	public Categories create(Categories category) throws SQLException {
		String insertCategories = "INSERT INTO Categories(RestaurantId,AmericanTraditional,Greek,"
				+ "Indian,Italian,Korean,Mexican,Thai,Bars,Buffets,Seafood,SteakHouse,Sandwiches,"
													+"Pizza) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertCategories);
			
			insertStmt.setString(1, category.getRestaurantId());
			insertStmt.setBoolean(2, category.getAmericanTraditional());
			insertStmt.setBoolean(3, category.getGreek());
			insertStmt.setBoolean(4, category.getIndian());
			insertStmt.setBoolean(5, category.getItalian());
			insertStmt.setBoolean(6, category.getKorean());
			insertStmt.setBoolean(7, category.getMexican());
			insertStmt.setBoolean(8, category.getThai());
			insertStmt.setBoolean(9, category.getBars());
			insertStmt.setBoolean(10, category.getBuffets());
			insertStmt.setBoolean(11, category.getSeafood());
			insertStmt.setBoolean(12, category.getSteakHouse());
			insertStmt.setBoolean(13, category.getSandwiches());
			insertStmt.setBoolean(14, category.getPizza());
			// Note that we call executeUpdate(). This is used for a INSERT/UPDATE/DELETE
			// statements, and it returns an int for the row counts affected (or 0 if the
			// statement returns nothing). For more information, see:
			// http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
			// I'll leave it as an exercise for you to write UPDATE/DELETE methods.
			insertStmt.executeUpdate();
			
			// Note 1: if this was an UPDATE statement, then the person fields should be
			// updated before returning to the caller.
			// Note 2: there are no auto-generated keys, so no update to perform on the
			// input param person.
			return category;
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
	 * Delete the Persons instance.
	 * This runs a DELETE statement.
	 */
	public Categories delete(Categories category) throws SQLException {
		String deleteCategories = "DELETE FROM Categories WHERE RestaurantId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteCategories);
			deleteStmt.setString(1, category.getRestaurantId());
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


	/**
	 * Get the Persons record by fetching it from your MySQL instance.
	 * This runs a SELECT statement and returns a single Persons instance.
	 */
	public Categories getCategoryByRestaurantId(String restaurantId) throws SQLException {
		String selectCategories = "SELECT RestaurantId,AmericanTraditional,Greek,Indian,Italian,Korean,"
													+"Mexican,Thai,Bars,Buffets,Seafood,SteakHouse,Sandwiches,"
													+"Pizza FROM Categories  WHERE RestaurantId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCategories);
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
				boolean at = results.getBoolean("AmericanTraditional");
				boolean greek = results.getBoolean("Greek");
				boolean indian = results.getBoolean("Indian");
				boolean italian = results.getBoolean("Italian");
				boolean korean = results.getBoolean("Korean");
				boolean mexican = results.getBoolean("Mexican");
				boolean thai = results.getBoolean("Thai");
				boolean bars = results.getBoolean("Bars");
				boolean buffets = results.getBoolean("Buffets");
				boolean seafood = results.getBoolean("Seafood");
				boolean steakHouse = results.getBoolean("SteakHouse");
				boolean sandwiches = results.getBoolean("Sandwiches");
				boolean pizza = results.getBoolean("Pizza");
				Categories category = new Categories(resultRestaurantId, at,greek,indian,italian,korean,
											mexican,thai,bars,buffets,seafood,steakHouse,sandwiches,
											pizza);
				return category;
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
