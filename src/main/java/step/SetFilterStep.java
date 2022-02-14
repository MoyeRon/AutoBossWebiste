package step;


import org.openqa.selenium.By;
import utils.ListUtils;
import utils.ThreadUtils;

public class SetFilterStep extends Step {
    public SetFilterStep() {
        super();
        stepName = "Set Filter";
    }

    @Override
    public boolean doStep() {
        ThreadUtils.SafeSleeping(5);
        driver.switchTo().frame("recommendFrame");
        if (!ListUtils.isEmptyList(driver.findElements(By.className("iboss-warning-fill")))) {
            driver.findElement(By.className("recover")).click();
            return true;
        }

        return super.doStep();
    }
}
