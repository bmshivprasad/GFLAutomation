package gflwishes.testcases;

import gflwishes.PageObjects.*;
import gflwishes.base.EnhancedBaseClass;
import gflwishes.utilities.ExcelUtils;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

public class EndToEndProspect extends EnhancedBaseClass {

    public EndToEndProspect() {
        log4j = Logger.getLogger("EndToEnd");
    }

    @Test
    public void TC03FM_Create_Vehicle_Functionality() {

        testCaseLog("TC03_Create_Vehicle_Functionality");

        LoginPage login = new LoginPage(fleetMapperDriver);
        vehiclePage vp=new vehiclePage(fleetMapperDriver);
        EndtoEndProspectPage vc=new EndtoEndProspectPage(fleetMapperDriver);

        login.loginAs(USER_NAME, PASSWORD);

        vc.getServiceZone();
        vc.OpenRegion();
        vc.ClickonVehicleTab();
        vc.clickonAddVehiclebutton();
        vp.selectBusinessUnit();
        vc.typeVehicalname();

        vc.typeVin();
        vc.selectServiceZone();
        vc.selectVehicleType();
        vc.clickonSaveButton();
        if (vc.isvehicleCreated()) {
            success("Vehicle Created Successfully");
        } else {
            failure("Vehicle not created");
        }
        vc.copyVehicleInProspectExcel();
    }

