package testrunner;



import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import com.gurock.testrail.APIClient;
import com.gurock.testrail.APIException;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import stepdefinitions.LoginSTeps;

@RunWith(Cucumber.class)
@CucumberOptions(features = "./src/test/resources/featurefiles/login.feature",
glue = "stepdefinitions",
plugin = {"pretty","json:target/cucumber-reports/Cucumber.json"})


public class Facebook_testRun {


	private static APIClient client;
	public static String Test_Run_ID = "1";

	public static Long runId;

	// Access TestRail IDs from step definitions
	LoginSTeps loginsteps = new LoginSTeps();

	/*
	 * 
	 */
	@BeforeClass
	public static void InitializeClient()  {
		APILogin();
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("include_all",true);
		data.put("name","cucumber "+System.currentTimeMillis());
		JSONObject c = null;
		try {
			c = (JSONObject)client.sendPost("add_run/"+Test_Run_ID,data);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (APIException e) {
			e.printStackTrace();
		}
		runId = (Long)c.get("id");
		System.out.println(runId);
	}
	public static APIClient APILogin() {
		client = new APIClient("https://madhusudhantestrail.testrail.io/");
		client.setUser("madhusudhankn07@gmail.com");
		client.setPassword("Madhu@1998");
		return client; 
	}
}
