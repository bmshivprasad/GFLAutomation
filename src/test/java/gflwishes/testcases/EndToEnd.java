package gflwishes.testcases;

import gflwishes.PageObjects.*;
import gflwishes.base.EnhancedBaseClass;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

public class EndToEnd extends EnhancedBaseClass {

    public EndToEnd() {
        log4j = Logger.getLogger("Service Order");
    }

    @Test
    public void TC001WS_Verify_Create_new_Customer_Functionality() {

        testCaseLog("TC001_TC002_TC003_Verify_Create_new_Customer_Functionality");

        LoginPageUpdated login = new LoginPageUpdated(driver);
        LandingPageUpdated lp = new LandingPageUpdated(driver);
        CustomerPage cp = new CustomerPage(driver);
        int rows = cp.getRowsExcel();

        login.loginAs(USER_NAME, PASSWORD);

        if (lp.isUserLoginSuccessful()) {
            success("User Login Successful");
        } else {
            failure("Failed to Login");
        }

        for (int i = 1; i < rows - 1; i++) {
            try {
                lp.OpenCustomer();


                if (cp.isCustomerPageOpen()) {
                    success("Customers page open successfully");
                } else {
                    failure("Customers page not open");
                }
                cp.clickonAddCustomerButton();
                if (cp.isPopupdisplayed()) {
                    success("Popup displayed");
                } else {
                    failure("Popup not displayed");
                }
                cp.typeCustomername(i);
                cp.clickonCreateNewCustomerlnk();
                if (cp.isEnteredCustomerDisplayed()) {
                    success("Entered customer name displayed as companyname");
                } else {
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
                if (cp.isSuccessMessageDisplayed()) {
                    success("Success Message displayed");
                } else {
                    failure("Success message not displayed");
                }
                if (cp.isCustomerAdded()) {
                    success("Customer Added successfully");
                } else {
                    failure("Customer not Added successfully");
                }
                cp.getCustomerID(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        sa.assertAll();
    }

    @Test
    public void TC002WS_Verify_Create_Service_order_Functionality() {

        testCaseLog("TC0011_Verify_Create_Service_order_Functionality");

        LandingPageUpdated lp = new LandingPageUpdated(driver);
        ServiceOrderPage cp = new ServiceOrderPage(driver);
        int rows = cp.getRowsExcel();

        //  new LoginPageUpdated(driver).loginAs(USER_NAME, PASSWORD);

        if (lp.isUserLoginSuccessful()) {
            success("User Login Successful");
        } else {
            failure("Failed to Login");
        }

        for (int i = 1; i < rows - 1; i++) {

            try {
                lp.OpenServiceOrder();


                if (cp.isServiceOrderPageOpen()) {
                    success("Service Order page open successfully");
                } else {
                    failure("Service Order page not open");
                }
                cp.clickonTempServicebtn();
                if (cp.isPopupdisplayed()) {
                    success("Popup displayed");
                } else {
                    failure("Popup not displayed");
                }
                cp.typeCustomername(i);
                cp.selectCustomer();
                cp.selectSite(i);

                if (cp.isSelectedCustomerOpen()) {
                    success("Selected Customer Opens successfully");
                } else {
                    failure("Selected customer not opend");
                }

                cp.selectMaterial(i);
                cp.selectContainerSize(i);
                cp.selectHaulType(i);
                if (cp.isServiceSectionDisplayed()) {
                    success("Services Details Section displayed on Haul Type selection");
                } else {
                    failure("Services detail section not displayed on Haul type selection");
                }
                cp.selectContainerType();
                //cp.selectRequiestedDate();
                cp.InsertUploadingTime();
                cp.selectDesposibleSite();
                cp.selectBillingCycle();
                cp.typeDemurageDay();
                cp.clickonGetPricing();
                if (cp.isServiceChargesectionDisplayed()) {
                    success("Get Price button working and displayed service charges");
                } else {
                    failure("Get Price button not working or service charges not displayed");
                }
                cp.typeDispatchnote();
                cp.typeDrivernote();
                cp.clickonSaveServiceButton();
                if (cp.isServiceSaved()) {
                    success("Save Service button is working");
                } else {
                    failure("Save Service button is not working");
                }
                if (cp.isApproveDisplay()) {
                    cp.clickonApprovebutton();
                    cp.typeApprovalReason();
                    cp.clickonReasonApprovebutton();


                    if (cp.isAllDetailsSavedCurreclty()) {
                        success("Services saved with all details");
                    } else {
                        failure("Services not saved with all details");
                    }
                }

		        /*
		        cp.clickonAddServiceOrderButton();
		        cp.selectHaulType(i);
		        cp.clickonSavebtnOfServiceOrder();
		        
		        if(cp.isNewServiceOrderAddded())
		        {
		          	success("New Service order added successfully");
		        }
		        else
		        {
		        	failure("New Service Order not saved");
		        }*/
                cp.clickonPaynow();
                cp.SelectPaymentMethod();
                cp.typeCVV();
                cp.SelectAddress();
                cp.SelectConfirmationCheckbox();
                cp.UploadFile();
                //cp.zoomout();
                cp.ClickonPayAmount();
                if (cp.isPaymentDone()) {
                    success("Paymenet Done successfully");
                } else {
                    failure("Payment not done successfully");
                }
            } catch (Exception e) {
                System.out.print("Service Order not created : ");
                e.printStackTrace();
            }

        }
        sa.assertAll();
    }

    @Test
    public void TC003FM_Verify_User_can_complete_pickup_order() {

        testCaseLog("Verify_User_can_complete_pickup_order");

        LoginPageUpdated login = new LoginPageUpdated(driver);
        DispatchPO dispatchPO = new DispatchPO(driver);

        login.selectSignIn(USER_NAME);

        dispatchPO.openDispatcher();

        if (dispatchPO.verifyDispatchPage()) {
            success("User can see the dispatch page.");
        } else {
            failure("ERROR : Dispatch page is not display.");
        }

        dispatchPO.searchAddress();
        dispatchPO.selectOrder();

        if (dispatchPO.verifyDeliveryDetails()) {
            success("User can see the dispatch oder details.");
        } else {
            failure("ERROR : Details are not display proper.");
        }

        if (dispatchPO.isOrderFromPast()) {
            dispatchPO.selectCurrentDate();
        }

        dispatchPO.addTruckFromMap();

        dispatchPO.openOrderFromVehiclePane();

        dispatchPO.startOrder();

        dispatchPO.enterContainerName();

        dispatchPO.enterTicketDetails();

        dispatchPO.completeOrder();

    }

    @Test
    public void TC004WS_Verify_All_deatails_in_wishes_if_service_order_status_Change_from_FM() {

        testCaseLog("TC005_Verify_All_deatails_in_wishes_if_service_order_status_Change_from_FM");

        LandingPageUpdated lp = new LandingPageUpdated(driver);
        ServiceOrderPage cp = new ServiceOrderPage(driver);
        int rows = cp.getRowsExcel();

        //login.selectSignIn(USER_NAME);

        if (lp.isUserLoginSuccessful()) {
            success("User Login Successful");
        } else {
            failure("Failed to Login");
        }

        for (int i = 1; i < rows - 1; i++) {

            try {
                lp.OpenServiceOrder();
                cp.changepagesize();
                cp.getCustomerName(i);
                cp.openServiceOrder();
                if (cp.isProperStatusDisplayed(i)) {
                    success("Proper Status of service order displayed");

                } else {
                    failure("Proper status of service order not displayed");
                }
                if (cp.isProperVehicleDisplayed(i)) {
                    success("Proper assigned Vehicle of service order displayed");

                } else {
                    failure("Proper Vehicle of service order not displayed");
                }
                if (cp.isProperDispatcherDisplayed(i)) {
                    success("Proper Dispatcher value of service order displayed");

                } else {
                    failure("Proper Dispatcher of service order not displayed");
                }

                if (cp.isProperDispatcherNoteDisplayed(i)) {
                    success("Proper Dispatcher note value of service order displayed");

                } else {
                    failure("Proper Dispatcher note value of service order not displayed");
                }
                if (cp.isProperDriverNoteDisplayed(i)) {
                    success("Proper Driver note value of service order displayed");

                } else {
                    failure("Proper driver note value of service order not displayed");
                }
                if (cp.isProperDriverDisplayed(i)) {
                    success("Proper Driver value of service order displayed");

                } else {
                    failure("Proper Driver value of service order not displayed");
                }


            } catch (Exception e) {
                System.out.print("Service Order not created : ");
            }

        }
        sa.assertAll();
    }
    
    /*
    @Test
    public void TC012_Assign_service_order_to_Vehical_and_Complete_Dispatching() throws IOException, InterruptedException
    {

        testCaseLog("TC012_Assign_service_order_to_Vehical_and_Complete_Dispatching");

        
        LoginPageUpdated login = new LoginPageUpdated(driver);
        LandingPageUpdated lp = new LandingPageUpdated(driver);
        ServiceOrderPage cp= new ServiceOrderPage(driver);//object creation for project page
        int rows=cp.getRowsExcel();
        driver.get("https://fleetmapper-qa.azurewebsites.net");
        
        login.loginAs(USER_NAME, PASSWORD);
        cp.openDispatcher();
        testStepsLog("Dispatcher page open");
                
        //cp.changeOrderDate();
        //cp.changeDate();
        cp.getEmptyVehicalname();
        cp.dragDeliveryOrderToTruck();
        cp.openVehicalDetail();
        cp.startOrder();
        
        
        //cp.openOrdersList();
        testStepsLog("Opening new tab");
        for(int i=1;i<rows-1;i++)
        {
        	
        
        	try
        	{
        		//WebElement body = driver.findElement(By.tagName("body"));
                //body.sendKeys(Keys.CONTROL + "t");
                
                ((JavascriptExecutor) driver).executeScript("window.open()");
                ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
                driver.switchTo().window(tabs.get(1));
                
                
        		driver.get("https://wishes-qa.azurewebsites.net");
        		driver.switchTo().window(tabs.get(0));
        		testStepsLog("back to first window");
        		driver.switchTo().window(tabs.get(1));
        		login.loginAs(USER_NAME, PASSWORD);
		        lp.OpenServiceOrder();
		        cp.changepagesize();
		        cp.getCustomerName(i);
		        cp.openServiceOrder();
		        if(cp.isProperStatusDisplayed(i))
		        {
		        	success("Proper Status of service order displayed");
		        	
		        }
		        else
		        {
		        	failure("Proper status of service order not displayed");
		        }
		        
		        
		        
        	}
        	catch(Exception e)
        	{
        		System.out.print("Service Order not created : " );
        		continue;
        	}
       
        } 
        sa.assertAll();
    }
    
    
    
    @Test
    public void TC005_Verify_All_deatails_in_wishes_if_service_order_status_Change_from_FM() throws IOException, InterruptedException
    {

        testCaseLog("TC005_Verify_All_deatails_in_wishes_if_service_order_status_Change_from_FM");

        LoginPageUpdated login = new LoginPageUpdated(driver);
        LandingPageUpdated lp = new LandingPageUpdated(driver);
        ServiceOrderPage cp= new ServiceOrderPage(driver);//object creation for project page
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
		        lp.OpenServiceOrder();
		        cp.changepagesize();
		        cp.getCustomerName(i);
		        cp.openServiceOrder();
		        if(cp.isProperStatusDisplayed(i))
		        {
		        	success("Proper Status of service order displayed");
		        	
		        }
		        else
		        {
		        	failure("Proper status of service order not displayed");
		        }
		        if(cp.isProperVehicleDisplayed(i))
		        {
		        	success("Proper assigned Vehicle of service order displayed");
		        	
		        }
		        else
		        {
		        	failure("Proper Vehicle of service order not displayed");
		        }
		        if(cp.isProperDispatcherDisplayed(i))
		        {
		        	success("Proper Dispatcher value of service order displayed");
		        	
		        }
		        else
		        {
		        	failure("Proper Dispatcher of service order not displayed");
		        }
		       
		        if(cp.isProperDispatcherNoteDisplayed(i))
		        {
		        	success("Proper Dispatcher note value of service order displayed");
		        	
		        }
		        else
		        {
		        	failure("Proper Dispatcher note value of service order not displayed");
		        }
		        if(cp.isProperDriverNoteDisplayed(i))
		        {
		        	success("Proper Driver note value of service order displayed");
		        	
		        }
		        else
		        {
		        	failure("Proper driver note value of service order not displayed");
		        }
		        if(cp.isProperDriverDisplayed(i))
		        {
		        	success("Proper Driver value of service order displayed");
		        	
		        }
		        else
		        {
		        	failure("Proper Driver value of service order not displayed");
		        }
		        
		        
        	}
        	catch(Exception e)
        	{
        		System.out.print("Service Order not created : " );
        		continue;
        	}
       
        } 
        sa.assertAll();
    }
     */

}