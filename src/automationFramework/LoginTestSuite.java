package automationFramework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class LoginTestSuite extends BaseTestSuite {
	
	/* 
	 * Attempt to login without proper credentials
	 */
	public boolean udxLoginWithoutProperCredentialsNoUserNameOrPassword() {
		WebDriver webdriver = udxBeginTest();
		boolean result1 = true, result2 = true;
		
		/* Start at the login screen */
		webdriver.get( udxUrlLogin );
		
		/* Click the login button */
		udxUtil.utilClickId( webdriver, udxButtonSignInId, 1000 );
		
		/* Find error message for missing username and password */
		result1 = udxUtil.utilDisplayedId( webdriver, udxTextUsernameErrorId, 2000, 3 );
		result2 = udxUtil.utilDisplayedId( webdriver, udxTextPasswordErrorId, 2000, 3 );
		
		webdriver.close();
		
		if( result1 && result2 )
			System.out.println( udxUtil.utilMethodName() + "(): PASSED\n" );
		else
			System.out.println( udxUtil.utilMethodName() + "(): FAILED\n" );
		
		return ( result1 && result2 );
	}
	
	/* 
	 * Attempt to login without proper credentials
	 */
	public boolean udxLoginWithoutProperCredentialsNoUserName() {
		WebDriver webdriver = udxBeginTest();
		boolean result = true;
		
		/* Start at the login screen */
		webdriver.get( udxUrlLogin );
		
		/* Type password, but no username */
		udxUtil.utilSendKeysId( webdriver, udxInputPasswordId, "qwerty", 1000, 3 );
		
		/* Click the login button */
		udxUtil.utilClickId( webdriver, udxButtonSignInId, 1000 );
		
		/* Find error message for missing username */
		result = udxUtil.utilDisplayedId( webdriver, udxTextUsernameErrorId, 2000, 3 );
		
		webdriver.close();
		
		if( result )
			System.out.println( udxUtil.utilMethodName() + "(): PASSED\n" );
		else
			System.out.println( udxUtil.utilMethodName() + "(): FAILED\n" );
		
		return result;
	}
	
	/* 
	 * Attempt to login without proper credentials (no password)
	 */
	public boolean udxLoginWithoutProperCredentialsNoPassword() {
		WebDriver webdriver = udxBeginTest();
		boolean result = true;
		
		/* Start at the login screen */
		webdriver.get( udxUrlLogin );
		
		/* Type username, but no password */
		udxUtil.utilSendKeysId( webdriver, udxInputUsernameId, "qwerty", 1000, 3 );
		
		/* Click the login button */
		udxUtil.utilClickId( webdriver, udxButtonSignInId, 1000 );
		
		/* Find error message for missing password */
		result = udxUtil.utilDisplayedId( webdriver, udxTextPasswordErrorId, 2000, 3 );
		
		webdriver.close();
		
		if( result )
			System.out.println( udxUtil.utilMethodName() + "(): PASSED\n" );
		else
			System.out.println( udxUtil.utilMethodName() + "(): FAILED\n" );
		
		return result;
	}
	
	
	/* 
	 * Attempt to login without proper credentials (bogus username and password)
	 */
	public boolean udxLoginWithoutProperCredentials() {
		WebDriver webdriver = udxBeginTest();
		boolean result = true;
		
		/* Start at the login screen */
		webdriver.get( udxUrlLogin );
		
		/* Type a bogus username and password */
		udxUtil.utilSendKeysId( webdriver, udxInputUsernameId, "bogusname", 1000, 3 );
		udxUtil.utilSendKeysId( webdriver, udxInputPasswordId, "boguspass", 1000, 3 );
		
		/* Click the login button */
		udxUtil.utilClickId( webdriver, udxButtonSignInId, 1000 );
		
		/* Find error message for bad credentials */
		result = udxUtil.utilDisplayedCssSelector( webdriver, udxTextCredentialErrorCss, 2000, 3 );
		
		webdriver.close();
		
		if( result )
			System.out.println( udxUtil.utilMethodName() + "(): PASSED\n" );
		else
			System.out.println( udxUtil.utilMethodName() + "(): FAILED\n" );
		
		return result;
	}
	
	/* 
	 * Attempt to login with admin credentials
	 */
	public boolean udxLoginAdminCredentials() {
		WebDriver webdriver = udxBeginTest();
		
		/* Start at the login screen */
		webdriver.get( udxUrlLogin );
		
		/* Type a bogus username and password */
		udxUtil.utilSendKeysId( webdriver, udxInputUsernameId, udxAdminUserName, 1000, 3 );
		udxUtil.utilSendKeysId( webdriver, udxInputPasswordId, udxAdminUserPass, 1000, 3 );
		
		/* Click the login button */
		udxUtil.utilClickId( webdriver, udxButtonSignInId, 1000 );
		
		/* Check page title to see if we logged in successfully */
		String title = webdriver.getTitle();
		System.out.println( udxUtil.utilMethodName() + "(): Title is: " + title );
		
		webdriver.close();
		
		/* I assume one of the other will suffice, I've gotten both so far */
		if( title.equals( udxTitleDashboard ) || title.equals( udxTitleViewRestaurant ) )
		{
			System.out.println( udxUtil.utilMethodName() + "(): PASSED\n" );
			return true;
		}
			
		System.out.println( udxUtil.utilMethodName() + "(): FAILED\n" );
		
		return false;
	}
	
	/* 
	 * Attempt to login with admin credentials, then log out
	 */
	public boolean udxLoginAdminCredentialsThenLogOut() {
		WebDriver webdriver  = udxBeginTest();
		boolean result = true;
		
		/* Start at the login screen */
		webdriver.get( udxUrlDashboardB );
		
		/* Do we need to login first? */
		String title = webdriver.getTitle();
		if( title.equals( udxTitleLogin ) ) {	
			if( !udxQuickLogin( webdriver, udxAdminUserName, udxAdminUserPass ) ) 
				result = false;
		}
		
		/* Are we at the dashboard yet? */
		title = webdriver.getTitle();
		System.out.println( udxUtil.utilMethodName() + "(): Title is: " + title );
		
		if( title.equals( udxTitleDashboard ) ) {
			udxUtil.utilClickLinkText( webdriver, "Logout", 1000 );
			
			/* Are we back at the login page? */
			title = webdriver.getTitle();
			if( !title.equals(  udxTitleLogin ) )
				result = false;
		} else result = false;
		
		webdriver.close();
		
		/* I assume one of the other will suffice, I've gotten both so far */
		if( result )
		{
			System.out.println( udxUtil.utilMethodName() + "(): PASSED\n" );
			return true;
		}
			
		System.out.println( udxUtil.utilMethodName() + "(): FAILED\n" );
		
		return false;
	}
	
	/*
	 * Runs all test cases
	 */
	public boolean[] run() {
		boolean[] results = new boolean[] {
			udxLoginWithoutProperCredentialsNoUserNameOrPassword(),
			udxLoginWithoutProperCredentialsNoUserName(),
			udxLoginWithoutProperCredentialsNoPassword(),
			udxLoginWithoutProperCredentials(),
			udxLoginAdminCredentials(),
			udxLoginAdminCredentialsThenLogOut(),
		};
		
		testCase = 0;
		
		return results;
	}
}
