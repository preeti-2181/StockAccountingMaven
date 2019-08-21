package commonFuncLibrary;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;
import utilities.PropertyFileUtil;

public class FunctionLibrary 
{
	static WebDriver driver;
	
	//Start Browser
	public static WebDriver startBrowser(WebDriver driver) throws IOException
	{
		if(PropertyFileUtil.getValueForKey("Browser").equalsIgnoreCase("FireFox"))
		{
			driver = new FirefoxDriver();
		}
		
		else if(PropertyFileUtil.getValueForKey("Browser").equalsIgnoreCase("Chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "D:\\Preeti Selenium Live Project\\StockAccountingMaven\\ExecutableFiles\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		else if(PropertyFileUtil.getValueForKey("Browser").equalsIgnoreCase("IE"))
		{
			System.setProperty("webdriver.ie.driver", "D:\\Preeti Selenium Live Project\\StockAccountingMaven\\ExecutableFiles\\IEDriverServer.exe");
		}
		return driver;
	}
	
	//Open Application
	
	public static void openApplication(WebDriver driver) throws IOException
	{
		driver.get(PropertyFileUtil.getValueForKey("URL"));
		driver.manage().window().maximize();
	}
	
	//Click Action
	public static void clickAction(WebDriver driver, String LocatorType, String LocatorValue)
	{
		if(LocatorType.equalsIgnoreCase("id"))
		{
			driver.findElement(By.id(LocatorValue)).click();
		}
		else if(LocatorType.equalsIgnoreCase("name"))
		{
			driver.findElement(By.name(LocatorValue)).click();
		}
		else if(LocatorType.equalsIgnoreCase("xpath"))
		{
			driver.findElement(By.xpath(LocatorValue)).click();
		}
		else if(LocatorType.equalsIgnoreCase("linkText"))
		{
			driver.findElement(By.linkText(LocatorValue)).click();
		}
	}
	
	//Type Action
	public static void typeAction(WebDriver driver, String LocatorType, String LocatorValue, String data)
	{
		if(LocatorType.equalsIgnoreCase("id"))
		{
			driver.findElement(By.id(LocatorValue)).clear();
			driver.findElement(By.id(LocatorValue)).sendKeys(data);
		}
		else if(LocatorType.equalsIgnoreCase("name"))
		{
			driver.findElement(By.name(LocatorValue)).clear();
			driver.findElement(By.name(LocatorValue)).sendKeys(data);
			
		}
		else if(LocatorType.equalsIgnoreCase("xpath"))
		{
			driver.findElement(By.xpath(LocatorValue)).clear();
			driver.findElement(By.xpath(LocatorValue)).sendKeys(data);
			
		}
	}
	
	//close application
	public static void closeBrowser(WebDriver driver)
	{
		driver.close();
	}
	
	//Wait for time
	public static void waitForTime(WebDriver driver, String LocatorType, String LocatorValue, String waitTime)
	{
		WebDriverWait myWait = new WebDriverWait(driver,Integer.parseInt(waitTime));
		if(LocatorType.equalsIgnoreCase("id"))
		{
			myWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id(LocatorValue)));
		}
		else if(LocatorType.equalsIgnoreCase("name"))
		{
			myWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.name(LocatorValue)));
		}
		else if(LocatorType.equalsIgnoreCase("xpath"))
		{
			myWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(LocatorValue)));
		}
	}
	
	//wait for click able element
	public static void waitForClickable(WebDriver driver, String LocatorType, String LocatorValue, String waitTime)
	{
		WebDriverWait myWait = new WebDriverWait(driver,Integer.parseInt(waitTime));
		if(LocatorType.equalsIgnoreCase("id"))
		{
			myWait.until(ExpectedConditions.elementToBeClickable(By.id(LocatorValue)));
		}
		else if(LocatorType.equalsIgnoreCase("name"))
		{
			myWait.until(ExpectedConditions.elementToBeClickable(By.name(LocatorValue)));
		}
		else if(LocatorType.equalsIgnoreCase("xpath"))
		{
			myWait.until(ExpectedConditions.elementToBeClickable(By.xpath(LocatorValue)));
		}
	}
	
	public static void clearValue(WebDriver driver, String LocatorType, String LocatorValue)
	{
		if(LocatorType.equalsIgnoreCase("id"))
		{
			driver.findElement(By.id(LocatorValue)).clear();
		}
		else if(LocatorType.equalsIgnoreCase("name"))
		{
			driver.findElement(By.name(LocatorValue)).clear();
		}
		else if(LocatorType.equalsIgnoreCase("xpath"))
		{
			driver.findElement(By.xpath(LocatorValue)).clear();
		}
	}
	
	public static void mouseAction(WebDriver driver)
	{
		Actions action = new Actions(driver);
		action.sendKeys(Keys.PAGE_DOWN).build().perform();
	}
	
	public static void mouseActionMoveTo(WebDriver driver, String LocatorType, String LocatorValue)
	{
		if(LocatorType.equalsIgnoreCase("id"))
		{
			Actions act = new Actions(driver);
			act.moveToElement(driver.findElement(By.id(LocatorValue))).build().perform();
			//act.moveToElement(driver.findElement(By.id(LocatorValue))).click().build().perform();
		}
		else if(LocatorType.equalsIgnoreCase("name"))
		{
			Actions act = new Actions(driver);
			act.moveToElement(driver.findElement(By.name(LocatorValue))).build().perform();
			//act.moveToElement(driver.findElement(By.name(LocatorValue))).click().build().perform();
		}
		else if(LocatorType.equalsIgnoreCase("xpath"))
		{
			Actions act = new Actions(driver);
			act.moveToElement(driver.findElement(By.xpath(LocatorValue))).build().perform();
			//act.moveToElement(driver.findElement(By.xpath(LocatorValue))).click().build().perform();
		}

	}
	
	//Method for capturing data
	public static void captureData(WebDriver driver, String LocatorType, String LocatorValue) throws IOException
	{
		String Data = "";
		
		if(LocatorType.equalsIgnoreCase("id"))
		{
			Data = driver.findElement(By.id(LocatorValue)).getAttribute("value");
		}
		else if(LocatorType.equalsIgnoreCase("name"))
		{
			Data = driver.findElement(By.name(LocatorValue)).getAttribute("value");
		}
		else if(LocatorType.equalsIgnoreCase("xpath"))
		{
			Data = driver.findElement(By.xpath(LocatorValue)).getAttribute("value");
		}
		
		FileWriter fw = new FileWriter("D:\\Preeti Selenium Live Project\\StockAccountingMaven\\CaptureData\\Data.txt");
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(Data);
		bw.flush();
		bw.close();
	}
	
	public static void getCatName(WebDriver driver, String LocatorType, String LocatorValue) throws IOException
	{
		String catName ="";
		
		if(LocatorType.equalsIgnoreCase("id"))
		{
			catName = driver.findElement(By.id(LocatorValue)).getAttribute("value");
		}
		else if(LocatorType.equalsIgnoreCase("name"))
		{
			catName = driver.findElement(By.name(LocatorValue)).getAttribute("value");
		}
		else if(LocatorType.equalsIgnoreCase("xpath"))
		{
			catName = driver.findElement(By.xpath(LocatorValue)).getAttribute("value");
		}
				
		FileWriter fw = new FileWriter("D:\\Preeti Selenium Live Project\\StockAccountingMaven\\CaptureData\\catName.txt");
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(catName);
		bw.flush();
		bw.close();
	}
	
	public static void tableValidation(WebDriver driver, String colNum) throws IOException, InterruptedException
	{
		FileReader fr = new FileReader("D:\\Preeti Selenium Live Project\\StockAccountingMaven\\CaptureData\\Data.txt");
		BufferedReader br = new BufferedReader(fr);
		String exp_data = br.readLine();
		
		int colNum1 = Integer.parseInt(colNum);
		
		if(driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("Search.panel"))).isDisplayed())
		{
			Thread.sleep(2000);
			driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("Search.panel"))).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("Search.box"))).clear();
			driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("Search.box"))).sendKeys(exp_data);
			Thread.sleep(2000);
			driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("Search.button"))).click();
			Thread.sleep(2000);
		}
		else
		{
			Thread.sleep(2000);
			driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("Search.box"))).clear();
			driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("Search.box"))).sendKeys(exp_data);
			Thread.sleep(2000);
			driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("Search.button"))).click();
			Thread.sleep(2000);
		}
		
		WebElement webtable = driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("webtable")));
		List<WebElement> rows = webtable.findElements(By.tagName("tr"));
		
		for(int i = 1; i<=rows.size(); i++)
		{
			String act_data = driver.findElement(By.xpath("//*@id='ewContentColumn']/div[3]/form/div//table[@id='tbl_a_supplierslist']/tbody/tr["+i+"]/td["+colNum1+"]/div/span/span")).getText();
			
			System.out.println(act_data);
			Assert.assertEquals(exp_data, act_data);
			break;
		}

	}
	
	public static void catNameValidation(WebDriver driver, String colNum) throws IOException, InterruptedException
	{
		FileReader fr = new FileReader("D:\\Preeti Selenium Live Project\\StockAccountingMaven\\CaptureData\\catName.txt");
		BufferedReader br = new BufferedReader(fr);
		String exp_data = br.readLine();
		
		int colNum1 = Integer.parseInt(colNum);
		

		if(driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("Search.panel.catname"))).isDisplayed())
		{
			Thread.sleep(2000);
			driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("Search.panel.catname"))).click();		
			Thread.sleep(2000);
			driver.findElement(By.id("psearch")).clear();
			driver.findElement(By.id("psearch")).sendKeys(exp_data);
			driver.findElement(By.xpath("//*[@id='btnsubmit']")).click();
		}
		
		else
		{
			Thread.sleep(2000);
			driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("Search.panel.catname"))).click();		
			driver.findElement(By.id("psearch")).clear();
			driver.findElement(By.id("psearch")).sendKeys(exp_data);
			driver.findElement(By.xpath("//*[@id='btnsubmit']")).click();
		}
		
		WebElement webtable = driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("catwebtable")));
		List<WebElement> rows = webtable.findElements(By.tagName("tr"));
		
		for(int i = 1; i<=rows.size(); i++)
		{
			String act_data = driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[3]/div[1]/div[1]/div[3]/form[1]/div[1]/div[2]/table[1]/tbody[1]/tr["+i+"]/td["+colNum1+"]/div[1]/span[1]/span[1]")).getText();
			System.out.println(act_data);
			Assert.assertEquals(exp_data, act_data);
			break;
		}
	}
	
	public static String generateDate()
	{
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY_MM_DD_SS");
		return sdf.format(date);
	}
	public static void main(String[] args) throws Throwable {
		driver = startBrowser(driver);
		openApplication(driver);
	}

}
						


