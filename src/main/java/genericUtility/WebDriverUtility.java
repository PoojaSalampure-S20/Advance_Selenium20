package genericUtility;

import java.awt.dnd.DragGestureEvent;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;
import java.util.logging.FileHandler;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility 
{
   public void waitForPageLoad(WebDriver driver) 
   {
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
   }
   
   public void waitForVisibilityofElement(WebDriver driver,WebElement element)
   {
	 WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
	 wait.until(ExpectedConditions.visibilityOf(element));
   }
   
   public void swtichToFrame(WebDriver driver,int index)  
   {
	  driver.switchTo().frame(index);
   }
   
   public void swtichToFrame(WebDriver driver,String nameorid) 
   {
	driver.switchTo().frame(nameorid);
   }
   
   public void switchToFrame(WebDriver driver,WebElement frameelemt) 
   {
	 driver.switchTo().frame(frameelemt);
   }
   
   public void switchToAlertAndAccept(WebDriver driver) 
   {
	 driver.switchTo().alert().accept();
   }
   
   public void swtichToAlertAndDismiss(WebDriver driver) 
   {
	 driver.switchTo().alert().dismiss();
   }
   
   public void switchToAlertAndSendKeys(WebDriver driver, String text) 
   {
	 driver.switchTo().alert().sendKeys(text);
   }
   
   public void switchToAlertAndGetText(WebDriver driver) 
   {
	 driver.switchTo().alert().getText();
   }
   
   public void select(WebElement element,String value) 
   {
	 Select sel= new Select(element);
	 sel.selectByValue(value);
   }
   
   public void select(String text,WebElement element) 
   {
	 Select sel=new Select(element);
	 sel.selectByVisibleText(text);
   }
   
   public void mouseHover(WebDriver driver,WebElement element) 
   {
	 Actions act=new Actions(driver);
	 act.moveToElement(element).perform();
   }
   public void clickOnWebElement(WebDriver driver,WebElement element) 
   {
	 Actions act=new Actions(driver);
	 act.moveToElement(element).click().perform();
   }
   public void doubbleClickOnWebElement(WebDriver driver, WebElement element) 
   {
	   Actions act=  new Actions(driver);
	   act.doubleClick(element).perform();
   }
   
   public void passInput(WebDriver driver,WebElement element,String text) 
   {
	 Actions act= new Actions(driver);
	 act.click(element).sendKeys(text).perform();
   }
   
   public void switchToWindow(WebDriver driver) 
   {
	 String parentId = driver.getWindowHandle();
	 Set<String> childId = driver.getWindowHandles();
	 childId.remove(parentId);
	 
	 for(String windId : childId)
	 {
		 driver.switchTo().window(windId);
	 }
   }
   public void takeScreenshot(WebDriver driver,String filename) throws IOException 
   {
	   TakesScreenshot ts=(TakesScreenshot)driver;
	   File temp = ts.getScreenshotAs(OutputType.FILE);
	   File prem=new File("./errorshots/"+ filename +".png");
	   org.openqa.selenium.io.FileHandler.copy(temp, prem);
	}
   
   
}
