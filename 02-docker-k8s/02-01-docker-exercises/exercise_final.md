# Docker Exercise

## Build Dockerfile for our app

1. Open the project stands-academy

2. Run the quarkus service using "mvn quarkus:dev".

3. If the quarkus application fails to reach the database, make sure you create a docker container with postgresql with the same properties that are in the application.properties file in the code you checked out.

4.  Test the functionalities of the application

5.  We want to make sure we can build our quarkus project with a Docker file. So let's create one using multi-staged build:
    - Create a Dockefile on the root of the project
    - In the first stage use a base maven image with the version "3.8.6" for Java 17 (Hint: Search Docker Hub for it):
        - Configure the Dockerfile to copy all the content in our current folder to a working directory called "/app" inside the container. (Hint: Don't forget to declare the working directory)
        - After this, you can run a mvn command to clean and package the application in order to generate the Jar file. (Hint: Before copying all the content and doing the package, copy just the pom.xml and run the mvn command "dependency:go-offline" to avoid downloading everytime the dependencies to the .m2 folder if there are no changes to the pom.xml)
        - Don't forget to skip tests or it will fail since we don't have any database in place.
    - In the second stage use a base Java distribution image for Java 17 (you can use amazoncorretto):
        - Copy the content of the folders "target/quarkus-app/lib", "target/quarkus-app/app" and "target/quarkus-app/quarkus", to a working directory called deployments.
            - Make sure that not only the content of the folders is copied but also the folder itself keeping the name.
        - Copy the jar files (Hint: Use regex) from "target/quarkus-app" folder to our working directory.
        - Then we end the Dockerfile with the command we want to run when the container is created from our image, in this case, it will be "java -jar {{jar-location}}. (Hint: It should be on /deployments/quarkus-run.jar)
    - If the build is successful, run the image without being in detach mode and you should see "Connection to localhost:5432 refused" in the logs.
    - At any moment if you want to test the directories, you can either remove the second stage if you want to test the first stage, or remove the command to be executed to test the directories of the second stage.

6. If everything goes right in the previous step you should see the error "Connection to localhost:5432 refused". This happens because inside the Docker container the network cannot reach the Postgres database that is exposed on the other container that you created previously. To solve this problem we need to put both of them running in the same docker network.
    - Create a docker network called "academy" using the docker cli.
    - Run the docker container from the database with the network option with the value "academy".
    - As we have seen in the PowerPoint, the network communication is done using the container name. That means that we cannot use the "localhost:5432" to reach the database since it is running inside the network academy. So, we need to adapt the code to start using "<database-container-name>:5432" where you will use the name that you used to create the database container.
    - Build again the image for our Application and run it, but this time, run with the network "academy" option. Expose the 8080 port to be able to open it on the browser.
    - The application should work now.

## Build Docker Compose for our app and database

1. Create a docker-compose.yml file in the root of the project.
2. And then inside "services:" create two, one for the database container and the other one that uses the Dockerfile that you wrote in the root of the project. Don't forget to replicate the same parameters you used on the docker run command for both containers on the docker compose file.
    - Be careful, in what names you give to the containers. Remember the previous steps.
3. Run the docker compose and test on the browser that it works.

## Extra - Adapt the database host per Quarkus profile

1. As you noticed, in the previous steps, you had to change the hostname of the database because "localhost" was not reachable from inside the container network but, you still want to access the localhost postgres database when you are running your app locally with "mvn quarkus:dev".
    - Configure multiple "quarkus.datasource.jdbc.url" in the application properties using different quarkus profiles. You can keep the localhost being the default value and add a new one with quarkus profile called "docker" with the host being the container name.
    - Test the application locally using "mvn quarkus:dev".
    - Change Dockerfile to use the profile "docker" in the maven package phase and then use the configured docker compose to test the application in the browser.