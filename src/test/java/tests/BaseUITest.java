package tests;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

import static ru.yandex.practicum.Constants.MAIN_PAGE_URL;

public class BaseUITest {
    protected WebDriver driver;

    @Before
    public void startUp(){
        initChrome();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
        driver.get(MAIN_PAGE_URL);
    }

    @After
    public void tearDown(){
        driver.quit();
    }

    public void initChrome(){
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
    }

    public void initFirefox(){
        FirefoxOptions options = new FirefoxOptions();
        driver = new FirefoxDriver(options);
    }
}
