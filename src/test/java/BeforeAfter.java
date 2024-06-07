import api.User;
import api.UserApi;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;

public class BeforeAfter {
    UserApi userApi;
    User user;
    String accessToken;

    @Before
    public void setUp() {
        userApi = new UserApi();
        user = User.random();
        ValidatableResponse response = userApi.createUser(user);
        userApi.checkCreatedSuccessfully(response);
        accessToken = response.extract().path("accessToken");
    }

    @After
    public void deleteUser() {
        if (accessToken != null) {
            ValidatableResponse deleteResponse = userApi.deleteUser(accessToken);
            userApi.checkDeleteSuccessfully(deleteResponse);
        }
    }
}


