package testCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class FlipKartTest {

	@Test
	public void verifyClick() throws InterruptedException {
		String url="https://www.flipkart.com/";
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(url);
		Thread.sleep(2000);
		driver.switchTo().newWindow(WindowType.TAB);
	}
}
