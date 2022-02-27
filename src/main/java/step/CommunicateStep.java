package step;

import context.AutoContext;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.ListUtils;
import utils.ThreadUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @name:
 * @link:
 * @author: taoyouwei
 * @date: 2022/2/27
 **/
public class CommunicateStep extends Step {
    public CommunicateStep() {
        super();
        stepName = "Communicate";
    }

    @Override
    public boolean doStep() {
        ThreadUtils.SafeSleeping(5);
        driver.switchTo().defaultContent();
        ThreadUtils.SafeSleeping(3);
        WebElement communicateElement = driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[2]/div[1]/dl[4]/dt/a"));
        communicateElement.click();
        ThreadUtils.SafeSleeping(5);
        WebElement communicateListElement = driver.findElement(By.className("geek-list-scroll-wrap"));
        List<WebElement> elements = communicateListElement.findElements(By.className("geek-item"))
                .stream()
                .filter(ele -> ListUtils.isEmptyList(ele.findElements(By.className("isTop"))))
                .filter(ele -> {
                    String text = ele.findElement(By.tagName("p")).getText();
                    String name = ele.findElement(By.className("name")).getText();
                    System.out.println("name:" + name + ";text:" + text);
                    return text.startsWith("您正在与牛人");
                })
                .collect(Collectors.toList());
        WebElement inputElement = driver.findElement(By.className("bosschat-chat-input"));
        WebElement sendElement = driver.findElement(By.className("btn-send"));
        elements.forEach(ele -> {
            ele.click();
            ThreadUtils.SafeSleeping(5);
            AutoContext.messages.forEach(message -> {
                inputElement.sendKeys(message.getMessage());
                sendElement.click();
                ThreadUtils.SafeSleeping(5);
            });
        });
        return super.doStep();
    }
}
