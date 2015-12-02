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

@WebServlet("/findrestaurants")
public class FindRestaurants extends HttpServlet{
	protected RestaurantsDao restaurantsDao;
	
	@Override
	public void init() throws ServletException {
		restaurantsDao = RestaurantsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		// Map for storing messages
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);
		List<Restaurants> restaurantList = new ArrayList<Restaurants>();
		
		/* 1. Retrieve and validate name */
		/*String rid = req.getParameter("restaurantId");
		String name = req.getParameter("restaurantName");
		String zipcode = req.getParameter("zip");
		String priceRange = req.getParameter("priceRange");
		
		if(rid != null && !rid.trim().isEmpty()) {
			// Retrieve Restaurants, and store as a message.
			try {
				restaurantList.add(restaurantsDao.getRestaurantById(rid));
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
			messages.put("success", "Displaying results for restaurant (restaurantId = "+ rid +")");
			// Save the previous search term, so it can be used as the default
        	// in the input box when rendering FindRestaurants.jsp.
			messages.put("previous", rid);
			req.setAttribute("restaurants", restaurantList);
			req.getRequestDispatcher("/FindRestaurants.jsp").forward(req, resp);
			return;
			
		}else if(name != null && !name.trim().isEmpty()) {
			// Retrieve Restaurants, and store as a message.
			try {
				restaurantList = restaurantsDao.getRestaurantByName(name);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
			messages.put("success", "Displaying results for "+ name + "restaurant");
			messages.put("previous", name);
			req.setAttribute("restaurants", restaurantList);
			req.getRequestDispatcher("/FindRestaurants.jsp").forward(req, resp);
			return;
			
		} else if(zipcode != null && !zipcode.trim().isEmpty()){
			int zip = Integer.parseInt(zipcode);
			// Retrieve Restaurants, and store as a message.
			try {
				restaurantList = restaurantsDao.getRestaurantsByZip(zip);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
			messages.put("success", "Displaying results for restaurant (zipcode = "+ zipcode +")");
			messages.put("previous", zipcode);
		
			req.setAttribute("restaurants", restaurantList);
			req.getRequestDispatcher("/FindRestaurants.jsp").forward(req, resp);
			return;
			
		} else if(priceRange != null && !priceRange.trim().isEmpty()){
			int price = Integer.parseInt(priceRange);
			try {
				restaurantList = restaurantsDao.getRestaurantsByPriceRange(price);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
			messages.put("success", "Displaying results for restaurant (priceRange = "+ priceRange +")");
			messages.put("previous", priceRange);
			
			req.setAttribute("restaurants", restaurantList);
			req.getRequestDispatcher("/FindRestaurants.jsp").forward(req, resp);
			
		}*/
		req.setAttribute("restaurants", restaurantList);
		req.getRequestDispatcher("/FindRestaurants.jsp").forward(req, resp);
		
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//Map for storing messages.
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);
		List<Restaurants> restaurantList = new ArrayList<Restaurants>();
		
		// Retrieve and validate name.
		String rid = req.getParameter("restaurantId");
		String name = req.getParameter("restaurantName");
		String zipcode = req.getParameter("zip");
		String priceRange = req.getParameter("priceRange");
		
		if(rid != null && !rid.trim().isEmpty()) {
			// Retrieve Restaurants, and store as a message.
			try {
				restaurantList.add(restaurantsDao.getRestaurantById(rid));
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
			
			req.setAttribute("restaurants", restaurantList);
			//req.getRequestDispatcher("/FindRestaurants.jsp").forward(req, resp);
			//return;
			
		}else if(name != null && !name.trim().isEmpty()) {
			// Retrieve Restaurants, and store as a message.
			try {
				System.out.print("Name is" + name);
				restaurantList = restaurantsDao.getRestaurantByName(name);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}

			req.setAttribute("restaurants", restaurantList);
			//req.getRequestDispatcher("/FindRestaurants.jsp").forward(req, resp);
			//return;
			
		} else if(zipcode != null && !zipcode.trim().isEmpty()){
			int zip = Integer.parseInt(zipcode);
			// Retrieve Restaurants, and store as a message.
			try {
				restaurantList = restaurantsDao.getRestaurantsByZip(zip);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
			req.setAttribute("restaurants", restaurantList);
			
		} else if(priceRange != null && !priceRange.trim().isEmpty()){
			int price = Integer.parseInt(priceRange);
			try {
				restaurantList = restaurantsDao.getRestaurantsByPriceRange(price);
				System.out.println(restaurantList);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}

			req.setAttribute("restaurants", restaurantList);
			//req.getRequestDispatcher("/FindRestaurants.jsp").forward(req, resp);
			
		}
		req.getRequestDispatcher("/FindRestaurants.jsp").forward(req, resp);
	}
}
