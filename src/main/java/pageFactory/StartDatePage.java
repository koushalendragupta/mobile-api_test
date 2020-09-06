package pageFactory;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class StartDatePage {
	public StartDatePage(AndroidDriver<AndroidElement> driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

//	@AndroidFindBy(id = "com.google.android.calendar:id/floating_action_button")
//	private WebElement actionButton;
	
	@AndroidFindBy(xpath="//*[@text='OK']")
	private WebElement okButton;

	public WebElement getOkButton() {
		System.out.println("Ok Button on Start Date Page");
		return okButton;
	}

}
