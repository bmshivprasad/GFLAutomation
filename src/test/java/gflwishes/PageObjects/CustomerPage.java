package gflwishes.PageObjects;

import gflwishes.base.Generics;
import gflwishes.testcases.Customer;
import gflwishes.testcases.Prospect;
import gflwishes.utilities.ExcelColumns;
import gflwishes.utilities.ExcelUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class CustomerPage extends Customer implements ExcelColumns {

    private WebDriver localDriver;
    private Generics generics;

    public CustomerPage(WebDriver baseDriver) {
        this.localDriver = baseDriver;
        PageFactory.initElements(localDriver, this);
        generics = new Generics(localDriver);
        log4j = Logger.getLogger("AdminContract");
    }


    @FindBy(xpath = "//button[contains(text(),'CUSTOMER')]/i")
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

    @FindBy(xpath = "//div[text()='Search Customer']/following-sibling::div//input")
    public WebElement txtCustomerName;

    public boolean isPopupdisplayed() {
        return generics.isPresent(txtCustomerName);
    }

    public static String CustomerName;

    public void typeCustomername(int row) {
        // CustomerName = generics.getRandomCharacters(10);
        CustomerName = generics.getRandomCharacters(5) + "_" + LocalDate.now().format(DateTimeFormatter.ofPattern("ddMM"))
                + "" + getRandomIntBetweenRange(100, 999);
        ;
        generics.clickOn(txtCustomerName);
        generics.type(txtCustomerName, CustomerName);
        try {
            SetTestData(CustomerName, row, CUSTOMER_NAME);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
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

    @FindBy(xpath = "//mat-option[3]")
    public WebElement SecondOption;

    @FindBy(xpath = "(//ng-dropdown-panel//span)[1]")
    public WebElement firstAddress;

    @FindBy(xpath = "(//mat-select[@formcontrolname='businessUnitId'])[1]")
    public WebElement dpBusinessUnit1;


    public void selectBusinessUnit1(int row) {

        String BU = excelUtils.getTestData(END_TO_END, row, BRANCH_BU);
        generics.clickOn(dpBusinessUnit1);
        generics.pause(2);
        WebElement element = localDriver.findElement(By.xpath("//mat-option/span[contains(text(),'" + BU + "')]"));
        element.click();
        testStepsLog("Site Business unit Selected : " + BU);
        generics.pause(2);

    }

    public void selectJurisdiction() {
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
        generics.pause(2);
    }

    @FindBy(xpath = "//mat-checkbox[@formcontrolname='billingAsCompanyAddress']//div[contains(@class,'mat-checkbox-inner-container')]")
    public WebElement chkBillingAddAsCompanyAdd;

    public void selectBillingAddAsCompanyAdd() {
        generics.clickOn(chkBillingAddAsCompanyAdd);
        testStepsLog("Bill Address checkbox selected");
        generics.pause(1);
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

    @FindBy(xpath = "(//input[@formcontrolname='postalCode'])[3]")
    public WebElement txtPostalcode;

    public void typePostalcode(int row) {
        String pc = excelUtils.getTestData(END_TO_END, row, POSTAL_CODE);
        generics.type(txtPostalcode, Keys.CONTROL + "a" + Keys.DELETE);
        generics.clickOn(txtPostalcode);
        generics.type(txtPostalcode, pc);
        testStepsLog("Postal code inserted +" + pc);
        generics.pause(2);
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
    	/*try {
			SetTestData(ContactPosition, 7, 2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
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
            SetTestData(SiteName, row, SITE_NAME);
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
    	/*try {
			SetTestData(generics.getText(firstOption), 10, 2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
        generics.clickOn(firstOption);

        testStepsLog("Site Business unit Selected");
        generics.pause(2);
    }

    @FindBy(xpath = "//mat-select[@formcontrolname='businessTypeErpCode']")
    public WebElement dpBusinessType;

    public void selectBusinessType() {

        generics.pause(4);
        generics.clickOn(dpBusinessType);
    	/*try {
			SetTestData(generics.getText(firstOption), 11, 2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
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
    	/*try {
			SetTestData(String.valueOf(PoNumber), 12, 2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
        testStepsLog("PoNumber inserted");
        JavascriptExecutor js = (JavascriptExecutor) localDriver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    @FindBy(xpath = "//mat-checkbox[@formcontrolname='siteAddressesSameAsCompanyAddresses']")
    public WebElement chksiteAddressesSameAsCompanyAddresses;

    public void DeSelectsiteAddressesSameAsCompanyAddresses() {
        generics.clickOn(chksiteAddressesSameAsCompanyAddresses);
        testStepsLog("Site Address Same As Company Address checkbox unSelected");
    }


    @FindBy(xpath = "//span[contains(text(),'Site Contacts Same As Company Contacts ')]")
    public WebElement chkSiteContactSameAsCompanyContact;

    @FindBy(xpath = "(//ng-select[@placeholder='Address Line 1'])[3]")
    public WebElement dpAddressLine1OfSite;

    @FindBy(xpath = "(//ng-select[@placeholder='Address Line 1'])[3]//input")
    public WebElement txtAddressLine1OfSite;


    public void selectAddressline1ofSite(int row) {
        String Add = excelUtils.getTestData(END_TO_END, row, SITE_ADDRESS);
        testStepsLog("Address : " + Add);
        generics.moveTo(txtPostalcode);
        generics.clickOn(dpAddressLine1OfSite);
        generics.type(txtAddressLine1OfSite, Add);
        generics.pause(5);
        generics.clickOn(firstAddress);

        testStepsLog("Address line selected for site");
        JavascriptExecutor js = (JavascriptExecutor) localDriver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
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
        List<WebElement> contact = localDriver.findElements(By.xpath("//td[contains(text(),'" + ContactName + "')]"));
        List<WebElement> email = localDriver.findElements(By.xpath("//td[contains(text(),'" + Email + "')]"));
        List<WebElement> position = localDriver.findElements(By.xpath("//td[contains(text(),'" + ContactPosition + "')]"));
        List<WebElement> phone = localDriver.findElements(By.xpath("//td[contains(text(),'" + PhoneNumber + "')]"));
        if (contact.size() > 0 && email.size() > 0 && position.size() > 0 && phone.size() > 0) {
            return true;
        } else {
            return false;
        }
    }


    @FindBy(xpath = "//table[contains(@class,'table_customers')]//tbody//tr[1]//td[1]//a")
    public WebElement firstCustomer;


    public void openfirstCustomer() {
        CustomerName = generics.getText(firstCustomer);
        generics.clickOn(firstCustomer);
        testStepsLog("First Customer open for Editing : " + CustomerName);
    }

    @FindBy(xpath = "//button[contains(text(),'EDIT CUSTOMER ')]")
    public WebElement btnEditCustomer;

    public void clickonEditCustomer() {
        generics.clickOn(btnEditCustomer);
        testStepsLog("Clicked on Edit Customer Button");
    }

    public void zoomout() {
        JavascriptExecutor executor = (JavascriptExecutor) localDriver;
        executor.executeScript("document.body.style.zoom = '0.8'");

        testStepsLog("Zoom out");
    }


    public void EditContactPosition() {
        ContactPosition = generics.getRandomCharacters(10);
        generics.type(txtContactPosition, Keys.CONTROL + "a" + Keys.DELETE);
        generics.type(txtContactPosition, ContactPosition);

        testStepsLog("New Value updated : " + ContactPosition);
    }

    @FindBy(xpath = "//button[contains(text(),'Save Changes')]")
    public WebElement btnSaveChanges;

    public void clickonSaveChanges() {
        generics.clickOn(btnSaveChanges);
        testStepsLog("Clicked on Save Changes Button");
    }

    public boolean ischangesSaved() {
        List<WebElement> element = localDriver.findElements(By.xpath("//td[contains(text(),'" + ContactPosition + "')]"));
        if (element.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    @FindBy(xpath = "//span[text()='UCN']/parent::p")
    public WebElement customerID;

    public static String cd;

    public void getCustomerID(int row) {

        cd = generics.getText(customerID);
        try {
            SetTestData(cd, row, CUSTOMER_ID);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        testStepsLog("Customer Created ID : " + cd);
        generics.pause(15);
    }



//----------------------------------------------------------------

    @FindBy(xpath = "//button[contains(text(),'CREATE QUOTE')]")
    public WebElement btnCreateQuote;

    public void clickonCreateQuote()
    {
        generics.clickOn(btnCreateQuote);
        testStepsLog("Clicked on Create Quote button");
    }

    @FindBy(xpath = "//button[contains(text(),'NEXT')]")
    public WebElement btnNext;

    public void clickonNextButton() {

        JavascriptExecutor js = (JavascriptExecutor) localDriver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        generics.clickOn(btnNext);
        testStepsLog("Clicked On Next Button");
        generics.pause(3);
    }
    @FindBy(xpath = "//button[contains(text(),'ADD SERVICE')]")
    public WebElement btnAddServices;
    public void clickonAddServiceButton() {

        JavascriptExecutor js = (JavascriptExecutor) localDriver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        generics.clickOn(btnAddServices);
        testStepsLog("Clicked On Add Service Button");
        generics.pause(3);
    }

    @FindBy(xpath = "//mat-select[@formcontrolname='serviceTypeId']")
    public WebElement dpServiceType;

    @FindBy(xpath = "//mat-option//span[text()=' Roll Off ']")
    public WebElement optionRollOff;


    public void selectServiceType()
    {
        generics.clickOn(dpServiceType);
        generics.clickOn(optionRollOff);
        testStepsLog("Service type selected");
    }

    @FindBy(xpath = "//input[@formcontrolname='containerCount']")
    public WebElement txtContainerCount;

    @FindBy(xpath = "//div[text()='DISPOSAL']")
    public WebElement titleDisposal;

    public void typeContainerCount(int row)
    {
        generics.moveTo(titleDisposal);
        String CC=excelUtils.getTestData("Prospect", row, 5);
        generics.type(txtContainerCount,CC);
        testStepsLog("Container Count : " + CC);
    }

    @FindBy(xpath = "//mat-select[@formcontrolname='containerTypeId']")
    public WebElement dpContainerType;

    public void SelectContainerType(int row)
    {
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
        String CF=excelUtils.getTestData("Prospect", row, 7);
        generics.type(txtContainerFee,CF);
        testStepsLog("Container Fee : " + CF);
    }

    @FindBy(xpath = "//mat-select[@formcontrolname='containerSizeId']")
    public WebElement dpContainerSize;

    public void SelectContainerSize(int row)
    {
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
        generics.moveTo(btnCalculate);
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
        generics.clickOn(dpdiposibleSite);
        generics.clickOn(firstOption);

        testStepsLog("Disposible site selected : ");
    }

    @FindBy(xpath = "//button[contains(text(),'Calculate')]")
    public WebElement btnCalculate;

    public void clickonCalculate()
    {
        generics.clickOn(btnCalculate);
        testStepsLog("Clicked ON calculate button");
        generics.pause(6);
    }




    @FindBy(xpath = "//input[@formcontrolname='timeAtDisposalSite']")
    public WebElement txtEstTime;

    public void typeEstTime(int row)
    {
        String ET=excelUtils.getTestData("Prospect", row, 13);
        generics.type(txtEstTime,ET);
        testStepsLog("Estimated time entered in min : " + ET);
    }





    @FindBy(xpath = "//textarea[@formcontrolname='notes']")
    public WebElement txtnote;

    public void typenote()
    {
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
        String LT=excelUtils.getTestData("Prospect", row, 14);
        generics.type(txtLocationType,LT);
        testStepsLog("Location Type : " + LT);
    }

    @FindBy(xpath = "//button[contains(text(),'ADD SERVICE')]")
    public WebElement AddService;

    public void clickonAddService()
    {
        generics.clickOn(AddService);
        testStepsLog("Clicked on Add Service button");
    }

    @FindBy(xpath = "//button[contains(text(),'Update Agreement')]")
    public WebElement UpdateAgreement;

    public void clickonUpdateAgreement()
    {
        generics.clickOn(UpdateAgreement);
        testStepsLog("Clicked on UpdateAgreement button");
    }

    @FindBy(xpath = "//button[contains(text(),'SAVE & SUBMIT CSA')]")
    public WebElement btnSaveAndSubmitCSA;

    public void clickonSaveAndSubmitCSA()
    {
        generics.clickOn(btnSaveAndSubmitCSA);
        testStepsLog("Clicked on Save And Submit CSA button");
    }

    @FindBy(xpath = "//button[contains(text(),'Submit')]")
    public WebElement btnSubmitted;

    public void clickonSubmitButton()
    {
        generics.clickOn(btnSubmitted);
        testStepsLog("Clicked on SubmitButton CSA button");
        generics.pause(5);
    }


    @FindBy(xpath = "//div[contains(text(),'Agreement has been updated successfully!')]")
    public WebElement AggreementUpdatedSuccessfully;

    public boolean isAggreementUpdated()
    {
        generics.pause(5);
        return generics.isPresent(AggreementUpdatedSuccessfully);
    }

    @FindBy(xpath = "//div[contains(text(),'CSA has been submitted successfully!\n')]")
    public WebElement CSASuccess;

    public boolean isCSASaved()
    {
        generics.pause(5);
        return generics.isPresent(CSASuccess);
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


    public int getRowsExcel() {
        FileInputStream file;
        try {
            file = new FileInputStream(
                    System.getProperty("user.dir") + "/src/test//java//gfl//testData//EndToEnd.xlsx");
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheet("Sheet1");
            return sheet.getPhysicalNumberOfRows();
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public String getTestData(int req_row, int col) throws IOException {

        FileInputStream file = new FileInputStream(
                System.getProperty("user.dir") + "/src/test//java//gfl//testData//Customer.xlsx");
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
                System.getProperty("user.dir") + "/src/test//java//gfl//testData//EndToEnd.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheet("Sheet1"); // workbook.getSheetAt(0)


        XSSFRow row = sheet.getRow(rowt); // Get the row in which data is stored
        XSSFCell cell = row.createCell(colt);
        cell.setCellValue(dta);
        FileOutputStream fo = new FileOutputStream(
                System.getProperty("user.dir") + "/src/test//java//gfl//testData//EndToEnd.xlsx");
        workbook.write(fo);
        workbook.close();
        file.close();
        fo.close();

    }

}