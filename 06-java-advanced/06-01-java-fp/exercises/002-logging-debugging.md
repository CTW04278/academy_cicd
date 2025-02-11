# BRANCH: steam-log-debug-test

# Logging & Debugging

## Logging
### Creating a Logger

There are 3 ways of creating a logger. Let's try to use all of them.

#### 1. Declaring a Logger Field

```
import org.jboss.logging.Logger;

public class MyService {
    private static final Logger log = Logger.getLogger(MyService.class); 

    public void doSomething() {
        log.info("CTW Academy rocks!"); 
    }
}
```
#### 2. Simplified logging with Quarkus
```
import io.quarkus.logging.Log; 

class MyService { 
    public void doSomething() {
        Log.info("So Simple!"); 
    }
}
```
#### 3. Injecting a Logger
```
import org.jboss.logging.Logger;

@ApplicationScoped
class MyService {

   @Inject
   Logger log; 

   @LoggerName("foo")
   Logger fooLog; 

   public void ping() {
     log.info("Inject is so simple!");
     fooLog.info("Goes to _foo_ logger!");
   }
}
```

### Logging levels

For every level there's a corresponding method with the same name. e.g.
`log.info`,
`log.debug`,
`log.error`

Analyse the code and add log entries with different levels. All of them are being logged?

### Logging categories

Logging is configured on a per-category basis, with each category being configured independently. Configuration for a category applies recursively to all subcategories unless there is a more specific subcategory configuration.

#### Root Logger Category
The parent of all logging categories is called the "root category."
Add the following properties to the `application.properties` (or yaml, yml).

| Property Name           | Default Value   | Description                                           |
|-------------------------|-----------------|-------------------------------------------------------|
| `quarkus.log.level`     | INFO            | The default log level for every log category.         |
| `quarkus.log.min-level` | DEBUG           | The default minimum log level for every log category. |

Change the levels for each configuration, interact with the application and observe the logs.

#### Specific categories

Modify the log level for different packages within the application by adding the following configuration property:

`quarkus.log.category."<category-name>".level` - The level to use to configure the category named <category-name>. The quotes are necessary.

e.g.: `quarkus.log.category."com.bmw.ctw.workstation.rack.api".level=DEBUG`

You can also define log categories for dependencies. e.g.: 
`quarkus.log.category."org.hibernate".level=DEBUG`

### Formatting the logged message

You can create the logging messages by manipulating the String. e.g.
using string concatenation or `"my string".formatted` method.

JBoss Logging provides handful methods to simplify the format. e.g.

`Log.infof("Request for user with id: %s", userId)`

`Log.infov("Request for user with id: {0}", userId)`

Improve your logging messages by using these methods. Which one is better?

### Logging Format

Quarkus generates human-readable text logs by default. It uses the following format to achieve that:

`%d{yyyy-MM-dd HH:mm:ss,SSS} %-5p [%c{3.}] (%t) %s%e%n`

Use the table below to play around with the format and create one by yourself.
For the console handler, the property is `quarkus.log.console.format`.

The logging format string supports the following symbols:

