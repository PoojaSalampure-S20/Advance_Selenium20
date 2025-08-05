package DDT;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.bidi.module.Browser;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ToReadTheDataFromPropertiesFile 
{

	public static void main(String[] args) throws IOException 
	{
		//Create an object of Fileinputstream
		FileInputStream fis=new FileInputStream(".\\src\\test\\resources\\Commondata.properties");
		//create an object of properties file
	    Properties prop=new Properties();
	    //load all the keys
	    prop.load(fis);
	    //get the properties
	    String BROWSER = prop.getProperty("Browser");
	    String URL = prop.getProperty("Url");
	    String USERNAME = prop.getProperty("Username");
	    String PASSWORD = prop.getProperty("Password");
	    
	    System.out.println(BROWSER);
	    System.out.println(URL);
	    
	    //Actual Script
	    
	    WebDriver driver=null;
	    if(BROWSER.equals("Edge"))
	    {
	    	driver=new EdgeDriver();
	    }
	    else if( BROWSER.equals("Chrome"))
	    {
			driver=new ChromeDriver();
		}
	    else if (BROWSER.equals("Firefox")) 
	    {
			driver=new FirefoxDriver();
		}
	    
	    driver.manage().window().maximize();
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	    driver.get(URL);
	    driver.findElement(By.id("username")).sendKeys(USERNAME);
	    driver.findElement(By.id("inputPassword")).sendKeys(PASSWORD);
	    driver.findElement(By.xpath("//button[text()='Sign In']")).click();
	    

	}

}
