package Gourmet.servlet;

import Gourmet.dal.*;
import Gourmet.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/reviewcreate")
public class ReviewCreate extends HttpServlet {
	protected ReviewsDao reviewsDao;
	protected UsersDao usersDao;
	protected RestaurantsDao restaurantsDao;
	
	@Override
	public void init() throws ServletException {
		reviewsDao = ReviewsDao.getInstance();
		usersDao = UsersDao.getInstance();
		restaurantsDao = RestaurantsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
		// Map for storing messages.
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);
		//Just render the JSP. 
		req.getRequestDispatcher("/ReviewCreate.jsp").forward(req, resp);
	}
	
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
		// Map for storing messages.
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);
		List<Reviews> reviewList = new ArrayList<Reviews>();
		List<Users> userList = new ArrayList<Users>();
		List<Restaurants> restaurantList = new ArrayList<Restaurants>();

		// Retrieve and validate name.
		String userName = req.getParameter("username");
		String restaurantName = req.getParameter("restaurantname");
		
		Users user = null;
		Restaurants restaurant = null;
		
		if( userName == null || userName.trim().isEmpty() || restaurantName == null || restaurantName.trim().isEmpty()){
			messages.put("success", "Invalid Input");
		}else{
			try {
				userList = usersDao.getUsersFromUserName(userName);
				if(!userList.isEmpty()){
					user = userList.get(0);
					int reviewCount = user.getReviewCount();
					user.setReviewCount(reviewCount++);
					int userId = user.getUserId();
					System.out.println("a");
				}else{
					messages.put("success", "Invalid User Name");
				}
				restaurantList = restaurantsDao.getRestaurantByName(restaurantName);
				if(!restaurantList.isEmpty()){
					restaurant = restaurantList.get(0);
					String restId = restaurant.getRestaurantId();
				}else{
					messages.put("success", "Invalid Restaurant Name");
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
			System.out.println("b");
			
			// Create the Review
			String stringReviewContent = req.getParameter("reviewContent");
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date createdSince = new java.util.Date();
			String rating = req.getParameter("rating");
			float ratingFloat = Float.parseFloat(rating);
			System.out.println("c");
			
			try {
				Reviews review = new Reviews(user,restaurant,stringReviewContent,new java.sql.Date(createdSince.getTime()),ratingFloat);
				review = reviewsDao.create(review);
				messages.put("success", "Successfully created review of " + review.getRestaurant().getName() + " by "
						+ review.getUser().getName());
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
		}	
		
		req.getRequestDispatcher("/ReviewCreate.jsp").forward(req, resp);
		
	}
	
}
