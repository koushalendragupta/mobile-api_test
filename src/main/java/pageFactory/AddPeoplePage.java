package pageFactory;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class AddPeoplePage {
	public AddPeoplePage(AndroidDriver<AndroidElement> driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(xpath="//*[@text='Add people']")
	private WebElement addPeopleInputField;
	
	@AndroidFindBy(id = "com.google.android.calendar:id/container")
	private WebElement emailLabel;
	
	@AndroidFindBy(xpath="//*[@text='Done']")
	private WebElement doneButton;
	
	public WebElement getAddPeopleTextField() {
		System.out.println("addPeopleInputField on AddPeoplePage");
		return addPeopleInputField;
	}
	
	public WebElement getEmailLabel() {
		System.out.println("emailLabel on AddPeoplePage");
		return emailLabel;
	}
	
	public WebElement getDoneButton() {
		System.out.println("doneButton on AddPeoplePage");
		return doneButton;
	}
	
}
