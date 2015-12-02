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

@WebServlet("/userupdate")
public class UserUpdate extends HttpServlet {
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

        // Retrieve user and validate.
        String userId = req.getParameter("userId");
        if (userId == null || userId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid userId.");
        } else {
        	try {
        		int id = (int)Integer.valueOf(userId);
        		Users user = usersDao.getUserFromUserId(id);
        		if(user == null) {
        			messages.put("success", "UserId does not exist. No update to perform");
        		}
        		req.setAttribute("user", user);
        		
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        req.getRequestDispatcher("/UserUpdate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        String userId = req.getParameter("userId");
        if (userId == null || userId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid userId.");
        } else {
        	try {
        		int id = Integer.valueOf(userId);
        		Users user = usersDao.getUserFromUserId(id);
        		String newUserName = req.getParameter("username");
        		String password = req.getParameter("oldPassword");
        		if(user == null || !user.getPassword().equals(password)) {
        			messages.put("success", "UserId does not exist, or incorrect User/Password combination. "
        					+ "No update to perform.");
        		} else {
        			
        			String newPassword = req.getParameter("newPassword");
        			if (newPassword == null || newPassword.trim().isEmpty()) {
        	            messages.put("success", "Please enter a valid new password.");
        			} else {
        				user = usersDao.updateNameAndPassword(user, newUserName, newPassword);
        				messages.put("success", "Successfully updated " + newUserName);
        			}
        			
        		}
        		req.setAttribute("user", user);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/UserUpdate.jsp").forward(req, resp);
    }
	
	
	
	
}
