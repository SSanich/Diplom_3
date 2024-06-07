package pageobject;


import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
public class MainPage {

    private  WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    private By logInAccountButton = By.xpath(".//button[text()='Войти в аккаунт']");

    public static By personalAccountButton = By.xpath(".//p[contains(text(),'Личный Кабинет')]");

    private final By bansTab = By.xpath("//div[span[text()='Булки']]");

    private final By saucesTab = By.xpath("//div[span[text()='Соусы']]");

    private final By fillingsTab = By.xpath("//div[span[text()='Начинки']]");

    private final By bun = By.xpath("//div[contains(span/text(),'Булки') and contains(@class,'current')]");

    private final By sauces = By.xpath("//div[contains(span/text(),'Соусы') and contains(@class,'current')]");

    private final By fillings = By.xpath("//div[contains(span/text(),'Начинки') and contains(@class,'current')]");

    private final By basketButton = By.xpath(".//div[starts-with(@class,'BurgerConstructor_basket__container')]/button");


   @Step(" Клик кнопки \"Личный кабинет\"")
    public LoginPage clickPersonalAccountButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(personalAccountButton));

        driver.findElement(personalAccountButton).click();
        return new LoginPage(driver);
    }

    @Step("Клик кнопки \"Войти в аккаунт\"")
    public LoginPage clickLogInAccountButton() {
        driver.findElement(logInAccountButton).click();
        return new LoginPage(driver);
    }

    @Step("Клик  \"Личный кабинет\" авторизированным пользователем")
     public ProfilePage clickPersonalAccountButtonAuthUser() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(personalAccountButton));

        driver.findElement(personalAccountButton).click();
        return new ProfilePage(driver);
    }

    @Step("Клик  вкладки \"Булка\"")
    public MainPage clickBunsTab() {
        driver.findElement(bansTab).click();
        return new MainPage(driver);
    }
    @Step("Клик вкладк \"Соусы\"")
    public MainPage clickSaucesTab() {
        driver.findElement(saucesTab).click();
        return new MainPage(driver);
    }

    @Step("Клик вкладки \"Начинки\"")
    public MainPage clickFillingsTab() {
        driver.findElement(fillingsTab).click();
        return new MainPage(driver);
    }

    @Step("Обнарружение на экране вкладки \"Булки\"")
    public boolean isBunsIsDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(bun));

        return driver.findElement(bun).isDisplayed();
    }

    @Step("Обнаружение на экране вкладки \"Соусы\"")
    public boolean isSaucesIsDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(sauces));

        return driver.findElement(sauces).isDisplayed();
    }

    @Step("Обнаружение на экране вкладки \"Соусы\"")
    public boolean isFillingsIsDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(fillings));

        return driver.findElement(fillings).isDisplayed();
    }

    @Step("получить текст кнопки\"Оформит заказ\"")
    public String getBasketButtonText() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(basketButton));
        return driver.findElement(basketButton).getText();
    }


}
