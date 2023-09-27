package stepdefinitions;


import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.gurock.testrail.APIClient;
import com.gurock.testrail.APIException;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.*;
import testrunner.Facebook_testRun;

public class LoginSTeps   {

	static APIClient client;
	public static int TestCase_Pass_Status = 1;
	public static int TestCase_Fail_Status = 5; 
	
	public static String numericValue;
	public static String testcaseId;
	public static Long runId = Facebook_testRun.runId;

	@Before 
	public void BeforeScenario(Scenario scenario) {
		extractTestRailId(scenario);
		testcaseId = numericValue;
		System.out.println("TestRail ID: " + testcaseId);    
	}
	private String extractTestRailId(Scenario scenario) {
		List<String> sourceTagNames = (List<String>) scenario.getSourceTagNames();
		for(String tag : sourceTagNames) {
 			Pattern pattern = Pattern.compile("\\d+");
			Matcher matcher = pattern.matcher(tag);
			while (matcher.find()) {
				numericValue = matcher.group();
				System.out.println("Numeric Value: " + numericValue);
			}
		}
		return null; // Return null if no TestRail ID is found
	}
	@After
	public static void Afterclass(Scenario scenario) {
		UpdateTestResults(scenario);
	}
	public static void UpdateTestResults(Scenario scenario) {
		client = Facebook_testRun.APILogin();
		String testcaseId = LoginSTeps.testcaseId;
		Map<String, Object> data = new HashMap<String, Object>();
		if(scenario.isFailed()) {
			data.put("status_id",TestCase_Fail_Status);
		}
		else{
			data.put("status_id",TestCase_Pass_Status);
		}
		try {
			client.sendPost("add_result_for_case/"+runId+"/"+testcaseId,data);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (APIException e) {
			e.printStackTrace();
		}
	}
	@Given("Launch Application")
	public void launch_applications() {
	}
	@When("User enters Username")
	public void user_enters_username() {
	}
	@When("User enters password")
	public void user_enters_password() {
	}
	@When("Click on Login button")
	public void click_on_login_button() {
	}
	@Then("Home page should display")
	public void home_page_should_display() {
	}
}
