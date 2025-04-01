package utilities;

import java.awt.Desktop;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseTest;

public class ExtentReportManager implements ITestListener {
	public ExtentReports reports;
	public ExtentSparkReporter spark;
	public ExtentTest extent;
	public String repName;
	public void onStart(ITestContext context) {
		String timestamp=new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss").format(new Date());
		repName="Test-Report-"+timestamp+".html";
		
		
		spark=new ExtentSparkReporter(System.getProperty("user.dir")+"/reports/"+repName);
		
		
		spark.config().setDocumentTitle("Opencart Automation Report");
		spark.config().setReportName("Opencart Functional Testing");
		spark.config().setTheme(Theme.STANDARD);
		
		reports=new ExtentReports();
		
		reports.attachReporter(spark);
		reports.setSystemInfo("Application","Opencart");
		reports.setSystemInfo("Module","Admin");
		reports.setSystemInfo("Sub Module","Customers");
		reports.setSystemInfo("User Name",System.getProperty("user.name"));
		reports.setSystemInfo("Environment","QA");
		
		String browser=context.getCurrentXmlTest().getParameter("browser");
		
		reports.setSystemInfo("Browser",browser);
		String os=context.getCurrentXmlTest().getParameter("operatingSystem");
		reports.setSystemInfo("Operating System",os);
		List<String> al = context.getCurrentXmlTest().getIncludedGroups();
		if(!al.isEmpty())reports.setSystemInfo("Groups",al.toString());
		
	}
	public void onTestSuccess(ITestResult result){
		
		extent=reports.createTest(result.getTestClass().getName());
		extent.assignCategory(result.getMethod().getGroups());
		extent.log(Status.PASS,result.getName()+" got successfully executed");
		try {
			String imagePath=new BaseTest().captureScreen(result.getName());
			extent.addScreenCaptureFromPath(imagePath);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void onTestFailure(ITestResult result){
		extent=reports.createTest(result.getTestClass().getName());
		extent.assignCategory(result.getMethod().getGroups());
		extent.log(Status.FAIL,result.getName()+" got failed");
		extent.log(Status.INFO,result.getThrowable().getMessage());
		try {
			String imagePath=new BaseTest().captureScreen(result.getName());
			extent.addScreenCaptureFromPath(imagePath);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void onTestSkipped(ITestResult result){
		extent=reports.createTest(result.getTestClass().getName());
		extent.assignCategory(result.getMethod().getGroups());
		extent.log(Status.SKIP,result.getName()+" got skipped");
		extent.log(Status.INFO,result.getThrowable().getMessage());
	}
	public void onFinish(ITestContext context){
		reports.flush();
		String pathOfExtentReport=System.getProperty("user.dir")+"/reports/"+repName;
		File extentReport=new File(pathOfExtentReport);
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		}catch(Exception e){
			e.printStackTrace();
		}
		/*
		try {
			URL url=new URL("file:///"+System.getProperty("user.dir")+"/reports/"+repName);
			ImageHtmlEmail email=new ImageHtmlEmail();
			email.setDataSourceResolver(new DataSourceUrlResolver(url));
			email.setHostName("smtp.gmail.com");
			email.setSmtpPort(465);
			email.setAuthentication("kiran.kiranjmps@gmail.com","basava8181");
			email.setSSLOnConnect(true);
			email.setFrom("kiran.kiranjmps@gmail.com");
			email.setSubject("Test Results");
			email.setMsg("Please Find Attached Report");
			email.addTo("kiran.kiranjmps@gmail.com");
			email.attach(url,"Extent Report","Please Check Report");
			email.send();
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		*/
	}
}
