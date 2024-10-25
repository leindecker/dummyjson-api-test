package tests.healthCheck.schema;

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

public class HealtCheckSchemaTest extends BaseConfig {

    @Test
    @SneakyThrows
    @DisplayName("Should validate JSON Schema on Health Check APi Service")
    @Category(ContractSuiteTests.class)
    public void shouldValidateHealthCheckJsonSchema() {
        given()
                .when().get(ResourcePathEnum.HEALTH_CHECK_RESOURCE.getValue())
                .then()
                .statusCode(OK.value())
                .body(matchesJsonSchemaInClasspath("healthCheck/GetHealthCheckSchema.json"));
    }
}
