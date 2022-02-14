package step;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;
import java.util.List;

public class StepManager {
    private static final Logger LOGGER = LogManager.getLogger(StepManager.class);


    private List<Step> steps = new LinkedList<>();

    public StepManager StepMangerInitialization() {
        steps = new LinkedList<>();
        return this;
    }

    public StepManager StepMangerInitialization(List<Step> steps) {
        this.steps = steps;
        return this;
    }

    public StepManager addStep(Step step) {
        steps.add(step);
        return this;
    }

    public StepManager addSteps(List<Step> steps) {
        this.steps.addAll(steps);
        return this;
    }

    public void startStep() {
        for (Step step : steps) {
            boolean finish = step.doStep();
            if (!finish) {
                LOGGER.error("step {} not finish", step.stepName);
                return;
            }
        }
    }

}
