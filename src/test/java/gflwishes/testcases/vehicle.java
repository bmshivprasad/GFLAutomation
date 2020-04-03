package gflwishes.testcases;

import gflwishes.PageObjects.LandingPage;
import gflwishes.PageObjects.LoginPage;
import gflwishes.PageObjects.vehiclePage;
import gflwishes.base.EnhancedBaseClass;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import java.io.IOException;

//import java.util.List;
//import java.util.Random;
//import org.openqa.selenium.*;

public class vehicle extends EnhancedBaseClass {

    public vehicle() {
        log4j = Logger.getLogger("Vehical");
    }


    public static int getRandomIntBetweenRange(int min, int max) {
        int x = (int) ((Math.random() * ((max - min) + 1)) + min);
        return x;
    }

    @Test
    public void TC03_Create_Vehicle_Functionality() throws IOException, InterruptedException {

        testCaseLog("TC03_Create_Vehicle_Functionality");

        LoginPage login = new LoginPage(driver);
        LandingPage lp = new LandingPage(driver);
        vehiclePage vc = new vehiclePage(driver);//object creation for project page

        login.loginAs(USER_NAME, PASSWORD);
        vc.getServiceZone();
        vc.OpenRegion();
        vc.ClickonVehicleTab();
        vc.clickonAddVehiclebutton();
        vc.selectBusinessUnit();
        vc.typeVehicalname();

        vc.typeVin();
        vc.selectServiceZone();
        vc.selectVehicleType();
        vc.clickonSaveButton();
        if(vc.isvehicleCreated())
        {
            success("Vehicle Created Successfully");
        }
        else
        {
            failure("Vehicle not created");
        }
        vc.copyVehicleInEndToEndExcel();




    }
    


}