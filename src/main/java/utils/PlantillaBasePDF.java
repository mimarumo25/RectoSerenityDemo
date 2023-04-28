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


public class PlantillaBasePDF {
    private final int tamañoFuente = 13;

    public void crearPlantillaPDF(String exitpath, ResultTest resultTest) throws IOException, DocumentException {
        String projectName = new File(".").getAbsoluteFile().getParentFile().getName();
        Font font = new Font();

        Paragraph proyecto = new Paragraph(new Chunk("Proyecto: " + projectName, new Font(Font.FontFamily.TIMES_ROMAN, tamañoFuente, Font.BOLD)));
        Paragraph fecha = new Paragraph(new Chunk("Fecha de Ejecución: " + formatoFecha(resultTest.getStartTime()), new Font(Font.FontFamily.TIMES_ROMAN, tamañoFuente, Font.BOLD)));
        Paragraph result = new Paragraph(new Chunk("Resultado de Ejecución: " + resultTest.getResult(), new Font(Font.FontFamily.TIMES_ROMAN, tamañoFuente, Font.BOLD)));
        Paragraph duration = new Paragraph(new Chunk("Tiempo de Ejecución: " + convertirAMilisegundos(resultTest.getDuration()), new Font(Font.FontFamily.TIMES_ROMAN, tamañoFuente, Font.BOLD)));
        Paragraph driver = new Paragraph(new Chunk("Navegador de Ejecución: " + resultTest.getDriver(), new Font(Font.FontFamily.TIMES_ROMAN, tamañoFuente, Font.BOLD)));

        font.setColor(resultTest.getResult().equals("SUCCESS") ? BaseColor.GREEN : BaseColor.RED);
        result.setFont(font);
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime currentDateTime = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        Document document = new Document();
        FileOutputStream archivo = new FileOutputStream(exitpath + "/Reporte-" + currentDateTime.format(formatter).replaceAll("\\s+", "-").replace(":", "-") + ".pdf");
        PdfWriter.getInstance(document, archivo);
        document.open();

        Image logo = Image.getInstance("src/test/resources/Logo.jpg");

        logo.scaleToFit(200, 100);
        logo.setAlignment(Chunk.ALIGN_CENTER);


        PdfPTable table = new PdfPTable(2); // 2 columnas
        table.setWidthPercentage(100);
        PdfPCell cell1 = new PdfPCell();
        cell1.addElement(logo);
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
        document.add(new Paragraph(new Chunk("Descripción del Escenario : " + resultTest.getName(), new Font(Font.FontFamily.TIMES_ROMAN, tamañoFuente, Font.BOLD))));


        Map<String, String> registros = formatoScenario(resultTest.getScenario());
        try {
            PdfPTable tablaScenario = new PdfPTable(registros.size());
            Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            headerFont.setColor(BaseColor.WHITE);
            headerFont.setSize(10);

            for (String key : registros.keySet()) {
                PdfPCell headerCell = new PdfPCell(new Phrase(key));
                headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                headerCell.setBackgroundColor(BaseColor.DARK_GRAY);
                tablaScenario.addCell(headerCell);
            }

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
        ArrayList<ResultTest.Steps> steps = resultTest.getSteps();
        String description = null;
        for (int i = 0; i < steps.size(); i++) {
            ResultTest.Steps step = steps.get(i);
            if (!step.getStep().equals(description)) {
                document.add(new Paragraph(new Chunk("Descripción del Paso: ", new Font(Font.FontFamily.TIMES_ROMAN, tamañoFuente, Font.BOLD))));
                document.add(new Paragraph(step.getStep()));
                description = step.getStep();
            }
            document.add(new Paragraph(new Chunk("Paso: ", new Font(Font.FontFamily.TIMES_ROMAN, tamañoFuente, Font.BOLD))));
            document.add(new Paragraph(step.getDescription()));
            Image imagen = Image.getInstance(exitpath + "/" + step.getScreenshot());

            imagen.scaleToFit(500, 500);
            imagen.setAlignment(Chunk.ALIGN_LEFT);
            document.add(imagen);
            document.add(new Paragraph(" "));
            if (step.getError() != null && resultTest.getResult().equals("ERROR")) {
                paragraph.add(new Chunk("Tipo de error: ", new Font(Font.FontFamily.TIMES_ROMAN, tamañoFuente, Font.BOLD)));
                paragraph.add(new Chunk(step.getErrorType() + "\n", new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.RED)));
                paragraph.add(new Chunk("Error: ", new Font(Font.FontFamily.TIMES_ROMAN, tamañoFuente, Font.BOLD)));
                paragraph.add(new Chunk(step.getError() + "\n", new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.RED)));
            }
            document.add(paragraph);
            document.add(new Paragraph(" "));
        }


        document.add(new Paragraph(new Chunk("Fecha Creación : " + now.format(formatter), new Font(Font.FontFamily.TIMES_ROMAN, tamañoFuente, Font.BOLD))));

        String describe = "Resultado: ";

// Crear el objeto Chunk con la descripción y el color correspondiente
        Chunk descriptionChunk = new Chunk(describe, new Font(Font.FontFamily.TIMES_ROMAN, tamañoFuente, Font.BOLD));
        Chunk resultChunk = new Chunk(resultTest.getResult(), result.equals("SUCCESS") ? new Font(Font.FontFamily.TIMES_ROMAN, tamañoFuente, Font.BOLD, BaseColor.GREEN) : new Font(Font.FontFamily.TIMES_ROMAN, tamañoFuente, Font.BOLD, BaseColor.RED));

// Crear el objeto Paragraph con los Chunks y agregarlo al documento
        Paragraph paragrap = new Paragraph();
        paragraph.add(descriptionChunk);
        paragraph.add(resultChunk);
        document.add(paragrap);
        document.close();
        System.out.println("Archivo creado con exito");
    }

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
