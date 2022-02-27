package step;


import context.AutoContext;
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
        elements.stream()
                .filter(GreetFilter::isAllowedSchool)
                .forEach(element -> {
                    ElementUtils.showElementInfoAtRecommendPage(element);
                    WebElement greetButton = element.findElement(By.className("btn-greet"));
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", greetButton);
                    ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-200)");
                    ThreadUtils.SafeSleeping(5);
                    greetButton.click();
                    ThreadUtils.SafeSleeping(5);
                    AutoContext.greetNames.add(ElementUtils.getNameAtRecommendPage(element));
                });
        System.out.println("current job finish");
        return true;
    }
}
