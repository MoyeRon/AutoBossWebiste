package config;

import context.AutoContext;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DriverConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(DriverConfig.class);

    public static void DriverConfigInit(String browserName) {
        if (browserName.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            AutoContext.webDriver = new ChromeDriver();
        } else if (browserName.equals("edge")) {
            WebDriverManager.edgedriver().setup();
            AutoContext.webDriver = new EdgeDriver();
        } else {
            LOGGER.error("current browser {} not support", browserName);
        }
    }
}
