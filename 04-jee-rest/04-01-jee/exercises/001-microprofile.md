# Create microprofile

1. We are going to use the exercise created during the Java 101 module to create our first microprofile application
   running in an application server. In our case we are going to use quarkus. Use the following command to
   create a new Quarkus project

   ```
   mvn io.quarkus.platform:quarkus-maven-plugin:3.11.3:create \
    -DprojectGroupId=com.ctw.workstation \
    -DprojectArtifactId=workstation-rack \
    -Dextensions='rest'

   ```
2. Open the new project in your IDE

3. Move all your code from the Java 101 module to your newly created microprofile application maintaining the
   package structure.
    - Move all classes from your `com.ctw.rack.data` package to the package `com.ctw.workstation.entity`;
    - Move all classes from your `com.ctw.rack.exception` package to the
      package `com.ctw.workstation.control.exception`;
    - Move all classes from your `com.ctw.rack.service` package to the package `com.ctw.workstation.boundary`;
    - Copy `com.ctw.java.Application` class to `com.ctw.workstation.Application`
4. Add the following imports to `com.ctw.workstation.Application` class:
   ```
   import io.quarkus.runtime.StartupEvent;
   import javax.enterprise.event.Observes;
   ```
5. Change the signture of `com.ctw.workstation.Application` main method
   to `void onApplicationStart(@Observes StartupEvent startupEvent)`
6. Run the application server.

