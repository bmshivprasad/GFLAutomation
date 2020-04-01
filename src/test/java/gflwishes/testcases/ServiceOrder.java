package gflwishes.testcases;

import gflwishes.PageObjects.LandingPage;
import gflwishes.PageObjects.LoginPage;
import gflwishes.PageObjects.ServiceOrderPage;
import gflwishes.base.EnhancedBaseClass;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import java.io.IOException;

//import java.util.List;
//import java.util.Random;
//import org.openqa.selenium.*;

public class ServiceOrder extends EnhancedBaseClass {

    public ServiceOrder() {
        log4j = Logger.getLogger("Service Order");
    }


    public static int getRandomIntBetweenRange(int min, int max) {
        int x = (int) ((Math.random() * ((max - min) + 1)) + min);
        return x;
    }

    @Test
    public void TC004_to_TC011_Verify_Create_Service_order_Functionality() throws IOException, InterruptedException {

        testCaseLog("TC0011_Verify_Create_Service_order_Functionality");

        LoginPage login = new LoginPage(driver);
        LandingPage lp = new LandingPage(driver);
        ServiceOrderPage cp = new ServiceOrderPage(driver);//object creation for project page
        int rows = cp.getRowsExcel();

        login.loginAs(USER_NAME, PASSWORD);

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
                cp.clickonApprovebutton();
                cp.typeApprovalReason();
                cp.clickonReasonApprovebutton();


                if (cp.isAllDetailsSavedCurreclty()) {
                    success("Services saved with all details");
                } else {
                    failure("Services not saved with all details");
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
                continue;
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