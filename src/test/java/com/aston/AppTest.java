package com.aston;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.jupiter.api.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest {
    private static WebDriver driver;

    @BeforeAll
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.mts.by");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement cookieAgreeButton = driver.findElement(By.id("cookie-agree"));
        cookieAgreeButton.click();
    }

    @AfterEach
    public void returnToStart() {
        driver.get("http://mts.by");
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }

    @Test
    @DisplayName("Проверка названия указанного блока")
    public void testBlockTitle() {
            WebElement titleElement = driver.findElement(By.xpath("//div[@class=\"pay__wrapper\"]//h2"));
            String text = titleElement.getText();
            assertEquals("Онлайн пополнение\nбез комиссии", text, "Названия не совпадают");
    }

    @Test
    @DisplayName("Проверка наличия логотипов платежных систем")
    public void testPaymentSystemLogos() {
        String[] logosAltTexts = {
                "Visa",
                "Verified By Visa",
                "MasterCard",
                "MasterCard Secure Code",
                "Белкарт"
        };

        for (String altText : logosAltTexts) {
            WebElement logo = driver.findElement(By.xpath("//img[@alt='" + altText + "']"));
            assertNotNull(logo, "Логотип " + altText + " не найден");
        }
    }

    @Test
    @DisplayName("Проверка работы ссылки \"Подробнее о сервисе\"")
    public void testMoreInfoLink() {
        WebElement moreInfoLink = driver.findElement(By.xpath("//div[@class=\"pay__wrapper\"]//a"));
        moreInfoLink.click();
        assertEquals("https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/",
                driver.getCurrentUrl(), "URL страницы не соответствует ожидаемому");
    }

    @Test
    @DisplayName("Проверка работы кнопки \"Продолжить\"")
    public void testContinueButton() {
        WebElement phoneNumber = driver.findElement(By.id("connection-phone"));
        phoneNumber.sendKeys("297777777");
        WebElement connectionSum = driver.findElement(By.id("connection-sum"));
        connectionSum.sendKeys("500 ");
        WebElement buttonContinue = driver.findElement(By.xpath("//*[@id=\"pay-connection\"]/button"));
        buttonContinue.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.className("bepaid-iframe")));
        WebElement paymentContainer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class=\"payment-page__container\"]")));
        assertTrue(paymentContainer.isDisplayed(), "Окно оплаты не отображается после нажатия на кнопку \"Продолжить\"");
    }
}