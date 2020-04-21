package gflwishes.testcases;

import gflwishes.PageObjects.CustomerPage;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;


import gflwishes.PageObjects.LandingPage;
import gflwishes.PageObjects.LoginPage;
import gflwishes.PageObjects.ProspectPage;
import gflwishes.base.EnhancedBaseClass;

public class Prospect extends EnhancedBaseClass {

	 public Prospect() {
	        log4j = Logger.getLogger("Prospect");
	    }


	    public static int getRandomIntBetweenRange(int min, int max) {
	        int x = (int) ((Math.random() * ((max - min) + 1)) + min);
	        return x;
	    }

	@Test
	public void TC001WS_Verify_Create_Prospect_Functionality_with_ServiceType_FrontEnd() {

		testCaseLog("TC001WS_Verify_Create_Prospect_Functionality_with_ServiceType_FrontEnd");

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

		for (int i = 1; i < rows ; i++) {

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

				pp.clickonAddNewServiceButton();
				//pp.clickonNextButton();

				//pp.clickonAddServiceButton();
				pp.selectServiceTypeFE();
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
				excelUtils.UpdateExternalSiteID("ProspectAll",i);
			}
			catch (Exception e) {
				System.out.print("Prospect not created");
				continue;
			}
		}
		sa.assertAll();

	}
}
