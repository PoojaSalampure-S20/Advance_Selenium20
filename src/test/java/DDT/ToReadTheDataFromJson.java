package DDT;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ToReadTheDataFromJson 
{

	public static void main(String[] args) throws IOException, ParseException 
	{
		FileReader fir=new FileReader(".\\src\\test\\resources\\Commandata1.json");
		JSONParser parsr=new JSONParser();
		//convert physical file into java object
		Object javaobj=parsr.parse(fir);
		//typecasting
		JSONObject obj=(JSONObject) javaobj;
		//Reading the data from Json file
		String Browser = obj.get("Browser").toString();
		String UserName = obj.get("Username").toString();
		String PassWord = obj.get("Password").toString();
		System.out.println(Browser);
		System.out.println(UserName);
		System.out.println(PassWord);
		
		

	}

}
