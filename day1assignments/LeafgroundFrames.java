package week4.day1assignments;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LeafgroundFrames {

	public static void main(String[] args) throws IOException {

		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://leafground.com/pages/frame.html");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//Take screenshot of Click me button of first frame
		WebElement frame1 = driver.findElementByXPath("//div[@id='wrapframe']/iframe");
		driver.switchTo().frame(frame1);
		WebElement clickmebutton = driver.findElementByXPath("//button[@id='Click']");
		File src1 = clickmebutton.getScreenshotAs(OutputType.FILE);  //Webelement name should be given instead of driver.
		File dst = new File("./snaps/clickmebutton.png");
		FileUtils.copyFile(src1, dst);
		driver.switchTo().defaultContent();
		
		//Find the number of frames visible to the main page (excluded nested frames count)
		List<WebElement> framelist = driver.findElementsByTagName("iframe");
		int noofframes = framelist.size();
		System.out.println("The number of frames present is " + noofframes);
		
	}

}
