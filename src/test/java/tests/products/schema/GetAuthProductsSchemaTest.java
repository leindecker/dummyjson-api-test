package tests.products.schema;

import core.BaseConfig;
import core.ResourcePathEnum;
import io.qameta.allure.junit4.DisplayName;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import suite.ContractSuiteTests;
import tests.login.util.AuthenticationUtil;
import tests.products.util.AuthEnum;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.springframework.http.HttpStatus.OK;

public class GetAuthProductsSchemaTest extends BaseConfig {

    @Test
    @SneakyThrows
    @DisplayName("Should validate JSON Schema on Get All Products with authentication")
    @Category(ContractSuiteTests.class)
    public void shouldValidateJsonSchemaGetAllProductsWithAuth() {
        given()
                .header("Authorization", "Bearer " + new AuthenticationUtil()
                        .setAccessToken(AuthEnum.USERNAME.getValue(),
                        AuthEnum.PASSWORD.getValue()))
                .when().get(ResourcePathEnum.AUTH_PRODUCTS_RESOURCE.getValue())
                .then()
                .statusCode(OK.value())
                .body(matchesJsonSchemaInClasspath("products/GetAllProductsSchema.json"));
    }
}
