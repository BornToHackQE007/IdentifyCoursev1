package utilities;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebElement;

public class SendToExcel extends BaseUi{

	// Creating WorkBook
	static XSSFWorkbook workbook = new XSSFWorkbook();

	/*
	 * Send Data to Excel
	 */
	public static void sendData(LinkedHashMap<String, String> filterMap, String sheetName, String colHeading1,
			String colHeading2) throws IOException {

		// Creating Sheet
		XSSFSheet sheet = workbook.createSheet(sheetName);

		// Create DATA
		Map<Integer, Object[]> dataset = new TreeMap<Integer, Object[]>();

		// Declare first row as headings
		dataset.put(1, new Object[] { colHeading1, colHeading2 });

		int i = 1;

		for (Entry<String, String> mapElement : filterMap.entrySet()) {

			// Get the row to store the product details
			XSSFRow row = sheet.getRow(i);

			// Storing the data received in the above acquired row
			dataset.put(i + 1, new Object[] { mapElement.getKey(), mapElement.getValue() });

			i++;

			// Iterating through data and creating cells with values (key)
			Set<Integer> set = dataset.keySet();
			int rownum = 0;
			for (Integer key : set) {
				row = sheet.createRow(rownum++);
				Object[] data = dataset.get(key);
				int cellnum = 0;

				// Set first 2 column widths
				sheet.setColumnWidth(0, 6000);
				sheet.setColumnWidth(1, 6000);
				for (Object value : data) {
					Cell cell = row.createCell(cellnum++);
					if (value instanceof Integer) {
						cell.setCellValue((Integer) value);
					} else if (value instanceof String) {
						cell.setCellValue((String) value);
					}
				}
			}
		}

		// Writing the data into the excel file
		FileOutputStream writefile;
		try {

			// Giving file path and name
			writefile = new FileOutputStream(System.getProperty("user.dir") + "/Output.xlsx");
			workbook.write(writefile);

			// If file created successfully, prompting the message in the console
			System.out.println(sheetName + " sheet created successfully\n\n");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void sendData(List<WebElement> courseName, List<WebElement> courseRating, String[] duration,
		
		String sheetName, String colHeading1, String colHeading2, String colHeading3) throws IOException {
		
		XSSFSheet sheet = workbook.createSheet(sheetName);

		// Create DATA
		Map<Integer, Object[]> dataset = new TreeMap<Integer, Object[]>();
		dataset.put(1, new Object[] { colHeading1, colHeading2, colHeading3 });
		int i = 1;
		for (int j = 0; j < 2; j++) {
			// Get the row to store the product details
			XSSFRow row = sheet.getRow(i);

			// Storing the data received in the above accquired row
			dataset.put(i + 1,new Object[] { courseName.get(j).getText(), courseRating.get(j).getText(), duration[j] });
			// Iterating through data and creating cells with values (key)
			i++;
			Set<Integer> set = dataset.keySet();
			int rownum = 0;
			for (Integer key : set) {
				row = sheet.createRow(rownum++);
				Object[] data = dataset.get(key);
				int cellnum = 0;

				// Set column 0th width
				sheet.setColumnWidth(0, 18000);
				sheet.setColumnWidth(1, 6000);
				sheet.setColumnWidth(2, 6000);
				for (Object value : data) {
					Cell cell = row.createCell(cellnum++);
					if (value instanceof Integer) {
						cell.setCellValue((Integer) value);
					} else if (value instanceof String) {
						cell.setCellValue((String) value);
					}
				}
			}

		}

		// Writing the data into the excel file
		FileOutputStream writefile;
		try {

			// Giving file path and name
			writefile = new FileOutputStream(System.getProperty("user.dir") + "/Output.xlsx");
			workbook.write(writefile);

			// If file created successfully, prompting the message in the console
			System.out.println("File created successfully");
			System.out.println("************************************************************");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
