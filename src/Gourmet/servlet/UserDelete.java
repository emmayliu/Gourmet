package Gourmet.servlet;

import Gourmet.dal.*;
import Gourmet.model.*;

import java.io.IOException;
import java.sql.SQLException;
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

@WebServlet("/userdelete")
public class UserDelete extends HttpServlet {
	
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
		// Provide a title and render the JSP.
        messages.put("title", "DELETE USER");        
        req.getRequestDispatcher("/UserDelete.jsp").forward(req, resp);
	}

	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        int userId = Integer.parseInt(req.getParameter("userId"));
        if (userId <=0 || userId > Integer.MAX_VALUE) {
            messages.put("title", "Invalid UserId");
            messages.put("disableSubmit", "true");
        } else {
        	// Delete the User.
	        Users user = new Users(userId);
	        try {
	        	user = usersDao.delete(user);
	        	// Update the message.
		        if (user == null) {
		            messages.put("title", "Successfully deleted " + userId + " user");
		            messages.put("disableSubmit", "true");
		        } else {
		        	messages.put("title", "Failed to delete " + userId + " user");
		        	messages.put("disableSubmit", "false");
		        }
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        req.getRequestDispatcher("/UserDelete.jsp").forward(req, resp);
	}
}
