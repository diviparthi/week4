package week4.day2assignments;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SelectableLeaftap {

	public static void main(String[] args) {

		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://www.leafground.com/pages/selectable.html");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Actions builder = new Actions(driver);
		WebElement item1 = driver.findElementByXPath("(//li[@class='ui-widget-content ui-selectee'])");
		WebElement item5 = driver.findElementByXPath("(//li[@class='ui-widget-content ui-selectee'])[5]");
		builder.clickAndHold(item1).moveToElement(item5).release().build().perform();
	}
	

}
