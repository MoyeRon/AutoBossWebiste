import config.DriverConfig;
import step.GreetStep;
import step.OpenAndLoginStep;
import step.StepManager;

public class Startup {
    public static void main(String[] args) {
        DriverConfig.DriverConfigInit("edge");
        StepManager stepManager = new StepManager();
        stepManager.addStep(new OpenAndLoginStep())
                .addStep(new GreetStep());
        stepManager.startStep();
    }
}
