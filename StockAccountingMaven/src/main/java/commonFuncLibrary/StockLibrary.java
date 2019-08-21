package commonFuncLibrary;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;


public class StockLibrary 
{
	
	WebDriver driver;
	String result;
	
	public String appLaunch(String url)
	{
		System.setProperty("webdriver.chrome.driver", "D://Preeti Selenium Live Project//StockAccounting//CommonJarFiles//chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(url);
		driver.manage().window().maximize();
		//validation
		if(driver.findElement(By.id("username")).isDisplayed())
		{
			result = "Navigated to right URL";
		}else
		{
			result = "Incorrect URL";
		}
		return result;
	}
	
	public String appLogin()
	{
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("admin");
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("master");
		driver.findElement(By.id("btnsubmit")).click();
		//validation
		if(driver.findElement(By.id("logout")).isDisplayed())
		{
			result = "Logged in successfully";
		}else
		{
			result = "Unsuccessfull Login";
		}
		return result;
	}
	
	public String supplierPage()
	{
		driver.findElement(By.linkText("Suppliers")).click();
		if(driver.getTitle().contains("Suppliers"))
		{
			result = "we are in supplier page";
		}else
		{
			result = "not in supplier page";
		}
		return result;
	}
	
	public String addSupplier(String suppliername, String address,String city, String country, String contact, String phone, String email, String mobile, String notes) throws Throwable
	{
		driver.findElement(By.xpath("//*[contains(@href,'a_suppliersadd.php?showdetail=')]")).click();
		String suppNumber = driver.findElement(By.xpath("//*[@id='x_Supplier_Number']")).getAttribute("value");
		driver.findElement(By.id("x_Supplier_Name")).clear();
		driver.findElement(By.id("x_Supplier_Name")).sendKeys(suppliername);
		driver.findElement(By.id("x_Address")).clear();
		driver.findElement(By.id("x_Address")).sendKeys(address);
		driver.findElement(By.id("x_City")).clear();
		driver.findElement(By.id("x_City")).sendKeys(city);
		driver.findElement(By.id("x_Country")).clear();
		driver.findElement(By.id("x_Country")).sendKeys(country);
		driver.findElement(By.id("x_Contact_Person")).clear();
		driver.findElement(By.id("x_Contact_Person")).sendKeys(contact);
		driver.findElement(By.id("x_Phone_Number")).clear();
		driver.findElement(By.id("x_Phone_Number")).sendKeys(phone);
		driver.findElement(By.id("x__Email")).clear();
		driver.findElement(By.id("x__Email")).sendKeys(email);
		driver.findElement(By.id("x_Mobile_Number")).clear();
		driver.findElement(By.id("x_Mobile_Number")).sendKeys(mobile);
		driver.findElement(By.id("x_Notes")).clear();
		driver.findElement(By.id("x_Notes")).sendKeys(notes);
		//when unable to see bottom of the page perform Actions class
		Actions action = new Actions(driver);
		action.sendKeys(Keys.PAGE_DOWN).build().perform();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//*[@id='btnAction']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//*[text()='OK!'])[1]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//*[text()='OK'])[6]")).click();
		
		//validation
		Thread.sleep(2000);
		
		if(driver.findElement(By.xpath("//*[@id='ewContentColumn']/div[2]/div[2]/div/button/span")).isDisplayed())
		{
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id='ewContentColumn']/div[2]/div[2]/div/button/span")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id='psearch']")).clear();
			driver.findElement(By.xpath("//*[@id='psearch']")).sendKeys(suppNumber);
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id='btnsubmit']")).click();
			Thread.sleep(2000);
		}
		else
		{
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id='psearch']")).clear();
			driver.findElement(By.xpath("//*[@id='psearch']")).sendKeys(suppNumber);
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id='btnsubmit']")).click();
			Thread.sleep(2000);
		}

		
		String actual = driver.findElement(By.xpath("//*[@id='el1_a_suppliers_Supplier_Number']/span")).getText();
		if(actual.equals(suppNumber))
		{
			result = "Pass";
		}else
		{
			result = "Fail";
		}
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='ewContentColumn']/div[2]/div[2]/div/button/span")).click();
		Thread.sleep(3000);
		return result;
	}
	
	public void catogorie() throws InterruptedException
	{
		Thread.sleep(2000);
		WebElement list = driver.findElement(By.id("mi_a_stock_items"));
		Actions act = new Actions(driver);
		act.moveToElement(list).build().perform();
		act.moveToElement(driver.findElement(By.xpath("//*[@id='mi_a_stock_categories']"))).click().build().perform();
		Thread.sleep(3000);
	}
	
	public String addCatogorie(String catName) throws InterruptedException
	{
		driver.findElement(By.xpath("//*[contains(@href,'a_stock_categoriesadd.php')]")).click();
		driver.findElement(By.xpath("//*[@id='x_Category_Name']")).sendKeys(catName);
		driver.findElement(By.id("btnAction")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[text()='OK!']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div[17]/div[2]/div/div[4]/div[2]/button")).click();
		
		
		if(driver.findElement(By.xpath("//*[@id='ewContentColumn']/div[2]/div[2]/div/button")).isDisplayed())
		{
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id='ewContentColumn']/div[2]/div[2]/div/button")).click();		
			Thread.sleep(2000);
			driver.findElement(By.id("psearch")).clear();
			driver.findElement(By.id("psearch")).sendKeys(catName);
			driver.findElement(By.xpath("//*[@id='btnsubmit']")).click();
		}
		
		else
		{
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id='ewContentColumn']/div[2]/div[2]/div/button")).click();		
			Thread.sleep(2000);
			driver.findElement(By.id("psearch")).clear();
			driver.findElement(By.id("psearch")).sendKeys(catName);
			driver.findElement(By.xpath("//*[@id='btnsubmit']")).click();
		}
			

		
		String actual = driver.findElement(By.xpath("//*[@id='el2_a_stock_categories_Category_Name']/span")).getText();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='ewContentColumn']/div[2]/div[2]/div/button")).click();
		if(actual.equals(catName))
		{
			result = "Pass";
		}else
		{
			result = "Fail";
		}
		return result;
	}
	
	public String appLogout()
	{
		driver.findElement(By.id("logout")).click();
		driver.findElement(By.xpath("//*[text()='OK!'])[1]")).click();
		//validation
		if(driver.findElement(By.id("username")).isDisplayed())
		{
			result = "Successfully Logged Out";
		}else
		{
			result = "Dint log out";
		}		
		return result;
	}
	
	public void appClose()
	{
		driver.close();
	}
	
	public static void main(String[] args) throws Throwable 
	{
		StockLibrary app = new StockLibrary();
		
		//Application launch
		String res = app.appLaunch("http://webapp.qedge.com/login.php");
		System.out.println(res);
		
		//Application login
		String logIn = app.appLogin();
		System.out.println(logIn);
		
		//click on supplier page
		String supp = app.supplierPage();
		System.out.println(supp);
		
		
		//add supplier
		String suppAdded = app.addSupplier("Test1", "32 canegrass drive", "melbourne", "australia", "preeti10", "0423248895", "preeti2181@gmail.com", "0423248895", "supplied");
		System.out.println(suppAdded);
		
		//move to category
		app.catogorie();
		
		
		//add category
		String catAdded = app.addCatogorie("Redmi 06");
		System.out.println(catAdded);
		//Logout
		//String logOut = app.appLogout();
		//System.out.println(logOut);
		
		//Close window
		//app.appClose();
		

	}

}
