package utils;

import com.itextpdf.text.DocumentException;
import org.json.JSONArray;
import org.json.JSONObject;
import utils.datosresult.ResultTest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Clase que lee archivos JSON y genera un documento PDF basado en los datos extraídos.
 */
public class LectorJson {
    // Variables estáticas utilizadas para almacenar los datos extraídos del archivo JSON
    static String name = "";
    static int duration = 0;
    static String startTime = "";
    static String result = "";
    static String scenario = "";
    static String pantallazo = "";
    static String description = "";
    static String paso = "";
    static String driver;
    static String error;
    static String errorType;
    static ArrayList<ResultTest.Steps> pasos = new ArrayList<>();
    static String rutaBase = System.getProperty("user.dir");
    static String folderPath = rutaBase + "/target/site/serenity";

    /**
     * Punto de entrada del programa.
     *
     * @param args Argumentos de línea de comandos (no se utilizan en este caso).
     */
    public static void main(String[] args) {
        try {
            identificaJson();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Identifica y procesa los archivos JSON en el directorio especificado.
     *
     * @throws IOException       Si ocurre un error de lectura de archivos.
     * @throws DocumentException Si ocurre un error al generar el documento PDF.
     */
    public static void identificaJson() throws IOException, DocumentException {
        File folder = new File(folderPath);
        File[] files = folder.listFiles();
        for (File file : files) {
            if (file.isFile() && file.getName().toLowerCase().endsWith(".json")) {
                System.out.println(file.getName());
                jsonFileReader(file.getName());
            }
        }
    }

    /**
     * Lee y procesa un archivo JSON especificado.
     *
     * @param file Nombre del archivo JSON.
     * @throws IOException       Si ocurre un error de lectura de archivos.
     * @throws DocumentException Si ocurre un error al generar el documento PDF.
     */
    public static void jsonFileReader(String file) throws IOException, DocumentException {
        String rutaArchivo = folderPath + "/" + file;
        byte[] bytes = Files.readAllBytes(Paths.get(rutaArchivo));
        String jsonString = new String(bytes);
        JSONObject jsonObject = new JSONObject(jsonString);
        name = jsonObject.getString("name");
        result = jsonObject.getString("result");
        driver = jsonObject.getString("driver");

        JSONArray results = jsonObject.getJSONArray("testSteps");
        for (int i = 0; i < results.length(); i++) {
            try {
                JSONObject testSteps = results.getJSONObject(i);
                duration = testSteps.getInt("duration");
                startTime = testSteps.getString("startTime");
                scenario = testSteps.getString("description");
                JSONArray children = testSteps.getJSONArray("children");
                for (int j = 0; j < children.length(); j++) {
                    JSONObject children1 = children.getJSONObject(j);
                    JSONArray childrenArray = children1.getJSONArray("children");
                    paso = children1.getString("description");
                    for (int k = 0; k < childrenArray.length(); k++) {
                        JSONObject children2 = childrenArray.getJSONObject(k);
                        JSONArray childrenArray1 = children2.optJSONArray("children");
                        if (childrenArray1 != null) {
                            for (int l = 0; l < childrenArray1.length(); l++) {
                                JSONObject screenshot = childrenArray1.getJSONObject(l);
                                description = screenshot.getString("description");
                                JSONArray screenshotInterno = screenshot.getJSONArray("screenshots");
                                if (screenshot.has("exception")) {
                                    JSONObject exception = screenshot.getJSONObject("exception");
                                    errorType = exception.getString("errorType");
                                    error = exception.getString("message");
                                }
                                for (int m = 0; m < screenshotInterno.length(); m++) {
                                    JSONObject screenshotInterno1 = screenshotInterno.getJSONObject(m);
                                    String pantallazo = screenshotInterno1.getString("screenshot");
                                    pasos.add(new ResultTest.Steps(paso, description, pantallazo, error, errorType));
                                }
                            }
                        } else {
                            description = children2.getString("description");
                            if (children2.has("exception")) {
                                JSONObject exception = children2.getJSONObject("exception");
                                errorType = exception.getString("errorType");
                                error = exception.getString("message");
                            }
                            JSONArray screenshotInterno = children2.getJSONArray("screenshots");
                            for (int m = 0; m < screenshotInterno.length(); m++) {
                                JSONObject screenshotInterno1 = screenshotInterno.getJSONObject(m);
                                pantallazo = screenshotInterno1.getString("screenshot");
                                pasos.add(new ResultTest.Steps(paso, description, pantallazo, error, errorType));

                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.getStackTrace();
            }
        }

        // Crear un objeto ResultTest y generar el documento PDF resultante y le pasa como parámetro la información extraída del archivo JSON
        ResultTest resultado = new ResultTest(name, duration, startTime, result, scenario, driver, pasos);
        PlantillaBasePDF plantillaBasePDF = new PlantillaBasePDF();
        plantillaBasePDF.crearPlantillaPDF(folderPath, resultado);
    }
}
