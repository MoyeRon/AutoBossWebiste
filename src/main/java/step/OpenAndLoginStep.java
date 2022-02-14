package step;


import utils.ListUtils;
import utils.ThreadUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class OpenAndLoginStep extends Step {
    private static final Logger LOGGER = LogManager.getLogger(OpenAndLoginStep.class);

    public OpenAndLoginStep() {
        super();
        stepName = "Login Step";
    }

    @Override
    public boolean doStep() {

        driver.get("https://www.zhipin.com/web/boss/recommend");
        ThreadUtils.SafeSleeping(5);
        WebElement loginButton = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[1]/div[4]/div/a[5]"));
        loginButton.click();

        while (true) {
            ThreadUtils.SafeSleeping(5);
            String currentUrl = driver.getCurrentUrl();
            if (currentUrl.equals("https://www.zhipin.com/web/boss/index")) {
                break;
            }
        }
        ThreadUtils.SafeSleeping(5);
        if (!ListUtils.isEmptyList(driver.findElements(By.xpath("/html/body/div[11]/div[2]/div[2]/div/img")))) {
            WebElement closeElement = driver.findElement(By.xpath("/html/body/div[11]/div[2]/div[1]/span/i"));
            closeElement.click();
        }
        ThreadUtils.SafeSleeping(5);
        WebElement searchButton = driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[1]/div[1]/dl[2]/dt/a"));
        searchButton.click();
        return true;
    }
}
