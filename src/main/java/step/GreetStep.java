package step;


import filter.GreetFilter;
import utils.ElementUtils;
import utils.ListUtils;
import utils.ThreadUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.List;

public class GreetStep extends Step {
    public GreetStep() {
        super();
        stepName = "Greet Step";
    }

    @Override
    public boolean doStep() {
        ThreadUtils.SafeSleeping(5);
        driver.switchTo().frame("recommendFrame");
        WebElement mainArea = driver.findElement(By.xpath("/html/body/div/div/div/div/div[2]/div/div/div/div/ul"));
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;

        while (ListUtils.isEmptyList(driver.findElements(By.className("nomore")))) {
            javascriptExecutor.executeScript("arguments[0].scrollIntoView(0);", mainArea);
            ThreadUtils.SafeSleeping(5);
        }


        List<WebElement> elements = mainArea.findElements(By.xpath("//*[@id=\"recommend-list\"]/div/ul/li"));
        elements.forEach(element -> {
            List<String> times = ElementUtils.getSchoolTimes(element);
            List<String> school = ElementUtils.getSchoolNames(element);
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            for (int i = 0; i < school.size(); i++) {
                sb.append(times.get(i)).append(" ").append(school.get(i)).append(";");
            }
            sb.append("]");
            System.out.println("name:" + ElementUtils.getName(element) + ";wanted:" + ElementUtils.getWanted(element) + ";" + sb);
            if (GreetFilter.isAllowedSchool(element)) {
                WebElement greetButton = element.findElement(By.className("btn-greet"));
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", greetButton);
                ThreadUtils.SafeSleeping(5);
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", greetButton);
                System.out.println("say hi");
                ThreadUtils.SafeSleeping(5);
            }
        });
        return true;
    }
}
