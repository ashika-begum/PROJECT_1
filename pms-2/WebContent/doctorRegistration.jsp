<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Doctor Registration</title>
    <style>
        /* Reset styles */
        body, html {
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif;
        }

        /* Full-page background video */
        #bg-video {
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            object-fit: cover;
            z-index: -1;
        }

        /* Overlay for dark effect */
        .overlay {
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background: rgba(0, 0, 0, 0.5); /* Slightly transparent black */
            z-index: 0;
        }

        /* Form container */
        .form-container {
            position: relative;
            z-index: 1;
            background: rgba(255, 255, 255, 0.9);
            max-width: 400px;
            margin: 5% auto;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            animation: fadeIn 1.5s ease-in-out;
        }

        /* Heading */
        h2 {
            text-align: center;
            color: #333;
            margin-bottom: 20px;
        }

        /* Form styling */
        label {
            display: block;
            font-weight: bold;
            color: #555;
            margin: 10px 0 5px;
        }

        input[type="text"],
        input[type="email"],
        input[type="password"],
        input[type="submit"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
            box-sizing: border-box;
            font-size: 16px;
        }

        input[type="submit"] {
            background: linear-gradient(to right, #007bff, #00c6ff);
            border: none;
            color: blue;
            font-weight: bold;
            cursor: pointer;
            transition: transform 0.2s ease-in-out;
        }

        input[type="submit"]:hover {
            transform: scale(1.05);
        }

        /* Responsive design */
        @media (max-width: 600px) {
            .form-container {
                margin: 10% auto;
                width: 90%;
            }
        }

        /* Animation for fade-in */
        @keyframes fadeIn {
            from {
                opacity: 0;
                transform: translateY(20px);
            }
            to {
                opacity: 1;
                transform: translateY(0);
            }
        }
    </style>
</head>
<body>

   

    <!-- Overlay for background effect -->
    <div class="overlay"></div>

    <!-- Form container -->
    <div class="form-container">
        <h2>Doctor Registration</h2>
        <form action="RegisterDoctor" method="post">
        
            <label for="name">DoctorId:</label>
            <input type="text" id="doctorId" name="doctorId" required>
            
            <label for="name">Name:</label>
            <input type="text" id="name" name="name" required>

            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required>

            <label for="phone">Phone:</label>
            <input type="text" id="phone" name="phone">

            <label for="specialty">Specialty:</label>
            <input type="text" id="specialty" name="specialty">

            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>

            <input type="submit" value="Register">
        </form>
    </div>

</body>
</html>
