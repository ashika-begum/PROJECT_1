<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Doctor Registration</title>
    <style>
        body { font-family: Arial, sans-serif; background-color: #f4f4f4; margin: 0; padding: 0; }
        .container { max-width: 600px; margin: 50px auto; background: #fff; padding: 20px; border-radius: 8px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); }
        h2 { color: #4CAF50; }
        p { font-size: 16px; color: #333; line-height: 1.6; }
        a { display: inline-block; margin-top: 20px; padding: 10px 20px; color: #fff; background: #4CAF50; text-decoration: none; border-radius: 5px; }
        a:hover { background: #45a049; }
    </style>
</head>
<body>
    <div class="container">
        <h2>Doctor Registration Successful</h2>
        <p><strong>DoctorId:</strong> ${doctorId}</p>
        <p><strong>Name:</strong> ${name}</p>
        <!-- Uncomment and add these if needed -->
         <p><strong>Email:</strong> ${Email}</p> 
         <p><strong>Phone:</strong> ${phone}</p> 
         <p><strong>Speciality:</strong> ${speciality}</p> 
        <p>Your information has been recorded successfully!</p>
        <a href="modern.jsp">Go back</a>
        <a href="modern.jsp">Go to Home</a>
    </div>
</body>
</html>
