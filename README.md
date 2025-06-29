# 🏥 Patient Management System

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![JSP](https://img.shields.io/badge/JSP-blue?style=for-the-badge)
![MySQL](https://img.shields.io/badge/MySQL-00000F?style=for-the-badge&logo=mysql&logoColor=white)
![Apache Tomcat](https://img.shields.io/badge/Tomcat-233143?style=for-the-badge&logo=apachetomcat&logoColor=yellow)

---

This is a web-based **Patient Management System** built using **JSP**, **Servlets**, and **MySQL**. The system allows doctors to register, log in, and manage patient information efficiently through a simple web interface.

---

## 🚀 Features
- 👨‍⚕️ Doctor registration and login
- 📝 Add new patients
- 📋 View patient details
- ✅ Registration success page
- 📂 MVC folder structure using JSP and Servlets

---

## 🛠️ Technologies Used
- Java (JSP & Servlets)
- MySQL Database
- Apache Tomcat Server
- HTML & CSS (for frontend)
- JDBC (for database connectivity)

---

## 🧑‍💻 How to Run This Project

1. **Clone or Download** this repository.
2. **Import in Eclipse** as a Dynamic Web Project.
3. **Setup Apache Tomcat Server** in Eclipse.
4. Place the `mysql-connector-java-5.1.49.jar` in `WEB-INF/lib`.
5. **Create the Database** in MySQL:
   - Database Name: `hospital`
   - Tables and columns should match your `patientDetails.jsp` usage.
6. **Run the project** and open on:  
   `http://localhost:8080/pms-2/`

---

## 📂 Project Structure
```
pms-2/
├── WebContent/
│ ├── newPatient.jsp
│ ├── patientDetails.jsp
│ └── WEB-INF/
│ ├── lib/mysql-connector-java-5.1.49.jar
│ ├── views/registrationSuccess.jsp
│ ├── web.xml
│ └── MANIFEST.MF
```
