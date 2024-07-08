# MongoDB insert rows

Let's populate our MongoDB, with some sand we can play better ;)

## Insert some rows

```
db.workstation_rack_bookings.insertMany([
    {
      requester: {
        ctwId: "CTW99999",
        name: "Peter Parker" 
      },
      rack: {
        assetTag: "CTWBMW-0001", 
        serialNumber: "1GYS4HEFXBR215225",
        status: "ACTIVE",
        defaultLocation: "Lisbon",
        teams: [{
          name: "Awesome team",
          product: "Amazing product",
          defaultLocation: "Lisbon",
        }, {
          name: "Outstanding team",
          product: "Espectacular product",
          defaultLocation: "Lisbon",
        }]
      },
      book_from: ISODate("2024-07-07T13:00:00.000Z"),
      book_to: ISODate("2024-07-07T13:30:00.000Z")
    }, {
      requester: {
        ctwId: "CTW99998",
        name: "Tony Stark" 
      },
      rack: {
        assetTag: "CTWBMW-0002", 
        serialNumber: "5FNYF186X5B029952",
        status: "REPAIR",
        defaultLocation: "Lisbon",
        teams: [{
          name: "Marvelous team",
          product: "Great product",
          defaultLocation: "Lisbon",
        }]
      },
      book_from: ISODate("2024-07-07T13:00:00.000Z"),
      book_to: ISODate("2024-07-10T13:30:00.000Z")
    }, {
      requester: {
        ctwId: "CTW99998",
        name: "Tony Stark" 
      },
      rack: {
        assetTag: "CTWBMW-0001", 
        serialNumber: "1GYS4HEFXBR215225",
        status: "ACTIVE",
        defaultLocation: "Lisbon",
        teams: [{
          name: "Awesome team",
          product: "Amazing product",
          defaultLocation: "Lisbon",
        }, {
          name: "Outstanding team",
          product: "Espectacular product",
          defaultLocation: "Lisbon",
        }]
      },
      book_from: ISODate("2024-07-12T13:00:00.000Z"),
      book_to: ISODate("2024-07-13T15:53:47.000Z")
    }, {
      requester: {
        ctwId: "CTW99997",
        name: "Barry Allen" 
      },
      rack: {
        assetTag: "CTWBMW-0015", 
        serialNumber: "KNDJT2A69C7761423",
        status: "OUTDATED",
        defaultLocation: "Porto",
        teams: [{
          name: "Dragon team",
          product: "Unexpectable product",
          defaultLocation: "Porto",
        }]
      },
      book_from: ISODate("2024-07-20T00:00:00.000Z"),
      book_to: ISODate("2024-07-25T23:59:59.999Z")
    }
])
```
