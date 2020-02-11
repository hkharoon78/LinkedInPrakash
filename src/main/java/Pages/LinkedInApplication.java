package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.Common;

public class LinkedInApplication {

	private WebDriver browser;
	
	public LinkedInApplication() {
		try {
			browser=getDriver(Common.getBrowser());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public LoginPage openLinkedInApplication() {
		try {
			browser.get(Common.getUrl());
			browser.manage().window().maximize();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new LoginPage(browser);
	}
	
	
	
	
	public  WebDriver getDriver(String browserName) {
		WebDriver driver = null;
		if (browserName.equalsIgnoreCase("firefox")) {

			//System.setProperty("webdriver.gecko.driver", "C://Apps//drivers//geckodriver.exe");	
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();

		}
		if(browserName.equalsIgnoreCase("chrome")){
			//System.setProperty("webdriver.chrome.driver", "/home/haroon/Documents/gopi/v5/source code/LinkedInPrakashv5/LinkedInPrakash/src/main/java/utilities/chromedriver");
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
		}
		if(browserName.equalsIgnoreCase("safari")){			
			driver=new SafariDriver();
		}
		return driver;
	}
}
