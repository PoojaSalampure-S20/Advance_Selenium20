package genericUtility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtility 
{
		public int togetRandomNumb()
		{
			Random ran=new Random();
			int rancount = ran.nextInt(1000);
			return rancount;
		}

	   public String togetCurrentDate()
	   {
		   Date date=new Date();
		   SimpleDateFormat sim=new SimpleDateFormat("dd-MM-yyyy");
		   String currentdate = sim.format(date);
		   return currentdate;
	   }
	   public String togetRequiredDate(int days)
	   {
		   Date date=new Date();
		   SimpleDateFormat sim=new SimpleDateFormat("dd-MM-yyyy");
		   Calendar cal = sim.getCalendar();
		   cal.add(Calendar.DAY_OF_MONTH,days);
		   String datereq = sim.format(cal.getTime());
		   return datereq;	   
		
	   }

}
