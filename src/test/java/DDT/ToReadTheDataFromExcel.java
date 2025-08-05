package DDT;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ToReadTheDataFromExcel 
{

	public static void main(String[] args) throws EncryptedDocumentException, IOException 
	{
		FileInputStream fis=new FileInputStream(".\\src\\test\\resources\\TestScript.xlsx");
		Workbook wb=WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet("Campaign");
		Row roww = sh.getRow(1);
		String campcellvalue = roww.getCell(2).getStringCellValue();
		String targetcellvalue = roww.getCell(3).getStringCellValue();
		System.out.println(campcellvalue);
		System.out.println(targetcellvalue);
		wb.close();

	}

	
	

}
