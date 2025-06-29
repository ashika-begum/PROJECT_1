import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pms.models.Appointment;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Database validation logic here
        boolean isValidUser = validateUser(username, password);

        if (isValidUser) {
            HttpSession session = request.getSession();
            session.setAttribute("username", username); // Store username in session
            session.setMaxInactiveInterval(3600); //for 1 min // Session valid for 1 hour (3600 seconds)
            response.sendRedirect("Appointments"); // Redirect to Appointments page
        } else {
            response.getWriter().write("<script>alert('Invalid username or password.'); window.location='modern.jsp';</script>");
        }
    }

    private boolean validateUser(String username, String password) 
    {
    	
    	try 
    	{
    		// Load MySQL JDBC driver
	        Class.forName("com.mysql.jdbc.Driver");

	        // Establish a connection (update with your DB details)
	        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/samplepms?useSSL=false", "root", "2312");

	        // SQL query to fetch appointments for the selected date
	        String sql = "SELECT doctorId, password FROM doctorreg WHERE doctorId = ?";
	        PreparedStatement preparedStatement = connection.prepareStatement(sql);
	        preparedStatement.setString(1, username);

	        // Execute the query
	        ResultSet resultSet = preparedStatement.executeQuery();

	        // Populate the list with the results
	        boolean matchingdasfsafsdfsdf = false;
	        while (resultSet.next()) {
	            String passwordInDb = resultSet.getString("password");
	            if (password.equals(passwordInDb)) {
	            	matchingdasfsafsdfsdf = true;
	            }
	            break;
	        }
	        // Close resources
	        resultSet.close();
	        preparedStatement.close();
	        connection.close();
	        
	        return matchingdasfsafsdfsdf;
	        
	        // Replace with DB validation logic
	        //return "doctor".equals(username) && "password".equals(password);
	        
    	 
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
    	
    }
}
