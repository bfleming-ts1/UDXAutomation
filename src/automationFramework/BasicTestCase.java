package automationFramework;

public class BasicTestCase {
	
	public static void showResults( String testSuiteName, boolean[] results ) {
		/* Show results of the test pass */
		int passed = 0;
		
		for( boolean test : results ) {
			passed += test == true ? 1 : 0;
		}
		
		System.out.println( "\n" + testSuiteName + " results: " + passed + " out of " + results.length + " passed...\n" );
	}
	
	public static void main(String[] args) {
		String wdName = "nil";
		
		/* Parse arguments */
		for( int i = 0; i < args.length; i++ ) {
			/* Select webdriver via main arguments */
			if( args[i].equalsIgnoreCase( "-driver" ) ) {
				i++;
				wdName = args[i];
			}
		}
		
		System.out.println( "Automation started (" + wdName + " web driver selected)..." );
		
		/* Base test suite */
		LoginTestSuite udxLoginTestSuite = new LoginTestSuite();
		MiscTestSuite udxMiscTestSuite = new MiscTestSuite();
		RestaurantTestSuite udxRestaurantTestSuite = new RestaurantTestSuite();
		
		udxLoginTestSuite.udxSetWebdriver(wdName);
		udxMiscTestSuite.udxSetWebdriver(wdName);
		udxRestaurantTestSuite.udxSetWebdriver(wdName);
		
		BaseAutomationSuite[] udxAutomationSuite = new BaseAutomationSuite[] {
			new BaseAutomationSuite() {	public void execute() { showResults( "LoginTestSuite", 		udxLoginTestSuite.run() ); } },
			new BaseAutomationSuite() {	public void execute() { showResults( "RestaurantTestSuite", udxRestaurantTestSuite.run() ); } },
			new BaseAutomationSuite() {	public void execute() { showResults( "MiscTestSuite", 		udxMiscTestSuite.run() ); } },
		};
				
		/* Execute test suite */
		for( BaseAutomationSuite suite : udxAutomationSuite ) {
			suite.execute();
		}
		
		System.out.println( "Vége..." );
	}
}
