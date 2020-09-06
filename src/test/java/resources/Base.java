package resources;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.URL;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class Base {
	public static AppiumDriverLocalService service;
	public static AndroidDriver<AndroidElement> driver;

	public static AndroidDriver<AndroidElement> capabilities() throws MalformedURLException {
		DesiredCapabilities cp = new DesiredCapabilities();
		cp.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Device");
		cp.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
		cp.setCapability("appPackage", "com.google.android.calendar");
		cp.setCapability("appActivity", "com.android.calendar.AllInOneActivity");
		driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), cp);
		return driver;
	}

	public AppiumDriverLocalService startServer() {
		boolean flag = checkIfServerIsRunnning(4723);
		if (!flag) {

			service = AppiumDriverLocalService.buildDefaultService();
			service.start();
		}
		return service;

	}

	public static boolean checkIfServerIsRunnning(int port) {

		boolean isServerRunning = false;
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(port);

			serverSocket.close();
		} catch (IOException e) {
			isServerRunning = true;
		} finally {
			serverSocket = null;
		}
		return isServerRunning;
	}

}