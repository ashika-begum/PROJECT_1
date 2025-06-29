<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>New Appointment</title>
    <style>
        body {
    background: url('http://localhost:8080/pms-2/Images/newbg.jpg') no-repeat center center fixed;
    background-size: cover;
    display: flex;
    align-items: center;
    justify-content: center;
    flex-direction: column;
    height: 100vh;
} {
            font-family: 'Roboto', Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #e3f2fd;
            color: #333;
        }

        .container {
            max-width: 700px;
            margin: 50px auto;
            padding: 30px;
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }

        h2 {
            text-align: center;
            font-size: 24px;
            color: #2196F3;
            margin-bottom: 20px;
        }

        form {
            display: flex;
            flex-direction: column;
        }

        label {
            font-weight: bold;
            margin-bottom: 8px;
            font-size: 14px;
        }

        input, button {
            padding: 12px;
            border: 1px solid #90caf9;
            border-radius: 5px;
            font-size: 14px;
            color: #555;
            margin-bottom: 20px;
            transition: border-color 0.3s;
        }

        input:focus, button:focus {
            outline: none;
            box-shadow: 0 0 5px rgba(33, 150, 243, 0.3);
        }

        button {
            background-color: #2196F3;
            color: white;
            border: none;
            cursor: pointer;
            font-size: 16px;
            transition: background-color 0.3s;
        }

        button:hover {
            background-color: #1976D2;
        }

        .back-link {
            text-align: center;
            margin-top: 20px;
        }

        .back-link a {
            text-decoration: none;
            color: #2196F3;
            font-weight: bold;
            transition: color 0.3s;
        }

        .back-link a:hover {
            color: #1976D2;
        }

        .inline {
            display: flex;
            justify-content: space-between;
        }

        .inline label, .inline input {
            width: 48%;
        }

        @media (max-width: 768px) {
            .container {
                margin: 20px;
                padding: 20px;
            }

            h2 {
                font-size: 20px;
            }

            button {
                font-size: 14px;
            }
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>New Appointment</h2>
        <form action="CreateAppointment" method="post" onsubmit="return validateDate()" novalidate>
            <div class="inline">
                <label for="patientId">Enter Patient ID</label>
                <input type="text" id="patientId" name="patientId" placeholder="Patient Id" required />
                <button type="button" onclick="fetchPatientDetails()">Get</button>
            </div>

            <div>
                <label for="name">Enter Patient Name</label>
                <input type="text" id="name" name="name" placeholder="Patient Name" required />
            </div>

            <div>
                <label for="phone">Enter Phone Number</label>
                <input type="text" id="phone" name="phone" placeholder="Phone Number" required />
            </div>

            <div>
                <label for="date">Enter Date of Appointment</label>
                <input type="date" id="date" name="date" required />
            </div>

            <button type="submit">Create Appointment</button>
        </form>
        <div class="back-link">
            <a href="Appointments">← Go Back to Appointments</a>
        </div>
    </div>

    <script>
        document.addEventListener("DOMContentLoaded", function() {
            const today = new Date();
            const year = today.getFullYear();
            const month = (today.getMonth() + 1).toString().padStart(2, '0');
            const day = today.getDate().toString().padStart(2, '0');
            document.getElementById("date").value = `${year}-${month}-${day}`;
        });

        function fetchPatientDetails() {
            const patientId = document.getElementById("patientId").value;
            if (!patientId) {
                alert("Please enter a Patient ID.");
                return;
            }

            fetch('GetPatientDetails?patientId='+patientId)
                .then(response => {
                    if (!response.ok) throw new Error("Patient not found");
                    return response.json();
                })
                .then(data => {
                    document.getElementById("name").value = data.name;
                    document.getElementById("phone").value = data.phone;
                })
                .catch(error => {
                    alert(error.message);
                    document.getElementById("name").value = "";
                    document.getElementById("phone").value = "";
                });
        }

        function validateDate() {
            const dateInput = document.getElementById("date");
            const dateValue = dateInput.value;
            if (!dateValue) {
                alert("Please select a valid date.");
                dateInput.focus();
                return false;
            }

            const selectedDate = new Date(dateValue);
            const today = new Date();
            today.setHours(0, 0, 0, 0);
            if (selectedDate < today) {
                alert("Please select a date that is today or in the future.");
                dateInput.value = "";
                dateInput.focus();
                return false;
            }

            return true;
        }
    </script>
</body>
</html>
