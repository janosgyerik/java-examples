package com.example.randomstuff;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateComparisonDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GregorianCalendar startdate = new GregorianCalendar(2012, 3, 3, 0, 00);
		GregorianCalendar enddate = new GregorianCalendar(2012, 3, 4, 0, 00);
		System.out.println("startdate = " + startdate.getTime() + ", " + startdate.getTime().getTime());
		System.out.println("enddate = " + enddate.getTime() + ", " + enddate.getTime().getTime());
		
		String[] dateStrings = new String[] { "04/03/2012 0:00", "04/03/2012 0:02", "04/04/2012 0:00" };
		int startPoint = -1;
		int endPoint = -1;
		Date[] dates = new Date[dateStrings.length];

		for (int i = 0; i < dateStrings.length; i++) {
			try {
				SimpleDateFormat formatter = new SimpleDateFormat(
						"MM/dd/yyyy k:mm");
				dates[i] = formatter.parse(dateStrings[i]);
				System.out.println("parsed: " + dates[i] + ", " + dates[i].getTime());

				if (startdate.getTime().equals(dates[i])) {
					startPoint = i;
				}
				if (enddate.getTime().equals(dates[i])) {
					endPoint = i;
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		System.out.println("startPoint = " + startPoint);
		System.out.println("endPoint = " + endPoint);
	}

}
