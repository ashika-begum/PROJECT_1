import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pms.models.Appointment;

@WebServlet("/Appointments")
public class AppointmentsServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    try {
	    	
	        HttpSession session = request.getSession(false); // Don't create a new session
	        if (session == null || session.getAttribute("username") == null) {
	            response.sendRedirect("HomePage.jsp"); // Redirect to login page if not logged in
	            return;
	        }
	        
	        // Get the selected date from the request parameter
	        String selectedDate = request.getParameter("date");
	        
	        // Default to today's date if no date is provided
	        if (selectedDate == null || selectedDate.isEmpty()) {
	            selectedDate = new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date());
	        }
	        
	        List<Appointment> appointments = new ArrayList<>();

	        // Load MySQL JDBC driver
	        Class.forName("com.mysql.jdbc.Driver");

	        // Establish a connection (update with your DB details)
	        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/samplepms?useSSL=false", "root", "2312");

	        // SQL query to fetch appointments for the selected date
	        String sql = "SELECT id, patientId, name, phone, date, status FROM appointments WHERE date = ? and doctorId = ?";
	        PreparedStatement preparedStatement = connection.prepareStatement(sql);
	        preparedStatement.setString(1, selectedDate);
	        preparedStatement.setString(2, (String)session.getAttribute("username"));

	        // Execute the query
	        ResultSet resultSet = preparedStatement.executeQuery();

	        // Populate the list with the results
	        while (resultSet.next()) {
	        	String id = resultSet.getString("id");
	        	String patientId = resultSet.getString("patientId");
	            String name = resultSet.getString("name");
	            String phone = resultSet.getString("phone");
	            String date = resultSet.getString("date");
	            String status = resultSet.getString("status"); 
	            appointments.add(new Appointment(id, patientId, name, phone, date, status));
	        }

	        // Close resources
	        resultSet.close();
	        preparedStatement.close();
	        connection.close();

	        // Set attributes for JSP
	        request.setAttribute("appointments", appointments);
	        request.setAttribute("selectedDate", selectedDate);

	        // Forward to the JSP
	        request.getRequestDispatcher("appointments.jsp").forward(request, response);

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	
}

