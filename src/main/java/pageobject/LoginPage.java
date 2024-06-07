package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private  WebDriver driver;

    private final By registrationButton = By.xpath(".//a[(@class = 'Auth_link__1fOlj' and text()= 'Зарегистрироваться')]");

    private final By signInButton = By.xpath(".//button[text()='Войти']");

    private final By signInButtonRememberedPassword = By.xpath(".//a[(@class = 'Auth_link__1fOlj' and text()= 'Войти')]");

    private final By emailField = By.xpath("//label[contains(text(),'Email')]/../input");

    private final By passwordField = By.xpath("//label[contains(text(),'Пароль')]/../input");

    private final By personalAccountButton = By.xpath(".//p[contains(text(),'Личный Кабинет')]");

    private final By forgotPasswordButton = By.xpath(".//a[(@class = 'Auth_link__1fOlj' and text()= 'Восстановить пароль')]");
    //Кнопка "Конструктора"
    private final By constructorButton = By.xpath(".//p[text()='Конструктор']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }


    @Step("Клик на кнопку \"Зарегистрироваться\"")
    public RegistrationPage clickRegistrationButton() {
        driver.findElement(registrationButton).click();
        return new RegistrationPage(driver);
    }

    @Step("Клик на кнопку \"Войти\"")
    public LoginPage clickSignInButton() {
        driver.findElement(signInButton).click();
        return this;
    }
    @Step("Клик на кнопку \"Войти\" при ваосстановлении пароля")
    public LoginPage clickSignInButtonRememberedPassword() {
        driver.findElement(signInButtonRememberedPassword).click();
        return this;
    }

    @Step("Передать Email")
    public void setEmailField(String userEmail) {
        driver.findElement(emailField).clear();
        driver.findElement(emailField).sendKeys(userEmail);
    }

    @Step("Передать пароль")
    public void setPasswordField(String userPassword) {
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(userPassword);
    }

    @Step("Клик \"Личный Кабинет\"")
    public void clickPersonalAccountButton() {
        driver.findElement(personalAccountButton).click();
    }

    @Step("Клик на кнопку \"Восстановить пароль\"")
    public void clickForgotPasswordButton() {

        WebElement element = driver.findElement(forgotPasswordButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

        driver.findElement(forgotPasswordButton).click();

    }

    @Step("заполнение  \"Email\" и \"Password\" на главной странице")
    public MainPage signInUser(String userEmail, String userPassword){

        clickPersonalAccountButton();
        setEmailField(userEmail);
        setPasswordField(userPassword);
        clickSignInButton();

        return new MainPage(driver);
    }

    @Step("обнаружение кнопки \"Войти\"")
    public boolean isSignInButtonDisplayed() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(signInButton));

        return driver.findElement(signInButton).isDisplayed();
    }

}