package automationFramework;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;


/*
 * A basic set of test cases
 */
public class BaseTestSuite {
	
	/* Master URLs for UDX */
	static String udxUrlLogin = "https://portals01.test.tsafe.systems/account/login";
	static String udxUrlDashboardA = "https://portals01.test.tsafe.systems/";
	static String udxUrlDashboardB = "https://portals01.test.tsafe.systems";
	
	/* Master titles for UDX */
	static String udxTitleLogin = "Login | TableSafe Portal";
	static String udxTitleDashboard = "Dashboard | TableSafe Portal";
	static String udxTitleViewRestaurant = "View Restaurant | TableSafe Portal";
	static String udxTitleRestaurantList = "Restaurants List | TableSafe Portal"; 
	
	/* Admin login credentials */
	static String udxAdminUserName = "NuBrandon";
	static String udxAdminUserPass = "Pass187#";
	/* Sudo login credentials */
	static String udxSudoUserName = "bfleming@tablesafe.com";
	static String udxSudoUserPass = "Welcome123";
	
	/* UDX Button IDs, classes, names, etc. */
	static String udxButtonSignInId = "btnSignIn";
	
	/* UDX Text fields */
	static String udxTextUsernameErrorId = "username-error";
	static String udxTextPasswordErrorId = "password-error";
	static String udxTextCredentialErrorCss = ".alert-danger";
	
	/* UDX Input fields */
	static String udxInputUsernameId = "username";
	static String udxInputPasswordId = "password";
	
	/* Utility class to keep our code short */
	static UDXUtil udxUtil = new UDXUtil();
	
	/* Webdriver name */
	static String udxWebdriverName = "nil";
	
	/* Test case number tracking */
	static int testCase = 0;
	
	public void beginTest() {
		testCase++;
		System.out.println( java.lang.Thread.currentThread().getStackTrace()[2].getMethodName() + "(): Test #" + testCase + " begin..." );
	}
	
	public void udxSetWebdriver( String webdriverName ) {
		udxWebdriverName = webdriverName;
	}
	
	public WebDriver udxBeginTest() {
		beginTest();
		
		WebDriver webdriver = null;
		
		if( udxWebdriverName.equalsIgnoreCase("firefox") )
			webdriver = new FirefoxDriver();
		else if( udxWebdriverName.equalsIgnoreCase("chrome") ) {
			System.setProperty( "webdriver.chrome.driver", "C:\\webdrivers\\chromedriver.exe" );
			webdriver = new ChromeDriver();
		}
		else if( udxWebdriverName.equalsIgnoreCase("ie") || udxWebdriverName.equalsIgnoreCase("explorer") ) {
			System.setProperty( "webdriver.ie.driver", "C:\\webdrivers\\IEDriverServer.exe" );
			webdriver = new InternetExplorerDriver();
		}
		else if( udxWebdriverName.equalsIgnoreCase("nil") || udxWebdriverName.equalsIgnoreCase("null") )
			System.out.println( java.lang.Thread.currentThread().getStackTrace()[1].getMethodName() + "(): specify a webdriver, please." );
		else
			System.out.println( java.lang.Thread.currentThread().getStackTrace()[1].getMethodName() + "(): Webdriver '" + udxWebdriverName + "' is not yet implemented!" );
		
		webdriver.get( udxUrlLogin );
		
		return webdriver;
	}

	public boolean udxQuickLogin( WebDriver webdriver, String userName, String password ) {
		/* Type a bogus username and password */
		udxUtil.utilSendKeysId( webdriver, udxInputUsernameId, userName, 500, 3 );
		udxUtil.utilSendKeysId( webdriver, udxInputPasswordId, password, 500, 3 );
		
		/* Click the login button */
		udxUtil.utilClickId( webdriver, udxButtonSignInId, 500 );
		
		/* Check page title to see if we logged in successfully */
		String title = webdriver.getTitle();
		
		/* Assume we failed if we are still at the login screen */
		if( !title.equals( udxTitleLogin ) )
			return true;
		
		System.out.println( udxUtil.utilMethodName() + "(): PASSED\n" );
		
		return false;
	}
	
	/*
	 * Runs all test cases (example)
	 */
	/*public boolean[] run() {
		boolean[] results = new boolean[] {
			udxOpenLoginURL(),
			udxLoginWithoutProperCredentialsNoUserNameOrPassword(),
			udxLoginWithoutProperCredentialsNoUserName(),
			udxLoginWithoutProperCredentialsNoPassword(),
			udxLoginWithoutProperCredentials(),
			udxLoginAdminCredentials(),
		};
		
		return results;
	}*/
}
