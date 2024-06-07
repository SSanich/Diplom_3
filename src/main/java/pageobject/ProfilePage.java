package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfilePage {
    private final WebDriver driver;

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    private final By accountText = By.xpath(".//p[text() = 'В этом разделе вы можете изменить свои персональные данные']");

    private final By exitButton = By.xpath(".//button[text()='Выход']");

    private final By constructorButton = By.xpath(".//p[text()='Конструктор']");

    private final By logoButton = By.xpath(".//div[@class='AppHeader_header__logo__2D0X2']");


    @Step("Обнаружение  текста в профиле при авториизации")
    public void isAccountTextDisplayed() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(accountText));
        driver.findElement(accountText).isDisplayed();
    }

    @Step("Клик  Выход")
    public LoginPage clickExitButton() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(exitButton));
        driver.findElement(exitButton).click();
        return new LoginPage(driver);
    }

    @Step("Клик по конструктору")
    public MainPage clickConstructorButton() {
        driver.findElement(constructorButton).click();
        return new MainPage(driver);
    }
@Step("Клик по лого")
    public MainPage clickLogoButton() {
        driver.findElement(logoButton).click();
        return new MainPage(driver);
    }
}
