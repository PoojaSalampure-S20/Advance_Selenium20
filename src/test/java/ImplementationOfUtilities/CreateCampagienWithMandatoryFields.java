package ImplementationOfUtilities;

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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import genericUtility.ExcelFileUtility;
import genericUtility.JavaUtility;
import genericUtility.PropertiesFileUtility;
import genericUtility.WebDriverUtility;

public class CreateCampagienWithMandatoryFields 
{

	public static void main(String[] args) throws IOException 
	{
		PropertiesFileUtility proprtyUtil=new PropertiesFileUtility();
		ExcelFileUtility exclutil=new ExcelFileUtility();
		JavaUtility javutil= new JavaUtility();
		WebDriverUtility wbdrvrutil= new WebDriverUtility();
		
		String BROWSER = proprtyUtil.togetDatafromPropertiesFile("Browser");
		String URL = proprtyUtil.togetDatafromPropertiesFile("Url");
		String USERNAME = proprtyUtil.togetDatafromPropertiesFile("Username");
		String PASSWORD = proprtyUtil.togetDatafromPropertiesFile("Password");
		
		String campname = exclutil.toReadDataFromExcelFile("Campaign",1,2);
		String target= exclutil.toReadDataFromExcelFile("Campaign",1,3);
		
	    System.out.println(BROWSER);
	    System.out.println(URL);
	    
	    WebDriver driver=null;
	    if(BROWSER.equals("Edge"))
	    {
	    	driver=new EdgeDriver();
	    }
	    else if (BROWSER.equals("Chrome")) 
	    {
			driver=new ChromeDriver();
		}
	    else if (BROWSER.equals("Firefox")) 
	    {
			driver=new FirefoxDriver();
		}
	    driver.manage().window().maximize();
	    wbdrvrutil.waitForPageLoad(driver);
	    
	    Random ran= new Random();
        int randomcount = ran.nextInt(1000);

        //Login

        driver.get(URL);
        driver.findElement(By.id("username")).sendKeys(USERNAME);
        driver.findElement(By.id("inputPassword")).sendKeys(PASSWORD);
        driver.findElement(By.xpath("//button[text()='Sign In']")).click();

        //Random ran=new Random();
        //int randcount = ran.nextInt(1000);

        //CreateCampigen

        driver.findElement(By.xpath("//span[text()='Create Campaign']")).click();
        driver.findElement(By.name("campaignName")).sendKeys(campname+javutil.togetRandomNumb());
        WebElement targetfield = driver.findElement(By.name("targetSize"));
        targetfield.clear();
        targetfield.sendKeys(target);
        driver.findElement(By.xpath("//button[text()='Create Campaign']")).click();

        //Validation

        WebElement toastmsg = driver.findElement(By.xpath("//div[@role='alert']"));
        wbdrvrutil.waitForVisibilityofElement(driver, toastmsg);
        String msgdisplay = toastmsg.getText();
        if(msgdisplay.contains(campname))
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
        Actions act=new Actions(driver);
        act.moveToElement(profileicon).perform();
        WebElement logout = driver.findElement(By.xpath("//div[@class='dropdown-item logout']"));
        act.moveToElement(logout).click().perform();

        driver.quit();



	}

}
