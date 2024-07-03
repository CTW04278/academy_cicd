# Simple Commands

1. Docker run two containers with nginx image:
    - Pull both images locally first using “docker pull”
    - Use 2 different images of “nginx” (Use latest version and the last specific version like eg. 1.23). 
    - Expose them using different ports.
    - Run only one of the images in detached mode 
    - Use flag “--name” to give containers names, that way you can use the names on the other docker commands
2. Access both of them in the browser using localhost:port with the port you chosen in the command above
3. Close the connection to the container not running in detach mode. Use “docker ps” to check you can still find the other container running and use “docker ps -a” to see also the stopped containers.
4. Stop the still running container. Start again both containers and verify that they are again working. Stop them and remove both containers.
5. Delete image of nginx that is using the latest version and keep the other one for the next exercise.
