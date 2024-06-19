# Rack Application Java 101 Example

Throughout the academy you will build an application to manage our Rack pool. A rack is a piece of hardware that contains multiple computers that emulate part of a car. The goal for this exercise is to reacap the basics of java and to built a solid ground for what will come next.

We have a real need for a rack management system. We need to know how many racks are available, the type of components each rack has, who is using it and when they are using, etc.

## Setup

- Fork and clone this repository.
- Validate that you have follow every step of the laptop setup.
- Open the project in your IDE.
- Start coding!

## Application

You will start with a simple standalone application, you should follow the exercisces to start building the app.

## Exercices

### Setup your data model

- Create a new class `Rack` that will represent each rack in our system. It should have at least the following fields:
  - id
  - name
  - serial number 
- Create a new class `Team` that will represent each team in our system. It should have at least the following fields:
  - id
  - name
  - product
- Create a new class `Booking` that will represent each booking in our system. It should have at least the following fields:
  - id
  - name
  - from
  - to
  - requester information
  - rack information
- Each class should have a **`builder`** class that will help you build each.
- Each class have common data you should take advantage of OO principles to enhance your code.

### Setup your persistence layer

 - `RackDatabase` will be your in memory database, choose a collection (List, Map...) that will hold your data and implement the following methods:
   - Add a method to add a rack to the database.  
   - Add a method to remove a rack from the database.
   - Add a method to find a rack by its id.
   - Add the same methods for teams and bookings.

### Setup your business layer

- Class `RackService` will be responsibile for handle all business opertions of our system. It should have at least these methods:
  - Find a rack by its id.
  - Add a rack to the database.
  - Remove a rack from the database.
  - Find all available racks.
  - Find all bookings.
  - Book a rack for a team.
  - Cancel a booking.
  - Find all teams.
  - Find all bookings for a team.
  - Find all bookings for a team in a specific period of time.
- This class should be a **`Singleton`**
- Don't forget to control the flow of your application:
  1. If a rack is already booked for a team in a specific period of time, you must throw an exception.
  2. If a rack id don't exists you must throw an exception.
  3. ...

### Setup your controller layer

- Class `Application" will be your controller layer.
- Here you should create new data, add it to the database and return it back to the user (print results to console).
- You should also handle exceptions.
