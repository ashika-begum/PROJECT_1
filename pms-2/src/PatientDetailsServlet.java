import java.io.Console;
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


import com.pms.models.Diagnosis;



@WebServlet("/PatientDetails")
public class PatientDetailsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String patientId = request.getParameter("pid"); // Get the patient ID from the request
        System.out.println(patientId);
        try {
        	
            HttpSession session = request.getSession(false); // Don't create a new session
            if (session == null || session.getAttribute("username") == null) {
                response.sendRedirect("HomePage.jsp"); // Redirect to login page if not logged in
                return;
            }
            
            // Load MySQL JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            // Establish a connection
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/samplepms?useSSL=false", "root", "2312");

            // Query to fetch past diagnoses
            String sql = "SELECT diagnosis, medicines, date FROM patient_diagnoses WHERE patient_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, patientId);

            // Execute the query
            ResultSet resultSet = preparedStatement.executeQuery();

            // Populate the list of past diagnoses
            List<Diagnosis> pastDiagnoses = new ArrayList<>();
            while (resultSet.next()) {
                String diagnosis = resultSet.getString("diagnosis");
                String medicines = resultSet.getString("medicines");
                String date = resultSet.getString("date");
                pastDiagnoses.add(new Diagnosis(diagnosis, medicines, date));
            }

            // Close resources
            resultSet.close();
            preparedStatement.close();
            connection.close();

            // Set the data as a request attribute
            request.setAttribute("pastDiagnoses", pastDiagnoses);

            // Forward to the JSP
            request.getRequestDispatcher("patientDetails.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while fetching patient details.");
        }
    }
}


