package week4.day2assignments;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SelectableJquery {

	public static void main(String[] args) {

		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://jqueryui.com/selectable/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement frame1 = driver.findElementByXPath("//iframe[@class='demo-frame']");
		driver.switchTo().frame(frame1);
		WebElement item2 = driver.findElementByXPath("(//li[@class='ui-widget-content ui-selectee'])[2]");
		WebElement item6 = driver.findElementByXPath("(//li[@class='ui-widget-content ui-selectee'])[6]");
		Actions builder = new Actions(driver);
		builder.clickAndHold(item2).moveToElement(item6).release().perform();
		driver.switchTo().defaultContent();
	}

}
