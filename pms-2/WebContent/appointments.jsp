<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, com.pms.models.Appointment" %>
<html>
<head>
    <title>Appointments</title>
    <style>
        body{
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
        }

        header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            background-color: #87CEFA;;
            color: white;
            padding: 10px 20px;
        }

        header h2 {
            margin: 0;
        }

        .logout-btn {
            background-color: #45a09e;
            color: white;
            border: none;
            padding: 8px 15px;
            border-radius: 5px;
            cursor: pointer;
            text-decoration: none;
            font-size: 14px;
        }

        .logout-btn:hover {
            background-color: #e60000;
        }

        table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
        }

        .form-container {
            text-align: center;
            margin: 20px;
        }

        .new-btn {
            display: block;
            width: fit-content;
            margin: 20px auto;
            padding: 10px 20px;
            background-color: #87CEFA;;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            text-align: center;
        }

        .new-btn:hover {
            background-color: #e60000;
        }
		        
		.success-message {
		    background-color: #87CEFA;
		    color: white;
		    padding: 15px;
		    border-radius: 5px;
		    margin: 20px auto;
		    text-align: left;
		    width: 60%;  /* Adjust width as needed */
		    display: flex;
		    justify-content: space-between;
		    align-items: center;
		    position: relative;
		}
		
		.error-message {
		    background-color: #e60000;
		    color: white;
		    padding: 15px;
		    border-radius: 5px;
		    margin: 20px auto;
		    text-align: left;
		    width: 60%;  /* Adjust width as needed */
		    display: flex;
		    justify-content: space-between;
		    align-items: center;
		    position: relative;
		}
		
		.close-btn {
		    background: none;
		    border: none;
		    color: white;
		    font-size: 16px;
		    cursor: pointer;
		    padding: 0 10px;
		}
		
		.close-btn:hover {
		    color: #e60000;
		}
		
		.success-message span,
		.error-message span {
		    flex-grow: 1;
		    text-align: center;
		}


    </style>
    <script>
        function fetchAppointments() {
            const form = document.getElementById("dateForm");
            form.submit();
        }
        
        function confirmCancel(appointmentId) {
        	//System.out.println(appointmentId);
        	console.log("Appointment ID 1: " + appointmentId); 
        	var selectedDate = document.getElementById("date").value;
            if (confirm("Are you sure you want to cancel this appointment?")) {
                // Redirect to the CancelAppointment servlet with the appointment ID
                window.location.href = "CancelAppointment?id="+appointmentId.toString() + "&date=" + selectedDate;
            }
        }
        
        function closeMessage() {
            var messageElement = document.querySelector(".success-message");
            messageElement.style.display = "none";
        }

        function closeErrorMessage() {
            var errorMessageElement = document.querySelector(".error-message");
            errorMessageElement.style.display = "none";
        }
        
        
    </script>
</head>
<body>
	<% 
	    String message = request.getParameter("message");
	    String errorMessage = request.getParameter("error");
	%>

	
	<%-- Display error message if it exists --%>
	<% if (errorMessage != null) { %>
	    <div class="error-message">
	        <span><%= errorMessage %></span>
	        <button class="close-btn" onclick="closeErrorMessage()">X</button>
	    </div>
	<% } %>
	
	<%-- Display success message if it exists --%>
	<% if (message != null) { %>
	    <div class="success-message">
	        <span><%= message %></span>
	        <button class="close-btn" onclick="closeMessage()">X</button>
	    </div>
	<% } %>
	


    <!-- Header Section -->
    <header>
        <h2>Appointments</h2>
        <a href="Logout" class="logout-btn">Logout</a>
        <!-- <a href="LogoutServlet" class="logout-btn">Logout</a>-->
        
    </header> 
    
    
    

    <!-- Date Picker -->
    <div class="form-container">
        <form id="dateForm" action="Appointments" method="get">
            <label for="date">Select Date: </label>
            <input type="date" id="date" name="date" value="<%= request.getParameter("date") != null ? request.getParameter("date") : new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()) %>" onchange="fetchAppointments()" required />
        </form>
    </div>

    <!-- Appointments Table -->
    <table>
        <thead>
            <tr>
            	<th>AppointmentId</th>
            	<th>Patient Id</th>
                <th>Name</th>
                <th>Phone</th>
                <th>Date</th>
                <th>Status</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <% 
                List<Appointment> appointments = (List<Appointment>) request.getAttribute("appointments");
                if (appointments != null && !appointments.isEmpty()) {
                    for (Appointment appointment : appointments) {
                    	String status = appointment.getStatus();
            %>
                        <tr>
                            <!-- Link to the patient details page -->
                            <td><%= appointment.getId() %></td>
                            <td><a href="PatientDetails?appointmentId=<%= appointment.getId() %>&pid=<%= appointment.getPid() %>&name=<%= appointment.getName() %>&phone=<%= appointment.getPhone() %>">
                                <%= appointment.getPid()%>
                            </a></td>
                            <td>
                                <%= appointment.getName() %>
                            </td>
                            <td><%= appointment.getPhone() %></td>
                            <td><%= appointment.getDate() %></td>
                            <td><%= status != null ? status : "Pending" %></td>
                            <td>
                                <!-- Cancel Button -->
                                <button class="cancel-btn" onclick="confirmCancel(<%= appointment.getId() %>)">X</button>
                            </td>
                        </tr>
            <% 
                    }
                } else {
            %>
            
                <tr>
                    <td colspan="3" style="text-align: center;">No appointments for the selected date!</td>
                </tr>
            <% } %>
        </tbody>
    </table>
    <a href="newAppointment.jsp" class="new-btn">New Appointment</a>
    <a href="newPatient.jsp" class="new-btn">New Patient</a>
</body>
</html>
