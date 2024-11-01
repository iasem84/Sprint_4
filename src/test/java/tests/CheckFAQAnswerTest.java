package tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.practicum.pageobject.MainPage;

import static org.junit.Assert.assertEquals;
import static ru.yandex.practicum.Constants.*;

@RunWith(Parameterized.class)

public class CheckFAQAnswerTest extends BaseUITest{
    private final String question;
    private final String expectedAnswer;

    public CheckFAQAnswerTest(String question, String expectedAnswer) {
        this.question = question;
        this.expectedAnswer = expectedAnswer;
    }

    @Parameterized.Parameters
    public static Object[][] getQuestion() {
        return new Object[][] {
                {HOW_MUCH_COST_QUESTION, HOW_MUCH_COST_ANSWER},
                {SEVERAL_SCOOTERS_QUESTION, SEVERAL_SCOOTERS_ANSWER},
                {RENTAL_TIME_QUESTION, RENTAL_TIME_ANSWER},
                {SCOOTER_FOR_TODAY_QUESTION, SCOOTER_FOR_TODAY_ANSWER},
                {EXTEND_OR_RETURN_EARLIER_QUESTION, EXTEND_OR_RETURN_EARLIER_ANSWER},
                {SCOOTER_CHARGER_QUESTION, SCOOTER_CHARGER_ANSWER},
                {ORDER_CANCELLATION_QUESTION, ORDER_CANCELLATION_ANSWER},
                {OUTSIDE_THE_MRR_QUESTION, OUTSIDE_THE_MRR_ANSWER},
        };
    }

    @Test
    public void checkFAQAnswer() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickFAQQuestion(question);
        String actualAnswer = mainPage.getFAQAnswer();

        assertEquals(actualAnswer, expectedAnswer);
    }

}
