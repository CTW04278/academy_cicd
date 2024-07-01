# Docker Hub

1. Let's use the image that you built before for the package "hollywood" and create a repository on DockerHub https://hub.docker.com/repositories/ (can be private or) with the "hollywood" name.
2. Before pushing the image, you need to tag it with the repository name you want to push it to. Syntax: docker tag {{local_image_name}}:{{tag}} {{docker_hub_username}}/{{repository_name}}:{{tag}}
3. Push the image and check on DockerHub that it worked. The image value to use on the push command is the following: {{docker_hub_username}}/{{repository_name}}:{{tag}}