# Kubernetes Exercise

## Create Deployment for our App

1. Use as base project the final exercise from the Docker class. Create a "kubernetes" folder where you will store all the Kubernetes files. Create a deployment.yaml file that will deploy the application to the Kubernetes cluster. You can use as a template the example given by kubernetes on their website -> https://kubernetes.io/docs/concepts/workloads/controllers/deployment/#creating-a-deployment (You can also check the resources folder of this exercise) and also take the opportunity to review the documentation to see which is included on the example.

2. The deployment file should include two containers, one for the app and another for the database. The database container should use the official postgres image. The app container should use the image created in the previous exercise:
    - You will need to configure the app container to not pull the container image, if you let it do it, the pod will break because it won't find the image on the DockerHub so, if you want to use the local image on your Docker, you need to put the configuration different from "Always" (Check all the options)
        - Add "imagePullPolicy: IfNotPresent" to the application container declaration.
    - Don't forget to expose the same ports and environment variables you did on the Docker exercise.
        - "8080" for application
        - "5432" for database
    - Edit the name and the label. Remember to always do this for all the kubernetes resources in this exercises.
        - You can use "academy" for the labels (all of them) and "academy-deployment" for the deployment name.

3. You can apply the deployment yaml with kubectl and target the "academy" namespace. If the namespace does not exist, please create it and apply the deployment again.

4. If you see the Pod (always using the "academy" namespace in the kubectl command) in a error state please look at the logs. If you see an error similar to "java.net.UnknownHostException: postgres" it might be because it cannot reach the postgres database by the container name since the containers inside the pod use the "localhost". To fix it, please rebuild the image using the correct quarkus profile (if you don't have the quarkus profile logic, please change the database host) to target "localhost:5432". Then, do a rollout restart deployment and they will rerun using the new image you just built. (If instead of using the same image name, you build with a new name and edited the deployment, you don't need to force a restart because it will be done automatically)
    - If you build the image again with the same tag. Run the command "kubectl rollout restart deployment -n academy academy-deployment" to force new pods to use the new image version.
    - If you build the image with a new tag. Please edit the deployment.yaml to use the new image/tag combination and it will launch the new pods automatically.

## Expose our app

1. Change the deployment to have 2 replicas, if it is using another number, and apply it again with kubectl apply -f command.

2. Now you should have 2 pods for our app. We want to do a "port-forwarding" of both pods so that we can access them on the Browser. Use "kubectl port-forward -h" on the command line to understand how you can use the command. Open two terminals and do a port-forward for each pod, you can either choose two different ports or just let kubectl choose a random port locally.
    - Execute in two different terminals:
    - > kubectl port-forward pod/{{first-pod-name}} -nacademy 8081:8080
    - > kubectl port-forward pod/{{second-pod-name}}  -nacademy 8082:8080

3. Access the two localhost address in the browser and make some requests. As you will understand, the cars created will be diferent on both because each pod has it's own database inside.


## Create a Kubernetes Service to access our Pods

1. We will create a Kubernetes Service so that we don't have to access the pods individually and this can be managed by Kubernetes. For this, we will create a new file service.yaml. Once again, you can use the example of Kubernetes on their website -> https://kubernetes.io/docs/concepts/services-networking/service/#defining-a-service (You can also check the resources folder of this exercise) (HINT: Be careful to use the exact same label and the corresponding value you used on your template pod)

2. The service should target our pods using the same ports we have been using to access them. The type should be "LoadBalancer" for us to access them in the browser. Apply the service.yaml on the namespace "academy" and do a "kubectl get service -n academy" to see if it was successful and you can see the created service.
    - Under "spec:" add "type: LoadBalancer"
    - Both Ports should use "8080"
    - In the selector, target the label "app" with the same value you gave it on the deployment.

