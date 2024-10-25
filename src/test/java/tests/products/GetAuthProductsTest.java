package tests.products;

import core.BaseConfig;
import core.ResourcePathEnum;
import io.qameta.allure.junit4.DisplayName;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import suite.RegressionSuiteTests;
import tests.login.util.AuthenticationUtil;
import tests.products.util.AuthEnum;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

public class GetAuthProductsTest extends BaseConfig {
    private static final String HEADER_NAME = "Authorization";
    private static final String HEADER_VALUE = "Bearer ";

    AuthenticationUtil authenticationUtil;

    public GetAuthProductsTest() {
        authenticationUtil = new AuthenticationUtil();
    }

    @Test
    @SneakyThrows
    @DisplayName("Should return all products with authentication")
    @Category(RegressionSuiteTests.class)
    public void shouldReturnAllProducts() {
        given()
                .header(HEADER_NAME, HEADER_VALUE + authenticationUtil.setAccessToken(AuthEnum.USERNAME.getValue(),
                        AuthEnum.PASSWORD.getValue()))
                .when()
                .get(ResourcePathEnum.AUTH_PRODUCTS_RESOURCE.getValue())
                .then()
                .body("products", hasItems())
                .body("title", is(not((empty()))))
                .body("category", is(not((empty()))))
                .statusCode(OK.value());
    }

    @Test
    @SneakyThrows
    @DisplayName("Should not return products without authentication")
    @Category(RegressionSuiteTests.class)
    public void shouldNotGetProductsWithNoToken() {
        given()
                .when()
                .get(ResourcePathEnum.AUTH_PRODUCTS_RESOURCE.getValue())
                .then()
                .body("message", is("Access Token is required"))
                .statusCode(UNAUTHORIZED.value());
    }

    @Test
    @SneakyThrows
    @DisplayName("Should not return Products with expired Access Token")
    @Category(RegressionSuiteTests.class)
    public void shouldNotGetProductsWithExpiredToken() {
        given()
                .header(HEADER_NAME, HEADER_VALUE + AuthEnum.EXPIRED_TOKEN.getValue())
                .when()
                .get(ResourcePathEnum.AUTH_PRODUCTS_RESOURCE.getValue())
                .then()
                .body("message", is("Token Expired!"))
                .statusCode(UNAUTHORIZED.value());
    }
}
