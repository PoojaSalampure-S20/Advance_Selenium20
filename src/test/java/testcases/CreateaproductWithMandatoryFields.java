package testcases;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.http.WebSocket;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateaproductWithMandatoryFields 
{
	
	public static void main(String[] args) throws InterruptedException, IOException 
	{
		//Read Properties file
		//Create an object of Fileinputstream
				FileInputStream fis=new FileInputStream(".\\src\\test\\resources\\Commondata.properties");
			    Properties prop=new Properties();
			    prop.load(fis);
			    String BROWSER = prop.getProperty("Browser");
			    String URL = prop.getProperty("Url");
			    String USERNAME = prop.getProperty("Username");
			    String PASSWORD = prop.getProperty("Password");
			    
			    System.out.println(BROWSER);
			    System.out.println(URL);
			    
			    WebDriver driver=new ChromeDriver();
			    driver.manage().window().maximize();
			    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
			    //Login
		
			    driver.get("http://49.249.28.218:8098/");
			    driver.findElement(By.id("username")).sendKeys(USERNAME);
			    driver.findElement(By.id("inputPassword")).sendKeys(PASSWORD);
			    driver.findElement(By.xpath("//button[text()='Sign In']")).click();
			    
			    Random randm=new Random();
			    randm.nextInt(1000);
		
			    //Read the Data from excel
			    
			    FileInputStream fis1=new FileInputStream(".\\src\\test\\resources\\TestScript.xlsx");
			    Workbook wb = WorkbookFactory.create(fis1);
			    Sheet sht = wb.getSheet("Product");
			    String productname = sht.getRow(1).getCell(0).getStringCellValue();
			    String quantity = sht.getRow(1).getCell(1).getStringCellValue();
			    String price = sht.getRow(1).getCell(2).getStringCellValue();
			    
			    
			    //Add Product
		
			    driver.findElement(By.linkText("Products")).click();
			    driver.findElement(By.xpath("//span[text()='Add Product']")).click();
			    driver.findElement(By.name("productName")).sendKeys(productname+randm);
		
			    WebElement selcategory = driver.findElement(By.name("productCategory"));
			    Select sel=new Select(selcategory);
			    sel.selectByVisibleText("Furniture");
		
			    driver.findElement(By.name("quantity")).sendKeys(quantity);
		
			    WebElement priceunit = driver.findElement(By.name("price"));
			    priceunit.clear();
			    priceunit.sendKeys(price);
	    
			    WebElement selectvendoedrpdwn = driver.findElement(By.name("vendorId"));
			    Select sel1=new Select(selectvendoedrpdwn);
			    sel1.selectByVisibleText("Vendor_50877 - (Electronics)");
		
			    driver.findElement(By.xpath("//button[@type='submit']")).click();
		
			    //Validation
			    WebElement toastemsg = driver.findElement(By.xpath("//div[@role='alert']"));
			    WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
			    wait.until(ExpectedConditions.visibilityOf(toastemsg));
			    String msgdisplay = toastemsg.getText();
			    if(msgdisplay.contains(productname))
			    {
			    	System.out.println("Product Created");
			    }
			    else 
			    {
			    	System.out.println("Product is not created");
			    }
			    
			    driver.findElement(By.xpath("//button[@aria-label='close']")).click();
		//Logout
		
			    WebElement proflicon = driver.findElement(By.xpath("//div[@class='user-icon']"));
			    Actions act=new Actions(driver);
			    act.moveToElement(proflicon).perform();
			    WebElement logout = driver.findElement(By.xpath("//div[@class='dropdown-item logout']"));
			    act.moveToElement(logout).click().perform();
		
			    driver.quit();
		
		
	    
	    
		
		
		

	}

}
