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
//import org.json.JSONObject;

@WebServlet("/GetPatientDetails")
public class GetPatientDetails extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String patientId = request.getParameter("patientId");
        response.setContentType("application/json");
        
        System.out.println("patientId : " + patientId);
        try (PrintWriter out = response.getWriter()) {
            // Connect to the database
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/samplepms?useSSL=false", "root", "2312");


            // Query to get patient details
            String query = "SELECT name, phone FROM patientreg WHERE patientId = ?";
            System.out.println("query : " + query);
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, patientId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // Create JSON response
                //JSONObject json = new JSONObject();
                //json.put("name", resultSet.getString("name"));
                //json.put("phone", resultSet.getString("phone"));
                //out.print(json.toString());
            	
            	// Set the response status code (Optional)
                response.setStatus(HttpServletResponse.SC_OK);
                
             // Write the JSON string to the response
                //PrintWriter out = response.getWriter();
             // Constructing a JSON response string manually
                String jsonResponse = "{"
                        + "\"name\": \"" + resultSet.getString("name") + "\","
                        + "\"phone\": \"" + resultSet.getString("phone") + "\""
                        + "}";
                out.print(jsonResponse);
                out.flush();
                
            } else {
                // Handle patient not found
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                out.print("{\"error\": \"Patient not found\"}");
            }

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().print("{\"error\": \"An error occurred\"}");
        }
    }
}