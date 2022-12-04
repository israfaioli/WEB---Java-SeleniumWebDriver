package common;

import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Browsers {

    public static WebDriver driver;
    private static ChromeOptions option;

    public static WebDriver setDriver(String browser) {
        switch (browser) {
            case "chromeHeadless":
                option = new ChromeOptions();
                option.addArguments("headless", "disable-gpu", "disable-popup-blocking", "no-sandbox",
                        "start-maximized", "window-size=1920,1080", "disable-dev-shm-usage");
                driver = new ChromeDriver(option);
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "chrome":
                option = new ChromeOptions();
                option.addArguments("start-maximized", "no-sandbox");
                driver = new ChromeDriver(option);
                break;
            case "chromeJenkins":
                option = new ChromeOptions();
                option.addArguments("headless", "disable-gpu", "disable-popup-blocking", "no-sandbox",
                        "start-maximized", "window-size=1920,1080", "disable-dev-shm-usage", "--ignore-certificate-errors");
                DesiredCapabilities capabilities = null;
                capabilities = DesiredCapabilities.chrome();
                capabilities.setCapability(ChromeOptions.CAPABILITY, option);
                try {
                    driver = new RemoteWebDriver(new URL("localhost:4444/wd/hub"), capabilities);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                break;
            default:
                option = new ChromeOptions();
                option.addArguments("--start-maximized");
                driver = new ChromeDriver(option);
                break;
        }
        return driver;
    }
}
