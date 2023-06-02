package testBase;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
//import java.util.ResourceBundle;


import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

//import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	public static WebDriver driver;
		
	public Logger logger; //for logging	
	
	
	//public ResourceBundle rb;
	
	@BeforeClass(groups= {"Master","Sanity","Regression"})
	@Parameters("browser")
	public void setup(String br)
	{
		//rb=ResourceBundle.getBundle("config"); //Load config.properties file
		
		logger=LogManager.getLogger(this.getClass()); //logging
		
	//	ChromeOptions options=new ChromeOptions();
	//	options.setExperimentalOption("excludeSwitches", new String[] {"enable-automation"});
		
	//	WebDriverManager.chromedriver().setup(); //no need of this command in new version of selenium
		//driver=new ChromeDriver(options);
		
		if(br.equals("chrome"))
		{
		driver=new ChromeDriver();
		}
		else if(br.equals("edge"))
		{
			driver=new EdgeDriver();
		}
		else
		{
			driver=new FirefoxDriver();
		}
		
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get("http://localhost/opencart/upload/index.php");
		//driver.get(rb.getString("appURL"));
		
		driver.manage().window().maximize();
	}
	
	
	
	@AfterClass(groups={"Master","Sanity","Regression"})
    public void tearDown()
	{
		driver.quit();
	}
	
	
	//random generating methods
	
	//randomly generating email/String of 5 characters
	public String randomeString()
	{
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		return(generatedString);
		
	}
	
	//randomly generating mobile number of ten characters
	/*	public String randomeNumber()
		{
			String generatedString2 = RandomStringUtils.randomNumeric(10);
			return(generatedString2);
		}
		
		*/
	
	//AlphaNumeric string generating
	public String randomeAlphaNumeric()
	{
		String st = RandomStringUtils.randomAlphabetic(4);
		String num = RandomStringUtils.randomNumeric(3);
		
		//return(st+num);
		return(st+"@"+num); //or any special character instead of @ {whatever you like}
			}	
	
	//to capture screenshots
	
	public String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
				
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";

		try {
			FileUtils.copyFile(source, new File(destination));
		} catch (Exception e) {
			e.getMessage();
		}
		return destination;

	}
	}
	
	
	

