package Utilites;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.microsoft.schemas.office.visio.x2012.main.CellType;

// TODO: Auto-generated Javadoc
/**
 * Classe SaveExcel
 */
public class SaveExcel {
	
	/**
	 * Créer un ficher excel
	 *
	 * @param filename the filename
	 */
	public void createFile(String filename) 
	{
		
		try
		{
	        HSSFWorkbook workbook = new HSSFWorkbook();
	        HSSFSheet sheet = workbook.createSheet("Scrutin");  
	        
	        HSSFRow rowhead = sheet.createRow((short)0);
	        rowhead.createCell(0).setCellValue("No");
	        rowhead.createCell(1).setCellValue("Date d'enregistrement");
	        rowhead.createCell(2).setCellValue("Type de scrutin");
	        rowhead.createCell(3).setCellValue("Nom du Candidat");
	        rowhead.createCell(4).setCellValue("Nombre d'électeurs");
	        rowhead.createCell(5).setCellValue("Pourcentage d'électeurs");
	        rowhead.createCell(6).setCellValue("Issue");
	        
	        
	        
	        
	        FileOutputStream fileOut = new FileOutputStream(filename);
	        workbook.write(fileOut);
	        fileOut.close();
	        workbook.close();
	        System.out.println("Nouveau Fichier Excel généré!");
		}
		catch(Exception e)
		{
			System.out.println("Erreur création fichier!");
		}
	}
	
	/**
	 * Ajouter une ligne dans un fichier excel
	 *
	 * @param filename
	 * @param c1
	 * @param c2
	 * @param c3
	 * @param c4
	 * @param c5
	 * @param c6
	 */
	public void AddRowFile(String filename, String c1, String c2, String c3, String c4, String c5, String c6)
	{
		File f = new File(filename);
		if(!f.exists()) //si le fichier n'existe pas, on le créé
		{
			createFile(filename);
		}
		
		try
		{
			//HSSFWorkbook workbook = new HSSFWorkbook();
			Workbook workbook=WorkbookFactory.create(new FileInputStream(filename));
			//HSSFSheet sheet = workbook.getSheet("FirstSheet");  
			HSSFSheet sheet = (HSSFSheet) workbook.getSheet("Scrutin");
	        
	        HSSFRow rowhead = sheet.createRow(sheet.getLastRowNum() + 1);
	        rowhead.createCell(0).setCellValue(String.valueOf(sheet.getLastRowNum()));
	        rowhead.createCell(1).setCellValue(c1);
	        rowhead.createCell(2).setCellValue(c2);
	        rowhead.createCell(3).setCellValue(c3);
	        rowhead.createCell(4).setCellValue(c4);
	        rowhead.createCell(5).setCellValue(c5);
	        rowhead.createCell(6).setCellValue(c6);
	        
	        //Ajustement des colonnes en fonctiones des données
	        sheet.autoSizeColumn(0);
	        sheet.autoSizeColumn(1);
	        sheet.autoSizeColumn(2);
	        sheet.autoSizeColumn(3);
	        sheet.autoSizeColumn(4);
	        sheet.autoSizeColumn(5);
	        sheet.autoSizeColumn(6);
	        
	        
	        FileOutputStream fileOut = new FileOutputStream(filename);
	        workbook.write(fileOut);
	        fileOut.close();
	        workbook.close();
		}
		catch(Exception e)
		{
			System.out.println("Erreur ajout de ligne! Peut-être que le fichier est ouvert?");
		}
		
	}
}













































