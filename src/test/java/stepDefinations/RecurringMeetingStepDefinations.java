package stepDefinations;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import pageFactory.AddEventPage;
import pageFactory.AddPeoplePage;
import pageFactory.CalendarSchedulePage;
import pageFactory.CustomRecurrencePage;
import pageFactory.EndTimePage;
import pageFactory.LaunchPage1;
import pageFactory.LaunchPage2;
import pageFactory.LaunchPage3;
import pageFactory.StartDatePage;
import resources.Base;
import resources.Utility;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import static org.junit.Assert.*;

@RunWith(Cucumber.class)
public class RecurringMeetingStepDefinations extends Base {
	AndroidDriver<AndroidElement> driver;

	@Given("^I have launched the Calendar App$")
	public void i_have_launched_the_calendar_app_and() throws Throwable {
		service = startServer();
		driver = capabilities();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		System.out.println("Application Launched");
	}

	@When("^It is not a (.+)$")
	public void it_is_not_a(String nonworkingday) throws Throwable {
		Utility.setNonWorking(nonworkingday.toUpperCase());
		System.out.println("Non Working day is set as : " + Utility.getNonWorking());
	}

	@Then("^I want to book a meeting with the title (.+)$")
	public void i_want_to_book_a_meeting_with_the_title(String title) throws Throwable {
		Utility.setMeetingName(title);

		LaunchPage1 lp1 = new LaunchPage1(driver);
		lp1.getNextArrorw().click();

		LaunchPage2 lp2 = new LaunchPage2(driver);
		lp2.getNextArrorw().click();

		LaunchPage3 lp3 = new LaunchPage3(driver);
		lp3.getGotItButton().click();

		CalendarSchedulePage csp = new CalendarSchedulePage(driver);
		csp.getActionButton().click();

		csp.getCreateEventButton().click();

		AddEventPage aep = new AddEventPage(driver);
		aep.getAddTitleTextField().sendKeys(title);

	}

	@Then("^I Check if the meeting is created as expected$")
	public void i_check_if_the_meeting_is_created_as_expected() throws Throwable {
		List<AndroidElement> meeting = driver
				.findElementsByXPath("//*[contains(@content-desc,'Recurring Team Catch Up')]");
		
		assertTrue(meeting.size()>1);

	}

	@And("^Meeting is not repeated on successive days$")
	public void meeting_is_not_repeated_on_successive_days() throws Throwable {
		String dayOfWeek = LocalDate.now().getDayOfWeek().name();

		if (dayOfWeek.equalsIgnoreCase(Utility.getNonWorking())) {
			Utility.setMeetingDay1(LocalDate.now().plusDays(1).getDayOfWeek().name().toLowerCase());
			Utility.setMeetingDay2(LocalDate.now().plusDays(3).getDayOfWeek().name().toLowerCase());
			Utility.setMeetingDay3(LocalDate.now().plusDays(5).getDayOfWeek().name().toLowerCase());
		} else {
			Utility.setMeetingDay1(LocalDate.now().plusDays(0).getDayOfWeek().name().toLowerCase());

			dayOfWeek = LocalDate.now().plusDays(2).getDayOfWeek().name();
			if (dayOfWeek.equalsIgnoreCase(Utility.getNonWorking())) {
				Utility.setMeetingDay2(LocalDate.now().plusDays(3).getDayOfWeek().name().toLowerCase());

				dayOfWeek = LocalDate.now().plusDays(5).getDayOfWeek().name();
				if (dayOfWeek.equalsIgnoreCase(Utility.getNonWorking())) {
					Utility.setMeetingDay3(LocalDate.now().plusDays(6).getDayOfWeek().name().toLowerCase());
				} else {
					Utility.setMeetingDay3(LocalDate.now().plusDays(5).getDayOfWeek().name().toLowerCase());
				}

			} else {
				Utility.setMeetingDay2(LocalDate.now().plusDays(2).getDayOfWeek().name().toLowerCase());

				dayOfWeek = LocalDate.now().plusDays(4).getDayOfWeek().name();
				if (dayOfWeek.equalsIgnoreCase(Utility.getNonWorking())) {
					Utility.setMeetingDay3(LocalDate.now().plusDays(5).getDayOfWeek().name().toLowerCase());
				} else {
					Utility.setMeetingDay3(LocalDate.now().plusDays(4).getDayOfWeek().name().toLowerCase());
				}

			}

		}

		System.out.println("Meeting will be created on " + Utility.getMeetingDay1() + ", " + Utility.getMeetingDay2()
				+ ", " + Utility.getMeetingDay3());
	}

