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

        String BU = excelUtils.getTestData(END_TO_END, row, 14);
        generics.pause(5);

        generics.clickOn(dpBusinessUnit1);
        generics.pause(5);
        WebElement element = localDriver.findElement(By.xpath("//mat-option/span[contains(text(),'" + BU + "')]"));
        element.click();
        testStepsLog("Site Business unit Selected : " + BU);
        generics.pause(5);

    }
  
    @FindBy(xpath = "//span[text()='Select Tax Jurisdiction']")
    public WebElement dpJurisdiction;
    
    @FindBy(xpath = "//mat-option[3]")
    public WebElement SecondOption;
    
    public void selectJurisdiction() {
        generics.clickOn(dpJurisdiction);
        generics.clickOn(SecondOption);
        testStepsLog("Jurisdiction selected");

    }
    
    @FindBy(xpath = "//mat-select[@aria-label='Select Customer Type']")
    public WebElement dpcustomertype;

    @FindBy(xpath = "//mat-option[2]")
    public WebElement firstOption;
    
    public void selectcustomertype() {
        generics.clickOn(dpcustomertype);
        generics.clickOn(firstOption);
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

}
