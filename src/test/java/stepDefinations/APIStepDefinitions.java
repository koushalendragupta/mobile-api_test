package stepDefinations;

import static io.restassured.RestAssured.given;

import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import java.util.ArrayList;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojoClasses.GetWeatherForecast;
import resources.Utility;

@RunWith(Cucumber.class)
public class APIStepDefinitions extends Utility {
	RequestSpecification res;
	ResponseSpecification resspec;
	Response response;
	GetWeatherForecast gf;

	@Given("^I like to holiday in \"([^\"]*)\" and I only like to holiday on \"([^\"]*)\"$")
	public void i_like_to_holiday_in_something_and_i_only_like_to_holiday_on_something(String city, String day)
			throws Throwable {
		res = given().spec(requestSpecification(city));
		Utility.setDayOfWeek(day);
	}

	@When("^I look up the the weather forecast for the next \"([^\"]*)\" days$")
	public void i_look_up_the_the_weather_forecast_for_the_next_something_days(String numberof) throws Throwable {

		Utility.setNumberOfDays(numberof);

		resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

		response = res.when().get();

		gf = response.as(GetWeatherForecast.class);

	}

	@Then("^The response is valid \"([^\"]*)\"$")
	public void the_response_is_valid_something(String responsetype) throws Throwable {
		assertEquals("Status Code Validation", response.getStatusCode(), 200);
		assertEquals("Content Type Validation", response.getContentType(), responsetype);
	}

	@And("^The response contains \"([^\"]*)\" as the destination.$")
	public void the_response_contains_something_as_the_destination(String city) throws Throwable {
		assertEquals("City Name Validation", gf.getCity_name(), city);
	}

	@And("^I can see the temperature is between \"([^\"]*)\" to \"([^\"]*)\" degrees in \"([^\"]*)\"$")
	public void i_can_see_the_temperature_is_between_something_to_something_degrees_in_something(String mintemp,
			String maxtemp, String city) throws Throwable {

		ArrayList<String> expecteddays = Utility.getDates();

		for (int j = 0; j < expecteddays.size(); j++) {
			for (int i = 0; i < gf.getData().size(); i++) {
				if (gf.getData().get(i).getDatetime().equalsIgnoreCase(expecteddays.get(j))) {
					assertTrue(
							"Temprature is in between 20 to 30 on " + Utility.getDayOfWeek() + " Date "
									+ expecteddays.get(j),
							gf.getData().get(i).getHigh_temp() < Double.parseDouble(maxtemp)
									&& gf.getData().get(i).getLow_temp() > Double.parseDouble(mintemp));
				}
			}

		}
	}

	@And("^Check If it has rained previous days$")
	public void check_if_it_has_rained_previous_days() throws Throwable {
		ArrayList<String> expecteddays = Utility.getDates();
		boolean flag = true;

		for (int i = 0; i < gf.getData().size(); i++) {
			if (gf.getData().get(i).getDatetime().equalsIgnoreCase(expecteddays.get(0))) {
				break;
			}

			if (gf.getData().get(i).getWeather().getDescription().contains("rain")) {
				flag = false;
				break;
			}
		}

		assertTrue("Its not rained before first " + Utility.getDayOfWeek() + " :", flag);
	}

	@And("^I can see that it won't be snowing for the next \"([^\"]*)\" days$")
	public void i_can_see_that_it_wont_be_snowing_for_the_next_something_days(String numberof) throws Throwable {
		boolean flag = true;

		for (int i = 0; i < gf.getData().size(); i++) {
			if (gf.getData().get(i).getWeather().getDescription().contains("snow")) {
				flag = false;
				break;
			}
		}

		assertTrue("It will not snow in next " + numberof + " days :", flag);
	}

}