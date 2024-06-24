package com.ctw.stands.controller;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class StandsControllerTest {
    @Test
    void testGetAllStands() {
        given()
                .when().get("/stands")
                .then()
                .statusCode(200);
    }
}
