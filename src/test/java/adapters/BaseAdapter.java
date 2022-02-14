package adapters;

import com.google.gson.Gson;

import static io.restassured.RestAssured.given;

public class BaseAdapter {

    Gson gson = new Gson();

    public String post(String body, int statusCode, String url) {
        return
        given()
                .log().all()
                .header("Accept" , "application/json")
                .header("Authorization", "Basic c2hhcm5pa292YWV2QGdtYWlsLmNvbTpMZW5vcjRpaw==")
                .header("Content-Type", "application/json")
                .header("_token", "O8l/91VWAh1HqHztE4qK")
                .body(body)
        .when()
                .post("https://sharnikova.testrail.io/index.php?/api/v2/" + url)
        .then()
                .log().all()
                .statusCode(statusCode)
                .extract().body().asString();
    }



    public String get (int statusCode, String url, String id) {
        return
         given().
                log().all()
                .header("Accept" , "application/json")
                .header("Authorization", "c2hhcm5pa292YWV2QGdtYWlsLmNvbTpMZW5vcjRpaw")
                .header("Content-Type", "application/json")
                .header("_token", "O8l/91VWAh1HqHztE4qK")
         .when()
                .get("https://sharnikova.testrail.io/index.php?/api/v2/" + url)
         .then()
                 .log().all()
                 .statusCode(statusCode)
                 .extract().body().asString();
    }
}