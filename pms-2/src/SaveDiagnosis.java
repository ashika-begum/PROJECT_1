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

@WebServlet("/SaveDiagnosis")
public class SaveDiagnosis extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String id = request.getParameter("id");
    	String appointmentId = request.getParameter("appointmentId");
        @SuppressWarnings("unused")
		String name = request.getParameter("name");
        String diagnosis = request.getParameter("diagnosis");
        String medicines = request.getParameter("medicines");
        

        try {
        	
            HttpSession session = request.getSession(false); // Don't create a new session
            if (session == null || session.getAttribute("username") == null) {
                response.sendRedirect("HomePage.jsp"); // Redirect to login page if not logged in
                return;
            }
            
            // Load MySQL JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            // Establish a connection (update with your DB details)
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/samplepms?useSSL=false", "root", "2312");

            // SQL query to save diagnosis
            String sql = "INSERT INTO patient_diagnoses (patient_id, diagnosis,medicines,date) VALUES (?, ?,?,CURRENT_DATE)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, diagnosis);
            preparedStatement.setString(3,medicines);
            // Execute the query
            preparedStatement.executeUpdate();
            
         // SQL query to update the appointment status
            String updateStatusSQL = "UPDATE Appointments SET status = 'Completed' WHERE id = ?";
            PreparedStatement updateStatusStmt = connection.prepareStatement(updateStatusSQL);
            updateStatusStmt.setString(1, appointmentId);
            updateStatusStmt.executeUpdate();
            
            
         // Close resources
            updateStatusStmt.close();
            preparedStatement.close();
            connection.close();

            
            // Redirect back to the appointments page
            response.sendRedirect("Appointments");

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("An error occurred while saving the diagnosis.");
        }
    }
}

