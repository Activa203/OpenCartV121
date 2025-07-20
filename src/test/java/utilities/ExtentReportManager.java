package utilities;

import java.awt.Desktop;
import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import net.bytebuddy.implementation.bytecode.constant.TextConstant;
import testBase.BaseClass;

public class ExtentReportManager implements ITestListener {

	ExtentSparkReporter sparkReport;
	ExtentReports extent;
	ExtentTest test;
	
	String repName;

	public void onStart(ITestContext context) {
		
		String timeStamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		repName="Test-Report-"+timeStamp+".html";
		
		
		sparkReport = new ExtentSparkReporter(".\\reports\\" + repName);
		sparkReport.config().setDocumentTitle("opencart Automation Report");
		sparkReport.config().setReportName("opencart Functional Testing");
		sparkReport.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		extent.attachReporter(sparkReport);
		
		extent.setSystemInfo("Application", "OpenCart");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Sub Module", "Customers");
		extent.setSystemInfo("User Name",System.getProperty("user.name"));
		extent.setSystemInfo("Envoroment", "QA");
		
		String os=context.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("OS", os);
		
		String browser=context.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);
		
		
		List<String> includeGroups = context.getCurrentXmlTest().getIncludedGroups();
		if(!includeGroups.isEmpty())
		{
			extent.setSystemInfo("Groups", includeGroups.toString());
		}

	}

	
	public void onTestSuccess(ITestResult result) {
		test=extent.createTest(result.getClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS,result.getName()+" got successfully executed...");
	}

	public void onTestFailure(ITestResult result) {
		test=extent.createTest(result.getClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		
		test.log(Status.FAIL,result.getName()+" got failed...");
		test.log(Status.INFO,result.getThrowable().getMessage());
		
		try {
			String imgPath=new BaseClass().captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgPath);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) {
		test=extent.createTest(result.getClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP,result.getName()+" got skipped...");
		test.log(Status.INFO,result.getThrowable().getMessage());
	}

	public void onFinish(ITestContext context) {
	    extent.flush();

	    String pathOfExtentReport = System.getProperty("user.dir") + "\\reports\\" + repName;
	    File extentReport = new File(pathOfExtentReport);

	    // Open the report in the default browser
	    try {
	        Desktop.getDesktop().browse(extentReport.toURI());
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

		/*
		 * // Send the report via email try { URL reportUrl = new URL("file:///" +
		 * System.getProperty("user.dir") + "\\reports\\" + repName);
		 * 
		 * ImageHtmlEmail email = new ImageHtmlEmail(); email.setDataSourceResolver(new
		 * DataSourceUrlResolver(reportUrl)); email.setHostName("smtp.googlemail.com");
		 * email.setSmtpPort(465); email.setAuthenticator(new
		 * DefaultAuthenticator("shubhamchougale2012@gmail.com", "password")); //
		 * Replace with actual password or use secure config
		 * email.setSSLOnConnect(true); email.setFrom("shubhamchougale2012@gmail.com");
		 * email.setSubject("Test Results");
		 * email.setMsg("Please find the attached report.");
		 * email.addTo("shubhamchougale2012@gmail.com"); email.attach(reportUrl,
		 * "Extent Report", "Please check the attached report.");
		 * 
		 * email.send(); // Send the email } catch (Exception e) { e.printStackTrace();
		 * }
		 */
	}


}