    @Test
    public void TC001WS_Verify_Create_new_Customer_Functionality() {

        testCaseLog("TC001_TC002_TC003_Verify_Create_new_Customer_Functionality");

        LoginPage login = new LoginPage(wishesDriver);
        LandingPage lp = new LandingPage(wishesDriver);
        CustomerPage cp = new CustomerPage(wishesDriver);

        ProspectPage pp = new ProspectPage(wishesDriver);
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
                pp.SelectSiteContactSameAsPrimaryContact();
                pp.clickonbtnSaveCustomer();

                if (pp.isProspectCreatedSuccessful()) {
                    success("Prospect created successfully.");
                } else {
                    failure("Prospect not created.");
                }

                pp.clickonCreateQuote();
                //pp.clickonCreateQuote();
                pp.clickonNextButton();
                //pp.clickonNextButton();
                pp.clickonAddServiceButton();
                pp.selectServiceType();
                pp.typeContainerCount(i);
                pp.SelectContainerType(i);
                pp.typeContainerFee(i);
                pp.SelectContainerSize(i);
                pp.SelectFreuency(i);
                pp.SelectChargeType(i);
                pp.SelectMaterial(i);
                pp.SelectHaulType(i);
                pp.typeEstTime(i);
                pp.selectDisposibleSite();

                pp.typeLocationType(i);

                pp.clickonCalculate();
                pp.typenote();
                pp.clickonAddService();
                pp.clickonUpdateAgreement();
                if(pp.isAggreementUpdated())
                {
                    success("Aggreeement updpated successfully ");
                }
                else
                {
                    failure("Aggreeement not updated successfully");
                }

                pp.clickonSaveAndSubmitCSA();
                pp.clickonSubmitButton();
                if(pp.isCSASaved())
                {
                    success("CSA Saved successfully");
                }
                else
                {
                    failure("CSA not Saved successfully");
                }

                lp.OpenAgreements();

                if (pp.isAgreementsPageOpen()) {
                    success("Agreements page open successfully");
                } else {
                    failure("Agreements page not open");
                }

                pp.clickonAgreementsNo();
                pp.clickonCustomerCopy();
                pp.clickonChkAgreementTearmsAndCondition();
                pp.clickonMarkAsSigned();
                pp.SelectIconfirmthatthisCSAhasbeensigned();
                pp.clickonDropFileHereorClicktoUpload();
                pp.clickonSubmitDocument();
                pp.clickonSubmitCDE();

                if (pp.isPopupdisplayedCSAtoCDE()) {
                    success("CSAtoCDE Popup displayed. ");
                } else {
                    failure("CSAtoCDE Popup not displayed.");
                }

                pp.clickonSubmit();
                pp.clickonSubmittoCDE();

                lp.OpenCDEDashboard();
                pp.ClickonUnassigned();

                if (pp.isTicketDetailsOpen()) {
                    success("Ticket Details page Open Successfully.");
                } else {
                    failure("Ticket Details page not Opened.");
                }
                pp.typeTruxCustomerNo();
                pp.typeSiteNo();
                pp.typeServiceLineItem();
                pp.SelectDocumentsReviewed();
                pp.typeLeaveAComment();
                pp.ClickonComplete();
                if (pp.isCpmpleteTicketpopup()) {
                    success("Complete Ticket Popup displayed. ");
                } else {
                    failure("Complete Ticket Popup not displayed.");
                }
                pp.ClickonPopComplete();

                if (pp.isCpmplettticketmsg()) {
                    success("Ticket has been completed successfully ! message verification ");
                } else {
                    failure("Ticket has been completed successfully ! meddage not verified.");
                }
                pp.getProspecctID(i);
            }
            catch (Exception e) {
                System.out.print("Prospect not created");
                continue;
            }
        }
        sa.assertAll();
        excelUtils.UpdateExternalSiteID();
    }

    @Test
    public void TC002WS_Verify_Create_Service_order_Functionality() {

        testCaseLog("TC0011_Verify_Create_Service_order_Functionality ");

        LandingPage lp = new LandingPage(wishesDriver);
        EndtoEndProspectPage cp = new EndtoEndProspectPage(wishesDriver);
        //EndtoEndProspectPage pp = new EndtoEndProspectPage(wishesDriver);
        int rows = cp.getRowsExcel();

        for (int i = 1; i < rows - 1; i++) {
            try {
                String CustID = cp.getCustomerIDStatus(i);
                if (CustID != "") {
                    lp.OpenServiceOrder();

                    if (cp.isServiceOrderPageOpen()) {
                        success("Service Order page open successfully");
                    } else {
                        failure("Service Order page not open");
                    }
                    cp.clickonTempServicebtn();
                    if (cp.isPopupdisplayedcus()) {
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
                    //cp.selectDesposibleSite();
                    cp.selectBillingCycle();
                    cp.typeDemurageDay();
                    cp.clickonGetPricing();
                    if (cp.isServiceChargesectionDisplayed()) {
                        success("Get Price button working and displayed service charges");
                    } else {
                        failure("Get Price button not working or service charges not displayed");
                    }
                    cp.typeDispatchnote(i);
                    cp.typeDrivernote(i);
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


                    cp.clickonPaynow();
                    cp.SelectPaymentMethod();
                    cp.typeCVV();
                    cp.SelectAddress();
                    cp.SelectConfirmationCheckbox();
                    cp.UploadFile();
                    // cp.ClickonPayAmount();
                    if (cp.isPaymentDone()) {
                        success("Paymenet Done successfully");
                        cp.UpdateStatus(i);
                    } else {
                        failure("Payment not done successfully");
                    }

                } else {
                    continue;
                }
            } catch (Exception e) {
                testStepsLog("Service order not Created : " + i);
            }
        }
        sa.assertAll();
    }

    @Test
    public void TC003FM_Verify_User_can_complete_pickup_order() {

        testCaseLog("Verify_User_can_complete_pickup_order");

        LoginPage login = new LoginPage(fleetMapperDriver);
        EndtoEndProspectPage dispatchPO = new EndtoEndProspectPage(fleetMapperDriver);

        dispatchPO.openDispatcher();

        if (dispatchPO.verifyDispatchPage()) {
            success("User can see the dispatch page.");
        } else {
            failure("ERROR : Dispatch page is not display.");
        }

        for (int count = 1; count < ExcelUtils.getRowsExcel(getClass().getSimpleName()) - 1; count++) {

            try {
                if (dispatchPO.isPaymentDone(count)) {

                    dispatchPO.getDispatcherName(count);

                    dispatchPO.searchAddress(count);
                    dispatchPO.selectOrder();

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

                    switch (dispatchPO.getOperationType().toLowerCase()) {
                        case "pick up":
                            dispatchPO.startOrder();
                            dispatchPO.enterPickUpContainerName();
                            dispatchPO.enterDriverNotes(count);
                            dispatchPO.completeOrder(count);
                            break;
                        case "delivery":
                            dispatchPO.startOrder();
                            dispatchPO.enterDropOffContainerName();
                            dispatchPO.enterDriverNotes(count);
                            dispatchPO.completeOrder(count);
                            break;
                        case "exchange":
                            if (dispatchPO.isIconUpward()) {
                                dispatchPO.startOrder();
                                dispatchPO.enterPickUpContainerName();
                                dispatchPO.enterTicketDetails(count);
                                dispatchPO.enterDropOffContainerName();
                                dispatchPO.enterDriverNotes(count);
                                dispatchPO.completeOrder(count);
                            } else {
                                dispatchPO.startOrder();
                                dispatchPO.enterDropOffContainerName();
                                dispatchPO.enterPickUpContainerName();
                                dispatchPO.enterTicketDetails(count);
                                dispatchPO.enterDriverNotes(count);
                                dispatchPO.completeOrder(count);
                            }
                            break;
                        case "empty & return":
                            dispatchPO.startOrder();
                            dispatchPO.enterPickUpContainerName();
                            dispatchPO.enterTicketDetails(count);
                            dispatchPO.enterDropOffContainerName();
                            dispatchPO.enterDriverNotes(count);
                            dispatchPO.completeOrder(count);
                            break;
                        case "move":
                            dispatchPO.startOrder();
                            dispatchPO.enterPickUpContainerName();
                            dispatchPO.enterDropOffContainerName();
                            dispatchPO.enterDriverNotes(count);
                            dispatchPO.completeOrder(count);
                            break;
                        case "pickup directive":
                            dispatchPO.startOrder();
                            dispatchPO.enterPickUpContainerName();
                            break;
                        case "drop directive":
                            dispatchPO.startOrder();
                            dispatchPO.enterDropOffContainerName();
                            break;
                    }
                    dispatchPO.setFlag(count, true);
                }
            } catch (Exception ignored) {
                dispatchPO.setFlag(count, false);
            }
        }
    }

    @Test
    public void TC004WS_Verify_All_deatails_in_wishes_if_service_order_status_Change_from_FM() {

        testCaseLog("TC005_Verify_All_deatails_in_wishes_if_service_order_status_Change_from_FM");

        LandingPage lp = new LandingPage(wishesDriver);
        ServiceOrderPage cp = new ServiceOrderPage(wishesDriver);
        int rows = cp.getRowsExcel();


        for (int i = 1; i < rows - 1; i++) {

            try {
                if (cp.isFMCompleted(i)) {

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
                    if (cp.isProperScaleDisplayed(i)) {
                        success("Proper Driver note value of service order displayed");

                    } else {
                        failure("Proper driver note value of service order not displayed");
                    }
                    if (cp.isProperweightDisplayed(i)) {
                        success("Proper Driver value of service order displayed");

                    } else {
                        failure("Proper Driver value of service order not displayed");
                    }
                } else {
                    continue;
                }
            } catch (Exception e) {
                testStepsLog("FM to Wishes comparision fail : " + i);
            }

        }
        sa.assertAll();
    }

}