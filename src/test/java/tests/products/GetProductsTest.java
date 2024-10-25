package tests.products;

import core.BaseConfig;
import core.ResourcePathEnum;
import io.qameta.allure.junit4.DisplayName;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import suite.RegressionSuiteTests;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

public class GetProductsTest extends BaseConfig {

    @Test
    @SneakyThrows
    @DisplayName("Should return all products with no authentication required")
    @Category(RegressionSuiteTests.class)
    public void shouldReturnAllProducts() {
        given()
                .when()
                .get(ResourcePathEnum.PRODUCTS_RESOURCE.getValue())
                .then()
                .body("products", hasItems())
                .body("title", is(not((empty()))))
                .body("category", is(not((empty()))))
                .statusCode(OK.value());
    }

    @Test
    @SneakyThrows
    @DisplayName("Should return product by ID")
    @Category(RegressionSuiteTests.class)
    public void shouldProductById() {
        Integer productId = given()
                .when()
                .get(ResourcePathEnum.PRODUCTS_RESOURCE.getValue())
                .then().extract().path("products.id[0]");

        given()
                .when()
                .get(ResourcePathEnum.PRODUCTS_RESOURCE.getValue() + "/" + productId)
                .then()
                .body("products", hasItems())
                .body("title", is(not((empty()))))
                .body("category", is(not((empty()))))
                .statusCode(OK.value());
    }

    @Test
    @SneakyThrows
    @DisplayName("Should not return a product with invalid ID")
    @Category(RegressionSuiteTests.class)
    public void shouldReturnErrorProducInvalidId() {
        given()
                .when()
                .get(ResourcePathEnum.PRODUCTS_RESOURCE.getValue() + "/" + 9999)
                .then()
                .body("message", is("Product with id '9999' not found"))
                .statusCode(NOT_FOUND.value());
    }
}
