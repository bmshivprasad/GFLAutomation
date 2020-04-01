package gflwishes.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Parameters;

public class LoginPage {
	
public WebDriver driver;

public WebDriver ldriver;
public LoginPage(WebDriver rdriver) {
	ldriver=rdriver;
	PageFactory.initElements(rdriver,this);
}

@FindBy(xpath="//input[@id='userNameInput']")
@CacheLookup
WebElement Username_txtbox;   


@FindBy(xpath="//*[@id='passwordInput']")
@CacheLookup
WebElement Password_txtbox;   

@FindBy(xpath="//*[@id='submitButton']")
@CacheLookup
WebElement Signin_btn;   	
	

	public void enterCredentials(String username,String password) {		
		
		Username_txtbox.sendKeys(username);
		Password_txtbox.sendKeys(password);
		Signin_btn.submit();
		
	}
	
}
