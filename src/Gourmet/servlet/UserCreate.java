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


@WebServlet("/usercreate")
public class UserCreate extends HttpServlet {
	protected UsersDao usersDao;
	
	@Override
	public void init() throws ServletException {
		usersDao = UsersDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
		// Map for storing messages.
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);
		//Just render the JSP. 
		req.getRequestDispatcher("/UserCreate.jsp").forward(req, resp);
	}
	
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
		// Map for storing messages.
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);
		
		// Retrieve and validate name.
		String name = req.getParameter("username");
		if(name == null || name.trim().isEmpty()){
			messages.put("success", "Invalid UserName");
		} else {
			// Create the User.
			String password = req.getParameter("password");
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String stringCreatedSince = req.getParameter("createdSince");
			java.util.Date createdSince = new java.util.Date();
			/*java.sql.Date sqlCreatedSince = new java.sql.Date(createdSince.getTime());
			try {
				createdSince = dateFormat.parse(stringCreatedSince);
				sqlCreatedSince = new java.sql.Date(createdSince.getTime());
			} catch (ParseException e) {
				e.printStackTrace();
				throw new IOException(e);
			}*/
			String stringReviewCount = req.getParameter("reviewCount");
			int reviewCount = Integer.parseInt(stringReviewCount);
			
			String stringGender = req.getParameter("gender");
			boolean gender = Boolean.parseBoolean(stringGender);
			
			try {
				Users user = new Users(name,password,new java.sql.Date(createdSince.getTime()),reviewCount,gender);
				user = usersDao.create(user);
				messages.put("success", "Successfully created " + name);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
		}
		req.getRequestDispatcher("/UserCreate.jsp").forward(req, resp);
		
	}
	
}
