## P0 - Northern Book Store

The Northern Book Store is a book management system that allows customers to purchase books from  online using our online system.  

## Functionality

Since this application is based on Java console, customers or users will be able to do the following

- User or customer will be able to create an account by:
- creating a username, password, email & his/her full name
- Logging in with the username and the password that the customer created

##### After creating an account
- Customer will be able to
   - signup
   - Login with username & password
   - see all the available books on Northern Book Store
   - view each book using isbn 
   - place multiple orders
   - review orders
   - search any book

##### Managers
- Managers will be able to create or add a new inventory
- The inventory will be viewable by customers immediately
- Managers will have all privileges to manager users & the inventory
- Mangers will be able to change  user's status


## Application Overall Structure

#### Models

```bash

1. User
2. Order
3. Payment
4. Book
```

### Service

```bash

1. UserService
2. BookService
3. OrderService
```

### DAO

```bash

1. UserDAO
2. OrderDAO
3. PaymentDAO
4. BookDAO

```


### Roles

```bash

1. Admin(Manager)
2. User(Customer)

```


### Database
- In this section, I will implement database connectivity and I will be using JDBC technology and [Postgress](https://www.postgresql.org/) as database storage for this application.

### Host
- This application will be hosted on AWS on one of the EC2 instance


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










