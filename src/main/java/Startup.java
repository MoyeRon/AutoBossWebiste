import config.ConfigInit;
import step.*;

public class Startup {
    public static void main(String[] args) {
        ConfigInit.driverConfigInit("edge");
        ConfigInit.jobFilterConfigInit();
        ConfigInit.schoolFilterConfigInit();
        ConfigInit.messageConfigInit();
        StepManager stepManager = new StepManager();
        stepManager.addStep(new OpenAndLoginStep())
//                .addStep(new JobSelectStep())
//                .addStep(new GreetStep())
                .addStep(new CommunicateStep());
        stepManager.startStep();
    }
}
