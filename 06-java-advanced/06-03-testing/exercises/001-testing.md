# Test your code

At this momenent you should be able to test your endpoints. As mention you'll use JUnit and Rest-Assured.


## Testing endpoints

When you created the application it was created a "GreetingResourceTest" class that contains one example on how to test an endpoint:

```java

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class GreetingResourceTest {
    @Test
    void testHelloEndpoint() {
        given()
          .when().get("/workstation/hello")
          .then()
             .statusCode(200)
             .body(is("Hello from Quarkus REST"));
    }

}
```

You can find examples in Quarkus documentation [here](https://quarkus.io/guides/getting-started-testing#testing-a-specific-endpoint).

## Exercise

- Add tests for every endpoint of the application. You should validate response status code, response body and response headers. Look for positive and negative scenarios.

    - You should do an integration test, not unitary, nothing should be mocked.

- Inspect your code, focus on the business logic and implement unit tests to validate the flow, every external dependency should be mocked.