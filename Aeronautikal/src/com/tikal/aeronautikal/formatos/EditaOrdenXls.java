package com.tikal.aeronautikal.formatos;

import java.io.*;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook; 
 
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.googlecode.objectify.annotation.Entity;
import com.tikal.aeronautikal.controller.vo.OrdenXlsVo; 
 
/** 
 * Utility class, where we will create methods for training read and write excel files,
 * with <a href="https://poi.apache.org/">Apache POI</a>, we use 
 * <a href="https://poi.apache.org/spreadsheet/">POI-HSSF and POI-XSSF - Java API To Access Microsoft</a>
 * HSSF is the POI Project's pure Java implementation of the Excel '97(-2007) file.
 * 
 * Clase de utilidades, donde crearemos métodos
 * para el aprendizaje de la lectura y escritura de ficheros excel con 
 * <a href="https://poi.apache.org/">Apache POI</a>, usaremos
 * <a href="https://poi.apache.org/spreadsheet/">POI-HSSF and POI-XSSF - Java API To Access Microsoft</a>
 * HSSF es el proyecto POI de implementación tota333l en Java para ficheros Excel '97(-2007).
 *
 * @author Xules You can follow me on my website http://www.codigoxules.org/en
 * Puedes seguirme en mi <span id="IL_AD7" class="IL_AD">web</span> http://www.codigoxules.org).
 */


public class EditaOrdenXls {

	 
	    /**
	     * Explanation of the method by which we read the excel file we pass as
	     * parameter if exists, and where we copy its content in a new excel spreadsheet 
	     * is also passed as a parameter.
	     * Método con el que leemos el fichero excel que pasamos como
	     * parámetro si existe y donde copiamos su contenido en una nueva hoja excel que
	     * también se pasa como parámetro.
	     * @param excelFile <code>String</code> 
	     *      excel File we are going to read. 
	     *      Fichero excel que vamos a leer. 
	     * @param excelNewFile <code>String</code> 
	     *      excel File we are going to write. 
	     *      Fichero excel en el que vamos a escribir. 
	     */
	    public void readWriteExcelFile(File excelFile, File excelNewFile){
	        InputStream excelStream = null;
	        OutputStream excelNewOutputStream = null;
	        try {            
	            excelStream = new FileInputStream(excelFile);
	            excelNewOutputStream = new FileOutputStream(excelNewFile);
	            // High level representation of a workbook.
	            // Representación del más alto nivel de la hoja excel.
	            HSSFWorkbook hssfWorkbook = new HSSFWorkbook(excelStream);
	            HSSFWorkbook hssfWorkbookNew = new HSSFWorkbook();
	            // We chose the sheet is passed as parameter. 
	            // Elegimos la hoja que se pasa por parámetro.
	            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
	            // We create the new sheet we are going to use.
	            // Creamos la hoja nueva que vamos a utilizar.
	            HSSFSheet hssfSheetNew = hssfWorkbookNew.createSheet("Copy-Copia");
	            // An object that allows us to read a row of the excel sheet, and extract from it the cell contents.
	            // Objeto que nos permite leer un fila de la hoja excel, y de aquí extraer el contenido de las celdas.
	            HSSFRow hssfRow;
	            HSSFRow hssfRowNew;
	            // Initialize the object to read the value of the cell 
	            // Inicializo el objeto que leerá el valor de la celda
	            HSSFCell cellNew;            
	            // I get the number of rows occupied on the sheet
	            // Obtengo el número de filas ocupadas en la hoja
	            int rows = hssfSheet.getLastRowNum();  
	            String cellValue; 
	            
	            // For this example we'll loop through the rows getting all the cells to copy them in the new sheet.
	            // Para este ejemplo vamos a recorrer todas las filas para obtener todas las celdas y copiarlas en la nueva hoja.
	            for (int r = 0; r < rows; r++) { hssfRow = hssfSheet.getRow(r); if (hssfRow == null){ break; }else{ System.out.print("Row: " + r + " -> ");
	                    // Creamos la columna en la nueva excel
	                    hssfRowNew = hssfSheetNew.createRow(r + 10);   
	                    for (int c = 0; c < hssfRow.getLastCellNum(); c++) {
	                        /* 
	                            We have those cell types (tenemos estos tipos de celda): 
	                                CELL_TYPE_BLANK, CELL_TYPE_NUMERIC, CELL_TYPE_BLANK, CELL_TYPE_FORMULA, CELL_TYPE_BOOLEAN, CELL_TYPE_ERROR
	                        */
	                        cellValue = hssfRow.getCell(c) == null?"":
	                                (hssfRow.getCell(c).getCellType() == Cell.CELL_TYPE_STRING)?hssfRow.getCell(c).getStringCellValue():
	                                (hssfRow.getCell(c).getCellType() == Cell.CELL_TYPE_NUMERIC)?"" + hssfRow.getCell(c).getNumericCellValue():
	                                (hssfRow.getCell(c).getCellType() == Cell.CELL_TYPE_BOOLEAN)?"" + hssfRow.getCell(c).getBooleanCellValue():
	                                //(hssfRow.getCell(c).getCellType() == Cell.CELL_TYPE_BLANK)?"BLANK":
	                                (hssfRow.getCell(c).getCellType() == Cell.CELL_TYPE_FORMULA)?"FORMULA":
	                                (hssfRow.getCell(c).getCellType() == Cell.CELL_TYPE_ERROR)?"ERROR":"";                                
	                        System.out.print("[Column " + c + ": " + cellValue + "] ");                        
	                        cellNew = hssfRowNew.createCell(c);
	                        cellNew.setCellType(HSSFCell.CELL_TYPE_STRING);
	                        cellNew.setCellValue(cellValue);        
	                    }
	                    System.out.println();
	                }
	            }                   
	            hssfWorkbookNew.write(excelNewOutputStream);
	            excelNewOutputStream.close();
	            System.out.println("Your excel file has been generated!(¡Se ha generado tu hoja excel!");
	        } catch (FileNotFoundException fileNotFoundException) {
	            System.out.println("The file not exists (No se encontró el fichero): " + fileNotFoundException);
	        } catch (IOException ex) {
	            System.out.println("Error in file procesing (Error al procesar el fichero): " + ex);
	        } finally {
	            try {
	                excelStream.close();
	            } catch (IOException ex) {
	                System.out.println("Error in file processing after close it (Error al procesar el fichero después de cerrarlo): " + ex);
	            }
	        }
	    }    
	     
	    
	  
