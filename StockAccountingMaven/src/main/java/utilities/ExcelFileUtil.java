package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileUtil 
{
	Workbook wb;

	
	//it will load Excel sheet
	public  ExcelFileUtil(String path) throws Throwable
	{
		FileInputStream fis = new FileInputStream(path);
		wb = WorkbookFactory.create(fis);
	}
	
	//rowcount
	public int rowCount(String sheetname)
	{
		return wb.getSheet(sheetname).getLastRowNum();

	}
	
	//Collumn Count
	
	public int colCount(String sheetname, int row)
	{
		return wb.getSheet(sheetname).getRow(row).getLastCellNum();

	}
	
	//reading the data
	public String getData(String sheetname, int row, int column)
	{
		String data = "";
		
		if(wb.getSheet(sheetname).getRow(row).getCell(column).getCellType()==Cell.CELL_TYPE_NUMERIC)
		{
			int celldata = (int)(wb.getSheet(sheetname).getRow(row).getCell(column).getNumericCellValue());
			data = String.valueOf(celldata);
		}
		
		else
		{
			data = wb.getSheet(sheetname).getRow(row).getCell(column).getStringCellValue();
		}
		
		return data;

	}
	
	public void setData(String path, String sheetname, int row, int column, String status) throws Throwable
	{
		Sheet sh = wb.getSheet(sheetname);
		Row rownum = sh.getRow(row);
		Cell cell = rownum.createCell(column);
		cell.setCellValue(status);
		
		if(status.equalsIgnoreCase("pass"))
		{
			//create a cell style
			CellStyle style = wb.createCellStyle();
			//create font
			Font font = wb.createFont();
			//apply color to the text
			font.setColor(IndexedColors.GREEN.getIndex());
			//apply bold to the text
			font.setBold(true);
			//set font
			style.setFont(font);
			//set cell style
			rownum.getCell(column).setCellStyle(style);		
		}
		else if(status.equalsIgnoreCase("fail"))
		{
			//create a cell style
			CellStyle style = wb.createCellStyle();
			//create font
			Font font = wb.createFont();
			//apply color to the text
			font.setColor(IndexedColors.RED.getIndex());
			//apply bold to the text
			font.setBold(true);
			//set font
			style.setFont(font);
			//set cell style
			rownum.getCell(column).setCellStyle(style);	
		}
		else if(status.equalsIgnoreCase("not executed"))
		{
			//create a cell style
			CellStyle style = wb.createCellStyle();
			//create font
			Font font = wb.createFont();
			//apply color to the text
			font.setColor(IndexedColors.BLUE.getIndex());
			//apply bold to the text
			font.setBold(true);
			//set font
			style.setFont(font);
			//set cell style
			rownum.getCell(column).setCellStyle(style);	
		}
		
		FileOutputStream fos = new FileOutputStream(path);
		wb.write(fos);
		fos.close();
	}
	
	public static void main(String[] args) throws Throwable
	{
		ExcelFileUtil excl = new ExcelFileUtil("D:\\Preeti Selenium Live Project\\StockAccountingMaven\\TestInput\\TestConditions.xlsx");
		System.out.println(excl.rowCount("MasterTestCases"));
		System.out.println(excl.colCount("MasterTestCases", 0));
		System.out.println(excl.getData("MasterTestCases", 0, 1));
		
	}
	
}
