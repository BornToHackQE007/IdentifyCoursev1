package functionalities;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import utilities.*;

public class filterFunctionality extends BaseUi{
			
		// Method to apply filters (Language and Level filters)
	    public void setFilters()
		{
			// Wait till element gets click-able
	    	waitElementClickable(config.getProperty("languageDrop"));
	    	
	    	// Find and Click Drop Down language filter
			driver.findElement(By.xpath(config.getProperty("languageDrop"))).click();
			snap("Language DropDown");

			// Find SHOW ALL element and click it
			waitElementClickable(config.getProperty("showAll"));
			driver.findElement(By.xpath(config.getProperty("showAll"))).click();
			
			// Take Screenshot
			snap("All Languages");
			
			// Create lists of filter names and number of courses in it
			List<WebElement> languages = driver.findElements(By.xpath(config.getProperty("languageList")));
			List<WebElement> langCounts = driver.findElements(By.xpath(config.getProperty("languageListCount")));
			
			LinkedHashMap<String , String > languageMap = new LinkedHashMap<String, String>();
			System.out.println("Languages and their course count");
			
			// Convert list to map, to store in excel file
			languageMap = converToMap.convert(languages, langCounts);
			try {
				
				// Send all the data to excel
				//Parameters: Data_To_Be_Sent_From, SheetName, Column1_Heading, Column2_Heading
				SendToExcel.sendData(languageMap,"Language","LANGUAGE NAME","INDIVIDUAL COURSE COUNT");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
			//Find and Click Drop Down level filter
			driver.findElement(By.xpath(config.getProperty("languageClose"))).click();
			driver.findElement(By.xpath(config.getProperty("show"))).click();
			snap("Level DropDown");

			List<WebElement> levels=driver.findElements(By.xpath(config.getProperty("levelList")));
			List<WebElement> levelsCount=driver.findElements(By.xpath(config.getProperty("levelListCount")));
			
			LinkedHashMap<String , String > lMap = new LinkedHashMap<String, String>();
			System.out.println("Levels and their course count");
			lMap = converToMap.convert(levels, levelsCount);
			try {
				SendToExcel.sendData(lMap,"Level","LEVEL NAME","INDIVIDUAL LEVEL COURSES");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
}
