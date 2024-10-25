package tests.healthCheck;

import core.BaseConfig;
import core.ResourcePathEnum;
import io.qameta.allure.junit4.DisplayName;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import suite.RegressionSuiteTests;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.http.HttpStatus.OK;

public class GetHealthCheckTest extends BaseConfig {

    @Test
    @SneakyThrows
    @DisplayName("Should Validade Health Check APi Service")
    @Category(RegressionSuiteTests.class)
    public void shouldValidateApiHealthCheckResponse() {
        given()
                .when()
                .get(ResourcePathEnum.HEALTH_CHECK_RESOURCE.getValue())
                .then()
                .statusCode(OK.value())
                .body("status", is("ok"))
                .body("method", is("GET"));
    }
}
