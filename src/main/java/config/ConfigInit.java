package config;

import com.google.gson.Gson;
import context.AutoContext;
import filter.FilterEntity;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.CharEncoding;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class ConfigInit {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigInit.class);

    private static final Gson GSON = new Gson();

    public static void driverConfigInit(String browserName) {
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

    public static void filterConfigInit() {
        String resourcePath = ConfigInit.class.getClassLoader().getResource("").getPath();
        List<File> jsonFiles = new ArrayList<>(FileUtils.listFiles(new File(resourcePath), new String[]{"json"}, false));
        try {
            for (File jsonFile : jsonFiles) {
                String jobDes = FileUtils.readFileToString(jsonFile, CharEncoding.UTF_8);
                FilterEntity filter = GSON.fromJson(jobDes, FilterEntity.class);
                AutoContext.filterEntities.add(filter);
            }
        } catch (IOException e) {
            LOGGER.error("read job description error", e);
        }
    }
}
