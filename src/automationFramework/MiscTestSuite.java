package automationFramework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class MiscTestSuite extends BaseTestSuite {
	
	/*
	 * Open the UDX login page and retrieve the title
	 */
	public boolean udxOpenLoginURL()
	{
		WebDriver webdriver = udxBeginTest();
		
		webdriver.get( udxUrlLogin );
		//webdriver.manage().window().maximize();
		
		String title = webdriver.getTitle();
		System.out.println( udxUtil.utilMethodName() + "(): Title is: " + title );
		
		webdriver.close();
		
		if( !title.equals( udxTitleLogin ) )
		{
			System.out.println( udxUtil.utilMethodName() + "(): FAILED\n" );
			return false;
		}
		
		System.out.println( udxUtil.utilMethodName() + "(): PASSED\n" );
		
		return true;
	}
	
	/*
	 * Navigate to the support page
	 */
	public boolean udxOpenSupportPage() {
		WebDriver webdriver = udxBeginTest();
		boolean result = true;
		
		/* Login */
		if( udxQuickLogin( webdriver, udxAdminUserName, udxAdminUserPass ) ) {
			
			/* Instead of clicking the support button and opening up a new window,
			 * get the URL and open it in the same window.
			 * */
			
			WebElement link = webdriver.findElement( By.linkText( "Support" ) );
			String linkLocation = link.getAttribute( "href" );
			System.out.println( udxUtil.utilMethodName() + "(): Support page: " + linkLocation );
			
			webdriver.get( linkLocation );
			
			/* Click the support button */
			//udxUtil.utilClickCssSelector( webdriver, "i[class='fa fa-question-circle']", 2000 );
			
			String title = webdriver.getTitle();
			System.out.println( udxUtil.utilMethodName() + "(): Title is: " + title );
			
			if( !title.equals( "TableSafe" ) )
				result = false;
		} else result = false;
		
		webdriver.close();
		
		/* TODO: How to deal with links opened up in new windows.  The webdriver only seems to handle the one
		 * we opened from the beginning.
		 */
		
		return result;
	}
	
	/*
	 * Runs all test cases
	 */
	public boolean[] run() {
		boolean[] results = new boolean[] {
			udxOpenLoginURL(),
			udxOpenSupportPage(),
		};
		
		testCase = 0;
		
		return results;
	}
}