| Symbol                       | Summary                           | Description                                                                                                                     |
|------------------------------|-----------------------------------|---------------------------------------------------------------------------------------------------------------------------------|
| `%%`                         | `%`                               | Renders a simple `%` character.                                                                                                 |
| `%c`                         | Category                          | Renders the category name.                                                                                                      |
| `%C`                         | Source class                      | Renders the source class name.                                                                                                  |
| `%d{xxx}`                    | Date                              | Renders a date with the given date format string, which uses the syntax defined by `java.text.SimpleDateFormat`.                |
| `%e`                         | Exception                         | Renders the thrown exception, if any.                                                                                           |
| `%F`                         | Source file                       | Renders the source file name.                                                                                                   |
| `%h`                         | Host name                         | Renders the system simple host name.                                                                                            |
| `%H`                         | Qualified host name               | Renders the system’s fully qualified host name, which might be the same as the simple host name, depending on OS configuration. |
| `%i`                         | Process ID                        | Render the current process PID.                                                                                                 |
| `%l`                         | Source location                   | Renders the source location information, which includes source file name, line number, class name, and method name.             |
| `%L`                         | Source line                       | Renders the source line number.                                                                                                 |
| `%m`                         | Full Message                      | Renders the log message plus exception (if any).                                                                                |
| `%M`                         | Source method                     | Renders the source method name.                                                                                                 |
| `%n`                         | Newline                           | Renders the platform-specific line separator string.                                                                            |
| `%N`                         | Process name                      | Render the name of the current process.                                                                                         |
| `%p`                         | Level                             | Render the log level of the message.                                                                                            |
| `%r`                         | Relative time                     | Render the time in milliseconds since the start of the application log.                                                         |
| `%s`                         | Simple message                    | Renders just the log message, with no exception trace.                                                                          |
| `%t`                         | Thread name                       | Render the thread name.                                                                                                         |
| `%t{id}`                     | Thread ID                         | Render the thread ID.                                                                                                           |
| `%z{<zone name>}`            | Time zone                         | Set the time zone of the output to `<zone name>`.                                                                               |
| `%X{<MDC property name>}`    | Mapped Diagnostic Context Value   | Renders the value from Mapped Diagnostic Context.                                                                               |
| `%X`                         | Mapped Diagnostic Context Values  | Renders all the values from Mapped Diagnostic Context in format `{property.key=property.value}`.                                |
| `%x`                         | Nested Diagnostics context values | Renders all the values from Nested Diagnostics Context in format `{value1.value2}`.                                             |

### JSON Logging Format

Add this extension to your build file:

```
<dependency>
    <groupId>io.quarkus</groupId>
    <artifactId>quarkus-logging-json</artifactId>
</dependency>
```

Build the application again and run it. Observe the logs.

Make it pretty by adding the following configuration property:

`quarkus.log.console.json.pretty-print=true`

In case you want to disable it, use the following property:

`quarkus.log.console.json=false`

### Adding contextual information at runtime 

Mapped Diagnostic Context (MDC) is a logging feature provided by logging frameworks that allows you to dynamically associate contextual information (key-value pairs) with log messages. This information is typically specific to the thread that is generating the log messages and can be used to enrich logs with additional, structured context that is useful for debugging, tracking, or filtering logs.
This allows developers to associate specific metadata with log entries, such as:
- User ID
- Transaction ID
- Session information
- Correlation IDs for distributed tracing

```
import io.quarkus.logging.Log;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.jboss.logmanager.MDC;

import java.util.UUID;

@Path("/hello")
public class GreetingResource {

    @GET
    @Path("/test")
    public String greeting() {
        MDC.put("request.id", UUID.randomUUID().toString());
        MDC.put("request.path", "/hello/test");
        Log.info("request received");
        return "hello world!";
    }
}
```

Add this feature to your logs and interact with the application. Can you see the information added by the MDC being logged?

### Final logging exercise

Add log entries to the application by applying all the concepts and best practices discussed in the module.

Homework :)
- How to handle sensitive information globally?
- How to mitigate [Log Injection](https://owasp.org/www-community/attacks/Log_Injection)?

## Debugging

<ol>
<li>Set a breakpoint in one of the resources</li>
<li>Run the application on debug mode</li>
<li>Make a request to the resource that has the breakpoint</li>
<li>Return to IntelliJ and check the information in the debugger tab</li>
<ol>
<li>What happens if you perform a new request?</li>
</ol>
<li>Right-click on the breakpoint and select "Thread" and click on "Done"</li>
<ol>
<li>What happens if you perform a new request?</li>
</ol>
<li>Right-click on the breakpoint and add a breakpoint condition in the "condition" input box</li>
<ol>
<li>What happens if you perform a new request?</li>
<li>What happens if you perform a new request that matched the condition?</li>
</ol>
</ol>