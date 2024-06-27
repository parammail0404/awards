On starting up the application, the Test date was inserted in the database.
Login authentication added with admin/admin.
service Endpoints:
	http://localhost:8080/customer/pointsReport
		To get the user-wise monthly points earned as per the test data.
	http://localhost:8080/customer/all
		To get all the data from the Database.
	http://localhost:8080/customer/{userID}
		To get the spacific user data based on the user ID.
  	http://localhost:8080/customer/find/title/{user name}
   		To get the spacific user data based on the customer name
  	http://localhost:8080/customer/find/date-after/{2024-08-15}
   		To get the data orders after the provided date
Sample date with calculations:

{"OrderDetails":{"User2":{"JUNE":[{"id":5,"name":"User2","orderAmount":55,"orderDate":"2024-06-01","month":"JUNE"},{"id":6,"name":"User2","orderAmount":110,"orderDate":"2024-06-05","month":"JUNE"}],"AUGUST":[{"id":7,"name":"User2","orderAmount":220,"orderDate":"2024-08-10","month":"AUGUST"}],"SEPTEMBER":[{"id":8,"name":"User2","orderAmount":320,"orderDate":"2024-09-25","month":"SEPTEMBER"}]},"User1":{"JUNE":[{"id":1,"name":"User1","orderAmount":100,"orderDate":"2024-06-01","month":"JUNE"}],"AUGUST":[{"id":3,"name":"User1","orderAmount":120,"orderDate":"2024-08-13","month":"AUGUST"}],"SEPTEMBER":[{"id":4,"name":"User1","orderAmount":140,"orderDate":"2024-09-15","month":"SEPTEMBER"}],"JULY":[{"id":2,"name":"User1","orderAmount":60,"orderDate":"2024-07-12","month":"JULY"}]},"User3":{"JUNE":[{"id":9,"name":"User3","orderAmount":150,"orderDate":"2024-06-01","month":"JUNE"}],"AUGUST":[{"id":11,"name":"User3","orderAmount":50,"orderDate":"2024-08-10","month":"AUGUST"}],"SEPTEMBER":[{"id":12,"name":"User3","orderAmount":50,"orderDate":"2024-09-25","month":"SEPTEMBER"}],"JULY":[{"id":10,"name":"User3","orderAmount":250,"orderDate":"2024-07-05","month":"JULY"}]}},"TotalOrderAmtPoint":{"User2":{"JUNE":{"JUNE":165,"Points":180},"AUGUST":{"Points":290,"AUGUST":220},"SEPTEMBER":{"Points":490,"SEPTEMBER":320}},"User1":{"JUNE":{"JUNE":100,"Points":50},"AUGUST":{"Points":90,"AUGUST":120},"SEPTEMBER":{"Points":130,"SEPTEMBER":140},"JULY":{"Points":50,"JULY":60}},"User3":{"JUNE":{"JUNE":150,"Points":150},"AUGUST":{"Points":0,"AUGUST":50},"SEPTEMBER":{"Points":0,"SEPTEMBER":50},"JULY":{"Points":350,"JULY":250}}}}
