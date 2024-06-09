import io.qameta.allure.junit4.DisplayName;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageobject.MainPage;

import static api.Constants.BASE_URI;
import static org.hamcrest.MatcherAssert.assertThat;

public class ConstructorTest {
    private DriverRule driverRule = new DriverRule();

    @Rule
    public DriverRule driver = new DriverRule();

    @Test
    @DisplayName("Переход к разделу Булки")
    public void goToTheBunSectionTest() {
        WebDriver driver = driverRule.getDriver();
        driver.get(BASE_URI);

        new MainPage(driver)
                .clickFillingsTab();
        Boolean actual = new MainPage(driver)
                .clickBunsTab()
                .isBunsIsDisplayed();

        assertThat("Ошибка перехода в раздел Булки", actual);
    }

    @Test
    @DisplayName("Переход к разделу Соусы")
    public void goToTheSaucesSectionTest() {
        WebDriver driver = driverRule.getDriver();
        driver.get(BASE_URI);

        Boolean actual = new MainPage(driver)
                .clickSaucesTab()
                .isSaucesIsDisplayed();

        assertThat("Ошибка перехода в раздел Соусы", actual);
    }

    @Test
    @DisplayName("Переход к разделу Начинки")
    public void goToTheFillingsSectionTest() {
        WebDriver driver = driverRule.getDriver();
        driver.get(BASE_URI);

        Boolean actual = new MainPage(driver)
                .clickFillingsTab()
                .isFillingsIsDisplayed();

        assertThat("Ошибка перехода в раздел Начинки", actual);
    }

}
