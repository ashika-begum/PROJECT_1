import java.io.IOException;
import java.io.PrintWriter;
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

import java.math.BigInteger;

@WebServlet("/AddPatient")
public class AddPatient extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 
    	
    	HttpSession session = request.getSession(false); // Don't create a new session
	        if (session == null || session.getAttribute("username") == null) {
	            response.sendRedirect("HomePage.jsp"); // Redirect to login page if not logged in
	            return;
	        }
    	
    	response.setContentType("text/html");
        PrintWriter out = response.getWriter();


        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String remarks = request.getParameter("remarks");

        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            // Establish a connection (update with your DB details)
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/samplepms?useSSL=false", "root", "2312");

            // Prepare an SQL query
            String query = "INSERT INTO patientreg (name,address, phone, remarks) VALUES ( ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query ,  PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, address);
            preparedStatement.setString(3, phone);
            preparedStatement.setString(4, remarks);

            // Execute the query
            preparedStatement.executeUpdate();
            
            
            // Get the generated patient_id (auto-incremented)
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            String patientIdString = "";
            if (generatedKeys.next()) {
                BigInteger patientId = new BigInteger(generatedKeys.getString(1));
                patientIdString = patientId.toString();
      
                
            }
            
            
            // Close connection
            preparedStatement.close();
            connection.close();

            // Success message
            
         // Success message with patient_id
            out.println("<html><body>");
            out.println("<h2>New Patient Added Successfully!</h2>");
            out.println("<p>Patient ID: " + patientIdString + "</p>");
            out.println("<a href='Appointments'>Go Back to Appointments</a>");
            out.println("</body></html>");
            

            
            
            
        } catch (Exception e) {
            e.printStackTrace();
            out.println("<html><body>");
            out.println("<h2>Error Adding Patient</h2>");
            out.println("<p>" + e.getMessage() + "</p>");
            out.println("<a href='newPatient.jsp'>Try Again</a>");
            out.println("</body></html>");
        } finally {
            out.close();
        }
    }
}
