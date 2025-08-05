package genericUtility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFileUtility {

	public String togetDatafromPropertiesFile(String key) throws IOException 
	{
	   FileInputStream fis= new FileInputStream(".\\src\\test\\resources\\Commondata.properties");
	   Properties pro=new Properties();
       pro.load(fis);
       String value = pro.getProperty(key);
       return value;
	}

}
