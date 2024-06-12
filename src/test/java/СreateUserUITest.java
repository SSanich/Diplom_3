import io.qameta.allure.junit4.DisplayName;
import api.User;
import api.UserApi;
import io.restassured.response.ValidatableResponse;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageobject.MainPage;
import static api.Constants.BASE_URI;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class СreateUserUITest {
    UserApi userApi;
    User user;
    String accessToken;
    private DriverRule driverRule = new DriverRule();

    @Rule
    public DriverRule driver = new DriverRule();

    @Test
    @DisplayName("Успешная регистрация пользователя")
    public void successfulUserRegistrationTest() {
        WebDriver driver = driverRule.getDriver();
        driver.get(BASE_URI);
        user = User.random();
        String actual = new MainPage(driver)
                .clickPersonalAccountButton()
                .clickRegistrationButton()
                .registerUser(user.getName(), user.getEmail(), user.getPassword())
                .signInUser(user.getEmail(), user.getPassword())
                .getBasketButtonText();
        assertThat("Ожидается текст «Оформить заказ» ", actual, equalTo("Оформить заказ"));
    }
@Test
@DisplayName("Успешная регистрация пользователя")
    public void deleteUser() {
        if (accessToken != null) {
            ValidatableResponse deleteResponse = userApi.deleteUser(accessToken);
            userApi.checkDeleteSuccessfully(deleteResponse);
        }
    }

}
