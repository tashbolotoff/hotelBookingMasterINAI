# Assignment

Dear INAI.kg student,

Thank you for applying for a Sur Place stipendium funded by the German Academic Exchange Service (DAAD). Here's your assignment:

Develop a Java program for dealing with use cases from the hotel booking domain. Following the scenarios and policies given below, please implement (a) classes representing the domain concepts (e.g., Hotel, Booking, Room, Reservation and so on) and their behavior and (b) unit tests which verify that your implementation meets the scenarios below.

Please hand in the Java project (source code) and a short video (5 to 10 minutes) where you explain and demonstrate (in English or German language) your solution.

Good luck,
Frank Grimm (University of Applied Sciences Zwickau)

## Specification

```
Feature: Booking a room for a hotel

	Policies:
		- All rooms must be paid in advance
		- Holiday period "Super Saver" rate is 50 Euro per night
		- Holiday period "Flexible" rate is 100 Euro per night
		- Term-time (school time) rates are always 50 Euro less 
		- Loyalty Card points can be used in blocks of 500 points
		- Each 500 points used discounts the rate by 5 Euro
		- For each 10 Euro spent, customers will earn 1 loyalty point

	Scenario: Booking a saver rate during summer school holiday
		Given I am not a Loyality Card holder
		When I book a room for 2 nights durchg the summer holiday period
		And I choose the "Super Saver" rate
		Then I should see that the rate is 50 Euro per night
		And the total amount payable up front is 100 Euro
		And I will not be able to cancel or change my booking

	Scenario: Using Loyalty Card points during booking get me a reduced rate during summer school holiday
		Given I am a Loyality Card holder with 800 points
		When I book a room for 2 nights durchg the summer holiday period
		And I choose the "Super Saver" rate
		And I opt to use 500 loyalty points 
		Then I should see that the rate is 50 Euro per night
		And I have a discount of 10 Euro from using my loyalty points
		And the total amount payable up front is 90 Euro
		And I will have earned 9 loyalty points
		And I will have 309 loyalty points left
		And I will not be able to cancel or change my booking
```
