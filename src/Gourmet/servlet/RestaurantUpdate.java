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

@WebServlet("/restaurantupdate")
public class RestaurantUpdate extends HttpServlet {
	protected RestaurantsDao restaurantsDao;
	
	@Override
	public void init() throws ServletException {
		restaurantsDao = RestaurantsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve a restaurant.
        String restaurantId = req.getParameter("restaurantId");
        if (restaurantId == null) {
            messages.put("success", "Please enter a valid restaurantId.");
        } else {
        	try {
        		Restaurants restaurant = restaurantsDao.getRestaurantById(restaurantId);
        		if(restaurant == null) {
        			messages.put("success", "Restaurant does not exist. No update to perform");
        		}
        		req.setAttribute("restaurant", restaurant);
        		
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        req.getRequestDispatcher("/RestaurantUpdate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        String restaurantId = req.getParameter("restaurantId");
        if (restaurantId == null) {
            messages.put("success", "Please enter a valid restaurantId.");
        } else {
        	try {
        		Restaurants restaurant = restaurantsDao.getRestaurantById(restaurantId);
        		if(restaurant == null) {
        			messages.put("success", "Restaurant does not exist,"
        					     + "No update to perform.");
        		} else {
        			double star = Double.parseDouble(req.getParameter("star"));
        			
        			if (star > 5 || star < 0) {
        	            messages.put("success", "Please enter a valid new star rating.");
        			} else {
        				restaurant = restaurantsDao.updateStar(restaurant, star);
        				messages.put("success", "Successfully updated star to " + star);
        			}
        			
        		}
        		req.setAttribute("restaurant", restaurant);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/RestaurantUpdate.jsp").forward(req, resp);
    }
	
	
	
	
}
