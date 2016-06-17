package automationFramework;

import org.openqa.selenium.WebDriver;

public class TransactionsTestSuite extends BaseTestSuite {
	
	/*
	 * Open the UDX login page and retrieve the title
	 */
	public boolean udxSelectFirstRestaurant()
	{
		WebDriver webdriver = udxBeginTest();
		
		webdriver.get( udxUrlLogin );
		
		boolean result = true;
		
		/* Login */
		if( udxQuickLogin( webdriver, udxAdminUserName, udxAdminUserPass ) ) {
			/* Click the restaurants button */
			udxUtil.utilClickCssSelector( webdriver, "i[class='fa fa-cutlery']", 1000 );
			
			/* Now click the restaurant list button */
			//udxUtil.utilClickCssSelector( webdriver, "li.active", 1000 );
			udxUtil.utilClickLinkText( webdriver, "Restaurants List", 1000 );
			
			/* Now click the view button on the first restaurant */
			//udxUtil.utilClickCssSelector( webdriver, "i[class='btn btn-default btn-xs btn-ladda-auto ladda-button']", 1000 );
			udxUtil.utilClickLinkText( webdriver, "View", 1000 );
			
			String title = webdriver.getTitle();
			System.out.println( udxUtil.utilMethodName() + "(): Title is: " + title );
			
			if( !title.equals( udxTitleViewRestaurant ) )
				result = false;
		} else result = false;
		
		webdriver.close();
		
		System.out.println( udxUtil.utilMethodName() + "(): PASSED\n" );
		
		return result;
	}
	
	/*
	 * Runs all test cases
	 */
	public boolean[] run() {
		boolean[] results = new boolean[] {
			udxSelectFirstRestaurant(),
		};
		
		testCase = 0;
		
		return results;
	}
}
