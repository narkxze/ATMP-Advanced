package reportportal.steps;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.asserts.SoftAssert;
import reportportal.model.Condition;
import reportportal.model.Filter;
import reportportal.model.Order;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class APISteps extends BaseSteps {
    public Response response;
    public Map<String, String> filterId = new LinkedHashMap<>();

    public RequestSpecification specs() {
        RestAssuredConfig config = RestAssuredConfig.config().httpClient(HttpClientConfig.httpClientConfig()
                .setParam("http.socket.timeout", 5000)
                .setParam("http.connection.timeout", 5000));

        return RestAssured.given()
                .config(config)
                .filter(new AllureRestAssured())
                .baseUri(System.getenv("API_BASE_URL"))
                .auth()
                .preemptive().oauth2(System.getenv("API_TOKEN"));
    }


    public void sendGETRequest(String endpoint) {
        response = specs()
                .when()
                .get(endpoint)
                .then().log().all().extract().response();

        System.out.println(response);
    }

    public int getResponseCode() {
        return response.statusCode();
    }

    public String updateProject(String endpoint, String projectName) {
        return endpoint.replace("{projectName}", projectName);
    }

    public String updateFilterId(String oldFilter, String endpoint) {
        return endpoint.replace("{filterId}", filterId.get(oldFilter));
    }

    public SoftAssert verifyResponseByJsonPathEquals(String jsonPath, String expectedValue, SoftAssert softly) {
        String actualValue = response.jsonPath().getString(jsonPath);
        softly.assertTrue(actualValue.contains(expectedValue), "Expected value " + jsonPath + " jsonPath");
        return softly;
    }

    public SoftAssert verifyResponseByJsonPathEqualsIsNotNull(String jsonPath, SoftAssert softly) {
        String actualValue = response.jsonPath().getString(jsonPath);
        softly.assertNotNull(actualValue, jsonPath + " jsonPath");
        return softly;
    }

    public Filter prepareFilterRequest(Map<String, String> request) {
        return Filter.builder()
                .name(request.get("name"))
                .description(request.get("description"))
                .type(request.get("type"))
                .conditions(Collections.singletonList(Condition.builder().condition(request.get("condition"))
                        .filteringField(request.get("filteringField"))
                        .value(request.get("value"))
                        .build()))
                .orders(Collections.singletonList(Order.builder().isAsc(Boolean.parseBoolean(request.get("isAsc")))
                        .sortingColumn(request.get("sortingColumn"))
                        .build()))
                .build();
    }

    public void sendCreateFilterRequest(String endpoint, Map<String, String> request) {
        response = specs()
                .body(prepareFilterRequest(request))
                .contentType(ContentType.JSON)
                .when()
                .post(endpoint)
                .then().log().all().extract().response();

    }

    public void saveFilterID(String filterName) {
        if (response.statusCode() == 201) {
            filterId.put(filterName, String.valueOf(response.jsonPath().getInt("id")));
            System.out.println(filterId);
        }
    }

    public void sendUpdateFilterRequest(String endpoint, Map<String, String> req) {
        response = specs()
                .body(prepareFilterRequest(req))
                .contentType(ContentType.JSON)
                .when()
                .put(endpoint)
                .then().log().all().extract().response();
    }

    public void sendDeleteFilterRequest(String endpoint) {
        response = specs()
                .when()
                .delete(endpoint)
                .then().log().all().extract().response();
    }
}
