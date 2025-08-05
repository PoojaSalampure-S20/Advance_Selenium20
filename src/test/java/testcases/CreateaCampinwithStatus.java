package testcases;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateaCampinwithStatus 
{

	public static void main(String[] args) throws IOException 
	{
		//Read the data from Properties file
		
		
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
	    
		//Read the data from Excel
			    FileInputStream fis1=new FileInputStream(".\\src\\test\\resources\\TestScript.xlsx");
				Workbook wb=WorkbookFactory.create(fis1);
				Sheet sh = wb.getSheet("Campaign");
				Row roww = sh.getRow(1);
				String campcellvalue = roww.getCell(2).getStringCellValue();
				String targetcellvalue = roww.getCell(3).getStringCellValue();
				System.out.println(campcellvalue);
				System.out.println(targetcellvalue);
				wb.close();
				
				Random ran=new Random();
				int randomcount = ran.nextInt(1000);

				WebDriver driver= new ChromeDriver();
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
				//Login
				driver.get("http://49.249.28.218:8098/");
				driver.findElement(By.id("username")).sendKeys(USERNAME);
				driver.findElement(By.id("inputPassword")).sendKeys(PASSWORD);
				driver.findElement(By.xpath("//button[@type='submit']")).click();
		
				//Create Campigne
				driver.findElement(By.xpath("//span[text()='Create Campaign']")).click();
				driver.findElement(By.name("campaignName")).sendKeys(campcellvalue+randomcount);
				driver.findElement(By.name("campaignStatus")).sendKeys(targetcellvalue+randomcount);
				WebElement targget = driver.findElement(By.name("targetSize"));
				targget.clear();
				targget.sendKeys("10");
				driver.findElement(By.xpath("//button[text()='Create Campaign']")).click();
		
				//Validation
				WebElement toastemsg = driver.findElement(By.xpath("//div[@role='alert']"));
				WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
				wait.until(ExpectedConditions.visibilityOf(toastemsg));
				String msgdisplay = toastemsg.getText();
				if(msgdisplay.contains(campcellvalue))
				{
					System.out.println("Campiegn Created");
				}
				else 
				{
					System.out.println("Campiegn is not created");
				}
				driver.findElement(By.xpath("//button[@aria-label='close']")).click();
		
				//Logout
				WebElement profileicon = driver.findElement(By.xpath("//div[@class=\"user-icon\"]"));
				Actions act=new Actions(driver);
				act.moveToElement(profileicon).perform();
				WebElement logout = driver.findElement(By.xpath("//div[@class='dropdown-item logout']"));
				act.moveToElement(logout).click().perform();
		
				driver.quit();
		
		
		
		
		
		

	}

}
