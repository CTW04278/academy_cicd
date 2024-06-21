# Create microprofile

From this module on we are going create our Rack Booking System Application.
As you know from the previous module, a rack is a piece of hardware that contains multiple computers that emulate part
of a car.
We will start in this module and evolve with the next modules.

The goal for this exercise is to create our first microprofile
application running in an application server. In our case we are going to use quarkus.

Use the following command to create a new Quarkus project

   ```
   mvn io.quarkus.platform:quarkus-maven-plugin:3.11.3:create \
    -DprojectGroupId=com.ctw.workstation \
    -DprojectArtifactId=workstation-rack \
    -Dextensions='rest'

   ```

Open the new project in your IDE

