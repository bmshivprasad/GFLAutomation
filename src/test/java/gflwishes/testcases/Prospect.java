package gflwishes.testcases;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;


import gflwishes.PageObjects.LandingPage;
import gflwishes.PageObjects.LoginPage;
import gflwishes.PageObjects.ProspectPage;
import gflwishes.base.EnhancedBaseClass;
import gflwishes.base.Generics;

public class Prospect extends EnhancedBaseClass {

	 public Prospect() {
	        log4j = Logger.getLogger("Prospect");
	    }


	    public static int getRandomIntBetweenRange(int min, int max) {
	        int x = (int) ((Math.random() * ((max - min) + 1)) + min);
	        return x;
	    }

	    @Test
	    public void TC001WS_Verify_Create_new_Prospect_Functionality() {

	        testCaseLog("TC001_TC002_TC003_Verify_Create_new_Prospect_Functionality");

	        LoginPage login = new LoginPage(driver);
	        LandingPage lp = new LandingPage(driver);
	        ProspectPage pp = new ProspectPage(driver);
	        int rows = pp.getRowsExcel();

	        login.loginAs(USER_NAME, PASSWORD);

	        if (lp.isUserLoginSuccessful()) {
	            success("User Login Successful");
	        } else {
	            failure("Failed to Login");
	        }

	        for (int i = 1; i < rows - 1; i++) {
	            try {
	        lp.OpenProspect();
	         
	        if (pp.isProspectPageOpen()) {
                success("Prospect page open successfully");
            } else {
                failure("Prospect page not open");
            }
	        
	        pp.clickonAddProspectButton();
	        
	        if (pp.isPopupdisplayed()) {
                success("Popup displayed");
            } else {
                failure("Popup not displayed");
            }
	        pp.typeProspectname(i);
	        pp.clickonCreateNewProspectlnk();
	        if (pp.isEnteredProspectDisplayed()) {
                success("Entered Prosepect name displayed as companyname");
            } else {
                failure("Entered Prosepect name not displayed as companyname");
            }
	        pp.typeWebsite();
	        pp.selectBillingCurrency();
	        pp.selectSalesRep();
	        pp.selectBusinessUnit1(i);
	        pp.selectcustomertype();
	        pp.selectJurisdiction();
	        pp.selectAddressline1();
	        pp.selectBillingAddAsCompanyAdd();
	        pp.typeContact();
	        pp.typeEmail();
	        pp.typeContactPosition();
	        pp.typePhoneNumber();
	        pp.typeExtention(); 
	        pp.typeSiteName(i);
	        pp.selectBusinessUnit(); 
	        pp.selectBusinessType();
	        
	        pp.selectAddressline1ofSite(i);
	        pp.typePostalcode(i);
	        pp.SelectbillToCustomerBillingAddress();
	        pp.SelectSitecontactsameasprimarycontact();
	        pp.clickonbtnSaveCustomer();
	        
	        if (pp.isProspectCreatedSuccessful()) {
                success("Prospect created successfull message validation.");
            } else {
                failure("Prospect not created.");
            }
	        
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	       
	        sa.assertAll();
	    }

	
}
