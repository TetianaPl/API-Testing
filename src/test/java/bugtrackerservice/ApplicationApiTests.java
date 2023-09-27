package bugtrackerservice;

import bugtrackerservice.model.Application;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

import static bugtrackerservice.requests.ApplicationApiRequests.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;

public class ApplicationApiTests {
    @Test
    public void verifyReadApplication() {
        ValidatableResponse response = getMethod(2);
        response.assertThat().statusCode(200);
    }

    @Test
    public void verifyReadApplicationDeserialized() {
        Application application = getMethodDeserialized(1);
        Application applicationExpected = new Application("appl", 1, "app1", "me");
        assertThat(application, samePropertyValuesAs(applicationExpected));
    }

    @Test
    public void verifyCreateApplication() {
        Application application = new Application("descr16", "name16", "owner16");
        int id = postMethod(application);
        Application applicationCreated = getMethodDeserialized(id);
        application.setId(id);
        assertThat(application, samePropertyValuesAs(applicationCreated));
    }

    @Test
    public void verifyUpdateApplication() {
        Application application = new Application("descr1", 1, "name1", "owner1");
        ValidatableResponse response = putMethodSerialized(application);
        response.assertThat().statusCode(200);
        Application applicationUpdated = getMethodDeserialized(application.getId());
        assertThat(application, samePropertyValuesAs(applicationUpdated));
    }

    @Test
    public void verifyDeleteApplication() {
        ValidatableResponse response = deleteMethod(9);
        response.assertThat().statusCode(204);
        getMethod(9).assertThat().statusCode(404);
    }
}
