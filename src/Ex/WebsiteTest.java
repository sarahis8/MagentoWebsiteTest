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

	//	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

	}

	@Test
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


@Test(priority =2)
public void  Logout() {
	//since the logout button has a link inside its element 
	//we can use it in testing whixch is easy without any webelement 
	
	driver.get(LogoutPage);
	
}

@Test (priority =3)
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


}