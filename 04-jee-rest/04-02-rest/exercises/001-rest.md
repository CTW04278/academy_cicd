# REST API

1. Create a rest api that adds a Rack to the list. Below you can check a request and response example.

```
REQUEST:
   URL: http://localhost:8080/workstation/rack
   VERB: POST
   BODY:
      {
         "serialNumber": "1000-12021-01",
         "status": "Active",
         "teamId": 1
      }

RESPONSE:
   STATUS: 201 CREATED
   BODY:
      {
         "id": "32146",
         "serialNumber": "1000-12021-01",
         "status": "Active",
         "teamId": 1
      }
```

2. Create a rest api that adds a Team to the list. Below you can check a request and response example.

```
REQUEST:
   URL: http://localhost:8080/workstation/team/
   VERB: POST
   BODY:
      {
         "name": "Transformers",
         "product": "Omniverse"
      }

RESPONSE:
   STATUS: 201 CREATED
   BODY:
      {
         "id": "323",
         "name": "Transformers",
         "product": "Omniverse"
      }
```

3. Create a rest api that adds a Booking to the list. Below you can check a request and response example.

```
REQUEST:
   URL: http://localhost:8080/workstation/booking/
   VERB: POST
   BODY:
      {
         "rackId": "23",
         "requesterId": "1",
         "bookFrom": "2016-03-16T13:00:00.492",
         "bookTo": "2016-03-17T14:00:00.000"
      }

RESPONSE:
   STATUS: 201 CREATED
   BODY:
      {
         "id": "59",
         "rackId": "23",
         "requesterId": "1",
         "bookFrom": "2016-03-16T13:00:00.492",
         "bookTo": "2016-03-17T14:00:00.000"
      }
```

4. Create a rest api that retrieves a list of all racks. Below you can check a request and response example.

```
REQUEST:
   URL: http://localhost:8080/workstation/rack/
   VERB: GET
RESPONSE:
   STATUS: 200 OK
   BODY:
      {
         "racks": [
            {
               "id": "32146",
               "serialNumber": "1000-12021-01",
               "status": "Active",
               "teamId": 1
            },
            {
               "id": "32147",
               "serialNumber": "1000-12021-02",
               "status": "Active",
               "teamId": 3
            },
            {
               "id": "32148",
               "serialNumber": "1000-12021-03",
               "status": "Active",
               "teamId": 2
            }
         ]
      }
```

4. Create a rest api that retrieves a rack by id. Below you can check a request and response example.

```
REQUEST:
   URL: http://localhost:8080/workstation/rack/32148
   VERB: GET
RESPONSE:
   STATUS: 200 OK
   BODY:
      {
         "id": "32148",
         "serialNumber": "1000-12021-03",
         "status": "Active",
         "teamId": 2
      }
```

5. Create a rest api that updates a rack by id.

6. Create a rest api that deletes a rack by id.

## Optional Exercise:

1. Add a query parameter to the list api that returns all racks of a specific status. Below you can check a request
   and response example.

```
REQUEST:
   URL: http://localhost:8080/workstation/rack/racks?status=active
   VERB: GET
RESPONSE:
   STATUS: 200 OK
   BODY:
      {
         "vehicles": [
            {
               "id": "32148",
               "serialNumber": "1000-12021-03",
               "status": "Active",
               "teamId": 2
            }
         ]
      }
```

2. Create a rest api for the remaining crud operations for all entities.
