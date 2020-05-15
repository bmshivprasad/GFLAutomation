package gflwishes.PageObjects;

import gflwishes.base.Generics;
import gflwishes.testcases.vehicle;
import gflwishes.utilities.ExcelColumns;
import gflwishes.utilities.ExcelUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class vehiclePage extends vehicle implements ExcelColumns {

    private WebDriver localDriver;
    private Generics generics;

    public vehiclePage(WebDriver baseDriver) {
        this.localDriver = baseDriver;
        PageFactory.initElements(localDriver, this);
        generics = new Generics(localDriver);
        log4j = Logger.getLogger("Vehicle");
    }

    @FindBy(xpath = "(//div[text()='REGIONS']/following-sibling::div//div[@class='card_title'])")
    WebElement regions;

    public void OpenRegion() {
        generics.clickOn(regions);
        testStepsLog("Open selected regions");
        generics.pause(3);
    }
    @FindBy(xpath = "//button[@routerlink='batches']//mat-icon")
    WebElement batcheicon;

    @FindBy(xpath = "//span[text()='Batches']")
    WebElement lnkbatches;

    public void clickonbatche()
    {
        generics.pause(2);
        generics.moveTo(batcheicon);
        generics.pause(2);
        generics.clickOn(lnkbatches);
        testStepsLog("Batches opend");
    }

    @FindBy(xpath = "//input[@type='email']")
    WebElement txtUsername;

    @FindBy(xpath = "//input[@type='password']")
    WebElement txtPassword;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement btnSubmit;
    public void loginAs(String username, String password) {
        generics.pause(5);
        generics.type(txtUsername, username);
        testStepsLog("Enter Username : " + username);
        generics.pause(3);
        generics.clickOn(btnSubmit);
        generics.pause(3);
        testStepsLog("Click on SignIn button");
        generics.type(txtPassword, password);
        testStepsLog("Enter Password");
        generics.clickOn(btnSubmit);
        testStepsLog("Click on SignIn button");
        generics.clickOn(btnSubmit);

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

    ExcelUtils excelUtils = new ExcelUtils();

    @FindBy(xpath = "//mat-select[@formcontrolname='businessUnitCreateIds']")
    public WebElement dpBusinessUnit;

    public void selectBusinessUnit() {

        generics.pause(5);
        String BU = excelUtils.getTestData(END_TO_END, 1, 14);
        generics.clickOn(dpBusinessUnit);
        generics.pause(2);
        WebElement element = localDriver.findElement(By.xpath("//mat-option/span[contains(text(),'" + BU + "')]"));
        element.click();
        testStepsLog("Site Business unit Selected : " + BU);
        generics.pause(2);

    }


    @FindBy(xpath = "//mat-select[@formcontrolname='serviceZoneCreateId']")
    public WebElement dpServiceZone;

    @FindBy(xpath = "//mat-option[2]")
    public WebElement firstOption;

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

    public void copyVehicleInEndToEndExcel() {
        System.out.println(ExcelUtils.getRowsExcel(END_TO_END));
        for (int i = 1; i < ExcelUtils.getRowsExcel(END_TO_END); i++)
            excelUtils.setTestData(END_TO_END, i, VEHICLE_NAME, VehicalName.toUpperCase());
        testStepsLog("Vehicle entery done in EndToEnd.xlsx file");
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