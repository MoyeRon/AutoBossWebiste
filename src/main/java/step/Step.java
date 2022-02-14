package step;

import context.AutoContext;
import org.openqa.selenium.WebDriver;

public class Step {
    public String stepName;
    public WebDriver driver;

    public Step() {
        driver = AutoContext.webDriver;
    }

    public boolean doStep() {
        return true;
    }
}
