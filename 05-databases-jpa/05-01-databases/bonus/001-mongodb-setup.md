# MongoDB setup

Let's first set our environemnt to start to play around

1. Pull MongoDB community server image

```
docker pull mongodb/mongodb-community-server:latest
```

2. Run a new container using the previous image pulled down

```
docker run --name mongodb -p 27017:27017 -d mongodb/mongodb-community-server:latest
```

3. Check whether the MongoDB container is up and running

```
docker container ls
```

4. Jump into the container to check MongoDB from inside

```
docker exec -it $(docker container ls --all --quiet --filter "name=mongodb") bash
```

5. Run the command inside the container to connect to MongoDB server

```
mongosh --port 27017
```

6. Check the database health with command below

```
db.runCommand(
   {
      hello: 1
   }
)
```

The outcome should be something similar to that:

```
{
  isWritablePrimary: true,
  topologyVersion: {
    processId: ObjectId('668c30721258ec15298107cb'),
    counter: Long('0')
  },
  maxBsonObjectSize: 16777216,
  maxMessageSizeBytes: 48000000,
  maxWriteBatchSize: 100000,
  localTime: ISODate('2024-07-08T18:39:14.426Z'),
  logicalSessionTimeoutMinutes: 30,
  connectionId: 8,
  minWireVersion: 0,
  maxWireVersion: 21,
  readOnly: false,
  ok: 1
}
```
