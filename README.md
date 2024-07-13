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

[{"id":1,"name":"User1","orderAmount":100,"orderDate":"2024-06-01","month":"JUNE","award":50},{"id":2,"name":"User1","orderAmount":60,"orderDate":"2024-07-12","month":"JULY","award":10},{"id":3,"name":"User1","orderAmount":120,"orderDate":"2024-07-13","month":"JULY","award":90},{"id":4,"name":"User1","orderAmount":140,"orderDate":"2024-09-15","month":"SEPTEMBER","award":130},{"id":5,"name":"User1","orderAmount":156,"orderDate":"2024-09-15","month":"SEPTEMBER","award":162},{"id":6,"name":"User2","orderAmount":55,"orderDate":"2024-06-01","month":"JUNE","award":5},{"id":7,"name":"User2","orderAmount":110,"orderDate":"2024-06-05","month":"JUNE","award":70},{"id":8,"name":"User2","orderAmount":220,"orderDate":"2024-08-10","month":"AUGUST","award":290},{"id":9,"name":"User2","orderAmount":320,"orderDate":"2024-09-25","month":"SEPTEMBER","award":490},{"id":10,"name":"User2","orderAmount":80,"orderDate":"2024-09-25","month":"SEPTEMBER","award":30},{"id":11,"name":"User3","orderAmount":150,"orderDate":"2024-06-01","month":"JUNE","award":150},{"id":12,"name":"User3","orderAmount":250,"orderDate":"2024-07-05","month":"JULY","award":350},{"id":13,"name":"User3","orderAmount":50,"orderDate":"2024-08-10","month":"AUGUST","award":0},{"id":14,"name":"User3","orderAmount":5,"orderDate":"2024-09-25","month":"SEPTEMBER","award":0},{"id":15,"name":"User3","orderAmount":250,"orderDate":"2024-09-25","month":"SEPTEMBER","award":350}]


[Report: ](http://localhost:8080/customer/pointsReport)
{"OrderDetails":[{"id":1,"name":"User1","orderAmount":100,"orderDate":"2024-06-01","month":"JUNE","award":50},{"id":2,"name":"User1","orderAmount":60,"orderDate":"2024-07-12","month":"JULY","award":10},{"id":3,"name":"User1","orderAmount":120,"orderDate":"2024-07-13","month":"JULY","award":90},{"id":4,"name":"User1","orderAmount":140,"orderDate":"2024-09-15","month":"SEPTEMBER","award":130},{"id":5,"name":"User1","orderAmount":156,"orderDate":"2024-09-15","month":"SEPTEMBER","award":162},{"id":6,"name":"User2","orderAmount":55,"orderDate":"2024-06-01","month":"JUNE","award":5},{"id":7,"name":"User2","orderAmount":110,"orderDate":"2024-06-05","month":"JUNE","award":70},{"id":8,"name":"User2","orderAmount":220,"orderDate":"2024-08-10","month":"AUGUST","award":290},{"id":9,"name":"User2","orderAmount":320,"orderDate":"2024-09-25","month":"SEPTEMBER","award":490},{"id":10,"name":"User2","orderAmount":80,"orderDate":"2024-09-25","month":"SEPTEMBER","award":30},{"id":11,"name":"User3","orderAmount":150,"orderDate":"2024-06-01","month":"JUNE","award":150},{"id":12,"name":"User3","orderAmount":250,"orderDate":"2024-07-05","month":"JULY","award":350},{"id":13,"name":"User3","orderAmount":50,"orderDate":"2024-08-10","month":"AUGUST","award":0},{"id":14,"name":"User3","orderAmount":5,"orderDate":"2024-09-25","month":"SEPTEMBER","award":0},{"id":15,"name":"User3","orderAmount":250,"orderDate":"2024-09-25","month":"SEPTEMBER","award":350}],"TotalOrderAmtPoint":{"User2":{"JUNE":{"JUNE":75},"AUGUST":{"AUGUST":290},"SEPTEMBER":{"SEPTEMBER":520}},"User1":{"JUNE":{"JUNE":50},"SEPTEMBER":{"SEPTEMBER":292},"JULY":{"JULY":100}},"User3":{"JUNE":{"JUNE":150},"AUGUST":{"AUGUST":0},"SEPTEMBER":{"SEPTEMBER":350},"JULY":{"JULY":350}}}}



