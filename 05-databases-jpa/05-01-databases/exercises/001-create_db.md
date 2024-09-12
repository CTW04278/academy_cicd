# Create DB

The goal for this exercise is to create a database for a Rack's Booking System.
Take a look at the sample DB and create the necessary SQL scripts for it.

## Setup Postgres

Under assets folder you will find a docker-compose file that will create a Postgres instance with the necessary schema, copy it to your project and run `docker-compose up -d` to start it up.

You can use an IDE of your choice to access the database.

URL: jdbc:postgresql://localhost:5433/workstation-rack

User: postgres

Password: postgres

## Exercise

![sample_db.png](../assets/sample_db.png)

Create a new folder named "db" in "src/main/resources" and add the necessary SQL scripts to it.


1. Create a new relation named “T_TEAM” which should contain the attributes (id, name, product,
   created_at, modified_at, default_location);
    1. The default locations should be 'Porto', 'Lisbon' or 'Braga';
2. Create a new relation named “T_TEAM_MEMBER” which should contain the attributes (id, team_id,
   ctw_id, name, created_at, modified_at);
3. Create a new relation named “T_RACK” which should contain the attributes (id, serial_number,
   status, team_id, default_location, assembled_at, created_at, modified_at);
    1. The allowed status are 'Active', 'Returned', 'Repair', 'Outdated', 'Bricked';
4. Create a new relation named “T_RACK_ASSET” which should contain the attributes (id, asset_tag,
   rack_id);
5. Create a new relation named “T_BOOKING” which should contain the attributes (rack_id, requester_id,
   book_from, book_to, created_at, modified_at);

