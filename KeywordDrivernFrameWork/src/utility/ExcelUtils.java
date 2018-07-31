package utility;

import java.io.File;
import java.io.FileInputStream;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import config.Constants;
import executionEngine.DriverScript;

public class ExcelUtils {
	
	public static XSSFWorkbook wb;
	public static XSSFSheet sheet;
	public static XSSFCell cell;
	public static File src;
	
	public static void setExcelFile (String path) {
		try {
			src = new File (path);
			FileInputStream fis = new FileInputStream(src);
			wb = new XSSFWorkbook(fis);
		} 
		catch (Exception e) {
			Log.error("Not able to set Excel File "+e.getMessage());
			DriverScript.bResult = false;
		}			
	}
	
	public static String getCellData (int rowNum, int colNum, String sheetName) {
		try {
			sheet = wb.getSheet(sheetName);
			cell = sheet.getRow(rowNum).getCell(colNum);
			String cellData = cell.getStringCellValue();
			return cellData;
		} 
		catch (Exception e) {
			Log.error("Not able to get the cell data "+e.getMessage());
			DriverScript.bResult = false;
			return "";
		}
	}
	
	public static int getRowCount (String sheetName) {
		try {
			sheet = wb.getSheet(sheetName);
			int rowCount = sheet.getLastRowNum()+1;
			return rowCount;
		} 
		catch (Exception e) {
			Log.error("Not able to get row count "+e.getMessage());
			DriverScript.bResult = false;
			return 0;
		}
	}

	public static int getRowContains (String sheetName, int colNum, String sTestCaseName ) {
		try {
			int i;
			for (i=0; i<ExcelUtils.getRowCount(sheetName);i++) {
				if(ExcelUtils.getCellData(i, colNum, sheetName).equalsIgnoreCase(sTestCaseName)) {
					break;
				}				
			}
			return i;
		} 
		catch (Exception e) {
			Log.error("Not able to get the row contains "+e.getMessage());
			DriverScript.bResult = false;
			return 0;
		}
	}
	
	public static int getTestStepsCount (String sheetName, String sTestCaseID, int iTestCaseStart ) {
		try {
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
		catch (Exception e) {
			Log.error("Not able to get the test steps count "+e.getMessage());
			DriverScript.bResult = false;
			return 0;
		}
		
	}
	
}
