# CICD Exercise

## Pre-Requesites

1. Install Act. This tool will allow us to run the Github Actions workflow on our local machine. You can see the official documentation here:
- https://nektosact.com/installation/index.html


2. Create a Github account if you don't have it already. If you prefer to work on new fresh Github account, you can create a throway email in Hotmail or Yahoo Mail. 

    1. Create a new repository called "academy" and make it Public.
    2. Make sure the "Actions" tab is enabled on the Github page.
    3. Generate an SSH Key and configure it on Github to make sure you have the right permissions to Read and Write to the repository

## Import the exercise code and create the first workflow

1. Follow the instructions (They are also below) on the Github repository page to import the code you previously did on the Kubernetes class and create the "main" branch. After this, you should be able to see the base code for our exercise on Github.

- NOTE: Use "git add ." to send all the code to github.
    ```
    git init
    git add .
    git commit -m "first commit"
    git branch -M main
    git remote add origin git@github.com:{{github-username}}/{{repo-name}}.git
    git push -u origin main
    ```

2. Go to the Actions page and select the "Docker Image" suggested workflow. Analyze the file and commit the changes to the main branch (For convenience we will only use the main branch across this exercise). 
    - Use the "Commit changes..." button and commit directly on the main branch.

3. After your commit on the Github UI in the Browser, you can go to the Actions tab and check the result of the build.
    - Check on the workflow file the reason why it ran automatically. (Hint: Check the "on" configuration on the workflow)

4. Pull the code to your machine and change the image name being generated on the docker build command to a value more similar to the ones you were using for the service in the other exercises.

5. Also add an extra step to list the docker images and confirm that indeed the docker image was created. Commit and Push the code to be able to see on the Actions the final result.
    - The image size should be more or less the same of the ones you created in your local machine.

## Create a custom action for the docker build step

1. Create a file for it in the following path of your repository ".github/actions/docker_build/action.yml". You can follow the template on the "action.yaml" file in the resources for this exercise. Please replace the {{}} by the right values.

2. Replicate the same code you had on your workflow but now on the new custom action. And call the new action from the workflow.
    - To reference the new action created on the workflow please add the property "uses" on the github action step.

        > uses: {{github-username}}/{{github-repository}}/.github/actions/docker_build@main
    - Normally the path can be defined with a relative path from the root since the action is defined in the same repository of the workflow. But, for our case, since we are going to use Act in a few steps, this will help.

3. Push the code and confirm that the image is built the same way as before. Check the "Actions" tab on Github.

## Improve the docker build custom action

1. To enhance the flexibility of our custom action and enable it to handle various use cases beyond just building the image for our project, we need to add the following inputs on the action file:
    - path_dockerfile: This allows us to locate the correct Dockerfile, even if it has a different name or is located in a different directory.
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
    - Go to the Settings on your repository and add the two repository secrets on the "Actions secrets and variables".
        - You can use the same names you see in the example in the step 1.

3. Now that we can login into our Docker account, we can build a new custom image to push the Docker image we built in the previous custom action. Create a new custom action using the same type of directories equal to "docker_build" but now for this new push action. In the new action file make sure to:
    - Have 2 inputs, one for the Docker Username and another one of the Image Name generated.
    - The code should tag the current image with the docker username to be able to send to Docker Hub. And then push the image.

5. Commit the code and after you run it sucessfully, go to DockerHub to confirm that your new image was uploaded.

## Running our Quarkus Tests

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

#
# Deploying to the local kubernetes

Now after we are able to test our application and even build and publish the image, we want to deploy it. For this, we want to use our local Kubernetes cluster like we did in the previous class. Unfortunately, we cannot connect from the Github to our local Kubernetes, so we will use the Act CLI tool that you installed in the pre-requesites to be able to run our Github Actions Workflow locally.

We will use this following command as our base to run the github actions:

> act -P ubuntu-latest=-self-hosted

This allows us to run the workflow directly on our host system taking into advantage our already installed tools.

IMPORTANT NOTE: Since the actions are retrieved from the main branch by Act, even though the code is on your local machine, you will NEED TO PUSH the code to the main branch everytime you change an Action to be able to use the most recent code locally using Act.

## Run the github workflow in your local machine

1. Now, on your console, run the base command described above to run the workflow but, for it to work, you will need to give as parameters both the DockerHub username and token like you did in the settings on the Github repository. Instructions:
    - https://nektosact.com/usage/index.html#secrets

    ```
    act -P ubuntu-latest=-self-hosted -s {{dockerhub-username-secret-name}}={{dockerhub-username-secret-value}} -s {{dockerhub-token-secret-name}}={{dockerhub-token-secret-value}}
    ```
NOTE: Replace the {{}}

2. After you are able to run the workflow with Act, go to DockerHub to confirm that indeed the new image is there even though you run the action locally and the quarkus tests are successful.

## Create PostgreSQL container locally for the mvn tests

1. Now, let's assume that we cannot be dependent on a PostgreSQL container already being created for us locally. So we will need to a new step before the "mvn test" command to create a PostgresSQL. Create a new action using the same structure of folders as the last two and configure it to recieve the following parameters:
    - name
    - port
    - username
    - password

2. Add the necessary code to create a docker container with PostgreSQL using the input values. Add extra code to if a container with the same name already exists to first delete it.

3. On the step to run the Quarkus tests we will need to modify the code. We still want to run the "mvn test" command but, if the environment variable ACT is true (meaning the CICD pipeline is running locally) we will call the mvn command but change the quarkus property with the datasource url to target localhost with the port we created in the previous step.

    ```
    if [[ "${ACT}" == "true" ]]; then
        mvn test -D{{Override the JDBC.URL property}}
    else
        mvn test
    fi
    ```

