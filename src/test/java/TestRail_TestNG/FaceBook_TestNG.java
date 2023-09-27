package TestRail_TestNG;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.testng.Assert;
import org.testng.annotations.Test;

import Base.BaseClass;

public class FaceBook_TestNG extends BaseClass{
	
	
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.METHOD) //on method level
	public @interface TestRails {
	String id() default "none";
	}
	@TestRails(id = "1")
	@Test(priority = 1)
	public void Title_Check() {
		String FaceBOokTitle = driver.getTitle();
		Assert.assertTrue(FaceBOokTitle.equals("Facebook – log in or sign up"));
	} 
}
 