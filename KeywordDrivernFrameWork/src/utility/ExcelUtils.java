package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	
	public static XSSFWorkbook wb;
	public static XSSFSheet sheet;
	public static XSSFCell cell;
	public static File src;
	
	public static void setExcelFile (String path, String sheetname) throws IOException {
		src = new File (path);
		FileInputStream fis = new FileInputStream(src);
		wb = new XSSFWorkbook(fis);
		sheet = wb.getSheet(sheetname);				
	}
	
	public static String getCellData (int rowNum, int colNum) {
		cell = sheet.getRow(rowNum).getCell(colNum);
		String cellData = cell.getStringCellValue();
		return cellData;
	}

}
