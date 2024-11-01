package tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.practicum.pageobject.OrderPage;
import ru.yandex.practicum.pageobject.MainPage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static ru.yandex.practicum.Constants.ORDER_PAGE_URL;

@RunWith(Parameterized.class)

public class ScooterOrderTest extends BaseUITest{
    private final String name;
    private final String surname;
    private final String address;
    private final String phoneNumber;
    private final String date;
    private final String period;
    private final String colour;
    private final String comment;

    public ScooterOrderTest(String name, String surname, String address, String phoneNumber, String date,
                            String period, String colour, String comment) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.period = period;
        this.colour = colour;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] getOrder() {
        return new Object[][] {
                {"Иван", "Бобров", "ул. Мирная, 18", "+77772333333", "26.11.2024", "четверо суток", "black", ""},
                {"Марго", "Имашева", "ул. Толе би 345", "89572333333", "11.11.2024", "двое суток", "grey", "без комментариев"},
                {"Батька", "Батькович", "Заводская 56", "87772333333", "05.11.2024", "сутки", "black", ""},
        };
    }

    @Test
    public void checkOrderByTopButton() {
        MainPage mainPage = new MainPage(driver);
        mainPage.topOrderButtonClick();

        OrderPage orderPage = new OrderPage(driver);
        orderPage.fillOrderFormFirstPage(name, surname, address, phoneNumber);
        orderPage.fillOrderFormSecondPage(date, period, colour, comment);

        assertTrue(orderPage.checkWindowWithOrderNumber());
    }

    @Test
    public void checkOrderByDownButton() {
        MainPage mainPage = new MainPage(driver);
        mainPage.downOrderButtonClick();

        String link = driver.getCurrentUrl();
        assertEquals(ORDER_PAGE_URL, link);
    }
}
