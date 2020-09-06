package pageFactory;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CalendarSchedulePage {
	public CalendarSchedulePage(AndroidDriver<AndroidElement> driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(id = "com.google.android.calendar:id/floating_action_button")
	private WebElement actionButton;
	
	@AndroidFindBy(xpath="//*[@text='Event']")
	private WebElement createEventButton;

	
	public WebElement getActionButton() {
		System.out.println("Calendar Schedule page Create Action Button");
		return actionButton;
	}
	
	public WebElement getCreateEventButton() {
		System.out.println("Calendar Schedule page Create Event Button");
		return createEventButton;
	}

}
