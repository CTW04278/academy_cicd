# Logging & Debugging

1. Add log line that writes the following line when a new booking is created:

```
2022-06-30 22:03:31,437 INFO  [com.ctw.wor.res.BookingResource] (executor-thread-198) [createBooking] resquester=Pedro rack=123455 from=12.03.2023 to=14.03.2023
```

2. Add a log line that writes the following line when you get a the list of all racks:

```
2022-06-30 22:07:32,950 INFO  [com.ctw.wor.res.RackResource] (executor-thread-198) [getRacks] location=null
```

3. Add a log line that writes the following line when you get the list of all racks with location Porto:

```
2022-06-30 22:08:57,731 INFO  [com.ctw.wor.res.RackResource] (executor-thread-198) [getRack] location=PORTO
```

4. Add a log line that writes the following line when you get a Team by id:

```
2022-06-30 22:10:12,022 INFO  [com.ctw.wor.res.TeamResource] (executor-thread-198) [getTeam] id=ee97288c-93fe-40e3-96ab-6cc28a0b2d60
```

5. Add a log line that writes the log below whenever you try to create any type of object with invalid parameters:

```
2022-06-30 22:11:45,126 SEVERE [com.ctw.wor.ent.<Object>] (executor-thread-198) [validateObject] errorMessage=The serial number lenght must be between 6 and 7
```

6. Change the log format not to have the thread information and to have the complete class name and the hostname. Below
   you can check an exemple:

```
2022-06-30 22:24:58,692 SEVERE [ctw00009] [package com.ctw.workstation.entity.<object>] [validateObject] errorMessage=The serial number lenght must be between 6 and 7
```
