package gflwishes.testcases;

import gflwishes.PageObjects.LandingPage;
import gflwishes.PageObjects.LoginPage;
import gflwishes.PageObjects.cleanfillPage;
import gflwishes.base.EnhancedBaseClass;

import org.apache.log4j.Logger;
import org.junit.After;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

//import java.util.List;
//import java.util.Random;
//import org.openqa.selenium.*;

public class cleanfill extends EnhancedBaseClass {

    public cleanfill() {
        log4j = Logger.getLogger("NEW BATCH REQUEST");
    }

    public static int getRandomIntBetweenRange(int min, int max) {
        int x = (int) ((Math.random() * ((max - min) + 1)) + min);
        return x;
    }
    @Test
    public void TC01_Create_Batches() throws IOException, InterruptedException {

        testCaseLog("TC01_Create_New_Batch");

        LoginPage login = new LoginPage(fleetMapperDriver);
        LandingPage lp = new LandingPage(fleetMapperDriver);    
        cleanfillPage cf = new cleanfillPage(fleetMapperDriver);

        //login.loginAs(USER_NAME, PASSWORD);
        cf.loginAs(USER_NAME, PASSWORD);
        cf.clickonbatche();
       // cf.dismisspopup();
        cf.clickonbatchcreate();
        cf.enterbatchdate();
        cf.selecteststartDate();
        cf.enterbatchname();
        cf.trackbyload();
        cf.trackbyweight();
        cf.estimatedloads();
        cf.numberOfTrucks();
        cf.estimatedweight();
        cf.txtestimatedvol();
        cf.batchnext();
        cf.stayonpage();
        cf.sourcesiteopen();
        cf.sourcesite();
        cf.sourcesitetypeopen();
        cf.sourcetypeoption();
        cf.sourcesitehstry();
        cf.sourcesitehstryselect();
        cf.presentuse();
        cf.addrefnotes();
        cf.primarycontact();
        cf.primarycontactoptions();
        cf.qualifiedperson();
        cf.qualifiedpersonoptn();
        cf.nextssinfo();
        cf.stayonpage();
        cf.soildescription();
        cf.soildescriptionoptn();
        cf.soilquantity();
        cf.soilqtyriptionoptn();
        cf.materialno();
        cf.materialyes();
        cf.entergradereason();
        cf.debrisyes();
        cf.debrisno();
        cf.enterdebrisreason();
        cf.analysisone();
        cf.analysisinorg();
        cf.analysismetals();
        cf.analysispcb();
        cf.analysispest();
        cf.analysissvoc();
        cf.analysisvoc();
        cf.analysisother();
        cf.nextsoildesc();
        cf.stayonpage();
        cf.UploadFile();
        cf.addlink();
        cf.pastlink();
        cf.comments();
        cf.submitbatchrequest();
        Thread.sleep(2000);
        }

}

