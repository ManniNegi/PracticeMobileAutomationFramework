package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadExcelData {
	public String email, password;
	
	public void ReadExcelData() {
		this.email = email;
		this.password = password;
	}

	public static void main(String args[]) throws EncryptedDocumentException, IOException {
	    String email = null, password =null;
		ReadExcelData rd = new ReadExcelData();
		rd.getData("/src/main/java/testData/SampleTestData.xlsx", "Staging", "professionalAdmin", email, password);
		System.out.println(email);
		System.out.println(password);

	}
	
    public String getData(String fileName, String excelSheetName, String accountType, String userName, String password) throws EncryptedDocumentException, IOException {
    	File f = new File(System.getProperty(fileName));
    	FileInputStream fis = new FileInputStream(f);
    	Workbook wb = WorkbookFactory.create(fis);
    	Sheet sheet =  wb.getSheet(excelSheetName);
    	
    	int totalRowNum = sheet.getLastRowNum();
    	System.out.print("Number of rows are" + totalRowNum);
    	Row rowCells = sheet.getRow(0);
    	int totalColNum = rowCells.getLastCellNum();
    	String testData[][] = new String [totalRowNum][totalColNum];
   
   
    	DataFormatter dataFormat = new DataFormatter();
    	for (int i=1; i<=totalRowNum; i++) {
    		Cell cell;
    		cell = sheet.getRow(i).getCell(0);
    		String CellData = null;
    		CellData = cell.getStringCellValue();
    		if (CellData == accountType) {
    			userName = sheet.getRow(i).getCell(1).getStringCellValue();
    			password = sheet.getRow(i).getCell(2).getStringCellValue();
    		}
    		
    	}
	//	return userName+"~"+password;
		return userName + password;
		
    	
    		
    	
    }
    
}

