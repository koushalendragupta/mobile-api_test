package pageFactory;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class AddEventPage {
	public AddEventPage(AndroidDriver<AndroidElement> driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(xpath="//*[@text='Add title']")
	private WebElement addTitleTextField;
	
	@AndroidFindBy(xpath="//*[contains(@content-desc,'Start date')]")
	private WebElement startDateLabel;
	
	@AndroidFindBy(xpath="//*[contains(@content-desc,'Start time')]")
	private WebElement startTimeLabel;

	@AndroidFindBy(xpath="//*[contains(@content-desc,'End time')]")
	private WebElement endTimeLabel;
	
	@AndroidFindBy(xpath="//*[@text='Does not repeat']")
	private WebElement doNotRepeatLabel;
	
	@AndroidFindBy(xpath="//*[@text='Add people']")
	private WebElement addPeopleLabel;
	
	@AndroidFindBy(xpath="//*[@text='Save']")
	private WebElement saveButton;
	
	@AndroidFindBy(xpath="//*[@text='Send']")
	private WebElement sendButton;
	
	
	public WebElement getAddTitleTextField() {
		System.out.println("Add Event Page Add Title Text Field");
		return addTitleTextField;
	}
	
	public WebElement getStartDateLabel() {
		System.out.println("Add Event Page Start Date Label");
		return startDateLabel;
	}
	
	public WebElement getStartTimeLabel() {
		System.out.println("Add Event Page Start Time Label");
		return startTimeLabel;
	}
	
	public WebElement getEndTimeLabel() {
		System.out.println("Add Event Page End Time Label");
		return endTimeLabel;
	}
	
	public WebElement getdoNotRepeatLabel() {
		System.out.println("Add Event Do Not Repeat Label");
		return doNotRepeatLabel;
	}
	
	public WebElement getAddPeopleLabel() {
		System.out.println("addPeopleLabel on AddEventPage");
		return addPeopleLabel;
	}
	
	public WebElement getSaveButton() {
		System.out.println("saveButton on AddEventPage");
		return saveButton;
	}
	
	public WebElement getSendButton() {
		System.out.println("sendButton on AddEventPage");
		return sendButton;
	}
}
