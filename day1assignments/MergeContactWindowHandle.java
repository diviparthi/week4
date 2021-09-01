package week4.day1assignments;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MergeContactWindowHandle {

	public static void main(String[] args) {

		// Launch URL "http://leaftaps.com/opentaps/control/login"
		
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://leaftaps.com/opentaps/control/login");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		// Enter UserName and Password Using Id Locator
		
		driver.findElementById("username").sendKeys("demosalesmanager");
		driver.findElementById("password").sendKeys("crmsfa");
		
		// Click on Login Button using Class Locator
		driver.findElementByClassName("decorativeSubmit").click();
		
		// Click on CRM/SFA Link
		driver.findElementByXPath("//a[contains(text(),'CRM/SFA')]").click();
		
		// Click on contacts Button
		driver.findElementByLinkText("Contacts").click();
		
		// Click on Merge Contacts using Xpath Locator
		driver.findElementByXPath("//a[contains(text(),'Merge Contacts')]").click();
		
		// Click on Widget of From Contact
		driver.findElementByXPath("//img[@src='/images/fieldlookup.gif']").click();
		
		// Click on First Resulting Contact
		Set<String> windowHandlesSet = driver.getWindowHandles(); //number of windows is plural so defined as Set of strings
		List<String> windowHandlesList = new ArrayList<String>(windowHandlesSet);  //Converting set to list as we cant get window based on index i
		String windowHandles = windowHandlesList.get(1);  //getting the child window
		driver.switchTo().window(windowHandles);    //switching to child window
		driver.findElementByXPath("(//table[@class='x-grid3-row-table']//tr/td)[1]//a[1]").click();
		driver.switchTo().window(windowHandlesList.get(0)); //switching back to main window from child window
		
		// Click on Widget of To Contact
		driver.findElementByXPath("(//img[@src='/images/fieldlookup.gif'])[2]").click();
		
		// Click on Second Resulting Contact
		Set<String> windowHandlesSet1 = driver.getWindowHandles();
		List<String> windowHandlesList1 = new ArrayList<String>(windowHandlesSet1); 
		driver.switchTo().window(windowHandlesList1.get(1));
		driver.findElementByXPath("((//table[@class='x-grid3-row-table'])[2]//tr/td)[1]//a").click();
		driver.switchTo().window(windowHandlesList1.get(0));
		
		// Click on Merge button using Xpath Locator
		driver.findElementByXPath("//a[text()='Merge']").click();
		
		// Accept the Alert
		Alert alert = driver.switchTo().alert();
		alert.accept();
		
		// Verify the title of the page
		System.out.println(driver.getTitle());
	}

}
