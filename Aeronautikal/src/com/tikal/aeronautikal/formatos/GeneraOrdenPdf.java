package com.tikal.aeronautikal.formatos;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.tikal.aeronautikal.controller.vo.ComDisVo;
import com.tikal.aeronautikal.controller.vo.DetalleDiscrepanciaVo;
import com.tikal.aeronautikal.controller.vo.OrdenXlsVo;

import java.io.*;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.List; 
public class GeneraOrdenPdf {
	private static final Font chapterFont = FontFactory.getFont(FontFactory.HELVETICA, 26, Font.BOLDITALIC);
    private static final Font paragraphFont = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL);
         
    private static final Font categoryFont = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD);
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
  //  public void GeneraOrdenPdf(File pdfNewFile) throws MalformedURLException, IOException {
      public GeneraOrdenPdf(OrdenXlsVo ox,   List<DetalleDiscrepanciaVo> dets) throws MalformedURLException, IOException {
        // Aquí introduciremos el código para crear el PDF.
    	  
    	  
    	try {
    	    Document document = new Document(PageSize.LETTER, 20,20,35,35);
    	    try {
    	        PdfWriter.getInstance(document, new FileOutputStream(ox.getNombreArchivo()));
    	    } catch (FileNotFoundException fileNotFoundException) {
    	        System.out.println("No such file was found to generate the PDF "
    	                + "(No se encontró el fichero para generar el pdf)" + fileNotFoundException);
    	    }
    	    document.open();
    	    Font fuente = new Font();
    	    fuente.setStyle(1);
    	    fuente.setStyle(Font.BOLD);
    	    Font f1 = new Font();
    	    f1.setStyle(2);
    	    f1.setSize(10);
    	    Font f2 = new Font();
    	    f2.setStyle(3);
    	    f2.setSize(8);
            // Este codigo genera una tabla de 3 columnas
            PdfPTable table = new PdfPTable(4);                
           
            
            Image imagen = Image.getInstance("img\\LogoCross.png");
            imagen.scaleAbsolute(190, 40);
            imagen.setSpacingBefore(1);
            imagen.setSpacingAfter(90);
            imagen.setAbsolutePosition(50, 30);
           
            
           
            //Alineamos la imagen al centro del documento.         
           // Paragraph p = new Paragraph(" \n",f1);
            PdfPCell c0 = new PdfPCell(imagen);
          //  c0.setHorizontalAlignment(3);
            //c0.setVerticalAlignment(1);
           // c0.setHorizontalAlignment(Element.ALIGN_LEFT);
            //c0.setVerticalAlignment(Element.ALIGN_CENTER);
            
            c0.setColspan(4);
            table.addCell(c0);
            
            Paragraph a = new Paragraph("CROSS AIR SERVICES, S.A. DE C.V.",f1);
            PdfPCell celdaFinal = new PdfPCell(a);
            celdaFinal.setHorizontalAlignment(Element.ALIGN_CENTER);             
            celdaFinal.setColspan(4);
            table.addCell(celdaFinal);
            Paragraph b =new Paragraph("WORK ORDER / ORDEN DE TRABAJO: \n",fuente);
            PdfPCell c2 =new PdfPCell(b);
            c2.setHorizontalAlignment(Element.ALIGN_CENTER);             
            c2.setColspan(4);
            table.addCell(c2);
           
            Paragraph d1 =new Paragraph("DATE / FECHA:\n"+ox.getFechaOrden(),f2);
            PdfPCell c3 =new PdfPCell(d1);
            c3.setHorizontalAlignment(Element.ALIGN_CENTER);             
            c3.setColspan(1);
            table.addCell(c3);
            
            Paragraph d2 =new Paragraph("NAMECOMPANY OR CUSTOMER AIRCRAFT / NOMBRE DE LA CIA. O PROPIETARIO DEL AERONAVE: \n"+ox.getNombreEmpresa(), f2);
         //   Paragraph dd = new Paragraph (ox.getNombreEmpresa(), f2);
            PdfPCell c4 =new PdfPCell(d2);           
            c4.setHorizontalAlignment(Element.ALIGN_CENTER);             
            c4.setColspan(2);
            table.addCell(c4);
            
            Paragraph d3 =new Paragraph("O.W. / O.T # :\n"+ox.getFolioOrden(),f2);
            PdfPCell c5 =new PdfPCell(d3);
            c5.setHorizontalAlignment(Element.ALIGN_CENTER);             
            c5.setColspan(1);
            table.addCell(c5);
            
            Paragraph d4 =new Paragraph("MARK / MARCA:\n"+ox.getMarcaAeronave(),f2);
            PdfPCell c6 =new PdfPCell(d4);
            c6.setHorizontalAlignment(Element.ALIGN_CENTER);             
            c6.setColspan(1);
            table.addCell(c6);
            
            Paragraph d5 =new Paragraph("MODEL / MODELO:\n"+ox.getModeloAeronave(),f2);
            PdfPCell c7 =new PdfPCell(d5);
            c7.setHorizontalAlignment(Element.ALIGN_CENTER);             
            c7.setColspan(1);
            table.addCell(c7);
            
            Paragraph d6 =new Paragraph("N/S:\n"+ox.getNumeroSerie(),f2);
            PdfPCell c8 =new PdfPCell(d6);
            c8.setHorizontalAlignment(Element.ALIGN_CENTER);             
            c8.setColspan(1);
            table.addCell(c8);
            
            Paragraph d7 =new Paragraph("REG. / MATRICULA:\n"+ox.getMatricula(),f2);
            PdfPCell c9 =new PdfPCell(d7);
            c9.setHorizontalAlignment(Element.ALIGN_CENTER);             
            c9.setColspan(1);
            table.addCell(c9);
            
            Paragraph d8 =new Paragraph("TT FUSELAGE/ TTPLANEADOR:\n"+ox.getPlaneador(),f2);
            PdfPCell c10 =new PdfPCell(d8);
            c10.setHorizontalAlignment(Element.ALIGN_CENTER);             
            c10.setColspan(1);
            table.addCell(c10);
            
            Paragraph d9 =new Paragraph("TT ENGINE #1/ TT MOTOR #1:\n"+ox.getMotor1(),f2);
            PdfPCell c11 =new PdfPCell(d9);
            c11.setHorizontalAlignment(Element.ALIGN_CENTER);             
            c11.setColspan(1);
            table.addCell(c11);
            
            Paragraph d10 =new Paragraph("TT ENGINE #2/ TT MOTOR #2:\n"+ox.getMotor2(),f2);
            PdfPCell c12 =new PdfPCell(d10);
            c12.setHorizontalAlignment(Element.ALIGN_CENTER);             
            c12.setColspan(1);
            table.addCell(c12);
            
            Paragraph d11 =new Paragraph("ENGINES MARK AND MODEL/ MARCA Y MODELO DE MOTORES:\n"+ox.getMarcas(),f2);
            PdfPCell c13 =new PdfPCell(d11);
            c13.setHorizontalAlignment(Element.ALIGN_CENTER);             
            c13.setColspan(1);
            table.addCell(c13);
            
            Paragraph d12 =new Paragraph("WORKS REGISTER / REGISTRO DE TRABAJOS: 'DISCREPANCIAS'",f1);
            PdfPCell c14 =new PdfPCell(d12);
            c14.setHorizontalAlignment(Element.ALIGN_CENTER);             
            c14.setColspan(4);
            table.addCell(c14);
 
            
            for (int i=1; i<=32; i++){            	 
            	 Paragraph d14 =new Paragraph(i+" "+ox.getAccionesDiscrepancia().get((i-1)),f2);
            	// System.out.println("variable i="+i);
                 PdfPCell c16 =new PdfPCell(d14);
                 c16.setHorizontalAlignment(Element.ALIGN_LEFT);             
                 c16.setColspan(4);
                 table.addCell(c16);            	
            }
            
            Paragraph d15 =new Paragraph("NAME COMPANY OR CUSTOMER AIRCRAFT / NOMBRE DEL REPRESENTANTE PROPIETARIO DE LA AERONAVE:\n",f2);
            PdfPCell c16 =new PdfPCell(d15);
            c16.setHorizontalAlignment(Element.ALIGN_CENTER);             
            c16.setColspan(2);
            table.addCell(c16);
            
            Paragraph d16 =new Paragraph("AUTORIZATION SIGN FOR COMPANY OR CUSTOMER AIRCRAFT FOR WILL MAKE WORK/ FIRMA DE CONFORMIDAD DEL REPRESENTANTE O PROPIETARIO DE LA AERONAVE POR LOS TRABAJOS A REALIZAR:\n\n",f2);
            PdfPCell c17 =new PdfPCell(d16);
            c17.setHorizontalAlignment(Element.ALIGN_CENTER);             
            c17.setColspan(2);
            table.addCell(c17);
   
            Paragraph d17 =new Paragraph("NAME AND SIGN FOR THE PERSON OPEN THE PRESENT WORK ORDER / NOMBRE Y FIRMA DE QUIEN ABRIO LA PRESENTE ORDEN DE TRABAJO:\n",f2);
            PdfPCell c18 =new PdfPCell(d17);
            c18.setHorizontalAlignment(Element.ALIGN_CENTER);             
            c18.setColspan(2);
            table.addCell(c18);
            
            Paragraph d18 =new Paragraph("NAME AND SIGN FOR THE PERSON RECEIBED THE PRESENT WORK ORDER FOR EXECUTION AND COMPLETION / NOMBRE Y FIRMA DE QUIEN RECIBE LA PRESENTE ORDEN DE TRABAJO PARA SU EJECUCION Y CUMPLIMIENTO:\n\n",f2);
            PdfPCell c19 =new PdfPCell(d18);
            c19.setHorizontalAlignment(Element.ALIGN_CENTER);             
            c19.setColspan(2);
            table.addCell(c19);
            
            Paragraph d19 =new Paragraph("OBSERVATION/ OBSERVACIONES:",f1);
            PdfPCell c20 =new PdfPCell(d19);
            c20.setHorizontalAlignment(Element.ALIGN_CENTER);             
            c20.setColspan(4);
            table.addCell(c20);
            
            Paragraph d20 =new Paragraph("\n",f1);
            PdfPCell c21 =new PdfPCell(d20);
            c21.setHorizontalAlignment(Element.ALIGN_CENTER);             
            c21.setColspan(4);
            table.addCell(c21);
            
            Paragraph d21 =new Paragraph("SIGN OF WORKSHOP RESPONSABLE FOR THE MANTENANCE FREE OF JOB EXECUTION / FRIMA DEL RESPONSABLE DE TALLER POR LIBERACION DE MANTENIMIENTO DE LOS TRABAJOS REALIZADOS:",f2);
            PdfPCell c22 =new PdfPCell(d21);
            c22.setHorizontalAlignment(Element.ALIGN_CENTER);             
            c22.setColspan(4);
            table.addCell(c22);
            
            Paragraph d22 =new Paragraph("\n",f1);
            PdfPCell c23 =new PdfPCell(d22);
            c23.setHorizontalAlignment(Element.ALIGN_CENTER);             
            c23.setColspan(4);
            table.addCell(c23);
            ////////////////////
            document.add(table);
            System.out.println("ss");
            ////////////termina de la orden, empieza a generar las discrepancias
            for ( DetalleDiscrepanciaVo det : dets){
            	//PdfPTable table2 = new PdfPTable(6);
            	
            	GeneraDiscrepanciaPdf(det, document,dets.indexOf(det));
            	
                
            }
           
            
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
      
      public void GeneraDiscrepanciaPdf(DetalleDiscrepanciaVo det, Document document, Integer index) throws DocumentException, MalformedURLException, IOException {
          // Aquí introduciremos el código para crear el PDF.      	  
      
    	  	PdfPTable table2 = new PdfPTable(12);      
    	    Font fuente = new Font();
	  	    fuente.setStyle(1);
	  	    fuente.setSize(10);
	  	    fuente.setStyle(Font.BOLD);
	  	    Font f1 = new Font();
	  	    f1.setStyle(2);
	  	    f1.setSize(9);
	  	    Font f2 = new Font();
	  	    f2.setStyle(3);
	  	    f2.setSize(8);
	  	    
	  	  Image imagen = Image.getInstance("img\\LogoCross.png");
          imagen.scaleAbsolute(190, 40);
          imagen.setSpacingBefore(1);
          imagen.setSpacingAfter(90);
          imagen.setAbsolutePosition(50, 30);
         
          
         
          //Alineamos la imagen al centro del documento.         
         // Paragraph p = new Paragraph(" \n",f1);
          PdfPCell c0 = new PdfPCell(imagen);

           // Paragraph d = new Paragraph("Logo");
            PdfPCell c = new PdfPCell(imagen);
            c.setHorizontalAlignment(Element.ALIGN_LEFT);
            c.setColspan(12);
            table2.addCell(c);
            
            Paragraph a = new Paragraph("CROSS AIR SERVICES, S.A. DE C.V.",f1);
            PdfPCell celdaFinal = new PdfPCell(a);
            celdaFinal.setHorizontalAlignment(Element.ALIGN_CENTER);             
            celdaFinal.setColspan(12);
            table2.addCell(celdaFinal);
            Paragraph b =new Paragraph("DISCREPANCY REPORT / REPORTE DE DISCREPANCIAS: \n",fuente);
            PdfPCell c2 =new PdfPCell(b);
            c2.setHorizontalAlignment(Element.ALIGN_CENTER);             
            c2.setColspan(12);
            table2.addCell(c2);
          
            Paragraph p2=new Paragraph("CUSTOMER / CLIENTE:"+det.getNombreEmpresa() ,f1);
            PdfPCell c3 =new PdfPCell(p2);
            c3.setHorizontalAlignment(Element.ALIGN_CENTER);             
            c3.setColspan(12);
            table2.addCell(c3);
            
            Paragraph p3=new Paragraph("MARK/MODEL - MARCA/MODELO:\n\n"+det.getModelo() ,f1);
            PdfPCell c4 =new PdfPCell(p3);
            c4.setHorizontalAlignment(Element.ALIGN_CENTER);             
            c4.setColspan(3);
            table2.addCell(c4);
            
            Paragraph p4=new Paragraph("N/S:\n\n"+det.getNoSerie() ,f1);
            PdfPCell c5 =new PdfPCell(p4);
            c5.setHorizontalAlignment(Element.ALIGN_CENTER);             
            c5.setColspan(3);
            table2.addCell(c5);
            
            Paragraph p5=new Paragraph("REG. / MATRICULA:\n\n"+det.getMatricula() ,f1);
            PdfPCell c6 =new PdfPCell(p5);
            c6.setHorizontalAlignment(Element.ALIGN_CENTER);             
            c6.setColspan(3);
            table2.addCell(c6);
            
            Paragraph p6=new Paragraph("O.T. #:\n\n"+det.getMatricula() ,f1);
            PdfPCell c7 =new PdfPCell(p6);
            c7.setHorizontalAlignment(Element.ALIGN_CENTER);             
            c7.setColspan(3);
            table2.addCell(c7);
            
            Paragraph p7=new Paragraph("DISCREPANCIA No:"+index ,f1);
            PdfPCell c8 =new PdfPCell(p7);
            c8.setHorizontalAlignment(Element.ALIGN_LEFT);             
            c8.setColspan(6);
            table2.addCell(c8);
            
            Paragraph p8=new Paragraph("DATE / FECHA:"+det.getFechaOrden(),f1);
            PdfPCell c9 =new PdfPCell(p8);
            c9.setHorizontalAlignment(Element.ALIGN_LEFT);             
            c9.setColspan(6);
            table2.addCell(c9);
            
            Paragraph p9=new Paragraph("DISCREPANCY / DISCREPANCIA:",f1);
            PdfPCell c10 =new PdfPCell(p9);
            c10.setHorizontalAlignment(Element.ALIGN_LEFT);             
            c10.setColspan(6);
            table2.addCell(c10);
            
            Paragraph p10=new Paragraph("CORRECT ACCTION / ACCION CORRECTIVA:",f1);
            PdfPCell c11 =new PdfPCell(p10);
            c11.setHorizontalAlignment(Element.ALIGN_LEFT);             
            c11.setColspan(6);
            table2.addCell(c11);
            
            Paragraph p11=new Paragraph("\n"+det.getDescripcion()+"\n",fuente);
            PdfPCell c12 =new PdfPCell(p11);
            c12.setHorizontalAlignment(Element.ALIGN_CENTER);             
            c12.setColspan(6);
            table2.addCell(c12);
            
            Paragraph p12=new Paragraph("\n"+det.getAccion()+"\n",fuente);
            PdfPCell c13 =new PdfPCell(p12);
            c13.setHorizontalAlignment(Element.ALIGN_CENTER);             
            c13.setColspan(6);
            table2.addCell(c13);
            
            Paragraph p13=new Paragraph("\n",f1); ////celda invisible
            PdfPCell c14 =new PdfPCell(p13);
            c14.setHorizontalAlignment(Element.ALIGN_CENTER);             
            c14.setColspan(6);
            table2.addCell(c14);
            
            Paragraph p14=new Paragraph("MAN TIME HOURS / TIEMPO HORAS HOMBRE:",f1); 
            PdfPCell c15 =new PdfPCell(p14);
            c15.setHorizontalAlignment(Element.ALIGN_LEFT);             
            c15.setColspan(6);
            table2.addCell(c15);
            
            Paragraph p15=new Paragraph("DESCRIPTION / DESCRIPCION:",f1); 
            PdfPCell c16 =new PdfPCell(p15);
            c16.setHorizontalAlignment(Element.ALIGN_CENTER);             
            c16.setColspan(2);
            table2.addCell(c16);
            
            Paragraph p16=new Paragraph("PART NUMBER / NUMERO DE PARTE:",f1); 
            PdfPCell c17=new PdfPCell(p16);
            c17.setHorizontalAlignment(Element.ALIGN_CENTER);             
            c17.setColspan(2);
            table2.addCell(c17);
            
            Paragraph p17=new Paragraph("QUANTITY / CANTIDAD:",f1); 
            PdfPCell c18=new PdfPCell(p17);
            c18.setHorizontalAlignment(Element.ALIGN_CENTER);             
            c18.setColspan(2);
            table2.addCell(c18);
            
            Paragraph p18=new Paragraph("N/S REMOVABLE / N/S REMOVIDA:",f1); 
            PdfPCell c19=new PdfPCell(p18);
            c19.setHorizontalAlignment(Element.ALIGN_CENTER);             
            c19.setColspan(2);
            table2.addCell(c19);
            
            Paragraph p19=new Paragraph("N/S INSTALL / N/S INSTALADA:",f1); 
            PdfPCell c20=new PdfPCell(p19);
            c20.setHorizontalAlignment(Element.ALIGN_CENTER);             
            c20.setColspan(2);
            table2.addCell(c20);
            
            Paragraph p20=new Paragraph("OSERVATION/OBSERVATIONS:",f1); 
            PdfPCell c21=new PdfPCell(p20);
            c21.setHorizontalAlignment(Element.ALIGN_CENTER);             
            c21.setColspan(2);
            table2.addCell(c21);
            
            List<ComDisVo> cds = det.getComponentes();
            System.out.println("cds:"+cds);
            if (cds.isEmpty()){
            	
	            Paragraph pp=new Paragraph("\n",f1); 
	            PdfPCell cp=new PdfPCell(pp);
	            cp.setHorizontalAlignment(Element.ALIGN_CENTER);             
	            cp.setColspan(2);
	            table2.addCell(cp);table2.addCell(cp);table2.addCell(cp);table2.addCell(cp);table2.addCell(cp);table2.addCell(cp);
	            table2.addCell(cp);table2.addCell(cp);table2.addCell(cp);table2.addCell(cp);table2.addCell(cp);table2.addCell(cp);
	            table2.addCell(cp);table2.addCell(cp);table2.addCell(cp);table2.addCell(cp);table2.addCell(cp);table2.addCell(cp);
	            table2.addCell(cp);table2.addCell(cp);table2.addCell(cp);table2.addCell(cp);table2.addCell(cp);table2.addCell(cp);
            }
            for (ComDisVo cd : cds){
            	
            	System.out.println("cds. nombre:"+cd.getNombre_componente());
	            Paragraph p21=new Paragraph(cd.getNombre_componente(),f1); 
	            PdfPCell c22=new PdfPCell(p21);
	            c22.setHorizontalAlignment(Element.ALIGN_CENTER);             
	            c22.setColspan(2);
	            table2.addCell(c22);
	            
	            System.out.println("cds. parte:"+cd.getNoParte());
	            Paragraph p22=new Paragraph(cd.getNoParte(),f1); 
	            PdfPCell c23=new PdfPCell(p22);
	            c23.setHorizontalAlignment(Element.ALIGN_CENTER);             
	            c23.setColspan(2);
	            table2.addCell(c23);
	            
	            Paragraph p23=new Paragraph(cd.getCantidad().toString(),f1); 
	            PdfPCell c24=new PdfPCell(p23);
	            c24.setHorizontalAlignment(Element.ALIGN_CENTER);             
	            c24.setColspan(2);
	            table2.addCell(c24);
	            
	            Paragraph p24=new Paragraph("N/A",f1); 
	            PdfPCell c25=new PdfPCell(p24);
	            c25.setHorizontalAlignment(Element.ALIGN_CENTER);             
	            c25.setColspan(2);
	            table2.addCell(c25);
	            table2.addCell(c25);
	            
	            Paragraph p25=new Paragraph("las observaciones de horas hombre",f1); 
	            PdfPCell c26=new PdfPCell(p25);
	            c26.setHorizontalAlignment(Element.ALIGN_CENTER);             
	            c26.setColspan(2);
	            table2.addCell(c26);
	            
	            
            
            }
            /////////////////////////validar cuantos comps vienen para poner los demas celdas vacias
           // for (){
            	
          //  }
            Paragraph p26=new Paragraph("REMOVIBLE BY/ REMOVIDO POR:\n",f1); 
            PdfPCell c27=new PdfPCell(p26);
            c27.setHorizontalAlignment(Element.ALIGN_LEFT);             
            c27.setColspan(6);
            table2.addCell(c27);
            
            Paragraph p27=new Paragraph("INSTALL BY/ INSTALADA POR:\n",f1); 
            PdfPCell c28=new PdfPCell(p27);
            c28.setHorizontalAlignment(Element.ALIGN_LEFT);             
            c28.setColspan(6);
            table2.addCell(c28);
            
            Paragraph p28=new Paragraph("SIGN / FIRMA:\n\n",f1); 
            PdfPCell c29=new PdfPCell(p28);
            c29.setHorizontalAlignment(Element.ALIGN_LEFT);             
            c29.setColspan(6);
            table2.addCell(c29);
            table2.addCell(c29);
            
            Paragraph p29=new Paragraph("PERMISSION / LICENCIA:\n",f1); 
            PdfPCell c30=new PdfPCell(p29);
            c30.setHorizontalAlignment(Element.ALIGN_LEFT);             
            c30.setColspan(6);
            table2.addCell(c30);
            table2.addCell(c30);
            
            Paragraph p30=new Paragraph("DATE / FECHA:\n",f1); 
            PdfPCell c31=new PdfPCell(p30);
            c31.setHorizontalAlignment(Element.ALIGN_LEFT);             
            c31.setColspan(6);
            table2.addCell(c31);
            table2.addCell(c31);
            
            Paragraph p31=new Paragraph("CUSTOMER AUTORIZATION / AUTORIZADO POR EL CLIENTE:\n\n NAME / NOMBRE:\n\n SIGN / FIRMA:\n\n",f1); 
            PdfPCell c32=new PdfPCell(p31);
            c32.setHorizontalAlignment(Element.ALIGN_LEFT);             
            c32.setColspan(12);
            table2.addCell(c32);
            
            Paragraph p32=new Paragraph("                                  FORM AUTORIZATION / MEDIO DE AUTORIZACION:\n PERSONAL:\n\n BY TELEFON / VIA TELEFONICA:\n\n "
            		+ "E-MAIL / CORREO ELECTRONICO:\n\n  HOUR / DATE / HORA Y FECHA \n\n",f1); 
            PdfPCell c33=new PdfPCell(p32);
            c33.setHorizontalAlignment(Element.ALIGN_LEFT);             
            c33.setColspan(12);
            table2.addCell(c33);
            
            Paragraph p33=new Paragraph("\n",f1); 
            PdfPCell c34=new PdfPCell(p33);
            c34.setHorizontalAlignment(Element.ALIGN_LEFT);             
            c34.setColspan(6);
            table2.addCell(c34);
            
            Paragraph p34=new Paragraph("Vo Bo INSPECTOR\n\n",f1); 
            PdfPCell c35=new PdfPCell(p34);
            c35.setHorizontalAlignment(Element.ALIGN_LEFT);             
            c35.setColspan(6);
            table2.addCell(c35);
            
            
            document.add(table2);
            document.add(new Paragraph("\n\n\n"));
            
      }

}

