## Deployment

1. Create a yaml file and use the code in this URL:
    - https://raw.githubusercontent.com/kubernetes/website/main/content/en/examples/application/nginx-app.yaml (You can also check the resources folder of this exercise)
    - This will help you create a Deployment and a Service to target the pods of your Deployment. Let’s make some changes to the file. 
2. Edit both resources to have the namespace defined as “academy”
3. Apply and confirm how many pods are being deployed on Kubernetes
4. Change the deployment manually on the cluster using the "kubectl edit" command and change it to only have 2 replicas.
    - > kubectl edit deployment {{resource-deployment-name}} -n academy
5. Confirm that now you only have 2 pods and make the change permanent by changing the yaml file.
6. Change the Service configuration to start using different values for the port that is exposed and the port that is targeted in the pods of the selector label. 
    - For this, in the Service declaration, change the port value to 8081 and add a new key called “targetPort” under the “port”.
    - The "targetPort" needs to use the same value as the port value that is being exposed in the Deployment declaration.
7. Now try to access the localhost:8081 and see the default page of nginx.
8. Open two terminals, in each of the terminals see the logs of one of the pods of your deployment with the stream option.
9. Open localhost:8081 on a normal tab on your browser and in one incognito tab of your browser. Refresh both pages and see in the logs if both pods are being used to manage the traffic between different clients (Normal Tab vs Incognito Tab).
10. Let’s take a look at the consumption metrics for the memory and cpu usage.
11. Let’s start by installing the metrics server with the command below taken from https://github.com/kubernetes-sigs/metrics-server 
    - kubectl apply -f https://github.com/kubernetes-sigs/metrics-server/releases/latest/download/components.yaml
12. Run the command ”kubectl top pods -n academy” to see the current CPU and Memory usage. If you have the following error  “Error from server (ServiceUnavailable): the server is currently unable to handle the request (get pods.metrics.k8s.io)” or “error: Metrics API not available”, please edit the deployment ”metrics-server” on the "kube-system" namespace and add the additional arg ” --kubelet-insecure-tls=true” to the container. Try to run the command again. (Wait for the pods to be running)
