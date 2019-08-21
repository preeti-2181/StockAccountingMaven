package commonFuncLibrary;

import utilities.ExcelFileUtil;

public class DataDrivenTesting 
{

	public static void main(String[] args) throws Throwable
	{
		ExcelFileUtil excl = new ExcelFileUtil("D://Preeti Selenium Live Project/StockAccounting/TestInput/InputSheet.xlsx");
		StockLibrary app = new StockLibrary();
		app.appLaunch("http://webapp.qedge.com/login.php");
		app.appLogin();
		app.supplierPage();
		
		for(int i=1; i<=excl.rowCount("SupplierData"); i++)
		{
			String suppliername = excl.getData("SupplierData", i, 0);
			String address = excl.getData("SupplierData", i, 1);
			String city = excl.getData("SupplierData", i, 2);
			String country = excl.getData("SupplierData", i, 3);
			String contact = excl.getData("SupplierData", i, 4);
			String phone = excl.getData("SupplierData", i, 5);
			String email = excl.getData("SupplierData", i, 6);
			String mobile = excl.getData("SupplierData", i, 7);
			String notes = excl.getData("SupplierData", i, 8);
			String result = app.addSupplier(suppliername, address, city, country, contact, phone, email, mobile, notes);
			excl.setData("D:\\Preeti Selenium Live Project\\StockAccountingMaven\\TestOutput\\OutputSheet.xlsx", "SupplierData", i, 9, result);

		}
		
		app.catogorie();
		ExcelFileUtil excl1 = new ExcelFileUtil("D:\\Preeti Selenium Live Project\\StockAccountingMaven\\TestInput\\Categorie Input.xlsx");
		
		for(int i=1; i<=excl1.rowCount("Category Name"); i++)
		{
			String catName = excl1.getData("Category Name", i, 0);	
			String result = app.addCatogorie(catName);
		}
		
		app.appLogout();
		app.appClose();

	}

}
