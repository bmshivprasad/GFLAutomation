package gflwishes.PageObjects;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage {

	public WebDriver ldriver;
	public  LandingPage(WebDriver rdriver) {
		ldriver=rdriver;
		PageFactory.initElements(rdriver,this);
	}
	@FindBy(xpath="//*[@id=\"userNameInput\"]")
	@CacheLookup
	WebElement loginname;
	
	@FindBy(xpath="//*[@id=\"passwordInput\"]")
	@CacheLookup
	WebElement passwordcredentials;
	
	@FindBy(xpath="//*[@id=\"submitButton\"]")
	@CacheLookup
	WebElement signin;
	
	@FindBy(xpath="//button[@aria-label='Open drawer']")
	@CacheLookup
	public WebElement threeline;	
	
	@FindBy(xpath="//div[text()='Project Management']")
	@CacheLookup
	WebElement pm;	
	
	@FindBy(xpath="//a[text()='Contracts']")
	@CacheLookup
	WebElement ct;
	
	
	@FindBy(xpath="//*[@id='root']/div/div/div[1]/div[2]/div/div/div/ul/li[2]/a")
	@CacheLookup
	WebElement pr;	
	
	
	@FindBy(xpath="//*[@id=\"root\"]/div/div/div[1]/div[2]/div/div/div/ul/li[3]/a")
	@CacheLookup
	WebElement pp;	
	public void clickusername(String username)
	{
		loginname.click();
		loginname.sendKeys(username);
	}
	public void clickpassword(String password) 
	{
		passwordcredentials.click();
		passwordcredentials.sendKeys(password);
		
	}
	public void signin()
	{
		signin.click();
	}
	public void clickpm()
	{
		pm.click();
	}
	
	public void clickct()
	{
		ct.click();
	}
	
	public void clickpr()
	{
		pr.click();
	}
	
	public void clickpp()
	{
		pp.click();
	}
	
	}
	
		
	
