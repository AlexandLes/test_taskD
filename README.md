This is a simple implementation of user registration

-------------------------------------------------------------
# Features
User:
* Create user
* Update user
* Check users in range of their birth dates
* Delete user
 --------------------------------------------------------   

# How to run application?

1. Clone this project to your local machine
2. Deploy generated WAR file to a servlet container such as Tomcat
3.You can send different requests using `Postman` service.

----------------------------------

# Structure

* controller
    * Create (POST) - `/users` - register a new user
    * Delete (DELETE) - `/users/id` - delete a user by id
    * Update (POST) - `/logout` - update user
    * Get all users by date between - `/users/date-between?..`
  
* dao - classes in this package are used for retrieving and sending data to db
* service - classes in this package are made for bussines - logic realization
* dto - wrapper for model objects to unify the requests and responses in controllers
* exception - custom exception class used for throwing exceptions in DAO`s
* validation - custom validation for fields
* model - POJO`s that represent data
* test - test package
* resources - here you can store non-java files(etc. used for setting db)

----------------------------------------

# Made by Leshtaiev Olexandr