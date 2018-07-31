package executionEngine;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;
import org.apache.log4j.xml.DOMConfigurator;
import config.ActionKeywords;
import config.Constants;
import utility.ExcelUtils;
import utility.Log;

public class DriverScript {
	
	public static Method method[];
	public static ActionKeywords actionKeywords;
	public static String sActionKeyword;
	public static Properties OR;
	public static String sPageObject;
	
	public static int iTestStep;
	public static int iTestLastStep;
	public static String sTestCaseID;
	public static String sRunMode;
		
	public static void main(String[] args) throws InterruptedException, IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		// TODO Auto-generated method stub
		
		String sPath = Constants.excelPath;	
		ExcelUtils.setExcelFile(sPath);
		
		DOMConfigurator.configure("log4j.xml");	
		
		String sORPath = Constants.orPath;
		FileInputStream fis = new FileInputStream(new File(sORPath));
		OR = new Properties(System.getProperties());
		OR.load(fis);
		
		actionKeywords = new ActionKeywords();
		method = actionKeywords.getClass().getMethods();			
		
		execute_TestCase();
		
	}
	
	public static void execute_TestCase() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		int iTotalTestCases = ExcelUtils.getRowCount(Constants.sheet_TestCases);
		
		for(int iTotalTestCase = 1; iTotalTestCase < iTotalTestCases; iTotalTestCase++) {
			sTestCaseID = ExcelUtils.getCellData(iTotalTestCase, Constants.col_TCID, Constants.sheet_TestCases);
			sRunMode = ExcelUtils.getCellData(iTotalTestCase, Constants.col_runmode, Constants.sheet_TestCases);
						
			if(sRunMode.equals("Yes")) {
				iTestStep = ExcelUtils.getRowContains(Constants.sheet_TestSteps, Constants.col_TCID, sTestCaseID);
				iTestLastStep = ExcelUtils.getTestStepsCount(Constants.sheet_TestSteps, sTestCaseID, iTestStep);
				
				Log.startTestCase(sTestCaseID);
				
				for(; iTestStep < iTestLastStep; iTestStep++) {
					sActionKeyword = ExcelUtils.getCellData(iTestStep, Constants.col_actionkeyword, Constants.sheet_TestSteps);
					sPageObject = ExcelUtils.getCellData(iTestStep, Constants.col_pageObject, Constants.sheet_TestSteps);
					
					executeActions();
				}
				
				Log.endTestCase(sTestCaseID);
			}
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
