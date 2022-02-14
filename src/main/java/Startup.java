import config.ConfigInit;
import step.GreetStep;
import step.JobSelectStep;
import step.OpenAndLoginStep;
import step.StepManager;

public class Startup {
    public static void main(String[] args) {
        ConfigInit.driverConfigInit("edge");
        ConfigInit.filterConfigInit();
        StepManager stepManager = new StepManager();
        stepManager.addStep(new OpenAndLoginStep())
                .addStep(new JobSelectStep())
                .addStep(new GreetStep());
        stepManager.startStep();
    }
}
