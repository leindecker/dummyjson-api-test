package tests.users.schema;

import core.BaseConfig;
import core.ResourcePathEnum;
import io.qameta.allure.junit4.DisplayName;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import suite.ContractSuiteTests;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.springframework.http.HttpStatus.OK;

public class GetUsersSchemaTest extends BaseConfig {

    @Test
    @SneakyThrows
    @DisplayName("Should validate JSON Schema on return Users response")
    @Category(ContractSuiteTests.class)
    public void shouldValidateHealthCheckJsonSchema() {
        given()
                .when().get(ResourcePathEnum.USERS_RESOURCE.getValue() + "/1")
                .then()
                .statusCode(OK.value())
                .body(matchesJsonSchemaInClasspath("users/GetUserByIdSchema.json"));
    }
}
