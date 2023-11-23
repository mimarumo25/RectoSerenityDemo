package utils.datosresult;

import java.util.ArrayList;

/**
 * Clase que representa los resultados de una prueba.
 */
public class ResultTest {
    private String name;
    private int duration;
    private String startTime;
    private String result;
    private String scenario;
    private String driver;
    private ArrayList<Steps> steps;

    /**
     * Constructor de la clase ResultTest.
     *
     * @param name     Nombre de la prueba.
     * @param duration Duración de la prueba en milisegundos.
     * @param startTime Hora de inicio de la prueba.
     * @param result   Resultado de la prueba (SUCCESS, ERROR, FAILURE, SKIPPED, etc.).
     * @param scenario Descripción del escenario de la prueba.
     * @param driver   Controlador utilizado para la ejecución de la prueba.
     * @param steps    Lista de pasos de la prueba.
     */
    public ResultTest(String name, int duration, String startTime, String result, String scenario, String driver, ArrayList<Steps> steps) {
        this.name = name;
        this.duration = duration;
        this.startTime = startTime;
        this.result = result;
        this.scenario = scenario;
        this.driver = driver;
        this.steps = steps;
    }

    /**
     * Obtiene el controlador utilizado para la ejecución de la prueba.
     *
     * @return Controlador utilizado para la ejecución de la prueba.
     */
    public String getDriver() {
        return driver;
    }

    /**
     * Establece el controlador utilizado para la ejecución de la prueba.
     *
     * @param driver Controlador utilizado para la ejecución de la prueba.
     */
    public void setDriver(String driver) {
        this.driver = driver;
    }

    /**
     * Obtiene el nombre de la prueba.
     *
     * @return Nombre de la prueba.
     */
    public String getName() {
        return name;
    }

    /**
     * Establece el nombre de la prueba.
     *
     * @param name Nombre de la prueba.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtiene la duración de la prueba en milisegundos.
     *
     * @return Duración de la prueba en milisegundos.
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Establece la duración de la prueba en milisegundos.
     *
     * @param duration Duración de la prueba en milisegundos.
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * Obtiene la hora de inicio de la prueba.
     *
     * @return Hora de inicio de la prueba.
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * Establece la hora de inicio de la prueba.
     *
     * @param startTime Hora de inicio de la prueba.
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     * Obtiene el resultado de la prueba.
     *
     * @return Resultado de la prueba.
     */
    public String getResult() {
        return result;
    }

    /**
     * Establece el resultado de la prueba.
     *
     * @param result Resultado de la prueba.
     */
    public void setResult(String result) {
        this.result = result;
    }

    /**
     * Obtiene la descripción del escenario de la prueba.
     *
     * @return Descripción del escenario de la prueba.
     */
    public String getScenario() {
        return scenario;
    }

    /**
     * Establece la descripción del escenario de la prueba.
     *
     * @param scenario Descripción del escenario de la prueba.
     */
    public void setScenario(String scenario) {
        this.scenario = scenario;
    }

    /**
     * Obtiene la lista de pasos de la prueba.
     *
     * @return Lista de pasos de la prueba.
     */
    public ArrayList<Steps> getSteps() {
        return steps;
    }

    /**
     * Establece la lista de pasos de la prueba.
     *
     * @param steps Lista de pasos de la prueba.
     */
    public void setSteps(ArrayList<Steps> steps) {
        this.steps = steps;
    }

    /**
     * Clase interna que representa un paso de la prueba.
     */
    public static class Steps {
        private String step;
        private String description;
        private String screenshot;
        private String error;
        private String errorType;

        /**
         * Constructor de la clase Steps.
         *
         * @param step       Paso de la prueba.
         * @param description Descripción del paso de la prueba.
         * @param screenshot Captura de pantalla del paso de la prueba.
         * @param error      Error del paso de la prueba.
         * @param errorType  Tipo de error del paso de la prueba.
         */
        public Steps(String step, String description, String screenshot, String error, String errorType) {
            this.step = step;
            this.description = description;
            this.screenshot = screenshot;
            this.error = error;
            this.errorType = errorType;
        }

        /**
         * Obtiene el paso de la prueba.
         *
         * @return Paso de la prueba.
         */
        public String getStep() {
            return step;
        }

        /**
         * Establece el paso de la prueba.
         *
         * @param step Paso de la prueba.
         */
        public void setStep(String step) {
            this.step = step;
        }

        /**
         * Obtiene la descripción del paso de la prueba.
         *
         * @return Descripción del paso de la prueba.
         */
        public String getDescription() {
            return description;
        }

        /**
         * Establece la descripción del paso de la prueba.
         *
         * @param description Descripción del paso de la prueba.
         */
        public void setDescription(String description) {
            this.description = description;
        }

        /**
         * Obtiene la captura de pantalla del paso de la prueba.
         *
         * @return Captura de pantalla del paso de la prueba.
         */
        public String getScreenshot() {
            return screenshot;
        }

        /**
         * Establece la captura de pantalla del paso de la prueba.
         *
         * @param screenshot Captura de pantalla del paso de la prueba.
         */
        public void setScreenshot(String screenshot) {
            this.screenshot = screenshot;
        }

        /**
         * Obtiene el error del paso de la prueba.
         *
         * @return Error del paso de la prueba.
         */
        public String getError() {
            return error;
        }

        /**
         * Establece el error del paso de la prueba.
         *
         * @param error Error del paso de la prueba.
         */
        public void setError(String error) {
            this.error = error;
        }

        /**
         * Obtiene el tipo de error del paso de la prueba.
         *
         * @return Tipo de error del paso de la prueba.
         */
        public String getErrorType() {
            return errorType;
        }

        /**
         * Establece el tipo de error del paso de la prueba.
         *
         * @param errorType Tipo de error del paso de la prueba.
         */
        public void setErrorType(String errorType) {
            this.errorType = errorType;
        }
    }
}
