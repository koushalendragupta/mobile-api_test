package pageFactory;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class EndTimePage {
	public EndTimePage(AndroidDriver<AndroidElement> driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(xpath="//*[@content-desc='Switch to text input mode for the time input.']")
	private WebElement inputModeButton;
	
	@AndroidFindBy(id = "android:id/input_hour")
	private WebElement hourInputField;
	
	@AndroidFindBy(id = "android:id/input_minute")
	private WebElement minInputField;
	
	@AndroidFindBy(id = "android:id/am_pm_spinner")
	private WebElement amPMDropdown;
	
	@AndroidFindBy(xpath="//*[@text='OK']")
	private WebElement okButton;
	
	
	public WebElement getInputModeButton() {
		System.out.println("InputModeButton on End Time Page");
		return inputModeButton;
	}
	
	public WebElement getHourInputField() {
		System.out.println("hourInputField on End Time Page");
		return hourInputField;
	}
	
	public WebElement getMinInputField() {
		System.out.println("minInputField on End Time Page");
		return minInputField;
	}
	
	public WebElement getAmPMDropdown() {
		System.out.println("amPMDropdown on End Time Page");
		return amPMDropdown;
	}
	
	public WebElement getOkButton() {
		System.out.println("Ok Button on End Time Page");
		return okButton;
	}

}
