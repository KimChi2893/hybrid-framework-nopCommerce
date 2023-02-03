package commons;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	private WebDriver driver;
	
	public WebDriver getWebDriveInstance() {
		return this.driver;
	}
	
	public WebDriver getDriverInstance() {
		return this.driver;
	}
	protected WebDriver getBrowserDriver(String browserName) {
		BrowserList browser = BrowserList.valueOf(browserName.toUpperCase());
		switch (browser) {
		case CHROME:
			driver = WebDriverManager.chromedriver().create();
			break;
		case FIREFOX:
			driver = WebDriverManager.firefoxdriver().create();
			break;
		case IE:
			driver = WebDriverManager.iedriver().create();
			break;
		case EDGE:
			driver = WebDriverManager.edgedriver().create();
			break;
		case SAFARI:
			driver = WebDriverManager.safaridriver().create();
			break;
		case OPERA:
			driver = WebDriverManager.operadriver().create();
			break;
		default:
			throw new RuntimeException("The browser name is not valid");
		}
		driver.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT, TimeUnit.SECONDS);
		driver.get(GlobalConstants.LIVE_USER_URL);
		return driver;
	}
	
	protected WebDriver getBrowserDriver(String browserName, String urlValue) {
		BrowserList browser = BrowserList.valueOf(browserName.toUpperCase());
		switch (browser) {
		case CHROME:
			driver = WebDriverManager.chromedriver().create();
			break;
		case FIREFOX:
			driver = WebDriverManager.firefoxdriver().create();
			break;
		case IE:
			driver = WebDriverManager.iedriver().create();
			break;
		case EDGE:
			driver = WebDriverManager.edgedriver().create();
			break;
		case SAFARI:
			driver = WebDriverManager.safaridriver().create();
			break;
		case OPERA:
			driver = WebDriverManager.operadriver().create();
			break;
		default:
			throw new RuntimeException("The browser name is not valid");
		}
		driver.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT, TimeUnit.SECONDS);
		driver.get(urlValue);
		return driver;
	}
	
	protected int getRandomNumber() {
		Random rand = new Random(System.currentTimeMillis());
		return rand.nextInt(999999);
	}
}
