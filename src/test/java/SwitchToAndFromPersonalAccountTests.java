import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;

import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageobject.LoginPage;
import pageobject.MainPage;
import pageobject.ProfilePage;

import static api.Constants.BASE_URI;
import static org.hamcrest.CoreMatchers.containsString;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

public class SwitchToAndFromPersonalAccountTests extends BeforeAfter {
    private DriverRule driverRule = new DriverRule();

    @Rule
    public DriverRule driver = new DriverRule();

    @Test
    @DisplayName("Переход в личный кабинет авторизованного пользователяпо клику на «Личный кабинет»")
    public void switchToPersonalAccountAuthUserTests() {
        WebDriver driver = driverRule.getDriver();
        driver.get(BASE_URI);

        new MainPage(driver)
                .clickLogInAccountButton();
        new LoginPage(driver)
                .signInUser(user.getEmail(), user.getPassword());
        new MainPage(driver)
                .clickPersonalAccountButtonAuthUser();
        new ProfilePage(driver)
                .isAccountTextDisplayed();
        assertThat("Некоректный URL  Личного кабинета", driver.getCurrentUrl(), containsString("/profile"));

    }

    @Test
    @DisplayName("Переход в личный кабинет неавторизованного пользователя по клику на «Личный кабинет»")
    @Description("Switch to personal account unauth user")
    public void switchToPersonalAccountUnAuthUserTests() {
        WebDriver driver = driverRule.getDriver();
        driver.get(BASE_URI);

        new MainPage(driver)
                .clickPersonalAccountButton();
        Boolean actual = new LoginPage(driver)
                .isSignInButtonDisplayed();
        assertTrue("Не авторизированный пользователь не может зайти в кабинет", actual);
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор по клику на «Конструктор»")
    public void switchFromPersonalAccountToConstructorTests() {
        WebDriver driver = driverRule.getDriver();
        driver.get(BASE_URI);

        new MainPage(driver)
                .clickLogInAccountButton();
        new LoginPage(driver)
                .signInUser(user.getEmail(), user.getPassword());
        new MainPage(driver)
                .clickPersonalAccountButtonAuthUser();
        Boolean actual = new ProfilePage(driver)
                .clickConstructorButton()
                .isBunsIsDisplayed();
        assertTrue("Ошибка открытия конструктора", actual);
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор по клику на логотип Stellar Burgers")
    public void switchFromPersonalAccountToConstructorToLogoTests() {
        WebDriver driver = driverRule.getDriver();
        driver.get(BASE_URI);

        new MainPage(driver)
                .clickLogInAccountButton();
        new LoginPage(driver)
                .signInUser(user.getEmail(), user.getPassword());
        new MainPage(driver)
                .clickPersonalAccountButtonAuthUser();
        Boolean actual = new ProfilePage(driver)
                .clickLogoButton()
                .isBunsIsDisplayed();
        assertTrue("Ошибка открытия главной страницы", actual);
    }
}
