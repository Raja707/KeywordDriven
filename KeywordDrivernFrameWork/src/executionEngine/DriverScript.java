package executionEngine;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import config.ActionKeywords;
import utility.ExcelUtils;

public class DriverScript {
	
	public static Method method[];
	public static ActionKeywords actionKeywords;
	public static String sActionKeyword;
		
	public static void main(String[] args) throws InterruptedException, IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		// TODO Auto-generated method stub
		
		String sPath = "C:\\Users\\Balasingh Nadar\\git\\KeywordDriven\\KeywordDrivernFrameWork\\src\\dataEngine\\DataEngine.xlsx";	
		ExcelUtils.setExcelFile(sPath, "Test Steps");
		
		actionKeywords = new ActionKeywords();
		method = actionKeywords.getClass().getMethods();
		
		for(int iRow = 1; iRow <= 7; iRow++) {
			sActionKeyword = ExcelUtils.getCellData(iRow, 3);
			
			executeActions();
		}

	}

	public static void executeActions() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		for(int i=0; i<method.length; i++) {
			if(method[i].getName().equalsIgnoreCase(sActionKeyword)) {
				method[i].invoke(actionKeywords);
				break;
			}
		}
	}

}
