package tests.login;

import core.BaseConfig;
import core.ResourcePathEnum;
import io.qameta.allure.junit4.DisplayName;
import lombok.SneakyThrows;
import org.json.simple.JSONObject;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import suite.RegressionSuiteTests;
import tests.login.util.AuthenticationUtil;
import tests.products.util.AuthEnum;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.http.HttpStatus.*;

public class PostAuthLoginTest extends BaseConfig {

    private final AuthenticationUtil authenticationUtil;

    public PostAuthLoginTest() {
        authenticationUtil = new AuthenticationUtil();
    }

    @Test
    @SneakyThrows
    @DisplayName("Should Authenticate using correctly credentials")
    @Category(RegressionSuiteTests.class)
    public void shouldAuthenticateWithSuccess() {
        JSONObject payload = authenticationUtil.createAuthPayload(AuthEnum.USERNAME.getValue(),
                AuthEnum.PASSWORD.getValue());

        given()
                .when()
                .body(payload)
                .post(ResourcePathEnum.LOGIN_RESOURCE.getValue())
                .then()
                .body("accessToken", is(notNullValue()))
                .body("refreshToken", is(notNullValue()))
                .body("id", is(equalTo(1)))
                .body("username", is(equalTo(AuthEnum.USERNAME.getValue())))
                .body("email", is(notNullValue()))
                .body("firstName", is(notNullValue()))
                .body("lastName", is(notNullValue()))
                .body("gender", is(notNullValue()))
                .body("image", is(notNullValue()))
                .statusCode(OK.value());
    }

    @Test
    @SneakyThrows
    @DisplayName("Should not authenticate with invalid credentials")
    @Category(RegressionSuiteTests.class)
    public void shouldThrowInvalidCredentialsError() {
        JSONObject payload = authenticationUtil.createAuthPayload("testesicredi", "testesicredipass");

        given()
                .when()
                .body(payload)
                .post(ResourcePathEnum.LOGIN_RESOURCE.getValue())
                .then()
                .body("message", is(equalTo("Invalid credentials")))
                .statusCode(BAD_REQUEST.value());
    }

    @Test
    @SneakyThrows
    @DisplayName("Should not authenticate without username and password")
    @Category(RegressionSuiteTests.class)
    public void shouldThrowErrorForRequiredUsernameAndPassword() {
        given()
                .when()
                .post(ResourcePathEnum.LOGIN_RESOURCE.getValue())
                .then()
                .body("message", is(equalTo("Username and password required")))
                .statusCode(BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Should not authenticate without password")
    @Category(RegressionSuiteTests.class)
    public void shouldThrowErrorForRequiredPassword() {
        JSONObject payload = authenticationUtil.createAuthPayload(AuthEnum.USERNAME.getValue(),
                AuthEnum.PASSWORD.getValue());
        payload.remove("password");

        given()
                .when()
                .body(payload)
                .post(ResourcePathEnum.LOGIN_RESOURCE.getValue())
                .then()
                .body("message", is(equalTo("Username and password required")))
                .statusCode(BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Should not authenticate without username")
    @Category(RegressionSuiteTests.class)
    public void shouldThrowErrorForRequiredUsername() {
        JSONObject payload = authenticationUtil.createAuthPayload(AuthEnum.USERNAME.getValue(),
                AuthEnum.PASSWORD.getValue());
        payload.remove("username");

        given()
                .when()
                .body(payload)
                .post(ResourcePathEnum.LOGIN_RESOURCE.getValue())
                .then()
                .body("message", is(equalTo("Username and password required")))
                .statusCode(BAD_REQUEST.value());
    }
}
