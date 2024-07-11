# Criminal Record System

## Overview
The Criminal Record System is a Java-based application that allows users to maintain and manage criminal records. This project demonstrates the use of data structures (like linked lists), user authentication, database integration using MySQL, advanced search functionality, and exception handling. The graphical user interface (GUI) is implemented using Java AWT.

## Features
- **User Authentication**: Users can register and log in to the system.
- **Record Management**: Add, display, and delete criminal records.
- **Advanced Search**: Search criminal records by name.
- **Database Integration**: Records are stored in a MySQL database.
- **Exception Handling**: Robust error handling throughout the application.

## Technologies Used

- Java
- Java Swing
- MySQL
- JDBC

## Getting Started

### Prerequisites
- **Java Development Kit (JDK)**: Ensure you have JDK 8 or higher installed.
- **MySQL**: Install MySQL and set up a database.
- **Git**: To clone the repository.

### Installation

1. **Clone the repository**:
    ```sh
    git clone https://github.com/your-username/CriminalRecordSystem.git
    cd CriminalRecordSystem
    ```

2. **Set up the MySQL Database**:
    - Create a new database named `criminal_db`.
    - Execute the following SQL script to create the required tables:
      ```sql
      CREATE TABLE users (
          id INT AUTO_INCREMENT PRIMARY KEY,
          username VARCHAR(50) NOT NULL UNIQUE,
          password VARCHAR(50) NOT NULL
      );

      CREATE TABLE records (
          id INT AUTO_INCREMENT PRIMARY KEY,
          name VARCHAR(100) NOT NULL,
          age INT NOT NULL,
          crime VARCHAR(255) NOT NULL,
          date VARCHAR(50) NOT NULL,
          user_id INT,
          FOREIGN KEY (user_id) REFERENCES users(id)
      );
      ```

3. **Configure Database Connection**:
    - Update the `DatabaseHelper` class with your MySQL credentials:
      ```java
      private static final String DB_URL = "jdbc:mysql://localhost:3306/criminal_db";
      private static final String DB_USER = "your_mysql_username";
      private static final String DB_PASSWORD = "your_mysql_password";
      ```

4. **Build and Run the Application**:
    - Open the project in your preferred IDE (e.g., IntelliJ IDEA).
    - Build the project.
    - Run the `MainApp` class.

### Usage

1. **Register**: Create a new account by entering a username and password.
2. **Login**: Use the registered credentials to log in.
3. **Add Record**: Enter the criminal's details and click "Add Record".
4. **Display Records**: Click "Display Records" to view all records.
5. **Search**: Enter a name and click "Search" to find a specific record.
6. **Delete Record**: Enter a name and click "Delete Record" to remove a record.

### Project Structure

- `MainApp.java`: The main entry point of the application.
- `LoginFrame.java`: Handles user login and registration.
- `MainFrame.java`: The main interface for managing criminal records.
- `DatabaseHelper.java`: Contains methods for database operations.
- `CriminalRecord.java`: Model class representing a criminal record.

### Contributing

1. **Fork the repository**.
2. **Create a new branch**: `git checkout -b feature-name`.
3. **Make your changes** and commit them: `git commit -m 'Add some feature'`.
4. **Push to the branch**: `git push origin feature-name`.
5. **Create a pull request**.

### License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

### Acknowledgments

- Thanks to the Java community for their extensive resources and support.

