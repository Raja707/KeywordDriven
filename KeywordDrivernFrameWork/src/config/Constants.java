package config;

public class Constants {
	
	//URL and Login Cred
	public static final String url = "https://test.salesforce.com/";
	public static final String uname = "rajasingh.nadar@infosys.com.vmstdemo";
	public static final String upass = "Raja@1506$$$$$$";
	
	//Excel File Path and OR Path
	public static final String excelPath = "C:\\Users\\Balasingh Nadar\\git\\KeywordDriven\\KeywordDrivernFrameWork\\src\\dataEngine\\DataEngine.xlsx";
	public static final String excelName = "DataEngine.xlsx";
	public static final String orPath = "C:\\Users\\Balasingh Nadar\\git\\KeywordDriven\\KeywordDrivernFrameWork\\src\\config\\OR";
	
	//Excel Data Column Details
	public static final int col_TCID = 0;
	public static final int col_TSID = 1;
	public static final int col_pageObject = 3;
	public static final int col_actionkeyword = 4;
	
	public static final int col_runmode = 2;
	
	public static final int col_TestSteps_results = 6;
	public static final int col_TestCases_results = 3;
	public static final int col_DataSet = 5;
	
	//Excel Sheets in Workbook
	public static final String sheet_TestSteps = "Test Steps";
	public static final String sheet_TestCases = "Test Cases";
	
	//Pass and Fail
	public static final String keyword_PASS = "PASS";
	public static final String keyword_FAIL = "FAIL";
}
