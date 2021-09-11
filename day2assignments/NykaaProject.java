package week4.day2assignments;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class NykaaProject {

	public static void main(String[] args) throws InterruptedException {

		// 1) Go to https://www.nykaa.com/
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.nykaa.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// 2) Mouseover on Brands and Mouseover on Popular
		WebElement brandhover = driver.findElementByXPath("//a[text()='brands']");
		Actions builder = new Actions(driver);
		builder.moveToElement(brandhover).perform();
		WebElement popularhover = driver.findElementByXPath("//a[text()='Popular']");
		builder.moveToElement(popularhover).perform();
		
		// 3) Click L'Oreal Paris
		driver.findElementByXPath("//a[@href='/brands/loreal-paris/c/595?eq=desktop']/img").click();
		
		// 4) Go to the newly opened window and check the title contains L'Oreal Paris
		Set<String> windowHandlesSet = driver.getWindowHandles();
		List<String> windowHandlesList = new ArrayList(windowHandlesSet);
		driver.switchTo().window(windowHandlesList.get(1));
		String title1 = driver.getTitle();
		int size = windowHandlesList.size();
		System.out.println("The number of windows currently is " + size);
		System.out.println("The Title of new window is " + title1);
		if (title1.contains("L'Oreal Paris")) 
		{
			System.out.println("Present in Loreal Paris window");
		} else
		{

			System.out.println("Not present in Loreal Paris window");
		}
		
		// 5) Click sort By and select customer top rated
		driver.executeScript("window.scrollBy(0,500)");  //To scroll the screen
		driver.findElementByXPath("//span[@class='pull-left']").click();
		driver.findElementByXPath("(//div[@class='control control--radio text-capitalize'])[4]").click();
	
		// 6) Click Category and click Shampoo
		Thread.sleep(2000);
		driver.executeScript("window.scrollBy(0,500)");
		driver.findElementByXPath("(//div[@class='pull-right filter-options-toggle'])[1]").click();
		driver.findElementByXPath("(//div[@class='category-wrap-top'])[1]/li").click();
		driver.findElementByXPath("(//div[@class='category-wrap-top'])[2]/li").click();
		driver.findElementByXPath("//input[@id='chk_Shampoo_undefined']/following-sibling::label").click();
		
		// 7) check whether the Filter is applied with Shampoo
		String text1 = driver.findElementByXPath("//ul[@class='pull-left applied-filter-lists']/li").getText();
		System.out.println("Filter applied text is " + text1);
		if (text1.contains("Shampoo")) 
		{
			System.out.println("Filter applied is Shampoo");
		} else
		{
			System.out.println("Filter applied is not Shampoo");

		}
		// 8) Click on L'Oreal Paris Colour Protect Shampoo
		driver.executeScript("window.scrollBy(0,500)");
		driver.findElementByXPath("(//span[contains(text(),'Paris Colour Protect Shampoo')])[1]").click();
		
		// 9) GO to the new window and select size as 175ml
		Set<String> windowHandlesSet1 = driver.getWindowHandles(); //Getting the windows again as another new window opened from child window
		List<String> windowHandlesList1 = new ArrayList<String>(windowHandlesSet1);
		int size1 = windowHandlesList1.size();
		System.out.println("The number of windows is " + size1);
		driver.switchTo().window(windowHandlesList1.get(2));
		System.out.println(driver.getTitle());
		
		// 10) Print the MRP of the product
		String text2= driver.findElementByXPath("(//span[@class='post-card__content-price-offer'])[1]").getText();
		String mrpprice = text2.replaceAll("[^0-9]", "");
		System.out.println("The MRP price of Loreal Paris colour protect shampoo is " + mrpprice);
		
		// 11) Click on ADD to BAG
		driver.findElementByXPath("(//div[@class='pull-left'])[1]//button").click();
		
		// 12) Go to Shopping Bag
		Thread.sleep(2000);
		driver.findElementByXPath("//div[@class='AddBagIcon']").click();
		
		// 13) Print the Grand Total amount
		String grandtotcart = driver.findElementByXPath("(//div[@class='value'])[4]").getText();
		String cartgrandtotal = grandtotcart.replaceAll("[^0-9]", "");
		System.out.println("The Grand Total shown in shopping bag is " + cartgrandtotal);
		
		// 14) Click Proceed
		Thread.sleep(2000);
		WebElement proceedbutton = driver.findElementByXPath("//button[@class='btn full fill no-radius proceed ']");
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].click()", proceedbutton);
	
		// 15) Click on Continue as Guest
		driver.executeScript("window.scrollBy(0,500)");
		driver.findElementByXPath("//button[@class='btn full big']").click();
		
		// 16) Check if this grand total is the same in step 13
		String grandtotcheckout = driver.findElementByXPath("(//div[@class='value'])[2]/span").getText();
		String checkoutgrandtotal = grandtotcheckout.replaceAll("[^0-9]", "");
		System.out.println("The Grandtotal shown in checkout summary page is " + checkoutgrandtotal);
		
		if (cartgrandtotal.equalsIgnoreCase(checkoutgrandtotal))
		{
			System.out.println("The Grandtotal in Cart and checkout are same");
		} else 
		{
			System.out.println("The Grandtotal in Cart and checkout are not same");
		}
		
		// 17) Close all windows
		driver.quit();

	}

}
