package ru.yandex.practicum.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ClientFormOrderPage {
    private WebDriver driver;

    //поле ввода имени заказчика
    private By nameField = By.xpath(".//input[@placeholder='* Имя']");

    //поле ввода фамилии заказчика
    private By surnameField = By.xpath(".//input[@placeholder='* Фамилия']");

    //поле ввода адреса доставки самоката
    private By addressField = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");

    //поле выбора станции метро из выпадающего списка
    private By metroStationList = By.xpath(".//div[@class='select-search__select']/ul/li");

    //поле ввода номера телефона заказчика
    private By phoneNumberField = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");

    //кнопка "Далее"
    private By furtherButton = By.className("Button_Button__ra12g Button_Middle__1CSJM");

    public ClientFormOrderPage(WebDriver driver) {
        this.driver = driver;
    }
}
