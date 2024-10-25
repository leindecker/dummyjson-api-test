package tests.products.schema;

import core.BaseConfig;
import core.ResourcePathEnum;
import data.ProductsDataFactory;
import io.qameta.allure.junit4.DisplayName;
import lombok.SneakyThrows;
import model.Products;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import suite.ContractSuiteTests;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.springframework.http.HttpStatus.CREATED;

public class PostAddProductsSchemaTest extends BaseConfig {
    @Test
    @SneakyThrows
    @DisplayName("Should validate JSON Schema on add products response")
    @Category(ContractSuiteTests.class)
    public void shouldValideJsonSchemaonAddProductsResponse() {
        Products validProduct = ProductsDataFactory.createValidProduct();

        given()
                .when()
                .body(validProduct)
                .post(ResourcePathEnum.ADD_PRODUCTS_RESOURCE.getValue())
                .then()
                .statusCode(CREATED.value())
                .body(matchesJsonSchemaInClasspath("products/PostAddProductSchema.json"));
    }
}
