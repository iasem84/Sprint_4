package ru.yandex.practicum.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MainPage {
    private WebDriver driver;

    //кнопка заказать вверху страницы
    private By topOrderButton = By.className("Button_Button__ra12g");

    //кнопка заказать внизу страницы
    private By downOrderButton = By.className("Button_Button__ra12g Button_Middle__1CSJM");

    //раскрывающийся список в разделе "Вопросы о важном"
    private By accordionArrow = By.className("accordion");

    //кнопка закрытия блока cookies "Да все привыкли"

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public MainPage openMainPage() {
        driver.get(Constants.MAIN_PAGE_URL);
        return this;
    }

    public void topOrderButtonClick() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(topOrderButton));
        driver.findElement(topOrderButton).click();
    }

    public void AccordionArrowClick() {
        List <WebElement> elements = driver.findElements(accordionArrow);

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(accordionArrow));

        for (WebElement element : elements) {
            element.click();
        }
    }


}
