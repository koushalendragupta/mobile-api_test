package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {

	public static RequestSpecification req;
	private static String dayOfWeek;
	private static String numberOfDays;

	public static String getDayOfWeek() {
		return dayOfWeek;
	}

	public static void setDayOfWeek(String dayOfWeek) {
		Utils.dayOfWeek = dayOfWeek;
	}

	public static String getNumberOfDays() {
		return numberOfDays;
	}

	public static void setNumberOfDays(String numberOfDays) {
		Utils.numberOfDays = numberOfDays;
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
}
