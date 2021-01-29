package common;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.EnvironmentProperties;

public class Base {

	 public static WebDriver driver;
	    private static final long DEFAULT_TIME_SECONDS_WAIT = EnvironmentProperties.getInt("defaultWaitTime");

	    public static void setDriver(String browserName) {
	        driver = Browsers.setDriver(browserName);
	    }

	    public static void visitUrl(String urlName) {
	        String url = EnvironmentProperties.getValue(urlName);
	        driver.manage().timeouts().pageLoadTimeout(DEFAULT_TIME_SECONDS_WAIT, TimeUnit.SECONDS);
	        driver.get(url);
	    }

	    public static String getUrl() {
	        return driver.getCurrentUrl();
	    }

	    public static void fillInput(By locator, String data) {
	        isVisible(locator);
	        getElement(locator).clear();
	        waitForTextInElement(locator, "");
	        getElement(locator).sendKeys(data);
	    }

	    public static void click(By locator) {
	        isClickable(locator);
	        getElement(locator).click();
	    }

	    public static WebElement waitElement(By locator) {
	        return new WebDriverWait(driver, DEFAULT_TIME_SECONDS_WAIT)
	                .until(ExpectedConditions.presenceOfElementLocated(locator));
	    }

	    public static boolean checkIfVisible(By locator) {
	        return driver.findElements(locator).size() > 0;
	    }

	    public static boolean isVisible(By locator) {
	        new WebDriverWait(driver, DEFAULT_TIME_SECONDS_WAIT)
	                .until(ExpectedConditions.visibilityOf(getElement(locator)));
	        return getElement(locator).isDisplayed();
	    }

	    public static boolean isClickable(By locator) {
	        new WebDriverWait(driver, DEFAULT_TIME_SECONDS_WAIT)
	                .until(ExpectedConditions.elementToBeClickable(getElement(locator)));
	        return getElement(locator).isDisplayed() && getElement(locator).isEnabled();
	    }

	    public static void waitForTextInElement(By locator, String textToBeWait) {
	        new WebDriverWait(driver, DEFAULT_TIME_SECONDS_WAIT).until(ExpectedConditions.textToBe(locator, textToBeWait));
	    }

	    public static void changeWindow(int window) {
	        ArrayList<String> tabs = new ArrayList<String>(Base.driver.getWindowHandles());
	        Base.driver.switchTo().window(tabs.get(window));
	    }

	    public static WebElement getElement(By locator) {
	        return waitElement(locator);
	    }

	    public static String getText(By locator) {
	        isVisible(locator);
	        WebElement textLocator = Base.waitElement(locator);
	        return textLocator.getText();
	    }
}
