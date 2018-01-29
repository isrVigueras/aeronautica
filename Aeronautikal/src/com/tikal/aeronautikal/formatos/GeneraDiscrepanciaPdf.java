package com.tikal.aeronautikal.formatos;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;

import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.tikal.aeronautikal.controller.vo.ComDisVo;
import com.tikal.aeronautikal.controller.vo.DetalleDiscrepanciaVo;


import java.io.*;
import java.net.MalformedURLException;

import java.util.List; 
public class GeneraDiscrepanciaPdf {
	
	public GeneraDiscrepanciaPdf(DetalleDiscrepanciaVo det, OutputStream ops) throws MalformedURLException, IOException {
 	   //public void GeneraDiscrepanciaPdf(DetalleDiscrepanciaVo det, Document document, Integer index) throws DocumentException, MalformedURLException, IOException {
 	  // Aquí introduciremos el código para crear el PDF.     
 	  try {
   	    Document document = new Document(PageSize.LETTER, 30,30,30,30);
   	    PdfWriter.getInstance(document,ops);
   	    document.open();
  
   
 	  	PdfPTable table2 = new PdfPTable(8);      
 	    Font fuente = new Font();
	  	    fuente.setStyle(1);
	  	    fuente.setSize(10);
	  	    fuente.setStyle(Font.BOLD);
	  	    Font f1 = new Font();
	  	    f1.setStyle(2);
	  	    f1.setSize(9);
	  	    Font f2 = new Font();
	  	    f2.setStyle(3);
	  	    f2.setSize(7);
	  	    
	  	  Image imagen = Image.getInstance("img/LogoCross.png");
       imagen.scaleAbsolute(200, 50);
       imagen.setSpacingBefore(1);
       imagen.setSpacingAfter(90);
       imagen.setAbsolutePosition(50, 30);
      
       
      
       //Alineamos la imagen al centro del documento.         
      // Paragraph p = new Paragraph(" \n",f1);
       document.add(new Paragraph("\n\n\n"));

        // Paragraph d = new Paragraph("Logo");
         PdfPCell c = new PdfPCell(imagen);
         c.setHorizontalAlignment(Element.ALIGN_LEFT);
         c.setColspan(8);
         c.setRowspan(4);
         table2.addCell(c);
         
         Paragraph a = new Paragraph("CROSS AIR SERVICES, S.A. DE C.V.",f1);
         PdfPCell celdaFinal = new PdfPCell(a);
         celdaFinal.setHorizontalAlignment(Element.ALIGN_CENTER);             
         celdaFinal.setColspan(8);
         table2.addCell(celdaFinal);
         
         Paragraph b =new Paragraph("DISCREPANCY REPORT / REPORTE DE DISCREPANCIAS",fuente);
         PdfPCell c2 =new PdfPCell(b);
         c2.setHorizontalAlignment(Element.ALIGN_CENTER);     
         c2.setVerticalAlignment(Element.ALIGN_CENTER);
         c2.setColspan(8);
         c2.setRowspan(2);
         table2.addCell(c2);
       
         Paragraph p2=new Paragraph("CUSTOMER / CLIENTE:"+det.getNombreEmpresa() ,f1);
         PdfPCell c3 =new PdfPCell(p2);
         c3.setHorizontalAlignment(Element.ALIGN_LEFT);             
         c3.setColspan(8);
         table2.addCell(c3);
         
         Paragraph p3=new Paragraph("MARK/MODEL - MARCA/MODELO:\n\n"+det.getModelo() ,f1);
         PdfPCell c4 =new PdfPCell(p3);
         c4.setHorizontalAlignment(Element.ALIGN_LEFT);             
         c4.setColspan(2);
         table2.addCell(c4);
         
         Paragraph p4=new Paragraph("\nN/S:\n\n"+det.getNoSerie() ,f1);
         PdfPCell c5 =new PdfPCell(p4);
         c5.setHorizontalAlignment(Element.ALIGN_LEFT);             
         c5.setColspan(2);
         table2.addCell(c5);
         
         Paragraph p5=new Paragraph("\nREG. / MATRICULA:\n\n"+det.getMatricula() ,f1);
         PdfPCell c6 =new PdfPCell(p5);
         c6.setHorizontalAlignment(Element.ALIGN_LEFT);             
         c6.setColspan(2);
         table2.addCell(c6);
         
         Paragraph p6=new Paragraph("\nO.T. #:\n\n"+det.getMatricula() ,f1);
         PdfPCell c7 =new PdfPCell(p6);
         c7.setHorizontalAlignment(Element.ALIGN_CENTER);             
         c7.setColspan(2);
         table2.addCell(c7);
         
         Paragraph p7=new Paragraph("DISCREPANCIA No:"+det.getId(),f1);
         PdfPCell c8 =new PdfPCell(p7);
         c8.setHorizontalAlignment(Element.ALIGN_LEFT);             
         c8.setColspan(4);
         table2.addCell(c8);
         
         Paragraph p8=new Paragraph("DATE / FECHA: "+det.getFechaOrden(),f1);
         PdfPCell c9 =new PdfPCell(p8);
         c9.setHorizontalAlignment(Element.ALIGN_LEFT);             
         c9.setColspan(4);
         table2.addCell(c9);
         
         Paragraph p9=new Paragraph("DISCREPANCY / DISCREPANCIA:",f1);
         PdfPCell c10 =new PdfPCell(p9);
         c10.setHorizontalAlignment(Element.ALIGN_LEFT);             
         c10.setColspan(4);
         table2.addCell(c10);
         
         Paragraph p10=new Paragraph("CORRECT ACCTION / ACCION CORRECTIVA:",f1);
         PdfPCell c11 =new PdfPCell(p10);
         c11.setHorizontalAlignment(Element.ALIGN_LEFT);             
         c11.setColspan(4);//c11.setFixedHeight(20f);
         table2.addCell(c11);
         
         Paragraph p11=new Paragraph("\n"+det.getDescripcion()+"\n\n",fuente);
         PdfPCell c12 =new PdfPCell(p11);
         c12.setHorizontalAlignment(Element.ALIGN_CENTER);             
         c12.setColspan(4);
         c12.setRowspan(6);
         table2.addCell(c12);
         
         Paragraph p12=new Paragraph("\n"+det.getAccion()+"\n",fuente);
         PdfPCell c13 =new PdfPCell(p12);
         c13.setHorizontalAlignment(Element.ALIGN_CENTER);             
         c13.setColspan(4);
         c13.setRowspan(5);
         table2.addCell(c13);
         
//         Paragraph p13=new Paragraph("\n",f1); ////celda invisible
//         PdfPCell c14 =new PdfPCell(p13);
//         c14.setHorizontalAlignment(Element.ALIGN_CENTER);             
//         c14.setColspan(6);
//         c14.setRowspan(1);
//         table2.addCell(c14);
         
         Paragraph p14=new Paragraph("MAN TIME HOURS / TIEMPO HORAS HOMBRE:",f1); 
         PdfPCell c15 =new PdfPCell(p14);
         c15.setHorizontalAlignment(Element.ALIGN_LEFT);             
         c15.setColspan(4);
         table2.addCell(c15);
         
         Paragraph p15=new Paragraph("DESCRIPTION / DESCRIPCION:",f2); 
         PdfPCell c16 =new PdfPCell(p15);
         c16.setHorizontalAlignment(Element.ALIGN_CENTER);             
         c16.setColspan(2);
         table2.addCell(c16);
         
         Paragraph p16=new Paragraph("PART NUMBER\n / NUMERO DE PARTE:",f2); 
         PdfPCell c17=new PdfPCell(p16);
         c17.setHorizontalAlignment(Element.ALIGN_CENTER);             
         c17.setColspan(1);
         table2.addCell(c17);
         
         Paragraph p17=new Paragraph("QUANTITY / CANTIDAD:",f2); 
         PdfPCell c18=new PdfPCell(p17);
         c18.setHorizontalAlignment(Element.ALIGN_CENTER);             
         c18.setColspan(1);
         table2.addCell(c18);
         
         Paragraph p18=new Paragraph("N/S REMOVABLE /\n N/S REMOVIDA:",f2); 
         PdfPCell c19=new PdfPCell(p18);
         c19.setHorizontalAlignment(Element.ALIGN_CENTER);             
         c19.setColspan(2);
         table2.addCell(c19);
         
         Paragraph p19=new Paragraph("N/S INSTALL / N/S INSTALADA:",f2); 
         PdfPCell c20=new PdfPCell(p19);
         c20.setHorizontalAlignment(Element.ALIGN_CENTER);             
         c20.setColspan(1);
         table2.addCell(c20);
         
         Paragraph p20=new Paragraph("OSERVATION/OBSERVATIONS:",f2); 
         PdfPCell c21=new PdfPCell(p20);
         c21.setHorizontalAlignment(Element.ALIGN_CENTER);             
         c21.setColspan(1);
         table2.addCell(c21);
         
         List<ComDisVo> cds = det.getComponentes();
         System.out.println("cds:"+cds);
//         if (cds.isEmpty()){
//         	
//	            Paragraph pp=new Paragraph("\n",f1); 
//	            PdfPCell cp=new PdfPCell(pp);
//	            cp.setHorizontalAlignment(Element.ALIGN_CENTER);             
//	            cp.setColspan(2);
//	            table2.addCell(cp);table2.addCell(cp);table2.addCell(cp);table2.addCell(cp);table2.addCell(cp);table2.addCell(cp);
//	            table2.addCell(cp);table2.addCell(cp);table2.addCell(cp);table2.addCell(cp);table2.addCell(cp);table2.addCell(cp);
//	            table2.addCell(cp);table2.addCell(cp);table2.addCell(cp);table2.addCell(cp);table2.addCell(cp);table2.addCell(cp);
//	            table2.addCell(cp);table2.addCell(cp);table2.addCell(cp);table2.addCell(cp);table2.addCell(cp);table2.addCell(cp);
//         }
         //imprime los componentes que estan en la discrepancia
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
	            c23.setColspan(1);
	            table2.addCell(c23);
	            
	            Paragraph p23=new Paragraph(cd.getCantidad().toString(),f1); 
	            PdfPCell c24=new PdfPCell(p23);
	            c24.setHorizontalAlignment(Element.ALIGN_CENTER);             
	            c24.setColspan(1);
	            table2.addCell(c24);
	            
	            Paragraph p24=new Paragraph("N/A",f1); 
	            PdfPCell c25=new PdfPCell(p24);
	            c25.setHorizontalAlignment(Element.ALIGN_CENTER);             
	            c25.setColspan(2);
	            table2.addCell(c25);
	         
	            
	            Paragraph p25=new Paragraph("N/A",f1); 
	            PdfPCell c26=new PdfPCell(p25);
	            c26.setHorizontalAlignment(Element.ALIGN_CENTER);             
	            c26.setColspan(1);
	            table2.addCell(c26);
	            
	            Paragraph p26=new Paragraph("obs",f1); 
	            PdfPCell c27=new PdfPCell(p26);
	            c27.setHorizontalAlignment(Element.ALIGN_CENTER);             
	            c27.setColspan(1);
	            table2.addCell(c27);
	            
	            
         }
        
         System.out.println("indice de cds"+cds.size());
         if (cds.size()<4){
         	Integer faltan=cds.size();
         	for (int i=faltan; i<4; i++){
         		 Paragraph pp=new Paragraph("\n",f1); 
  	            PdfPCell cp=new PdfPCell(pp);
  	            cp.setHorizontalAlignment(Element.ALIGN_CENTER);             
  	            cp.setColspan(2);
  	            cp.setRowspan(1);
  	            table2.addCell(cp);
  	            
  	          Paragraph p1=new Paragraph("\n",f1); 
	            PdfPCell cp1=new PdfPCell(p1);
	            cp1.setHorizontalAlignment(Element.ALIGN_CENTER);             
	            cp1.setColspan(1);
	            cp1.setRowspan(1);
	            table2.addCell(cp1);
	            table2.addCell(cp1);
	            
  	            
  	            table2.addCell(cp);table2.addCell(cp1);table2.addCell(cp1);
         		
         	}
         }
         /////////////////////////validar cuantos comps vienen para poner los demas celdas vacias
        // for (){
         	
       //  }
         Paragraph p26=new Paragraph("REMOVIBLE BY/ REMOVIDO POR:\n\n",f1); 
         PdfPCell c27=new PdfPCell(p26);
         c27.setHorizontalAlignment(Element.ALIGN_LEFT);             
         c27.setColspan(4);
         table2.addCell(c27);
         
         Paragraph p27=new Paragraph("INSTALL BY/ INSTALADA POR:\n\n",f1); 
         PdfPCell c28=new PdfPCell(p27);
         c28.setHorizontalAlignment(Element.ALIGN_LEFT);             
         c28.setColspan(4);
         table2.addCell(c28);
         
         Paragraph p28=new Paragraph("SIGN / FIRMA:\n\n",f1); 
         PdfPCell c29=new PdfPCell(p28);
         c29.setHorizontalAlignment(Element.ALIGN_LEFT);             
         c29.setColspan(4);
         table2.addCell(c29);
         table2.addCell(c29);
         
         Paragraph p29=new Paragraph("PERMISSION / LICENCIA:\n\n",f1); 
         PdfPCell c30=new PdfPCell(p29);
         c30.setHorizontalAlignment(Element.ALIGN_LEFT);             
         c30.setColspan(4);
         table2.addCell(c30);
         table2.addCell(c30);
         
         Paragraph p30=new Paragraph("DATE / FECHA:\n\n",f1); 
         PdfPCell c31=new PdfPCell(p30);
         c31.setHorizontalAlignment(Element.ALIGN_LEFT);             
         c31.setColspan(4);
         table2.addCell(c31);
         table2.addCell(c31);
         
         Paragraph p31=new Paragraph("CUSTOMER AUTORIZATION / AUTORIZADO POR EL CLIENTE:\n\n NAME / NOMBRE:\n\n SIGN / FIRMA:\n\n",f1); 
         PdfPCell c32=new PdfPCell(p31);
         c32.setHorizontalAlignment(Element.ALIGN_LEFT);             
         c32.setColspan(8);
         table2.addCell(c32);
         
         Paragraph p32=new Paragraph("                                  FORM AUTORIZATION / MEDIO DE AUTORIZACION:\n PERSONAL:\n\n BY TELEFON / VIA TELEFONICA:\n\n "
         		+ "E-MAIL / CORREO ELECTRONICO:\n\n  HOUR / DATE / HORA Y FECHA \n\n",f1); 
         PdfPCell c33=new PdfPCell(p32);
         c33.setHorizontalAlignment(Element.ALIGN_LEFT);             
         c33.setColspan(8);
         table2.addCell(c33);
         
         Paragraph p33=new Paragraph("\n",f1); 
         PdfPCell c34=new PdfPCell(p33);
         c34.setHorizontalAlignment(Element.ALIGN_LEFT);             
         c34.setColspan(4);
         c34.setBorder(Rectangle.NO_BORDER);
         table2.addCell(c34);
         
         Paragraph p34=new Paragraph("Vo Bo INSPECTOR\n\n",f1); 
         PdfPCell c35=new PdfPCell(p34);
         c35.setHorizontalAlignment(Element.ALIGN_LEFT);             
         c35.setColspan(4);
         table2.addCell(c35);
         
         
         document.add(table2);
         document.add(new Paragraph("\n\n\n"));
         document.close();
 	    System.out.println("Your PDF file has been generated!(¡Se ha generado tu hoja PDF!");
 	} catch (DocumentException documentException) {
 	    System.out.println("The file not exists (Se ha producido un error al generar un documento): " + documentException);
 	}
 }


}