package gflwishes.PageObjects;

import gflwishes.base.Generics;
import gflwishes.testcases.vehicle;
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


public class vehiclePage extends vehicle {

    private WebDriver localDriver;
    private Generics generics;

    public vehiclePage(WebDriver baseDriver) {
        this.localDriver = baseDriver;
        PageFactory.initElements(localDriver, this);
        generics = new Generics(localDriver);
        log4j = Logger.getLogger("Service Order");
    }

    @FindBy(xpath = "//span[@class='date_string']")
    WebElement lblToday;

    @FindBy(xpath = "//mat-icon[text()='filter_list ']")
    WebElement btnFilter;
    public boolean verifyDispatchPage() {
        return generics.isDisplay(lblToday) && generics.isDisplay(btnFilter);
    }


    public static String VehicalName;



    public void openVehicle() {
        localDriver.navigate().to("https://fleetmapper-qa.azurewebsites.net/admin/view-region/1/businessunits");
        generics.pause(15);
    }

    @FindBy(xpath = "//a[contains(text(),'VEHICLES')]")
    public WebElement tabVehicle;

    @FindBy(xpath = "//span[@dataname='ADD VEHICLE']")
    public WebElement btnAddVehicle;

    public void ClickonVehicleTab()
    {
        generics.clickOn(tabVehicle);
        testStepsLog("Clicked on Vehicle tab");
    }
    public void clickonAddVehiclebutton()
    {
        generics.clickOn(btnAddVehicle);
        testStepsLog("Clicked on add vehicle button");
    }

    @FindBy(xpath = "//input[@formcontrolname='vin']")
    public WebElement txtVin;

    public void typeVin()
    {
        String Vin= generics.getRandomCharacters(5);
        generics.type(txtVin, Vin);
        testStepsLog("Vin value inserted : " + Vin);
    }
    @FindBy(xpath = "//mat-select[@formcontrolname='serviceZoneCreateId']")
    public WebElement dpServiceZone;

    @FindBy(xpath = "//mat-option[2]")
    public WebElement firstOption;

    public void selectServiceZone()
    {
        generics.clickOn(dpServiceZone);
        generics.clickOn(firstOption);
        testStepsLog("Service Zone Selected");
    }

    @FindBy(xpath = "//mat-select[@formcontrolname='regionTruxVehicleTypeId']")
    public WebElement dpVehicleType;

    public void selectVehicleType()
    {
        generics.clickOn(dpVehicleType);
        generics.clickOn(firstOption);
        testStepsLog("Vehicle Type Selected");
    }

    @FindBy(xpath = "//div[text()='SAVE VEHICLE']")
    public WebElement btnSaveVehicle;

    public void clickonSaveButton()
    {
        generics.clickOn(btnSaveVehicle);
        testStepsLog("Clicked on Save Vehicle Button");
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


}