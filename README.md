
# Online Bookstore

## Overview

Developed a comprehensive online bookstore using Spring Boot, PostgreSQL, and Thymeleaf. The project includes features such as user authentication, book management, categorization and search, shopping cart functionality, secure order processing, and a responsive UI design for a seamless user experience and efficient management of bookstore operations.

## Features

- **User Authentication**: Secure login and registration for users and admins.
- **Book Management**: Admins can add, edit, and delete books, including categorization and search functionality.
- **Shopping Cart**: Users can add books to their cart, update quantities, and remove items.
- **Order Processing**: Secure order checkout with total amount calculation.
- **Role-Based Access Control**: Different access levels for regular users and admin users.
- **Transaction History**: Users can view their purchase history.
- **Responsive UI Design**: Optimized for various devices to ensure a seamless user experience.

## Technologies Used

- **Backend**: Spring Boot
- **Frontend**: Thymeleaf, HTML, CSS
- **Database**: PostgreSQL
- **Version Control**: Git & GitHub

## Installation

1. **Clone the repository**
    ```bash
    git clone https://github.com/durgaraoande/bookstore.git
    cd bookstore
    ```

2. **Configure the database**
    - Create a PostgreSQL database named `bookstore`.
    - Update the database configuration in `src/main/resources/application.properties`.

3. **Build and run the application**
    ```bash
    ./mvnw spring-boot:run
    ```

4. **Access the application**
    - Open a web browser and navigate to `http://localhost:8080`.

## Usage

### Admin Login
- Username: `admin`
- Password: `admin`

### User Login
- Register a new user or use pre-existing credentials.

## Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── bookstore/
│   │           ├── controller/
│   │           ├── model/
│   │           ├── repository/
│   │           ├── service/
│   │           └── OnlineBookstoreApplication.java
│   ├── resources/
│   │   ├── static/
│   │   ├── templates/
│   │   └── application.properties
└── test/
    └── java/
```

## Contributing

1. Fork the repository.
2. Create a new branch (`git checkout -b feature-branch`).
3. Commit your changes (`git commit -am 'Add new feature'`).
4. Push to the branch (`git push origin feature-branch`).
5. Create a new Pull Request.


## Contact

For any inquiries or feedback, please contact [Durga Rao](mailto:durgaraoande9@gmail.com).
