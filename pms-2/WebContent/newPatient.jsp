<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>New Patient Registration</title>
    <style>
        body {
    background: url('http://localhost:8080/pms-2/Images/newbg.jpg') no-repeat center center fixed;
    background-size: cover;
    display: flex;
    align-items: center;
    justify-content: center;
    flex-direction: column;
    height: 100vh;
}{
            font-family: 'Roboto', Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #E3F2FD; /* Light blue background */
            color: #0D47A1; /* Dark blue text */
        }

        .container {
            max-width: 700px;
            margin: 50px auto;
            padding: 30px;
            background-color: #BBDEFB; /* Blue container */
            border-radius: 10px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }

        h2 {
            text-align: center;
            font-size: 24px;
            color: #0D47A1; /* Dark blue heading */
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
            color: #0D47A1;
        }

        input, textarea {
            padding: 12px;
            border: 1px solid #64B5F6; /* Light blue border */
            border-radius: 5px;
            font-size: 14px;
            background-color: #E3F2FD;
            color: #0D47A1;
            margin-bottom: 20px;
            transition: border-color 0.3s;
        }

        input:focus, textarea:focus {
            border-color: #2196F3; /* Bright blue highlight */
            outline: none;
            box-shadow: 0 0 5px rgba(33, 150, 243, 0.5);
        }

        .gender-group {
            display: flex;
            align-items: center;
            gap: 15px;
            margin-bottom: 20px;
        }

        .gender-group input {
            margin-right: 5px;
        }

        button {
            background-color: #2196F3; /* Blue button */
            color: white;
            padding: 12px;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        button:hover {
            background-color: #1976D2; /* Darker blue on hover */
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
            color: #0D47A1; /* Darker blue hover */
        }

        /* Responsive Design */
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
        <h2>New Patient Registration</h2>
        <form action="AddPatient" method="post">

            <label for="name">Patient Name</label>
            <input type="text" id="name" name="name" placeholder="Enter patient's full name" required>

            <label for="address">Address</label>
            <textarea id="address" name="address" rows="3" placeholder="Enter patient's address" required></textarea>

            <label for="phone">Phone Number</label>
            <input type="tel" id="phone" name="phone" placeholder="Enter patient's phone number" required>

            <label for="remarks">Remarks</label>
            <textarea id="remarks" name="remarks" rows="3" placeholder="Additional notes or comments"></textarea>

            <button type="submit">Add Patient</button>
        </form>
        <div class="back-link">
            <a href="Appointments">← Go Back to Appointments</a>
        </div>
    </div>
</body>
</html>
