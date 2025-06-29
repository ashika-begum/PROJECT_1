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

@WebServlet("/CreateAppointment")
public class CreateAppointmentServlet extends HttpServlet {
   
	private static final long serialVersionUID = 1L;

	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
		String patientId = request.getParameter("patientId");
		String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String date = request.getParameter("date");
        
        HttpSession session = request.getSession(false); // Don't create a new session
        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect("HomePage.jsp"); // Redirect to login page if not logged in
            return;
        }

        // Check if the patientId exists in the patientreg table
		if (!doesPatientExist(patientId)) {
			// If patient doesn't exist, show an error message
			PrintWriter out = response.getWriter();
			out.println("<html><body>");
			out.println("<h2>Patient does not exist in the database!</h2>");
			out.println("<p>Please create a new patient.</p>");
			out.println("<a href='newPatient.jsp'>Create New Patient</a>");
			out.println("</body></html>");
			return;
		}
     			
        // Save appointment to database (simulate here)
        // Example: Database.save(new Appointment(name, phone, date));
        saveToDatabase(patientId, name, phone,date, (String)session.getAttribute("username"));

        // Redirect back to the appointments page
        response.sendRedirect("Appointments");
		}
		catch (Exception e) {
			PrintWriter out = response.getWriter();
            e.printStackTrace();
            out.println("<html><body>");
            out.println("<h2>Error Adding Appointment</h2>");
            out.println("<p>" + e.getMessage() + "</p>");
            out.println("<a href='newAppointment.jsp'>Try Again</a>");
            out.println("</body></html>");
        }
    }
	
	private void saveToDatabase(String patientId, String name, String phone, String date, String doctorId) throws Exception
    {

            // Load MySQL JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            // Establish a connection (update with your DB details)
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/samplepms?useSSL=false", "root", "2312");

            // Prepare an SQL query
            //String query = "INSERT INTO doctorreg (name) VALUES (?)";
            String query = "INSERT INTO appointments (patientId, name, phone, date, doctorId) VALUES (?, ?, ?, ?, ?)";//
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, patientId);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, phone);
            preparedStatement.setString(4, date);
            preparedStatement.setString(5, doctorId);
            


            // Execute the query
            preparedStatement.executeUpdate();

            // Close connection
            preparedStatement.close();
            connection.close();

    }
	
	// Method to check if the patient exists in the patientreg table
		private boolean doesPatientExist(String patientId) throws Exception {
			// Load MySQL JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// Establish a connection (update with your DB details)
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/samplepms?useSSL=false", "root", "2312");

			// SQL query to check if the patientId exists
			String query = "SELECT COUNT(*) FROM patientreg WHERE patientId = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, patientId);

			// Execute the query
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			int count = resultSet.getInt(1);

			// Close resources
			resultSet.close();
			preparedStatement.close();
			connection.close();

			// If count > 0, patient exists
			return count > 0;
		}
		
}
