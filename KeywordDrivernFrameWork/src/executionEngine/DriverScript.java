package executionEngine;

import java.io.IOException;

import config.ActionKeywords;
import utility.ExcelUtils;

public class DriverScript {

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		
		String sPath = "C:\\Users\\Balasingh Nadar\\git\\KeywordDriven\\KeywordDrivernFrameWork\\src\\dataEngine\\DataEngine.xlsx";	
		ExcelUtils.setExcelFile(sPath, "Test Steps");
		
		for (int iRow=1; iRow<=7; iRow++) {
			String sActionKeyword = ExcelUtils.getCellData(iRow, 3);
			
			if (sActionKeyword.equalsIgnoreCase("openBrowser")) {
				ActionKeywords.openBrowser();
			}
			else if (sActionKeyword.equalsIgnoreCase("navigateUrl")) {
				ActionKeywords.navigateUrl();
			}
			else if (sActionKeyword.equalsIgnoreCase("input_uname")) {
				ActionKeywords.input_uname();
			}
			else if (sActionKeyword.equalsIgnoreCase("input_upass")) {
				ActionKeywords.input_upass();
			}
			else if (sActionKeyword.equalsIgnoreCase("clickButton")) {
				ActionKeywords.clickButton();
			}
			else if (sActionKeyword.equalsIgnoreCase("waitSometime")) {
				ActionKeywords.waitSometime();
			}
			else if (sActionKeyword.equalsIgnoreCase("closeBrowser")) {
				ActionKeywords.closeBrowser();
			}
		}

	}

}
