package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileUtil 
{

	public static String getValueForKey(String Key) throws IOException
	{

		Properties configProperties = new Properties();
		
		FileInputStream fis = new FileInputStream("D:\\Preeti Selenium Live Project\\StockAccountingMaven\\PropertiesFile\\Environment.properties");
	
		configProperties.load(fis);
		
		return configProperties.getProperty(Key);
	}
	public static void main(String[] args) throws Throwable {
		String dd = getValueForKey("URL");
		System.out.println(dd);
	}

}
