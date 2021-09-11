package week4.day2assignments;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DraggableJquery {

	public static void main(String[] args) {

		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://jqueryui.com/draggable/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement frame1 = driver.findElementByXPath("//iframe[@class='demo-frame']");
		driver.switchTo().frame(frame1);
		WebElement dragbox = driver.findElementById("draggable");
		Actions builder = new Actions(driver);
		builder.dragAndDropBy(dragbox, 100, 100).perform();
		driver.switchTo().defaultContent();
	}

}
