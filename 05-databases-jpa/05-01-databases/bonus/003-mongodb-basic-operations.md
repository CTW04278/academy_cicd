# MongoDB try out some basic operations

1. Select all

```
db.workstation_rack_bookings.find( { } )
```

2. Filter bookings to Peter Parker

```
db.workstation_rack_bookings.find( { "requester": { ctwId: "CTW99999", "name": "Peter Parker" } } )
```

3. Filter bookings to Tony Stark

```
db.workstation_rack_bookings.find( { "requester.name": "Tony Stark" } )
```

4. Filtering by date, list all bookings after 12/07/2024

```
db.workstation_rack_bookings.find({ book_from: { $gt: ISODate("2024-07-12") }, })
```

5. Remove bookings to Tony Stark

```
db.workstation_rack_bookings.deleteOne( { requester: { ctwId: "CTW99998", name: "Tony Stark" } } )
```

6. Check if it has really gone

```
db.workstation_rack_bookings.find( { requester: { ctwId: "CTW99998", name: "Tony Stark" } } )
```

7. Filtering by date, list all bookings before 12/07/2024

```
db.workstation_rack_bookings.find({ released: { $lt: ISODate("2024-07-12") }, })
```