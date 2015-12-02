package Gourmet.servlet;

import Gourmet.dal.*;
import Gourmet.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;
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

@WebServlet("/restaurantcreate")
public class RestaurantCreate extends HttpServlet{
	protected RestaurantsDao restaurantsDao;
	
	@Override
	public void init() throws ServletException {
		restaurantsDao = RestaurantsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
		// Map for storing messages.
		Map<String,String> messages = new HashMap<String,String>();
		req.setAttribute("messages", messages);
		// Just render the JSP.
		req.getRequestDispatcher("/RestaurantCreate.jsp").forward(req, resp);
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
		// Map for storing messages.
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);
		
		// Retrieve and validate name.
		String name = req.getParameter("restaurantname");
		if(name == null || name.trim().isEmpty()){
			messages.put("success", "Invalid RestaurantName");
		} else {
			// Create the Restaurant.
			String restaurantId = req.getParameter("restaurantId");
			boolean acceptCreditCard = Boolean.parseBoolean(req.getParameter("acceptCreditCard"));
			boolean wifi = Boolean.parseBoolean(req.getParameter("wifi"));
			int priceRange = Integer.parseInt(req.getParameter("priceRange"));
			//time
			Time openTime = Time.valueOf(req.getParameter("openTime"));
			Time closeTime = Time.valueOf(req.getParameter("closeTime"));
			
			int noiseLevel = Integer.parseInt(req.getParameter("noiseLevel"));
			String neighborhood = req.getParameter("neighborhood");
			double star = Double.parseDouble(req.getParameter("star"));
			int parking = Integer.parseInt(req.getParameter("parking"));
			String street = req.getParameter("street");
			String city = req.getParameter("city");
			String state = req.getParameter("state");
			int zip = Integer.parseInt(req.getParameter("zip"));
			
			
			try {
				Restaurants restaurant = new Restaurants(restaurantId, name, acceptCreditCard,wifi,priceRange,openTime,closeTime,
						noiseLevel,neighborhood,star,parking,street,city,state,zip);
				restaurant = restaurantsDao.create(restaurant);
				messages.put("success", "Successfully created " + name);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
		}
		req.getRequestDispatcher("/RestaurantCreate.jsp").forward(req, resp);
		
	}
	
	
	
	
	
}
