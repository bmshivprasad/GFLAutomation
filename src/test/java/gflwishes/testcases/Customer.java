package gflwishes.testcases;

import java.awt.Desktop.Action;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
//import java.util.List;
//import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
//import org.openqa.selenium.*;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import gflwishes.PageObjects.CustomerPage;
import gflwishes.PageObjects.LandingPageUpdated;
import gflwishes.base.EnhancedBaseClass;
import gflwishes.utilities.ExcelUtils;
import gflwishes.PageObjects.LoginPageUpdated;
public class Customer extends EnhancedBaseClass
{

    public Customer() {
        log4j = Logger.getLogger("Customer");
    }


    public static int getRandomIntBetweenRange(int min, int max){
        int x = (int) ((Math.random()*((max-min)+1))+min);
        return x;
    }


    @Test
    public void TC001_TC002_TC003_Verify_Create_new_Customer_Functionality() throws IOException, InterruptedException
    {

        testCaseLog("TC001_TC002_TC003_Verify_Create_new_Customer_Functionality");

        LoginPageUpdated login = new LoginPageUpdated(driver);
        LandingPageUpdated lp = new LandingPageUpdated(driver);
        CustomerPage cp= new CustomerPage(driver);//object creation for project page
        int rows=cp.getRowsExcel();
        
        login.loginAs(USER_NAME, PASSWORD);

        if (lp.isUserLoginSuccessful()) {
            success("User Login Successful");
        } else {
            failure("Failed to Login");
        }
        
        for(int i=1;i<rows-1;i++)
        {
        	try
        	{
		        lp.OpenCustomer();
		             
		        
		        if(cp.isCustomerPageOpen())
		        {
		        	success("Customers page open successfully");
		        }
		        else
		        {
		        	failure("Customers page not open");
		        }
		        cp.clickonAddCustomerButton();
		        if(cp.isPopupdisplayed())
		        {
		        	success("Popup displayed");
		        }
		        else
		        {
		        	failure("Popup not displayed");
		        }
		        cp.typeCustomername(i);
		        cp.clickonCreateNewCustomerlnk();
		        if(cp.isEnteredCustomerDisplayed())
		        {
		        	success("Entered customer name displayed as companyname");
		        }
		        else
		        {
		        	failure("Entered customer name not displayed as companyname");
		        }
		        cp.selectBusinessUnit1(i);
		        cp.selectJurisdiction();
		        cp.selectcustomertype();
		        cp.selectAddressline1();
		        //cp.selectcountry();
		        //cp.selectState();
		        //cp.typeCity();
		        
		        cp.selectBillingAddAsCompanyAdd();
		        cp.typeContact();
		        cp.typeEmail();
		        cp.typeContactPosition();
		        cp.typePhoneNumber();
		        cp.typeExtention();
		        cp.typeSiteName(i);
		        cp.selectBusinessUnit();
		        cp.selectBusinessType();
		        cp.typePoNumber();
		        cp.DeSelectsiteAddressesSameAsCompanyAddresses();
		        cp.selectAddressline1ofSite(i);
		        cp.typePostalcode(i);
		        cp.SelectbillToCustomerBillingAddress();
		        cp.clickonbtnSaveCustomer();
		        if(cp.isSuccessMessageDisplayed())
		        {
		        	success("Success Message displayed");
		        }
		        else
		        {
		        	failure("Success message not displayed");
		        }
		        if(cp.isCustomerAdded())
		        {
		        	success("Customer Added successfully");
		        }
		        else
		        {
		        	failure("Customer not Added successfully");
		        }
		        cp.getCustomerID(i);
        	}
        	catch(Exception e)
        	{
        		System.out.print("Customer not created : " );
        		continue;
        	}
        }
        
        sa.assertAll();
    }
    
    @Test
    public void TC004_Edit_Customer_Functionality() throws IOException, InterruptedException
    {

        testCaseLog("TC004_Edit_Customer_Functionality");

        LoginPageUpdated login = new LoginPageUpdated(driver);
        LandingPageUpdated lp = new LandingPageUpdated(driver);
        CustomerPage cp= new CustomerPage(driver);//object creation for project page


        login.loginAs(USER_NAME, PASSWORD);

        if (lp.isUserLoginSuccessful()) {
            success("User Login Successful");
        } else {
            failure("Failed to Login");
        }

       
        lp.OpenCustomer();
             
        
        if(cp.isCustomerPageOpen())
        {
        	success("Customers page open successfully");
        }
        else
        {
        	failure("Customers page not open");
        }
        
        cp.openfirstCustomer();
        cp.clickonEditCustomer();
        cp.EditContactPosition();
        cp.clickonSaveChanges();
        if(cp.ischangesSaved())
        {
        	success("Edit Customers functionality is working");
        }
        else
        {
        	failure("Edit Customers Functionality not working");
        }
        	
        
        
        sa.assertAll();
    }
    
   
    
      
}