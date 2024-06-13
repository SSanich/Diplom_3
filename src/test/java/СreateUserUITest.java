import io.qameta.allure.junit4.DisplayName;
import api.User;
import api.UserApi;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageobject.MainPage;
import static api.Constants.BASE_URI;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class СreateUserUITest {
    UserApi userApi = new UserApi();
    User user;
    String accessToken;
    private DriverRule driverRule = new DriverRule();

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

    @After
    @DisplayName("Успешное удаление пользователя")
    public void deleteUser() {
        accessToken = userApi.loginUser(user.toUserCredential())
                .extract()
                .path("accessToken");
        if (accessToken != null) {
            userApi.deleteUser(accessToken);
        }
    }
}
