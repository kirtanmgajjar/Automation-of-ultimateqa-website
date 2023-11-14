package utilities;

import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
	
	private InputStream inputStream;
	private XSSFWorkbook workBook;
	private XSSFSheet sheet;
	private int rowCount;
	private int columnCount;
	
	public ExcelUtility(InputStream fileStream) throws IOException
	{
		this.inputStream = fileStream;
		this.workBook = new XSSFWorkbook(inputStream);
	}
	
	public void openSheet(String sheetName)
	{
		this.sheet = workBook.getSheet(sheetName);
		this.rowCount = this.sheet.getLastRowNum();
		this.columnCount = this.sheet.getRow(0).getLastCellNum()-1;
	}
	
	public int getRowCount()
	{
		return this.rowCount;
	}
	public int getColumnCount()
	{
		return this.columnCount;
	}
	
	public String getCellData(int row, int column)
	{
		String data = this.sheet.getRow(row).getCell(column).getStringCellValue();
		return data;
		
	}
	
	public void closeFile() throws IOException
	{
		this.inputStream.close();
		this.workBook.close();
	}
}
