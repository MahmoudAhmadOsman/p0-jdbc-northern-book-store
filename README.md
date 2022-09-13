## P0 - Northern Book Store

The Northern Book Store is a book management system that allows customers to purchase books from  online using our website.  

## Functionality

Since this application is based on Java console, customers or users will be able to do the following

- User or customer will be able to create an account by:
- creating a username, password, email & his/her full name
- Logging in with the username and the password that the customer created

##### After creating an account
- Customer will be able to
   - Login using username and password
   - see all the available books that the Northern Book Store has on its inventory
   - view individual book using by the book isbn 
   - able to place multiple orders by using the book's isbn
   - able to review all of his/her orders in details
   - able to search any book that is available in Northern Book Store inventory

##### Managers
- Managers will be able to create or add a new book into the inventory
- The inventory will be viewable by customers as soon as the managers listed on the Nothern Book Store's application
- Managers will have all the privileges to control the application
- Mangers will be able to change any user's status such disabling user's account or granting him/her an access level than the normal user


## Application Overall Structure
- This application is implementing one of the many methodologies of designs or MVCs

### Models

```bash

1. User
2. Order
3. Payment
4. Book
```

### Service
- This part of the application is where I will perform the business login and other necessary validation

```bash

1. UserService
2. BookService
3. OrderService
```

### DAO
- Also, this part of the application is where I will perform all the necessary data access object

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
- In this section, I will perform or conduct database interactivity login. For this application, I will be using JDBC technology and [Postgress](https://www.postgresql.org/) as my application backend database storage
Add as much models as you would need for your design


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










