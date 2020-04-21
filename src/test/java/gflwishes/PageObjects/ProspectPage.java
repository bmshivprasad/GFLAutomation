package gflwishes.PageObjects;

import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import gflwishes.base.Generics;
import gflwishes.testcases.Prospect;
import gflwishes.utilities.ExcelUtils;

public class ProspectPage extends Prospect {

	private WebDriver localDriver;
    private Generics generics;

    public ProspectPage(WebDriver baseDriver) {
        this.localDriver = baseDriver;
        PageFactory.initElements(localDriver, this);
        generics = new Generics(localDriver);
        log4j = Logger.getLogger("AdminContract");
    }

    public int getRowsExcel() {
        FileInputStream file;
        try {
            file = new FileInputStream(
                    System.getProperty("user.dir") + "/src/test//java//gfl//testData//ProspectAll.xlsx");
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
                System.getProperty("user.dir") + "/src/test//java//gfl//testData//ProspectAll.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheet("Sheet1"); // workbook.getSheetAt(0)


        XSSFRow row = sheet.getRow(rowt); // Get the row in which data is stored
        XSSFCell cell = row.createCell(colt);
        cell.setCellValue(dta);
        FileOutputStream fo = new FileOutputStream(
                System.getProperty("user.dir") + "/src/test//java//gfl//testData//ProspectAll.xlsx");
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
        String BU = excelUtils.getTestData(ProspectAll, row, 4);

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
        String Add = excelUtils.getTestData(ProspectAll, row, 0);
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
        String pc = excelUtils.getTestData(ProspectAll, row, 1);
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
    @FindBy(xpath = "//button[contains(text(),'Add New Service')]")
    public WebElement btnAddnewServices;

    public void clickonAddNewServiceButton() {

        JavascriptExecutor js = (JavascriptExecutor) localDriver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        generics.moveTo(Aggreement);
        generics.clickOn(btnAddnewServices);
        testStepsLog("Clicked On Add new Service Button");
        generics.pause(3);
    }

    @FindBy(xpath = "//button[contains(text(),'ADD SERVICE')]")
    public WebElement btnAddServices;

    public void clickonAddServiceButton() {

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

    @FindBy(xpath = "//mat-option//span[text()=' Front End ']")
    public WebElement optionFrontEnd;

    @FindBy(xpath = "//mat-option//span[text()=' Rear Load ']")
    public WebElement optionRearLoad;

    @FindBy(xpath = "//mat-option//span[text()=' Rear Load Toters ']")
    public WebElement optionRearLoadToters;

    @FindBy(xpath = "//mat-option//span[text()=' Side Load Toters ']")
    public WebElement optionSideLoadToters;

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

    public void selectServiceTypeFE()
    {
        generics.pause(2);
        generics.clickOn(dpServiceType);
        generics.clickOn(optionFrontEnd);
        testStepsLog("Service type selected : Front End");
    }

    public void selectServiceTypeRL()
    {
        generics.pause(2);
        generics.clickOn(dpServiceType);
        generics.clickOn(optionRearLoad);
        testStepsLog("Service type selected : Rear Load");
    }

    public void selectServiceTypeSLT()
    {
        generics.pause(2);
        generics.clickOn(dpServiceType);
        generics.clickOn(optionSideLoadToters);
        testStepsLog("Service type selected : Side Load toters");
    }

    public void selectServiceTypeRLT()
    {
        generics.pause(2);
        generics.clickOn(dpServiceType);
        generics.clickOn(optionRearLoadToters);
        testStepsLog("Service type selected : Rear");
    }

    @FindBy(xpath = "//input[@formcontrolname='containerCount']")
    public WebElement txtContainerCount;

    @FindBy(xpath = "//*[contains(text(),'DISPOSAL')]")
    public WebElement titleDisposal;

    public void typeContainerCount(int row)
    {
    	generics.pause(2);
        generics.moveTo(titleDisposal);
        String CC=excelUtils.getTestData("ProspectAll", row, 5);
        generics.type(txtContainerCount,CC);
        testStepsLog("Container Count : " + CC);
    }

    @FindBy(xpath = "//mat-select[@formcontrolname='containerTypeId']")
    public WebElement dpContainerType;

    public void SelectContainerType(int row)
    {
    	generics.pause(6);
        String CT=excelUtils.getTestData("ProspectAll", row, 6);
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
        String CF=excelUtils.getTestData("ProspectAll", row, 7);
        generics.type(txtContainerFee,CF);
        testStepsLog("Container Fee : " + CF);
    }

    @FindBy(xpath = "//mat-select[@formcontrolname='containerSizeId']")
    public WebElement dpContainerSize;

    public void SelectContainerSize(int row)
    {
    	generics.pause(2);
        String CS=excelUtils.getTestData("ProspectAll", row, 8);
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
        String F=excelUtils.getTestData("ProspectAll", row, 9);
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
        String CT=excelUtils.getTestData("ProspectAll", row, 10);
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
    	//JavascriptExecutor js = (JavascriptExecutor)localDriver;
    	//js.executeScript("arguments[0].scrollIntoView(true);",dpHaultype);

    	generics.pause(2);
        String HT=excelUtils.getTestData("ProspectAll", row, 11);
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
        String M=excelUtils.getTestData("ProspectAll", row, 12);
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
        generics.pause(10);
        testStepsLog("Disposible site selected : ");
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
        String ET=excelUtils.getTestData("ProspectAll", row, 13);
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

        JavascriptExecutor js = (JavascriptExecutor)localDriver;
        js.executeScript("arguments[0].scrollIntoView(true);",btnCalculate);

    	generics.pause(5);
        String LT=excelUtils.getTestData("ProspectAll", row, 14);
        generics.type(txtLocationType,LT);
        testStepsLog("Location Type : " + LT);
    }

    @FindBy(xpath = "//button[contains(text(),'ADD SERVICE')]")
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
    	 generics.pause(5);
    	generics.clickOn(btnSubmitDocument);
        generics.pause(5);
        testStepsLog("Clicked on Submit Document.");
    }

    @FindBy(xpath = "//button[contains(text(),'SUBMIT TO CDE')]")
    public WebElement btnSubmittoCDE;
    public void clickonSubmitCDE() {

    	generics.pause(5);
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
        generics.clickOn(btnSubmit);
        generics.pause(5);
        testStepsLog("Clicked on SUBMIT.");
    }

    @FindBy(xpath = "//button[contains(text(),'SUBMIT TO CDE')]")
    public WebElement btnSubmittocde;

    public void clickonSubmittoCDE() {
    	generics.pause(5);
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

    @FindBy(xpath = "//tr/td[contains(.,'CapexTest Approver')]/../td//div[contains(text(),'Unassigned')]")
    public WebElement btnUnassigned;

    @FindBy(xpath = "(//td[text()=' CapexTest Approver ']/preceding-sibling::td//a)[1]")
    public WebElement AssigntoMe;


    public void ClickonUnassigned() {
    	generics.pause(5);
        generics.moveTo(btnUnassigned);
        generics.clickOn(AssigntoMe);
        generics.pause(5);
        testStepsLog("Clicked on Unassigned.");
    }

    @FindBy(xpath = "//div[contains(text(),'TICKET DETAILS')]')]")
    public WebElement Ticketdetailspage;

    @FindBy(xpath = "//h3[contains(text(),'ORDER FORM NOTES')]")
    public WebElement Orderformnotes;

    public boolean isTicketDetailsOpen() {

        generics.pause(3);
        return generics.isPresent(Ticketdetailspage);

    }

    @FindBy(xpath ="//input[@formcontrolname=\"erpId\"]")
    public WebElement txtServicelineitem;
    public static int Servicelineitem;

    public void typeServiceLineItem() {
    	Servicelineitem = generics.getRandomBetween(1, 99999);
        generics.clickOn(txtServicelineitem);
        generics.type(txtServicelineitem, String.valueOf(Servicelineitem));
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
    public WebElement btncomplete;

    public void ClickonPopComplete() {
        generics.clickOn(btncomplete);
        generics.pause(4);
        testStepsLog("Clicked on COMPLETE.");
    }


    @FindBy(xpath = "//div[contains(text(),'Ticket has been completed successfully !')]")
    public WebElement Completetticketmessage;

    public boolean isCpmplettticketmsg() {
    	 generics.pause(3);
    	 generics.waitForElementVisible(Completetticketmessage);
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

    //===================================for Front End

    @FindBy(xpath = "//mat-select[@formcontrolname='serviceZoneProductivityId']")
    public WebElement dpserviceZone;

    public void SelectServiceZone(int row)
    {
        generics.pause(2);
        String M=excelUtils.getTestData("ProspectALL", row, 12);
        generics.clickOn(dpserviceZone);
        WebElement element=localDriver.findElement(By.xpath("//span[@class='mat-option-text' and contains(text(),'"+M+"')]"));
        element.click();
        testStepsLog("Material selected : " + M);
    }

}