NOTE: If ACT is not true, it means it is running on Github Actions. In that case, we just need to run "mvn test" since it will use the database in the "services:"

4. Before we test this run, we should also add a if to the step declaration to create the PostgreSQL container to make sure the step only runs when the environment variable ACT is true since we don't need this extra container on Github because the "services" we declared in the workflow already cover our needs.
    > if: ${{ !env.ACT }}
    - Curious fact, the "services" declaration would also work locally if we were using a container to run the workflow instead of our own machine.

## Prepare the Kubernertes Resources

1. We now want to make our application more flexible. Where we can easily change the username/password through the CICD pipeline depending on the environment we are deploying, or change the image name on the deployment yaml to deploy the image we just publish in the previous steps. Edit the username/password on both secrets for the application and PostgreSQL so that the value has a placeholder (eg. %DATABASE_USERNAME%) and also edit the image name on the deployment yaml to have a two placeholders for both the repository and the image in the property "image:"

2. Add on the repository secrets in Github the database username and password. DON'T FORGET to also send them both with the same key-value on the act command as well to work locally.

3. Create a new step on the pipeline to replace all placeholders with the values we got from the Github secrets. For this you can use the "sed" command on the files.
    > sed -i'' -e "s/%{{PLACE-HOLDER}}%/${{variable-with-value}}/g" {{path-to-file-from-root}}
    - Replace the {{}}
    - For the secrets, convert them first to base64 before replacing it.
    - HINT: The reason you need two placeholders on the image property is that if you use the "sed" command the "/" will break the command (eg. ctw00010/academy:cicd-project)

4. Run the act command and test your new step. You can add a "cat" command to see the files you just replaced in the end of the step to confirm that the placeholders were replaced. (The secrets might appear as ****)

## Kubernetes Resources ready to be deployed

1. Now that we have the resources with the correct values from our CICD pipeline. We want to apply it on our local Kubernetes cluster. Create a new step to run the kubectly apply command for the resources of PostgreSQL and our Application.

2. The step should have in it's declaration an if to garantee that this only occurrs running locally (use the env.ACT again) since on the Github runner we wouldn't be able to reach our local kubernetes.

3. Make sure you apply first the PostgreSQL folder with all of it's resources inside. Then you will have to implicit wait for the deployment to be ready (Use the code below) and then, we are able to also apply the Application kubernetes resources folder.
    > kubectl wait --for=condition=available deployment/{{postgres-deployment-name}} --timeout=60s -n {{namespace-name}}  

## Transform workflow on a multiple job workflow

1. If you started the exercise using the template that is mentioned on the first step (The "Docker Image" suggested workflow), you will have just one job that is named "build". Basically, we want now to separate the kubernetes deployment on it's job called deploy. Create the new job with the following requirements:
    - It will have to implicitly say that it needs the "build" job
    - The runner is the same as the previous job
    - Besides copying the two steps related with kubernetes, you will also need to start again with the step of the checkout of our code since running a new job is a process on a new machine without the data that we already had.

        ```
        name: Docker Image CI

        on:
            ...

        jobs:

        build:
            ...

        deploy:
            needs: build
            runs-on: ubuntu-latest
            
            steps:
            - uses: actions/checkout@v3
            ....
        ```

2. Try to run it but you will soon realize that we cannot use the environment variables across the jobs. So what is the solution? We need to define an output of our first job to use in this second job. In this case, the variable that we need is the docker image. Remember that this is the output of the step Build Docker Image. Change the step accordingly following this template -> https://docs.github.com/en/actions/using-jobs/defining-outputs-for-jobs#example-defining-outputs-for-a-job . After that, we can use the output on the second job as it is described in the template.

## Ask for authorization to deploy

1. We now want to also ask for authorization to deploy on Kubernetes (NOTE: This will not be reflected on the act running locally and only on Github website). For this, go to the repository settings and create a new environment that has yourself as the required reviewers. Then, use that environment name to add the key "environment" on the deploy job declaration.

    ```
    deploy:
        needs: build
        runs-on: ubuntu-latest
        environment: {{environment-value}}
    ```

3. With this implementation, on the next run, an approval from your side should be necessary to start the second job. Run manually the Github Action on the website (Do not forget to commit the changes after you make them to reflect on the website)

## Add input for the Uppercase Force variable

As a final step for our workflow, we want to add more customization to our CICD pipeline and be able to decide before running the CICD pipeline which value we want for our environment variable.

1. Start by adapting the workflow_dispatch declaration to have a new input, the variable "PriceTaxBoost" and it should be of type "boolean".

2. On the step to replace the variables in the kubernetes resources add a new environment variable that receives the value from the inputs. Example:
    - https://docs.github.com/en/actions/using-workflows/events-that-trigger-workflows#providing-inputs

3. Use the value to replace our variable value on the configmap file by using a placeholder.

4. Even though you can use default values, these inputs only have a value associated when they are started by "workflow_dispatch", in other words, manually. To prevent errors for null variables, we want to check if the variable has value and, if not, assume the default value of "false".

    ```
    if [ -z "${{ environment-variable-name }}" ]; then
        {{ environment-variable-name }}="false"
    fi
    ```

5. You can test the workflow on Github website. To test it using "act", you can create an events file, like the one below and send it using "--eventpath". Note: You will need to use the variable "github.event.inputs" for it to work locally

    ```
    {
    "action": "workflow_dispatch",
    "inputs": {
        "PriceTaxBoost": "true"
    }
    }
    ```

