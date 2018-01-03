package com.tikal.aeronautikal.formatos;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.*;
import java.net.MalformedURLException;
import java.util.Date; 
public class GeneraOrdenPdf {
	private static final Font chapterFont = FontFactory.getFont(FontFactory.HELVETICA, 26, Font.BOLDITALIC);
    private static final Font paragraphFont = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL);
         
    private static final Font categoryFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
    private static final Font subcategoryFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
    private static final Font blueFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.RED);    
    private static final Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
     
    private static final String iTextExampleImage = "/home/xules/codigoxules/iText-Example-image.png";
    /**
     * We create a PDF document with iText using different elements to learn 
     * to use this library.
     * Creamos un documento PDF con iText usando diferentes elementos para aprender 
     * a usar esta librería.
     * @param pdfNewFile  <code>String</code> 
     *      pdf File we are going to write. 
     *      Fichero pdf en el que vamos a escribir. 
     * @throws IOException 
     * @throws MalformedURLException 
     */
    public void GeneraOrdenPdf(File pdfNewFile) throws MalformedURLException, IOException {
        // Aquí introduciremos el código para crear el PDF.
    	try {
    	    Document document = new Document();
    	    try {
    	        PdfWriter.getInstance(document, new FileOutputStream(pdfNewFile));
    	    } catch (FileNotFoundException fileNotFoundException) {
    	        System.out.println("No such file was found to generate the PDF "
    	                + "(No se encontró el fichero para generar el pdf)" + fileNotFoundException);
    	    }
    	    document.open();
            
            // Este codigo genera una tabla de 3 columnas
            PdfPTable table = new PdfPTable(4);                
             
            // addCell() agrega una celda a la tabla, el cambio de fila
            // ocurre automaticamente al llenar la fila
           
             
            // Si desea crear una celda de mas de una columna
            // Cree un objecto Cell y cambie su propiedad span
             
            PdfPCell celdaFinal = new PdfPCell(new Paragraph("Final de la tabla"));
             
            // Indicamos cuantas columnas ocupa la celda
            celdaFinal.setColspan(4);
            table.addCell(celdaFinal);
         ////////////////////
          //  PdfPTable table = new PdfPTable(2);
         //   table.setWidthPercentage(100f);
         //   PdfPCell cell;
         //   Image image = Image.getInstance("LogoCross.jpg");
        //    cell = new PdfPCell(image);
        //    table.addCell(cell);
           // cell = new PdfPCell(new Phrase("Mudvayne es una banda de metal alternativo formada en Peoria, Illinois en 1996. Consta de cuatro miembros, el cantante principal Chad Gray, Greg Tribbett en guitarras, Ryan Martinie en bajos y Matthew McDonough en batería."));
           // table.addCell(cell);
            ////////////////////
            document.add(table);
             
            document.close();
    	    System.out.println("Your PDF file has been generated!(¡Se ha generado tu hoja PDF!");
    	} catch (DocumentException documentException) {
    	    System.out.println("The file not exists (Se ha producido un error al generar un documento): " + documentException);
    	}
    }
//    public void main(String args[]) {
//    	@SuppressWarnings("unused")
//		GeneraOrdenPdf generaOrdenPdf = new GeneraOrdenPdf();
//    	GeneraOrdenPdf.GeneraOrdenPdf(new File("../Escritorio/Orden de trabajo.pdf"));
//    }
      
    
}

