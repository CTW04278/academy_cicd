# CICD Exercise

## Pre-Requesites

1. Create a Github account if you don't have it already. If you prefer to work on new fresh Github account, you can create a throway email in Hotmail or Yahoo Mail. 

    1. Create a new repository called "academy" and make it Public.
    2. Make sure the "Actions" tab is enabled on the Github page.
    3. Generate an SSH Key and configure it on Github to make sure you have the right permissions to Read and Write to the repository

## Import the exercise code and create the first workflow

1. Follow the instructions (They are also below) on the Github repository page to import the backend project that was implement in the previous modules and create the "main" branch. After this, you should be able to see the base code for our exercise on Github.

- NOTE: Use "git add ." to send all the code to github.
    ```
    git init
    git add .
    git commit -m "first commit"
    git branch -M main
    git remote add origin git@github.com:{{github-username}}/{{repo-name}}.git
    git push -u origin main
    ```

2. Go to the Actions page and select the "Java with Maven" suggested workflow. A workflow template will be created for you, analyze the file and update it to look like:

```yaml
name: Java CI with Maven

on:
  push:
    branches: [ "main" ]
  pull_request:
    branches: [ "main" ]

jobs:
  build:

    runs-on: ubuntu-latest

    steps:
    - uses: actions/checkout@v4
    - name: Set up JDK 21
      uses: actions/setup-java@v4
      with:
        java-version: '21'
        distribution: 'temurin'
        cache: maven
    - name: Build with Maven
      run: mvn -B package --file pom.xml
```

Commit the changes to the main branch (For convenience we will only use the main branch across this exercise). 
    - Use the "Commit changes..." button and commit directly on the main branch.

3. After your commit on the Github UI in the Browser, you can go to the Actions tab and check the result of the build.
    - Check on the workflow file the reason why it ran automatically. (Hint: Check the "on" configuration on the workflow)

4. Pull the code to your machine and add a new step to the workflow to create a new docker image.
    ```bash
        docker build . --file [path_to_dockerfile] --tag academy:$(date +%s)
    ```

5. Also add an extra step to list the docker images and confirm that indeed the docker image was created. Commit and Push the code to be able to see on the Actions the final result.
    - The image size should be more or less the same of the ones you created in your local machine.

## Create a custom action for the maven build step

1. Create a file for it in the following path of your repository ".github/actions/maven_build/action.yml". You can follow the template on the "action.yaml" file in the resources for this exercise.

2. Call the new action from the workflow:
    - To reference the new action created on the workflow please add the property "uses" on the github action step.

        > -uses: {{github-username}}/{{github-repository-name}}/.github/actions/maven_build@main

    - Normally the path can be defined with a relative path from the root since the action is defined in the same repository of the workflow. But, for our case, since we are going to use Act in a few steps, this will help.

3. Update the action with the necessary changes to run the maven build.

4. Push the code and confirm that the image is built the same way as before. Check the "Actions" tab on Github.

## Create a custom action for the docker build step

1. Create a file for it in the following path of your repository ".github/actions/docker_build/action.yml". You can follow the template on the "action.yaml" file in the resources for this exercise.

2. Call the new action from the workflow:
    - To reference the new action created on the workflow please add the property "uses" on the github action step.

        > -uses: {{github-username}}/{{github-repository-name}}/.github/actions/docker_build@main

    - Normally the path can be defined with a relative path from the root since the action is defined in the same repository of the workflow. But, for our case, since we are going to use Act in a few steps, this will help.

3. Update the action with the necessary changes to run the docker build.

4. Push the code and confirm that the image is built the same way as before. Check the "Actions" tab on Github.

## Improve the docker build custom action

1. To enhance the flexibility of our custom action and enable it to handle various use cases beyond just building the image for our project, we need to add the following inputs on the action file:
    - path_dockerfile: This allows us to locate the correct Dockerfile, even if it has a different name or is located in a different directory. (already define in the example)
    - image_name: Specifies the name for the generated Docker image.
    - tag_name: Specifies the tag to be added to the final image.

2. Adapt the code to use the input variables to build the docker image with the following requirements:
    - Unix timestamp needs to be used if the input tag is not sent.
        ```
        if [ -z "${{ inputs.{{input-name}} }}" ]; then
            echo "TAG=$(date +%s)" >> $GITHUB_ENV
        else
            echo "TAG=${{ inputs.{{input-name}} }}" >> $GITHUB_ENV
        fi
        ```
    - The path needs to use "Dockerfile" as the default value
    - The full name of the image with the tag needs to be exported to the Github Environment variable "DOCKER_IMAGE_NAME"
        ``` 
        echo "DOCKER_IMAGE_NAME=$NAME" >> $GITHUB_ENV 
        ```

3. Test calling the new function from the workflow using different parameters. Declare the input parameters like this:
    ```
    with:
        {{ input-name }}: {{ input-value }} 
    ```

## Login and Push the image to Docker Hub

1. Let's create a new step on our workflow to use the action provided by Docker to login on Docker (Use the step in the first example on the Usage section):
    - https://github.com/docker/login-action

2. As you can see, the action just needs two variables, the username and password. We are going to pass these two values to the action from the Github Secrets.
    - Go to your profile on Docker Hub (use the same account that you used on Docker Desktop) and retrieve your username. In the Docker Hub settings of your account generate an Access Token and save it somewhere on your machine.
    - Go to the Settings on your Git Hub repository and add two repository secrets on the "Actions secrets and variables".
        - You can use the same names you see in the example in the step 1.
        - DOCKERHUB_USERNAME
        - DOCKERHUB_TOKEN

3. Now that we can login into our Docker account, we can build a new custom image to push the Docker image we built in the previous custom action. Create a new custom action using the same type of directories equal to "docker_build" but now for this new push action. In the new action file make sure to:
    - Have 2 inputs, one for the Docker Username and another one of the Image Name generated.
    - The code should tag the current image with the docker username to be able to send to Docker Hub. And then push the image.

5. Commit the code and after you run it sucessfully, go to DockerHub to confirm that your new image was uploaded.

## Running Quarkus Tests

1. First, we will need to have Java available on the workflow and, until now, besides the custom action we just checkout the code. Add now a new step that uses the action "setup-java" to setup java with the necessary Java version and distribution (use the same ones as we did before in the exercices)
    - Check https://github.com/actions/setup-java and use the values:
        - distribution: corretto
        - java-version: 17

2. If you remember, we will need a PostgreSQL instance so that the tests can run correctly. Github provides the necessary documentation to create a PostgreSQL "service container" on the workflow. Edit the workflow and add the necessary code to the workflow.
    - Use the example "services:" that is mentioned in the first example here -> https://docs.github.com/en/actions/using-containerized-services/creating-postgresql-service-containers
    - DO NOT FORGET to configure the PostgreSQL with the right name and environment variables to be accessible from the service.

3. Now that we have Java and our Database ready and exposed at localhost, we can create a new step in the workflow to run the mvn test command. No need to create a custom action for this direct command but you can still do it. Place this step before the Docker steps since we do not want to build the docker image if the tests fail.

## Running Manually the Workflow on Github

1. If you go to our workflow on the Actions tab you will see that there is no button to Run the Job. This happens if the template you are using only has "on: push:" and "on: pull_request" which makes these two events the only triggers. To avoid this, we need to add another event to trigger the workflow, in this case just add an empty "workflow_dispatch".

    ```
    on:
    workflow_dispatch:
    ```

2. Commit and after you push the code you should be able to run manually on Github.
