# Docker Exercise

## Build Dockerfile for our app

1. Open the project stands-academy

2. Run the quarkus service using "mvn quarkus:dev".

3. If the quarkus application fails to reach the database, make sure you create a docker container with postgresql with the same properties that are in the application.properties file in the code you checked out.
    - Check the values on the file src/main/resources/application.properties (Hint: Check the database name in the end of the jdbc.rul)
    - Run the command
        - docker run -d --rm --name {{container-name}} -p 5432:5432 -e POSTGRES_DB={{Postgres-Database-Name}} -e POSTGRES_USER={{Postgres-User}} -e POSTGRES_PASSWORD={{Postgres-Password}} postgres:14.7

4.  Test the functionalities of the application

5.  We want to make sure we can build our quarkus project with a Docker file. So let's create one using multi-staged build:
    - Create a Dockefile on the root of the project
    - First Stage:
        - We can use "maven:3.8.6-amazoncorretto-17" in the FROM command
        - Configure the Dockerfile to copy all the content in our current folder to a working directory called "/app" inside the container. (Hint: Don't forget to declare the working directory)
            - Declare the Working Directory "/app" and Copy all the content of our project to inside the image
        - After this, you can run a mvn command to clean and package the application in order to generate the Jar file.
            - Add "-Dmaven.test.skip" to the mvn command to skip the tests
        - Now to help to not download all the dependencies of your pom.xml every time, lets run the command "mvn dependency:go-offline".
            - Before the two instructions of copying all your content ("COPY . .") and the mvn package command. Copy just the pom.xml to the image and then run the command "mvn dependency:go-offline"
    - Second Stage:
        - We can use "FROM amazoncorretto:17.0.10-alpine3.19" in the FROM command.
        - Declare the working directory "/deployments"
        - Copy the content of the folders "target/quarkus-app/lib", "target/quarkus-app/app" and "target/quarkus-app/quarkus", to the working directory.
            - Make sure that not only the content of the folders is copied but also the folder itself keeping the name.
            - The commands should look like this:
                - > COPY --from=build /app/target/quarkus-app/lib/ lib/
        - Copy the jar files (Hint: Use regex) from "target/quarkus-app" folder to our working directory.
            - Use the following regex:
                - > /app/target/quarkus-app/*.jar
        - Then we end the Dockerfile with the command we want to run when the container is created from our image, in this case, it will be "java -jar {{jar-location}}. (Hint: It should be on /deployments/quarkus-run.jar)
            - Use "CMD" docker file instruction to run the java command.
    - Build the image
    - If the build is successful, run the image without being in detach mode and you should see "Connection to localhost:5432 refused" in the logs.
    - At any moment if you want to test the directories, you can either remove the second stage if you want to test the first stage, or remove the command to be executed to test the directories of the second stage.

6. If everything goes right in the previous step you should see the error "Connection to localhost:5432 refused". This happens because inside the Docker container the network cannot reach the Postgres database that is exposed on the other container that you created previously. To solve this problem we need to put both of them running in the same docker network.
    - Create a docker network called "academy" using the docker cli.
        - > docker network create academy
    - Run the docker container from the database with the network option with the value "academy".
        - Stop the previous postgrest container and run again the database but now with the network option:
        - > docker run -d --network academy --rm --name {{container-name}} -p 5432:5432 -e POSTGRES_DB={{Postgres-Database-Name}} -e POSTGRES_USER={{Postgres-User}} -e POSTGRES_PASSWORD={{Postgres-Password}} postgres:14.7
    - As we seen in the PowerPoint, the network communication is done using the container name. That means that we cannot use the "localhost:5432" to reach the database since it is running inside the network academy. So, we need to adapt the code to start using "<database-container-name>:5432" where you will use the name that you used to create the database container.
        - Change the application.properties jdbc.url to use the database container name you just created.
    - Build again the image for our Application and run it, but this time, run with the network "academy" option. Expose the 8080 port to be able to open it on the browser. Run the docker run command with now the following options:
        - > --network academy -p 8080:8080
    - The application should work now.

## Build Docker Compose for our app and database

1. Create a docker-compose.yml file in the root of the project.
2. And then inside "services:" create two, one for the database container and the other one that uses the Dockerfile that you wrote in the root of the project. Don't forget to replicate the same parameters you used on the docker run command for both containers on the docker compose file.
    - Start the file with the following content:
     ``` 
        version: '3.8'
        services:
            postgres:
            app:
    ```
    - We now have a base docker compose file to create two containers "postgres" and "app". You can change the container names if you want.
    - Be careful, in what names you give to the containers. Remember you already edited the code for a specific database container name.
    - Inside the "postgres" service you can follow the structure below:
    ```
    postgres:
        image: postgres:14.7
        restart: always
        environment:
            POSTGRES_DB: {{postgres-database-name}}
            POSTGRES_USER: {{postgres-user}}
            POSTGRES_PASSWORD: {{postgres-password}}
    ```
    - Inside "app" service you can follow the structure below:
    ```
    app:
        build: .
        ports:
            - "8080:8080"
        restart: always
        depends_on:
            - postgres
    ```
3. Run the docker compose and test on the browser that it works.

## Extra - Adapt the database host per Quarkus profile

1. As you noticed, in the previous steps, you had to change the hostname of the database because "localhost" was not reachable from inside the container network but, you still want to access the localhost postgres database when you are running your app locally with "mvn quarkus:dev".
    - Configure multiple "quarkus.datasource.jdbc.url" in the application properties using different quarkus profiles. You can keep the localhost being the default value and add a new one with quarkus profile called "docker" with the host being the container name.
        - Keep the default jdbc.url using localhost
        - Add one more jdbc.url property but now use "%" to assign it to a profile.
        - > %docker.quarkus.datasource.jdbc.url
        - In this case the url should use the container name of the database.
    - Test the application locally using "mvn quarkus:dev".
    - Change Dockerfile to use the profile "docker" in the maven package phase and then use the configured docker compose to test the application in the browser.
        - Add the "-Dquarkus.profile=docker" option to the mvn package command