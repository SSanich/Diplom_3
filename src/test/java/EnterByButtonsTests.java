import io.qameta.allure.junit4.DisplayName;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageobject.LoginPage;
import pageobject.MainPage;
import static api.Constants.BASE_URI;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class EnterByButtonsTests  extends BeforeAfter {

    private DriverRule driverRule = new DriverRule();

    @Rule
    public DriverRule driver = new DriverRule();


    @Test
    @DisplayName("Войти профиль по кнопке «Войти в аккаунт» на главной странице")
    public void logInUsingLogInAccountButtonOnTheMainPageTest() {
        WebDriver driver = driverRule.getDriver();
        driver.get(BASE_URI);

        new MainPage(driver)
                .clickLogInAccountButton()
                .signInUser(user.getEmail(), user.getPassword());

        String actual = new MainPage(driver).getBasketButtonText();
        assertThat("Ожидается текст «Оформить заказ» ", actual, equalTo("Оформить заказ"));
    }

    @Test
    @DisplayName("Войти в профиль по кнопке «Личный кабинет»")
    public void signInWithButtonPersonalAccountTest() {
        WebDriver driver = driverRule.getDriver();
        driver.get(BASE_URI);

        new MainPage(driver)
                .clickPersonalAccountButtonAuthUser();

        new LoginPage(driver)
                .clickSignInButton()
                .signInUser(user.getEmail(), user.getPassword());

        String actual = new MainPage(driver).getBasketButtonText();
        assertThat("Ожидается текст «Оформить заказ» ", actual, equalTo("Оформить заказ"));
    }

    @Test
    @DisplayName("Войти в профиль по «Войти» в форме регистрации")
    public void singInWithButtonOnRegistrationPageTest() {
        WebDriver driver = driverRule.getDriver();
        driver.get(BASE_URI);

        new MainPage(driver)
                .clickPersonalAccountButton();
        new LoginPage(driver)
                .clickRegistrationButton()
                .clickSignInButton()
                .signInUser(user.getEmail(), user.getPassword());

        String actual = new MainPage(driver).getBasketButtonText();
        assertThat("Ожидается текст «Оформить заказ» ", actual, equalTo("Оформить заказ"));
    }

    @Test
    @DisplayName("Войти в профиль по «Войти» в форме восстановления пароль")
    public void singInWithButtonOnForgotPasswordPageTest() {
        WebDriver driver = driverRule.getDriver();
        driver.get(BASE_URI);

        new MainPage(driver)
                .clickPersonalAccountButton();
        new LoginPage(driver)
                .clickForgotPasswordButton();
        new LoginPage(driver)
                .clickSignInButtonRememberedPassword()
                .signInUser(user.getEmail(), user.getPassword());

        String actual = new MainPage(driver).getBasketButtonText();
        assertThat("Ожидается текст «Оформить заказ»", actual, equalTo("Оформить заказ"));
    }
}

