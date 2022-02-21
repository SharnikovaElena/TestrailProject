package adapters;

import com.google.gson.Gson;

import static io.restassured.RestAssured.given;

public class BaseAdapter {

    Gson gson = new Gson();

    public String post(String body, int statusCode, String url) {
        return
                given()
                        .log().all()
                        .header("accept", "application/json")
                        .header("authorization", "Basic dG1zZGlwbG9tQG1haWxpbmF0b3IuY29tOkxlbm9yNGlr")
                        .header("cache-control", "no-cache")
                        .header("content-type", "application/json")
                        .header("postman-token", "b3b61d21-78d8-3e0a-af16-3e9c3e79d2ad")
                        .body(body)
                        .when()
                        .post("https://diplomtms.testrail.io/index.php?/api/v2/" + url)
                        .then()
                        .log().all()
                        .statusCode(statusCode)
                        .extract().body().asString();
    }


    public String get(int statusCode, String url) {
        return
                given().
                        log().all()
                        .header("accept", "application/json")
                        .header("authorization", "Basic dG1zZGlwbG9tQG1haWxpbmF0b3IuY29tOkxlbm9yNGlr")
                        .header("cache-control", "no-cache")
                        .header("content-type", "application/json")
                        .header("postman-token", "b3b61d21-78d8-3e0a-af16-3e9c3e79d2ad")
                .when()
                        .get("https://diplomtms.testrail.io/index.php?/api/v2/" + url)
                        .then()
                        .log().all()
                        .statusCode(statusCode)
                        .extract().body().asString();
    }

    public int delete(int statusCode, int case_id) {
        return
                given().
                        log().all()
                        .header("accept", "application/json")
                        .header("authorization", "Basic dG1zZGlwbG9tQG1haWxpbmF0b3IuY29tOkxlbm9yNGlr")
                        .header("cache-control", "no-cache")
                        .header("content-type", "application/json")
                        .header("postman-token", "b3b61d21-78d8-3e0a-af16-3e9c3e79d2ad")
                .when()
                        .post("https://diplomtms.testrail.io/index.php?/api/v2/delete_case/" + case_id)
                .then()
                        .log().all()
                        .statusCode(statusCode)
                        .extract().statusCode();
    }
}
