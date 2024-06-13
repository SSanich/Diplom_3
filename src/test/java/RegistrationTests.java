import api.User;
import io.qameta.allure.junit4.DisplayName;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageobject.MainPage;
import pageobject.RegistrationPage;

import static api.Constants.BASE_URI;
import static org.hamcrest.CoreMatchers.equalTo;

public class RegistrationTests {
    User user;
    private DriverRule driverRule = new DriverRule();

    @Test
    @DisplayName("Ошибка при пароле менее 6 символов")

    public void registrationWithInvalidPasswordTest() {
        driverRule.initDriver();
        WebDriver driver = DriverRule.getDriver();
        driver.get(BASE_URI);
        user = User.random();
        new MainPage(driver)
                .clickPersonalAccountButton()
                .clickRegistrationButton()
                .registerUser(user.getName(), user.getEmail(), user.getPassword().substring(0, 4));
        String actual = new RegistrationPage(driver).getErrorMessage();
        MatcherAssert.assertThat("Неверный текст  сообщения об ошибке", actual, equalTo("Некорректный пароль"));
    }
}
