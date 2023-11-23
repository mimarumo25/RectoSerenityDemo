package utils;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import utils.datosresult.ResultTest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * La clase 'PlantillaBasePDF' proporciona la funcionalidad para crear una plantilla de informe en formato PDF
 * basada en los resultados de las pruebas automatizadas.
 */
public class PlantillaBasePDF {
    private final int tamanoFuente = 13;

    /**
     * Crea una plantilla de informe en formato PDF basada en los resultados de las pruebas automatizadas.
     *
     * @param exitpath   Ruta de salida para el archivo PDF resultante.
     * @param resultTest Objeto ResultTest que contiene los datos de los resultados de las pruebas.
     * @throws IOException       Excepción lanzada si hay un error al leer o escribir en archivos.
     * @throws DocumentException Excepción lanzada si hay un error en la estructura o contenido del documento PDF.
     */
    public void crearPlantillaPDF(String exitpath, ResultTest resultTest) throws IOException, DocumentException {
        // Obtener el nombre del proyecto
        String projectName = new File(".").getAbsoluteFile().getParentFile().getName();
        Font font = new Font();

        // Crear los párrafos con la información del proyecto, fecha, resultado, tiempo de ejecución y navegador
        Paragraph proyecto = new Paragraph(new Chunk("Proyecto: " + projectName, new Font(Font.FontFamily.TIMES_ROMAN, tamanoFuente, Font.BOLD)));
        Paragraph fecha = new Paragraph(new Chunk("Fecha de Ejecución: " + formatoFecha(resultTest.getStartTime()), new Font(Font.FontFamily.TIMES_ROMAN, tamanoFuente, Font.BOLD)));
        Paragraph result = new Paragraph(new Chunk("Resultado de Ejecución: " + resultTest.getResult(), new Font(Font.FontFamily.TIMES_ROMAN, tamanoFuente, Font.BOLD)));
        Paragraph duration = new Paragraph(new Chunk("Tiempo de Ejecución: " + convertirAMilisegundos(resultTest.getDuration()), new Font(Font.FontFamily.TIMES_ROMAN, tamanoFuente, Font.BOLD)));
        Paragraph driver = new Paragraph(new Chunk("Navegador de Ejecución: " + resultTest.getDriver(), new Font(Font.FontFamily.TIMES_ROMAN, tamanoFuente, Font.BOLD)));

        // Establecer el color del texto del resultado según el éxito o fallo de la ejecución
        font.setColor(resultTest.getResult().equals("SUCCESS") ? BaseColor.GREEN : BaseColor.RED);
        result.setFont(font);
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime currentDateTime = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        Document document = new Document();
        //Ruta de donde se guarda el archivo pdf con la evidencias
        FileOutputStream archivo = new FileOutputStream(exitpath + "/Reporte-" + currentDateTime.format(formatter).replaceAll("\\s+", "-").replace(":", "-") + ".pdf");
        PdfWriter.getInstance(document, archivo);
        document.open();

        // Crear la tabla con la información del proyecto y ejecución
        PdfPTable table = new PdfPTable(2); // 2 columnas
        table.setWidthPercentage(100);
        PdfPCell cell1 = new PdfPCell();
        cell1.setBorderWidth(0.1F);
        table.addCell(cell1);

        PdfPCell cell2 = new PdfPCell();
        cell2.addElement(proyecto);
        cell2.addElement(fecha);
        cell2.addElement(duration);
        cell2.addElement(result);
        cell2.addElement(driver);
        cell2.setBorderWidth(0.1F);

        table.addCell(cell2);

        document.add(table);
        document.add(new Paragraph(" "));
        document.add(new Paragraph(new Chunk("Descripción del Escenario : " + resultTest.getName(), new Font(Font.FontFamily.TIMES_ROMAN, tamanoFuente, Font.BOLD))));

        // Obtener y formatear los datos del escenario
        Map<String, String> registros = formatoScenario(resultTest.getScenario());
        try {
            PdfPTable tablaScenario = new PdfPTable(registros.size());
            Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            headerFont.setColor(BaseColor.WHITE);
            headerFont.setSize(10);

            // Agregar las cabeceras de las columnas de la tabla de datos del escenario
            for (String key : registros.keySet()) {
                PdfPCell headerCell = new PdfPCell(new Phrase(key));
                headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                headerCell.setBackgroundColor(BaseColor.DARK_GRAY);
                tablaScenario.addCell(headerCell);
            }

            // Agregar los datos del escenario a la tabla
            for (String key : registros.keySet()) {
                tablaScenario.addCell(new PdfPCell(new Phrase(registros.get(key))));
            }
            document.add(new Paragraph(" "));
            document.add(tablaScenario);
        } catch (Exception e) {
            e.getStackTrace();
        }

        Paragraph paragraph = new Paragraph();
        document.add(Chunk.NEWLINE);

        // Agregar los pasos de prueba, descripción, capturas de pantalla y posibles errores
        ArrayList<ResultTest.Steps> steps = resultTest.getSteps();
        String description = null;
        for (int i = 0; i < steps.size(); i++) {
            ResultTest.Steps step = steps.get(i);
            if (!step.getStep().equals(description)) {
                document.add(new Paragraph(new Chunk("Descripción del Paso: ", new Font(Font.FontFamily.TIMES_ROMAN, tamanoFuente, Font.BOLD))));
                document.add(new Paragraph(step.getStep()));
                description = step.getStep();
            }
            document.add(new Paragraph(new Chunk("Paso: ", new Font(Font.FontFamily.TIMES_ROMAN, tamanoFuente, Font.BOLD))));
            document.add(new Paragraph(step.getDescription()));

            // Agregar la captura de pantalla del paso
            Image imagen = Image.getInstance(exitpath + "/" + step.getScreenshot());
            imagen.scaleToFit(500, 500);
            imagen.setAlignment(Chunk.ALIGN_LEFT);
            document.add(imagen);
            document.add(new Paragraph(" "));

            // Agregar detalles de error si existe y el resultado de la ejecucion es ERROR
            if (step.getError() != null && resultTest.getResult().equals("ERROR")) {
                paragraph.add(new Chunk("Tipo de error: ", new Font(Font.FontFamily.TIMES_ROMAN, tamanoFuente, Font.BOLD)));
                paragraph.add(new Chunk(step.getErrorType() + "\n", new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.RED)));
                paragraph.add(new Chunk("Error: ", new Font(Font.FontFamily.TIMES_ROMAN, tamanoFuente, Font.BOLD)));
                paragraph.add(new Chunk(step.getError() + "\n", new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.RED)));
            }
            document.add(paragraph);
            document.add(new Paragraph(" "));
        }

        document.add(new Paragraph(new Chunk("Fecha Creación : " + now.format(formatter), new Font(Font.FontFamily.TIMES_ROMAN, tamanoFuente, Font.BOLD))));

        String describe = "Resultado: ";

        // Crear el objeto Chunk con la descripción y el color correspondiente
        Chunk descriptionChunk = new Chunk(describe, new Font(Font.FontFamily.TIMES_ROMAN, tamanoFuente, Font.BOLD));
        Chunk resultChunk = new Chunk(resultTest.getResult(), result.equals("SUCCESS") ? new Font(Font.FontFamily.TIMES_ROMAN, tamanoFuente, Font.BOLD, BaseColor.GREEN) : new Font(Font.FontFamily.TIMES_ROMAN, tamanoFuente, Font.BOLD, BaseColor.RED));

        // Crear el objeto Paragraph con los Chunks y agregarlo al documento
        Paragraph paragrap = new Paragraph();
        paragraph.add(descriptionChunk);
        paragraph.add(resultChunk);
        document.add(paragrap);

        document.close();
        System.out.println("Archivo creado con éxito");
    }

    /**
     * Convierte una fecha en formato String a un formato específico (dd/MM/yyyy HH:mm:ss).
     *
     * @param fechaStr Fecha en formato String.
     * @return Fecha formateada en formato específico.
     */
    public String formatoFecha(String fechaStr) {
        String fecha = "";
        try {
            ZonedDateTime fechaOriginal = ZonedDateTime.parse(fechaStr);

            LocalDateTime fechaLocal = fechaOriginal.toLocalDateTime();

            DateTimeFormatter formatoDeseado = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            fecha = fechaLocal.format(formatoDeseado);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return fecha;
    }

    /**
     * Formatea la cadena del escenario y la convierte en un mapa de datos clave-valor.
     *
     * @param cadena Cadena del escenario en formato específico ({clave1=valor1, clave2=valor2}).
     * @return Mapa de datos clave-valor con los elementos del escenario.
     */
    public Map<String, String> formatoScenario(String cadena) {

        Map<String, String> datos = new HashMap<>();

        Pattern pattern = Pattern.compile("\\{([^}]*)\\}");

        Matcher matcher = pattern.matcher(cadena);
        if (matcher.find()) {
            String[] elementos = matcher.group(1).split(", ");
            for (String elemento : elementos) {
                String[] partes = elemento.split("=");
                datos.put(partes[0], partes[1]);
            }
        }
        return datos;
    }

    /**
     * Convierte el tiempo en milisegundos a un formato más legible (segundos o minutos).
     *
     * @param tiempo Tiempo en milisegundos.
     * @return Tiempo formateado en segundos o minutos.
     */
    public static String convertirAMilisegundos(int tiempo) {
        System.out.println(tiempo);
        double segundos = (double) tiempo / 1000;

        if (segundos > 60) {
            double minutos = segundos / 60;

            return minutos + " minutos";
        } else {
            return segundos + " segundos";
        }
    }
}

