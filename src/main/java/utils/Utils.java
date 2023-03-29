package utils;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;


public class Utils {

    static String rutaBase = System.getProperty("user.dir");
    static String folderPath = rutaBase + "/target/site/serenity";

    /*public static void main(String[] args) {
        System.out.println("Generating Serenity Reports PDF");
        try {
            identificaJson();
        } catch (IOException e) {
            throw new RuntimeException("Error al Identificar el Archivo: " + e);
        }
    }*/

    public  void identificaJson() throws IOException {
        File folder = new File("C:/Users/mrubidem/Desktop/RetoSerenityDemo/target/site");
        File[] files = folder.listFiles();
        for (File file : files) {
            if (file.isFile() && file.getName().toLowerCase().endsWith(".json")) {
                jsonFileReader(file.getName());
                System.out.println(file.getName());
            }

        }
    }

    public static void jsonFileReader(String file) throws IOException {
        String rutaArchivo = folderPath + "/" + file;
        byte[] bytes = Files.readAllBytes(Paths.get(rutaArchivo));
        String jsonString = new String(bytes);

        JSONObject jsonObject = new JSONObject(jsonString);
        JSONArray Pnatalla = jsonObject.getJSONArray("testSteps");
        for (int i = 0; i < Pnatalla.length(); i++) {
            JSONObject phone = Pnatalla.getJSONObject(i);
            String result = phone.getString("result");
            System.out.println(" Resultado  :" + result);
            String escenario = phone.getString("description");
            System.out.println("Descripcion : " + escenario);
            JSONArray children1 = phone.getJSONArray("children");
            for (int j = 0; j < children1.length(); j++) {
                JSONObject hijoP = children1.getJSONObject(j);
                JSONArray screenshots = hijoP.getJSONArray("children");
                for (int k = 0; k < screenshots.length(); k++) {
                    JSONObject screenshotInterno1 = screenshots.getJSONObject(k);
                    JSONArray pantallazo = screenshotInterno1.getJSONArray("children");
                    for (int l = 0; l < pantallazo.length(); l++) {
                        JSONObject screenshotInterno2 = pantallazo.getJSONObject(l);
                        String description = screenshotInterno2.getString("description");
                        System.out.println("Descripción del Paso  : " + description);
                        JSONArray screenshotInterno3 = screenshotInterno2.getJSONArray("screenshots");
                        for (int m = 0; m < screenshotInterno3.length(); m++) {
                            JSONObject screenshotInterno4 = screenshotInterno3.getJSONObject(m);
                            String pantallazo3 = screenshotInterno4.getString("screenshot");
                            System.out.println("Pantallazo del Paso : " + pantallazo3);
                        }
                    }

                }

            }

        }
        try {
            //Object jsonObject = objectMapper.readValue(jsonFile, Object.class);
            generaPdf(jsonString);


        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private static void generaPdf(String jsonString) throws Exception {



           /* PlantillaBasePDF plantillaBasePDF = new PlantillaBasePDF();

            ObjectMapper mapper = new ObjectMapper();

            Map<String, Object> atributosGuiaFedex = mapper.convertValue(jsonFilePath, Map.class);
            plantillaBasePDF.crearPlantillaPDF(atributosGuiaFedex);*/

    }
}
