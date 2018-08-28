package executionEngine;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.Properties;
import org.apache.log4j.xml.DOMConfigurator;
import org.testng.annotations.Test;
import config.ActionKeywords;
import config.Constants;
import utility.ExcelUtils;
import utility.Log;

public class DriverScript {
	
	//Method - Reflection class
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
	
	@Test
	public void main() {
		// TODO Auto-generated method stub
		
		try {
			//Starting the excel with providing the Excel Path
			String sPath = Constants.excelPath;	
			ExcelUtils.setExcelFile(sPath);
			
			//Configuring the log4j.xml file
			DOMConfigurator.configure("log4j.xml");	
			
			//loading the OR file
			String sORPath = Constants.orPath;
			FileInputStream fis = new FileInputStream(new File(sORPath));
			OR = new Properties(System.getProperties());
			OR.load(fis);
			
			//Initiating the ActionKeywords class with reference actionKeywords and creating a new class at running with the methods of that class
			actionKeywords = new ActionKeywords();
			method = actionKeywords.getClass().getMethods();			
			
			//Executing the TestCase Functions
			execute_TestCase();
		} 
		catch (Exception e) {
			Log.error("Main method error "+e.getMessage());
			DriverScript.bResult = false;
		}		
	}
	
	
	public static void execute_TestCase() {
		//Total Test Case in the TestCase Sheet
		int iTotalTestCases = ExcelUtils.getRowCount(Constants.sheet_TestCases);
		
		//To get all the rows from the TestCase Sheet
		for(int iTestCase = 1; iTestCase < iTotalTestCases; iTestCase++) {
			bResult = true;
			
			//Assigning the TestCaseID and runmode to an object and referring which test cases to run based on runmode
			sTestCaseID = ExcelUtils.getCellData(iTestCase, Constants.col_TCID, Constants.sheet_TestCases);
			sRunMode = ExcelUtils.getCellData(iTestCase, Constants.col_runmode, Constants.sheet_TestCases);
			
			//To check the runmode value from TestCase sheet for executing
			if(sRunMode.equals("Yes")) {
				
				//To get the start row of the TestCaseID that you are going to run
				iTestStep = ExcelUtils.getRowContains(Constants.sheet_TestSteps, Constants.col_TCID, sTestCaseID);
				//To get the last row of the TestCaseID that you are running
				iTestLastStep = ExcelUtils.getTestStepsCount(Constants.sheet_TestSteps, sTestCaseID, iTestStep);				
				
				System.out.println(iTestStep);
				System.out.println(iTestLastStep);
				
				Log.startTestCase(sTestCaseID);
				bResult = true;
				
				//To get all the rows from the TestSteps Sheet
				for(; iTestStep < iTestLastStep; iTestStep++) {
					
					//To get value for actionKeyword, pageObject, dataset from TestSteps Sheet
					sActionKeyword = ExcelUtils.getCellData(iTestStep, Constants.col_actionkeyword, Constants.sheet_TestSteps);
					sPageObject = ExcelUtils.getCellData(iTestStep, Constants.col_pageObject, Constants.sheet_TestSteps);
					sData = ExcelUtils.getCellData(iTestStep, Constants.col_DataSet, Constants.sheet_TestSteps);
					
					//To execute ExecuteActions function which has the logic to execute the actionKeywords functions
					executeActions();
					
					//After executing each row from TestSteps it will check for bResult. If false it will assign the Fail value to TestCase
					if(bResult == false) {
						//Setting the result as Fail for TestCase Sheet
						ExcelUtils.setCellData(Constants.sheet_TestCases, iTestCase, Constants.col_TestCases_results, Constants.keyword_FAIL);
						Log.endTestCase(sTestCaseID);
						break;
					}
				}
				
				//After all the rows of the TestSteps are executed it will check for bResult.If true it will assign the Pass to the TestCase
				if(bResult == true) {
					//setting the result as Pass for TestCase Sheet
					ExcelUtils.setCellData(Constants.sheet_TestCases, iTestCase, Constants.col_TestCases_results, Constants.keyword_PASS);
					Log.endTestCase(sTestCaseID);
				}				
				
			}
		}
	}
	

	public static void executeActions() {
		try {
			//It will check methods in the reflection class and search for the right method
			for(int i=0; i<method.length; i++) {
				if(method[i].getName().equalsIgnoreCase(sActionKeyword)) {
					method[i].invoke(actionKeywords,sPageObject,sData);
					
					if(bResult == true) {
						//If the method is executed then it will set the cell as Pass for TestStep Sheet
						ExcelUtils.setCellData(Constants.sheet_TestSteps, iTestStep, Constants.col_TestSteps_results, Constants.keyword_PASS);
						break;
					}
					else {
						//If the method is not executed then it will set the cell as Fail for TestStep Sheet
						ExcelUtils.setCellData(Constants.sheet_TestSteps, iTestStep, Constants.col_TestSteps_results, Constants.keyword_FAIL);
						//If it fails in any step then the browser close function is invoked
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
