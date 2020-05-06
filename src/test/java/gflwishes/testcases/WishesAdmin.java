package gflwishes.testcases;

import gflwishes.PageObjects.ProspectPage;
import gflwishes.PageObjects.WishesAdminPage;
import gflwishes.PageObjects.LandingPage;
import gflwishes.PageObjects.LoginPage;

import gflwishes.base.EnhancedBaseClass;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

public class WishesAdmin extends EnhancedBaseClass
{

	 public WishesAdmin() {
	        log4j = Logger.getLogger("WishesAdmin");
	    }


	    public static int getRandomIntBetweenRange(int min, int max) {
	        int x = (int) ((Math.random() * ((max - min) + 1)) + min);
	        return x;
	    }

	@Test
	public void TC001WS_Verify_Add_CAndM_RollOFF() {

		testCaseLog("TC001WS_Verify_Add_CAndM_RollOFF");

		LoginPage login = new LoginPage(wishesDriver);
		LandingPage lp = new LandingPage(wishesDriver);
		WishesAdminPage ap = new WishesAdminPage(wishesDriver);
		ProspectPage pp = new ProspectPage(wishesDriver);

		login.loginAs(USER_NAME, PASSWORD);

		if (lp.isUserLoginSuccessful()) {
			success("User Login Successful");
		} else {
			failure("Failed to Login");
		}
		lp.OpenAdmin();
		lp.OpenCAndM();
		ap.clickOnTabRollOff();
		ap.clickonAddAcceptedMaterialButton();
		ap.selectMaterial();
		ap.clickonAddContainerSizeButton();
		ap.selectContainerSize();
		ap.clickonAddLoaingTimeButton();
		ap.selectLoadingTime();
		ap.clickonAddHaulMultiplierButton();
		ap.selectHaulMultipler();
		ap.clickonAddFees();
		ap.selectFees();
		lp.OpenProspect();
		ap.OpenProspect();
		pp.clickonCreateQuote();

		pp.clickonAddNewServiceButton();

		pp.selectServiceType();
		if (ap.isAddedContainerSizeDisplayed())
		{
			success("Added container size displayed");
		}
		else
		{
			failure("Added container size not displayed");
		}

		if (ap.isAddedMaterialDisplayed())
		{
			success("Added Material displayed");
		}
		else
		{
			failure("Added Material not displayed");
		}

		if (ap.isAddedHaultypeDisplayed())
		{
			success("Added Haul type displayed");
		}
		else
		{
			failure("Added Haul type not displayed");
		}

		if (ap.isAddedFeesDisplayed())
		{
			success("Added Fee displayed");
		}
		else
		{
			failure("Added Fee not displayed");
		}


		sa.assertAll();

	}

	@Test
	public void TC001WS_Verify_Edit_CAndM_RollOFF() {

		testCaseLog("TC001WS_Verify_Edit_CAndM_RollOFF");

		LoginPage login = new LoginPage(wishesDriver);
		LandingPage lp = new LandingPage(wishesDriver);
		WishesAdminPage ap = new WishesAdminPage(wishesDriver);
		ProspectPage pp = new ProspectPage(wishesDriver);

		login.loginAs(USER_NAME, PASSWORD);

		if (lp.isUserLoginSuccessful()) {
			success("User Login Successful");
		} else {
			failure("Failed to Login");
		}
		lp.OpenAdmin();
		lp.OpenCAndM();
		ap.clickOnTabRollOff();

		ap.clickonEditbuttonMaterial();
		ap.selectMaterial();
		if(ap.isMaterialEdited())
		{
			success("Material edited successfully ");
		}
		else
		{
			failure("Material not edited");
		}

		ap.clickonEditbuttonContainer();
		ap.selectContainerSize();
		if(ap.isConainerSizeEdited())
		{
			success("Container Size edited successfully ");
		}
		else
		{
			failure("Container Size not edited");
		}


		ap.clickonEditbuttonLoadingTime();
		ap.selectLoadingTime();

		if(ap.isTimeEdited())
		{
			success("Loading Time edited successfully ");
		}
		else
		{
			failure("Loading Time not edited");
		}

		ap.clickonEditbuttonHaulType();
		ap.selectHaulMultipler();
		if(ap.isHaultypeEdited())
		{
			success("Haul type edited successfully ");
		}
		else
		{
			failure("Haul type not edited");
		}
		ap.clickonAddFees();
		ap.selectFees();
		if(ap.isFeeEdited())
		{
			success("Fees edited successfully ");
		}
		else
		{
			failure("Fees not edited");
		}


		lp.OpenProspect();
		ap.OpenProspect();
		pp.clickonCreateQuote();

		pp.clickonAddNewServiceButton();

		pp.selectServiceType();
		if (ap.isAddedContainerSizeDisplayed())
		{
			success("Added container size displayed");
		}
		else
		{
			failure("Added container size not displayed");
		}

		if (ap.isAddedMaterialDisplayed())
		{
			success("Added Material displayed");
		}
		else
		{
			failure("Added Material not displayed");
		}

		if (ap.isAddedHaultypeDisplayed())
		{
			success("Added Haul type displayed");
		}
		else
		{
			failure("Added Haul type not displayed");
		}

		if (ap.isAddedFeesDisplayed())
		{
			success("Added Fee displayed");
		}
		else
		{
			failure("Added Fee not displayed");
		}


		sa.assertAll();

	}
}
