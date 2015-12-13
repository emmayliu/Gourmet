package Gourmet.servlet;

import Gourmet.dal.*;
import Gourmet.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/restaurantdelete")
public class RestaurantDelete extends HttpServlet {
	
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
		// Provide a title and render the JSP.
        messages.put("title", "DELETE RESTAURANT");        
        req.getRequestDispatcher("/RestaurantDelete.jsp").forward(req, resp);
	}

	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) 
    throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String RestaurantId = req.getParameter("restaurantId");
        if (RestaurantId == null || RestaurantId.trim().isEmpty()) {
            messages.put("title", "Invalid Restaurant");
            messages.put("disableSubmit", "true");
        } else {
   
        	// Delete the Restaurant.
	        Restaurants restaurant = new Restaurants(RestaurantId);
	        try {
	        	restaurant = restaurantsDao.delete(restaurant);
	        	// Update the message.
		        if (restaurant == null) {
		            messages.put("title", "Successfully deleted " + restaurant.getName() + "("+RestaurantId+")");
		            messages.put("disableSubmit", "true");
		        } else {
		        	messages.put("title", "Failed to delete " + restaurant.getName() + "("+RestaurantId+")");
		        	messages.put("disableSubmit", "false");
		        }
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
		req.getRequestDispatcher("/RestaurantDelete.jsp").forward(req, resp);
		
        }
	}


