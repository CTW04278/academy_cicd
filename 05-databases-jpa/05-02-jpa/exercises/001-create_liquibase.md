# Create Liquibase

From that point on you should have a Java application hosted in a Quarkus webserver.
Therefore, our goal in these exercises is to prepare all database infrastructure needed for the application.

1. Add the Liquibase and JDBC dependency to the application.
   ````
   <dependency>
      <groupId>io.quarkus</groupId>
      <artifactId>quarkus-liquibase</artifactId>
   </dependency>
   
   <dependency>
      <groupId>io.quarkus</groupId>
      <artifactId>quarkus-jdbc-postgresql</artifactId>
   </dependency>
   ````
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
8. Add the properties for the DB connection to the `application.properties` file. See the example.
   ````
   quarkus.datasource.db-kind=postgresql
   quarkus.datasource.username=postgres
   quarkus.datasource.password=postgres
   quarkus.datasource.jdbc.url=jdbc:postgresql://localhost:5432/fs_academy
   ````

9. Add the liquibase properties to the `application.properties` file. See the example.
   ````
   quarkus.liquibase.migrate-at-start=true
   quarkus.liquibase.change-log=db/changeLog.xml
   ````
