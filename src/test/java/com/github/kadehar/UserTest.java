package com.github.kadehar;

import com.github.kadehar.lombok.LombokUserData;
import com.github.kadehar.models.UserData;
import org.junit.jupiter.api.Test;

import static com.github.kadehar.Specs.request;
import static com.github.kadehar.Specs.responseSpec;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {
    @Test
    void singleUser() {
        // @formatter:off
        given()
            .spec(request)
        .when()
            .get("/users/2")
        .then()
            .spec(responseSpec)
            .log().body();
        // @formatter:on
    }

    @Test
    void listOfUsers() {
        // @formatter:off
        given()
            .spec(request)
        .when()
            .get("/users?page=2")
        .then()
            .log().body();
        // @formatter:on
    }

    @Test
    void singleUserWithModel() {
        // @formatter:off
        UserData data = given()
                            .spec(request)
                        .when()
                            .get("/users/2")
                        .then()
                            .spec(responseSpec)
                            .log().body()
                            .extract().as(UserData.class);
        // @formatter:on
        assertEquals(2, data.getData().getId());
    }

    @Test
    void singleUserWithLombokModel() {
        // @formatter:off
        LombokUserData data = given()
                                    .spec(request)
                              .when()
                                    .get("/users/2")
                              .then()
                                    .spec(responseSpec)
                                    .log().body()
                                    .extract().as(LombokUserData.class);
        // @formatter:on
        assertEquals(2, data.getUser().getId());
    }
}
