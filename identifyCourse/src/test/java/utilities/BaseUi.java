package utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseUi {

	public static Properties config=new Properties();
	public static WebDriver driver;
    
    /*
	 * Creating constructor to initialize properties file
     */	
    public BaseUi()
	{
		
    	FileReader reader = null;
    	String path=System.getProperty("user.dir")+"/Config.properties";

		try {
			reader = new FileReader(path);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			config.load(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    
    /* 
     * Method to open user choice of browser
     */
    public WebDriver getDriver(String browser)
    {
    	//System.out.println("Enter browser name from available options: \n1. Chrome\n2. Firefox\n3. Opera");
    	System.out.println("\n Browser selected: "+browser);
    	System.out.println("\nTo change browser selection go-through README File");
    	
		// If browser entered is chrome, open chrome browser
		if(browser.equalsIgnoreCase("chrome"))
    	{
    		 System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/drivers/chromedriver.exe");
    		 driver = new ChromeDriver();
    	}
		
		// If browser entered is firefox, open firefox browser
    	else if(browser.equalsIgnoreCase("firefox"))		
    	{
    		System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"/drivers/geckodriver.exe");
    		driver = new FirefoxDriver();
    	}
		//If browser entered is opera, open opera browser
    	else if(browser.equalsIgnoreCase("opera"))		
    	{
    		System.setProperty("webdriver.opera.driver", System.getProperty("user.dir")+"/drivers/operadriver.exe");
    		driver = new OperaDriver();
    	}
		
		
		
		// Maximize window
    	driver.manage().window().maximize();	
		return driver;
    }
	
    /*
     * Method to Open URL
	 */
    public void openUrl()
	{
		driver.get(config.getProperty("baseUrl"));
	}
    
	/*
	 * Method to wait till element is click-able
	 */
	public void waitElementClickable(String elementXpath)
	{
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(elementXpath)));
	}
	
	/*
	 * Take Screenshot
	 */
public String snap(String fileName) {
		
		// Creating a screenshot driver and storing in scrFile temporarily.
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		// Now you can do whatever you need to do with it, for example copy somewhere
		try {
			FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir") + "/Screenshots/"+fileName+".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		String destination = System.getProperty("user.dir") + "/Screenshots/"+fileName;
		return destination;
	}

	/*
	 * Page Load Timeout
	 */
	public void pageLoad(int time) {
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
	}
	
	/*
	 * Page Refresh
	 */
	public void refresh()
	{
		driver.navigate().refresh();
	}
		
	/*
	 * Closing browser
	 */
	public void quitBrowser() {
		driver.quit();
	}
	

}	
