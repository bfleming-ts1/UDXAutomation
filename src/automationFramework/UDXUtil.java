package automationFramework;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class UDXUtil {
	/* 
	 * Just a sleep function 
	 */
	public void utilSleep( int ms )  {
		try {
		    Thread.sleep(ms);
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
	}
	
	
	/*
	 * Returns the name of the method that called this method
	 */
	public String utilMethodName() {
		return java.lang.Thread.currentThread().getStackTrace()[2].getMethodName();
	}
	
	
	/* 
	 * Clicks an element (id) 
	 */
	void utilClickId( WebDriver webdriver, String id, int delay ) {
		String caller = java.lang.Thread.currentThread().getStackTrace()[2].getMethodName();
		
		System.out.println( caller + "(): clicking id " + id + " (delay: " + delay + ")..." );
		
		utilSleep(delay);
		
		try {
			webdriver.findElement( By.id( id ) ).click();
		} catch( NoSuchElementException e ) {
			System.out.println( caller + "(): could not find id '" + id + "'" );
		}
	}
	
	/* 
	 * Clicks an element (id) 
	 */
	void utilClickClass( WebDriver webdriver, String _class, int delay ) {
		String caller = java.lang.Thread.currentThread().getStackTrace()[2].getMethodName();
		
		System.out.println( caller + "(): clicking class " + _class + " (delay: " + delay + ")..." );
		
		utilSleep(delay);
		
		try {
			webdriver.findElement( By.className( _class ) ).click();
		} catch( NoSuchElementException e ) {
			System.out.println( caller + "(): could not find class '" + _class + "'" );
		}
	}
	
	/* 
	 * Clicks an element (css) 
	 */
	void utilClickCssSelector( WebDriver webdriver, String css, int delay ) {
		String caller = java.lang.Thread.currentThread().getStackTrace()[2].getMethodName();
		
		System.out.println( caller + "(): clicking CSS selector " + css + " (delay: " + delay + ")..." );
		
		utilSleep(delay);
		
		try {
			webdriver.findElement( By.cssSelector( css ) ).click();
		} catch( NoSuchElementException e ) {
			System.out.println( caller + "(): could not find css selector '" + css + "'" );
		}
	}
	
	/* 
	 * Clicks an element (css) 
	 */
	void utilClickLinkText( WebDriver webdriver, String linkText, int delay ) {
		String caller = java.lang.Thread.currentThread().getStackTrace()[2].getMethodName();
		
		System.out.println( caller + "(): clicking link text " + linkText + " (delay: " + delay + ")..." );
		
		utilSleep(delay);
		
		try {
			webdriver.findElement( By.linkText( linkText ) ).click();
		} catch( NoSuchElementException e ) {
			System.out.println( caller + "(): could not find link text '" + linkText + "'" );
		}
	}
	
	/*
	 * Returns true if this id is currently being displayed
	 */
	boolean utilDisplayedId( WebDriver webdriver, String id, int delay, int retryCount ) {
		int retries = 0;
		boolean result = true;
		String caller = java.lang.Thread.currentThread().getStackTrace()[2].getMethodName();
		
		System.out.println( caller + "(): verifying id " + id + " is currently displayed (delay: " + delay + ")..." );
		
		while( retries < retryCount ) {
			utilSleep(delay);
			
			try {
				result = webdriver.findElement( By.id( id ) ).isDisplayed();
			} catch( NoSuchElementException e ) {
				result = false;
			}
			
			/* Retry */
			if( result == false ) {
				retries++;
				System.out.println( "Retry #" + retries + "..." );
				utilSleep(delay);
			} else {
				System.out.println( "^ SUCCESS" );
				break;
			}
		}
		
		if( !result )
			System.out.println( "^ FAILED" );
		
		return result;
	}
	
	
	/*
	 * Returns true if this class is currently being displayed
	 */
	boolean utilDisplayedClass( WebDriver webdriver, String _class, int delay, int retryCount ) {
		int retries = 0;
		boolean result = true;
		String caller = java.lang.Thread.currentThread().getStackTrace()[2].getMethodName();
		
		System.out.println( caller + "(): verifying class " + _class + " is currently displayed (delay: " + delay + ")..." );
		
		while( retries < retryCount ) {
			utilSleep(delay);
			
			try {
				result = webdriver.findElement( By.className( _class ) ).isDisplayed();
			} catch( NoSuchElementException e ) {
				result = false;
			}
			
			/* Retry */
			if( result == false ) {
				retries++;
				System.out.println( "Retry #" + retries + "..." );
				utilSleep(delay);
			} else {
				System.out.println( "^ SUCCESS" );
				break;
			}
		}
		
		if( !result )
			System.out.println( "^ FAILED" );
		
		return result;
	}
	
	
	/*
	 * Returns true if the CSS selector is displayed
	 */
	boolean utilDisplayedCssSelector( WebDriver webdriver, String css, int delay, int retryCount ) {
		int retries = 0;
		boolean result = true;
		String caller = java.lang.Thread.currentThread().getStackTrace()[2].getMethodName();
		
		System.out.println( caller + "(): verifying css selector " + css + " is currently displayed (delay: " + delay + ")..." );
		
		while( retries < retryCount ) {
			utilSleep(delay);
			
			try {
				result = webdriver.findElement( By.cssSelector( css ) ).isDisplayed();
			} catch( NoSuchElementException e ) {
				result = false;
			}
			
			/* Retry */
			if( result == false ) {
				retries++;
				System.out.println( "Retry #" + retries + "..." );
				utilSleep(delay);
			} else {
				System.out.println( "^ SUCCESS" );
				break;
			}
		}
		
		if( !result )
			System.out.println( "^ FAILED" );
		
		return result;
	}
	
	
	/*
	 * Sends keystrokes to the id, assuming it's an input field
	 */
	boolean utilSendKeysId( WebDriver webdriver, String id, String keys, int delay, int retryCount ) {
		int retries = 0;
		boolean result = true;
		String caller = java.lang.Thread.currentThread().getStackTrace()[2].getMethodName();
		CharSequence seq = keys;
		
		System.out.println( caller + "(): entering keys to id " + id + " (delay: " + delay + ")..." );
		
		while( retries < retryCount ) {
			utilSleep(delay);
			
			try {
				result = webdriver.findElement( By.id( id ) ).isDisplayed();
			} catch( NoSuchElementException e ) {
				result = false;
			}
			
			/* Retry */
			if( result == false ) {
				retries++;
				System.out.println( "Retry #" + retries + "..." );
				utilSleep(delay);
			} else {
				System.out.println( "^ SUCCESS" );
				break;
			}
		}
		
		if( !result )
			System.out.println( "^ FAILED" );
		
		webdriver.findElement( By.id( id ) ).sendKeys(Keys.chord(Keys.CONTROL, "a"), seq);
		//webdriver.findElement( By.id( id ) ).sendKeys(seq);
		
		return result;
	}
	
	/*
	 * Sends keystrokes to the id, assuming it's an input field
	 */
	boolean utilSendKeysCssSelector( WebDriver webdriver, String css, String keys, int delay, int retryCount ) {
		int retries = 0;
		boolean result = true;
		String caller = java.lang.Thread.currentThread().getStackTrace()[2].getMethodName();
		CharSequence seq = keys;
		
		System.out.println( caller + "(): entering keys to CSS selector " + css + " (delay: " + delay + ")..." );
		
		while( retries < retryCount ) {
			utilSleep(delay);
			
			try {
				result = webdriver.findElement( By.cssSelector( css ) ).isDisplayed();
			} catch( NoSuchElementException e ) {
				result = false;
			}
			
			/* Retry */
			if( result == false ) {
				retries++;
				System.out.println( "Retry #" + retries + "..." );
				utilSleep(delay);
			} else {
				System.out.println( "^ SUCCESS" );
				break;
			}
		}
		
		if( !result )
			System.out.println( "^ FAILED" );
		
		webdriver.findElement( By.cssSelector( css ) ).sendKeys(Keys.chord(Keys.CONTROL, "a"), seq);
		//webdriver.findElement( By.cssSelector( css ) ).sendKeys(seq);
		
		return result;
	}
}
