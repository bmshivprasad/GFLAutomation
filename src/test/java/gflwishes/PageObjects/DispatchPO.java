package gflwishes.PageObjects;

import gflwishes.base.Generics;
import gflwishes.testcases.Dispatch;
import gflwishes.utilities.ExcelUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DispatchPO extends Dispatch {

    private WebDriver driver;
    private Generics generics;

    private static String lblPickUpOrderName;

    public DispatchPO(WebDriver baseDriver) {
        this.driver = baseDriver;
        PageFactory.initElements(driver, this);
        generics = new Generics(driver);
        log4j = Logger.getLogger("DispatchPO");
    }

    @FindBy(xpath = "//span[@class='date_string']")
    WebElement lblToday;

    @FindBy(xpath = "//mat-icon[text()='filter_list ']")
    WebElement btnFilter;

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
    WebElement btnComplete;

    @FindBy(xpath = "//div[@class='mat-form-field-infix']")
    WebElement btnDriver;

    @FindAll(value = {@FindBy(xpath = "//mat-option")})
    List<WebElement> lstOption;

    public void openDispatcher() {
        testStepsLog("Open Dispatcher");
        driver.navigate().to(FM_URL + File.separator + "dispatch");
    }

    public boolean verifyDispatchPage() {
        return generics.isDisplay(lblToday) && generics.isDisplay(btnFilter);
    }

    public void addTruckFromMap(int count) {
        generics.pause(4);
        Actions act = new Actions(driver);
        generics.scrollToElement(truckDragAndDrop);
        testStepsLog("Drag and Drop Truck from the map to order.");
        excelUtils.setTestData(END_TO_END, count, 18, firstempyTruck.getText());
        act.dragAndDrop(driver.findElement(By.xpath("//map-common-vehicle-item//span[contains(text(),'" +
                excelUtils.getTestData("EndToEnd", count, 8) + "')]")), truckDragAndDrop).build().perform();
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
        generics.scrollToElement(driver.findElements(By.xpath("//map-common-list-header[contains(text(),'PICKUP')]" +
                "//ancestor::map-common-order-list//p[text()=' " + lblPickUpOrderName + " ']")).get(1));
        generics.clickOn(driver.findElements(By.xpath("//map-common-list-header[contains(text(),'PICKUP')]/" +
                "/ancestor::map-common-order-list//p[text()=' " + lblPickUpOrderName + " ']")).get(1));
        generics.pause(10);
    }

    public void selectDeliver() {
        System.out.println("Click on oder to open.");
        generics.scrollToElement(driver.findElements(By.xpath("//map-common-list-header[contains(text(),'DELIVER')]" +
                "//ancestor::map-common-order-list//p[text()=' " + lblPickUpOrderName + " ']")).get(1));
        generics.clickOn(driver.findElements(By.xpath("//map-common-list-header[contains(text(),'DELIVER')]/" +
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
        new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(btnComplete));
        generics.scrollToElement(btnComplete);
        generics.clickOnJS(btnComplete);
        generics.pause(2);
        ((JavascriptExecutor) driver).executeScript("document.evaluate('//div[@class=\"mat-form-field-infix\"]'," +
                "   document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue.click();");
        excelUtils.setTestData(END_TO_END, count, 11, lstDrivers.get(0).getText());
        excelUtils.setTestData(END_TO_END, count, 7, "COMPLETED");
        generics.clickOnJS(lstDrivers.get(0));
        generics.clickOnJS(btnProceed);
        generics.pause(5);
    }

    @FindBy(xpath = "//i[text()='date_range']")
    WebElement btnDatePicker;

    @FindBy(xpath = "//dispatch-order-aside-header//mat-icon[text()='search']")
    WebElement btnSearchOrder;

    @FindBy(xpath = "//button[@id='orderSearchButton']//following-sibling::input")
    WebElement txtSearch;

    public static String SiteAddress;
    ExcelUtils excelUtils = new ExcelUtils();

    public void searchAddress(int count) {
        SiteAddress = excelUtils.getTestData(END_TO_END, count, 0);
        new WebDriverWait(driver, 30).until(ExpectedConditions.invisibilityOfElementLocated(
                By.xpath("//div[text()='Loading...']")));
        generics.clickOnJS(btnSearchOrder);
        generics.type(txtSearch, SiteAddress.split(" ")[0]);
    }

    public void selectOrder() {
        generics.clickOn(driver.findElement(By.xpath("(//div[@class='card']//p[contains(text()," +
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
        SiteCustomer = excelUtils.getTestData(END_TO_END, count, 2);
        System.out.println(lblOrderType.getText());
        System.out.println(driver.findElement(By.xpath("//span[contains(text(),'" +
                SiteAddress.split(",")[0] + "')]")).getText().
                replace("\n", " ").replaceAll("\\s", ""));
        System.out.println(SiteAddress.replaceAll("\\s", ""));
        return lblOrderType.getText().equalsIgnoreCase("UNASSIGNED") &&
                driver.findElement(By.xpath("//span[contains(text(),'" +
                        SiteAddress.split(",")[0] + "')]")).
                        getText().replace("\n", "").trim().replaceAll("\\s", "").
                        equalsIgnoreCase(SiteAddress.replaceAll("\\s", ""))
                && lblSiteContact.getText().equalsIgnoreCase(excelUtils.getTestData(END_TO_END,
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

    public void enterTicketDetails() {
        generics.pause(2);
        generics.clickOnJS(btnScaleTicket);
        generics.type(txtScaleTicket, generics.getRandomBetween(100, 999) + "");
        generics.type(txtWeight, generics.getRandomBetween(100, 999) + "");
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
}
