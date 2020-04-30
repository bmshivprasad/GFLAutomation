package gflwishes.testcases;

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
	public void TC001WS_Verify_Adding()
	{

		testCaseLog("TC001WS_Verify_Create_Prospect_Functionality_with_ServiceType_FrontEnd");

		LoginPage login = new LoginPage(wishesDriver);
		LandingPage lp = new LandingPage(wishesDriver);
		WishesAdminPage ap = new WishesAdminPage(wishesDriver);


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
		ap.selectContainerSize();
		ap.selectLoadingTime();
		ap.selectHaulMultipler();
		ap.selectFees();

		sa.assertAll();

	}
}