	    	public void FileCopy(String sourceFile, String destinationFile) {
	    		System.out.println("Desde: " + sourceFile);
	    		System.out.println("Hacia: " + destinationFile);

	    		try {
	    			File inFile = new File(sourceFile);
	    			File outFile = new File(destinationFile);

	    			FileInputStream in = new FileInputStream(inFile);
	    			FileOutputStream out = new FileOutputStream(outFile);

	    			int c;
	    			while( (c = in.read() ) != -1)
	    				out.write(c);

	    			in.close();
	    			out.close();
	    		} catch(IOException e) {
	    			System.err.println("Hubo un error de entrada/salida!!!");
	    		}
	    	}
	    
	    	
	    	public void SendInfo( String destinationFile) {
	    	
	    	}
	    	
	    	// @SuppressWarnings("deprecation")
			public static void WriteXls(OrdenXlsVo ox) throws IOException{
				int renglon=0;
			  	int columna=0;
				System.out.println("ya esta escribiendo..........:");
	    		 	FileInputStream file = new FileInputStream(new File(ox.getNombreArchivo()));
	    		 	System.out.println("1111111");
	    		 	HSSFWorkbook workbook = new HSSFWorkbook(file);
	    		 	System.out.println("222222");
	    		  	HSSFSheet sheet = workbook.getSheetAt(0);
	    		  	System.out.println("nombre de hoja:"+ 	sheet.getSheetName());
	    		  	Iterator<Row> rowIterator = sheet.iterator();
	    		 	Row row;
	    		  	while (rowIterator.hasNext()){
	    		  		row = rowIterator.next();	
	    		  		 Iterator<Cell> cellIterator = row.cellIterator();
  	    		 	     renglon = renglon +1;

	    		  	    while (cellIterator.hasNext()){
	    		  	    	columna=columna+1;
	    		  	    	Cell celda;
	    		  	    	celda = cellIterator.next();
//	    		 	 		// Dependiendo del formato de la celda el valor se debe mostrar como String, Fecha, boolean, entero...
	    		 	 		switch(celda.getCellType()) {
		    		 	 		
		    		 	 		case Cell.CELL_TYPE_STRING:
		    		 	 		    System.out.println(celda.getStringCellValue());
		    		 	 		    if (celda.getStringCellValue().equals("fecha")){
		    		 	 		    	celda.setCellType(Cell.CELL_TYPE_STRING);
		    		 	 		    	celda.setCellValue(ox.getFechaOrden());
		    		 	 		    }
		    		 	 		    break;
		    		 	 		
	    		 	 		}
	    		 	    }
	    		 	}
	    		 		// cerramos el libro excel
	    		  	
	    		 	file.close();
	    		 		
	    	            FileOutputStream fileOut = new FileOutputStream("C:/Users/Lenovo/Desktop/OTs/nueva_orden.xls"); //Doy la ruta y el nombre del archivo nuevo que se generará
	    	            workbook.write(fileOut); //Escribo el nuevo archivo
	    	            fileOut.close(); //Cierro el archivo
	    		}
	}
