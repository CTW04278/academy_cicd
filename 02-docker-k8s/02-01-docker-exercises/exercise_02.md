# Nginx Port Configuration

1. Run a detached container of nginx, use a local image, exposing the port 80 of the container on our port 8080. (Hint: If you don’t have any local image, just grab any specific vesion of nginx from DockerHub). Verify that the service is running on localhost:8080.
2. Start a bash shell inside the pod by doing a docker exec with the command “bash”.
3. We are going to need to edit a file inside the container, verify if “vim” is installed, if not, follow the next commands:
    - apt-get update
    - apt-get install vim
4. Edit the file “/etc/nginx/conf.d/default.conf” where it is possible to find some nginx configuration. Our goal here is to change the nginx to listen on port 81 instead of port 80. Save the file.
5. Now if we refresh the localhost on the browser we see that the service is still working even though we changed the listen port. Check with docker ps that indeed the docker is still targeting the port 80 on the container (You can do this in another console or leave the bash of the container)
6. This happens because we need to restart the nginx server. We can do this by running “nginx –s reload” inside of the container. Refresh the browser and confirm it is not working anymore.
7. Now, to expose a new port, we need to recreate a container but, we do not want to loose our changes on the container. For this, we can create a new image based on our current container. Use the ”docker commit” command and call the new image [last_container_name]-updated
8. Docker run the new image but now targeting the new port 81 that we changed in the nginx configuration file.
9. Check on the browser that indeed we can access the nginx default page again.
