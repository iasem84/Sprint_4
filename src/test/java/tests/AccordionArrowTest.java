package tests;

import org.junit.Assert;
import org.junit.Test;
import ru.yandex.practicum.pageobject.MainPage;

public class AccordionArrowTest extends BaseUITest{

    @Test
    public void checkAccordionArrowClick() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage();
        mainPage.AccordionArrowClick();
    }

}