3. Check on the browser you can access the application using localhost and the port you indicated on the yaml configuration. Now, open two terminals and see the logs of each pod, this time use the right flag to stream the logs to the console so that you don't need to always run the command. After you configured the two terminals, go again to the browser to access the application and spam the refresh button, you should see the logs appearing for your requests. (HINT: If you open in incognito mode also, you should also see the logs appearing on the other pod, but this can also not happen due to client/session affinity that causes both Browsers to send traffic to the same pod.)

## Separate Postgres in a different container

1. As you seen, multiple pods increase the scalability and performance of our service but also makes the data inconsistent since each pod has it's own database. To solve this, we will need to separate the 2 containers in different deployments because we can't have multiple databases just because we want to scale the service to have more than 1 pod. 
    - Make sure to use an image of our service in the deployment.yaml that calls the database using a jdbc.url to "{{postgres-container-name}}:5432" since the communication App <-> Database is no longer inside a pod but in the kubernetes network so we won't use the localhost host to communicate.
    - If you are not sure which image is using container name to target the database, please build again a new image of our service.

2. Now you need to edit the deployment.yaml to remove the postgres container
    - Save the container configuration for the next step.

3. Create a deployment file for the postgres pod with 1 replica only and apply it.
    - You can use the container configuration and just paste it on the deployment pod template.

4. Create a service file for postgres pod to make it accessible. Remember this service name needs to be the same name you use on the hostname for the database in the application properties.
    - We will expose the pod through the Service. So the service name should be the "container name" you gave on the jdbc.url in the image you created/used in step 1.

5. Separate the postgres deployment and service in a separated folder called "kubernetes-postgres". Apply both kubernetes folders.

6. The pods should start working between restarts, give them 1 minute. If not, provoke a rollout restart deployment.

## Use Kubernetes Secrets and ConfigMaps to make the App more flexible - Secrets

1. As you could already see having the username/password to access the database in plain text in the application properties files is not a good security measure. Also, having to re-build the image everytime we want to change the application properties is also not very pleasant.

2. Let's start by configuring the secrets. Create a secret.yaml and follow the small template in Kubernetes documentation (you only need the first section, in doubt look at the resources folder of this exercise) -> https://kubernetes.io/docs/concepts/configuration/secret/
    - Inside put both the username and password of our database. Use the same configuration names as it was in the application properties "quarkus.datasource.username" and "quarkus.datasource.password".
    - To generate the base64 please use the command -> "echo -n {{replace-with-value}} | base64"

3. Now you need to add 3 new lines in the application properties. (Hint: Look at the "Configuration Reference" in the guide for the kubernetes-config)
    - Enable the kubernetes-config
        - > quarkus.kubernetes-config.enabled=true
    - Enable the secrets
        - > quarkus.kubernetes-config.secrets.enabled=true
    - Define the secret name property (Do not forget to use the same name you used on the secret.yaml)
        - > quarkus.kubernetes-config.secrets={{secret-resource-name}}
4. Build again the docker image.

5. Apply the secret.yaml and then also apply the deployment to use the new tag of the new built image. You will see that the pods are starting to enter CrashLoopBackOff and if you read the error, you will find the message:
 
> User "system:serviceaccount:academy:default" cannot get resource "secrets" in API group "" in the namespace "academy"

6. In the error above you can read that the service account "default" in the "academy" namespace does not have permission to get to the secrets. So, we will need to create a new Role resource and then use the resource RoleBinding to bind the new role to the default service account.

7. Create a role yaml file and use as a template the "Role example" -> https://kubernetes.io/docs/reference/access-authn-authz/rbac/#role-example (You can also check the resources folder of this exercise)
    - With the following rules
        - apiGroups: [""]
        - resources: ["pods","configmaps","secrets"]
        - verbs: ["list","get","watch"]

8. Create a RoleBinding yaml file and use as a template the "RoleBinding examples" -> https://kubernetes.io/docs/reference/access-authn-authz/rbac/#rolebinding-example (You can also check the resources folder of this exercise)
    - Make sure the role match with the one you created in the step above and the subject should be the ServiceAccount "default" (Hint: See the documentation page)
    - The "subjects" should be configured with:
        - kind: ServiceAccount and name: default

