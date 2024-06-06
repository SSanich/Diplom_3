package api;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.CoreMatchers;
import java.net.HttpURLConnection;
import static api.Constants.*;
import static io.restassured.RestAssured.given;
import static java.net.HttpURLConnection.HTTP_ACCEPTED;
import static org.hamcrest.Matchers.equalTo;

public class UserApi {

    public static RequestSpecification spec() {
        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URI);
    }

    @Step("Создание пользователя")
    public ValidatableResponse createUser(User user) {
        return spec()
                .body(user)
                .post(CREATE_USER)
                .then().log().all();
    }

    @Step("Удаление пользователя")
    public ValidatableResponse deleteUser(String accessToken) {
        return spec()
                .auth().oauth2(accessToken.substring(accessToken.indexOf(" ") + 1))
                .delete(CHANGE_USER)
                .then().log().all();
    }
    @Step("Check user create ")
    public void checkCreatedSuccessfully(ValidatableResponse response) {
        response
                .assertThat()
                .body("success", equalTo(true))
                .statusCode(HttpURLConnection.HTTP_OK);
    }
    @Step("Check user delete ")
    public void checkDeleteSuccessfully(ValidatableResponse deleteResponse) {
        deleteResponse
                .assertThat()
                .statusCode(HTTP_ACCEPTED)
                .body("success", CoreMatchers.equalTo(true), "message", CoreMatchers.equalTo("User successfully removed"));
    }

}
