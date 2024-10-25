package tests.users;

import core.BaseConfig;
import core.ResourcePathEnum;
import io.qameta.allure.junit4.DisplayName;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import suite.RegressionSuiteTests;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.*;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

public class GetUsersTest extends BaseConfig {

    @Test
    @SneakyThrows
    @DisplayName("Should return All users")
    @Category(RegressionSuiteTests.class)
    public void getUsersTest() {
        given()
                .when()
                .get(ResourcePathEnum.USERS_RESOURCE.getValue())
                .then()
                .body("size()", greaterThan(0))
                .body("users.id", notNullValue())
                .body("users.firstName", notNullValue())
                .body("users.lastName", notNullValue())
                .body("users.hair", hasItems())
                .body("users.adress", hasItems())
                .body("users.bank", hasItems())
                .body("users.company", hasItems())
                .statusCode(OK.value());
    }

    @Test
    @SneakyThrows
    @DisplayName("Should return a especific user by id")
    @Category(RegressionSuiteTests.class)
    public void shouldReturnUserById() {
        Integer userId = given()
                .when()
                .get(ResourcePathEnum.USERS_RESOURCE.getValue())
                .then().extract().path("users.id[0]");

        given()
                .when()
                .get(ResourcePathEnum.USERS_RESOURCE.getValue() + "/" + userId)
                .then()
                .assertThat()
                .body("id", notNullValue())
                .body("firstName", notNullValue())
                .body("lastName", notNullValue())
                .body("hair", hasItems())
                .body("adress", hasItems())
                .body("bank", hasItems())
                .body("company", hasItems())
                .statusCode(OK.value());
    }

    @Test
    @SneakyThrows
    @DisplayName("Should not return any user by invalid id")
    @Category(RegressionSuiteTests.class)
    public void shouldNotReturnUserByInvalidId() {
        given()
                .when()
                .get(ResourcePathEnum.USERS_RESOURCE.getValue() + "/" + 99999)
                .then()
                .assertThat()
                .body("message", is(equalTo("User with id '99999' not found")))
                .statusCode(NOT_FOUND.value());
    }
}
