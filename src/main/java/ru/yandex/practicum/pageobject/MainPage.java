package ru.yandex.practicum.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    private WebDriver driver;

    //кнопка заказать вверху страницы
    private By topOrderButton = By.className("Button_Button__ra12g");

    //кнопка заказать внизу страницы
    private By downOrderButton = By.xpath(".//div[contains(@class, 'Home_FinishButton__1_cWm')]/button");

    //раскрывающийся список в разделе "Вопросы о важном"
    private static final String FAQ_QUESTION_PATTERN = ".//div[contains(@class, 'accordion__button') and contains(text(), '%s')]";
    private By answerLocator = By.xpath(".//div[contains(@class, 'accordion__panel') and not(@hidden)]/p");

    //кнопка закрытия блока cookies "Да все привыкли"
    private By shutDownCookiesButton = By.xpath(".//button[@class='App_CookieButton__3cvqF']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void topOrderButtonClick() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(topOrderButton));
        shutDownCookiesButtonClick();
        driver.findElement(topOrderButton).click();
    }

    public void downOrderButtonClick() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(downOrderButton));
        shutDownCookiesButtonClick();
        driver.findElement(downOrderButton).click();
    }

    public void clickFAQQuestion(String questionMessage) {
        String questionLocator = String.format(FAQ_QUESTION_PATTERN, questionMessage);

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(questionLocator)));

        shutDownCookiesButtonClick();

        WebElement questionElement = driver.findElement(By.xpath(questionLocator));
        scrollToElement(questionElement);
        questionElement.click();
    }

    public String getFAQAnswer() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(answerLocator));
        return driver.findElement(answerLocator).getText();
    }

    private void scrollToElement(WebElement element) {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView", element);
    }

    private void shutDownCookiesButtonClick() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(shutDownCookiesButton));

        driver.findElement(shutDownCookiesButton).click();
    }


}