## Use Kubernetes Secrets and ConfigMaps to make the App more flexible - ConfigMaps

1. Now what we want is to move some of the application properties to a ConfigMap, something that is a lot more flexible since it can be changed easily in the Kubernetes without building the all code. 

2. Let's create a simple ConfigMap, you can follow the example here:
    - https://kubernetes.io/docs/concepts/configuration/configmap/#configmaps-and-pods (You can also check the resources folder of this exercise)
    - Where we will add, the "quarkus.datasource.jdbc.url" and the "com.ctw.stands.add.pt.address" properties.
  
3. Apply the configmap in the academy namespace. 

3. Since we already activated the kubernetes-config, we just need to add one configuration property extra that is to set the value for the ConfigMap name that we created (Hint: Look at the "Configuration Reference" in the guide for the kubernetes-config). 
    - > quarkus.kubernetes-config.config-maps={{configmap-resource-name}}
    - Delete the "com.ctw.stands.add.pt.address" property from the application.properties after and build the image with a new tag name.
        - There is no problem in leaving the "quarkus.datasource.jdbc.url" since it still has value with the quarkus profiles and the ConfigMap has priority so it won't affect us.
        - As a side note, we could also leave the "com.ctw.stands.add.pt.address" with the "false" value as a default and then use the ConfigMap to override as we want. Either way it would work the same.

4. Check that after you activate the PT Address it changes the way the application works by always adding " - Portugal" at the end of the address. Try now to change the value again, but now, by editing with the kubectl command the configmap and then force the rollout restart deployment. Side note, you need to restart the pods in order for the to grab the newest version of the configmaps.
    - > kubectl edit configmap -nacademy {{configmap-resource-name}}
    - > kubectl rollout restart deployment -nacademy {{deployment-resource-name}}

# Use Kubernetes Secrets on the PostgreSQL deployment

1. Now let's do the same we just did for the application but also for PostgreSQL. Create a new file in the PostgreSQL folder for the secret where we will store the username and password of the database. Then edit the deployment to use the values from the secrets instead of just having them hardcoded in the deployment.
    - The environment values in the postgres deployment should look like this:
    ```
    - name: POSTGRES_USER
      valueFrom:
        secretKeyRef:
            name: postgres-secret
            key: username
    ```

## Ingress

1. While directly exposing ports with a Service load balancer can suffice for simple setups, utilizing an Ingress offers several key benefits. It will allow us to define a Host so that we don't need to access the service by using localhost and define, if we want, more advanced rules to redirect the traffic. Start by running the command below mentioned in the Quick Start guide of Nginx Ingress to create all necessary resources -> https://kubernetes.github.io/ingress-nginx/deploy/#quick-start

> kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/controller-v1.10.0/deploy/static/provider/cloud/deploy.yaml

2. Now we can create an ingress.yalm file and you can use the template here, you can keep just the first rule as base code -> https://kubernetes.io/docs/concepts/services-networking/ingress/#the-ingress-resource (You can also check the resources folder of this exercise)
    - Use "academy.com" has a host.
    - Use the service name you created previousily and the same port you are using to expose the service and the deployment
    - Set up any path you desired. You can use "/"
    - For the Ingress Class name, check on the kubernetes cluster what was the name of the Ingress class name created in the step 1. You can do that by using a get ingressclass kubectl command on the "nginx-ingress" namespace.
        - > kubectl get ingressclass -n nginx-ingress

3. Now, to be able to access through the browser we need to edit the hosts files with the following line:

    - 127.0.0.1     academy.com

-   Hosts file location:
    - Windows 10 - "C:\Windows\System32\drivers\etc\hosts"
    - Linux - "/etc/hosts"
    - Mac OS X - "/private/etc/hosts"

4. Access the browser using "academy.com" with any additional path in case if you defined it in the configuration yaml.

5. After this, revert the changes on the hosts file to not interfere with any future developments.