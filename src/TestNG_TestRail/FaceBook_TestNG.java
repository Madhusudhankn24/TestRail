package TestNG_TestRail;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FaceBook_TestNG {

	WebDriver driver ;

	@BeforeMethod
	public void setup_Browser() {
//		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
//		driver = new ChromeDriver();
//		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
//		driver.manage().window().maximize();
//		driver.manage().deleteAllCookies();
//		driver.get("https://www.facebook.com");
		WebDriverManager.chromedriver().setup();
	}
//	@Test
//	public void VerifyPageTitleTest() {
//		String title = driver.getTitle();
//		System.out.println(title);
//	}
	@AfterMethod
	public void Close_Browser() {
		driver.quit();
	}
}
