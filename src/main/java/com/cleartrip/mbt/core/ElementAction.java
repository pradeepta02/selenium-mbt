package com.cleartrip.mbt.core;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementAction {

    private WebDriver driver;

    public ElementAction(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement fetchElement(String loc) {

        final LocatorStrategy strategy = LocatorStrategy.valueOf(loc.split(",")[0]);
        final String locator = loc.split(",")[1];

        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(30, TimeUnit.SECONDS).pollingEvery(5,
                        TimeUnit.SECONDS).ignoring(NoSuchElementException.class);

        WebElement element = wait.until(new Function<WebDriver, WebElement>() {

            public WebElement apply(WebDriver driver) {
                By by = null;

                switch (strategy) {
                case ID:
                    by = By.id(locator);
                    break;
                case XPATH:
                    by = By.xpath(locator);
                    break;
                case NAME:
                    by = By.name(locator);
                    break;
                case LINKTEXT:
                    by = By.linkText(locator);
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported locator type!");
                }

                return driver.findElement(by);
            }
        });

        return element;
    }

    public void click(String locator) {

        WebElement element = fetchElement(locator);

        try {
            new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(element));
            element.click();
        } catch (TimeoutException ex) {

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", element);

        } catch (StaleElementReferenceException ex) {
            ex.printStackTrace();
        } catch (NoSuchElementException ex) {
            ex.printStackTrace();
        }
    }

    public void hover(String locator) {
        WebElement element = fetchElement(locator);

        try {
            Actions builder = new Actions(driver);
            builder.moveToElement(element).perform();
        } catch (StaleElementReferenceException ex) {
            ex.printStackTrace();
        } catch (NoSuchElementException ex) {
            ex.printStackTrace();
        }
    }

    public void uncheck(String locator) {
        WebElement element = fetchElement(locator);

        try {
            new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(element));
            if (element.isSelected()) {
                element.click();
            }
        } catch (StaleElementReferenceException ex) {
            ex.printStackTrace();
        } catch (NoSuchElementException ex) {
            ex.printStackTrace();
        }
    }

    public void type(String locator, String textToType) {

        WebElement element = fetchElement(locator);

        try {
            new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOf(element));
            element.sendKeys(textToType);
        } catch (StaleElementReferenceException ex) {
            ex.printStackTrace();
        } catch (NoSuchElementException ex) {
            ex.printStackTrace();
        }
    }

    public void pressKey(String locator, Keys key) {

        WebElement element = fetchElement(locator);

        try {
            new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOf(element));
            element.sendKeys(key);
        } catch (StaleElementReferenceException ex) {
            ex.printStackTrace();
        } catch (NoSuchElementException ex) {
            ex.printStackTrace();
        }
    }

    public void clear(String locator) {

        WebElement element = fetchElement(locator);

        try {
            new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOf(element));
            element.clear();
        } catch (StaleElementReferenceException ex) {
            ex.printStackTrace();
        } catch (NoSuchElementException ex) {
            ex.printStackTrace();
        }
    }

    public void sleep(int timeout) {
        try {
            Thread.sleep(timeout);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

enum LocatorStrategy {
    ID, XPATH, NAME, CSSSELECTOR, LINKTEXT, TAGNAME
}
