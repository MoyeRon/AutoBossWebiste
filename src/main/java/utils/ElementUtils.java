package utils;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class ElementUtils {

    public static List<String> getSchoolNamesAtRecommendPage(WebElement element) {
        WebElement eduElement = element.findElement(By.className("edu-exp-box"));
        List<WebElement> subEduElements = eduElement.findElements(By.tagName("li"));
        return subEduElements.stream().map(subEduElement -> {
            WebElement schoolElement = subEduElement.findElement(By.className("exp-content"));
            return schoolElement.getText().split("·")[0].trim();
        }).collect(Collectors.toList());
    }

    public static List<String> getSchoolTimesAtRecommendPage(WebElement element) {
        WebElement eduElement = element.findElement(By.className("edu-exp-box"));
        List<WebElement> subEduElements = eduElement.findElements(By.tagName("li"));
        return subEduElements.stream().map(subEduElement -> {
            WebElement schoolElement = subEduElement.findElement(By.className("date"));
            return schoolElement.getText();
        }).collect(Collectors.toList());
    }

    public static String getNameAtRecommendPage(WebElement element) {
        WebElement nameElement = element.findElement(By.className("name"));
        return nameElement.findElements(By.tagName("span")).get(0).getText();
    }

    public static String getWantedAtRecommendPage(WebElement element) {
        WebElement expectedElement = element.findElement(By.className("expect-box"));
        return expectedElement.findElements(By.tagName("span")).get(1).getText().split("·")[1].trim();
    }

    public static void showElementInfoAtRecommendPage(WebElement element) {
        List<String> times = ElementUtils.getSchoolTimesAtRecommendPage(element);
        List<String> school = ElementUtils.getSchoolNamesAtRecommendPage(element);
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < school.size(); i++) {
            sb.append(times.get(i)).append(" ").append(school.get(i)).append(";");
        }
        sb.append("]");
        System.out.println("name:" + ElementUtils.getNameAtRecommendPage(element) + ";wanted:" + ElementUtils.getWantedAtRecommendPage(element) + ";" + sb);
    }

    public static String getNameAtCommunicationPage(WebElement element) {
        return "";
    }

}
