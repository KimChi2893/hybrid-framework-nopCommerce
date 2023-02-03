package commons;

public class GlobalConstants {
	//System info
	public static final String PROJECT_PATH = System.getProperty("user.dir");
	public static final String JAVA_VERSION = System.getProperty("java.version");
	
	public static final String OS_NAME = System.getProperty("os.name.version");
	
	
	// Additional info for user: Variables are used generally for all classes
	public static final String LIVE_USER_URL = "https://demo.nopcommerce.com/";
	
	// Additional info for Admin
	public static final String LIVE_ADMIN_URL = "https://admin-demo.nopcommerce.com/login?ReturnUrl=/admin/";
	
	//public static final String USER_USERNAME = "automationfc.vn@gmail.com";
	//public static final String USER_PASSWORD = "123456";
	
	public static final String ADMIN_USERNAME = "admin@yourstore.com";
	public static final String ADMIN_PASSWORD = "admin";
	
	// Browser logs/ Extension
	public static final String REPORTNG_SCREENSHOT = PROJECT_PATH + "/reportNGImages/";
	
	// Wait Info
	public static final long SHORT_TIMEOUT = 5;
	public static final long LONG_TIMEOUT = 30;
}
