package gflwishes.testcases;

import gflwishes.PageObjects.DispatchPO;
import gflwishes.PageObjects.LoginPage;
import gflwishes.base.EnhancedBaseClass;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

public class RODSP06 extends EnhancedBaseClass {

    public RODSP06() {
        log4j = Logger.getLogger("EndToEnd");
    }

    @Test
    public void TC034_Assign_an_order_which_requested_date_time_is_in_past_to_a_vehicle() {

        testCaseLog("TC034 : Assign an order which requested date time is in past to a vehicle");

        LoginPage login = new LoginPage(fleetMapperDriver);
        DispatchPO dispatchPO = new DispatchPO(fleetMapperDriver);
        login.loginAs(USER_NAME, PASSWORD);

        dispatchPO.openDispatcher();

        if (dispatchPO.verifyDispatchPage()) {
            success("User can see the dispatch page.");
        } else {
            failure("ERROR : Dispatch page is not display.");
        }

        dispatchPO.addTruckFromMapForPast();

        if (dispatchPO.verifyPastDateTruckMove()) {
            success("Verify error message for Past Date order");
        } else {
            failure("ERROR : Verify error message for Past Date order");
        }

    }

    @Test
    public void TC035_Assign_an_order_which_requested_date_time_is_today() {

        testCaseLog("TC035 : Assign an order which requested date time is today");

        LoginPage login = new LoginPage(fleetMapperDriver);
        DispatchPO dispatchPO = new DispatchPO(fleetMapperDriver);
        login.loginAs(USER_NAME, PASSWORD);

        dispatchPO.openDispatcher();

        if (dispatchPO.verifyDispatchPage()) {
            success("User can see the dispatch page.");
        } else {
            failure("ERROR : Dispatch page is not display.");
        }

        if (dispatchPO.isTodayOrderNotDisplay()) {
            dispatchPO.selectOrder(false);
            dispatchPO.selectCurrentDate();
        }

        dispatchPO.addTruckFromMapForToday();

        if (dispatchPO.verifyOrderAssigned()) {
            success("Verify order assigned successfully");
        } else {
            failure("ERROR : Verify order assigned successfully");
        }

        if (dispatchPO.orderStatusAssigned()) {
            success("Verify order status display assigned successfully");
        } else {
            failure("Verify order status display assigned successfully");
        }

        dispatchPO.filterOrder("assigned");

        dispatchPO.searchAddress();

        if (dispatchPO.verifyOrderFilteredAsAssigned()) {
            success("Verify order status display assigned successfully");
        } else {
            failure("Verify order status display assigned successfully");
        }

    }


}
