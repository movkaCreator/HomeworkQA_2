package com.aston;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.jupiter.api.*;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class TestMts {
    private static WebDriver driver;
    private static PageMts pageMts;

    @BeforeAll
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.get("https://www.mts.by");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.id(PageMts.btnCookie)).click();
        pageMts = new PageMts(driver);
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
        String text = pageMts.getNameOfBlock();
        assertEquals("Онлайн пополнение\nбез комиссии", text, "Названия не совпадают.");
    }

    @Test
    @DisplayName("Проверка наличия логотипов платежных систем")
    public void testPaymentSystemLogos() {
        assertAll(
                () -> assertTrue(pageMts.checkVisibilityOfVisa()),
                () -> assertTrue(pageMts.checkVisibilityOfVerifiedByVisa()),
                () -> assertTrue(pageMts.checkVisibilityOfMastercard()),
                () -> assertTrue(pageMts.checkVisibilityOfMastercardSecureCode()),
                () -> assertTrue(pageMts.checkVisibilityOfBelcard())
        );
    }

    @Test
    @DisplayName("Проверка работы ссылки «Подробнее о сервисе»")
    public void testMoreInfoLink() {
        pageMts.clickLinkOfPayingOrder();
        assertEquals("Порядок оплаты и безопасность интернет платежей",
                pageMts.getTitleOfPageWithPayingOrder(), "URL страницы не соответствует ожидаемому.");
    }

    @Test
    @DisplayName("Проверка работы кнопки \"Продолжить\"")
    public void testContinueButton() {
        pageMts.sendPhoneAndSumAndClickContinue();
        pageMts.openFrameAndFindLocatorWithPayDescription();
        Assertions.assertEquals("Оплата: Услуги связи Номер:375297777777",
                pageMts.getPayDescriptionText());
    }

    @Test
    @DisplayName("Проверка плейсхолдеров в графе \"Услуги связи\"")
    public void checkPlaceholdersForCommunicationServices() {
        assertAll(
                () -> assertEquals("Услуги связи",
                        pageMts.getTextOfSelectedService()),
                () -> assertEquals("Номер телефона",
                        pageMts.getAttributeOfPhone()),
                () -> assertEquals("Сумма",
                        pageMts.getAttributeOfAmount()),
                () -> assertEquals("E-mail для отправки чека",
                        pageMts.getAttributeOfEmail())
        );
    }

    @Test
    @DisplayName("Проверка плейсхолдеров в графе \"Домашний интернет\"")
    public void checkPlaceholdersForHomeInternet() {
        pageMts.clickSelectButton();
        pageMts.clickHomeInternetOption();
        assertAll(
                () -> assertEquals("Домашний интернет",
                        pageMts.getTextOfSelectedService()),
                () -> assertEquals("Номер абонента",
                        pageMts.getAttributeOfInternetPhone()),
                () -> assertEquals("Сумма",
                        pageMts.getAttributeOfAmount()),
                () -> assertEquals("E-mail для отправки чека",
                        pageMts.getAttributeOfEmail())
        );
    }

    @Test
    @DisplayName("Проверка плейсхолдеров в графе \"Рассрочка\"")
    public void checkPlaceholdersForInstallment() {
        pageMts.clickSelectButton();
        pageMts.clickInstallmentOption();
        assertAll(
                () -> assertEquals("Рассрочка",
                        pageMts.getTextOfSelectedService()),
                () -> assertEquals("Номер счета на 44",
                        pageMts.getAttributeOfScoreInstalment()),
                () -> assertEquals("Сумма",
                        pageMts.getAttributeOfAmount()),
                () -> assertEquals("E-mail для отправки чека",
                        pageMts.getAttributeOfEmail())
        );
    }

    @Test
    @DisplayName("Проверка плейсхолдеров в графе \"Задолженность\"")
    public void checkPlaceholdersForDebt() {
        pageMts.clickSelectButton();
        pageMts.clickDebtOption();
        assertAll(
                () -> assertEquals("Задолженность",
                        pageMts.getTextOfSelectedService()),
                () -> assertEquals("Номер счета на 2073",
                        pageMts.getAttributeOfScoreArrears()),
                () -> assertEquals("Сумма",
                        pageMts.getAttributeOfAmount()),
                () -> assertEquals("E-mail для отправки чека",
                        pageMts.getAttributeOfEmail())
        );
    }

    @Test
    @DisplayName("Проверка корректного отбражения всех полей в графе \"Услуги связи\"")
    public void fullCheckCommunicationServices() {
        pageMts.sendPhoneAndSumAndClickContinue();
        pageMts.openFrameAndFindLocatorWithPayDescription();
        assertAll(
                () -> assertEquals("10.00 BYN", pageMts.getPayDescriptionCost()),
                () -> assertEquals("Оплатить 10.00 BYN", pageMts.getBtnPayText()),
                () -> assertTrue(pageMts.getPayDescriptionText().contains("297777777")),
                () -> assertEquals("Номер карты", pageMts.getCreditCardNumberWhenEmpty()),
                () -> assertEquals("Срок действия", pageMts.getExpirationDateWhenEmpty()),
                () -> assertEquals("Имя держателя (как на карте)", pageMts.getHolderNameWhenEmpty()),
                () -> assertEquals("CVC", pageMts.getCvcWhenEmpty()),
                () -> assertTrue(pageMts.checkMastercardLogo()),
                () -> assertTrue(pageMts.checkVisaLogo()),
                () -> assertTrue(pageMts.checkBelcardLogo()),
                () -> assertTrue(pageMts.checkMirLogo()),
                () -> assertTrue(pageMts.checkMaestroLogo())
        );
    }
}