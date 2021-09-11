package week4.day2assignments;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;
import week4.day1.LearnAlerts;

public class MouseHoverLeaftaps {

	public static void main(String[] args) {

		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://www.leafground.com/pages/mouseOver.html");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		WebElement course = driver.findElement(By.xpath("//a[contains(text(),'TestLeaf Courses')]"));          
		Actions builder = new Actions(driver);
		builder.moveToElement(course).perform();
		
		driver.findElementByXPath("//a[contains(text(),'Selenium')]").click();
		Alert alert = driver.switchTo().alert();
		alert.accept();
		builder.moveToElement(course).perform();
		
		List<WebElement> list = driver.findElements(By.xpath("//a[@class='listener']"));		
		for (WebElement eachData : list) {
			String text = eachData.getText();
			System.out.println(text);	
		}
		
		
	}
	

}
