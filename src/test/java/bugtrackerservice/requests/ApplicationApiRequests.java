package bugtrackerservice.requests;

import bugtrackerservice.model.Application;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class ApplicationApiRequests {
    //Used to create an application entry and find its ID
    public static int postMethod(Application application) {

        HttpURLConnection conn;
        int responseCode;
        try {
            URL url = new URL("http://localhost:8080/v1/application");
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            OutputStream os = conn.getOutputStream();
            os.write(application.toString().getBytes());
            os.flush();

            responseCode = conn.getResponseCode();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Response code: " + responseCode);

        Map<String, List<String>> headersMap = conn.getHeaderFields();
// Print the headers
        for (Map.Entry<String, List<String>> entry : headersMap.entrySet()) {
            String key = entry.getKey();
            List<String> value = entry.getValue();
            System.out.println(key + ": " + value.toString());
        }
        if (responseCode == 201) {
            String newElementLink = headersMap.get("Location").toString();
            return Integer.parseInt(newElementLink.split("application/")[1].replace("]", ""));
        } else {
            return 0;
        }
    }

    public static ValidatableResponse postMethodSerialized(Application application) {
        return RestAssured.given()
                .contentType(ContentType.JSON)
//                .header("Content-Type", "application/json")
                .body(application)
                .when()
                .post("http://localhost:8080/v1/application")
                .then()
                .log().status()
                .log().headers()
                .log().body();
    }

    public static ValidatableResponse getMethod(int id) {
        return RestAssured.given()
                .when()
                .get("http://localhost:8080/v1/application/" + id)
                .then()
                .log().status()
                .log().headers()
                .log().body();
    }

    public static Application getMethodDeserialized(int id) {
        return RestAssured.given()
                .when()
                .get("http://localhost:8080/v1/application/" + id)
                .as(Application.class);
    }

    public static ValidatableResponse putMethodSerialized(Application application) {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .body(application)
                .when()
                .put("http://localhost:8080/v1/application")
                .then()
                .log().status()
                .log().headers()
                .log().body();
    }

    public static ValidatableResponse deleteMethod(int id) {
        return RestAssured.given()
                .when()
                .delete("http://localhost:8080/v1/application/" + id)
                .then()
                .log().status()
                .log().headers()
                .log().body();
    }
}
