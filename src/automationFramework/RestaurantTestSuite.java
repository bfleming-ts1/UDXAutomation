package automationFramework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class RestaurantTestSuite extends BaseTestSuite {
	
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
	 * Open the UDX login page and retrieve the title
	 */
	public boolean udxCreateNweRestaurant()
	{
		WebDriver webdriver = udxBeginTest();
		
		webdriver.get( udxUrlLogin );
		
		boolean result = true;
		
		/* Login */
		if( udxQuickLogin( webdriver, udxSudoUserName, udxSudoUserPass ) ) {
			/* Click the restaurants button */
			udxUtil.utilClickCssSelector( webdriver, "i[class='fa fa-cutlery']", 1000 );
			
			/* Now click the add restaurant button */
			udxUtil.utilClickLinkText( webdriver, "Add Restaurant", 1000 );
			
			/* Fill out the required information */
			udxUtil.utilSendKeysId( webdriver, "txtRestaurantName", "American Burger Joint", 100, 3 );
			udxUtil.utilSendKeysId( webdriver, "txtReceiptEmail", "bfleming@tablesafe.com", 100, 3 );
			new Select( webdriver.findElement( By.id( "selPrimaryAddressAddressType" ) ) ).selectByVisibleText( "Physical" );
			udxUtil.utilSendKeysId( webdriver, "txtPrimaryAddressAddress", "123 Fake St", 100, 3 );
			udxUtil.utilSendKeysId( webdriver, "txtPrimaryAddressCity", "Seattle", 100, 3 );
			new Select( webdriver.findElement( By.id( "selPrimaryAddressState" ) ) ).selectByVisibleText( "Washington" );
			udxUtil.utilSendKeysId( webdriver, "txtPrimaryAddressZipCode", "98199", 100, 3 );
			udxUtil.utilSendKeysId( webdriver, "txtPrimaryContactName", "Jim Bob", 100, 3 );
			udxUtil.utilSendKeysId( webdriver, "txtPrimaryContactPhone", "2065551234", 100, 3 );
			udxUtil.utilSendKeysId( webdriver, "txtPrimaryContactEmail", "bfleming@tablesafe.com", 100, 3 );
			new Select( webdriver.findElement( By.id( "selNetworkConfigurationTimeZone" ) ) ).selectByVisibleText( "US/Pacific" );
			
			/* Click the "Create Restaurant" button */
			/* TODO: Find out why selenium's click command isn't working on this button! */
//			udxUtil.utilClickId( webdriver, "btnCreateRestaurant", 500 );
			webdriver.findElement( By.id( "btnCreateRestaurant") ).click();
			//udxUtil.utilClickLinkText( webdriver, "Create Restaurant", 500 );
			
			String title = webdriver.getTitle();
			System.out.println( udxUtil.utilMethodName() + "(): Title is: " + title );
			
			if( !title.equals( udxTitleViewRestaurant ) )
				result = false;
		} else result = false;
		
		webdriver.close();
		
		if( result )
			System.out.println( udxUtil.utilMethodName() + "(): PASSED\n" );
		else
			System.out.println( udxUtil.utilMethodName() + "(): FAILED\n" );
		
		return result;
	}
	
	public boolean udxViewRestaurant()
	{
		WebDriver webdriver = udxBeginTest();
		
		webdriver.get( udxUrlLogin );
		
		boolean result = true;
		
		/* Login */
		if( udxQuickLogin( webdriver, udxAdminUserName, udxAdminUserPass ) ) {
			/* Click the restaurants button */
			udxUtil.utilClickCssSelector( webdriver, "i[class='fa fa-cutlery']", 1000 );
			
			/* Now click the add restaurant button */
			udxUtil.utilClickLinkText( webdriver, "Restaurants List", 1000 );
			
			WebElement link = webdriver.findElement( By.linkText( "View" ) );
			String linkLocation = link.getAttribute( "href" );
			//System.out.println( "Link Location: " + linkLocation );
			
			webdriver.get( linkLocation );
		} else result = false;
		
		webdriver.close();
		
		if( result )
			System.out.println( udxUtil.utilMethodName() + "(): PASSED\n" );
		else
			System.out.println( udxUtil.utilMethodName() + "(): FAILED\n" );
		
		return result;
	}
	
	public boolean udxSearchRestaurant()
	{
		WebDriver webdriver = udxBeginTest();
		
		webdriver.get( udxUrlLogin );
		
		boolean result = true;
		
		/* Login */
		if( udxQuickLogin( webdriver, udxSudoUserName, udxSudoUserPass ) ) {
			String title = webdriver.getTitle();
			
			if( !title.equals( udxTitleRestaurantList ) || !webdriver.getPageSource().contains( "Restaurants List" ) ) {
				
				/* Click the restaurants button */
				udxUtil.utilClickCssSelector( webdriver, "i[class='fa fa-cutlery']", 1000 );
				
				/* Now click the add restaurant button */
				udxUtil.utilClickLinkText( webdriver, "Restaurants List", 1000 );
			}
			
			/* Search for the A-1 Steakhouse restaurant by inputting the text into the search bar */
			udxUtil.utilSendKeysCssSelector( webdriver, "input.form-control.input-sm", "A-1", 1000, 3 );
			
			/* If the table is empty, then we failed to find that restaurant */
			if( udxUtil.utilDisplayedClass( webdriver, "dataTables_empty", 100, 3 ) )
				result = false;
			
			result = udxUtil.utilDisplayedClass( webdriver, "sorting_1", 100, 3 );
			
			
		} else result = false;
		
		webdriver.close();
		
		if( result )
			System.out.println( udxUtil.utilMethodName() + "(): PASSED\n" );
		else
			System.out.println( udxUtil.utilMethodName() + "(): FAILED\n" );
		
		return result;
	}
	
	public boolean udxRestaurantTips()
	{
		WebDriver webdriver = udxBeginTest();
		
		webdriver.get( udxUrlLogin );
		
		boolean result = true;
		
		/* Login */
		if( udxQuickLogin( webdriver, udxSudoUserName, udxSudoUserPass ) ) {
			String title = webdriver.getTitle();
			
			if( !title.equals( udxTitleRestaurantList ) || !webdriver.getPageSource().contains( "Restaurants List" ) ) {
				
				/* Click the restaurants button */
				udxUtil.utilClickCssSelector( webdriver, "i[class='fa fa-cutlery']", 1000 );
				
				/* Now click the add restaurant button */
				udxUtil.utilClickLinkText( webdriver, "Restaurants List", 1000 );
			}
			
			/* Search for the A-1 Steakhouse restaurant by inputting the text into the search bar */
			udxUtil.utilSendKeysCssSelector( webdriver, "input.form-control.input-sm", "A-1", 1000, 3 );
			
			/* If the table is empty, then we failed to find that restaurant */
			if( udxUtil.utilDisplayedClass( webdriver, "dataTables_empty", 100, 3 ) )
				result = false;
			
			result = udxUtil.utilDisplayedClass( webdriver, "sorting_1", 100, 3 );
			
			
			/* Click the A-1 Steakhouse restaurant */
			//udxUtil.utilClickCssSelector( webdriver, "a[class='btn btn-default btn-xs btn-ladda-auto ladda-button']", 1000 );
			udxUtil.utilClickLinkText( webdriver, "Edit", 500 );
			
			/* Input negative values for the tips */
			udxUtil.utilSendKeysId( webdriver, "txtTipConfigurationTipTier1", "-1", 1000, 3 );
			udxUtil.utilSendKeysId( webdriver, "txtTipConfigurationTipTier2", "-1", 1000, 3 );
			udxUtil.utilSendKeysId( webdriver, "txtTipConfigurationTipTier3", "-1", 1000, 3 );
			udxUtil.utilSendKeysId( webdriver, "txtTipConfigurationForcedGratuityAmount", "-1", 1000, 3 );
			
			/* Click update button */
			udxUtil.utilClickId( webdriver, "btnUpdateRestaurant", 1000 );
			
			/* Did we get an error message for each field? */
			if( !udxUtil.utilDisplayedId( webdriver, "txtTipConfigurationTipTier1-error", 1000, 3 ) ) result = false;
			if( !udxUtil.utilDisplayedId( webdriver, "txtTipConfigurationTipTier2-error", 1000, 3 ) ) result = false;
			if( !udxUtil.utilDisplayedId( webdriver, "txtTipConfigurationTipTier3-error", 1000, 3 ) ) result = false;
			if( !udxUtil.utilDisplayedId( webdriver, "txtTipConfigurationForcedGratuityAmount-error", 1000, 3 ) ) result = false;
			
			/* So far so good.  Now, let's see if we got the right error message shown. */
			String error = "Please enter a value greater than or equal to 1.";
			if( !error.equals( webdriver.findElement( By.id( "txtTipConfigurationTipTier1-error" ) ).getText() ) ) result = false;
			if( !error.equals( webdriver.findElement( By.id( "txtTipConfigurationTipTier2-error" ) ).getText() ) ) result = false;
			if( !error.equals( webdriver.findElement( By.id( "txtTipConfigurationTipTier3-error" ) ).getText() ) ) result = false;
			if( !error.equals( webdriver.findElement( By.id( "txtTipConfigurationForcedGratuityAmount" ) ).getText() ) ) result = false;
			
			
			/* Now let's input invalid numbers for the tips */
			udxUtil.utilSendKeysId( webdriver, "txtTipConfigurationTipTier1", "60-1", 1000, 3 );
			udxUtil.utilSendKeysId( webdriver, "txtTipConfigurationTipTier2", "60-1", 1000, 3 );
			udxUtil.utilSendKeysId( webdriver, "txtTipConfigurationTipTier3", "60-1", 1000, 3 );
			udxUtil.utilSendKeysId( webdriver, "txtTipConfigurationForcedGratuityAmount", "60-1", 1000, 3 );
			
			/* Click update button */
			udxUtil.utilClickId( webdriver, "btnUpdateRestaurant", 1000 );
			
			/* Did we get an error message for each field? */
			if( !udxUtil.utilDisplayedId( webdriver, "txtTipConfigurationTipTier1-error", 1000, 3 ) ) result = false;
			if( !udxUtil.utilDisplayedId( webdriver, "txtTipConfigurationTipTier2-error", 1000, 3 ) ) result = false;
			if( !udxUtil.utilDisplayedId( webdriver, "txtTipConfigurationTipTier3-error", 1000, 3 ) ) result = false;
			if( !udxUtil.utilDisplayedId( webdriver, "txtTipConfigurationForcedGratuityAmount-error", 1000, 3 ) ) result = false;
			
			/* So far so good.  Now, let's see if we got the right error message shown. */
			String error2 = "Please enter a valid number.";
			if( !error2.equals( webdriver.findElement( By.id( "txtTipConfigurationTipTier1-error" ) ).getText() ) ) result = false;
			if( !error2.equals( webdriver.findElement( By.id( "txtTipConfigurationTipTier2-error" ) ).getText() ) ) result = false;
			if( !error2.equals( webdriver.findElement( By.id( "txtTipConfigurationTipTier3-error" ) ).getText() ) ) result = false;
			if( !error2.equals( webdriver.findElement( By.id( "txtTipConfigurationForcedGratuityAmount" ) ).getText() ) ) result = false;
			
			
			/* And now let's input letters for the tips */
			udxUtil.utilSendKeysId( webdriver, "txtTipConfigurationTipTier1", "E", 1000, 3 );
			udxUtil.utilSendKeysId( webdriver, "txtTipConfigurationTipTier2", "E", 1000, 3 );
			udxUtil.utilSendKeysId( webdriver, "txtTipConfigurationTipTier3", "E", 1000, 3 );
			udxUtil.utilSendKeysId( webdriver, "txtTipConfigurationForcedGratuityAmount", "E", 1000, 3 );
			
			/* Click update button */
			udxUtil.utilClickId( webdriver, "btnUpdateRestaurant", 1000 );
			
			/* Did we get an error message for each field? */
			if( !udxUtil.utilDisplayedId( webdriver, "txtTipConfigurationTipTier1-error", 1000, 3 ) ) result = false;
			if( !udxUtil.utilDisplayedId( webdriver, "txtTipConfigurationTipTier2-error", 1000, 3 ) ) result = false;
			if( !udxUtil.utilDisplayedId( webdriver, "txtTipConfigurationTipTier3-error", 1000, 3 ) ) result = false;
			if( !udxUtil.utilDisplayedId( webdriver, "txtTipConfigurationForcedGratuityAmount-error", 1000, 3 ) ) result = false;
			
			/* So far so good.  Now, let's see if we got the right error message shown. */
			String error3 = "Please enter a valid number.";
			if( !error3.equals( webdriver.findElement( By.id( "txtTipConfigurationTipTier1-error" ) ).getText() ) ) result = false;
			if( !error3.equals( webdriver.findElement( By.id( "txtTipConfigurationTipTier2-error" ) ).getText() ) ) result = false;
			if( !error3.equals( webdriver.findElement( By.id( "txtTipConfigurationTipTier3-error" ) ).getText() ) ) result = false;
			if( !error3.equals( webdriver.findElement( By.id( "txtTipConfigurationForcedGratuityAmount" ) ).getText() ) ) result = false;
			
			
			udxUtil.utilSendKeysId( webdriver, "txtTipConfigurationTipTier1", "\b", 1000, 3 );
			udxUtil.utilSendKeysId( webdriver, "txtTipConfigurationTipTier2", "\b", 1000, 3 );
			udxUtil.utilSendKeysId( webdriver, "txtTipConfigurationTipTier3", "\b", 1000, 3 );
			udxUtil.utilSendKeysId( webdriver, "txtTipConfigurationForcedGratuityAmount", "\b", 1000, 3 );
			
			/* Click update button */
			udxUtil.utilClickId( webdriver, "btnUpdateRestaurant", 1000 );
			
			/* Did we get an error message for each field? */
			if( !udxUtil.utilDisplayedId( webdriver, "txtTipConfigurationTipTier1-error", 1000, 3 ) ) result = false;
			/*if( !udxUtil.utilDisplayedId( webdriver, "txtTipConfigurationTipTier2-error", 1000, 3 ) ) result = false;
			if( !udxUtil.utilDisplayedId( webdriver, "txtTipConfigurationTipTier3-error", 1000, 3 ) ) result = false;
			if( !udxUtil.utilDisplayedId( webdriver, "txtTipConfigurationForcedGratuityAmount-error", 1000, 3 ) ) result = false;*/
			
			/* So far so good.  Now, let's see if we got the right error message shown. */
			String error4 = "This field is required.";
			if( !error4.equals( webdriver.findElement( By.id( "txtTipConfigurationTipTier1-error" ) ).getText() ) ) result = false;
			/*if( !error4.equals( webdriver.findElement( By.id( "txtTipConfigurationTipTier2-error" ) ).getText() ) ) result = false;
			if( !error4.equals( webdriver.findElement( By.id( "txtTipConfigurationTipTier3-error" ) ).getText() ) ) result = false;
			if( !error4.equals( webdriver.findElement( By.id( "txtTipConfigurationForcedGratuityAmount" ) ).getText() ) ) result = false;*/
		} else result = false;
		
		//webdriver.close();
		
		if( result )
			System.out.println( udxUtil.utilMethodName() + "(): PASSED\n" );
		else
			System.out.println( udxUtil.utilMethodName() + "(): FAILED\n" );
		
		return result;
	}
	
	public boolean udxCreateRestaurantSurvey()
	{
		WebDriver webdriver = udxBeginTest();
		
		webdriver.get( udxUrlLogin );
		
		boolean result = true;
		
		/* Login */
		if( udxQuickLogin( webdriver, udxSudoUserName, udxSudoUserPass ) ) {
			String title = webdriver.getTitle();
			
			if( !title.equals( udxTitleRestaurantList ) || !webdriver.getPageSource().contains( "Restaurants List" ) ) {
				
				/* Click the restaurants button */
				udxUtil.utilClickCssSelector( webdriver, "i[class='fa fa-cutlery']", 1000 );
				
				/* Now click the add restaurant button */
				udxUtil.utilClickLinkText( webdriver, "Restaurants List", 1000 );
			}
			
			/* Search for the A-1 Steakhouse restaurant by inputting the text into the search bar */
			udxUtil.utilSendKeysCssSelector( webdriver, "input.form-control.input-sm", "A-1", 1000, 3 );
			
			/* If the table is empty, then we failed to find that restaurant */
			if( udxUtil.utilDisplayedClass( webdriver, "dataTables_empty", 100, 3 ) )
				result = false;
			
			result = udxUtil.utilDisplayedClass( webdriver, "sorting_1", 100, 3 );
			
			/* Click the A-1 Steakhouse restaurant, this will select a restaurant */
			udxUtil.utilClickLinkText( webdriver, "View", 500 );
			
			/* Go to A-1 Steakhouse -> Surveys -> Add Survey */
			udxUtil.utilClickLinkText( webdriver, "Surveys", 500 );
			udxUtil.utilClickLinkText( webdriver, "Add Survey", 500 );
			
			/* Add a question */
			udxUtil.utilSendKeysId( webdriver, "txtQuestionText", "Was the steak good?", 1000, 3 );
			/* Add answers: yes or no */
			udxUtil.utilSendKeysId( webdriver, "txtQuestionChoice1", "Yes", 1000, 3 );
			udxUtil.utilSendKeysId( webdriver, "txtQuestionChoice2", "No", 1000, 3 );
			
			/* Click the "add question" button */
			udxUtil.utilClickId( webdriver, "btnAddQuestion", 1000 );
			/* Click the next step 2 button */
			udxUtil.utilClickId( webdriver, "btnGotoStep2", 1000 );
			
			/* Input survey title */
			udxUtil.utilSendKeysId( webdriver, "txtSurveyTitle", "Steak feedback", 1000, 3 );
			/* Click the next step 3 button */
			udxUtil.utilClickId( webdriver, "btnGotoStep3", 1000 );
			
			/* Just click complete for now */
			udxUtil.utilClickId( webdriver, "btnGotoComplete", 1000 );
			
			/* Search for the following string to verify success */
			if( !udxUtil.utilDisplayedCssSelector( webdriver, "div.alert.alert-success.alert-dismissible", 500, 3 ) )
				result = false;
			
			/* Now, search for the survey we just created */
			udxUtil.utilSendKeysCssSelector( webdriver, "input.form-control.input-sm", "Steak", 1000, 3 );
			
			/* View the survey */
			udxUtil.utilClickLinkText( webdriver, "View", 500 );
		} else result = false;
		
		webdriver.close();
		
		if( result )
			System.out.println( udxUtil.utilMethodName() + "(): PASSED\n" );
		else
			System.out.println( udxUtil.utilMethodName() + "(): FAILED\n" );
		
		return result;
	}
	
	public boolean udxDeleteRestaurantSurvey()
	{
		WebDriver webdriver = udxBeginTest();
		
		webdriver.get( udxUrlLogin );
		
		boolean result = true;
		
		/* Login */
		if( udxQuickLogin( webdriver, udxSudoUserName, udxSudoUserPass ) ) {
			String title = webdriver.getTitle();
			
			if( !title.equals( udxTitleRestaurantList ) || !webdriver.getPageSource().contains( "Restaurants List" ) ) {
				
				/* Click the restaurants button */
				udxUtil.utilClickCssSelector( webdriver, "i[class='fa fa-cutlery']", 1000 );
				
				/* Now click the add restaurant button */
				udxUtil.utilClickLinkText( webdriver, "Restaurants List", 1000 );
			}
			
			/* Search for the A-1 Steakhouse restaurant by inputting the text into the search bar */
			udxUtil.utilSendKeysCssSelector( webdriver, "input.form-control.input-sm", "A-1", 1000, 3 );
			
			/* If the table is empty, then we failed to find that restaurant */
			if( udxUtil.utilDisplayedClass( webdriver, "dataTables_empty", 100, 3 ) )
				result = false;
			
			result = udxUtil.utilDisplayedClass( webdriver, "sorting_1", 100, 3 );
			
			/* Click the A-1 Steakhouse restaurant, this will select a restaurant */
			udxUtil.utilClickLinkText( webdriver, "View", 500 );
			
			/* Go to A-1 Steakhouse -> Surveys -> Scheduled Surveys */
			udxUtil.utilClickLinkText( webdriver, "Surveys", 500 );
			udxUtil.utilClickLinkText( webdriver, "Scheduled Surveys", 500 );
			
			/* Search for the steak feedback survey */
			udxUtil.utilSendKeysCssSelector( webdriver, "input.form-control.input-sm", "Steak feedback", 1000, 3 );
			
			/* Delete the survey */
			udxUtil.utilClickLinkText( webdriver, "Delete", 500 );
			/* Click OK */
			udxUtil.utilClickCssSelector( webdriver, "button.btn.btn-primary", 1000 );
			//udxUtil.utilClickLinkText( webdriver, "OK", 1000 );
			
			/* Did we get a messaging that we successfully deleted the survey? */
			result = udxUtil.utilDisplayedCssSelector( webdriver, "div.alert.alert-success.alert-dismissable", 1000, 3 );
		} else result = false;
		
		webdriver.close();
		
		if( result )
			System.out.println( udxUtil.utilMethodName() + "(): PASSED\n" );
		else
			System.out.println( udxUtil.utilMethodName() + "(): FAILED\n" );
		
		return result;
	}
	
	/*
	 * Runs all test cases
	 */
	public boolean[] run() {
		boolean[] results = new boolean[] {
			//udxSelectFirstRestaurant(),
			//udxCreateNweRestaurant(), 	/* BROKEN */
			//udxViewRestaurant(),
			//udxSearchRestaurant(),
			//udxRestaurantTips(),		/* BROKEN */
			//udxCreateRestaurantSurvey(),
			udxDeleteRestaurantSurvey(),
		};
		
		testCase = 0;
		
		return results;
	}
}
