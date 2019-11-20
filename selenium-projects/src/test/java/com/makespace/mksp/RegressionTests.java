package com.makespace.mksp;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.AfterMethod;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;


public class RegressionTests {

	private WebDriver driver;

	@Parameters({ "browser" })
	@BeforeMethod(alwaysRun = true)
	public void openBrowser(@Optional("chrome") String browser) {

		switch (browser) {
		case "crome":
			System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
			driver = new ChromeDriver();
			break;
		case "firefox":
			System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
			driver = new FirefoxDriver();
			break;

		default:
			System.out.println("The browser is not specified. Start Chrome browser");
			System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
			driver = new ChromeDriver();
			break;
		}
		// wait for 3 seconds to page to load
		waitForPageToLoad(1000);

		// maximize window
		driver.manage().window().maximize();

		// implicit wait for the elements that not immediately available on page
		// driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// open page
		String url = "https://dev.mksp.co";
		driver.get(url);
		System.out.println("Page is opened");

	}

	@Parameters({ "name", "phone", "zip" })
//	@Test(priority = 1, groups = { "positiveTest", "smokeTest" })
	@Test(priority = 1)
	public void makeAppointmentPositive(String name, String phoneNumber, String zipCode) {
		
		// enter name
		WebElement nameElement = driver.findElement(By.name("name"));
		nameElement.sendKeys(name);
		
		// enter phone number
		WebElement phoneNumberElement = driver.findElement(By.name("phone"));
		phoneNumberElement.sendKeys(phoneNumber);

		// enter zip code
		WebElement zipCodeElement = driver.findElement(By.name("zip"));
		zipCodeElement.sendKeys(zipCode);

		// wait for 1 seconds to page to load
		waitForPageToLoad(1000);
		
		//explicit wait
//		WebDriverWait wait = new WebDriverWait(driver, 10);

		// press Get a Quote button
		WebElement getQuoteElement = driver.findElement(By.xpath("//button[contains(text(),'Get a Quote')]"));
//		wait.until(ExpectedConditions.elementToBeClickable(getQuoteElement));
		getQuoteElement.click();

		// wait for 1 seconds to page to load
		waitForPageToLoad(1000);

		// Step 1 - validate you are on the right page
		WebElement successMsg1 = driver.findElement(By.xpath("//h3[@class='light-header--blue booking-intro-header']"));
		String expectedMsg1 = "Thanks! We’ll be in touch soon.";
		String actualMsg1 = successMsg1.getText();
		Assert.assertEquals(actualMsg1, expectedMsg1, "The message is correct");

		// wait for 1 seconds to page to load
		waitForPageToLoad(1000);
		
		// select Walk-in Closet Plan
		WebElement walkinClosetElement = driver.findElement(By.xpath("/html//div[@id='ms-booking']//section[@class='all-plans-container flex']/div[1]/div[@class='flipper']/article[1]//button[.='Select Plan']"));
		walkinClosetElement.click();
		
		// select 4 bins
		String bin = "4";
		WebElement binElement = driver.findElement(By.name("bin"));
		binElement.sendKeys(bin);
		
		// click Continue button
		WebElement continueButtonElement1 = driver.findElement(By.xpath("//button[contains(text(),'Continue')]"));
		continueButtonElement1.click();
		
		// Step 2 - validate you are on the right page
		WebElement successMsg2 = driver.findElement(By.xpath("//h3[@class='light-header--blue booking-intro-header']"));
		String expectedMsg2 = "Now, let’s get some details.";
		String actualMsg2 = successMsg2.getText();
		Assert.assertEquals(actualMsg2, expectedMsg2, "The message is correct");

		// select all details
		WebElement selectFirstElement = driver.findElement(By.xpath("/html//div[@id='ms-booking']//div[@class='new-content-container']/div/div[1]/div[1]//div[.='No']"));
		selectFirstElement.click();
		
		WebElement selectSecondElement = driver.findElement(By.xpath("/html//div[@id='ms-booking']//div[@class='new-content-container']/div/div[1]/div[2]//div[.='Yes']"));
		selectSecondElement.click();

		WebElement selectThirdElement = driver.findElement(By.xpath("/html//div[@id='ms-booking']//div[@class='new-content-container']/div/div[1]/div[3]//div[.='Yes']]"));
		selectThirdElement.click();

		WebElement selectFourthElement = driver.findElement(By.xpath("/html//div[@id='ms-booking']//div[@class='new-content-container']/div/div[1]/div[4]//div[.='No']"));
		selectFourthElement.click();

		WebElement selectFifthElement = driver.findElement(By.xpath("/html//div[@id='ms-booking']//div[@class='new-content-container']/div/div[1]/div[5]//div[.='No']"));
		selectFifthElement.click();

		// click Continue button
		WebElement continueButtonElement2 = driver.findElement(By.xpath("//button[contains(text(),'Continue')]"));
		continueButtonElement2.click();

		// Step 3 - validate you are on the right page
		WebElement successMsg3 = driver.findElement(By.xpath("//h3[@class='light-header--blue booking-intro-header']"));
		String expectedMsg3 = "Great! Let’s confirm some last details.";
		String actualMsg3 = successMsg3.getText();
		Assert.assertEquals(actualMsg3, expectedMsg3, "The message is correct");
	
		// fill Create Your Account form
		// enter Full Name
		WebElement fullnameElement = driver.findElement(By.name("name"));
		fullnameElement.sendKeys(name);
		
		// enter Email
		String email = "test@gmail.com";
		WebElement emailElement = driver.findElement(By.name("email"));
		emailElement.sendKeys(email);

		// select active member of the United States Armed Forces?
		WebElement radioButtonElement = driver.findElement(By.xpath("//span[contains(text(),'No')]"));
		radioButtonElement.click();
		
		// click Create my account button
		WebElement createAccountButtonElement = driver.findElement(By.xpath("//button[contains(text(),'Create my account')]"));
		createAccountButtonElement.click();

		// fill Appointment Address form
		// enter Appointment Address
		String address = "123 William Street, New York, NY, USA";
		WebElement appointmentAddressElement = driver.findElement(By.name("address_pretty"));
		appointmentAddressElement.sendKeys(address);

		// click Confirm and continue button
		WebElement confirmAndContinueButtonElement1 = driver.findElement(By.xpath("//button[contains(text(),'Confirm and continue')]"));
		confirmAndContinueButtonElement1.click();

		// fill Appointment Date and Time form
		// enter Date
		String date = "20/12/2019";
		WebElement appointmentDateElement = driver.findElement(By.xpath("//div[contains(text(),'mm/dd/yyyy')]"));
		appointmentDateElement.sendKeys(date);

		String time = "8am-11am";
		WebElement appointmentTimeElement = driver.findElement(By.name("Time"));
		appointmentTimeElement.sendKeys(time);

		// click Confirm and continue button
		WebElement confirmAndContinueButtonElement2 = driver.findElement(By.xpath("//button[contains(text(),'Confirm and continue')]"));
		confirmAndContinueButtonElement2.click();

		// fill Bill form
		// enter Card Number
		String cardNumber = "4111111111111111";
		WebElement cardNumberElement = driver.findElement(By.name("cardnumber"));
		cardNumberElement.sendKeys(cardNumber);

		// enter Expiration Date
		String expirationDate = "02/22";
		WebElement expirationDateElement = driver.findElement(By.name("exp-date"));
		expirationDateElement.sendKeys(expirationDate);

		// enter Security Code
		String cvc = "222";
		WebElement cvcElement = driver.findElement(By.name("cvc"));
		cvcElement.sendKeys(cvc);

		// enter Name on Card
		String nameOnCard = "XXX YYY";
		WebElement nameOnCardElement = driver.findElement(By.name("card-name"));
		nameOnCardElement.sendKeys(nameOnCard);

		// check MakeSpace Terms of Servise radio button
		WebElement termsOfServiceRadioButtonElement = driver.findElement(By.xpath("//span[contains(@class, 'makespace-ui-library-14ovu1w e1ovri130')]"));
		termsOfServiceRadioButtonElement.click();

				
		// validate Successful message "Your appointment for November 29, from 8am-11am is confirmed!"
		WebElement successMsg = driver.findElement(By.xpath("//h4[@class='light-header--blue']"));
		String expectedMsg = "is confirmed!";
		String actualMsg = successMsg.getText();
		Assert.assertTrue(actualMsg.contains(expectedMsg),
				"The message is correct.\nActual Message: " + actualMsg + "\nExpected Message: " + expectedMsg);

		// click Done button
		WebElement doneButtonElement = driver.findElement(By.xpath("//button[contains(text(),'Done')]"));
		doneButtonElement.click();

	}
	
