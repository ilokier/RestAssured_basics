import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class JsonPlaceHolder extends BaseTest {
    private final String baseUrl = "https://jsonplaceholder.typicode.com";
    private final String users = "/users";

    @Test
    public void shouldGetUsers() {
        given().log().all().when().get(System.getProperty("baseUrl") + System.getProperty("apiUsers")).then().statusCode(200);
    }

    @Test
    public void shouldGetUsersParams2() {
        given().spec(requestSpecification).queryParam("username", "Bret").queryParam("email", "Sincere@april.biz").when().get(baseUrl + users).then().statusCode(200);

    }

    @Test
    public void shouldGetUsersQueryParams1() {
        Response response = given().spec(requestSpecification).queryParam("username", "Bret").queryParam("email", "Sincere@april.biz").when().get().then().statusCode(200).extract().response();
        JsonPath jsonPath = response.jsonPath();
        String jsonName = jsonPath.getString("username").replaceAll("\\[", "").replaceAll("\\]", "");
        String jsonCity = jsonPath.getString("address.city").replaceAll("\\[", "").replaceAll("\\]", "");
        Assert.assertEquals(jsonName, "Bret");
        Assert.assertEquals(jsonCity, ("Gwenborough"));
    }

    @Test
    public void shouldGetUsersQueryParams2() {
        Response response = given().spec(requestSpecification).queryParam("username", "Bret").queryParam("email", "Sincere@april.biz").when().get().then().statusCode(200).extract().response();
        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(jsonPath.get("username[0]"), "Bret");
        Assert.assertEquals(jsonPath.get("address.city[0]"), ("Gwenborough"));

    }

    @Test
    public void shouldGetUsersQueryParams3() {
        Response response =
                given()
                        .spec(requestSpecification)
                        .queryParam("username", "Bret")
                        .queryParam("email", "Sincere@april.biz")
                        .when()
                        .get()
                        .then()
                        .extract().response();
        JsonPath jsonPath = response.jsonPath();
        List<String> allUsernames = jsonPath.getList("username");
        List<String> cities = jsonPath.getList("address.city");
        Object responseJson = jsonPath.get("username");
        Map<String, Object> map = (Map<String, Object>) jsonPath.getList("$").get(0);
        assertThat(map.get("username"), equalTo("Bret"));
        assertThat(responseJson.toString(), containsString("Bret"));
        assertThat(response.statusCode(), equalTo(200));

        assertThat(allUsernames.get(0), equalTo("Bret"));
        assertThat(allUsernames, containsInAnyOrder("Bret"));
        Assert.assertEquals(jsonPath.get("username[0]"), "Bret");

        assertThat(cities.get(0), equalTo("Gwenborough"));
        assertThat(cities, containsInAnyOrder("Gwenborough"));
        Assert.assertEquals(jsonPath.get("address.city[0]"), ("Gwenborough"));

    }

    @Test
    public void shouldGetUser1() {
        Response response = given()
                .spec(requestSpecification)
                .when()
                .get("/1")
                .then()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(jsonPath.get("username"), ("Bret"));
        Assert.assertEquals(jsonPath.get("address.city"), ("Gwenborough"));
        assertThat(response.statusCode(), equalTo(200));

    }

    @Test
    public void shouldGetUser1inaczej() {
        Response response = given()
                .spec(requestSpecification)
                .pathParams("userId", 1)
                .when()
                .get("/{userId}")
                .then()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(jsonPath.get("username"), ("Bret"));
        Assert.assertEquals(jsonPath.get("address.city"), ("Gwenborough"));
        assertThat(response.statusCode(), equalTo(200));

    }


    @Test
    public void shouldPostNewUser() {
        String body = "{\n" +
                "    \"name\": \"Ilona Kier\",\n" +
                "    \"username\": \"Bret\",\n" +
                "    \"email\": \"Sincere@april.biz\",\n" +
                "    \"address\": {\n" +
                "        \"street\": \"Kulas Light\",\n" +
                "        \"suite\": \"Apt. 556\",\n" +
                "        \"city\": \"Gwenborough\",\n" +
                "        \"zipcode\": \"92998-3874\",\n" +
                "        \"geo\": {\n" +
                "            \"lat\": \"-37.3159\",\n" +
                "            \"lng\": \"81.1496\"\n" + "        }\n" +
                "    },\n" + "    \"phone\": \"1-770-736-8031 x56442\",\n" +
                "    \"website\": \"hildegard.org\",\n" +
                "    \"company\": {\n" +
                "        \"name\": \"Romaguera-Crona\",\n" +
                "        \"catchPhrase\": \"Multi-layered client-server neural-net\",\n" +
                "        \"bs\": \"harness real-time e-markets\"\n" +

                "    }\n" + "}";

        given().spec(requestSpecification).body(body).when().post().then().statusCode(201);

    }

    @Test
    public void shouldUpdateUser() {
        String body = "{\n" +
                "    \"name\": \"Ilona dwf\"\n" +
                "}";

        given()
                .spec(requestSpecification)
                .body(body)
                .when()
                .patch("/1")
                .then().statusCode(200);


    }

}
