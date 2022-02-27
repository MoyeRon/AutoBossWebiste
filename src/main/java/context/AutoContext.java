package context;

import filter.FilterEntity;
import filter.SchoolEntity;
import org.openqa.selenium.WebDriver;

import java.util.LinkedList;
import java.util.List;

public class AutoContext {
    public static WebDriver webDriver = null;

    public static List<FilterEntity> filterEntities = new LinkedList<>();

    public static List<SchoolEntity> schoolEntities = new LinkedList<>();

    public static List<String> greets = new LinkedList<>();

}
