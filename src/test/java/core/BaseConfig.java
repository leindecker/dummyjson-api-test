package core;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.enableLoggingOfRequestAndResponseIfValidationFails;
import static io.restassured.RestAssured.requestSpecification;
import static io.restassured.RestAssured.responseSpecification;
import static org.hamcrest.Matchers.lessThan;

public class BaseConfig {

    private static final String BASE_URL = "https://dummyjson.com";
    private static final ContentType CONTENT_TYPE = ContentType.JSON;
    private static final Long MAX_TIMEOUT = 15000L;

    @BeforeClass
    public static void setUp() {
        baseURI = BASE_URL;

        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setContentType(CONTENT_TYPE);
        requestSpecification = requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();
        responseSpecBuilder.expectResponseTime(lessThan(MAX_TIMEOUT));
        responseSpecification = responseSpecBuilder.build();

        enableLoggingOfRequestAndResponseIfValidationFails();
    }
}
