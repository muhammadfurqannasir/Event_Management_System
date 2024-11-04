# Event Management System in Java

This project is an Event Management System developed in Java, designed to allow users to create events, add guests, and manage bookings. The system utilizes a relational database to store information about events, guests, and bookings.

## Features

- Create and manage events
- Add and manage guests
- Handle event bookings
- Persistent storage using a relational database

## Technologies Used

- Java
- JDBC (Java Database Connectivity)
- SQL
- [Your Database of Choice] (e.g., MySQL, PostgreSQL)

## Getting Started

### Prerequisites

- Java Development Kit (JDK) installed
- [Your Database of Choice] installed and running
- An IDE (e.g., IntelliJ IDEA, Eclipse) for Java development

### Setup

1. Clone the repository to your local machine:
   ```bash
   git clone https://github.com/muhammadfurqannasir/Event_Management_System.git
   ```

2. Configure the database connection:
   - Update the database URL, username, and password in the connection configuration section of your code.

3. Create the necessary database tables:
   - Run the SQL scripts provided in the `sql` directory to create the `events`, `guests`, and `bookings` tables.

4. Compile and run the Java program:
   ```bash
   javac Main.java
   java Main
   ```

## Usage

- Follow the prompts in the console to create events, add guests, and manage bookings.
- The system interacts with the database to perform CRUD (Create, Read, Update, Delete) operations.

## Contributing

Contributions are welcome! Please follow these steps to contribute:

1. Fork the repository.
2. Create a new branch:
   ```bash
   git checkout -b feature/YourFeature
   ```
3. Commit your changes:
   ```bash
   git commit -m "Add some feature"
   ```
4. Push to the branch:
   ```bash
   git push origin feature/YourFeature
   ```
5. Open a Pull Request.

## Author

- **Muhammad Furqan Nasir**
