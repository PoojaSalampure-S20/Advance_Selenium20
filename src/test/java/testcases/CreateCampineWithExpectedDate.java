package testcases;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateCampineWithExpectedDate 
{

	public static void main(String[] args) throws IOException, InterruptedException 
	{   
		//Read the data from properties file
				FileInputStream fis=new FileInputStream(".\\src\\test\\resources\\Commondata.properties");
			    Properties prop=new Properties();
			    prop.load(fis);
			    String BROWSER = prop.getProperty("Browser");
			    String URL = prop.getProperty("Url");
			    String USERNAME = prop.getProperty("Username");
			    String PASSWORD = prop.getProperty("Password");
			    
			    System.out.println(BROWSER);
			    System.out.println(URL);
			    
			    //Read the data from Excel File
			    FileInputStream fis1=new FileInputStream(".\\src\\test\\resources\\TestScript.xlsx");
				Workbook wb=WorkbookFactory.create(fis1);
				Sheet sh = wb.getSheet("Campaign");
				Row roww = sh.getRow(1);
				String campcellvalue = roww.getCell(2).getStringCellValue();
				String targetcellvalue = roww.getCell(3).getStringCellValue();
				System.out.println(campcellvalue);
				System.out.println(targetcellvalue);
				wb.close();

			    WebDriver driver=new ChromeDriver();
			    driver.manage().window().maximize();
			    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
			    //Login
		
			    driver.get("http://49.249.28.218:8098/");
			    driver.findElement(By.id("username")).sendKeys(USERNAME);
			    driver.findElement(By.id("inputPassword")).sendKeys(PASSWORD);
			    driver.findElement(By.xpath("//button[text()='Sign In']")).click();
			    
			    Thread.sleep(5000);
		
			    Random ran=new Random();
			    int randcount = ran.nextInt(1000);
		
			    Date date=new Date();
			    SimpleDateFormat simp=new SimpleDateFormat("dd-MM-yyyy");
			    simp.format(date);
			    Calendar cal=simp.getCalendar();
			    cal.add(Calendar.DAY_OF_MONTH,30);
			    String requireddate = simp.format(cal.getTime());
		
			    //CreateCampigen
		
			    driver.findElement(By.xpath("//span[text()='Create Campaign']")).click();
			    driver.findElement(By.name("campaignName")).sendKeys(campcellvalue+randcount);
			    WebElement targetfield = driver.findElement(By.name("targetSize"));
			    targetfield.clear();
			    targetfield.sendKeys("10");
		
			    WebElement expecteddate = driver.findElement(By.name("expectedCloseDate"));
			    Actions act= new Actions(driver);
			    act.click(expecteddate).sendKeys(requireddate).perform();
		
			    driver.findElement(By.xpath("//button[text()='Create Campaign']")).click();
		
			    //Validation
		
			    WebElement toastmsg = driver.findElement(By.xpath("//div[@role='alert']"));
			    WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
			    wait.until(ExpectedConditions.visibilityOf(toastmsg));
			    String msgdisplay = toastmsg.getText();
			    if(msgdisplay.contains(campcellvalue))
			    {
			    	System.out.println("Campigen Created");
			
			    }
			    else 
			    {
			    	System.out.println("Campigen is not created");
			    }
			    driver.findElement(By.xpath("//button[@aria-label='close']")).click();
		
			    //Logout
			    WebElement profileicon = driver.findElement(By.xpath("//div[@class='user-icon']"));
			    //Actions act=new Actions(driver);
			    act.moveToElement(profileicon).perform();
			    WebElement logout = driver.findElement(By.xpath("//div[@class='dropdown-item logout']"));
			    act.moveToElement(logout).click().perform();
		
			    driver.quit();
		


	}

}
