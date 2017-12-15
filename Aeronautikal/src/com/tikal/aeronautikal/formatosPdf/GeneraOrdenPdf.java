package com.tikal.aeronautikal.formatosPdf;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.*; 
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
     */
    public static void GeneraOrdenPdf(File pdfNewFile) {
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
    	 
    	    // AQUÍ COMPLETAREMOS NUESTRO CÓDIGO PARA GENERAR EL PDF
    	    document.addTitle("Table export to PDF (Exportamos la tabla a PDF)");
    	    document.addSubject("Using iText (usando iText)");
    	    document.addKeywords("Java, PDF, iText");
    	    document.addAuthor("Código Xules");
    	    document.addCreator("Código Xules");
    	    document.close();
    	    System.out.println("Your PDF file has been generated!(¡Se ha generado tu hoja PDF!");
    	} catch (DocumentException documentException) {
    	    System.out.println("The file not exists (Se ha producido un error al generar un documento): " + documentException);
    	}
    }
    public void main(String args[]) {
    	@SuppressWarnings("unused")
		GeneraOrdenPdf generaOrdenPdf = new GeneraOrdenPdf();
    	GeneraOrdenPdf.GeneraOrdenPdf(new File("../Escritorio/Orden de trabajo.pdf"));
    }
      
    
}

