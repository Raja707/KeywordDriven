package executionEngine;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

import config.ActionKeywords;
import config.Constants;
import utility.ExcelUtils;

public class DriverScript {
	
	public static Method method[];
	public static ActionKeywords actionKeywords;
	public static String sActionKeyword;
	public static Properties OR;
	public static String sPageObject;
		
	public static void main(String[] args) throws InterruptedException, IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		// TODO Auto-generated method stub
		
		String sPath = Constants.excelPath;	
		ExcelUtils.setExcelFile(sPath, Constants.sheet_TestSteps);
		
		String sORPath = Constants.orPath;
		FileInputStream fis = new FileInputStream(new File(sORPath));
		OR = new Properties(System.getProperties());
		OR.load(fis);
		
		actionKeywords = new ActionKeywords();
		method = actionKeywords.getClass().getMethods();
		
		for(int iRow = 1; iRow <= 7; iRow++) {
			sActionKeyword = ExcelUtils.getCellData(iRow, Constants.col_actionkeyword);
			sPageObject = ExcelUtils.getCellData(iRow, Constants.col_pageObject);
			
			executeActions();
		}

	}

	public static void executeActions() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		for(int i=0; i<method.length; i++) {
			if(method[i].getName().equalsIgnoreCase(sActionKeyword)) {
				method[i].invoke(actionKeywords,sPageObject);
				break;
			}
		}
	}

}
