package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class Input{
	
	public String ReadExcelData(int vRow, int vColumn)
	{
		String value=null;
		 //Get the excel file and create an input stream for excel
	     FileInputStream fis = null;
		try {
			fis = new FileInputStream(System.getProperty("user.dir")+"/Input/Input.xlsx");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 //load the input stream to a workbook object
		 //Use XSSF for (xlsx) excel file and HSSF for (.xls) excel file
		 XSSFWorkbook wb = null;
		try {
			wb = new XSSFWorkbook(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 //get the sheet from the workbook by index
		 XSSFSheet sheet=wb.getSheetAt(0);
		 Row row=sheet.getRow(vRow);
		 Cell cell=row.getCell(vColumn);
		 CellType type=cell.getCellType();
		 if(type==CellType.NUMERIC) {
			 value=String.valueOf(cell.getNumericCellValue());
		 }
		 else if(type==CellType.STRING) {
			 value=cell.getStringCellValue();
		 }	 
		 return value;
		
	}

}