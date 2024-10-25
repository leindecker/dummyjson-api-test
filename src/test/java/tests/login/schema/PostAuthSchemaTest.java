package tests.login.schema;

import core.BaseConfig;
import core.ResourcePathEnum;
import io.qameta.allure.junit4.DisplayName;
import lombok.SneakyThrows;
import org.json.simple.JSONObject;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import suite.ContractSuiteTests;
import tests.login.util.AuthenticationUtil;
import tests.products.util.AuthEnum;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.springframework.http.HttpStatus.OK;

public class PostAuthSchemaTest extends BaseConfig {

    private final AuthenticationUtil authenticationUtil;

    public PostAuthSchemaTest() {
        authenticationUtil = new AuthenticationUtil();
    }

    @Test
    @SneakyThrows
    @DisplayName("Should validate JSON Schema on Login")
    @Category(ContractSuiteTests.class)
    public void shouldValideJsonSchemaOnLogin() {
        JSONObject payload = authenticationUtil.createAuthPayload(AuthEnum.USERNAME.getValue(),
                AuthEnum.PASSWORD.getValue());

        given()
                .when()
                .body(payload)
                .post(ResourcePathEnum.LOGIN_RESOURCE.getValue())
                .then()
                .statusCode(OK.value())
                .body(matchesJsonSchemaInClasspath("login/PostLoginAuthSchema.json"));
    }
}
