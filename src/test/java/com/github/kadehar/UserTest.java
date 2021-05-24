package com.github.kadehar;

import com.github.kadehar.lombok.User;
import com.github.kadehar.models.UserData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {
    @Test
    void singleUser() {
        Specs.request
                .when()
                .get("/users/2")
                .then()
                .spec(Specs.responseSpec)
                .log().body();
    }

    @Test
    void listOfUsers() {
        Specs.request
                .when()
                .get("/users?page=2")
                .then()
                .log().body();
    }

    @Test
    void singleUserWithModel() {
        UserData data = Specs.request
                .when()
                .get("/users/2")
                .then()
                .spec(Specs.responseSpec)
                .log().body()
                .extract().as(UserData.class);

        assertEquals(3, data.getData().getId());
    }

    @Test
    void singleUserWithLombokModel() {
        com.github.kadehar.lombok.UserData data = Specs.request
                .when()
                .get("/users/2")
                .then()
                .spec(Specs.responseSpec)
                .log().body()
                .extract().as(com.github.kadehar.lombok.UserData.class);

        User user = User.builder().id(2).email("").build();

        assertEquals(3, data.getUser().getId());
    }

    @Test
    void singleUserFormatterOff() {
        // @formatter:off
        Specs.request
                .when()
                    .get("/users/2")
                .then()
                    .spec(Specs.responseSpec)
                    .log().body();
        // @formatter:on
    }
}
