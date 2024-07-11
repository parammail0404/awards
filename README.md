On starting up the application, the Test date inserted in the database.
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

All records: http://localhost:8080/customer/all

	[{"id":1,"name":"User1","orderAmount":100,"orderDate":"2024-06-01","month":"JUNE","award":50},{"id":2,"name":"User1","orderAmount":60,"orderDate":"2024-07-12","month":"JULY","award":10},{"id":3,"name":"User1","orderAmount":120,"orderDate":"2024-08-13","month":"AUGUST","award":140},{"id":4,"name":"User1","orderAmount":140,"orderDate":"2024-09-15","month":"SEPTEMBER","award":180},{"id":5,"name":"User2","orderAmount":55,"orderDate":"2024-06-01","month":"JUNE","award":5},{"id":6,"name":"User2","orderAmount":110,"orderDate":"2024-06-05","month":"JUNE","award":120},{"id":7,"name":"User2","orderAmount":220,"orderDate":"2024-08-10","month":"AUGUST","award":340},{"id":8,"name":"User2","orderAmount":320,"orderDate":"2024-09-25","month":"SEPTEMBER","award":540},{"id":9,"name":"User3","orderAmount":150,"orderDate":"2024-06-01","month":"JUNE","award":200},{"id":10,"name":"User3","orderAmount":250,"orderDate":"2024-07-05","month":"JULY","award":400},{"id":11,"name":"User3","orderAmount":50,"orderDate":"2024-08-10","month":"AUGUST","award":0},{"id":12,"name":"User3","orderAmount":50,"orderDate":"2024-09-25","month":"SEPTEMBER","award":0}]


[Report: ](http://localhost:8080/customer/pointsReport)
{"OrderDetails":{"User2":{"JUNE":[{"id":5,"name":"User2","orderAmount":55,"orderDate":"2024-06-01","month":"JUNE","award":55},{"id":6,"name":"User2","orderAmount":110,"orderDate":"2024-06-05","month":"JUNE","award":110}],"AUGUST":[{"id":7,"name":"User2","orderAmount":220,"orderDate":"2024-08-10","month":"AUGUST","award":220}],"SEPTEMBER":[{"id":8,"name":"User2","orderAmount":320,"orderDate":"2024-09-25","month":"SEPTEMBER","award":320}]},"User1":{"JUNE":[{"id":1,"name":"User1","orderAmount":100,"orderDate":"2024-06-01","month":"JUNE","award":100}],"AUGUST":[{"id":3,"name":"User1","orderAmount":120,"orderDate":"2024-08-13","month":"AUGUST","award":120}],"SEPTEMBER":[{"id":4,"name":"User1","orderAmount":140,"orderDate":"2024-09-15","month":"SEPTEMBER","award":140}],"JULY":[{"id":2,"name":"User1","orderAmount":60,"orderDate":"2024-07-12","month":"JULY","award":60}]},"User3":{"JUNE":[{"id":9,"name":"User3","orderAmount":150,"orderDate":"2024-06-01","month":"JUNE","award":150}],"AUGUST":[{"id":11,"name":"User3","orderAmount":50,"orderDate":"2024-08-10","month":"AUGUST","award":50}],"SEPTEMBER":[{"id":12,"name":"User3","orderAmount":50,"orderDate":"2024-09-25","month":"SEPTEMBER","award":50}],"JULY":[{"id":10,"name":"User3","orderAmount":250,"orderDate":"2024-07-05","month":"JULY","award":250}]}},"TotalOrderAmtPoint":{"User2":{"JUNE":{"JUNE":165,"Points":230},"AUGUST":{"Points":340,"AUGUST":220},"SEPTEMBER":{"Points":540,"SEPTEMBER":320}},"User1":{"JUNE":{"JUNE":100,"Points":50},"AUGUST":{"Points":140,"AUGUST":120},"SEPTEMBER":{"Points":180,"SEPTEMBER":140},"JULY":{"Points":10,"JULY":60}},"User3":{"JUNE":{"JUNE":150,"Points":200},"AUGUST":{"Points":0,"AUGUST":50},"SEPTEMBER":{"Points":0,"SEPTEMBER":50},"JULY":{"Points":400,"JULY":250}}}}
