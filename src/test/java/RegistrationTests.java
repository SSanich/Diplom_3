import io.qameta.allure.junit4.DisplayName;

import org.hamcrest.MatcherAssert;

import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageobject.MainPage;
import pageobject.RegistrationPage;

import static api.Constants.BASE_URI;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class RegistrationTests extends BeforeAfter {
    private DriverRule driverRule = new DriverRule();

    @Rule
    public DriverRule driver = new DriverRule();

    @Test
    @DisplayName("Успешная регистрация пользователя")
    public void successfulUserRegistrationTest() {
        WebDriver driver = driverRule.getDriver();
        driver.get(BASE_URI);

        String actual = new MainPage(driver)
                .clickPersonalAccountButton()
                .clickRegistrationButton()
                .registerUser(user.getName(), user.getEmail(), user.getPassword())
                .signInUser(user.getEmail(), user.getPassword())
                .getBasketButtonText();
        assertThat("Ожидается текст «Оформить заказ» ", actual, equalTo("Оформить заказ"));
    }

    @Test
    @DisplayName("Ошибка при пароле менее 6 символов")

    public void registrationWithInvalidPasswordTest() {
        WebDriver driver = driverRule.getDriver();
        driver.get(BASE_URI);

        new MainPage(driver)
                .clickPersonalAccountButton()
                .clickRegistrationButton()
                .registerUser(user.getName(), user.getEmail(), user.getPassword().substring(0, 4));
        String actual = new RegistrationPage(driver).getErrorMessage();
        MatcherAssert.assertThat("Неверный текст  сообщения об ошибке", actual,equalTo("Некорректный пароль"));
    }
}
