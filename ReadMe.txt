On starting up the application, the Test date was inserted in the database.
Login authentication added with admin/admin
service Endpoints:
	http://localhost:8080/customer/pointsReport
		To get the user-wise monthly points earned as per the test data.
	http://localhost:8080/customer/all
		To get all the data from the Database
	http://localhost:8080/customer/<userID>
		To get the special user data
