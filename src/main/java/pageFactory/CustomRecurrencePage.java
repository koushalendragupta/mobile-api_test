package pageFactory;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CustomRecurrencePage {
	public CustomRecurrencePage(AndroidDriver<AndroidElement> driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(xpath="//*[@text='Custom…']")
	private WebElement customLabel;
	
	@AndroidFindBy(xpath="//*[@text='Done']")
	private WebElement doneButton;
	
	public WebElement getCustomLabel() {
		System.out.println("CustomLabel on CustomRecurrencePage");
		return customLabel;
	}
	
	public WebElement getdoneButton() {
		System.out.println("DoneButton on CustomRecurrencePage");
		return doneButton;
	}
	
	
}
