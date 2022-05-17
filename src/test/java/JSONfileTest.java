import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class JSONfileTest extends BaseTest {
    private final String baseUrl = "https://jsonplaceholder.typicode.com";
    private final String users = "/users";

    @Test
    public void shouldPostNewUser() {
        //   String filePatch = System.getProperty("user.dir") + "/src/main/resources/user.json";
        File jsonArrayFile = new File(System.getProperty("path"));
        Response response =
                given()
                        .spec(requestSpecification)
                        .body(jsonArrayFile)
                        .when()
                        .post()
                        .then()
                        .extract().response();
        assertThat(response.statusCode(), equalTo(201));
        assertThat(response.jsonPath().getString("username"), equalTo(System.getProperty("username")));
    }

}
