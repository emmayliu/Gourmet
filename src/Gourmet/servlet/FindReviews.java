package Gourmet.servlet;

import Gourmet.dal.*;
import Gourmet.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/findreviews")
public class FindReviews extends HttpServlet{
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
		//Map for storing messages.
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);
		List<Reviews> reviewList = new ArrayList<Reviews>();
		
		// Retrieve and validate name.
        String userName = req.getParameter("username");
		String restaurantName = req.getParameter("restaurantname");
	
		List<Users> userList = new ArrayList<Users>();
		List<Restaurants> restaurantList = new ArrayList<Restaurants>();
		
		try {
			userList = usersDao.getUsersFromUserName(userName);
			restaurantList = restaurantsDao.getRestaurantByName(restaurantName);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
		
		
		if(restaurantName != null && !restaurantName.trim().isEmpty()) {
			for(Restaurants rest : restaurantList){
				String restId = rest.getRestaurantId();
				try {
					reviewList = reviewsDao.getReviewsByRestaurantId(restId);
				} catch (SQLException e) {
					e.printStackTrace();
					throw new IOException(e);
				}
				

				req.setAttribute("reviews", reviewList);
			}
		}else if(userName != null && !userName.trim().isEmpty()) {
			for (Users user : userList) {
				int userId = user.getUserId();
				try {
					reviewList.addAll(reviewsDao.getReviewsByUserId(userId));
				} catch (SQLException e) {
					e.printStackTrace();
					throw new IOException(e);
				}
				req.setAttribute("reviews", reviewList);
			}
			
		} 
		req.getRequestDispatcher("/FindReviews.jsp").forward(req, resp);
		
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//Map for storing messages.
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);
		List<Reviews> reviewList = new ArrayList<Reviews>();
		
		// Retrieve and validate name.
        String userName = req.getParameter("username");
		String restaurantName = req.getParameter("restaurantname");
		String restaurantId = req.getParameter("restaurantid");
	
		List<Users> userList = new ArrayList<Users>();
		List<Restaurants> restaurantList = new ArrayList<Restaurants>();
		
		try {
			userList = usersDao.getUsersFromUserName(userName);
			restaurantList = restaurantsDao.getRestaurantByName(restaurantName);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
		
		if(userName != null && !userName.trim().isEmpty()) {
			for (Users user : userList) {
				int userId = user.getUserId();
				try {
					reviewList.addAll(reviewsDao.getReviewsByUserId(userId));
				} catch (SQLException e) {
					e.printStackTrace();
					throw new IOException(e);
				}
			}
			req.setAttribute("reviews", reviewList);
		}else if(restaurantName != null && !restaurantName.trim().isEmpty()) {
			for(Restaurants rest : restaurantList){
				String restId = rest.getRestaurantId();
				System.out.println(" rId:" + restId);
				try {
					reviewList.addAll(reviewsDao.getReviewsByRestaurantId(restId));
				} catch (SQLException e) {
					continue;
				}
			}
			req.setAttribute("reviews", reviewList);
		}else if(restaurantId != null && !restaurantId.trim().isEmpty()) {
			System.out.println(restaurantId);
			try {
				reviewList.addAll(reviewsDao.getReviewsByRestaurantId(restaurantId));
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
			req.setAttribute("reviews", reviewList);
		}  
		req.getRequestDispatcher("/FindReviews.jsp").forward(req, resp);
	}
}
