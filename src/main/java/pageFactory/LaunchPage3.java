package pageFactory;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class LaunchPage3 {
	public LaunchPage3(AndroidDriver<AndroidElement> driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(xpath="//*[@text='Got it']")
	private WebElement gotItButton;

	public WebElement getGotItButton() {
		System.out.println("Launch Page 3 Got it Button Element");
		return gotItButton;
	}

}
