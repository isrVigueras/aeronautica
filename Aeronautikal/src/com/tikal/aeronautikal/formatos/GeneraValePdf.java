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
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.tikal.aeronautikal.controller.vo.ComDisVo;
import com.tikal.aeronautikal.controller.vo.DetalleDiscrepanciaVo;
import com.tikal.aeronautikal.controller.vo.OrdenXlsVo;
import com.tikal.aeronautikal.controller.vo.ValePdfVo;

import java.io.*;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.List; 
public class GeneraValePdf {
	
      public GeneraValePdf(ValePdfVo vp,  OutputStream ops) throws MalformedURLException, IOException {

    	  
    	try {
    		Document document = new Document(PageSize.LETTER.rotate(),20,20,20,20);   	  
    	        PdfWriter.getInstance(document, new FileOutputStream(vp.getNombreArchivo()));
    	    document.open();
    	    
    	    
    	    Font f1 = new Font();
    	    f1.setStyle(2);
    	    f1.setSize(10);
    	    f1.setColor(BaseColor.WHITE);
    	    
    	    Font f2 = new Font();
    	    f2.setStyle(2);
    	    f2.setSize(10);
    	    f2.setColor(BaseColor.BLACK);
    	   
            PdfPTable table = new PdfPTable(16);              
            
            Image imagen = Image.getInstance("img/LogoCross.png");
            imagen.scaleAbsolute(150, 45);
           
      
            PdfPCell c1 = new PdfPCell(imagen);
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            c1.setVerticalAlignment(Element.ALIGN_CENTER);
            c1.setColspan(6);
            c1.setRowspan(4);
            c1.setBorder(Rectangle.NO_BORDER);
            table.addCell(c1);
           
            Paragraph p2 = new Paragraph("\n");
            PdfPCell c2 = new PdfPCell(p2);
            c2.setHorizontalAlignment(Element.ALIGN_LEFT);
            c2.setColspan(6);
            c2.setBorder(Rectangle.NO_BORDER);
            table.addCell(c2);
            
            Paragraph p3 = new Paragraph("fecha: "+vp.getFechaVale(),f2);
            PdfPCell c3 = new PdfPCell(p3);
            c3.setHorizontalAlignment(Element.ALIGN_LEFT);
            c3.setColspan(4);
            table.addCell(c3);
            
            Paragraph p4 = new Paragraph("matricula: "+vp.getMatricula(),f2);
            PdfPCell c4 = new PdfPCell(p4);
            c4.setHorizontalAlignment(Element.ALIGN_LEFT);
            c4.setColspan(4);
            table.addCell(c4);
            
            Paragraph p5 = new Paragraph("No Serie: "+vp.getNoSerie(),f2);
            PdfPCell c5 = new PdfPCell(p5);
            c5.setHorizontalAlignment(Element.ALIGN_LEFT);
            c5.setColspan(4);
            table.addCell(c5);
            
            Paragraph p6 = new Paragraph("\n");
            PdfPCell c6 = new PdfPCell(p6);
            c6.setHorizontalAlignment(Element.ALIGN_LEFT);
            c6.setColspan(2);
            c6.setBorder(Rectangle.NO_BORDER);
            table.addCell(c6);
            
            Paragraph p7 = new Paragraph("Orden :"+vp.getNoOrden(),f2);
            PdfPCell c7 = new PdfPCell(p7);
            c7.setHorizontalAlignment(Element.ALIGN_LEFT);
            c7.setColspan(4);
            table.addCell(c7);
            
            Paragraph p8 = new Paragraph("Discrepancia :"+vp.getNoDiscrepancia(),f2);
            PdfPCell c8 = new PdfPCell(p8);
            c8.setHorizontalAlignment(Element.ALIGN_LEFT);
            c8.setColspan(6);
            table.addCell(c8);
           // table.addCell(c6);
            
            Paragraph px = new Paragraph("\n");
            PdfPCell cx = new PdfPCell(px);
            cx.setHorizontalAlignment(Element.ALIGN_LEFT);
            cx.setColspan(10);
            cx.setBorder(Rectangle.NO_BORDER);
            table.addCell(cx);
            
            document.add(new Paragraph("\n\n\n"));
    // segunda parte... tabla 2

            document.add(table);
            PdfPTable table2 = new PdfPTable(16);        
            
            
            
            Paragraph pxx = new Paragraph("\n");
            PdfPCell cxx = new PdfPCell(pxx);
            cxx.setHorizontalAlignment(Element.ALIGN_LEFT);
            cxx.setColspan(16);
            cxx.setBorder(Rectangle.NO_BORDER);
            table.addCell(cxx);
            
            Paragraph p9 = new Paragraph("ITEM",f1);
            PdfPCell c9 = new PdfPCell(p9);
            //c9.setBackgroundColor(black);
            c9.setHorizontalAlignment(Element.ALIGN_LEFT);
            c9.setColspan(1);
            c9.setRowspan(1);
            c9.setBackgroundColor(BaseColor.BLACK);
            table2.addCell(c9);
            
            Paragraph p1 = new Paragraph("No de Parte",f1);
            PdfPCell c11 = new PdfPCell(p1);
            c11.setHorizontalAlignment(Element.ALIGN_LEFT);
            c11.setBackgroundColor(BaseColor.BLACK);
            c11.setColspan(3);
            table2.addCell(c11);
            
            Paragraph p10 = new Paragraph("No de serie",f1);
            PdfPCell c10 = new PdfPCell(p10);
            c10.setHorizontalAlignment(Element.ALIGN_LEFT);
            c10.setBackgroundColor(BaseColor.BLACK);
            c10.setColspan(2);
            table2.addCell(c10);
            
            Paragraph p12 = new Paragraph("Descripcion",f1);
            PdfPCell c12 = new PdfPCell(p12);
            c12.setHorizontalAlignment(Element.ALIGN_LEFT);
            c12.setBackgroundColor(BaseColor.BLACK);
            c12.setColspan(4);
            table2.addCell(c12);
            
            Paragraph p13 = new Paragraph("Condicion",f1);
            PdfPCell c13 = new PdfPCell(p13);
            c13.setHorizontalAlignment(Element.ALIGN_LEFT);
            c13.setBackgroundColor(BaseColor.BLACK);
            c13.setColspan(2);
            table2.addCell(c13);
            
            Paragraph p14 = new Paragraph("Cantidad",f1);
            PdfPCell c14 = new PdfPCell(p14);
            c14.setHorizontalAlignment(Element.ALIGN_LEFT);
            c14.setBackgroundColor(BaseColor.BLACK);
            c14.setColspan(2);
            table2.addCell(c14);
            
            Paragraph p15 = new Paragraph("Observaciones",f1);
            PdfPCell c15 = new PdfPCell(p15);
            c15.setHorizontalAlignment(Element.ALIGN_LEFT);
            c15.setBackgroundColor(BaseColor.BLACK);
            c15.setColspan(2);
            table2.addCell(c15);
            
            ////////imprimiendo os componentes
           
             for (int i=1; i<=vp.getItems().size(); i++){            	 
            	 System.out.println("sizeeeeeee:"+vp.getItems().size());
               Paragraph p16 =new Paragraph(String.valueOf(i)+"\n",f2);
               PdfPCell c16 =new PdfPCell(p16);
               c16.setHorizontalAlignment(Element.ALIGN_CENTER);             
               c16.setColspan(1);
               c16.setRowspan(2);
               table2.addCell(c16);       
               
               Paragraph p17 =new Paragraph(vp.getItems().get((i-1)).getNoParte()+"\n",f2);
               PdfPCell c17 =new PdfPCell(p17);
               c17.setHorizontalAlignment(Element.ALIGN_CENTER);             
               c17.setColspan(3);
               c17.setRowspan(2);
               table2.addCell(c17); 
               
               Paragraph p18 =new Paragraph(vp.getItems().get((i-1)).getNoSerie()+"\n",f2);
               PdfPCell c18 =new PdfPCell(p18);
               c18.setHorizontalAlignment(Element.ALIGN_CENTER);             
               c18.setColspan(2);
               c18.setRowspan(2);
               table2.addCell(c18);                
               
               Paragraph p19 =new Paragraph(vp.getItems().get((i-1)).getDescripcion()+"\n",f2);
               PdfPCell c19 =new PdfPCell(p19);
               c19.setHorizontalAlignment(Element.ALIGN_LEFT);             
               c19.setColspan(4);
               c19.setRowspan(2);
               table2.addCell(c19); 
               
               Paragraph p22 =new Paragraph(String.valueOf(vp.getItems().get((i-1)).getCondicion())+"\n",f2);
               PdfPCell c22 =new PdfPCell(p22);
               c22.setHorizontalAlignment(Element.ALIGN_CENTER);             
               c22.setColspan(2);
               c22.setRowspan(2);
               table2.addCell(c22); 
               
               Paragraph p20 =new Paragraph(String.valueOf(vp.getItems().get((i-1)).getCantidad())+"\n",f2);
               PdfPCell c20 =new PdfPCell(p20);
               c20.setHorizontalAlignment(Element.ALIGN_CENTER);             
               c20.setColspan(2);
               c20.setRowspan(2);
               table2.addCell(c20); 
               
               Paragraph p21 =new Paragraph("\n\n",f2);
               PdfPCell c21 =new PdfPCell(p21);
               c21.setHorizontalAlignment(Element.ALIGN_LEFT);             
               c21.setColspan(2);
               c21.setRowspan(2);
               table2.addCell(c21); 
                
          }
            
             for (int i=vp.getItems().size() ; i<12 ;i++){
            	 System.out.println("imprime las celdas vacias");
            	  Paragraph p26 =new Paragraph(String.valueOf(i+1)+"\n",f2);
                  PdfPCell c26 =new PdfPCell(p26);
                  c26.setHorizontalAlignment(Element.ALIGN_CENTER);             
                  c26.setColspan(1);
                  c26.setRowspan(2);
                  table2.addCell(c26);       
                  
                  Paragraph p27 =new Paragraph("\n\n",f2);
                  PdfPCell c27 =new PdfPCell(p27);
                  c27.setHorizontalAlignment(Element.ALIGN_CENTER);             
                  c27.setColspan(3);
                  c27.setRowspan(2);
                  table2.addCell(c27); 
                  
                  Paragraph p28 =new Paragraph("\n\n",f2);
                  PdfPCell c28 =new PdfPCell(p28);
                  c28.setHorizontalAlignment(Element.ALIGN_CENTER);             
                  c28.setColspan(2);
                  c28.setRowspan(2);
                  table2.addCell(c28);                
                  
                  Paragraph p29 =new Paragraph("\n\n",f2);
                  PdfPCell c29 =new PdfPCell(p29);
                  c29.setHorizontalAlignment(Element.ALIGN_LEFT);             
                  c29.setColspan(4);
                  c29.setRowspan(2);
                  table2.addCell(c29); 
                  table2.addCell(c28);
                  table2.addCell(c28);
                  table2.addCell(c28);
                  
             }
            
            document.add(table2);
            
           // document.add(new Paragraph("\n\n\n"));
            
            PdfPTable table3 = new PdfPTable(16); 
            
            Paragraph p29 =new Paragraph("la",f1);
            PdfPCell c29 =new PdfPCell(p29);
            c29.setHorizontalAlignment(Element.ALIGN_CENTER);     
            c29.setColspan(16);
            c29.setBorder(Rectangle.NO_BORDER);
            table3.addCell(c29); 
            
            Paragraph p30 =new Paragraph("Observaciones",f1);
            PdfPCell c30 =new PdfPCell(p30);
            c30.setHorizontalAlignment(Element.ALIGN_CENTER);     
            c30.setBackgroundColor(BaseColor.BLACK);
            c30.setColspan(4);
            c30.setRowspan(1);
            table3.addCell(c30); 
            
            Paragraph p31 =new Paragraph("\n",f2);
            PdfPCell c31 =new PdfPCell(p31);
            c31.setHorizontalAlignment(Element.ALIGN_CENTER);     
            c31.setColspan(12);
            table3.addCell(c31); 
            
            
            Paragraph p32 =new Paragraph("\n",f2);
            PdfPCell c32 =new PdfPCell(p32);
            c32.setHorizontalAlignment(Element.ALIGN_CENTER);     
            c32.setColspan(16);
  
            table3.addCell(c32); 
            table3.addCell(c32);
            table3.addCell(c32);
            table3.addCell(c29); 
           // table3.addCell(c32);
            
            Paragraph p33 =new Paragraph("\n\n",f2);
            PdfPCell c33 =new PdfPCell(p33);
            c33.setHorizontalAlignment(Element.ALIGN_CENTER);     
            c33.setColspan(4);
            table3.addCell(c33); 
            
            
            Paragraph p34 =new Paragraph("\n\n",f2);
            PdfPCell c34 =new PdfPCell(p34);
            c34.setHorizontalAlignment(Element.ALIGN_CENTER);     
            c34.setColspan(2);
            c34.setBorder(Rectangle.NO_BORDER);
            table3.addCell(c34); 
            
            table3.addCell(c33); 
            table3.addCell(c34);
            //table3.addCell(c33); 

            
            table3.addCell(c33); //celda sin borde
            
            Paragraph p35 =new Paragraph("Solicitante\n Nombre y Firma",f2);
            PdfPCell c35=new PdfPCell(p35);
            c35.setHorizontalAlignment(Element.ALIGN_CENTER);     
            c35.setColspan(4);
            c35.setBorder(Rectangle.NO_BORDER);
            table3.addCell(c35); 
            
            table3.addCell(c34);
            
            Paragraph p36 =new Paragraph("Entregó\n Nombre y Firma",f2);
            PdfPCell c36=new PdfPCell(p36);
            c36.setHorizontalAlignment(Element.ALIGN_CENTER);     
            c36.setColspan(4);
            c36.setBorder(Rectangle.NO_BORDER);
            table3.addCell(c36); 
            
            table3.addCell(c34);
            
            Paragraph p37 =new Paragraph("Autorizó\n Nombre y Firma",f2);
            PdfPCell c37=new PdfPCell(p37);
            c37.setHorizontalAlignment(Element.ALIGN_CENTER);     
            c37.setColspan(4);
            c37.setBorder(Rectangle.NO_BORDER);
            table3.addCell(c37); 
            
            ////////////termina de la orden, empieza a generar las discrepancias      
            document.add(table3);
            
            document.close();
    	    System.out.println("Your PDF file has been generated!(¡Se ha generado tu hoja PDF!");
    	} catch (DocumentException documentException) {
    	    System.out.println("The file not exists (Se ha producido un error al generar un documento): " + documentException);
    	}
    }

      
      public void GeneraComDisPdf(DetalleDiscrepanciaVo det, Document document) throws DocumentException {
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
            Paragraph d = new Paragraph("Logo");
            PdfPCell c0 = new PdfPCell(d);
            c0.setHorizontalAlignment(Element.ALIGN_LEFT);
            c0.setColspan(12);
            table2.addCell(c0);
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
            
            Paragraph p7=new Paragraph("DISCREPANCIA No:"+d.lastIndexOf(det) ,f1);
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

