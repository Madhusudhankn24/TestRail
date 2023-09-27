package TestRailManager;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import com.gurock.testrail.APIClient;
import com.gurock.testrail.APIException;

public class TestRailManager {
	
	public static String Test_Run_ID = "1";
	
	public static String Test_Rail_Username = "madhusudhankn07@gmail.com";
	public static String Test_Rail_Password = "Madhu@1998";
	
	public static String TestRail_URL = "https://madhusudhantestrail.testrail.io/";
	
	public static int TestCase_Pass_Status = 1;
	public static int TestCase_Fail_Status = 5;
	
	public static void addResultsForTestCase(String testcase_Id, int status, String error) {
		String testRunID = Test_Run_ID;
		APIClient client = new APIClient(TestRail_URL);
		client.setUser(Test_Rail_Username);
		client.setPassword(Test_Rail_Password);
		
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("status_id", status);
		data.put("Comment", error);
		
		try {
			client.sendPost("add_result_for_case/"+testRunID+"/"+testcase_Id, data);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (APIException e) {
			e.printStackTrace();
		}
	}
}