	@And("^Set Meeting duration as (.+) in the evening$")
	public void set_meeting_duration_as_in_the_evening(String hrsminutes) throws Throwable {
		String[] time = hrsminutes.split(":");
		Utility.setMins(time[1]);
		Utility.setHours(time[0]);

		String dayOfWeek = LocalDate.now().getDayOfWeek().name();
		LocalDate nextWorkDayDate = LocalDate.now();
		int date;
		AddEventPage aep = new AddEventPage(driver);

		if (dayOfWeek.equalsIgnoreCase(Utility.getNonWorking())) {
			nextWorkDayDate = LocalDate.now().plusDays(1);
			date = nextWorkDayDate.getDayOfMonth();
			aep.getStartDateLabel().click();

			StartDatePage sdp = new StartDatePage(driver);

			driver.findElement(By.xpath("//*[@text='" + date + "']")).click();
			sdp.getOkButton().click();
		}

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a");

		String startTime = aep.getStartTimeLabel().getText();
		startTime = startTime.toUpperCase();
		LocalTime currTime1 = LocalTime.parse(startTime, formatter);

		LocalTime currTime = currTime1.plusHours(Integer.parseInt(Utility.getHours()))
				.plusMinutes(Integer.parseInt(Utility.getMins()));
		String timeString = currTime.format(formatter);
		String timing = timeString.split(" ")[0];

		aep.getEndTimeLabel().click();

		EndTimePage etp = new EndTimePage(driver);
		etp.getInputModeButton().click();
		etp.getHourInputField().sendKeys(timing.split(":")[0]);
		etp.getMinInputField().sendKeys(timing.split(":")[1]);
		etp.getAmPMDropdown().click();
		driver.findElement(By.xpath("//*[@text='" + timeString.split(" ")[1].toLowerCase() + "']")).click();
		etp.getOkButton().click();

		aep.getdoNotRepeatLabel().click();

		CustomRecurrencePage crp = new CustomRecurrencePage(driver);
		crp.getCustomLabel().click();

		String dayOfWeekMeetingStart = nextWorkDayDate.getDayOfWeek().name().toLowerCase();
		dayOfWeekMeetingStart = Character.toUpperCase(dayOfWeekMeetingStart.charAt(0))
				+ dayOfWeekMeetingStart.substring(1);
		System.out.println(dayOfWeekMeetingStart);
		driver.findElement(By.xpath("//*[@content-desc='" + dayOfWeekMeetingStart + "']")).click();

		String meetingDay1 = Utility.convertFirstCharToUpperCase(Utility.getMeetingDay1());

		String meetingDay2 = Utility.convertFirstCharToUpperCase(Utility.getMeetingDay2());

		String meetingDay3 = Utility.convertFirstCharToUpperCase(Utility.getMeetingDay3());
		driver.findElement(By.xpath("//*[@content-desc='" + meetingDay1 + "']")).click();
		driver.findElement(By.xpath("//*[@content-desc='" + meetingDay2 + "']")).click();
		driver.findElement(By.xpath("//*[@content-desc='" + meetingDay3 + "']")).click();

		crp.getdoneButton().click();

	}

	@And("^I invite (.+) of people$")
	public void i_invite_of_people(String numberofinvitee) throws Throwable {
		String[] email = numberofinvitee.split(",");
		Utility.setEmails(email);

		AddEventPage aep = new AddEventPage(driver);
		AddPeoplePage app = new AddPeoplePage(driver);

		String[] invitee = numberofinvitee.split(",");
		for (int i = 0; i < invitee.length; i++) {
			aep.getAddPeopleLabel().click();
			app.getAddPeopleTextField().sendKeys(invitee[i]);
			app.getEmailLabel().click();
		}
		app.getDoneButton().click();

	}

	@And("^I save the meeting$")
	public void i_save_the_meeting() throws Throwable {

		AddEventPage aep = new AddEventPage(driver);
		aep.getSaveButton().click();
		aep.getSendButton().click();
	}

	@After
	public void tearDown(Scenario scenario) throws IOException {
	    if (scenario.isFailed()) {
		String path = System.getProperty("user.dir");
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File(path + "//screenshot.png"));
	    }
	}
}