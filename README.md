# Student Management System (Java Servlet + JSP + MySQL)

This project is a simple **Student Management System** built using **Java Servlets**, **JSP**, and **MySQL**. It enables both admins and students to manage their profiles, register, login, update and delete details.

## 🛠️ Technologies Used

- Java (Servlets & JSP)
- HTML/CSS (Bootstrap 5)
- Apache Tomcat
- MySQL Database
- Maven Project
- Eclipse IDE

## 🎯 Features

### Admin
- Login with credentials
- View all registered students
- Add, Edit, and Delete student records
- Change student passwords

### Student
- Register and Login
- Update personal details
- Change password

## 📁 Project Structure

src/
└── main/
├── java/studentmanagement/
│ ├── LoginServlet.java
│ ├── RegisterServlet.java
│ ├── UpdateStudentServlet.java
│ └── ...
└── webapp/
├── login.html
├── admin.html
├── editProfile.html
└── ...

## ⚙️ Database Configuration

Make sure you have a MySQL database named `studentmanagement` and a table `student` with columns:

```sql
CREATE TABLE student (
  username VARCHAR(50) PRIMARY KEY,
  firstname VARCHAR(50),
  lastname VARCHAR(50),
  email VARCHAR(100),
  phonenumber VARCHAR(15),
  collegename VARCHAR(100),
  password VARCHAR(100)
);
🚀 How to Run
Clone the repository

Open with Eclipse or IntelliJ

Setup Tomcat Server

Make sure MySQL is running and DB is created

Run the application and visit: http://localhost:8080/student-management/

This project was developed for learning and academic purposes.
