package utils.datosresult;

import java.util.ArrayList;

public class ResultTest {
    private String name;
    private int duration;
    private String startTime;
    private String result;
    private String scenario;
    private String driver;
    private ArrayList<Steps> steps;

    public ResultTest(String name, int duration, String startTime, String result, String scenario, String driver, ArrayList<Steps> steps) {
        this.name = name;
        this.duration = duration;
        this.startTime = startTime;
        this.result = result;
        this.scenario = scenario;
        this.driver = driver;
        this.steps = steps;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getScenario() {
        return scenario;
    }

    public void setScenario(String scenario) {
        this.scenario = scenario;
    }

    public ArrayList<Steps> getSteps() {
        return steps;
    }

    public void setSteps(ArrayList<Steps> steps) {
        this.steps = steps;
    }

    public static class Steps {
        private String step;
        private String description;
        private String screenshot;
        private String error;
        private String errorType;
        public Steps(String step, String description, String screenshot, String error, String errorType) {
            this.step = step;
            this.description = description;
            this.screenshot = screenshot;
            this.error = error;
            this.errorType = errorType;
        }

        public String getStep() {
            return step;
        }

        public void setStep(String step) {
            this.step = step;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getScreenshot() {
            return screenshot;
        }

        public void setScreenshot(String screenshot) {
            this.screenshot = screenshot;
        }

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }

        public String getErrorType() {
            return errorType;
        }

        public void setErrorType(String errorType) {
            this.errorType = errorType;
        }
    }
}
