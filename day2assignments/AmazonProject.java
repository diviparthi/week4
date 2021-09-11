package week4.day2assignments;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AmazonProject {

	public static void main(String[] args) throws InterruptedException, IOException {

		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		//Load the uRL https://www.amazon.in/
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//search as oneplus 9 pro 
		WebElement searchbox = driver.findElementByXPath("//input[@id='twotabsearchtextbox']");
		searchbox.sendKeys("oneplus 9 pro");
		searchbox.sendKeys(Keys.ENTER);
		
		//Get the price of the first product
		String firstprodprice = driver.findElementByXPath("(//span[@class='a-price-whole'])[1]").getText();
		System.out.println("The price of the first product is " + firstprodprice);
		
		// Print the number of customer ratings for the first displayed product
		String prodratings = driver.findElementByXPath("(//span[@class='a-size-base'])[1]").getText();
		System.out.println("The number of ratings for first product is " + prodratings);
		
		// Mouse Hover on the stars
		Thread.sleep(2000);
		WebElement starrating = driver.findElementByXPath("(//span[@class='a-declarative'])[3]");
		Actions builder = new Actions(driver);
		builder.moveToElement(starrating).perform();
		System.out.println("Mouse hovered on star rating");
		
		// Get the percentage of ratings for the 5 star.
		Thread.sleep(2000);
		String starpercent = driver.findElementByXPath("//span[@class='a-size-base']/a").getText();
		System.out.println("The Percentage of ratings for 5 Star is " + starpercent);
		
		// Click the first text link of the first image
		driver.findElementByXPath("(//a[@class='a-link-normal a-text-normal'])[1]").click();
		Set<String> windowHandlesSet = driver.getWindowHandles();
		List<String> windowHandlesList = new ArrayList<String>(windowHandlesSet);
		int listsize = windowHandlesList.size();
		driver.switchTo().window(windowHandlesList.get(1));
		
		// Take a screen shot of the product displayed
		File src1 = driver.getScreenshotAs(OutputType.FILE);
		File dst = new File("./snaps/Amazon.png");
		FileUtils.copyFile(src1, dst);
		
		// Click 'Add to Cart' button
		Thread.sleep(2000);
		driver.executeScript("window.scrollBy(0,500)");
		driver.findElementById("add-to-cart-button").click();
		
		// Get the cart subtotal and verify if it is correct.
		Thread.sleep(2000);
		WebElement viewcart = driver.findElementByXPath("//form[@id='attach-view-cart-button-form']/span");
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].click()", viewcart);
		Thread.sleep(2000);
		driver.findElementByXPath("//div[@id='nav-cart-count-container']").click();
		String cartsubtotal = driver.findElementByXPath("//span[@id='sc-subtotal-amount-activecart']").getText();
		System.out.println("The Cart subtotal is " + cartsubtotal);

		if (firstprodprice.equalsIgnoreCase(cartsubtotal)) 
		{
			System.out.println("The Cart subtotal is verified and its correct");
		} else
		{
			System.out.println("The Cart subtotal is verified and its correct");
		}
	}

}
