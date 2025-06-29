import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/CancelAppointment")
public class CancelAppointment extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String appointmentId = request.getParameter("id");
        String selectedDate = request.getParameter("date");

        try {
        	
            HttpSession session = request.getSession(false); // Don't create a new session
 	        if (session == null || session.getAttribute("username") == null) {
 	            response.sendRedirect("HomePage.jsp"); // Redirect to login page if not logged in
 	            return;
 	        }
 	        
        	//int result = divide(10, 0); // This will throw ArithmeticException
        	
            // Database connection logic
            // Assuming you have a DAO class to handle the database operations
            AppointmentDAO appointmentDAO = new AppointmentDAO();
            boolean isDeleted = appointmentDAO.deleteAppointmentById(Integer.parseInt(appointmentId), selectedDate);

            if (isDeleted) {
                // Redirect to the appointments page with a success message
                response.sendRedirect("Appointments?message=Appointment canceled successfully.&date=" + selectedDate);
            } else {
                // Redirect to the appointments page with an error message
                response.sendRedirect("Appointments?error=Failed to cancel the appointment.&date=" + selectedDate);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("Appointments?error=An error occurred.&date=" + selectedDate);
        }
    }
    
 // Method to divide two numbers
    public static int divide(int a, int b) {
        return a / b; // Will throw ArithmeticException when b is zero
    }
    
}

class AppointmentDAO {
    public boolean deleteAppointmentById(int id, String date) {
        String query = "DELETE FROM appointments WHERE id = ? AND date = ?";
        try 
        {
        	// Load MySQL JDBC driver
            Class.forName("com.mysql.jdbc.Driver");
            // Establish a connection (update with your DB details)
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/samplepms?useSSL=false", "root", "2312");
            PreparedStatement pstmt = connection.prepareStatement(query);
             
            pstmt.setInt(1, id);
            pstmt.setString(2, date);
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}