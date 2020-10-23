package tests;

import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import utilities.*;
import functionalities.*;


public class courseSearch extends BaseUi{
	
	public courseDetailsFunctionality details;
	public filterFunctionality filter;
	public FormFillingFunctionality fill;
	
	public static WebDriver driver;
	
	@BeforeSuite
	public void openWebsite()
	{
		driver = getDriver("Chrome");
		openUrl();	
	}
	
	@BeforeTest
	public void initiateClasses()
	{
		details = new courseDetailsFunctionality();
		filter = new filterFunctionality();
		fill = new FormFillingFunctionality();
	}
	
	@Test (priority=1)
	public void searchCourse() 
	{
		
		details.search("Web Development");
		details.filter("English","Beginner");
		details.getCourseDetails(2);
	}
	
	@Test (priority=2)
	public void filterDetails()
	{
		
		openUrl();
		details.search("Language Learning");
		filter.setFilters();
	}
	
	@Test (priority = 3)
	public void fillFormIncorrectData()
	{
		refresh();	
		fill.getCourse();
		fill.navigateForm();
		fill.setFormValues(1);
	}
	
	@Test (priority = 4)
	public void fillFormCorrectData()
	{
		refresh();
		fill.setFormValues(2);
	}
	
	
	@AfterSuite
	public void exitBrowser()
	{
		quitBrowser();
	}
	
}
