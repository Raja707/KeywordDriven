package executionEngine;

import java.io.File;
import java.io.FileInputStream;
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
	
	public static boolean bResult;
	public static String sData;
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
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
		catch (Exception e) {
			Log.error("Main method error "+e.getMessage());
			DriverScript.bResult = false;
		}		
	}
	
	
	public static void execute_TestCase() {
		int iTotalTestCases = ExcelUtils.getRowCount(Constants.sheet_TestCases);
		
		for(int iTestCase = 1; iTestCase < iTotalTestCases; iTestCase++) {
			bResult = true;
			
			sTestCaseID = ExcelUtils.getCellData(iTestCase, Constants.col_TCID, Constants.sheet_TestCases);
			sRunMode = ExcelUtils.getCellData(iTestCase, Constants.col_runmode, Constants.sheet_TestCases);
						
			if(sRunMode.equals("Yes")) {
				iTestStep = ExcelUtils.getRowContains(Constants.sheet_TestSteps, Constants.col_TCID, sTestCaseID);
				iTestLastStep = ExcelUtils.getTestStepsCount(Constants.sheet_TestSteps, sTestCaseID, iTestStep);				
				
				System.out.println(iTestStep);
				System.out.println(iTestLastStep);
				
				Log.startTestCase(sTestCaseID);
				bResult = true;
				
				for(; iTestStep < iTestLastStep; iTestStep++) {
					sActionKeyword = ExcelUtils.getCellData(iTestStep, Constants.col_actionkeyword, Constants.sheet_TestSteps);
					sPageObject = ExcelUtils.getCellData(iTestStep, Constants.col_pageObject, Constants.sheet_TestSteps);
					sData = ExcelUtils.getCellData(iTestStep, Constants.col_DataSet, Constants.sheet_TestSteps);
					
					executeActions();
					
					if(bResult == false) {
						ExcelUtils.setCellData(Constants.sheet_TestCases, iTestCase, Constants.col_TestCases_results, Constants.keyword_FAIL);
						Log.endTestCase(sTestCaseID);
						break;
					}
				}
				
				if(bResult == true) {
					ExcelUtils.setCellData(Constants.sheet_TestCases, iTestCase, Constants.col_TestCases_results, Constants.keyword_PASS);
					Log.endTestCase(sTestCaseID);
				}				
				
			}
		}
	}
	

	public static void executeActions() {
		try {
			for(int i=0; i<method.length; i++) {
				if(method[i].getName().equalsIgnoreCase(sActionKeyword)) {
					method[i].invoke(actionKeywords,sPageObject,sData);
					
					if(bResult == true) {
						ExcelUtils.setCellData(Constants.sheet_TestSteps, iTestStep, Constants.col_TestSteps_results, Constants.keyword_PASS);
						break;
					}
					else {
						ExcelUtils.setCellData(Constants.sheet_TestSteps, iTestStep, Constants.col_TestSteps_results, Constants.keyword_FAIL);
						ActionKeywords.closeBrowser("","");
						break;
					}
					
				}
			}
		} 
		catch (Exception e) {
			Log.error("Reflection class error "+e.getMessage());
			DriverScript.bResult = false;
		}
	}

}
