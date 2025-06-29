<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, com.pms.models.Diagnosis" %>

<html>
<head>
    <title>Patient Details</title>
    <style>
        body {
    background: url('http://localhost:8080/pms-2/Images/newbg.jpg') no-repeat center center fixed;
    background-size: cover;
    display: flex;
    align-items: center;
    justify-content: center;
    flex-direction: column;
    height: 100vh;
}
        {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-image: url('C:\Users\jahir\OneDrive\Pictures\securitybg.jpg.jpg'); /* Replace with your image URL */
            background-size: cover;
            background-position: center;
            background-attachment: fixed;
            color: #333;
        }
       .container {
    background: rgba(255, 255, 255, 0.9); /* Slightly transparent background */
    padding: 20px;
    border-radius: 10px;
    margin: 40px auto;
    width: 80%;
    max-width: 800px;
    box-shadow: 0 4px 10px rgba(0, 0, 0, 0.3);
}

h2, h3 {
    text-align: center;
    color: #007BFF; /* Blue theme */
}

table {
    width: 100%;
    border-collapse: collapse;
    margin: 20px 0;
}

th, td {
    border: 1px solid #ddd;
    padding: 8px;
    text-align: left;
}

th {
    background-color: #007BFF; /* Blue theme */
    color: white;
}

tr:nth-child(even) {
    background-color: #e3f2fd; /* Light blue alternate rows */
}

tr:hover {
    background-color: #d0ebff;
}

.no-data {
    text-align: center;
    font-style: italic;
    color: gray;
}

form {
    margin-top: 20px;
}

textarea, button {
    width: 100%;
    padding: 10px;
    margin: 10px 0;
    border: 1px solid #ddd;
    border-radius: 5px;
}

button {
    background-color: #007BFF; /* Blue theme */
    color: white;
    border: none;
    cursor: pointer;
    font-size: 16px;
}

button:hover {
    background-color: #0056b3;
}

    </style>
</head>
<body>
    <div class="container">
        <h2>Patient Details</h2>
        <div class="details">
            <p><strong>AppointmentID:</strong> <%= request.getParameter("appointmentId") %></p>
            <p><strong>P. ID:</strong> <%= request.getParameter("pid") %></p>
            <p><strong>Name:</strong> <%= request.getParameter("name") %></p>
            <p><strong>Phone:</strong> <%= request.getParameter("phone") %></p>
        </div>

        <!-- Past Diagnoses Section -->
        <h3>Past Diagnoses</h3>
        <table>
            <thead>
                <tr>
                    <th>Diagnosis</th>
                    <th>Medicines</th>
                    <th>Date</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    List<Diagnosis> pastDiagnoses = (List<Diagnosis>) request.getAttribute("pastDiagnoses");
                    if (pastDiagnoses != null && !pastDiagnoses.isEmpty()) {
                        for (Diagnosis diagnosis : pastDiagnoses) {
                %>
                            <tr>
                                <td><%= diagnosis.getDiagnosis() %></td>
                                <td><%= diagnosis.getMedicines() %></td>
                                <td><%= diagnosis.getDate() %></td>
                            </tr>
                <% 
                        }
                    } else {
                %>
                    <tr>
                        <td colspan="3" class="no-data">No past diagnoses found for this patient.</td>
                    </tr>
                <% } %>
            </tbody>
        </table>

        <h3>Add Diagnosis</h3>
        <form action="SaveDiagnosis" method="post">
            <input type="hidden" name="appointmentId" value="<%= request.getParameter("appointmentId") %>" />
            <input type="hidden" name="id" value="<%= request.getParameter("pid") %>" />
            <input type="hidden" name="name" value="<%= request.getParameter("name") %>" />
            <strong>Observation:</strong>
            <textarea name="diagnosis" placeholder="Enter diagnosis details here..." required></textarea>
            <strong>Medicines:</strong>
            <textarea name="medicines" placeholder="Enter medicines prescribed..." required></textarea>
            <button type="submit">Save Diagnosis</button>
        </form>
    </div>
</body>
</html>
