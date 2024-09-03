package com.aston;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class PageMts {
    WebDriver driver;

    public static final String btnCookie = "cookie-agree";
    public static final String nameOfBlock = "//div[@class='pay__wrapper']/h2";
    public static final String payPartnerVisa = "//img[@alt='Visa']";
    public static final String payPartnerVerifiedByVisa = "//img[@alt='Verified By Visa']";
    public static final String payPartnerMastercard = "//img[@alt='MasterCard']";
    public static final String payPartnerMastercardSecureCode = "//img[@alt='MasterCard Secure Code']";
    public static final String payPartnerBelcard = "//img[@alt='Белкарт']";
    public static final String linkAboutServices = "//div[@class='pay__wrapper']/a";
    public static final String titleOfPagePaymentOrder = "//span[@class='breadcrumbs__link']/span";
    public static final String connectionPhoneId = "connection-phone";
    public static final String connectionSumId = "connection-sum";
    public static final String btnContinue = "//*[@id=\"pay-connection\"]/button";
    public static final String payDescriptionText = "//div[@class='pay-description__text']";
    public static final String iframe = "//iframe[@class='bepaid-iframe']";

    public static final String selectButton = "//div[@class=\"select__wrapper\"]/button/span";
    public static final String phoneId = "connection-phone";
    public static final String amountId = "connection-sum";
    public static final String emailId = "connection-email";
    public static final String homeInternetOption = "//ul[@class='select__list']/li/p[contains(text(),'Домашний интернет')]";
    public static final String internetPhoneId = "internet-phone";
    public static final String installmentOption = "//ul[@class='select__list']/li/p[contains(text(),'Рассрочка')]";
    public static final String scoreInstalmentId = "score-instalment";
    public static final String debtOption = "//ul[@class='select__list']/li/p[contains(text(),'Задолженность')]";
    public static final String scoreArrearsId = "score-arrears";

    public static final String payDescriptionCost = "//div[@class='pay-description__cost']/span";
    public static final String btnPay = "//div[@class='card-page__card']/button";
    public static final String creditCardNumber = "//div[@class=\"app-input ng-tns-c46-1\"]/div/div/label";
    public static final String expirationDate = "//div[@class='app-input ng-tns-c46-4']/div/div/label";
    public static final String holderName = "//div[@class=\"app-input ng-tns-c46-3\"]/div/div/label";
    public static final String cvc = "//div[@class='app-input ng-tns-c46-5']/div/div/label";
    public static final String masterCardLogoOnFrame = "//div[contains(@class, 'cards-brands')]/img[contains(@src, 'mastercard')]";
    public static final String visaLogoOnFrame = "//div[contains(@class, 'cards-brands')]/img[contains(@src, 'visa')]";
    public static final String belcardLogoOnFrame = "//div[contains(@class, 'cards-brands')]/img[contains(@src, 'belkart')]";
    public static final String mirLogoOnFrame = "//div[contains(@class, 'cards-brands_random')]/img[contains(@src, 'mir')]";
    public static final String maestroLogoOnFrame = "//div[contains(@class, 'cards-brands_random')]/img[contains(@src, 'maestro')]";

    public PageMts (WebDriver driver) {
        this.driver = driver;
    }

    public String getNameOfBlock() {
        return driver.findElement(By.xpath(PageMts.nameOfBlock)).getText();
    }

    public boolean checkVisibilityOfVisa() {
        return driver.findElement(By.xpath(PageMts.payPartnerVisa)).isDisplayed();
    }

    public boolean checkVisibilityOfVerifiedByVisa() {
        return driver.findElement(By.xpath(PageMts.payPartnerVerifiedByVisa)).isDisplayed();
    }

    public boolean checkVisibilityOfMastercard() {
        return driver.findElement(By.xpath(PageMts.payPartnerMastercard)).isDisplayed();
    }

    public boolean checkVisibilityOfMastercardSecureCode() {
        return driver.findElement(By.xpath(PageMts.payPartnerMastercardSecureCode)).isDisplayed();
    }

    public boolean checkVisibilityOfBelcard() {
        return driver.findElement(By.xpath(PageMts.payPartnerBelcard)).isDisplayed();
    }

    public void clickLinkOfPayingOrder() {
        driver.findElement(By.xpath(PageMts.linkAboutServices)).click();
    }

    public String getTitleOfPageWithPayingOrder() {
        return driver.findElement(By.xpath(PageMts.titleOfPagePaymentOrder)).getText();
    }

    public void sendPhoneAndSumAndClickContinue() {
        driver.findElement(By.id(PageMts.connectionPhoneId)).sendKeys("297777777");
        driver.findElement(By.id(PageMts.connectionSumId)).sendKeys("10");
        driver.findElement(By.xpath(PageMts.btnContinue)).click();
    }

    public void openFrameAndFindLocatorWithPayDescription() {
        WebElement iframe = driver.findElement(By.xpath(PageMts.iframe));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.switchTo().frame(iframe);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(PageMts.payDescriptionText))));
    }

    public String getPayDescriptionText() {
        return driver.findElement(By.xpath(PageMts.payDescriptionText)).getText();
    }



    public String getTextOfSelectedService() {
        return driver.findElement(By.xpath(selectButton)).getText();
    }

    public String getAttributeOfPhone() {
        return driver.findElement(By.id(phoneId)).getAttribute("placeholder");
    }

    public String getAttributeOfAmount() {
        return driver.findElement(By.id(amountId)).getAttribute("placeholder");
    }

    public String getAttributeOfEmail() {
        return driver.findElement(By.id(emailId)).getAttribute("placeholder");
    }

    public void clickSelectButton() {
        driver.findElement(By.xpath(selectButton)).click();
    }

    public void clickHomeInternetOption() {
        driver.findElement(By.xpath(homeInternetOption)).click();
    }

    public String getAttributeOfInternetPhone() {
        return driver.findElement(By.id(internetPhoneId)).getAttribute("placeholder");
    }

    public void clickInstallmentOption() {
        driver.findElement(By.xpath(installmentOption)).click();
    }

    public String getAttributeOfScoreInstalment() {
        return driver.findElement(By.id(scoreInstalmentId)).getAttribute("placeholder");
    }

    public void clickDebtOption() {
        driver.findElement(By.xpath(debtOption)).click();
    }

    public String getAttributeOfScoreArrears() {
        return driver.findElement(By.id(scoreArrearsId)).getAttribute("placeholder");
    }



    public String getPayDescriptionCost() {
        return driver.findElement(By.xpath(payDescriptionCost)).getText();
    }

    public String getBtnPayText() {
        return driver.findElement(By.xpath(btnPay)).getText();
    }

    public String getCreditCardNumberWhenEmpty() {
        return driver.findElement(By.xpath(creditCardNumber)).getText();
    }

    public String getExpirationDateWhenEmpty() {
        return driver.findElement(By.xpath(expirationDate)).getText();
    }

    public String getHolderNameWhenEmpty() {
        return driver.findElement(By.xpath(holderName)).getText();
    }

    public String getCvcWhenEmpty() {
        return driver.findElement(By.xpath(cvc)).getText();
    }

    public boolean checkMastercardLogo() {
        return driver.findElement(By.xpath(masterCardLogoOnFrame)).isDisplayed();
    }

    public boolean checkVisaLogo() {
        return driver.findElement(By.xpath(visaLogoOnFrame)).isDisplayed();
    }

    public boolean checkBelcardLogo() {
        return driver.findElement(By.xpath(belcardLogoOnFrame)).isDisplayed();
    }

    public boolean checkMirLogo() {
        return waitForLogoVisibility(mirLogoOnFrame);
    }

    public boolean checkMaestroLogo() {
        return waitForLogoVisibility(maestroLogoOnFrame);
    }

    public boolean waitForLogoVisibility(String logoXpath) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement logo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(logoXpath)));
        return logo.isDisplayed();
    }
}