import io.qameta.allure.junit4.DisplayName;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageobject.LoginPage;
import pageobject.MainPage;
import pageobject.ProfilePage;
import static api.Constants.BASE_URI;
import static org.junit.Assert.assertTrue;

public class ExitAccountTests extends BeforeAfter {
    private DriverRule driverRule = new DriverRule();

    @Rule
    public DriverRule driver = new DriverRule();


    @Test
    @DisplayName("Выход из акаунта по кнопке «Выйти» в личном кабинете")
    public void exitAccountWithButtonExit() {
        WebDriver driver = driverRule.getDriver();
        driver.get(BASE_URI);

        new MainPage(driver)
                .clickPersonalAccountButton()
                .signInUser(user.getEmail(), user.getPassword())
                .clickPersonalAccountButton();

       new ProfilePage(driver)
                .clickExitButton();

        Boolean actual = new LoginPage(driver)
                .isSignInButtonDisplayed();

        assertTrue("Ошибка выхода из аккаунта", actual);

    }

}
