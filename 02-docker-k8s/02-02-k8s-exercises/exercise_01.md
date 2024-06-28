## Pod

1. List all namespaces in your Kubernetes. (Hint: use “kubectl get {{resource-name}}” command)
2. Create a yaml file and use the code in this URL -> https://raw.githubusercontent.com/kubernetes/website/main/content/en/examples/pods/simple-pod.yaml (If the URL does not work, check the resources folder of this exercise)
3. Apply the resource on your Kubernetes cluster. (Hint: use “kubectl apply -f {{file-name}}”)
4. List all pods in the default namespace (Hint: For the default namespace, you don’t need to define the namespace in the command).
5. Create a namespace called “academy”.
6. Apply again the file that you downloaded but now in the namespace ”academy”.
7. List all pods in the “default” namespace, then in the “academy” namespace. Finally, list all pods in all namespaces (Hint: You have a command option for this).
8. Now let’s apply again the pod we downloaded but this time we don’t want to have to specify the namespace everytime in the kubectl command. Edit the file you downloaded to add a namespace with the value “academy” inside the metadata yaml object and, to better distinguish our new pod, change the name to “nginx-academy”. Apply the resource without the namespace option in the kubectl command.
9. List all pods in the namespace “academy” and confirm that you see the pod there.
10. Now, everytime we do any work on this pod, don’t forget to include the namespace in the kubectl command to make sure the execution is going to happen in the “academy” namespace scope.
11. Now, let’s use the command “kubectl port-forward” to be able to expose a port of a pod to your localhost. The command should look like this:
    - > kubectl port-forward pod/{{pod-name}} -n academy {{port-to-access-on-browser}}:{{exposed-port-by-pod}}
    - For the pod port, check the yaml file to see which port is being exposed by the container.
12. Access in the browser http://localhost:<port-localhost>/ and you should be able to see a default page for the nginx image.
13. Without closing the connection of the port-forward. Open a new terminal and take a look at the logs of the container (Hint: “kubectl logs <pod-name>”). Use an additional option that opens a stream for the logs so that you can see them appearing live. When you have both the stream of logs open and the port-forward running on the other terminal, refresh your browser to see if in fact the calls are being logged on the pod.
