package stepDefinations;

import java.io.IOException;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class BeforAfterHooks{
	
	@Before("@UIMobileTest")
	public void beforeUIMobileTest() throws IOException, InterruptedException{
		
		System.out.println("Starting the Appium Server");
		Runtime.getRuntime().exec(System.getProperty("user.dir")+"\\src\\test\\java\\resources\\startAppium.bat");
		Thread.sleep(6000);
	}
	
	@After("@UIMobileTest")
	public void afterUIMobileTest() throws IOException, InterruptedException{	
		Runtime.getRuntime().exec(System.getProperty("user.dir")+"\\src\\test\\java\\resources\\stopAppium.bat");
		Thread.sleep(3000);
		System.out.println("Stopping the Appium Server");
		
	}

}
