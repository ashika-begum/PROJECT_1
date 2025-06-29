
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Map this servlet to the URL "/RegisterDoctor"
@WebServlet("/RegisterDoctor")
public class RegisterDoctor extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Handles POST requests from the form
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Set response content type
        //response.setContentType("text/html");
    	
        // Set response content type
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String doctorId = "";
    	try
    	{
	        // Retrieve form parameters
	        doctorId = request.getParameter("name");
	        String name = request.getParameter("name");
	        String email = request.getParameter("email");
	        String phone = request.getParameter("phone");
	        String speciality = request.getParameter("speciality");
	        String password = request.getParameter("password");
	        
	        System.out.println(doctorId);
	        System.out.println(name);
	        // Optional: Save data to database (example code provided below)
	          saveDoctorDetailsToDatabase(doctorId, name, email, phone, speciality, password);
	        //saveDoctorDetailsToDatabase(name);
	        
	        // Write response to the client
	        //PrintWriter out = response.getWriter();
	        
	        /*out.println("<html><body>");
	        out.println("<h2>Doctor Registration Successful</h2>");
	        out.println("<p><strong>Name:</strong> " + name + "</p>");
	        out.println("<p><strong>Email:</strong> " + email + "</p>");
	        out.println("<p><strong>Phone:</strong> " + phone + "</p>");
	        out.println("<p><strong>Specialty:</strong> " + specialty + "</p>");
	        out.println("<p>Your information has been recorded successfully!</p>");
	        out.println("<a href='doctorRegistration.jsp'>Go back</a>");
	        out.println("</body></html>");*/
	
	        request.setAttribute("doctorId", doctorId); // Set it as a request attribute
	        request.setAttribute("name", name); // Set it as a request attribute
	        request.setAttribute("Email", email);
	        request.setAttribute("phone", phone);
	        request.setAttribute("speciality", speciality);
	        
	        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/registrationSuccess.jsp");
	        dispatcher.forward(request, response);
	
	
	
	        //out.close();
    	}
    	catch (Exception e) {
            e.printStackTrace();
            
         // If an exception occurs, display an error message to the user
            out.println("<html><body>");
			out.println("<h2>Error Adding Doctor sdfdsf Details</h2> :"+doctorId);
            
            out.println("<p>There was an error while processing your request fssfsdsfsf:</p>");
            out.println("<pre>" + e.getMessage() + "</pre>"); // Display exception message
            out.println("<pre>");
            e.printStackTrace(out); // Display stack trace
            out.println("</pre>");
            out.println("<a href='doctorRegistration.jsp'>Go Back</a>");
            out.println("</body></html>");
            
           // out.close();
            
        }
    	finally {
    		out.close();
    	}
    	
    }
    
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Set response content type
        response.setContentType("text/html");

        // Write response to the client
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h2>  GET Request Not Implemented</h2>");
        out.println("<p>It is not implemented yet.</p>");
        out.println("</body></html>");
        out.close();
    }
    
    
    
    // Optional: Save doctor details to the database (Uncomment if database is used)
    
    private void saveDoctorDetailsToDatabase
    (String doctorId, String name, String email, String phone, String specialty, String password)
    		throws Exception 
    {

            // Load MySQL JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            // Establish a connection (update with your DB details)
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/samplepms?useSSL=false", "root", "2312");

            // Prepare an SQL query
            //String query = "INSERT INTO doctorreg (name) VALUES (?)";
            String query = "INSERT INTO doctorreg (doctorId, name, email, mobile, speciality, password) VALUES (?, ?, ?, ?, ?, ?)";//
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, doctorId);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, phone);
            preparedStatement.setString(5, specialty);
            preparedStatement.setString(6, password);

            // Execute the query
            preparedStatement.executeUpdate();

            // Close connection
            preparedStatement.close();
            connection.close();

    }
    
}
