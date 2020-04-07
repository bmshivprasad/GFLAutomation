package gflwishes.PageObjects;

import gflwishes.base.Generics;
import gflwishes.testcases.ServiceOrder;
import gflwishes.utilities.ExcelUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class ServiceOrderPage extends ServiceOrder {

    private WebDriver localDriver;
    private Generics generics;

    public ServiceOrderPage(WebDriver baseDriver) {
        this.localDriver = baseDriver;
        PageFactory.initElements(localDriver, this);
        generics = new Generics(localDriver);
        log4j = Logger.getLogger("Service Order");
    }


    @FindBy(xpath = "//button[contains(text(),'CUSTOMER')]/i")
    public WebElement btnAddCustomer;


    public boolean isCustomerPageOpen() {
        return generics.isPresent(btnAddCustomer);
    }

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


    @FindBy(xpath = "//form//span[text()='Add']")
    public WebElement btnAdd;

    public void clickonAddCustomerButton() {
        generics.pause(5);
        generics.clickOn(btnAddCustomer);
        testStepsLog("Clicked ON Add Customer button");
        generics.pause(2);
    }

    @FindBy(xpath = "//div[contains(text(),'Search Customer')]/following-sibling::div//input")
    public WebElement txtCustomerName;

    public boolean isPopupdisplayed() {
        return generics.isPresent(txtCustomerName);
    }


    public static String CustomerName;

    public void typeCustomername(int row) {
        CustomerName = excelUtils.getTestData(END_TO_END, row, 2);
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
        String St = excelUtils.getTestData(END_TO_END, row, 3);
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
        String Material = excelUtils.getTestData(END_TO_END, row, 4);
        JavascriptExecutor js = (JavascriptExecutor) localDriver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        generics.scrollToElement(dpMaterial);
        generics.clickOn(dpMaterial);
        WebElement element = localDriver.findElement(By.xpath("//span[contains(text(),'" + Material + "')]"));
        element.click();

        testStepsLog("Material selected :" + Material);
        generics.pause(2);
    }

    @FindBy(xpath = "//mat-select[@formcontrolname='containerSizeId']")
    public WebElement dpContainerSize;

    public void selectContainerSize(int row) {
        generics.clickOn(dpContainerSize);
        String ContainerSize = excelUtils.getTestData(END_TO_END, row, 5);
        WebElement element = localDriver.findElement(By.xpath("//span[contains(text(),'" + ContainerSize + "')]"));
        element.click();
        testStepsLog("Container size selected : " + ContainerSize);
    }

    @FindBy(xpath = "//mat-select[@formcontrolname='haulTypeId']")
    public WebElement dpHaulType;

    public void selectHaulType(int row) {
        generics.clickOn(dpHaulType);
        String HaulType = excelUtils.getTestData(END_TO_END, row, 6);
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

    @FindBy(xpath = "//input[@placeholder='Company Name']")
    public WebElement txtComplanyName;

    public boolean isEnteredCustomerDisplayed() {
        String cn = generics.getValue(txtComplanyName);
        if (cn.equals(CustomerName)) {
            return true;
        } else {
            return false;
        }

    }

    @FindBy(xpath = "//span[text()='Select  tax jurisdiction']")
    public WebElement dpJurisdiction;

    @FindBy(xpath = "//mat-option[2]")
    public WebElement firstOption;

    @FindBy(xpath = "(//ng-dropdown-panel//span)[1]")
    public WebElement firstAddress;

    @FindBy(xpath = "(//mat-select[@formcontrolname='businessUnitId'])[1]")
    public WebElement dpBusinessUnit1;

    public void selectBusinessUnit1() {

        generics.clickOn(dpBusinessUnit1);
        generics.pause(2);

        generics.clickOn(firstOption);
        testStepsLog("Site Business unit Selected");
        generics.pause(4);

    }

    @FindBy(xpath = "//mat-option[3]")
    public WebElement SecondOption;

    public void selectJurisdiction() {
        generics.pause(2);
        generics.clickOn(dpJurisdiction);

        generics.clickOn(SecondOption);
        testStepsLog("Jurisdiction selected");

    }

    @FindBy(xpath = "//mat-select[@aria-label='Select Customer Type']")
    public WebElement dpcustomertype;

    public void selectcustomertype() {
        generics.clickOn(dpcustomertype);
        generics.clickOn(firstOption);
        testStepsLog("Customer type selected");
    }

    @FindBy(xpath = "(//mat-select[@formcontrolname='countryId'])[1]")
    public WebElement dpCountry;

    public void selectcountry() {
        generics.moveTo(dpCountry);
        generics.clickOn(dpCountry);
        generics.clickOn(firstOption);
    }

    @FindBy(xpath = "(//ng-select[@placeholder='Address Line 1'])[1]")
    public WebElement dpAddressLine1;

    @FindBy(xpath = "(//ng-select[@placeholder='Address Line 1'])[1]//input")
    public WebElement txtAddressLine1;

    ExcelUtils excelUtils = new ExcelUtils();

    public void selectAddressline1() {
        String Add = "70 Eglin Blvd, Londonderry, NH, 03053, USA";
        generics.moveTo(SectionContractDetails);
        generics.clickOn(dpAddressLine1);
        generics.type(txtAddressLine1, Add);
        generics.pause(5);
        generics.clickOn(firstAddress);

        testStepsLog("Address line selected");
        generics.pause(4);
    }

    @FindBy(xpath = "//mat-checkbox[@formcontrolname='billingAsCompanyAddress']//div[contains(@class,'mat-checkbox-inner-container')]")
    public WebElement chkBillingAddAsCompanyAdd;

    public void selectBillingAddAsCompanyAdd() {
        generics.clickOn(chkBillingAddAsCompanyAdd);
        testStepsLog("Bill Address checkbox selected");
        generics.pause(2);
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

    public int getRowsExcel() {
        try {
            FileInputStream file = new FileInputStream(
                    System.getProperty("user.dir") + "/src/test//java//gfl//testData//ServiceOrder.xlsx");
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheet("Sheet1");
            return sheet.getPhysicalNumberOfRows();
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @FindBy(xpath = "//div[text()='CONTACT DETAILS']")
    public WebElement SectionContractDetails;

    @FindBy(xpath = "(//mat-label[text()='Contact Name']/ancestor::span/preceding-sibling::input)[1]")
    public WebElement txtContactName;

    public static String ContactName;

    public void typeContact() {
        ContactName = generics.getRandomCharacters(10);
        generics.moveTo(txtContactName);
        generics.type(txtContactName, ContactName);

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

    @FindBy(xpath = "(//input[@formcontrolname='primaryPhoneNumber'])[1]")
    public WebElement txtPhoneNumber;
    public static String PhoneNumber;

    public void typePhoneNumber() {
        PhoneNumber = "9898989898";
        generics.moveTo(chkSiteContactSameAsCompanyContact);
        generics.moveTo(txtPhoneNumber);
        generics.type(txtPhoneNumber, PhoneNumber);

        testStepsLog("PhoneNumber inserted");
    }


    @FindBy(xpath = "(//input[@formcontrolname='position'])[1]")
    public WebElement txtContactPosition;

    public static String ContactPosition;

    public void typeContactPosition() {
        ContactPosition = generics.getRandomCharacters(10);

        generics.moveTo(txtContactPosition);
        generics.type(txtContactPosition, ContactPosition);

        testStepsLog("ContactPosition inserted");
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
        generics.moveTo(chkSiteContactSameAsCompanyContact);
        generics.moveTo(txtSiteName);
        generics.type(txtSiteName, String.valueOf(SiteName));
        try {
            SetTestData(SiteName, row, 2);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        testStepsLog("SiteName inserted");
    }

    @FindBy(xpath = "(//mat-select[@formcontrolname='businessUnitId'])[2]")
    public WebElement dpBusinessUnit;

    public void selectBusinessUnit() {

        generics.clickOn(dpBusinessUnit);

        generics.clickOn(firstOption);
        testStepsLog("Site Business unit Selected");
        generics.pause(2);
    }


    @FindBy(xpath = "//mat-select[@formcontrolname='businessTypeErpCode']")
    public WebElement dpBusinessType;

    public void selectBusinessType() {

        generics.pause(4);
        generics.clickOn(dpBusinessType);

        generics.clickOn(firstOption);
        testStepsLog("Site Business Type Selected");
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

    @FindBy(xpath = "(//ng-select[@placeholder='Address Line 1'])[3]")
    public WebElement dpAddressLine1OfSite;

    @FindBy(xpath = "(//ng-select[@placeholder='Address Line 1'])[3]//input")
    public WebElement txtAddressLine1OfSite;

    @FindBy(xpath = "//span[contains(text(),'Site Contacts Same As Company Contacts ')]")
    public WebElement chkSiteContactSameAsCompanyContact;

    public void selectAddressline1ofSite(int row) {
        String Add = excelUtils.getTestData(END_TO_END, row, 0);
        testStepsLog("Address : " + Add);
        generics.moveTo(txtPostalcode);
        generics.clickOn(dpAddressLine1OfSite);
        generics.type(txtAddressLine1OfSite, Add);
        generics.pause(5);
        generics.clickOn(firstAddress);

        testStepsLog("Address line selected for site");
        JavascriptExecutor js = (JavascriptExecutor) localDriver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        generics.pause(4);
    }

    @FindBy(xpath = "(//input[@formcontrolname='postalCode'])[3]")
    public WebElement txtPostalcode;

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
    public WebElement btnSave;

    public void clickonbtnSaveCustomer() {
        generics.clickOn(btnSave);
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

    @FindBy(xpath = "//mat-select[@formcontrolname='containerTypeId']")
    public WebElement dpContainerType;

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

    public void typeDispatchnote() {
        note = generics.getRandomCharacters(10);
        JavascriptExecutor js = (JavascriptExecutor) localDriver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        generics.clickOn(txtDispatchnote);
        generics.type(txtDispatchnote, note);

        testStepsLog("Dispatch note inserted " + note);
    }

    @FindBy(xpath = "//div[contains(text(),'Driver')]")
    public WebElement tabdriver;

    @FindBy(xpath = "//textarea[@formcontrolname='driverNotes']")
    public WebElement txtDrivernote;

    public static String Drivernote;

    public void typeDrivernote() {
        Drivernote = generics.getRandomCharacters(10);
        generics.clickOn(tabdriver);
        generics.clickOn(txtDrivernote);
        generics.type(txtDrivernote, Drivernote);

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

    @FindBy(xpath = "//button[contains(text(),' APPROVE')]")
    public WebElement btnApproved;

    public void clickonApprovebutton() {
        try {
            generics.scrollToElement(btnApproved);
            generics.clickOn(btnApproved);
            testStepsLog("Clicked on Approved button");
        } catch (Exception ignored) {

        }
    }

    @FindBy(xpath = "//textarea[@placeholder='Enter Reason For Approval']")
    public WebElement txtApproval;

    public void typeApprovalReason() {
        generics.clickOn(txtApproval);
        generics.type(txtApproval, generics.getRandomCharacters(10));
        testStepsLog("Reason inserted : ");
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

    @FindBy(xpath = "//mat-checkbox[@formcontrolname='billToCustomerBillingAddress']")
    public WebElement chkbillToCustomerBillingAddress;

    public void SelectbillToCustomerBillingAddress() {
        generics.clickOn(chkbillToCustomerBillingAddress);
        testStepsLog("Select Bill to Customer Billing Address checkbox");
        generics.pause(2);
    }


    //--------------------------------------------

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
        CustomerName = excelUtils.getTestData(END_TO_END, row, 2);
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
        String st = excelUtils.getTestData(END_TO_END, row, 7);
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
        String vc = excelUtils.getTestData(END_TO_END, row, 8);
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
        String ds = excelUtils.getTestData(END_TO_END, row, 9);
        if (Dispatcher.toLowerCase().equals(ds.toLowerCase())) {
            return true;
        } else {
            return false;
        }
    }

    @FindBy(xpath = "//p[text()='Driver']/following-sibling::p")
    public WebElement txtDriver;

    public boolean isProperDriverDisplayed(int row) {

        String Driver = generics.getText(txtDriver);
        String dv = excelUtils.getTestData(END_TO_END, row, 11);
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
        String dn = excelUtils.getTestData(END_TO_END, row, 10);
        if (Dispatchernote.equals(dn)) {
            return true;
        } else {
            return false;
        }
    }

    @FindBy(xpath = "//p[text()='Driver Notes']/following-sibling::div/p")
    public WebElement txtDriverNote;

    public boolean isProperDriverNoteDisplayed(int row) {

        String Drivernote = generics.getText(txtDriverNote);
        String dn = excelUtils.getTestData(END_TO_END, row, 12);
        if (Drivernote.equals(dn)) {
            return true;
        } else {
            return false;
        }
    }

    //===============================================================================

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
    WebElement firstempyTruck;

    @FindBy(xpath = "(//map-common-vehicle-list//mat-list-item//span[text()='This vehicle has no assignments'])[1]/ancestor::map-common-vehicle-item//span[@class='vehicle_id_font _ellipsis']")
    WebElement EmptyVehicalname;


    public void dragPickupOrderToTruck() {
        Actions action = new Actions(localDriver);
        action.dragAndDrop(firstPickup, firstempyTruck).build().perform();
        testStepsLog("Dragged order to truck");

    }

    public static String VehicalName;

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

    @FindBy(xpath = "//div[text()='PROCEED']")
    WebElement btnProceed;

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

    public void openDispatcher() {
        localDriver.navigate().to("https://fleetmapper-qa.azurewebsites.net//dispatch");
        generics.pause(15);
    }

    @FindBy(xpath = "//button//mat-icon[text()='play_circle_outline']")
    WebElement btnStart;

    public void startOrder() {
        testStepsLog("Click on Start button to start order.");
        generics.clickOnJS(btnStart);
        testStepsLog("Order started and change status to in progress");
    }


    public boolean searching(String keywords, int column) {
        String xpath = "//table[contains(@class,'MuiTable-root')]/tr";
        localDriver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        int rowcount = findcount(xpath);
        boolean flag = true;
        for (int i = 1; i < rowcount + 1; i++) {
            String value = "";

            String xpathnew;
            if (column == 1)
                xpathnew = xpath + "[" + i + "]/td[" + column + "]//div";
            else
                xpathnew = xpath + "[" + i + "]/td[" + column + "]/div";

            value = localDriver.findElement(By.xpath(xpathnew)).getText();
            testStepsLog("The Keyword to search is  : " + keywords);
            testStepsLog("The value of row " + i + " and column " + column + " is " + value);
            if (value.contains(keywords)) {
                continue;
            } else {

                flag = false;
            }
        }
        return flag;
    }

    public int findcount(String xpath) {
        localDriver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        int count = localDriver.findElements(By.xpath(xpath)).size();
        return count;
    }


    public static int generateRandomDigits(int n) {
        int m = (int) Math.pow(10, n - 1);
        return m + new Random().nextInt(9 * m);
    }


    public String getTestData(int req_row, int col) throws IOException {

        FileInputStream file = new FileInputStream(
                System.getProperty("user.dir") + "/src/test//java//gfl//testData//PORequisition.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheet("Sheet1"); // workbook.getSheetAt(0)

        // logger.info("getting from xl row: " + req_row); ;
        XSSFRow row = sheet.getRow(req_row); // Get the row in which data is stored

        // logger.info("getting from xl cell 1");
        try {
            String req_data = row.getCell(col).getStringCellValue(); // Value is always in Cell 1. Cell 0 is description
            // of value
            workbook.close();
            return req_data;
        } catch (Exception e) {
            DataFormatter dataformat = new DataFormatter();
            String req_data = dataformat.formatCellValue(sheet.getRow(req_row).getCell(col)); // Value is always in Cell
            // 1. Cell 0 is
            // description of value
            workbook.close();
            return req_data;
        }

        // XSSFCell cell = sheet.getRow(1).getCell(1);
        // String req_data=cell.getStringCellValue();
        // logger.info("sending back: " + req_data);

    }

    public static void SetTestData(String dta, int rowt, int colt) throws IOException {

        FileInputStream file = new FileInputStream(
                System.getProperty("user.dir") + "/src/test//java//gfl//testData//ServiceOrder.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheet("Sheet1"); // workbook.getSheetAt(0)

        // logger.info("getting from xl row: " + req_row);
        XSSFRow row = sheet.getRow(rowt); // Get the row in which data is stored
        XSSFCell cell = row.createCell(colt);
        cell.setCellValue(dta);
        FileOutputStream fo = new FileOutputStream(
                System.getProperty("user.dir") + "/src/test//java//gfl//testData//ServiceOrder.xlsx");
        workbook.write(fo);
        workbook.close();
        file.close();
        fo.close();

    }

    public boolean isApproveDisplay() {
        return generics.isPresent(btnApproved);
    }
}