package driverFactory;



import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import commonFuncLibrary.FunctionLibrary;
import utilities.ExcelFileUtil;

public class DriverScript 
{
	static WebDriver driver;
	ExtentReports report;
	ExtentTest logger;
	
	@Test
	//public static void main(String[] args) throws Throwable {
	
	public void startTest() throws Throwable
	{
		ExcelFileUtil excl = new ExcelFileUtil("D:\\Preeti Selenium Live Project\\StockAccountingMaven\\TestInput\\TestConditions.xlsx");
		
		for(int i=1; i<=excl.rowCount("MasterTestCases"); i++)
		{
			String ModuleStatus = "";

				if(excl.getData("MasterTestCases", i, 2).equalsIgnoreCase("Y"))
				{
					//Data for Module
					String TCModule = excl.getData("MasterTestCases", i, 1);
					report = new ExtentReports("D:\\Preeti Selenium Live Project\\StockAccountingMaven\\Reports\\"+TCModule+FunctionLibrary.generateDate()+".html");
					logger = report.startTest(TCModule);
					for(int j=1; j<=excl.rowCount(TCModule); j++)
					{
						String Description = excl.getData(TCModule, j, 0);
						String Object_Type = excl.getData(TCModule, j, 1);
						String Locator_Type = excl.getData(TCModule, j, 2);
						String Locator_Value = excl.getData(TCModule, j, 3);
						String Test_Data = excl.getData(TCModule, j, 4);
					
						try
						{
							if(Object_Type.equalsIgnoreCase("startBrowser"))
							{
								driver = commonFuncLibrary.FunctionLibrary.startBrowser(driver);
							}
							if(Object_Type.equalsIgnoreCase("openApplication"))
							{
								commonFuncLibrary.FunctionLibrary.openApplication(driver);
							}
							if(Object_Type.equalsIgnoreCase("typeAction"))
							{
								commonFuncLibrary.FunctionLibrary.typeAction(driver, Locator_Type, Locator_Value, Test_Data);
							}
							if(Object_Type.equalsIgnoreCase("clickAction"))
							{
								commonFuncLibrary.FunctionLibrary.clickAction(driver, Locator_Type, Locator_Value);
							}
							if(Object_Type.equalsIgnoreCase("closeBrowser"))
							{
								commonFuncLibrary.FunctionLibrary.closeBrowser(driver);
							}
							if(Object_Type.equalsIgnoreCase("waitForElement"))
							{
								commonFuncLibrary.FunctionLibrary.waitForTime(driver, Locator_Type, Locator_Value, Test_Data);
							}
							if(Object_Type.equalsIgnoreCase("waitForClickable"))
							{
								commonFuncLibrary.FunctionLibrary.waitForClickable(driver, Locator_Type, Locator_Value, Test_Data);
							}
							if(Object_Type.equalsIgnoreCase("clearValue"))
							{
								commonFuncLibrary.FunctionLibrary.clearValue(driver, Locator_Type, Locator_Value);
							}
							if(Object_Type.equalsIgnoreCase("mouseAction"))
							{
								commonFuncLibrary.FunctionLibrary.mouseAction(driver);
							}
							if(Object_Type.equalsIgnoreCase("mouseActionMoveTo"))
							{
								commonFuncLibrary.FunctionLibrary.mouseActionMoveTo(driver, Locator_Type, Locator_Value);
							}
							if(Object_Type.equalsIgnoreCase("captureData"))
							{
								commonFuncLibrary.FunctionLibrary.captureData(driver, Locator_Type, Locator_Value);
							}
							if(Object_Type.equalsIgnoreCase("tableValidation"))
							{
								commonFuncLibrary.FunctionLibrary.tableValidation(driver, Test_Data);
							}
							if(Object_Type.endsWith("getCatName"))
							{
								commonFuncLibrary.FunctionLibrary.getCatName(driver, Locator_Type, Locator_Value);
							}
							if(Object_Type.endsWith("catNameValidation"))
							{
								commonFuncLibrary.FunctionLibrary.catNameValidation(driver, Test_Data);
							}
							
											
							excl.setData("D:\\Preeti Selenium Live Project\\StockAccountingMaven\\TestOutput\\TestResults.xlsx", TCModule, j, 5, "PASS");
							
							ModuleStatus = "true";
							
						}catch(Exception e)
						{	
							File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
							FileUtils.copyFile(srcFile, new File("D:\\Preeti Selenium Live Project\\StockAccountingMaven\\ScreenShots\\"+Description+commonFuncLibrary.FunctionLibrary.generateDate()+".png"));
							
							excl.setData("D:\\Preeti Selenium Live Project\\StockAccountingMaven\\TestOutput\\TestResults.xlsx", TCModule, j, 5, "FAIL");
								
							ModuleStatus = "false";
								
							break;			
							
						}
					}
						
					if(ModuleStatus.equalsIgnoreCase("true"))
					{
						excl.setData("D:\\Preeti Selenium Live Project\\StockAccountingMaven\\TestOutput\\TestResults.xlsx", "MasterTestCases", i, 3, "PASS");
					}
					else if(ModuleStatus.equalsIgnoreCase("false"))
					{
						excl.setData("D:\\Preeti Selenium Live Project\\StockAccountingMaven\\TestOutput\\TestResults.xlsx", "MasterTestCases", i, 3, "FAIL");

					}
					
				}
					else
				{
					excl.setData("D:\\Preeti Selenium Live Project\\StockAccountingMaven\\TestOutput\\TestResults.xlsx", "MasterTestCases", i, 3, "NOT EXECUTED");
				}
				
					
			}
		}
}

