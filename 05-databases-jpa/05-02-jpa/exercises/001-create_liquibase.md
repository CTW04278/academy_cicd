# Create Liquibase

From that point on you should have a Java application hosted in a Quarkus webserver.
Therefore, our goal in these exercises is to prepare all database infrastructure needed for the application.

1. Add the Liquibase dependency to the application.
2. Create a new file which should contains the schema creation.
3. Create a new file to add a new relation named “T_TEAM” which should contain the attributes (id, name, product,
   created_at, modified_at, default_location);
4. Create a new file to add a new relation named “T_TEAM_MEMBER” which should contain the attributes (id, team_id,
   ctw_id, name, created_at, modified_at);
5. Create a new file to add a new relation named “T_RACK” which should contain the attributes (id, serial_number,
   status, team_id, default_location, created_at, modified_at);
6. Create a new file to add a new relation named “T_RACK_ASSET” which should contain the attributes (id, asset_tag,
   rack_id);
7. Create a new file to add a new relation named “T_BOOKING” which should contain the attributes (rack_id, requester_id,
   book_from, book_to, created_at, modified_at);
