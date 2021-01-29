package steps;

import common.Base;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import java.io.IOException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hooks {

    @After(order = 0)
    public static void screenShot(Scenario scenario) throws IOException {
        byte[] screenshot = ((TakesScreenshot) Base.driver).getScreenshotAs(OutputType.BYTES);
        scenario.embed(screenshot, "image/png");
    }
}
