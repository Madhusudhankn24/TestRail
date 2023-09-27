 package BAse;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;
import org.junit.runners.model.TestClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.gurock.testrail.APIClient;
import com.gurock.testrail.APIException;

import TestRail_Junit.FaceBook_Junit.TestRails;
import io.github.bonigarcia.wdm.WebDriverManager;


public class Junit_BaseClass {

	protected static WebDriver driver;

	public static String Test_Run_ID = "1";

	public static String Test_Rail_Username = "madhusudhankn07@gmail.com";
	public static String Test_Rail_Password = "Madhu@1998";

	public static String TestRail_URL = "https://madhusudhantestrail.testrail.io/";

	public static int TestCase_Pass_Status = 1;
	public static int TestCase_Fail_Status = 5;

	public static APIClient client;
	public static Long runId;
	private String caseId;

	@BeforeClass
	public static void createSuite() throws MalformedURLException, IOException, APIException {
		/*
		 * Login to API
		 */
		client = new APIClient(TestRail_URL);
		client.setUser(Test_Rail_Username);
		client.setPassword(Test_Rail_Password);
		/*
		 * Create Test Run
		 */
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("include_all",true);
		data.put("name","Test Run "+System.currentTimeMillis());
		JSONObject c = (JSONObject)client.sendPost("add_run/"+Test_Run_ID,data);
		//Extract Test Run Id
		runId = (Long)c.get("id");
	}
	@Before
	public void beforeTest() throws NoSuchMethodException, SecurityException {
		LaunchApplication();
		TestClass testclass = new TestClass(getClass());
		FrameworkMethod frameworkmethod = testclass.getAnnotatedMethods(Test.class).get(0);
		Method m = getClass().getMethod(frameworkmethod.getName());
		if (m.isAnnotationPresent(TestRails.class)) {
			TestRails ta = m.getAnnotation(TestRails.class);
			caseId = ta.id();
			System.out.println(caseId);
		}
	}
	public void LaunchApplication() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.facebook.com/");
	}
	@After
	public void TearDown() {
		driver.quit();
	}
	@Rule
	public final TestRule watchman = new TestWatcher() {
		Map<String, Object> data = new HashMap<String, Object>();
		@Override
		public Statement apply(Statement base, Description description) {
			return super.apply(base, description);
		}
		@Override
		protected void succeeded(Description description) {
			data.put("status_id", TestCase_Pass_Status);
		}
		// This method gets invoked if the test fails for any reason:
		@Override
		protected void failed(Throwable e, Description description) {
			data.put("status_id", TestCase_Fail_Status);
		}
		// This method gets called when the test finishes, regardless of status
		// If the test fails, this will be called after the method above
		@Override
		protected void finished(Description description) {
			try {
				client.sendPost("add_result_for_case/" + runId + "/" + caseId, data);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (APIException e) {
				e.printStackTrace();
			}
		}
		;
	};
}
