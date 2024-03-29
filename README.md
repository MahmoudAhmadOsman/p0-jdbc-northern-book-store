# P0 - Northern Book Store

Welcome to the Northern Book Store, a comprehensive book management system that empowers customers to effortlessly purchase books online through our user-friendly online platform.

## Functionality

Our application, built on Java console, offers a wide range of features to enhance the customer experience. Users, whether customers or managers, can perform the following actions:

### Customers

1. **Account Creation**: Customers can easily create an account by providing the following details:
    - Username
    - Password
    - Email
    - Full Name

2. **User Login**: After creating an account, customers can securely log in using their username and password.

3. **Book Selection**: Once logged in, users can explore our extensive collection of books available at the Northern Book Store.

4. **Book Details**: Users can access detailed information about each book by searching using its ISBN (International Standard Book Number).

5. **Order Placement**: Customers have the convenience of placing multiple book orders, ensuring a seamless shopping experience.

6. **Order Review**: Users can review their previous orders, facilitating efficient order management.

7. **Book Search**: A powerful search feature enables users to quickly locate any book they desire.

### Managers

In addition to the above customer-centric features, managers have access to additional privileges:

1. **Inventory Management**: Managers can create and update the store's inventory, ensuring that customers have access to the latest book selections.

2. **Real-time Inventory Updates**: Any changes made to the inventory are immediately visible to customers, ensuring accurate and up-to-date listings.

3. **User Management**: Managers can efficiently manage user accounts, including modifying user privileges and statuses.

4. **Privilege Control**: Managers have full control over user privileges, allowing them to tailor access levels as needed for their team.

At Northern Book Store, we aim to provide an exceptional online book shopping experience for both customers and managers. Enjoy a vast selection of books, seamless transactions, and efficient management capabilities with our user-friendly system. 

## Application Overall Structure

#### Models

```bash

1. User
2. Order
3. Payment
4. Book
```

### Service

- The service layer provides business logic and operations for the application, ensuring proper interactions between models and the database. Key services include:

```bash

1. UserService
2. BookService
3. OrderService
```

### DAO

- Data Access Objects interact with the database to perform CRUD (Create, Read, Update, Delete) operations on the application's models. These DAOs are responsible for data retrieval and storage:

```bash

1. UserDAO
2. OrderDAO
3. PaymentDAO
4. BookDAO

```


### Roles

- The application distinguishes between two primary user roles:

```bash

1. Admin(Manager)
2. User(Customer)

```


### Database

- -The application relies on PostgreSQL as the backend database storage. PostgreSQL is a robust relational database management system, ensuring data integrity and scalability.

### Host

- The application will be hosted on Amazon Web Services (AWS) using an EC2 instance. Additionally, AWS RDS (Relational Database Service) with PostgreSQL will serve as the database infrastructure, ensuring secure, scalable, and highly available data storage for the application.

- This well-structured architecture ensures that the application efficiently manages user accounts, orders, payments, and book data while providing a seamless experience for both administrators and customers.


## Tech Stack 
> This application utilizes the following technologies




| Technology   	|Description    	                                                           |
|----	|-----------	                                                                       |
| 1. Java    	      |  Developed in Java 8  	                                         |
| 2. Apache Maven    	| Software project management and comprehension tool    	           |
| 3. PostgresSQL        | Database storage  	                                               |
| 4. Mockito   	      | Testing library for Java application  	                             |
| 5. Docker   	      | Hosting environment  	                                               |
| 6. DBeaver    	      | SQL client software application and a database administration tool   |
| 7. Git  	            | Source control system                                                |   	                                                                 
| 8. Github             | Code hosting platform for version control and collaboration          |


____________________________________________________________________________________________________________

### ERD Schema Design

![ERD Schema Design](https://github.com/MahmoudAhmadOsman/p0-jdbc-nbs/blob/main/src/main/resources/design/nbs-new-erd.png)










