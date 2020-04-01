package gflwishes.base;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Generics extends EnhancedBaseClass {

    private WebDriver generalDriver;
    private WebDriverWait wait;

    public Generics(WebDriver baseDriver) {
        this.generalDriver = baseDriver;
        wait = new WebDriverWait(generalDriver, 45);
        log4j = Logger.getLogger("Generics");
    }

    public void clickOn(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        pause(1);
    }

    public void type(WebElement element, String text) {
        wait.until(ExpectedConditions.visibilityOfAllElements(element));
        element.clear();
        pause(1);
        element.sendKeys(text);
    }

    public void clear(WebElement element) {
        wait.until(ExpectedConditions.visibilityOfAllElements(element));
        element.clear();
    }

    public String getRandomCharacters(int length) {
        return RandomStringUtils.randomAlphabetic(length);
    }

    public boolean isDisplay(WebElement element) {
        wait.until(ExpectedConditions.visibilityOfAllElements(element));
        return element.isDisplayed();
    }

    public boolean isPresent(String locator) {
        try {
            return generalDriver.findElement(By.xpath(locator)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isPresent(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void moveTo(WebElement element) {
        wait.until(ExpectedConditions.visibilityOfAllElements(element));
        Actions action = new Actions(generalDriver);
        action.moveToElement(element).build().perform();
    }

    public String getText(WebElement element) {
        wait.until(ExpectedConditions.visibilityOfAllElements(element));
        return element.getText().trim();
    }

    public String getInnerText(WebElement element) {
        wait.until(ExpectedConditions.visibilityOfAllElements(element));
        return element.getAttribute("innerText").trim();
    }

    public String getValue(WebElement element) {
        wait.until(ExpectedConditions.visibilityOfAllElements(element));
        return element.getAttribute("value").trim();
    }

    public void waitForElementVisible(WebElement... element) {
        wait.until(ExpectedConditions.visibilityOfAllElements(element));
    }

    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) generalDriver).executeScript("arguments[0].scrollIntoView(true);", element);
        ((JavascriptExecutor) generalDriver).executeScript("window.scrollBy(0,-100)", "");
        pause(2);
    }

    public void scrollToTop() {
        WebElement element = generalDriver.findElement(By.tagName("body"));
        element.sendKeys(Keys.PAGE_UP);
        element.sendKeys(Keys.PAGE_UP);
        element.sendKeys(Keys.PAGE_UP);
    }

    public void scrollToBottom() {
        WebElement element = generalDriver.findElement(By.tagName("body"));
        element.sendKeys(Keys.PAGE_DOWN);
        element.sendKeys(Keys.PAGE_DOWN);
        element.sendKeys(Keys.PAGE_DOWN);
    }

    public void switchToWindow() {
        System.out.println(generalDriver.getWindowHandles());
        for (String winHandle : generalDriver.getWindowHandles()) {
            generalDriver.switchTo().window(winHandle);
        }
        System.out.println(generalDriver.getWindowHandle());
    }

    public void closeCurrentWindow() {
        generalDriver.close();
        switchToWindow();
    }

    public void clickOnJS(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) generalDriver;
        executor.executeScript("arguments[0].click();", element);
    }

    public void pause(int seconds) {
        try {
            Thread.sleep(1000 * seconds);
        } catch (Exception e) {
            System.out.println("Pause failed");
        }
    }

    public void mouseHover(WebElement element) {
        Actions builder = new Actions(generalDriver);
        builder.moveToElement(element).build().perform();
    }

    public int getRandomBetween(int min, int max) {
        return (int) (Math.random() * ((max - min) + 1)) + min;
    }

    public void isClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public boolean getRandomBoolean() {
        return Math.random() < 0.5;
    }

    public String getAttribute(WebElement element, String attribute) {
        wait.until(ExpectedConditions.visibilityOfAllElements(element));
        return element.getAttribute(attribute);
    }

    public String getOrdinal(int number) {
        String[] sufixes = new String[]{"th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th"};
        switch (number % 100) {
            case 11:
            case 12:
            case 13:
                return number + "th";
            default:
                return number + sufixes[number % 10];

        }
    }

    public boolean isAttributePresent(WebElement element, String attribute) {
        boolean result = false;
        try {
            String value = element.getAttribute(attribute);
            if (value != null) {
                result = true;
            }
        } catch (Exception e) {
        }

        return result;
    }

    public void openFM() {
        generalDriver.get(FM_URL);
    }
}