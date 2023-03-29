package utils;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

public class PlantillaBasePDF {
    public void crearPlantillaPDF( Map<String, Object> resultJSON) throws IOException, DocumentException {

        Document document = new Document();
        Paragraph titulo = new Paragraph("Escenario :"+resultJSON.get("name"));
        FileOutputStream archivo = new FileOutputStream("C:\\Users\\mrubidem\\Desktop\\RetoSerenityDemo\\target\\site\\serenity\\resultPDF/Reporte.pdf");
        PdfWriter.getInstance(document, archivo);
        document.open();


        Image imagen = Image.getInstance("C:\\Users\\mrubidem\\Desktop\\RetoSerenityDemo\\target\\site\\serenity\\resultPDF/logo.jpg");

        imagen.scaleToFit(200, 100);
        imagen.setAlignment(Chunk.ALIGN_LEFT);

        titulo.setAlignment(1);
        Paragraph fecha = new Paragraph();
        PdfPTable table = new PdfPTable(2); // 2 columnas
        table.setWidthPercentage(100);
        PdfPCell cell1 = new PdfPCell();
        cell1.addElement(imagen);
        cell1.setBorderWidth(0);
        table.addCell(cell1);

        PdfPCell cell2 = new PdfPCell();
        cell2.addElement(fecha);
        cell2.addElement(fecha);
        cell2.addElement(fecha);
        cell2.addElement(fecha);

        cell2.setBorderWidth(0);


        table.addCell(cell2);

        document.add(table);
        document.add(titulo);

        document.add(new Paragraph("Nombre : " + "Miguel Mariano Rubide"));
        document.add(new Paragraph("Cargo : " + "Automatizador"));

        document.add(Chunk.NEWLINE);
        Paragraph texto = new Paragraph("-\tUso: Facilidad en la utilización e incorporación por parte de analistas con conocimientos básicos en temas de automatización y/o determinar el nivel de conocimiento requerido para operar la herramienta.\n" +
                "Lo que se buscar es determinar que tanto se requiere de esfuerzos a nivel de inducción, capacitación y acompañamiento (Costo de incorporar un analista al uso de la herramienta).\n");

        texto.setAlignment(Element.ALIGN_JUSTIFIED);
        document.add(texto);

        document.add(Chunk.NEWLINE);

        document.add(new Paragraph("Fecha Inicio : " + "16/02/2023"));

        document.close();
        System.out.println("Archivo creado con exito");
    }
}
