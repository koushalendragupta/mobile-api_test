package pageFactory;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class LaunchPage2 {
	public LaunchPage2(AndroidDriver<AndroidElement> driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(id = "com.google.android.calendar:id/next_arrow_touch")
	private WebElement nextArrorw;

	public WebElement getNextArrorw() {
		System.out.println("Launch Page 2 Next Arrow Element");
		return nextArrorw;
	}

}
