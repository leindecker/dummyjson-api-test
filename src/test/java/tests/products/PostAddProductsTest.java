package tests.products;

import core.BaseConfig;
import core.ResourcePathEnum;
import data.ProductsDataFactory;
import io.qameta.allure.junit4.DisplayName;
import lombok.SneakyThrows;
import model.Products;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import suite.RegressionSuiteTests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.http.HttpStatus.CREATED;

public class PostAddProductsTest extends BaseConfig {

    @Test
    @SneakyThrows
    @DisplayName("Should add Product")
    @Category(RegressionSuiteTests.class)
    public void shouldReturnAllProducts() {
        Products validProduct = ProductsDataFactory.createValidProduct();

        given()
                .body(validProduct)
                .post(ResourcePathEnum.ADD_PRODUCTS_RESOURCE.getValue())
                .then()
                .body("id", notNullValue())
                .body("title", is(validProduct.getTitle()))
                .body("description", is(validProduct.getDescription()))
                .body("price", is(validProduct.getPrice()))
                .body("discountPercentage", is(validProduct.getDiscountPercentage()))
                .body("rating", is(validProduct.getRating()))
                .body("stock", is(validProduct.getStock()))
                .body("brand", is(validProduct.getBrand()))
                .body("category", is(validProduct.getCategory()))
                .body("thumbnail", is(validProduct.getThumbnail()))
                .statusCode(CREATED.value());
    }
}
