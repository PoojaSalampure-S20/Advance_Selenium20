package firstpackage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class EdgeBrowserTest {

	public static void main(String[] args) throws InterruptedException 
	{
		System.setProperty("webdriver,edge,driver","C:\\Users\\HP\\Downloads\\edgedriver_win64 (1)\\msedgedriver.exe");
		WebDriver driver=new EdgeDriver();
		driver.get("https://www.instagram.com/");
		Thread.sleep(3000);
		driver.close();
		

	}

}