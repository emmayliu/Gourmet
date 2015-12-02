package Gourmet.servlet;

import Gourmet.dal.*;
import Gourmet.model.*;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/** 4. Point your browser to http://localhost:8080/pm4/findusers.*/

@WebServlet("/findusers")
public class FindUsers extends HttpServlet {
   protected UsersDao usersDao;
   
   @Override
	public void init() throws ServletException {
		usersDao = UsersDao.getInstance(); 
   }
public void doGet(HttpServletRequest req, HttpServletResponse resp) 
		throws ServletException, IOException {
		// Map for storing messages.
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);
		List<Users> userList = new ArrayList<Users>();
		
		// Retrieve and validate name.
		String name = req.getParameter("username");
		if(name == null || name.trim().isEmpty()){
			messages.put("success", "Please enter a valid name.");
		} else {
			// Retrieve Users, and store as a message.
			try {
				userList = usersDao.getUsersFromUserName(name);
			} catch(SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
			messages.put("success", "Displaying results for "+ name);
			// Save the previous search term, so it can be used as the default
        	// in the input box when rendering FindUsers.jsp.
			messages.put("previousName", name);
		}
		req.setAttribute("users", userList);
		req.getRequestDispatcher("/FindUsers.jsp").forward(req, resp);
	}
	
	//@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) 
		throws ServletException, IOException {
		// Map for storing messages.
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);
		List<Users> userList = new ArrayList<Users>();
		
		// Retrieve and validate name.
		String name = req.getParameter("username");
		if(name == null || name.trim().isEmpty()) {
			messages.put("success", "Please enter a valid name.");
		} else {
			// Retrieve Users, and store as a message.
			try {
				userList = usersDao.getUsersFromUserName(name);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
			

			
		}
		req.setAttribute("users",userList);
		req.getRequestDispatcher("/FindUsers.jsp").forward(req, resp);
	}
}

