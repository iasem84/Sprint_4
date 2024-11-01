package ru.yandex.practicum.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderPage {
    private WebDriver driver;

    //поле ввода имени заказчика
    private By nameField = By.xpath(".//input[contains(@placeholder, 'Имя')]");

    //поле ввода фамилии заказчика
    private By surnameField = By.xpath(".//input[contains(@placeholder, 'Фамилия')]");

    //поле ввода адреса доставки самоката
    private By addressField = By.xpath(".//input[contains(@placeholder, 'Адрес: куда привезти заказ')]");

    //поле выбора станции метро из выпадающего списка
    private By metroStationField = By.xpath(".//div[@class='select-search__value']");
    private static final String METRO_STATION_LIST = ".//li[@class='select-search__row' and contains(@data-value, '%d')]";

    //поле ввода номера телефона заказчика
    private By phoneNumberField = By.xpath(".//input[contains(@placeholder, 'Телефон: на него позвонит курьер')]");

    //кнопка "Далее"
    private By furtherButton = By.xpath(".//button[contains(@class, 'Button_Middle__1CSJM') " +
            "and contains(text(), 'Далее')]");

    //поле выбора даты начала аренды самоката
    private By deliveryDateField = By.xpath(".//input[contains(@placeholder, 'Когда привезти самокат')]");
    private By deliveryDateDataPicker = By.xpath(".//div[contains(@class, 'datepicker__day--selected')]");

    //поле выбора срока аренды самоката из выпадающего списка
    private static final String RENT_PERIOD_PATTERN = ".//div[contains(@class, 'Dropdown-option') and contains(text(), '%s')]";
    private By rentPeriodField = By.xpath(".//div[contains(@class, 'Dropdown-root')]");

    //поле с чек-боксом выбора цвета самоката - необязательное
    private static final String SCOOTER_COLOUR_PATTERN = (".//input[contains(@id, '%s')]");

    //поле ввода комментария для курьера - необязательное
    private By commentField = By.xpath(".//input[contains(@placeholder, 'Комментарий для курьера')]");

    //кнопка "Заказать"
    private By orderButton = By.xpath(".//button[contains(@class, 'Button_Middle__1CSJM') " +
            "and contains(text(), 'Заказать')]");

    //кнопка подтверждения заказа
    private By confirmationButton = By.xpath(".//button[contains(@class, 'Button_Middle__1CSJM') " +
            "and contains(text(), 'Да')]");

    //модальное окно с номером заказа
    private By windowWithOrderNumber = By.xpath(".//div[contains(@class, 'Order_ModalHeader__3FDaJ') " +
            "and contains(text(), 'Заказ оформлен')]");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setNameField(String name) {
        driver.findElement(nameField).clear();
        driver.findElement(nameField).sendKeys(name);
    }

    public void setSurnameField(String surname){
        driver.findElement(surnameField).clear();
        driver.findElement(surnameField).sendKeys(surname);
    }

    public void setAddressField(String address) {
        driver.findElement(addressField).clear();
        driver.findElement(addressField).sendKeys(address);
    }

    public void setMetroStationList() {
        driver.findElement(metroStationField).click();

        int stationValue = (int)(1+Math.random()*236);
        String stationLocator = String.format(METRO_STATION_LIST, stationValue);
        WebElement stationElement = driver.findElement(By.xpath(stationLocator));
        scrollToElement(stationElement);
        stationElement.click();
    }

    public void setPhoneNumberField(String phoneNumber) {
        driver.findElement(phoneNumberField).clear();
        driver.findElement(phoneNumberField).sendKeys(phoneNumber);
    }

    public void furtherButtonClick() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(furtherButton));
        driver.findElement(furtherButton).click();
    }

    public void setDeliveryDateField(String date) {
        new WebDriverWait(driver, Duration.ofSeconds(3)).
                until(ExpectedConditions.visibilityOfElementLocated(deliveryDateField));
        driver.findElement(deliveryDateField).clear();
        driver.findElement(deliveryDateField).sendKeys(date);
        driver.findElement(deliveryDateDataPicker).click();
    }

    public void setRentPeriodField(String period) {
        driver.findElement(rentPeriodField).click();

        String periodLocator = String.format(RENT_PERIOD_PATTERN, period);

        WebElement periodElement = driver.findElement(By.xpath(periodLocator));
        scrollToElement(periodElement);
        periodElement.click();
    }

    public void setScooterColour(String colour) {
        String colourLocator = String.format(SCOOTER_COLOUR_PATTERN, colour);
        driver.findElement(By.xpath(colourLocator)).click();
    }

    public void setCommentField(String comment) {
        driver.findElement(commentField).clear();
        driver.findElement(commentField).sendKeys(comment);
    }

    public void orderButtonClick() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(orderButton));
        driver.findElement(orderButton).click();
    }

    public void confirmationButtonClick() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(confirmationButton));
        driver.findElement(confirmationButton).click();
    }

    public boolean checkWindowWithOrderNumber() {
         return driver.findElement(windowWithOrderNumber).isDisplayed();
    }

    public void fillOrderFormFirstPage(String name, String surname, String address, String phoneNumber) {
        setNameField(name);
        setSurnameField(surname);
        setAddressField(address);
        setMetroStationList();
        setPhoneNumberField(phoneNumber);
        furtherButtonClick();
    }

    public void fillOrderFormSecondPage(String date, String period, String colour, String comment) {
        setDeliveryDateField(date);
        setRentPeriodField(period);
        setScooterColour(colour);
        setCommentField(comment);
        orderButtonClick();
        confirmationButtonClick();
    }

    private void scrollToElement(WebElement element) {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView", element);
    }
}
