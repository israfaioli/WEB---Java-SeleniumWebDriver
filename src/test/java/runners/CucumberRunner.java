package runners;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import common.Base;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty", "html:target/cucumber", "json:target/cucumber.json"},
        monochrome = true,
        snippets = SnippetType.CAMELCASE,
        dryRun = false, strict = false,
        features = "src/test/resources/features",
        glue = {"steps" })

public class CucumberRunner {
	
    protected static WebDriver driver;

    @BeforeClass
    public static void setup() {
        String browserName = System.getProperty("browser");
        Base.setDriver(browserName);
    }

    @AfterClass
    public static void closeBrowser() {
        Base.driver.close();
    }

}
