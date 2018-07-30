package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import config.Constants;

public class ExcelUtils {
	
	public static XSSFWorkbook wb;
	public static XSSFSheet sheet;
	public static XSSFCell cell;
	public static File src;
	
	public static void setExcelFile (String path) throws IOException {
		src = new File (path);
		FileInputStream fis = new FileInputStream(src);
		wb = new XSSFWorkbook(fis);
		//sheet = wb.getSheet(sheetname);				
	}
	
	public static String getCellData (int rowNum, int colNum, String sheetName) {
		sheet = wb.getSheet(sheetName);
		cell = sheet.getRow(rowNum).getCell(colNum);
		String cellData = cell.getStringCellValue();
		return cellData;
	}
	
	public static int getRowCount (String sheetName) {
		sheet = wb.getSheet(sheetName);
		int rowCount = sheet.getLastRowNum()+1;
		return rowCount;
	}

	public static int getRowContains (String sheetName, int colNum, String sTestCaseName ) {
		int i;
		for (i=0; i<ExcelUtils.getRowCount(sheetName);i++) {
			if(ExcelUtils.getCellData(i, colNum, sheetName).equalsIgnoreCase(sTestCaseName)) {
				break;
			}
				
		}
		return i;
	}
	
	public static int getTestStepsCount (String sheetName, String sTestCaseID, int iTestCaseStart ) {
		for(int i = iTestCaseStart; i<ExcelUtils.getRowCount(sheetName); i++) {
			
			if(!sTestCaseID.equals(ExcelUtils.getCellData(i, Constants.col_TCID, sheetName))) {
				int number = i;				
				return number;
				
			}
		}
		
		sheet = wb.getSheet(sheetName);
		int number = sheet.getLastRowNum()+1;
		return number;
		
	}
	
}
