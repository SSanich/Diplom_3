package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationPage {

    private final WebDriver driver;


    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By nameField = By.xpath("//label[contains(text(),'Имя')]/../input");

    private final By emailField = By.xpath("//label[contains(text(),'Email')]/../input");

    private final By passwordField = By.xpath("//label[contains(text(),'Пароль')]/../input");

    private final By registrationButton = By.xpath(".//button[text()='Зарегистрироваться']");

    private final By signInButton = By.xpath("//a[text()='Войти']");

    private final By errorShortPassword = By.xpath(".//p[text() = 'Некорректный пароль']");

    @Step("ввод данных в поле \"Имя\"")
    public RegistrationPage setNameField(String userName) {
        driver.findElement(nameField).sendKeys(userName);
        return this;
    }

    @Step("ввод данных в поле \"Email\"")
    public RegistrationPage setEmailField(String userEmail) {
        driver.findElement(emailField).sendKeys(userEmail);
        return this;
    }

    @Step("ввод данных в поле \"Пароль\"")
    public RegistrationPage setPasswordField(String userPassword) {
        driver.findElement(passwordField).sendKeys(userPassword);
        return this;
    }

    @Step("Клик на кнопку \"Зарегистрироваться\"")
    public void clickRegisterButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement registerButton = wait.until(ExpectedConditions.visibilityOfElementLocated(registrationButton));
        registerButton.click();
    }

    @Step("Заполнение полей \"Имя\", \"Email\" и \"Пароль\" на странице регистрации")
    public LoginPage registerUser(String name, String email, String password) {
        setNameField(name);
        setEmailField(email);
        setPasswordField(password);
        clickRegisterButton();
        return new LoginPage(driver);
    }

    @Step("Клик на кнопку \"Войти\"")
    public LoginPage clickSignInButton() {
        driver.findElement(signInButton).click();
        return new LoginPage(driver);
    }

    @Step("сообщение об ошибке")
    public String getErrorMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement errorShortPasswordElement = wait.until(ExpectedConditions.visibilityOfElementLocated(errorShortPassword));
        return errorShortPasswordElement.getText();
    }

}
