package reportportal.stepdefs;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import lombok.AllArgsConstructor;
import org.testng.asserts.SoftAssert;
import reportportal.steps.APISteps;
import reportportal.steps.BaseSteps;

import java.util.List;
import java.util.Map;

import static org.testng.AssertJUnit.assertEquals;

@AllArgsConstructor
public class APIDefinitions {
    BaseSteps baseSteps;
    APISteps apiSteps;

    @Given("I send GET request for {string} endpoint for {string} project")
    public void sendGETRequest(String endpoint, String projectName) {
        endpoint = apiSteps.updateProject(endpoint, projectName);
        apiSteps.sendGETRequest(endpoint);
    }

    @Then("The Response code is {int}")
    public void theResponseCodeIs(int statusCode) {
        assertEquals(statusCode, apiSteps.getResponseCode());
    }

    @Then("Response filtered by JSON Path contains values")
    public void responseFilteredByJSONPath(DataTable table) {
        SoftAssert softly = new SoftAssert();
        for (Map<String, String> row : table.asMaps(String.class, String.class)) {
            softly = apiSteps.verifyResponseByJsonPathEquals(row.get("field"), row.get("value"), softly);
        }
        softly.assertAll();
    }

    @Then("Response filtered by JSON Path is not null")
    public void responseFilteredByJSONPathNotNull(DataTable table) {
        SoftAssert softly = new SoftAssert();
        for (Map<String, String> row : table.asMaps(String.class, String.class)) {
            softly = apiSteps.verifyResponseByJsonPathEqualsIsNotNull(row.get("field"), softly);
        }
        softly.assertAll();
    }

    @Given("I prepare and send POST request for {string} endpoint to Create Filter under {string} project")
    public void iPrepareRequestForCreateFilter(String endpoint, String projectName, DataTable dataTable) {
        endpoint = apiSteps.updateProject(endpoint, projectName);
        List<Map<String, String>> createFilterRequestList = dataTable.asMaps();
        for (Map<String, String> req : createFilterRequestList) {
            apiSteps.sendCreateFilterRequest(endpoint, req);
            apiSteps.saveFilterID(req.get("name"));
        }
    }

    @Given("I prepare and send PUT request for {string} endpoint to Update Filter under {string} project")
    public void iPrepareRequestForUpdateFilter(String endpoint, String projectName, DataTable dataTable) {
        endpoint = apiSteps.updateProject(endpoint, projectName);
        List<Map<String, String>> updateFilterRequestList = dataTable.asMaps();
        for (Map<String, String> req : updateFilterRequestList) {
            endpoint = apiSteps.updateFilterId(req.get("oldFilter"), endpoint);
            System.out.println(endpoint);
            apiSteps.sendUpdateFilterRequest(endpoint, req);
        }
    }

    @Given("I prepare and send DELETE request for {string} endpoint to Delete Filter under {string} project")
    public void iPrepareRequestForDeleteFilter(String endpoint, String projectName, DataTable dataTable) {
        endpoint = apiSteps.updateProject(endpoint, projectName);
        List<Map<String, String>> deleteFilterRequestList = dataTable.asMaps();
        for (Map<String, String> req : deleteFilterRequestList) {
            endpoint = apiSteps.updateFilterId(req.get("oldFilter"), endpoint);
            apiSteps.sendDeleteFilterRequest(endpoint);
        }
    }
}