	@Parameters({ "name", "phone", "zip" })
//	@Test(priority = 2, groups = { "negativeTest", "smokeTest" })
	@Test(priority = 2)
	public void makeAppointmentNegative(String name, String phoneNumber, String zipCode) {
		// enter name
		WebElement nameElement = driver.findElement(By.name("name"));
		nameElement.sendKeys(name);
		
		// enter phone number
		WebElement phoneNumberElement = driver.findElement(By.name("phone"));
		phoneNumberElement.sendKeys(phoneNumber);

		// enter zip code
		WebElement zipCodeElement = driver.findElement(By.name("zip"));
		zipCodeElement.sendKeys(zipCode);

		// wait for 1 seconds to page to load
		waitForPageToLoad(1000);
		
		//explicit wait
//		WebDriverWait wait = new WebDriverWait(driver, 10);

		// press Get a Quote button
		WebElement getQuoteElement = driver.findElement(By.xpath("//button[contains(text(),'Get a Quote')]"));
//		wait.until(ExpectedConditions.elementToBeClickable(getQuoteElement));
		getQuoteElement.click();

		// wait for 1 seconds to page to load
		waitForPageToLoad(1000);

		// Step 1 - validate you are on the right page
		WebElement successMsg1 = driver.findElement(By.xpath("//h3[@class='light-header--blue booking-intro-header']"));
		String expectedMsg1 = "Thanks! We’ll be in touch soon.";
		String actualMsg1 = successMsg1.getText();
		Assert.assertEquals(actualMsg1, expectedMsg1, "The message is correct");

		// wait for 1 seconds to page to load
		waitForPageToLoad(1000);
		
		// select Walk-in Closet Plan
		WebElement walkinClosetElement = driver.findElement(By.xpath("/html//div[@id='ms-booking']//section[@class='all-plans-container flex']/div[1]/div[@class='flipper']/article[1]//button[.='Select Plan']"));
		walkinClosetElement.click();
		
		// select 4 bins
		String bin = "4";
		WebElement binElement = driver.findElement(By.name("bin"));
		binElement.sendKeys(bin);
		
		// click Continue button
		WebElement continueButtonElement1 = driver.findElement(By.xpath("//button[contains(text(),'Continue')]"));
		continueButtonElement1.click();
		
		// Step 2 - validate you are on the right page
		WebElement successMsg2 = driver.findElement(By.xpath("//h3[@class='light-header--blue booking-intro-header']"));
		String expectedMsg2 = "Now, let’s get some details.";
		String actualMsg2 = successMsg2.getText();
		Assert.assertEquals(actualMsg2, expectedMsg2, "The message is correct");

		// select all details
		WebElement selectFirstElement = driver.findElement(By.xpath("/html//div[@id='ms-booking']//div[@class='new-content-container']/div/div[1]/div[1]//div[.='No']"));
		selectFirstElement.click();
		
		WebElement selectSecondElement = driver.findElement(By.xpath("/html//div[@id='ms-booking']//div[@class='new-content-container']/div/div[1]/div[2]//div[.='Yes']"));
		selectSecondElement.click();

		WebElement selectThirdElement = driver.findElement(By.xpath("/html//div[@id='ms-booking']//div[@class='new-content-container']/div/div[1]/div[3]//div[.='Yes']]"));
		selectThirdElement.click();

		WebElement selectFourthElement = driver.findElement(By.xpath("/html//div[@id='ms-booking']//div[@class='new-content-container']/div/div[1]/div[4]//div[.='No']"));
		selectFourthElement.click();

		WebElement selectFifthElement = driver.findElement(By.xpath("/html//div[@id='ms-booking']//div[@class='new-content-container']/div/div[1]/div[5]//div[.='No']"));
		selectFifthElement.click();

		// click Continue button
		WebElement continueButtonElement2 = driver.findElement(By.xpath("//button[contains(text(),'Continue')]"));
		continueButtonElement2.click();

		// Step 3 - validate you are on the right page
		WebElement successMsg3 = driver.findElement(By.xpath("//h3[@class='light-header--blue booking-intro-header']"));
		String expectedMsg3 = "Great! Let’s confirm some last details.";
		String actualMsg3 = successMsg3.getText();
		Assert.assertEquals(actualMsg3, expectedMsg3, "The message is correct");
	
		// fill Create Your Account form
		// enter Full Name
		WebElement fullnameElement = driver.findElement(By.name("name"));
		fullnameElement.sendKeys(name);
		
		// enter Email
		String email = "test@gmail.com";
		WebElement emailElement = driver.findElement(By.name("email"));
		emailElement.sendKeys(email);

		// select active member of the United States Armed Forces?
		WebElement radioButtonElement = driver.findElement(By.xpath("//span[contains(text(),'No')]"));
		radioButtonElement.click();
		
		// click Create my account button
		WebElement createAccountButtonElement = driver.findElement(By.xpath("//button[contains(text(),'Create my account')]"));
		createAccountButtonElement.click();

		// fill the Incorrect Appointment Address form
		// enter incorrect Appointment Address
		String incorrectAddress = "123 William Street Floor 22";
		WebElement appointmentAddressElement = driver.findElement(By.name("address_pretty"));
		appointmentAddressElement.sendKeys(incorrectAddress);

		// click Confirm and continue button
		WebElement confirmAndContinueButtonElement1 = driver.findElement(By.xpath("//button[contains(text(),'Confirm and continue')]"));
		confirmAndContinueButtonElement1.isEnabled();
		Assert.assertFalse(confirmAndContinueButtonElement1.isEnabled(), "Continue and confirm button is disabled.");
	}
	
	@AfterMethod(alwaysRun = true) 
	  private void closeBrowser() { 
		  // close browser
		  driver.quit(); 
	  }
	 
	private void waitForPageToLoad(long m) {
		try {
			Thread.sleep(m);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
