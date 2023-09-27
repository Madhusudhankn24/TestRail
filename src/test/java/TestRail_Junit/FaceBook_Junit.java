package TestRail_Junit;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.junit.Assert;
import org.junit.Test;

import BAse.Junit_BaseClass;

public class FaceBook_Junit extends Junit_BaseClass{

	
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.METHOD)
	public @interface TestRails {
		String id() default "none";
	}
	
	@TestRails(id = "1")
	@Test
	public void Title_Verification() {
		String FaceBOokTitle = driver.getTitle();
		Assert.assertTrue(FaceBOokTitle.equals("Facebook – log in or sign up"));
	}
	@TestRails(id = "2")
	@Test
	public void Title_Verification1() {
		String FaceBOokTitle = driver.getTitle();
		Assert.assertTrue(FaceBOokTitle.equals("Facebook – log in or sign up"));
	}
	/*
	*/
}
