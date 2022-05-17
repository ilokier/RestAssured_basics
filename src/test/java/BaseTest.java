import Configuration.AppProperties;
import Configuration.DriverHandle;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class BaseTest {

    private static Logger log = LoggerFactory.getLogger("BaseTest.class");
    protected static DriverHandle driverHandle;
    private static AppProperties appProperties;
    private final String baseUrl = "https://jsonplaceholder.typicode.com";
    private final String users = "/users";
    RequestSpecification requestSpecification;

    @BeforeSuite
    static void beforeAll() {
        appProperties = AppProperties.getInstance();
        driverHandle = new DriverHandle();

    }

    @BeforeMethod
    public void setup() {
        //RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        requestSpecification = RestAssured
                .given()
                .baseUri(baseUrl)
                .basePath(users)
                .contentType(ContentType.JSON);
    }
}
