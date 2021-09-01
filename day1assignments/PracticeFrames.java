package week4.day1assignments;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PracticeFrames {

	public static void main(String[] args) {

		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://chercher.tech/practice/frames-example-selenium-webdriver");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//Frame1 to key in the Topic text box
		WebElement nestedframe1 = driver.findElementByXPath("//iframe[@id='frame1']");
		driver.switchTo().frame(nestedframe1);
		driver.findElementByXPath("//b[@id='topic']/following-sibling::input").sendKeys("Not a friendly topic");
		
		//Frame3 to click the checkbox - Frame3 is present inside Frame1 as nested frame.So didnt come out of that frame1.
		//Stayed inside frame1 to switch to frame3
		driver.switchTo().frame("frame3");  //frame3 is 2nd option of frames - if there is id we can directly give that inside
		driver.findElementByXPath("//input[@id='a']").click();
		driver.switchTo().parentFrame();  //Command to come out of frame3 to parent frame(frame1)
		WebElement topictext = driver.findElementByXPath("//b[@id='topic']/following-sibling::input");
		topictext.clear();
		topictext.sendKeys("Sorry it is friendly");
		driver.switchTo().defaultContent();
		
		//Frame2 - To select the dropdown option - Frame2 is present outside and not inside frame1
		WebElement frame2 = driver.findElementByXPath("//iframe[@id='frame2']");
		driver.switchTo().frame(frame2);
		WebElement drop1 = driver.findElementByXPath("//select[@id='animals']");
		Select drpdwn1 = new Select(drop1);
		drpdwn1.selectByValue("big baby cat");
		driver.switchTo().defaultContent();
		
	
	}

}
