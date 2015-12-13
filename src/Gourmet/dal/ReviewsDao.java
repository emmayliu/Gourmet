
package Gourmet.dal;

import Gourmet.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ReviewsDao {
	protected ConnectionManager connectionManager;

	private static ReviewsDao instance = null;
	protected ReviewsDao() {
		connectionManager = new ConnectionManager();
	}
	public static ReviewsDao getInstance() {
		if(instance == null) {
			instance = new ReviewsDao();
		}
		return instance;
	}

	public Reviews create(Reviews review) throws SQLException {
		String insertReview =
			"INSERT INTO Reviews(UserId,RestaurantId,Review,Created,Rating) " +
			"VALUES(?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertReview, Statement.RETURN_GENERATED_KEYS);
			insertStmt.setInt(1, review.getUser().getUserId());
			insertStmt.setString(2, review.getRestaurant().getRestaurantId());
			insertStmt.setString(3, review.getReview());
			insertStmt.setTimestamp(4, new Timestamp(review.getCreated().getTime()));
			insertStmt.setFloat(5, review.getRating());
			insertStmt.executeUpdate();
			
			// Retrieve the auto-generated key and set it, so it can be used by the caller.
			resultKey = insertStmt.getGeneratedKeys();
			int reviewId = -1;
			if(resultKey.next()) {
				reviewId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			review.setReviewId(reviewId);
			return review;
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
	
	public Reviews getReviewById(int ReviewId) throws SQLException {
		String selectReview =
			"SELECT ReviewId,UserId,RestaurantId,Review,Created,Rating " +
			"FROM Reviews " +
			"WHERE ReviewId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReview);
			selectStmt.setInt(1, ReviewId);
			results = selectStmt.executeQuery();
			UsersDao usersDao = UsersDao.getInstance();
			RestaurantsDao restaurantsDao = RestaurantsDao.getInstance();
			if(results.next()) {
				int reviewId = results.getInt("ReviewId");
				int userId = results.getInt("UserId");
				String restaurantId = results.getString("RestaurantId");
				String reviewcontext = results.getString("Review");
				Date created =  new Date(results.getTimestamp("Created").getTime());
				float rating = results.getFloat("Rating");
				
				Users user = usersDao.getUserFromUserId(userId);
				Restaurants restaurant = restaurantsDao.getRestaurantById(restaurantId);
				Reviews review = new Reviews(reviewId, user, restaurant, reviewcontext, created, rating);
				return review;
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

	
	public List<Reviews> getReviewsByUserId(int userId) throws SQLException {
		List<Reviews> reviews = new ArrayList<Reviews>();
		String selectReviews =
				"SELECT ReviewId,UserId,RestaurantId,Review,Created,Rating "
				+ "FROM Reviews  "
				+ "WHERE UserId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReviews);
			selectStmt.setInt(1,userId);
			results = selectStmt.executeQuery();
			System.out.print("Is null? " + results.wasNull());
			UsersDao usersDao = UsersDao.getInstance();
			RestaurantsDao restaurantsDao = RestaurantsDao.getInstance();
			while(results.next()) {
				System.out.print(" running here " + '\n');
				int reviewId = results.getInt("ReviewId");
				int resultuserId = results.getInt("UserId");
				String restaurantId = results.getString("RestaurantId");
				String reviewcontext = results.getString("Review");
				Date created = null;
				try {
					created = new Date(results.getTimestamp("Created").getTime());
				} catch (SQLException e) {
					continue;
				}
				float rating = results.getFloat("Rating");

				Users user = usersDao.getUserFromUserId(userId);
				Restaurants restaurant = restaurantsDao.getRestaurantById(restaurantId);
				Reviews review = new Reviews(reviewId, user, restaurant, reviewcontext, created, rating);
				reviews.add(review);
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
		return reviews;
	}
	
	public List<Reviews> getReviewsByRestaurantId(String restaurantId) throws SQLException {
		List<Reviews> reviews = new ArrayList<Reviews>();
		String selectReviews =
				"SELECT ReviewId,UserId,RestaurantId,Review,Created,Rating "
						+ "FROM Reviews  "
						+ "WHERE RestaurantId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReviews);
			selectStmt.setString(1,restaurantId);
			results = selectStmt.executeQuery();
			UsersDao usersDao = UsersDao.getInstance();
			RestaurantsDao restaurantsDao = RestaurantsDao.getInstance();
			while(results.next()) {
				int reviewId = results.getInt("ReviewId");
				int userId = results.getInt("UserId");
				String resultrestaurantId = results.getString("RestaurantId");
				String reviewcontext = results.getString("Review");
				Date created = null;
				try{
					created =  new Date(results.getTimestamp("Created").getTime());
				} catch (SQLException e) {
					continue;
				}
				System.out.print("time");
				float rating = results.getFloat("Rating");

				Users user = usersDao.getUserFromUserId(userId);
				Restaurants restaurant = restaurantsDao.getRestaurantById(resultrestaurantId);
				Reviews review = new Reviews(reviewId, user, restaurant, reviewcontext, created, rating);
				reviews.add(review);
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
		return reviews;
	}
	
	/**
	 * Delete the Reviews instance.
	 * This runs a DELETE statement.
	 */
	public Reviews delete(Reviews review) throws SQLException {
		String deleteReview = "DELETE FROM Reviews WHERE ReviewId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteReview);
			deleteStmt.setInt(1, review.getReviewId());
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
