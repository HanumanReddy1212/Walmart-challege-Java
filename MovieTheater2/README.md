Movie Theater Seating Challenge

Problem Statement:

Implement an algorithm for assigning seats within a movie theater to fulfill reservation requests. Assume the movie theater has the seating arrangement of 10 rows x 20 seats, 
as illustrated to the right.

Your homework assignment is to design and write a seat assignment program to maximize both customer satisfaction and customer safetyFor the purpose of public safety, 
assume that a buffer of three seats and/or one row is required.

Program Inputs:
•	Takes an input file from command line argument
•	File contains of multiple RequestId and numberOfSeats to be booked.
•	Assuming input doesn't change.

Program Output:
•	Outputs a file name which contains following:
•	RequestId [seatsNumbers(comma separated)]

Assumptions:
These are the following assumptions I have made during developing this application:
•	The system will not reserve seats for a group if the requested number of seats is greater than the available seats. If this is the case people are informed about that seats are full. 
•	Assuming that the group can be either be a family or friends who wish to enjoy the movie by seating together. Therefore I assume the people in family and friends trust among themselves. 
	So seats can be assigned in continuoues manner.

Customer Satisfaction:

•	I have followed the "first come first serve" principle to satisfy the customer.
•	My goal was to provide tickets to the  group of people like family or friends in such a way that they are seated together.
•	I have also allotted seats in the order they are received. So the priority will be to allocate seats for the group in a single row. 
•	If the number of people exceeds the row capacity, I have allotted the remaining people in another row. 

Customer Safety:

Divided customer safety into 3 groups:
•	High: Should have a buffer of 3 seats in between two groups and  seats are allotted in alternative rows.
•	Medium: Buffer of 3 seats in maintained between groups of people.
• 	Low: Buffer not required.
