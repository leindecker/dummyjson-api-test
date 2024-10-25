package tests.login.util;

import core.BaseConfig;
import core.ResourcePathEnum;
import org.json.simple.JSONObject;
import static io.restassured.RestAssured.given;

public class AuthenticationUtil extends BaseConfig {

    String accessToken;

    public JSONObject createAuthPayload(String username, String password) {
        JSONObject payload = new JSONObject();
        payload.put("username", username);
        payload.put("password", password);
        return payload;
    }

    public String setAccessToken(String username, String password) {
        JSONObject payload = createAuthPayload(username, password);

        accessToken = given()
                .when()
                .body(payload)
                .post(ResourcePathEnum.LOGIN_RESOURCE.getValue()).then().extract().path("accessToken");

        return accessToken;

    }
}
