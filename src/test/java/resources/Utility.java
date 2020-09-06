package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Properties;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;

public class Utility {

	public static RequestSpecification req;
	private static String dayOfWeek;
	private static String numberOfDays;
	private static String nonWorkingDay;
	private static String meetingDay1;
	private static String meetingDay2;
	private static String meetingDay3;
	private static String hours;
	private static String mins;
	private static String[] emails;
	private static String meetingName;
	
	public static String getMeetingName() {
		return meetingName;
	}

	public static void setMeetingName(String meetingName) {
		Utility.meetingName = meetingName;
	}

	public static String[] getEmails() {
		return emails;
	}

	public static void setEmails(String[] emails) {
		Utility.emails = emails;
	}

	public static String getHours() {
		return hours;
	}

	public static void setHours(String hours) {
		Utility.hours = hours;
	}

	public static String getMins() {
		return mins;
	}

	public static void setMins(String mins) {
		Utility.mins = mins;
	}

	public static String getMeetingDay1() {
		return meetingDay1;
	}

	public static void setMeetingDay1(String meetingDay1) {
		Utility.meetingDay1 = meetingDay1;
	}

	public static String getMeetingDay2() {
		return meetingDay2;
	}

	public static void setMeetingDay2(String meetingDay2) {
		Utility.meetingDay2 = meetingDay2;
	}

	public static String getMeetingDay3() {
		return meetingDay3;
	}

	public static void setMeetingDay3(String meetingDay3) {
		Utility.meetingDay3 = meetingDay3;
	}
	
	public static String getNonWorking() {
		return nonWorkingDay;
	}

	public static void setNonWorking(String nonWorkingDay) {
		Utility.nonWorkingDay = nonWorkingDay;
	}

	public static String getDayOfWeek() {
		return dayOfWeek;
	}

	public static void setDayOfWeek(String dayOfWeek) {
		Utility.dayOfWeek = dayOfWeek;
	}

	public static String getNumberOfDays() {
		return numberOfDays;
	}

	public static void setNumberOfDays(String numberOfDays) {
		Utility.numberOfDays = numberOfDays;
	}

	public RequestSpecification requestSpecification(String city) throws IOException {

		if (req == null) {
			PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
			req = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl"))
					.addQueryParam("key", "cae77b7012af416585b460aae2d3c3d3")
					.addQueryParam("lang", getGlobalValue("lang")).addQueryParam("units", getGlobalValue("units"))
					.addQueryParam("days", numberOfDays).addQueryParam("city", city)
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log)).build();
			return req;
		}
		return req;

	}

	public static String getGlobalValue(String key) throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				"C:\\Users\\somya\\Downloads\\APIFramework\\src\\test\\java\\resources\\global.properties");
		prop.load(fis);
		return prop.getProperty(key);
	}

	public static ArrayList<String> getDates() {
		ArrayList<String> dates = new ArrayList<String>();
		LocalDate ld = LocalDate.now();
		System.out.println(ld);
		ld = ld.with(TemporalAdjusters.nextOrSame(DayOfWeek.valueOf(dayOfWeek)));
		dates.add(ld.toString());
		ld = ld.with(TemporalAdjusters.next(DayOfWeek.valueOf(dayOfWeek)));
		dates.add(ld.toString());
		System.out.println("Thrusdays : " + dates);

		return dates;
	}

	public static String convertFirstCharToUpperCase(String day) {		
		day = Character.toUpperCase(day.charAt(0)) + day.substring(1);
		
		return day;	
	}
}
