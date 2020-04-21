package gflwishes.testcases;

import gflwishes.PageObjects.DispatchPO;
import gflwishes.PageObjects.LandingPage;
import gflwishes.PageObjects.LoginPage;
import gflwishes.PageObjects.ServiceOrderPage;
import gflwishes.base.EnhancedBaseClass;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import java.io.IOException;

public class Dispatch extends EnhancedBaseClass {

    protected Dispatch() {
        log4j = Logger.getLogger("Dispatch");
    }

    @Test
    public void TC001_Verify_User_can_complete_pickup_order() {

        testCaseLog("Verify_User_can_complete_pickup_order");

        LoginPage login = new LoginPage(wishesDriver);
        DispatchPO dispatchPO = new DispatchPO(wishesDriver);

        int count = 0;

        login.openFM();
        login.loginAs(USER_NAME, PASSWORD);

        dispatchPO.openDispatcher();

        if (dispatchPO.verifyDispatchPage()) {
            success("User can see the dispatch page.");
        } else {
            failure("ERROR : Dispatch page is not display.");
        }

        dispatchPO.searchAddress(count);
        dispatchPO.selectOrder(true);

        if (dispatchPO.verifyDeliveryDetails(count)) {
            success("User can see the dispatch oder details.");
        } else {
            failure("ERROR : Details are not display proper.");
        }

        if (dispatchPO.isOrderFromPast()) {
            dispatchPO.selectCurrentDate();
        }

        dispatchPO.addTruckFromMap(count);

        dispatchPO.openOrderFromVehiclePane();

        dispatchPO.startOrder();

        dispatchPO.enterPickUpContainerName();

        dispatchPO.enterTicketDetails(count);

        dispatchPO.completeOrder(count);

    }

    @Test
    public void TC005_Verify_All_deatails_in_wishes_if_service_order_status_Change_from_FM() throws IOException, InterruptedException {

        testCaseLog("TC005_Verify_All_deatails_in_wishes_if_service_order_status_Change_from_FM");

        LoginPage login = new LoginPage(wishesDriver);
        LandingPage lp = new LandingPage(wishesDriver);
        ServiceOrderPage cp = new ServiceOrderPage(wishesDriver);//object creation for project page
        int rows = cp.getRowsExcel();
        wishesDriver.get("https://wishes-qa.azurewebsites.net/");
        login.loginAs(USER_NAME, PASSWORD);

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
                continue;
            }

        }
        sa.assertAll();
    }

}
