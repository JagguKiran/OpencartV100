package testBase;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.github.javafaker.Faker;

import utilities.ExcelUtil;
import utilities.GeneralUtil;

public class BaseTest {
	protected static WebDriver driver;
	protected Logger logger;
	protected WebDriverWait wait;
	protected Faker faker;
	protected ExcelUtil excelOp;
	@BeforeClass(groups="Master")
	@Parameters({"operatingSystem","browser"})
	public void setUp(String os,String br) throws MalformedURLException {
		os=os.toLowerCase();
		br=br.toLowerCase();
		if(GeneralUtil.getProperty("execution_env").trim().equalsIgnoreCase("remote")){
			DesiredCapabilities cap=new DesiredCapabilities();
			switch(os.charAt(0)) {
				case 'w':cap.setPlatform(Platform.WIN10);break;
				case 'm':cap.setPlatform(Platform.MAC);break;
				case 'l':cap.setPlatform(Platform.LINUX);break;
				default:cap.setPlatform(Platform.WIN10);
			}
			switch(br.charAt(0)){
				case 'c':cap.setBrowserName("chrome");break;
				case 'e':cap.setBrowserName("edge");break;
				case 'f':cap.setBrowserName("firefox");break;
				default:cap.setBrowserName("chrome");return;
			}	
			driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cap);
			
		}else {
			switch(br.charAt(0)){
				case 'c':driver=new ChromeDriver();break;
				case 'e':  driver=new EdgeDriver();break;
				case 'f':driver=new FirefoxDriver();break;
				default:System.out.println("Invalid Browser");return;
			}
		}
		faker=new Faker();
		excelOp=new ExcelUtil();
		logger=LogManager.getLogger(this.getClass());
		driver.manage().deleteAllCookies();
		logger.info("Deleted All Cookies");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		logger.info("Provided Implicit Wait");
		driver.manage().window().maximize();
		driver.get(GeneralUtil.getProperty("url"));
		logger.info("Maximize the window");
		wait=new WebDriverWait(driver,Duration.ofSeconds(10));
	}
	@AfterClass(groups="Master")
	public void tearDown() {
		logger.info("Quiting Browser");
		driver.quit();
	}
	public String captureScreen(String tname) {
		String timestamp=new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss").format(new Date());
		TakesScreenshot ts=(TakesScreenshot)driver;
		File sourceFile = ts.getScreenshotAs(OutputType.FILE);
		String targetFilePath=System.getProperty("user.dir")+"/screenshots/"+tname+"_"+timestamp+".png";
		File destinationFile=new File(targetFilePath);
		sourceFile.renameTo(destinationFile);
		return targetFilePath;
	}
}
