package gflwishes.PageObjects;

import gflwishes.base.Generics;
import gflwishes.testcases.Prospect;
import gflwishes.utilities.ExcelColumns;
import gflwishes.utilities.ExcelUtils;
import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class EndtoEndProspectPage extends Prospect implements ExcelColumns {

	private WebDriver localDriver;
    private Generics generics;

    public EndtoEndProspectPage(WebDriver baseDriver) {
        this.localDriver = baseDriver;
        PageFactory.initElements(localDriver, this);
        generics = new Generics(localDriver);
        log4j = Logger.getLogger("AdminContract");
    }
    
    public int getRowsExcel() {
        FileInputStream file;
        try {
            file = new FileInputStream(
                    System.getProperty("user.dir") + "/src/test//java//gfl//testData//Prospect.xlsx");
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheet("Sheet1");
            return sheet.getPhysicalNumberOfRows();
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    
    
    
    
    @FindBy(xpath = "//button[contains(text(),'PROSPECTS')]/i")
    public WebElement btnAddCustomer;


    public boolean isCustomerPageOpen() {
        return generics.isPresent(btnAddCustomer);
    }


    @FindBy(xpath = "//form//span[text()='Add']")
    public WebElement btnAdd;

    public void clickonAddCustomerButton() {
        generics.pause(5);
        generics.scrollToElement(btnAddCustomer);
        generics.clickOn(btnAddCustomer);
        testStepsLog("Clicked ON Add Customer button");
        generics.pause(2);
    }
	
    @FindBy(xpath = "//button[contains(text(),'PROSPECT')]/i")
    public WebElement btnAddProspect;


    public boolean isProspectPageOpen() {
        return generics.isPresent(btnAddProspect);
    }

    public void clickonAddProspectButton() {
        generics.pause(5);
        generics.scrollToElement(btnAddProspect);
        generics.clickOn(btnAddProspect);
        testStepsLog("Clicked ON Add Prospect button");
        generics.pause(2);
    }
    
    @FindBy(xpath = "//div[text()='Search Customer / Prospect']/following-sibling::div//input")
    public WebElement txtProspectName;

    public boolean isPopupdisplayed() {
        return generics.isPresent(txtProspectName);
    }

    public static String ProspectName;

    public void typeProspectname(int row) {
        // CustomerName = generics.getRandomCharacters(10);
    	ProspectName = "AutoCust"+generics.getRandomCharacters(5) + "_" + LocalDate.now().format(DateTimeFormatter.ofPattern("ddMM"))
                + "" + getRandomIntBetweenRange(100, 999);
        ;
        generics.clickOn(txtProspectName);
        generics.type(txtProspectName, ProspectName);
        try {
            SetTestData(ProspectName, row, 2);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        generics.pause(5);
    }
    
    public static void SetTestData(String dta, int rowt, int colt) throws IOException {

        FileInputStream file = new FileInputStream(
                System.getProperty("user.dir") + "/src/test//java//gfl//testData//Prospect.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheet("Sheet1"); // workbook.getSheetAt(0)


        XSSFRow row = sheet.getRow(rowt); // Get the row in which data is stored
        XSSFCell cell = row.createCell(colt);
        cell.setCellValue(dta);
        FileOutputStream fo = new FileOutputStream(
                System.getProperty("user.dir") + "/src/test//java//gfl//testData//Prospect.xlsx");
        workbook.write(fo);
        workbook.close();
        file.close();
        fo.close();

    }


    @FindBy(xpath = "//b[text()='Create New Prospect']")
    public WebElement lnkCreateNewProspect;

    public void clickonCreateNewProspectlnk() {
        generics.clickOn(lnkCreateNewProspect);
        testStepsLog("Clicked on Create New Prospect button");
    }


    @FindBy(xpath = "//input[@placeholder='Company Name']")
    public WebElement txtComplanyName;

    public boolean isEnteredProspectDisplayed() {
        String cn = generics.getValue(txtComplanyName);
        if (cn.equals(ProspectName)) {
            return true;
        } else {
            return false;
        }

    }

    ExcelUtils excelUtils = new ExcelUtils();

    @FindBy(xpath = "(//mat-select[@formcontrolname='businessUnitId'])[1]")
    public WebElement dpBusinessUnit1;

    public void selectBusinessUnit1(int row) {
        generics.pause(3);
        String BU = excelUtils.getTestData(Prospect, row, 4);

        generics.clickOn(dpBusinessUnit1);

        WebElement element = localDriver.findElement(By.xpath("//mat-option/span[contains(text(),'" + BU + "')]"));
        element.click();
        testStepsLog("Site Business unit Selected : " + BU);
        generics.pause(3);

    }

    @FindBy(xpath ="//input[@formcontrolname='webSite']")
    public WebElement txtWebsite;

    public static String Website;

    public void typeWebsite() {
        Website = "www." + generics.getRandomCharacters(5) + ".com";
        generics.clickOn(txtWebsite);
        generics.type(txtWebsite, Website);
        testStepsLog("Website inserted");
    }

    @FindBy(xpath = "//span[contains(text(),'Canadian Dollar')]")
    public WebElement dpBillingCurrency;

    @FindBy(xpath = "//mat-option[3]")
    public WebElement ThirdOption;

    public void selectBillingCurrency() {
        generics.clickOn(dpBillingCurrency);
        generics.clickOn(ThirdOption);
        testStepsLog("BillingCurrency selected");
        generics.pause(5);
    }

    @FindBy(xpath = "//mat-select[@formcontrolname='salesRepId']")
    public WebElement dpSalesRep;

    @FindBy(xpath = "//mat-option[contains(.,'CapexTest Approver')]")
    public WebElement dpRepOption;

    public void selectSalesRep() {
        generics.clickOn(dpSalesRep);
        generics.clickOn(dpRepOption);
        testStepsLog("Sales Rep selected");
        generics.pause(3);
    }

    @FindBy(xpath = "//mat-select[@formcontrolname='taxJurisdictionErpCode']")
    public WebElement dpJurisdiction;

    @FindBy(xpath = "//mat-option[3]")
    public WebElement SecondOption;

    public void selectJurisdiction() {
        generics.clickOn(dpJurisdiction);
        generics.clickOn(SecondOption);
        testStepsLog("Jurisdiction selected");
        generics.pause(3);
    }

    @FindBy(xpath = "//mat-select[@aria-label='Select Customer Type']")
    public WebElement dpcustomertype;

    @FindBy(xpath = "//mat-option[2]")
    public WebElement firstOption;

    public void selectcustomertype() {
        generics.pause(2);
        generics.clickOn(dpcustomertype);
        generics.clickOn(firstOption);
        generics.pause(2);
        testStepsLog("Customer type selected");
    }

    @FindBy(xpath = "//div[text()='CONTACT DETAILS']")
    public WebElement SectionContractDetails;

    @FindBy(xpath = "(//ng-select[@placeholder='Address Line 1'])[1]")
    public WebElement dpAddressLine1;

    @FindBy(xpath = "(//ng-select[@placeholder='Address Line 1'])[1]//input")
    public WebElement txtAddressLine1;

    @FindBy(xpath = "(//ng-dropdown-panel//span)[1]")
    public WebElement firstAddress;

    public void selectAddressline1() {
        String Add = "70 Eglin Blvd, Londonderry, NH, 03053, USA";
        generics.moveTo(SectionContractDetails);
        generics.clickOn(dpAddressLine1);
        generics.type(txtAddressLine1, Add);
        generics.pause(5);
        generics.clickOn(firstAddress);
        testStepsLog("Address line selected");
        generics.pause(2);
    }

    @FindBy(xpath = "//mat-checkbox[@formcontrolname='billingAsCompanyAddress']//div[contains(@class,'mat-checkbox-inner-container')]")
    public WebElement chkBillingAddAsCompanyAdd;

    public void selectBillingAddAsCompanyAdd() {
        generics.clickOn(chkBillingAddAsCompanyAdd);
        testStepsLog("Bill Address checkbox selected");
        generics.pause(3);
    }

    @FindBy(xpath = "(//mat-label[text()='Contact Name']/ancestor::span/preceding-sibling::input)[1]")
    public WebElement txtContactName;

    public static String ContactName;

    public void typeContact() {
        ContactName = generics.getRandomCharacters(10);
        generics.moveTo(txtContactName);
        generics.type(txtContactName, ContactName);
        generics.pause(2);
        testStepsLog("Contact detail inserted");
    }

    @FindBy(xpath = "(//input[@formcontrolname='email'])[1]")

    public WebElement txtEmail;

    public static String Email;

    public void typeEmail() {
        Email = generics.getRandomCharacters(10) + "@gmail.com";



        generics.moveTo(txtEmail);
        generics.type(txtEmail, Email);

        testStepsLog("Email inserted");


    }

    @FindBy(xpath = "(//input[@formcontrolname='position'])[1]")

    public WebElement txtContactPosition;

    public static String ContactPosition;

    public void typeContactPosition() {
        ContactPosition = generics.getRandomCharacters(10);

        generics.moveTo(txtContactPosition);
        generics.type(txtContactPosition, ContactPosition);
    	/*try {
			SetTestData(ContactPosition, 7, 2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
        testStepsLog("ContactPosition inserted");
    }


    @FindBy(xpath = "(//input[@formcontrolname='primaryPhoneNumber'])[1]")
    public WebElement txtPhoneNumber;
    public static String PhoneNumber;

    public void typePhoneNumber() {
        PhoneNumber = "9898989898";
        generics.moveTo(txtPhoneNumber);
        generics.type(txtPhoneNumber, PhoneNumber);

        testStepsLog("PhoneNumber inserted");
    }





    @FindBy(xpath = "(//input[@formcontrolname='primaryExtension'])[1]")
    public WebElement txtExt;

    public static int extension;

    public void typeExtention() {
        extension = generics.getRandomBetween(11111, 99999);

        generics.moveTo(txtExt);
        generics.type(txtExt, String.valueOf(extension));
        testStepsLog("extension inserted");

        JavascriptExecutor je = (JavascriptExecutor) localDriver;
        je.executeScript("arguments[0].scrollIntoView(true);",txtEmail);
    }


    @FindBy(xpath = "//input[@placeholder='Enter Site Name']")
    public WebElement txtSiteName;

    public static String SiteName;

    public void typeSiteName(int row) {
        SiteName = generics.getRandomCharacters(10);
        generics.moveTo(txtSiteName);
        generics.type(txtSiteName, String.valueOf(SiteName));
        generics.pause(5);
        try {
            SetTestData(SiteName, row, 3);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        testStepsLog("SiteName inserted");
    }

    @FindBy(xpath = "(//mat-select[@formcontrolname='businessUnitId'])[2]")
    public WebElement dpBusinessUnit;

    public void selectBusinessUnit() {
        generics.moveTo(dpBusinessUnit);
        generics.clickOn(dpBusinessUnit);
        generics.pause(2);
    	/*try {
			SetTestData(generics.getText(firstOption), 10, 2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
        generics.clickOn(firstOption);
        generics.pause(2);
        testStepsLog("Site Business unit Selected");
        generics.pause(2);

    }

    @FindBy(xpath = "//mat-select[@formcontrolname='businessTypeErpCode']")
    public WebElement dpBusinessType;
    public void selectBusinessType() {

        generics.pause(4);
        generics.clickOn(dpBusinessType);
        generics.pause(2);
    	/*try {
			SetTestData(generics.getText(firstOption), 11, 2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
        generics.clickOn(firstOption);
        generics.pause(2);
        testStepsLog("Site Business Type Selected");

        JavascriptExecutor je = (JavascriptExecutor) localDriver;
        je.executeScript("arguments[0].scrollIntoView(true);",dpBusinessUnit);
    }

    @FindBy(xpath = "//mat-select[@id='mat-select-18']")
    public WebElement dpSalesRep1;
    public void selectSalesRep1() {

        generics.pause(4);
        generics.clickOn(dpSalesRep1);
        generics.pause(2);
    	/*try {
			SetTestData(generics.getText(firstOption), 11, 2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
        generics.clickOn(firstOption);
        generics.pause(2);
        testStepsLog("Site Business Sales Rep Selected");


    }

    //@FindBy(xpath = "(//mat-checkbox[@formcontrolname='addressAsCustomerAddress']//div[contains(@class,'mat-checkbox-inner-container')]")
    // public WebElement chkbx;


    @FindBy(xpath = "(//ng-select[@placeholder='Address Line 1'])[3]")
    public WebElement dpAddressLine1OfSite;

    @FindBy(xpath = "(//ng-select[@placeholder='Address Line 1'])[3]//input")
    public WebElement txtAddressLine1OfSite;

    @FindBy(xpath = "(//input[@formcontrolname='postalCode'])[3]")
    public WebElement txtPostalcode;

    public void selectAddressline1ofSite(int row) {
        String Add = excelUtils.getTestData(Prospect, row, 0);
        testStepsLog("Address : " + Add);


        generics.moveTo(txtPostalcode);
        generics.clickOn(dpAddressLine1OfSite);
        generics.type(txtAddressLine1OfSite, Add);
        generics.pause(5);
        generics.clickOn(firstAddress);

        testStepsLog("Address line selected for site");
//        js = (JavascriptExecutor) localDriver;
//        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        generics.pause(2);
    }

    public void typePostalcode(int row) {
        String pc = excelUtils.getTestData(Prospect, row, 1);
        generics.type(txtPostalcode, Keys.CONTROL + "a" + Keys.DELETE);
        generics.clickOn(txtPostalcode);
        generics.type(txtPostalcode, pc);
        testStepsLog("Postal code inserted +" + pc);
        generics.pause(2);
    }

    @FindBy(xpath = "//mat-checkbox[@formcontrolname='billToCustomerBillingAddress']")
    public WebElement chkbillToCustomerBillingAddress;

    public void SelectbillToCustomerBillingAddress() {
        generics.scrollToElement(chkbillToCustomerBillingAddress);
        generics.clickOn(chkbillToCustomerBillingAddress);
        testStepsLog("Select Bill to Customer Billing Address checkbox");
        generics.pause(2);
    }

    @FindBy(xpath = "//mat-checkbox[@formcontrolname='isSiteContactSameAsPrimaryContact']")
    public WebElement chkbSitecontactsameasprimarycontact;

    public void SelectSiteContactSameAsPrimaryContact() {
        generics.scrollToElement(chkbSitecontactsameasprimarycontact);
        generics.clickOn(chkbSitecontactsameasprimarycontact);
        testStepsLog("Select Site contact same as primary contact checkbox");
        generics.pause(2);
    }

    @FindBy(xpath = "//button[contains(text(),'SAVE AND SUBMIT')]")
    public WebElement btnSave;

    public void clickonbtnSaveCustomer() {
        generics.clickOn(btnSave);
        generics.pause(4);
        testStepsLog("Clicked on Save Customer button");
    }

    @FindBy(xpath = "//div[contains(text(),'Prospect has been created successfully!')]")
    public WebElement Prospectsuccessmsg;

    public boolean isProspectCreatedSuccessful() {
        generics.pause(3);
        pId = generics.getText(prospectID);
        testStepsLog("Prospect ID = " + pId);
        return generics.isPresent(Prospectsuccessmsg);

    }

    //====================================CK Code for Add Service====================================

    @FindBy(xpath = "//button[contains(text(),'CREATE QUOTE')]")
    public WebElement btnCreateQuote;

    public void clickonCreateQuote()
    {
        generics.pause(4);
        generics.clickOn(btnCreateQuote);
        generics.pause(4);
        testStepsLog("Clicked on Create Quote button");
    }

    @FindBy(xpath = "//button[contains(text(),'NEXT')]")
    public WebElement btnNext;
    public void clickonNextButton() {

        generics.pause(7);
        generics.waitForElementVisible(localDriver.findElement(By.xpath("//span[@title='Clear all']")));
        JavascriptExecutor js = (JavascriptExecutor) localDriver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        generics.clickOn(btnNext);
        generics.pause(2);
        testStepsLog("Clicked On Next Button");
        
    }
    @FindBy(xpath = "//button[contains(text(),'ADD SERVICE')]")
    public WebElement btnAddServices;
    public void clickonAddServiceButton() {
        generics.pause(6);
        JavascriptExecutor js = (JavascriptExecutor) localDriver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        generics.moveTo(Aggreement);
        generics.clickOn(btnAddServices);
        testStepsLog("Clicked On Add Service Button");
        generics.pause(3);
    }

    @FindBy(xpath = "//mat-select[@formcontrolname='serviceTypeId']")
    public WebElement dpServiceType;

    @FindBy(xpath = "//mat-option//span[text()=' Roll Off ']")
    public WebElement optionRollOff;

    @FindBy(xpath = "//button[contains(text(),'CREATE QUOTE')]")
    public WebElement btnCreatequote;

    public boolean isAgreementsPageOpen() {
        return generics.isPresent(btnCreatequote);
    }
  

    public void selectServiceType()
    {
    	generics.pause(2);
        generics.clickOn(dpServiceType);
        generics.clickOn(optionRollOff);
        testStepsLog("Service type selected");
    }

    @FindBy(xpath = "//button[contains(text(),'Add New Service')]")
    public WebElement btnAddnewServices;

    public void clickonAddNewServiceButton() {

        generics.pause(6);
        JavascriptExecutor js = (JavascriptExecutor) localDriver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        generics.moveTo(Aggreement);
        generics.clickOn(btnAddnewServices);
        testStepsLog("Clicked On Add new Service Button");
        generics.pause(3);
    }

        @FindBy(xpath = "//input[@formcontrolname='containerCount']")
    public WebElement txtContainerCount;

    @FindBy(xpath = "//div[text()='DISPOSAL']")
    public WebElement titleDisposal;

    public void typeContainerCount(int row)
    {
        try {
            generics.pause(2);
            generics.moveTo(titleDisposal);
            String CC = excelUtils.getTestData("Prospect", row, 5);
            generics.type(txtContainerCount, CC);
            testStepsLog("Container Count : " + CC);
        }
        catch(Exception e)
        {
            testStepsLog("Container count is not editable");
        }
    }

    @FindBy(xpath = "//mat-select[@formcontrolname='containerTypeId']")
    public WebElement dpContainerType;

    public void SelectContainerType(int row)
    {
    	generics.pause(6);
        String CT=excelUtils.getTestData("Prospect", row, 6);
        generics.clickOn(dpContainerType);
        WebElement element=localDriver.findElement(By.xpath("//span[@class='mat-option-text' and contains(text(),'"+CT+"')]"));
        element.click();
        testStepsLog("Container Type Selected : " + CT);
    }

    @FindBy(xpath = "//input[@formcontrolname='containerRentalFee']")
    public WebElement txtContainerFee;

    public void typeContainerFee(int row)
    {
    	generics.pause(2);
        String CF=excelUtils.getTestData("Prospect", row, 7);
        generics.type(txtContainerFee,CF);
        testStepsLog("Container Fee : " + CF);
    }

    @FindBy(xpath = "//mat-select[@formcontrolname='containerSizeId']")
    public WebElement dpContainerSize;

    public void SelectContainerSize(int row)
    {
    	generics.pause(2);
        String CS=excelUtils.getTestData("Prospect", row, 8);
        generics.clickOn(dpContainerSize);
        WebElement element=localDriver.findElement(By.xpath("//span[@class='mat-option-text' and contains(text(),'"+CS+"')]"));
        element.click();
        testStepsLog("Container Size Selected : " + CS);
    }

    @FindBy(xpath = "//mat-select[@formcontrolname='serviceFrequencyId']")
    public WebElement dpFrequency;

    @FindBy(xpath = "//mat-chip[contains(text(),'W')]")
    public WebElement Wednesday;


    public void SelectFreuency(int row)
    {
    	generics.pause(2);
        String F=excelUtils.getTestData("Prospect", row, 9);
        generics.clickOn(dpFrequency);
        WebElement element=localDriver.findElement(By.xpath("//span[@class='mat-option-text' and contains(text(),'"+F+"')]"));
        element.click();
        testStepsLog("Frequency selected : " + F);
        generics.clickOn(Wednesday);
        testStepsLog("Preffered service day selected");
    }

    @FindBy(xpath = "//mat-select[@formcontrolname='disposalChargeTypeId']")
    public WebElement dpChargeType;

    @FindBy(xpath = "//input[@formcontrolname='minimumWeight']")
    public WebElement txtminweight;

    @FindBy(xpath = "//input[@formcontrolname='maximumWeight']")
    public WebElement txtmaxweight;

    public void SelectChargeType(int row)
    {
    	JavascriptExecutor js = (JavascriptExecutor)localDriver;
    	js.executeScript("arguments[0].scrollIntoView(true);",localDriver.findElement(By.xpath("//p[contains(.,'The selected service day')]")));
   	
//      generics.moveTo("//div[@class='card_title'][contains(.,'DISPOSAL')]")));
        generics.pause(2);
        String CT=excelUtils.getTestData("Prospect", row, 10);
        generics.clickOn(dpChargeType);
        WebElement element=localDriver.findElement(By.xpath("//span[@class='mat-option-text' and contains(text(),'"+CT+"')]"));
        element.click();
        testStepsLog("Charge Type selected : " + CT);
        if(CT.equals("MT / haul"))
        {
            generics.type(txtminweight,String.valueOf(generics.getRandomBetween(1,5)));
            generics.type(txtmaxweight,String.valueOf(generics.getRandomBetween(6,9)));
            testStepsLog("Min/max weight inserted");
        }
    }

    @FindBy(xpath = "//mat-select[@formcontrolname='haulTypeId']")
    public WebElement dpHaultype;

    public void SelectHaulType(int row)
    {

    	generics.pause(2);
        String HT=excelUtils.getTestData("Prospect", row, 11);
        generics.clickOn(dpHaultype);
        WebElement element=localDriver.findElement(By.xpath("//span[@class='mat-option-text' and contains(text(),'"+HT+"')]"));
        element.click();
        testStepsLog("Frequency selected : " + HT);
    }
    @FindBy(xpath = "//mat-select[@formcontrolname='serviceTypeMaterialId']")
    public WebElement dpserviceTypeMeterial;

    public void SelectMaterial(int row)
    {
    	generics.pause(2);
        String M=excelUtils.getTestData("Prospect", row, 12);
        generics.clickOn(dpserviceTypeMeterial);
        WebElement element=localDriver.findElement(By.xpath("//span[@class='mat-option-text' and contains(text(),'"+M+"')]"));
        element.click();
        testStepsLog("Material selected : " + M);
    }
    @FindBy(xpath = "//mat-select[@formcontrolname='disposalSiteId']")
    public WebElement dpdiposibleSite;

    public void selectDisposibleSite()
    {
        //String M=excelUtils.getTestData("Prospect", row, 12);
    	generics.pause(6);        
        generics.clickOn(dpdiposibleSite);
        generics.clickOn(firstOption);

        testStepsLog("Disposible site selected : ");
        generics.waitForElementVisible(btnCalculate);
        generics.pause(15);
    }

    @FindBy(xpath = "//button[contains(text(),'Calculate')]")
    public WebElement btnCalculate;

    public void clickonCalculate()
    {
    	generics.pause(2);
        generics.clickOn(btnCalculate);
        testStepsLog("Clicked ON calculate button");
        generics.pause(6);
    }



    @FindBy(xpath = "//input[@formcontrolname='timeAtDisposalSite']")
    public WebElement txtEstTime;

    public void typeEstTime(int row)
    {
    	generics.pause(2);
        String ET=excelUtils.getTestData("Prospect", row, 13);
        generics.type(txtEstTime,ET);
        testStepsLog("Estimated time entered in min : " + ET);
    }





    @FindBy(xpath = "//textarea[@formcontrolname='notes']")
    public WebElement txtnote;

    public void typenote()
    {
    	generics.pause(4);       
        JavascriptExecutor js = (JavascriptExecutor) localDriver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        String note=generics.getRandomCharacters(10);
        generics.type(txtnote,note);
        testStepsLog("Additional note : " + note);
    }

    @FindBy(xpath = "//input[@formcontrolname='customerBusinessType']")
    public WebElement txtLocationType;

    public void typeLocationType(int row)
    {
    	generics.pause(5);
        String LT=excelUtils.getTestData("Prospect", row, 14);
        generics.type(txtLocationType,LT);
        testStepsLog("Location Type : " + LT);
    }

    @FindBy(xpath = "//button[contains(text(),'Add Service Line')]")
    public WebElement AddService;

    public void clickonAddService()
    {
    	generics.pause(5);
        generics.clickOn(AddService);
        testStepsLog("Clicked on Add Service button");
    }

    @FindBy(xpath = "//button[contains(text(),'Update Agreement')]")
    public WebElement UpdateAgreement;

    public void clickonUpdateAgreement()
    {
    	generics.pause(5);
    	generics.clickOn(UpdateAgreement);
        testStepsLog("Clicked on UpdateAgreement button");
    }

    @FindBy(xpath = "//button[contains(text(),'SAVE & SUBMIT CSA')]")
    public WebElement btnSaveAndSubmitCSA;

    public void clickonSaveAndSubmitCSA()
    {
    	generics.pause(5);
        generics.clickOn(btnSaveAndSubmitCSA);
        testStepsLog("Clicked on Save And Submit CSA button");
    }

    @FindBy(xpath = "//button[contains(text(),'Submit')]")
    public WebElement btnSubmitted;

    public void clickonSubmitButton()
    {
    	generics.pause(5);
        generics.clickOn(btnSubmitted);
        testStepsLog("Clicked on SubmitButton CSA button");
        generics.pause(10);
    }


    @FindBy(xpath = "//div[contains(text(),'Agreement has been updated successfully!')]")
    public WebElement AggreementUpdatedSuccessfully;

    public boolean isAggreementUpdated()
    {
        generics.pause(5);
        return generics.isPresent(AggreementUpdatedSuccessfully);
    }

    @FindBy(xpath = "//div[contains(text(),'CSA has been submitted successfully!')]")
    public WebElement CSASuccess;

    public boolean isCSASaved()
    {
        generics.pause(5);
        return generics.isPresent(CSASuccess);
    }
    @FindBy(xpath = "//*[contains(text(),'AGREEMENT TERMS AND CONDITIONS')]")
    public WebElement Aggreement;

    public void clickonAgreementsNo() {
        generics.moveTo(localDriver.findElement(By.xpath("//td[contains(text(),'"+ProspectName+"')]/../td[contains(.,'Approved')]/..//a")));
        generics.clickOn(localDriver.findElement(By.xpath("//td[contains(text(),'"+ProspectName+"')]/../td[contains(.,'Approved')]/..//a")));
        generics.pause(5);
        testStepsLog("Clicked on Approveed Agreement.");
    }
  
 @FindBy(xpath = "//a[contains(text(),' 4 ')]")
    
    public WebElement Pageno;
    
    public void clickonPageNo() {
    	
    	JavascriptExecutor je = (JavascriptExecutor) localDriver;
        je.executeScript("arguments[0].scrollIntoView(true);",Pageno);
        generics.clickOn(Pageno);
        generics.pause(3);
        testStepsLog("Clicked on PageNo.");
    }
  
    @FindBy(xpath = "//button[contains(text(),' CUSTOMER COPY')]")
    public WebElement btnCustomercopy;
    
    public void clickonCustomerCopy() {
    	generics.pause(3);
        generics.clickOn(btnCustomercopy);
        generics.pause(5);
        testStepsLog("Clicked on CUSTOMER COPY.");
    }
    
   // @FindBy(xpath = "//div[@class='mat-checkbox-inner-container   Hover']")
    
    @FindBy(xpath = "//span[contains(text(),'THIS IS A LEGALLY BINDING CONTRACT')]")
    public WebElement chkAgreementTC;
    
    public void clickonChkAgreementTearmsAndCondition() {
    	//generics.moveTo(chkAgreementTC);
    	JavascriptExecutor je = (JavascriptExecutor) localDriver;
        je.executeScript("arguments[0].scrollIntoView(true);",chkAgreementTC);
    	generics.pause(3);
        generics.clickOn(chkAgreementTC);
        generics.pause(3);
        testStepsLog("Checked Tearms and Condition checkbox.");
    }
 
    @FindBy(xpath = "//button[contains(text(),'MARK AS SIGNED')]")
    public WebElement btnMarkassigned;
    
    public void clickonMarkAsSigned() {
        generics.clickOn(btnMarkassigned);
        generics.pause(5);
        testStepsLog("Clicked on MARK AS SIGNED.");
    }
    
    @FindBy(xpath = "//input[@id='file']")
    public WebElement btnUpload;
    public static String Uploadpath;
    public void clickonDropFileHereorClicktoUpload() {
    	Uploadpath = TEST_DATA_LOCATION + File.separator + "Signature.PNG";     
        btnUpload.sendKeys(Uploadpath); 
        generics.pause(5);
        testStepsLog("Digital Signature Uploaded Sucessfully.");
    }
    
    @FindBy(xpath = "//strong[contains(text(),'I confirm that this CSA has been signed')]")
    public WebElement chkCSASigned;
    
    public void SelectIconfirmthatthisCSAhasbeensigned() {
        generics.clickOn(chkCSASigned);
        generics.pause(5);
        JavascriptExecutor je = (JavascriptExecutor) localDriver;
        je.executeScript("arguments[0].scrollIntoView(true);",btnUpload);
        testStepsLog("Clicked I confirm that this CSA has been signed.");
    }
    
    @FindBy(xpath = "//button[contains(text(),' Submit Document ')]")
    public WebElement btnSubmitDocument;
    
    public void clickonSubmitDocument() {
        generics.clickOn(btnSubmitDocument);
        generics.pause(5);
        testStepsLog("Clicked on Submit Document.");
    }
    
    @FindBy(xpath = "//button[contains(text(),'SUBMIT TO CDE')]")
    public WebElement btnSubmittoCDE; 
    public void clickonSubmitCDE() {
        generics.clickOn(btnSubmittoCDE);
        generics.pause(5);
        testStepsLog("Clicked on SUBMIT TO CDE.");
    }
    
    @FindBy(xpath = "//h2[contains(text(),' Are you sure you want to Submit this CSA to CDE ? ')]")
    public WebElement txtConfirmamMSG;
    public boolean isPopupdisplayedCSAtoCDE() {
        return generics.isPresent(txtConfirmamMSG);
    }
    
    @FindBy(xpath = "//button[@id='btnProceed']")
    public WebElement btnSubmit;

    public void clickonSubmit() {
        if(generics.isPresent(btnSubmit)) {
            generics.clickOn(btnSubmit);
            generics.pause(5);
            testStepsLog("Clicked on SUBMIT.");
        }
    }
    
    @FindBy(xpath = "//button[contains(text(),'SUBMIT TO CDE')]")
    public WebElement btnSubmittocde;
    
    public void clickonSubmittoCDE() {
        generics.clickOn(btnSubmittocde);
        generics.pause(5);
        testStepsLog("Clicked on SUBMIT TO CDE.");
    }
    
    @FindBy(xpath = "//button[contains(text(),' CLONE ')]")
    public WebElement btnCLONE;
    
    public void clickonCLONE() {
        generics.clickOn(btnCLONE);
       
        testStepsLog("Clicked on CLONE.");
    }
    
    @FindBy(xpath = "//div[contains(text(),'Unassigned')]")
    public WebElement btnUnassigned;
    
    public void ClickonUnassigned() {
        generics.clickOn(btnUnassigned);
        generics.pause(5);
        testStepsLog("Clicked on Unassigned.");
    }
    
    @FindBy(xpath = "//div[contains(text(),'TICKET DETAILS')]")
    public WebElement Ticketdetailspage;
    
    @FindBy(xpath = "//h3[contains(text(),'ORDER FORM NOTES')]")
    public WebElement Orderformnotes;
    
    public boolean isTicketDetailsOpen() {
    	

        generics.pause(8);
        return generics.isPresent(Ticketdetailspage);
            
    }
    
    @FindBy(xpath ="//input[@formcontrolname=\"erpId\"]")
    public WebElement txtServicelineitem;
    public static String Servicelineitem;

    public void typeServiceLineItem() {
        Servicelineitem = "12345";
        generics.clickOn(txtServicelineitem);
        generics.type(txtServicelineitem, Servicelineitem);
        testStepsLog("Service Line Item inserted.");
    }
    
    @FindBy(xpath ="//mat-checkbox[@formcontrolname='isChecked'][1]")
    public WebElement chkAgreement;

    public void SelectDocumentsReviewed() {
    	generics.pause(3);
        generics.clickOn(chkAgreement);
        generics.pause(3);
        testStepsLog("Agreement checkbox selected.");
    }
    
    @FindBy(xpath ="//textarea[@formcontrolname='leaveANote']")
    public WebElement txtLeavecomment;
    public static String Leavecomment;

    public void typeLeaveAComment() {
    	Leavecomment = generics.getRandomCharacters(10);
    	generics.pause(3);
        generics.clickOn(txtLeavecomment);
        generics.pause(2);
        generics.type(txtLeavecomment, Leavecomment);
        generics.pause(3);
        testStepsLog("Leave a Comment inserted.");
    }
    
    @FindBy(xpath = "//button[contains(text(),' Complete ')]")
    public WebElement btnComplete;
    
    public void ClickonComplete() {
        generics.clickOn(btnComplete);
        generics.pause(5);
        testStepsLog("Clicked on COMPLETE.");
    }
    
    @FindBy(xpath = "//H2[contains(text(),' Are you sure you want to complete this ticket? ')]")
    public WebElement Completettickeheading;
    
    public boolean isCpmpleteTicketpopup() { 
        return generics.isPresent(Completettickeheading);        
    }
    
    @FindBy(xpath = "//button[@id='btnProceed']")
    public WebElement btncProcceed;
    
    public void Clickoncomplete() {
        generics.clickOn(btncProcceed);
        generics.pause(4);
        testStepsLog("Clicked on COMPLETE.");
    }
    
  
    @FindBy(xpath = "//div[contains(text(),'Ticket has been completed successfully !')]")
    public WebElement Completetticketmessage;
    
    public boolean isCpmplettticketmsg() { 
    	 generics.pause(3);
        return generics.isPresent(Completetticketmessage);        
    }
    
    @FindBy(xpath ="//input[@formcontrolname=\"customerErpId\"]")
    public WebElement txtTruxcustomerno;
    public static int Truxcustomerno;

    public void typeTruxCustomerNo() {
        JavascriptExecutor je = (JavascriptExecutor) localDriver;
        je.executeScript("arguments[0].scrollIntoView(true);",Orderformnotes);
        Truxcustomerno = generics.getRandomBetween(1, 99999);
        generics.clickOn(txtTruxcustomerno);
        generics.type(txtTruxcustomerno, String.valueOf(Truxcustomerno));
        testStepsLog("Trux Customer No. inserted.");
    }
    
    @FindBy(xpath ="//input[@formcontrolname=\"siteErpId\"]")
    public WebElement txtSiteNo;
    public static int Siteno;

    public void typeSiteNo() {
        Siteno = generics.getRandomBetween(1, 99999);
        generics.clickOn(txtSiteNo);
        generics.type(txtSiteNo, String.valueOf(Siteno));
        testStepsLog("Site No. inserted.");
    }

    @FindBy(xpath = "(//div[text()='REGIONS']/following-sibling::div//div[@class='card_title'])")
    WebElement regions;

    public void OpenRegion() {
        generics.clickOn(regions);
        testStepsLog("Open selected regions");
        generics.pause(3);
    }


    @FindBy(xpath = "//span[@class='date_string']")
    WebElement lblToday;

    @FindBy(xpath = "//mat-icon[text()='filter_list ']")
    WebElement btnFilter;


    public static String VehicalName;


    public void openVehicle() {

        localDriver.navigate().to("https://fleetmapper-10qa.azurewebsites.net/admin/view-region/1/vehicles");
        generics.pause(7);
    }

    @FindBy(xpath = "//mat-icon[text()='chevron_right']")
    public WebElement sidepanel;

    @FindBy(xpath = "//mat-icon[text()='keyboard_arrow_left']")
    public WebElement leftarrow;

    @FindBy(xpath = "(//h3[text()='Logged into:']/following-sibling::div/p)[1]")
    public WebElement servicezone;

    public static String Zone;

    public void getServiceZone() {
        generics.clickOn(sidepanel);
        String z = generics.getText(servicezone);

        Zone = (z.split("-")[1]).trim();
        testStepsLog("Service zone : " + Zone);
        generics.clickOn(leftarrow);
    }


    @FindBy(xpath = "//a[contains(text(),'VEHICLES')]")
    public WebElement tabVehicle;

    @FindBy(xpath = "//span[@dataname='ADD VEHICLE']//preceding-sibling::mat-icon[text()='add']")
    public WebElement btnAddVehicle;

    public void ClickonVehicleTab() {
        generics.clickOn(tabVehicle);
        testStepsLog("Clicked on Vehicle tab");
    }

    public void clickonAddVehiclebutton() {

        generics.pause(2);

        generics.clickOn(btnAddVehicle);
        testStepsLog("Clicked on add vehicle button");
        generics.waitForElementVisible(txtVin);
    }

    @FindBy(xpath = "//input[@formcontrolname='vin']")
    public WebElement txtVin;

    public void typeVin() {
        String Vin = generics.getRandomCharacters(5);
        generics.type(txtVin, Vin);
        testStepsLog("Vin value inserted : " + Vin);
    }

    @FindBy(xpath = "//input[@formcontrolname='vehiclename']")
    public WebElement txtVehiclename;

    public void typeVehicalname() {
        generics.pause(3);
        VehicalName = "V_" + generics.getRandomCharacters(8);
        generics.type(txtVehiclename, VehicalName);
        testStepsLog("Vehicle value inserted : " + VehicalName);
    }




    @FindBy(xpath = "//mat-select[@formcontrolname='serviceZoneCreateId']")
    public WebElement dpServiceZone;



    public void selectServiceZone() {
        generics.clickOn(dpServiceZone);
        WebElement element = localDriver.findElement(By.xpath("//span[@class='mat-option-text' and contains(text(),'" + Zone + "')]"));
        element.click();
        testStepsLog("Service Zone Selected : " + Zone);
    }

    @FindBy(xpath = "//p[contains(text(),'Odometer Units')]")
    public WebElement lblOdo;


    @FindBy(xpath = "//mat-select[@formcontrolname='regionTruxVehicleTypeId']")
    public WebElement dpVehicleType;

    public void selectVehicleType() {
        String vehicleType = "ROLL OFF";
        generics.moveTo(lblOdo);
        generics.clickOn(dpVehicleType);
        generics.clickOn(localDriver.findElement(By.xpath("//mat-option//span[contains(text(),'" + vehicleType + "')]")));
        testStepsLog("Vehicle Type Selected");
    }

    @FindBy(xpath = "//div[text()='SAVE VEHICLE']")
    public WebElement btnSaveVehicle;

    public void clickonSaveButton() {
        generics.moveTo(btnSaveVehicle);
        generics.clickOn(btnSaveVehicle);
        testStepsLog("Clicked on Save Vehicle Button");

        generics.pause(6);
    }

    public boolean isvehicleCreated() {
        String CVehical = VehicalName.toUpperCase();
        List<WebElement> element = localDriver.findElements(By.xpath("//div[text()=' " + CVehical + " ']"));
        if (element.size() > 0)
            return true;
        else
            return false;
    }



    public void copyVehicleInProspectExcel() {
        String CVehical = VehicalName.toUpperCase();
        int row = getRowsExcel();
        for (int i = 1; i < row; i++) {
            excelUtils.setTestData("Prospect", i, 15, CVehical);
        }
        testStepsLog("Vehicle entery done in Prospect.xlsx file");
    }

    //==============================================================================

    public static String SiteAddress;


    public static String lblPickUpOrderName;


    @FindAll(value = {@FindBy(xpath = "//map-common-list-header[contains(text(),'PICKUP')]//" +
            "ancestor::mat-expansion-panel-header//following-sibling::div[@role='region']//mat-list-item//div[text()!='ON HOLD']")})
    List<WebElement> lstPickUpNewOrder;

    @FindAll(value = {@FindBy(xpath = "//map-common-list-header[contains(text(),'PICKUP')]//ancestor::mat-expansion-panel" +
            "-header//following-sibling::div[@role='region']//mat-list-item//p")})
    List<WebElement> lstPickUpOrders;

    @FindBy(xpath = "//map-common-order-item-details//div[contains(text(),'ORDER NUMBER')]//span")
    WebElement lblOrderNumber;

    @FindAll(value = {@FindBy(xpath = "//map-common-vehicle-header-map-icon//span[@class='vehicle_id_font']")})
    List<WebElement> lstVehicleName;

    @FindAll(value = {@FindBy(xpath = "//map-common-vehicle-item-header//span[contains(@class,'vehicle_id')]")})
    List<WebElement> lstVehicle;

    @FindBy(xpath = "//map-common-dispatch//img[contains(@class,'vehicle-img')]")
    WebElement lblVehicleOnMap;

    @FindBy(xpath = "//map-common-white-container//div[text()='DRAG & DROP HERE']")
    WebElement truckDragAndDrop;

    @FindBy(xpath = "(//map-common-vehicle-list//mat-list-item//span[text()='This vehicle has no assignments'])[1]" +
            "//ancestor::map-common-vehicle-item//map-common-vehicle-item-header//span[contains(text(),'')]")
    WebElement firstempyTruck;

    @FindBy(xpath = "(//span[contains(text(),'ORDERS')]//..//..//following-sibling::div//span)[2]")
    WebElement btnFilterOpen;

    @FindAll(value = {@FindBy(xpath = "//button[@role='menuitem']")})
    List<WebElement> lstMenuItem;

    @FindBy(xpath = "//button//mat-icon[text()='play_circle_outline']")
    WebElement btnStart;

    @FindBy(xpath = "//button[@title='']//mat-icon[text()='check']")
    WebElement btnCompleted;

    @FindBy(xpath = "//div[@class='mat-form-field-infix']")
    WebElement btnDriver;

    @FindAll(value = {@FindBy(xpath = "//mat-option")})
    List<WebElement> lstOption;

    @FindBy(xpath = "//mat-sidenav//mat-card-title")
    WebElement lblDispatcher;

    public void openDispatcher() {
        testStepsLog("Open Dispatcher");
        localDriver.navigate().to(FM_URL + File.separator + "dispatch");
    }

    public void getDispatcherName(int count) {
        excelUtils.setTestData(Prospect, count, 16, localDriver.findElement(By.xpath("//mat-sidenav//mat-card-title")).
                getAttribute("innerHTML"));
    }

    public boolean verifyDispatchPage() {
        return generics.isDisplay(lblToday) && generics.isDisplay(btnFilter);
    }

    public void addTruckFromMap(int count) {
        generics.pause(4);
        Actions act = new Actions(localDriver);
        generics.scrollToElement(truckDragAndDrop);
        testStepsLog("Drag and Drop Truck from the map to order.");
        act.dragAndDrop(localDriver.findElement(By.xpath("//map-common-vehicle-item//span[contains(text(),'" +
                excelUtils.getTestData("Prospect", count, 15) + "')]")), truckDragAndDrop).build().perform();
        generics.pause(10);
    }

    public void filterOrder(String status) {
        clickOnOrderStatusFilter();
        testStepsLog("Select Status : " + status);
        switch (status.toLowerCase()) {
            case "all":
                generics.clickOn(lstMenuItem.get(0));
                break;
            case "unassigned":
                generics.clickOn(lstMenuItem.get(1));
                break;
            case "assigned":
                generics.clickOn(lstMenuItem.get(2));
                break;
            case "in progress":
                generics.clickOn(lstMenuItem.get(3));
                break;
            case "on hold":
                generics.clickOn(lstMenuItem.get(4));
                break;
        }

    }

    private void clickOnOrderStatusFilter() {
        testStepsLog("Click on ORDER Status");
        generics.clickOnJS(btnFilterOpen);
    }

    public void clickOnFilter() {
        testStepsLog("Click on Order Filter.");
        generics.pause(2);
        generics.scrollToElement(btnFilter);
        generics.clickOnJS(btnFilter);
    }

    public void selectPickUp() {
        System.out.println("Click on oder to open.");
        generics.scrollToElement(localDriver.findElements(By.xpath("//map-common-list-header[contains(text(),'PICKUP')]" +
                "//ancestor::map-common-order-list//p[text()=' " + lblPickUpOrderName + " ']")).get(1));
        generics.clickOn(localDriver.findElements(By.xpath("//map-common-list-header[contains(text(),'PICKUP')]/" +
                "/ancestor::map-common-order-list//p[text()=' " + lblPickUpOrderName + " ']")).get(1));
        generics.pause(10);
    }

    public void selectDeliver() {
        System.out.println("Click on oder to open.");
        generics.scrollToElement(localDriver.findElements(By.xpath("//map-common-list-header[contains(text(),'DELIVER')]" +
                "//ancestor::map-common-order-list//p[text()=' " + lblPickUpOrderName + " ']")).get(1));
        generics.clickOn(localDriver.findElements(By.xpath("//map-common-list-header[contains(text(),'DELIVER')]/" +
                "/ancestor::map-common-order-list//p[text()=' " + lblPickUpOrderName + " ']")).get(1));
        generics.pause(10);
    }

    public void startOrder() {
        generics.pause(3);
        testStepsLog("Click on Start button to start order.");
        generics.clickOnJS(btnStart);
        generics.pause(2);
    }

    @FindAll(value = {@FindBy(xpath = "//span[@class='mat-option-text']")})
    List<WebElement> lstDrivers;

    public void completeOrder(int count) {
        new WebDriverWait(localDriver, 30).until(ExpectedConditions.visibilityOf(btnCompleted));
        generics.scrollToElement(btnCompleted);
        generics.clickOnJS(btnCompleted);
        generics.pause(2);
        ((JavascriptExecutor) localDriver).executeScript("document.evaluate('//div[@class=\"mat-form-field-infix\"]'," +
                "   document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue.click();");
        excelUtils.setTestData(Prospect, count, 25, "COMPLETED");
        String driverName = excelUtils.getTestData(Prospect, count, 18);
        generics.clickOnJS(localDriver.findElement(By.xpath("//span[@class='mat-option-text' and text()='" + driverName + "']")));
        generics.clickOnJS(btnProceed);
        generics.pause(5);
    }

    @FindBy(xpath = "//i[text()='date_range']")
    WebElement btnDatePicker;

    @FindBy(xpath = "//dispatch-order-aside-header//mat-icon[text()='search']")
    WebElement btnSearchOrder;

    @FindBy(xpath = "//button[@id='orderSearchButton']//following-sibling::input")
    WebElement txtSearch;

    public void searchAddress(int count) {
        SiteAddress = excelUtils.getTestData(Prospect, count, 0);
        new WebDriverWait(localDriver, 30).until(ExpectedConditions.invisibilityOfElementLocated(
                By.xpath("//div[text()='Loading...']")));
        generics.clickOnJS(btnSearchOrder);
        generics.type(txtSearch, SiteAddress.split(" ")[0]);
    }

    public void selectOrder() {
        generics.clickOn(localDriver.findElement(By.xpath("(//div[@class='card']//p[contains(text()," +
                "' " + SiteAddress.split(",")[0].toUpperCase() + " ')])[last()]")));
    }

    @FindBy(xpath = "//map-common-order-item-details//span[@class='badge_text']")
    WebElement lblOrderType;

    @FindBy(xpath = "//div[text()='SITE CONTACT']//following-sibling::div[1]")
    WebElement lblSiteContact;

    @FindBy(xpath = "//div[text()='SITE CONTACT']//following-sibling::div//div")
    WebElement lblCustomer;


    public static String SiteCustomer;

    public boolean verifyDeliveryDetails(int count) {
        SiteCustomer = excelUtils.getTestData(Prospect, count, 2);
        System.out.println(lblOrderType.getText());
        System.out.println(localDriver.findElement(By.xpath("//span[contains(text(),'" +
                SiteAddress.split(",")[0] + "')]")).getText().
                replace("\n", " ").replaceAll("\\s", ""));
        System.out.println(SiteAddress.replaceAll("\\s", ""));
        return lblOrderType.getText().equalsIgnoreCase("UNASSIGNED") &&
                localDriver.findElement(By.xpath("//span[contains(text(),'" +
                        SiteAddress.split(",")[0] + "')]")).
                        getText().replace("\n", "").trim().replaceAll("\\s", "").
                        equalsIgnoreCase(SiteAddress.replaceAll("\\s", ""))
                && lblSiteContact.getText().equalsIgnoreCase(excelUtils.getTestData(Prospect,
                count, 3)) && lblCustomer.getText().equalsIgnoreCase(SiteCustomer);
    }

    @FindBy(xpath = "//div[contains(text(),'EXPECTED TIME ON SITE')]//following-sibling::div//map-common-date-time-view//div")
    WebElement lblOrderDate;

    @FindBy(xpath = "//span[contains(@class,'selected')]")
    WebElement btnCurrentDate;

    @FindBy(xpath = "//button//div[contains(text(),'PROCEED')]")
    WebElement btnProceed;

    public boolean isOrderFromPast() {
        System.out.println(lblOrderDate.getText());
        return lblOrderDate.getText().contains(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy")));
    }

    public void selectCurrentDate() {
        generics.clickOn(lblOrderDate);
        generics.clickOn(btnCurrentDate);
        generics.clickOnJS(btnProceed);
    }

    @FindAll(value = {@FindBy(xpath = "//div[contains(@class,'_inprogress') or contains(@class,'_assigned')]//p")})
    List<WebElement> lstAssignedInProgressOrders;

    public void openOrderFromVehiclePane() {
        for (WebElement e : lstAssignedInProgressOrders) {
            System.out.println(e.getText());
            if (e.getText().contains(SiteAddress.split(",")[0])) {
                generics.clickOnJS(e);
                generics.pause(2);
                break;
            }
        }
    }

    @FindBy(xpath = "//map-common-vehicle-order-detail//div[contains(text(),'PICK UP')]")
    WebElement lblOrder;

    public boolean isPickupProject() {
        return lblOrder.getText().equalsIgnoreCase("PICK UP");
    }

    @FindBy(xpath = "//mat-icon[text()='filter_list']")
    WebElement btnMapFilter;

    public void addDisposalSite() {
        generics.clickOnJS(btnMapFilter);
    }

    @FindBy(xpath = "//mat-icon[contains(@class,'container_buttons')]")
    List<WebElement> btnEditContainer;

    @FindBy(xpath = "//input[@formcontrolname='dropContainerName']")
    WebElement txtContainerName;

    @FindAll(value = {@FindBy(xpath = "//mat-radio-button")})
    List<WebElement> lstRadios;

    @FindBy(xpath = "//form//mat-icon[text()='check']")
    WebElement btnSaveContainerName;

    public void enterPickUpContainerName() {
        generics.pause(1);
        generics.scrollToElement(btnEditContainer.get(0));
        generics.clickOnJS(btnEditContainer.get(0));
        generics.pause(2);
        if (!lstRadios.get(1).getAttribute("class").contains("mat-radio-disabled"))
            generics.type(txtContainerName, "Test_" + generics.getRandomBetween(100, 999));
        generics.clickOnJS(btnSaveContainerName);
        generics.pause(2);
    }

    public void enterDropOffContainerName() {
        generics.pause(2);
        generics.scrollToElement(btnEditContainer.get(1));
        generics.clickOnJS(btnEditContainer.get(1));
        if (!lstRadios.get(1).getAttribute("class").contains("mat-radio-disabled"))
            generics.type(txtContainerName, "Test_" + generics.getRandomBetween(100, 999));
        generics.clickOnJS(btnSaveContainerName);
        generics.pause(3);
    }

    @FindBy(xpath = "//span[text()='SCALE TICKET # ']")
    WebElement btnScaleTicket;

    @FindBy(xpath = "//span[text()='WEIGHT ']")
    WebElement btnWeight;

    @FindBy(xpath = "//div[contains(text(),'YES')]")
    WebElement btnYes;

    @FindBy(xpath = "//input[@formcontrolname='scaleTicket']")
    WebElement txtScaleTicket;

    @FindBy(xpath = "//input[@formcontrolname='weight']")
    WebElement txtWeight;

    @FindBy(xpath = "//map-common-disposal-site-info//mat-icon[text()='check']")
    WebElement btnAcceptTickerDetails;

    public void enterTicketDetails(int count) {
        int scaleTicket = generics.getRandomBetween(100, 999);
        int weight = generics.getRandomBetween(100, 999);
        generics.pause(2);
        generics.clickOnJS(btnScaleTicket);
        generics.type(txtScaleTicket, String.valueOf(scaleTicket));
        generics.type(txtWeight, String.valueOf(weight));
        excelUtils.setTestData(Prospect, count, 22, String.valueOf(scaleTicket));
        excelUtils.setTestData(Prospect, count, 23, String.valueOf(weight));
        generics.clickOnJS(btnAcceptTickerDetails);
        generics.pause(3);
    }

    @FindBy(xpath = "//map-common-vehicle-order-detail//div[contains(@class,'_dispatch_order_bold')]")
    WebElement lblOperationType;

    public String getOperationType() {
        System.out.println(generics.getText(lblOperationType));
        return generics.getText(lblOperationType);
    }

    @FindBy(xpath = "(//map-common-vehicle-order-detail//mat-icon)[2]")
    WebElement lblMatIcon;

    public boolean isIconUpward() {
        return generics.getText(lblMatIcon).equalsIgnoreCase("arrow_upward");
    }

    @FindBy(xpath = "//mat-tab-header//div[text()='FOR DRIVER']")
    WebElement btnDriverNotes;

    @FindBy(xpath = "//mat-tab-body//mat-icon[text()='add ']")
    WebElement btnAddDriverNote;

    @FindBy(xpath = "//textarea[@formcontrolname='note']")
    WebElement txtDriverNotes;

    @FindBy(xpath = "//form//mat-icon[text()='check']")
    WebElement btnAddNote;

    public void enterDriverNotes(int count) {
        String driverNote = generics.getRandomCharacters(10);
        generics.clickOn(btnDriverNotes);
        generics.pause(1);
        generics.clickOn(btnAddDriverNote);
        generics.pause(1);
        generics.type(txtDriverNotes, driverNote);
        driverNote = excelUtils.getTestData(Prospect, count, 19) + "," + driverNote;
        excelUtils.setTestData(Prospect, count, 19, driverNote);
        generics.clickOn(btnAddNote);
        generics.pause(5);
    }


    public boolean isPaymentDone(int count) {
        return !excelUtils.getTestData(Prospect, count, 24).isEmpty();
    }

    public void setFlag(int count, boolean flag) {
        if (flag) excelUtils.setTestData(Prospect, count, 25, "INCOMPLETE");
    }

    //===============================================================================Service Order




    @FindBy(xpath = "//button[contains(text(),'TEMP SERVICE')]/i")
    public WebElement btnTempService;

    public boolean isServiceOrderPageOpen() {
        return generics.isPresent(btnTempService);
    }

    public void clickonTempServicebtn() {
        generics.moveTo(btnTempService);
        JavascriptExecutor js = (JavascriptExecutor) localDriver;
        js.executeScript("window.scrollBy(0,-450)", "");
        generics.scrollToElement(btntempServices);
        generics.clickOn(btntempServices);
    }




    @FindBy(xpath = "//div[contains(text(),'Search Customer')]/following-sibling::div//input")
    public WebElement txtCustomerName;

    public boolean isPopupdisplayedcus() {
        return generics.isPresent(txtCustomerName);
    }


    public static String CustomerName;

    public void typeCustomername(int row) {
        CustomerName = excelUtils.getTestData(Prospect, row, 2);
        generics.clickOn(txtCustomerName);
        generics.type(txtCustomerName, CustomerName);

        generics.pause(5);
    }

    @FindBy(xpath = "//span[text()='Customer']")
    public WebElement lnkSelectCustomer;

    public void selectCustomer() {
        generics.clickOn(lnkSelectCustomer);
        testStepsLog("Customer Selected : " + CustomerName);
    }

    @FindBy(xpath = "//ng-select[@placeholder='Select Site']")
    public WebElement dpSelectSite;

    public void selectSite(int row) {
        String St = excelUtils.getTestData(Prospect, row, 3);
        generics.clickOn(dpSelectSite);
        generics.pause(1);

        WebElement element = localDriver.findElement(By.xpath("//span[text()='" + St + "']"));
        element.click();
        testStepsLog("Site selected : " + St);
        generics.pause(5);

    }


    public boolean isSelectedCustomerOpen() {
        List<WebElement> element = localDriver.findElements(By.xpath("//h3[text()='" + CustomerName + "']"));
        if (element.size() > 0) {
            return true;
        } else {
            return false;
        }

    }

    @FindBy(xpath = "//mat-select[@formcontrolname='materialId']")
    public WebElement dpMaterial;

    public void selectMaterial(int row) {
        generics.pause(5);
        generics.moveTo(dpMaterial);
        String Material = excelUtils.getTestData(Prospect, row, 12);
        JavascriptExecutor js = (JavascriptExecutor) localDriver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        generics.scrollToElement(dpMaterial);
        generics.clickOn(dpMaterial);
        WebElement element = localDriver.findElement(By.xpath("//span[contains(text(),'" + Material + "')]"));
        element.click();

        testStepsLog("Material selected :" + Material);
        generics.pause(2);
    }


    public void selectContainerSize(int row) {
        generics.clickOn(dpContainerSize);
        String ContainerSize = excelUtils.getTestData(Prospect, row, 8);
        WebElement element = localDriver.findElement(By.xpath("//span[contains(text(),'" + ContainerSize + "')]"));
        element.click();
        testStepsLog("Container size selected : " + ContainerSize);
    }

    @FindBy(xpath = "//mat-select[@formcontrolname='haulTypeId']")
    public WebElement dpHaulType;

    public void selectHaulType(int row) {
        generics.clickOn(dpHaulType);
        String HaulType = excelUtils.getTestData(Prospect, row, 11);
        WebElement element = localDriver.findElement(By.xpath("//span[contains(text(),'" + HaulType + "')]"));
        element.click();
        testStepsLog("Haul Type selected : " + HaulType);
        generics.pause(5);
    }


    @FindBy(xpath = "//b[text()='Create New Customer']")
    public WebElement lnkCreateNewCustomer;

    public void clickonCreateNewCustomerlnk() {
        generics.clickOn(lnkCreateNewCustomer);
        testStepsLog("Clicked on Create New Customer button");
    }



    public boolean isEnteredCustomerDisplayed() {
        String cn = generics.getValue(txtComplanyName);
        if (cn.equals(CustomerName)) {
            return true;
        } else {
            return false;
        }

    }




    public void selectBusinessUnit1() {

        generics.clickOn(dpBusinessUnit1);
        generics.pause(2);

        generics.clickOn(firstOption);
        testStepsLog("Site Business unit Selected");
        generics.pause(4);

    }




    @FindBy(xpath = "(//mat-select[@formcontrolname='countryId'])[1]")
    public WebElement dpCountry;

    public void selectcountry() {
        generics.moveTo(dpCountry);
        generics.clickOn(dpCountry);
        generics.clickOn(firstOption);
    }





    @FindBy(xpath = "(//mat-select[@formcontrolname='provinceId'])[1]")
    public WebElement dpstate;

    public void selectState() {
        generics.moveTo(dpstate);
        generics.clickOn(dpstate);
        generics.clickOn(firstOption);
        testStepsLog("State selected");
    }

    @FindBy(xpath = "(//input[@formcontrolname='city'])[1]")
    public WebElement dpCity;

    public void typeCity() {
        generics.moveTo(dpCity);
        generics.clickOn(dpCity);
        generics.type(dpCity, "test");
        testStepsLog("City selected");
    }



    public int getRowsCustomerCreation() {
        try {
            FileInputStream file = new FileInputStream(
                    System.getProperty("user.dir") + "/src/test//java//gfl//testData//Prospect.xlsx");
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheet("Sheet1");
            return sheet.getPhysicalNumberOfRows();
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }




    @FindBy(xpath = "//input[@formcontrolname='poNumber']")
    public WebElement txtPoNumber;

    public static int PoNumber;

    public void typePoNumber() {
        PoNumber = generics.getRandomBetween(111111, 999999);

        generics.moveTo(txtPoNumber);
        generics.type(txtPoNumber, String.valueOf(PoNumber));

        testStepsLog("PoNumber inserted");
        JavascriptExecutor js = (JavascriptExecutor) localDriver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }



    @FindBy(xpath = "//span[contains(text(),'Site Contacts Same As Company Contacts ')]")
    public WebElement chkSiteContactSameAsCompanyContact;



    //@FindBy(xpath = "(//input[@formcontrolname='postalCode'])[3]")
    //public WebElement txtPostalcode;

    public void typePostalcode() {
        String a = generics.getRandomCharacters(1);
        String b = generics.getRandomCharacters(1);
        String c = generics.getRandomCharacters(1);
        String a1 = String.valueOf(generics.getRandomBetween(1, 9));
        String b1 = String.valueOf(generics.getRandomBetween(1, 9));
        String c1 = String.valueOf(generics.getRandomBetween(1, 9));
        String pc = a + a1 + b + " " + b1 + c + c1;
        generics.type(txtPostalcode, Keys.CONTROL + "a" + Keys.DELETE);
        generics.clickOn(txtPostalcode);
        generics.type(txtPostalcode, pc);
        testStepsLog("Postal code inserted +" + pc);
        generics.pause(4);
    }

    @FindBy(xpath = "//button[contains(text(),'save customer')]")
    public WebElement btnSaveCustomer;

    public void clickonbtnSaveCustomer1() {
        generics.clickOn(btnSaveCustomer);
        generics.pause(5);
        testStepsLog("Clicked on Save Customer button");
    }

    @FindBy(xpath = "//div[contains(text(),'Customer has been created successfully!')]")
    public WebElement SuccessMessage;

    public boolean isSuccessMessageDisplayed() {
        return generics.isPresent(SuccessMessage);
    }

    public boolean isCustomerAdded() {
        List<WebElement> element = localDriver.findElements(By.xpath("//h2[text()='" + CustomerName + "']"));
        if (element.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isAllDetailsCorrect() {
        generics.pause(4);
        List<WebElement> contact = localDriver.findElements(By.xpath("//td[text()=' " + ContactName + " ']"));
        List<WebElement> email = localDriver.findElements(By.xpath("//td[text()=' " + Email + " ']"));
        List<WebElement> position = localDriver.findElements(By.xpath("//td[text()=' " + ContactPosition + " ']"));
        List<WebElement> phone = localDriver.findElements(By.xpath("//td[contains(text(),'" + PhoneNumber + "')]"));
        if (contact.size() > 0 && email.size() > 0 && position.size() > 0 && phone.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    @FindBy(xpath = "//span[text()='SITES' and contains(@class,'stepper')]")
    public WebElement tabSite;

    @FindBy(xpath = "//li[contains(text(),'SERVICES')]/mat-icon")
    public WebElement tabServices;

    @FindBy(xpath = "//span[text()='schedule event']")
    public WebElement btnscheduleEvent;


    public void clickonSitetab() {
        generics.clickOn(tabSite);
        testStepsLog("CLicked ON Site tab after customer creation");
    }

    public void clickonServicestab() {
        generics.pause(3);
        generics.moveTo(btnscheduleEvent);
        generics.clickOn(tabServices);
        testStepsLog("CLicked ON Services tab after customer creation");
    }

    @FindBy(xpath = "//button[contains(text(),'TEMP SERVICE')]")
    public WebElement btntempServices;

    public void clickonTempServiceButton() {
        generics.moveTo(btntempServices);
        generics.clickOn(btntempServices);
        testStepsLog("Clickon Temp Serivce button");
        generics.pause(2);
    }

    @FindBy(xpath = "//h5[text()='Create Service']")
    public WebElement titleCreateService;

    @FindBy(xpath = "//h5[text()='Service Addresses']")
    public WebElement titleServiceAddress;

    public boolean isCreateServiceSectionDisplayed() {
        generics.pause(3);
        return generics.isPresent(titleCreateService);
    }


    @FindBy(xpath = "//h5[text()='Service Details']")
    public WebElement titleServiceDetails;

    public boolean isServiceSectionDisplayed() {
        generics.pause(3);
        return generics.isPresent(titleServiceDetails);
    }



    public void selectContainerType() {
        generics.scrollToElement(dpContainerType);
        generics.pause(2);
        generics.clickOn(dpContainerType);

        generics.clickOn(firstOption);
        testStepsLog("Container Type selected");
        generics.pause(2);
    }

    @FindBy(xpath = "//h6[text()='Requested Date']/following-sibling::mat-form-field//button[@aria-label='Open calendar']")
    public WebElement btnRequestedDate;

    @FindBy(xpath = "//div[contains(@class,'mat-calendar-body-today')]/parent::td/following-sibling::td[1]/div")
    public WebElement btnNextDay;

    @FindBy(xpath = "//div[contains(@class,'mat-calendar-body-today')]/ancestor::tr/following-sibling::tr[1]//td[1]/div")
    public WebElement btnNextDayofNextWeek;


    public void selectRequiestedDate() {
        generics.moveTo(txtUploadingTime);
        generics.clickOn(btnRequestedDate);
        if (generics.isPresent(btnNextDay)) {
            generics.clickOn(btnNextDay);
        } else {
            generics.clickOn(btnNextDayofNextWeek);
        }

    }


    @FindBy(xpath = "//input[@formcontrolname='unloadingTime']")
    public WebElement txtUploadingTime;

    public static String UploadTime;

    public void InsertUploadingTime() {
        UploadTime = String.valueOf(generics.getRandomBetween(1, 9));
        generics.moveTo(btnGetPricing);
        generics.type(txtUploadingTime, UploadTime);

        testStepsLog("Uploading Time Inserted " + UploadTime);
        generics.pause(2);

    }

    @FindBy(xpath = "//button[contains(text(),'Get Pricing')]")
    public WebElement btnGetPricing;

    @FindBy(xpath = "//mat-select[@formcontrolname='disposalSiteId']")
    public WebElement dpDesposibleSite;

    public void selectDesposibleSite() {

        generics.clickOn(dpDesposibleSite);

        generics.clickOn(firstOption);
        testStepsLog("Desposible site selected");
    }

    @FindBy(xpath = "//mat-select[@formcontrolname='billingCycleErpCode']")
    public WebElement dpBillingCycle;

    public void selectBillingCycle() {

        generics.clickOn(dpBillingCycle);

        generics.clickOn(firstOption);
        testStepsLog("Billing Cycle selected");
        generics.pause(1);
    }

    @FindBy(xpath = "//input[@formcontrolname='demurrageDays']")
    public WebElement txtDemurrageDays;

    public static String DMDay;

    public void typeDemurageDay() {
        DMDay = String.valueOf(generics.getRandomBetween(1, 9));
        generics.moveTo(lblExtraFees);
        generics.type(txtDemurrageDays, DMDay);

        testStepsLog("Demmurage Day Inserted " + DMDay);
        generics.pause(1);

    }


    @FindBy(xpath = "//div[text()='EXTRA FEES']")
    public WebElement lblExtraFees;


    public void clickonGetPricing() {
        generics.mouseHover(lblExtraFees);
        generics.waitForElementVisible(btnGetPricing);
        generics.scrollToElement(btnGetPricing);
        generics.mouseHover(lblExtraFees);
        generics.clickOn(btnGetPricing);
        testStepsLog("Clicked on button get Pricing");
        generics.pause(8);
    }

    @FindBy(xpath = "//h2[text()='SERVICE CHARGES']")
    public WebElement lblServiceCharge;

    public boolean isServiceChargesectionDisplayed() {
        return generics.isPresent(lblServiceCharge);

    }


    @FindBy(xpath = "//textarea[@formcontrolname='dispatcherNotes']")
    public WebElement txtDispatchnote;

    public static String note;

    public void typeDispatchnote(int i) {
        note = generics.getRandomCharacters(10);
        JavascriptExecutor js = (JavascriptExecutor) localDriver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        generics.clickOn(txtDispatchnote);
        generics.type(txtDispatchnote, note);
        excelUtils.setTestData(Prospect,i, 17,note);
        testStepsLog("Dispatch note inserted " + note);
    }

    @FindBy(xpath = "//div[contains(text(),'Driver')]")
    public WebElement tabdriver;

    @FindBy(xpath = "//textarea[@formcontrolname='driverNotes']")
    public WebElement txtDrivernote;

    public static String Drivernote;

    public void typeDrivernote(int i) {
        Drivernote = generics.getRandomCharacters(10);
        generics.clickOn(tabdriver);
        generics.clickOn(txtDrivernote);
        generics.type(txtDrivernote, Drivernote);
        excelUtils.setTestData(Prospect,i, 19,note);
        testStepsLog("Driver note inserted " + Drivernote);
    }

    @FindBy(xpath = "//button[contains(text(),'Save Service')]")
    public WebElement btnSaveService;

    public void clickonSaveServiceButton() {
        generics.clickOn(btnSaveService);
        generics.pause(10);
    }

    @FindBy(xpath = "//div[contains(text(),'Service has been created successfully !')]")
    public WebElement SuccessMessageofSaveService;

    public boolean isServiceSaved() {
        return generics.isPresent(SuccessMessageofSaveService);
    }

    @FindBy(xpath = "//button[contains(text(),'APPROVE')]")
    public WebElement btnApproved;

    public void clickonApprovebutton()
    {
        if(generics.isPresent(btnApproved)) {
            generics.clickOn(btnApproved);
            testStepsLog("Clicked on Approved button");
        }
    }

    @FindBy(xpath = "//textarea[@placeholder='Enter Reason For Approval']")
    public WebElement txtApproval;

    public void typeApprovalReason() {
        generics.clickOn(txtApproval);
        generics.type(txtApproval, generics.getRandomCharacters(10));
        testStepsLog("Reason inserted : ");
    }

    @FindBy(xpath = "//input[@formcontrolname='searchValue']")
    public WebElement Searchbox;

    public void searchProspect()
    {
        generics.pause(3);
        generics.type(Searchbox,ProspectName+Keys.ENTER);
        generics.pause(4);

    }

    @FindBy(xpath = "(//button[contains(text(),' APPROVE')])[2]")
    public WebElement btnReasonApprove;

    public void clickonReasonApprovebutton() {
        generics.clickOn(btnReasonApprove);
        testStepsLog("Clicked on Approved button");
    }


    public boolean isAllDetailsSavedCurreclty() {
        List<WebElement> dn = localDriver.findElements(By.xpath("//p[text()='" + note + "']"));
        List<WebElement> drn = localDriver.findElements(By.xpath("//p[text()='" + Drivernote + "']"));
        List<WebElement> cn = localDriver.findElements(By.xpath("//span[text()='" + CustomerName + "']"));
        List<WebElement> sn = localDriver.findElements(By.xpath("//p[text()='" + SiteName + "']"));
        if (dn.size() > 0 && drn.size() > 0 && cn.size() > 0 && sn.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    @FindBy(xpath = "//mat-chip//mat-icon[text()='add']")
    public WebElement btnAddServiceOrder;

    @FindBy(xpath = "//h3[text()='SERVICE ORDER SITES']")
    public WebElement titleServiceOrderSite;


    public void clickonAddServiceOrderButton() {
        generics.moveTo(titleServiceOrderSite);
        generics.clickOn(btnAddServiceOrder);
        testStepsLog("Clicked on Add Service Order button");
        generics.pause(5);
    }

    @FindBy(xpath = "//div[contains(text(),'Service order has been saved successfully !')]")
    public WebElement SuccessMessageServiceOrder;

    @FindBy(xpath = "//button[contains(text(),'Save')]")
    public WebElement btnSaveServiceOrder;

    public void clickonSavebtnOfServiceOrder() {
        generics.clickOn(btnSaveServiceOrder);
        testStepsLog("Clicked on Save button of Service order");
        generics.pause(5);

    }

    public boolean isNewServiceOrderAddded() {
        return generics.isPresent(SuccessMessageServiceOrder);
    }

    @FindBy(xpath = "//button[contains(text(),'Pay Now')]")
    public WebElement btnPayNow;

    public void clickonPaynow() {
        generics.clickOn(btnPayNow);
        testStepsLog("Clicked on Pay Now Button");
        generics.pause(3);
    }

    @FindBy(xpath = "//mat-radio-group[@formcontrolname='existingCardNo']//mat-radio-button//label/div[1]")
    public WebElement rbPaymentMethod;

    public void SelectPaymentMethod() {
        generics.clickOn(rbPaymentMethod);
        testStepsLog("Payment method selected");
    }

    @FindBy(xpath = "//input[@formcontrolname='existingCardCvv']")
    public WebElement txtCVV;

    public void typeCVV() {
        generics.type(txtCVV, String.valueOf(generics.getRandomBetween(111, 999)));

        testStepsLog("Payment method selected");
    }

    @FindBy(xpath = "//mat-radio-group[@formcontrolname='existingAddress']")
    public WebElement rbAddress;

    public void SelectAddress() {
        generics.clickOn(rbAddress);
        testStepsLog("Address selected");
    }

    @FindBy(xpath = "//mat-checkbox[@formcontrolname='confirmBox']")
    public WebElement chkConfirmation;

    public void SelectConfirmationCheckbox() {
        generics.clickOn(chkConfirmation);
        testStepsLog("Confirmation checkbox selected");
    }

    @FindBy(xpath = "//div[contains(@class,'pay_now_buttons')]/button[2]")
    public WebElement btnPayAmount;

    public void ClickonPayAmount() {
        generics.scrollToElement(btnPayAmount);
        JavascriptExecutor js = (JavascriptExecutor) localDriver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        generics.clickOn(btnPayAmount);
        testStepsLog("Clicked on PayAmount button");
        generics.pause(10);
    }

    @FindBy(xpath = "//input[@formcontrolname='file']")
    public WebElement btnFileUpload;

    public void UploadFile() {
        try {
            Actions action = new Actions(localDriver);
            action.moveToElement(btnFileUpload, 20, 0).click().build().perform();
            Robot robot = new Robot();
            generics.pause(2);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            StringSelection stringSelection = new StringSelection(TEST_DATA_LOCATION + File.separator + "username.xlsx");
            clipboard.setContents(stringSelection, null);
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            testStepsLog("Clicked on File Upload");
            generics.pause(10);

        } catch (Exception e) {
            testStepsLog("File Not uploaded");
        }


    }

    @FindBy(xpath = "//button[contains(text(),' save & proceed to temp service')]")
    public WebElement btnSaveAndProceedToTempService;

    public void clickonSaveAndProceedToTempServicebutton() {
        generics.clickOn(btnSaveAndProceedToTempService);
        testStepsLog("Clicked ON Save and Proceed To Temp Service Button");
        generics.pause(6);
    }

    @FindBy(xpath = "//div[contains(text(),'Payment Successful')]")
    public WebElement SuccessMessagePayment;

    public boolean isPaymentDone() {
        return generics.isPresent(SuccessMessagePayment);
    }

    public void zoomout() {
        JavascriptExecutor executor = (JavascriptExecutor) localDriver;
        executor.executeScript("document.body.style.zoom = '0.8'");

        testStepsLog("Zoom out");
        generics.pause(2);
    }

    @FindBy(xpath = "//span[text()='UCN']/parent::p")
    public WebElement customerID;

    public static String cd;

    public void getCustomerID(int row) {
        cd = generics.getText(customerID);
        try {
            SetTestData(cd, row, 3);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        testStepsLog("Customer Created ID : " + cd);
    }

    @FindBy(xpath = "//mat-checkbox[@formcontrolname='siteAddressesSameAsCompanyAddresses']")
    public WebElement chksiteAddressesSameAsCompanyAddresses;

    public void DeSelectsiteAddressesSameAsCompanyAddresses() {
        generics.clickOn(chksiteAddressesSameAsCompanyAddresses);
        testStepsLog("Site Address Same As Company Address checkbox unSelected");
    }






    @FindBy(xpath = "//mat-form-field[contains(@class,'show_page_select')]//mat-select")
    public WebElement dppagesize;

    @FindBy(xpath = "//span[contains(text(),'100')]")
    public WebElement hundred;


    public void changepagesize() {
        JavascriptExecutor js = (JavascriptExecutor) localDriver;
        js.executeScript("window.scrollBy(0,-700)", "");
        generics.pause(2);
        generics.clickOn(dppagesize);
        generics.clickOn(hundred);
        generics.pause(3);
        testStepsLog("Pagesize changed to 100");

    }

    public void getCustomerName(int row) {
        CustomerName = excelUtils.getTestData(Prospect, row, 2);
        testStepsLog("Checking details for customer : " + CustomerName);
    }

    public void openServiceOrder() {
        WebElement element = localDriver.findElement(By.xpath("//td[contains(text(),'" + CustomerName + "')]/preceding-sibling::td/a"));
        element.click();
        testStepsLog("selected service order opened ");
    }

    @FindBy(xpath = "//p[text()='Status']/following-sibling::*")
    public WebElement txtStatus;

    public boolean isProperStatusDisplayed(int row) {
        String status = generics.getText(txtStatus);
        String st = excelUtils.getTestData(Prospect, row, 7);
        if (status.toLowerCase().equals(st.toLowerCase())) {
            return true;
        } else {
            return false;
        }
    }

    @FindBy(xpath = "//p[text()='Vehicle']/following-sibling::div//p")
    public WebElement txtVehicle;

    public boolean isProperVehicleDisplayed(int row) {
        JavascriptExecutor js = (JavascriptExecutor) localDriver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        String vehicle = generics.getText(txtVehicle);
        String vc = excelUtils.getTestData(Prospect, row, 15);
        if (vehicle.toLowerCase().equals(vc.toLowerCase())) {
            return true;
        } else {
            return false;
        }
    }

    @FindBy(xpath = "//p[text()='Dispatcher']/following-sibling::p")
    public WebElement txtDispatcher;

    public boolean isProperDispatcherDisplayed(int row) {

        String Dispatcher = generics.getText(txtDispatcher);
        String ds = excelUtils.getTestData(Prospect, row, 16);
        if (Dispatcher.toLowerCase().equals(ds.toLowerCase())) {
            return true;
        } else {
            return false;
        }
    }

    @FindBy(xpath = "//p[text()='Driver']/following-sibling::p")
    public WebElement txtDriver;

    public boolean isProperScaleDisplayed(int row) {

        String scale = generics.getText(scaleticket);
        String s = excelUtils.getTestData(Prospect, row, 22);
        if (scale.equals(s)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isProperweightDisplayed(int row) {

        String Weight = generics.getText(weight);
        String w = excelUtils.getTestData(Prospect, row, 23);
        if (Weight.equals(w)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isProperDriverDisplayed(int row) {

        String Driver = generics.getText(txtDriver);
        String dv = excelUtils.getTestData(Prospect, row, 18);
        if (Driver.toLowerCase().equals(dv.toLowerCase())) {
            return true;
        } else {
            return false;
        }
    }

    @FindBy(xpath = "//p[text()='Dispatcher Notes']/following-sibling::div/p")
    public WebElement txtDispatcherNote;

    public boolean isProperDispatcherNoteDisplayed(int row) {

        String Dispatchernote = generics.getText(txtDispatcherNote);
        String dn = excelUtils.getTestData(Prospect, row, 17);
        String[] dis= dn.split(",");
        for(int i=0;i<dis.length;i++) {
            if (Dispatchernote.contains(dis[i])) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }

    @FindBy(xpath = "//p[text()='Driver Notes']/following-sibling::div/p")
    public WebElement txtDriverNote;

    public boolean isProperDriverNoteDisplayed(int row) {

        String Drivernote = generics.getText(txtDriverNote);
        String dn = excelUtils.getTestData(Prospect, row, 19);
        if (Drivernote.equals(dn)) {
            return true;
        } else {
            return false;
        }
    }



    @FindAll(value = {@FindBy(xpath = "//*[name()='mat-icon' and text()='check']")})
    List<WebElement> check_button;

    @FindBy(xpath = "(//span[@class='serviceZone_text'])[1]")
    WebElement first_zone;

    public void selectZones() {
        if (check_button.size() > 0) {
            generics.clickOn(first_zone);
            generics.clickOn(check_button.get(0));
        }
    }

    @FindBy(xpath = "//span[contains(@class,'mat-menu-trigger') and text()='UNASSIGNED']")
    WebElement orders_list_trigger;

    public void openOrdersList() {
        generics.clickOn(orders_list_trigger);
    }

    @FindBy(xpath = "//map-common-list-header[contains(text(),'PICKUP')]/ancestor::mat-expansion-panel-header/following-sibling::div//mat-list-item[1]")
    WebElement firstPickup;

    @FindBy(xpath = "//map-common-list-header[contains(text(),'DELIVER')]/ancestor::mat-expansion-panel-header/following-sibling::div//mat-list-item[1]")
    WebElement firstDelivery;

    @FindBy(xpath = "(//map-common-vehicle-list//mat-list-item//span[text()='This vehicle has no assignments'])[1]")
    WebElement firstempyTrucks;

    @FindBy(xpath = "(//map-common-vehicle-list//mat-list-item//span[text()='This vehicle has no assignments'])[1]/ancestor::map-common-vehicle-item//span[@class='vehicle_id_font _ellipsis']")
    WebElement EmptyVehicalname;


    public void dragPickupOrderToTruck() {
        Actions action = new Actions(localDriver);
        action.dragAndDrop(firstPickup, firstempyTrucks).build().perform();
        testStepsLog("Dragged order to truck");

    }


    public void getEmptyVehicalname() {
        VehicalName = generics.getText(EmptyVehicalname);
        testStepsLog("Vehical Name : " + VehicalName);
    }

    public void openVehicalDetail() {
        generics.moveTo(firstempyTruck);
        generics.clickOn(EmptyVehicalname);
    }

    public void dragDeliveryOrderToTruck() {
        Actions action = new Actions(localDriver);
        action.dragAndDrop(firstDelivery, firstempyTruck).build().perform();
        testStepsLog("Dragged order to truck");

    }

    @FindBy(xpath = "//i[text()='date_range']")
    WebElement dildate;

    //@FindBy(xpath = "//div[text()='PROCEED']")
    //WebElement btnProceed;

    @FindBy(xpath = "//div[contains(text(),'EXPECTED TIME ON SITE')]/following-sibling::div")
    public WebElement orderdate;

    @FindBy(xpath = "//span[contains(@class,'selected')]/parent::td/following-sibling::td/span")
    public WebElement ordernextday;

    @FindBy(xpath = "//span[contains(@class,'selected')]/ancestor::tr/following-sibling::tr[1]//td[2]/span")
    public WebElement ordernextday1;


    @FindBy(xpath = "//div[contains(@class,'is-today')]/parent::div/following-sibling::div[1]")
    public WebElement btnNextDayFM;


    public void changeDate() {
        generics.pause(3);
        generics.clickOn(dildate);


        if (generics.isPresent(btnNextDayFM)) {
            generics.clickOn(btnNextDayFM);
        }
        generics.pause(5);

    }

    public void changeOrderDate() {
        generics.pause(3);
        generics.clickOn(firstPickup);
        generics.clickOn(orderdate);


        if (generics.isPresent(ordernextday)) {
            generics.clickOn(ordernextday);
        } else {
            generics.clickOn(ordernextday1);
        }
        generics.clickOn(btnProceed);
        generics.pause(2);

    }




    @FindBy(xpath = "//p[text()='Scale Ticket No.']/following-sibling::p")
    WebElement scaleticket;

    @FindBy(xpath = "//p[text()='Weight']/following-sibling::p")
    WebElement weight;

    public String getCustomerIDStatus(int row) {

        String Cust= excelUtils.getTestData(Prospect, row, 20);
        return Cust;

    }
    public void UpdateStatus(int row) {

        excelUtils.setTestData(Prospect,row,24,"Pass");
        testStepsLog("Status updated in excel");


    }

    public boolean isFMCompleted(int count) {
        return !excelUtils.getTestData(Prospect, count, 24).isEmpty();
    }
    public boolean isApproveDisplay() {
        return generics.isPresent(btnApproved);
    }

    @FindBy(xpath = "//button[@id='btnProceed']")
    public WebElement btncomplete;

    public void ClickonPopComplete() {
        generics.clickOn(btncomplete);
        generics.pause(4);
        testStepsLog("Clicked on COMPLETE.");
    }


    @FindBy(xpath = "//div[@class='flex_row align_items_left id_container']/div/div")
    public WebElement prospectID;

    public static String pId;

    public void getProspecctID(int row) {

        try {
            SetTestData(pId, row, 20);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        testStepsLog("Prospect Created ID  : " + pId);
    }
}
