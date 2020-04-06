package gflwishes.PageObjects;

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
    	ProspectName = generics.getRandomCharacters(5) + "_" + LocalDate.now().format(DateTimeFormatter.ofPattern("ddMM"))
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
    	generics.pause(5);
        String BU = excelUtils.getTestData(END_TO_END, row, 14);
        generics.pause(5);

        generics.clickOn(dpBusinessUnit1);
        generics.pause(5);
        WebElement element = localDriver.findElement(By.xpath("//mat-option/span[contains(text(),'" + BU + "')]"));
        element.click();
        testStepsLog("Site Business unit Selected : " + BU);
        generics.pause(5);

    }
    
    @FindBy(xpath ="//input[@formcontrolname=\"webSite\"]")
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
        generics.pause(3);
        generics.clickOn(ThirdOption);
        testStepsLog("BillingCurrency selected");
        generics.pause(5);
    }
    
    @FindBy(xpath = "//span[contains(text(),'CapexTest Approver')]")
    public WebElement dpSalesRep;
    
    @FindBy(xpath = "//mat-option[3]")
    public WebElement ThirdOption1;
    
    public void selectSalesRep() {
        generics.clickOn(dpSalesRep);
        generics.pause(3);
        generics.clickOn(ThirdOption1);
        testStepsLog("BillingCurrency selected");
        generics.pause(5);
    }
  
    @FindBy(xpath = "//mat-select[@id='mat-select-12']")
    public WebElement dpJurisdiction;
    
    @FindBy(xpath = "//mat-option[3]")
    public WebElement SecondOption;
    
    public void selectJurisdiction() {
        generics.clickOn(dpJurisdiction);
        generics.pause(3);
        generics.clickOn(SecondOption);
        testStepsLog("Jurisdiction selected");
        generics.pause(5);
    }
    
    @FindBy(xpath = "//mat-select[@aria-label='Select Customer Type']")
    public WebElement dpcustomertype;

    @FindBy(xpath = "//mat-option[2]")
    public WebElement firstOption;
    
    public void selectcustomertype() {
        generics.clickOn(dpcustomertype);
        generics.pause(5);
        generics.clickOn(firstOption);
        generics.pause(5);
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
        generics.pause(5);
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
    }
    
    
    @FindBy(xpath = "//input[@placeholder='Enter Site Name']")
    public WebElement txtSiteName;

    public static String SiteName;

    public void typeSiteName(int row) {
        SiteName = generics.getRandomCharacters(10);
        
        JavascriptExecutor je = (JavascriptExecutor) localDriver;
        je.executeScript("arguments[0].scrollIntoView(true);",txtSiteName);
        
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
    
    @FindBy(xpath = "(//ng-select[@placeholder='Address Line 1'])[3]")
    public WebElement dpAddressLine1OfSite;

    @FindBy(xpath = "(//ng-select[@placeholder='Address Line 1'])[3]//input")
    public WebElement txtAddressLine1OfSite;

    @FindBy(xpath = "(//input[@formcontrolname='postalCode'])[3]")
    public WebElement txtPostalcode;

    public void selectAddressline1ofSite(int row) {
        String Add = excelUtils.getTestData(END_TO_END, row, 0);
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
        String pc = excelUtils.getTestData(END_TO_END, row, 1);
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

    public void SelectSitecontactsameasprimarycontact() {
        generics.scrollToElement(chkbSitecontactsameasprimarycontact);
        generics.clickOn(chkbSitecontactsameasprimarycontact);
        testStepsLog("Select Site contact same as primary contact checkbox");
        generics.pause(2);
    }
    
    @FindBy(xpath = "//button[contains(text(),'SAVE AND SUBMIT')]")
    public WebElement btnSave;

    public void clickonbtnSaveCustomer() {
        generics.clickOn(btnSave);
        generics.pause(5);
        testStepsLog("Clicked on Save Customer button");
    }
}
