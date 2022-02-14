package step;


import context.AutoContext;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.ThreadUtils;

import java.util.List;

public class JobSelectStep extends Step {
    public JobSelectStep() {
        super();
        stepName = "Job Select";
    }

    @Override
    public boolean doStep() {
        ThreadUtils.SafeSleeping(5);
        driver.switchTo().frame("recommendFrame");
        WebElement jobSelectElement = driver.findElement(By.className("job-selecter-wrap"));
        jobSelectElement.click();
        ThreadUtils.SafeSleeping(2);
        List<WebElement> jobItemElements = driver.findElements(By.className("job-item"));
        WebElement targetJobItem = jobItemElements.stream()
                .map(webElement -> webElement.findElement(By.tagName("span")))
                .filter(webElement -> webElement.getText().equals(AutoContext.filterEntities.get(0).getJobName()))
                .findFirst()
                .get();
        targetJobItem.click();
        return true;
    }
}
