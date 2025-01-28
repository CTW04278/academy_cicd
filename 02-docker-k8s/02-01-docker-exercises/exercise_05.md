# Hollywood

1. Create the following Dockerfile: 
    - Using “debian:latest” as a base image in the ”FROM” step.
    - Set the environment variable in the Dockerfile “DEBIAN_FRONTEND” with the necessary value to guarantee that no interaction will be asked during the execution of the apt-get. (Hint: This is a apt-get variable, search on the web for the correct value)
    - Run commands that:
        - Run the update command of apt-get
        - Installs the package ”hollywood” using apt-get 
2. Try to build Dockerfile
3. The Dockerfile build will fail because it needs an approve from you.
    - Add the right flag to apt-get to require no input during the installation.
4. Add an extra command to delete all the files inside the folder /var/lib/apt/lists since this will allow to reduce the size of the image by deleting all the cache files of the apt-get command. (This is already done automatically by the base image (debian) but for transparency we will still add it) 
5. Define the entrypoint as the execution of the package. (Hint: It’s the package name you've installed)
6. Build the image by running the “docker build” command inside the same folder of the Dockerfile.
7. Now run the image and start to hack NASA?! (Hint: You need to run it with the flag "-it" to enable an interactive session)
8. To leave the container spam "Ctrl+c" and if needed write "exit" to leave the container.