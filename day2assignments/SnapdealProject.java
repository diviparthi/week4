package week4.day2assignments;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SnapdealProject {

	public static void main(String[] args) throws InterruptedException, IOException {

		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();

		// Launch https://www.snapdeal.com/
		driver.get("https://www.snapdeal.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// Go to Mens Fashion
		WebElement menfashion = driver.findElementByXPath("(//span[@class='catText'])[6]");
		Actions builder = new Actions(driver);
		builder.moveToElement(menfashion).perform();
		
		// Go to Sports Shoes
		driver.findElementByXPath("(//span[contains(text(),'Sports Shoes')])[2]").click();
		
		// Get the count of the sports shoes
		String sportshoecount = driver.findElementByXPath("//span[@class='category-count']").getText();
		String sportshoe = sportshoecount.replaceAll("[^0-9]", "");
		System.out.println("The Sports shoes category count is " + sportshoe);
		
		
		// Click Training shoes
		driver.findElementByXPath("(//div[@class='child-cat-name '])[2]").click();
		
		// Sort by Low to High
		driver.findElementByXPath("//div[@class='sorting-sec animBounce']").click();
		Thread.sleep(2000);
		driver.findElementByXPath("//ul[@class='sort-value']/li[2]").click();
		Thread.sleep(2000);
		
		// Check if the items displayed are sorted correctly
		List<WebElement> prlist = driver.findElements(By.xpath("//span[@class='lfloat product-price']"));				 
		List<Integer> val = new ArrayList<Integer>();
		for(int i=0; i<prlist.size(); i++)  {
			String pr = prlist.get(i).getText().replaceAll("[^0-9]", "");
			System.out.println(pr);
			val.add(Integer.parseInt(pr));
		}
	
		List<Integer> sortlist = new ArrayList<Integer>(val);
		Collections.sort(sortlist);
		for(int i=0; i<val.size(); i++) {
			if(val.get(i) == sortlist.get(i)) {
			System.out.println("Items are sorted correctly");
			} else {
			System.out.println("Items are not sorted");
			}
		} 
		
		// Mouse Hover on puma Blue Training shoes (Selected Asian black training shoe as Puma shoes are not in site)
		WebElement prodimage = driver.findElementByXPath("//img[@class='product-image wooble' and @title='ASIAN Black Training Shoes']");
		builder.moveToElement(prodimage).perform();
		
		// click QuickView button
		driver.findElementByXPath("(//div[@class='center quick-view-bar  btn btn-theme-secondary  '])[4]").click();
		
		// Print the cost and the discount percentage
		String shoecost = driver.findElementByXPath("//span[@class='payBlkBig']").getText();
		System.out.println("The Cost of Asian black training shoe is  " + shoecost);
		String shoediscount = driver.findElementByXPath("//span[@class='percent-desc ']").getText();
		System.out.println("The Discount percentage shown is " + shoediscount);
		
		// Take the snapshot of the shoes.
		File src1 = driver.getScreenshotAs(OutputType.FILE);
		File dst = new File("./snaps/snapdealshoe.png");
		FileUtils.copyFile(src1, dst);
		
		// Close the current window -- Quick view opens as modal popup not as window
		driver.findElementByXPath("(//i[@class='sd-icon sd-icon-delete-sign'])[3]").click();
		
		// Close the main window - Not applicable
		// select the brand Puma - Not applicable as brand Puma is not present
	}

}
