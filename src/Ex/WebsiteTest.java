package Ex;


import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import javax.print.DocFlavor.READER;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.v125.domstorage.model.Item;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class WebsiteTest {

	WebDriver driver = new ChromeDriver();

	String myWebsite = "https://magento.softwaretestingboard.com/";

	Random rand= new Random();

	String password="s@rah123";
	
	String LogoutPage="https://magento.softwaretestingboard.com/customer/account/logout/";
	
	String LoginEmailAddress= "";
	
	
	@BeforeTest
	public void mySetup() {
		driver.manage().window().maximize();
		driver.get(myWebsite);

		//we added this after the forth test 
		//element not found -> managing for timeout (thread sleep)
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

	}

	@Test(priority=1,enabled=false)
	public void CreateAnAccount() {

		// xpath
		//	WebElement createAccountPage = driver.findElement(By.xpath("//a[@href='https://magento.softwaretestingboard.com/customer/account/create/']"));

		// partialLinkText
		//WebElement createAccountPage = driver.findElement(By.partialLinkText("Account"));
		
		//link text 
		WebElement createAccountPage= driver.findElement(By.linkText("Create an Account")); 
		createAccountPage.click();
	
		
		
	String[] Firstname= { "Alice", "Bob", "Charlie", "Diana", "Ethan",
            "Fiona", "George", "Hannah", "Ian", "Jessica"};
	String[] Lastname = { "Alice", "Bob", "Charlie", "Diana", "Ethan",
            "Fiona", "George", "Hannah", "Ian", "Jessica"};

	int randfirstname= rand.nextInt(Firstname.length);
	int randlastname= rand.nextInt(Lastname.length);
//	System.out.println(randfirstname);
//	System.out.println(randlastname);
	
	
	// putting samples in create account text boxes
	//getting inputs from every webelement in the website 
	
	
	//driver.findElement(By.id("firstname")).sendKeys(Firstname[randfirstname]);
	WebElement firstnameInput=driver.findElement(By.id("firstname"));
	//driver.findElement(By.id("lastname")).sendKeys(Lastname[randlastname]);
	WebElement lastnameInput= driver.findElement(By.id("lastname"));
	
	WebElement EmailAddressInput=driver.findElement(By.id("email_address"));
	WebElement PasswordInput=driver.findElement(By.id("password"));
	WebElement PasswordConfirmation=driver.findElement(By.id("password-confirmation"));
	
	WebElement createAccountButton=driver.findElement(By.xpath("//button[@title='Create an Account']"));
	
	String first_name=Firstname[randfirstname];
	String last_name=Lastname[randlastname];
	int randomnumber=rand.nextInt(1234);
	String domain="@gmail.com";
//	String []domains= {};
	
	
	//filling the text boxes in the website (webelements) 
	
	firstnameInput.sendKeys(first_name);
	lastnameInput.sendKeys(last_name);
	EmailAddressInput.sendKeys(first_name+last_name
			+randomnumber+domain);
	PasswordInput.sendKeys(password);
	PasswordConfirmation.sendKeys(password);
	
	//now to create account we need to submit the information 
	//click the create account button 
	createAccountButton.click();
	
	
	//for login purposes 
	LoginEmailAddress=first_name+last_name
			+randomnumber+domain;
	
	
	//popping msg to the customer 
	
//	WebElement MessageAsWebElement = driver.findElement(By.className("messages"));
//
//	String TheActualMessage = MessageAsWebElement.getText();
//
//	String ExpectedMessage = "Thankyou testing shit over here idkk help ";
//
//	Assert.assertSame(TheActualMessage, ExpectedMessage);
//	
	
	}


@Test(priority =2,enabled=false)
public void  Logout() {
	//since the logout button has a link inside its element 
	//we can use it in testing whixch is easy without any webelement 
	
	driver.get(LogoutPage);
	
}

@Test (priority =3,enabled=false)
public void LoginTest() {
	
	WebElement LoginPage=driver.findElement(By.linkText("Sign In"));
	LoginPage.click();
	
	WebElement LoginEmail= driver.findElement(By.id("email"));
	LoginEmail.sendKeys(LoginEmailAddress);
	
	WebElement LoginPassword=driver.findElement(By.id("pass"));
	LoginPassword.sendKeys(password);
	
//	WebElement LoginButton= driver.findElement(By.className("action login primary"));
	WebElement LoginButton= driver.findElement(By.cssSelector(".action.login.primary"));
	LoginButton.click();
	
}

@Test(priority=4, enabled=true)
public void addMenItem() throws InterruptedException {
	
	WebElement Mens= driver.findElement(By.cssSelector("a[id='ui-id-5'] span:nth-child(2)"));
	Mens.click();
	
//	//we used here .findElement (one element)
//	WebElement ItemsContainers= driver.findElement(By.cssSelector(".product-items.widget-product-grid"));
//	
//	//here we used .findElements (multiple elements) 
//	//int x= ItemsContainers.findElements(By.tagName("li")).size();
//	
//	List<WebElement> Items=ItemsContainers.findElements(By.tagName("li"));
//	
////	System.out.println(Items.size());
//	
//	//this code mo zabet 
////	Items.get(0).click();
////	
////	driver.navigate().back();
////	Thread.sleep(3000);
////    Items.get(1).click();
//
//	for(int i =0; i<Items.size();i++) {
//		Items.get(rand.nextInt(Items.size())).click();
//	}
//	
	//----note:----
		//theres no need for for loop or a loop to select any element 
		//we just have to deal with css selector or the web elements 
	
	
	//----------------------------------------
	//selecting a random item 
	WebElement productITemsContainer= driver.findElement(By.className("product-items"));
	
	List<WebElement> AllItems = productITemsContainer.findElements(By.tagName("li"));

	int totalNumberOfItems = AllItems.size();
	int randomItem = rand.nextInt(totalNumberOfItems);

	AllItems.get(randomItem).click();

	
	//selecting a random size 
	WebElement theContainerOfSizes = driver.findElement(By.cssSelector(".swatch-attribute-options.clearfix"));;

	String[] sizes = { "33", "34", "36", "37" };
	
	List<WebElement> ListOfSizes = theContainerOfSizes.findElements(By.className("swatch-option"));
	int numberofSizes = ListOfSizes.size();

	int randomSize = rand.nextInt(numberofSizes);
	ListOfSizes.get(randomSize).click();
	
	
	//selecting a random color 
	WebElement ColorsContainers= driver.findElement(By.cssSelector("div[class='swatch-attribute color'] div[role='listbox']"));
	//the colors are bunch of colors so they are list 
	List<WebElement> ColorsList = ColorsContainers.findElements(By.tagName("div"));
	int NumofColors=ColorsList.size();
	int randomColor=rand.nextInt(NumofColors);
	ColorsList.get(randomColor).click();
	
	
	//adding the ite to the cart
	WebElement AddTOCartButton= driver.findElement(By.id("product-addtocart-button"));
	AddTOCartButton.click();
	
	//msg
	WebElement MessageAdded = driver.findElement(By.cssSelector(".message-success.success.message"));
	System.out.println(MessageAdded.getText().contains("You added"));
	
	//actual/expected 
	Assert.assertEquals(MessageAdded.getText().contains("You added"), true);
	
}
	



}