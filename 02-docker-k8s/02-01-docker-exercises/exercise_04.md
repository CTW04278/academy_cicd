# Multi-Stage

1. Use the base code inside the folder "exercise_04_sample_project".
2. Let's create a new Dockerfile called "multi-stage-build". Use as based the Dockerfile already present in the directory and do the following changes:
    - Create a new stage before the entrypoint using the image "scratch" (Use Dockerhub to learn about this image usaged)
    - Copy the bin files inside the folders "/bin/client" and "/bin/server" to "/bin/" (Hint: You need to specify that the files are coming from the previous stage)
3. Build a new image with this new Dockerfile and compare to the size of the previous one.

# Multi-Stage parallelism

1. Now to show another capability of the multi-stage in docker we will run 2 stages in parallel. This can be very useful in big projects where different parts can run in parallel and don't need an output from eachother. Separate the two "RUN go build" in different stages and make sure that the FROM command is mentioning the name of the first stage, if you didn't name the first stage yet, please do it.
    - Hint: You might need to update the Copy commands to retrieve from the correct stage

2. Run docker build again and see both stages in parallel. You might need to do "docker build --no-cache ..." to better see it.