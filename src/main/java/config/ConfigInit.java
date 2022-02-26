package config;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import context.AutoContext;
import filter.FilterEntity;
import filter.SchoolEntity;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.codec.CharEncoding;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
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

    public static void jobFilterConfigInit() {
        String resourcePath = ConfigInit.class.getClassLoader().getResource("").getPath() + File.separator + "job";
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

    public static void schoolFilterConfigInit() {
        String resourcePath = ConfigInit.class.getClassLoader().getResource("").getPath() + File.separator + "school";
        File schoolFile = FileUtils.getFile(new File(resourcePath), "school.json");
        String schoolDes = null;
        try {
            schoolDes = FileUtils.readFileToString(schoolFile, CharEncoding.UTF_8);
            Type type = new TypeToken<ArrayList<SchoolEntity>>() {
            }.getType();
            List<SchoolEntity> schools = GSON.fromJson(schoolDes, type);
            AutoContext.schoolEntities.addAll(schools);
        } catch (IOException e) {
            LOGGER.error("read school description error", e);
        }

    }
}
