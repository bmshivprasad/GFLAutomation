package gflwishes.PageObjects;

import gflwishes.base.Generics;
import gflwishes.testcases.Dispatch;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
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

public class DispatchPO_2 extends Dispatch {

    private WebDriver driver;
    private Generics generics;

    private static String orderTitle = "";
    private static String vehicleName = "";
    private static String SiteAddress;
    private static String lblPickUpOrderName;

    public DispatchPO_2(WebDriver baseDriver) {
        this.driver = baseDriver;
        PageFactory.initElements(driver, this);
        generics = new Generics(driver);
        log4j = Logger.getLogger("DispatchPO");
    }

    @FindBy(xpath = "//span[@class='date_string']")
    WebElement lblToday;

    @FindBy(xpath = "//mat-icon[text()='filter_list ']")
    WebElement btnFilter;

    @FindAll(value = {@FindBy(xpath = "//dispatch-order-aside-header//following-sibling::map-common-group-order-list//map-common-order-item//div[text()!='ON HOLD']")})
    List<WebElement> lstAllOrders;

    @FindBy(xpath = "//dispatch-order-aside-header//following-sibling::map-common-group-order-list//div[@class='pastDate']")
    List<WebElement> lstPastOrders;

    @FindBy(xpath = "//dispatch-order-aside-header//following-sibling::map-common-group-order-list" +
            "//div[@class='pastDate']//map-common-date-time-view//div")
    List<WebElement> lstPastOrderTitle;

    @FindBy(xpath = "//h3[contains(@class,'error_text')]")
    WebElement lblError;

    @FindAll(value = {@FindBy(xpath = "//dispatch-order-aside-header//following-sibling::map-common-group-order-list//map-common-order-item/div[not(@class='pastDate')]//div[text()!='ON HOLD']")})
    List<WebElement> lstTodayOrders;

    @FindAll(value = {@FindBy(xpath = "//dispatch-order-aside-header//following-sibling::map-common-group-order-list//map-common-order-item//div[text()!='ON HOLD']")})
    List<WebElement> lstTodayAssignedOrders;

    @FindAll(value = {@FindBy(xpath = "//dispatch-order-aside-header//following-sibling::map-common-group-order-list//map-common-order-item//div[text()!='ON HOLD']//ancestor::map-common-order-item//p")})
    List<WebElement> lstTodayAssignedOrderTitle;

    @FindAll(value = {@FindBy(xpath = "//dispatch-order-aside-header//following-sibling::map-common-group-order-list//map-common-order-item//div[text()='ON HOLD']")})
    List<WebElement> lstTodayOnHoldOrders;

    @FindAll(value = {@FindBy(xpath = "//dispatch-order-aside-header//following-sibling::map-common-group-order-list//map-common-order-item//div[text()='ON HOLD']//ancestor::map-common-order-item//p")})
    List<WebElement> lstTodayOnHoldOrderTitle;

    @FindAll(value = {@FindBy(xpath = "//dispatch-order-aside-header//following-sibling::map-common-group-order-list" +
            "//map-common-order-item//div[text()!='ON HOLD']")})
    List<WebElement> lstTomorrowOrders;

    @FindAll(value = {@FindBy(xpath = "//dispatch-order-aside-header//following-sibling::map-common-group-order-list" +
            "//map-common-order-item//div[text()!='ON HOLD']//ancestor::map-common-order-item//p")})
    List<WebElement> lstTomorrowOrderTitle;

    @FindBy(xpath = "//div[contains(text(),'EXPECTED TIME ON SITE')]//following-sibling::div//map-common-date-time-view//div")
    WebElement lblOrderDate;

    @FindBy(xpath = "//span[contains(@class,'selected')]")
    WebElement btnCurrentDate;

    @FindBy(xpath = "//button//div[contains(text(),'PROCEED')]")
    WebElement btnProceed;

    @FindBy(xpath = "//span[@class='noAssignments_text']//ancestor::map-common-vehicle-item//span[contains(@class,'vehicle_id_font')]")
    WebElement lblEmptyTruck;

    @FindAll(value = {@FindBy(xpath = "//dispatch-vehicle-aside-header//following-sibling::map-common-vehicle-list" +
            "//div[@id='order-item']//p")})
    List<WebElement> lstAssignedOrders;

    @FindAll(value = {@FindBy(xpath = "//dispatch-order-aside-header//following-sibling::map-common-group-order-list" +
            "//map-common-order-item/div[not(@class='pastDate')]//div[text()!='ON HOLD']//ancestor::map-common-order-item//p")})
    List<WebElement> lstTodayOrderTitle;

    @FindBy(xpath = "(//span[contains(text(),'ORDERS')]//..//..//following-sibling::div//span)[2]")
    WebElement btnFilterOpen;

    @FindBy(xpath = "//button[@id='orderSearchButton']//following-sibling::input")
    WebElement txtSearch;

    @FindAll(value = {@FindBy(xpath = "//button[@role='menuitem']")})
    List<WebElement> lstMenuItem;

    @FindBy(xpath = "//i[text()='date_range']//following-sibling::span")
    WebElement btnDatePicker;

    @FindBy(xpath = "//dispatch-order-aside-header//mat-icon[text()='search']")
    WebElement btnSearchOrder;

    @FindBy(xpath = "//button//span[@dataname='PUT ON HOLD']")
    WebElement btnOnHold;

    @FindBy(xpath = "//button//mat-icon[text()='play_circle_outline']")
    WebElement btnStart;

    @FindAll(value = {@FindBy(xpath = "//map-common-list-header[contains(text(),'EMPTY AND RETURN') or contains(text()," +
            "'EXCHANGE')]//ancestor::map-common-order-list//mat-list-item//div[text()!='ON HOLD' and contains(text(),'-')]")})
    List<WebElement> lstEmptyReturnExchangeUnassignedOrdersToday;

    @FindAll(value = {@FindBy(xpath = "//map-common-list-header[contains(text(),'EMPTY AND RETURN') or contains(text()," +
            "'EXCHANGE')]//ancestor::map-common-order-list//mat-list-item//div[text()!='ON HOLD' and contains(text(),'-')]//ancestor::map-common-order-item//p")})
    List<WebElement> lstEmptyReturnExchangeUnassignedOrdersTodayTitle;

    @FindAll(value = {@FindBy(xpath = "//map-common-list-header[contains(text(),'EMPTY AND RETURN') or contains(text()," +
            "'EXCHANGE')]//ancestor::map-common-order-list//div[@class='pastDate']")})
    List<WebElement> lstEmptyReturnExchangeUnassignedOrdersPastDate;

    @FindBy(xpath = "//button[@class='close-btn']//mat-icon[text()='close']")
    WebElement btnCloseOrder;

    @FindBy(xpath = "//button[@class='close-btn']//mat-icon[text()='chevron_right']")
    WebElement btnVehicleBack;

    public void openDispatcher() {
        testStepsLog("Open Dispatcher");
        driver.navigate().to(FM_URL + File.separator + "dispatch");
    }

    public boolean verifyDispatchPage() {
        generics.pause(10);
        new WebDriverWait(driver, 30).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='spinner']")));
        System.err.println("Wait COmpleted");
        return generics.isDisplay(lblToday) && generics.isDisplay(btnFilter);
    }

    public void addPastOrderFromMapToTruck() {
        generics.pause(4);
        Actions act = new Actions(driver);
        testStepsLog("Drag and Drop Truck from the map to order.");
        generics.scrollToElement(lblEmptyTruck);
        generics.scrollToElement(lstPastOrders.get(0));
        orderTitle = lstPastOrderTitle.get(0).getText();
        act.dragAndDrop(lblEmptyTruck, lstPastOrders.get(0)).build().perform();
        generics.pause(4);
    }

    public boolean verifyPastDateTruckMove() {
        return lblError.getText().equalsIgnoreCase("This operation cannot be performed.");
    }

    public boolean isTodayOrderNotDisplay() {
        System.out.println(lstTodayOrders.size());
        return lstTodayOrders.isEmpty();
    }

    public boolean isERExOrderNotDisplay() {
        System.out.println(lstEmptyReturnExchangeUnassignedOrdersToday.size());
        return lstEmptyReturnExchangeUnassignedOrdersToday.isEmpty();
    }

    public void selectPastOrder() {
        generics.clickOn(lstPastOrders.get(0));
    }

    public void selectPastERExOrder() {
        generics.clickOn(lstEmptyReturnExchangeUnassignedOrdersPastDate.get(0));
    }

    public void setCurrentDate() {
        generics.clickOn(lblOrderDate);
        generics.clickOn(btnCurrentDate);
        generics.clickOnJS(btnProceed);
        generics.clickOnJS(btnCloseOrder);
    }

    public void addTodayOrderFromMapToTruck() {
        generics.pause(4);
        Actions act = new Actions(driver);
        testStepsLog("Drag and Drop Truck from the map to order.");
        generics.scrollToElement(lblEmptyTruck);
        generics.scrollToElement(lstTodayOrders.get(0));
        orderTitle = lstTodayOrderTitle.get(0).getText();
        vehicleName = lblEmptyTruck.getText();
        System.out.println(orderTitle + vehicleName);
        act.dragAndDrop(lblEmptyTruck, lstTodayOrders.get(0)).build().perform();
        generics.pause(10);
    }

    public void addTodayOrderFromMapToTruckWithVehicle() {
        generics.pause(4);
        Actions act = new Actions(driver);
        testStepsLog("Drag and Drop Truck from the map to order.");
        generics.scrollToElement(lblEmptyTruck);
        generics.scrollToElement(lstTodayOrders.get(0));
        orderTitle = lstTodayOrderTitle.get(0).getText();
        System.out.println(orderTitle);
        act.dragAndDrop(driver.findElement(By.xpath("//div[@appoverlay='VehicleList']//span[text()='" + vehicleName + "']")), lstTodayOrders.get(0)).build().perform();
        generics.pause(10);
    }

    public void addTodayAssignedOrderFromMapToTruck() {
        generics.pause(4);
        Actions act = new Actions(driver);
        testStepsLog("Drag and Drop Truck from the map to order.");
        generics.scrollToElement(lblEmptyTruck);
        generics.scrollToElement(lstTodayAssignedOrders.get(0));
        orderTitle = lstTodayAssignedOrderTitle.get(0).getText();
        System.out.println(orderTitle);
        act.dragAndDrop(lblEmptyTruck, lstTodayAssignedOrders.get(0)).build().perform();
        generics.pause(10);
    }

    public void addTomorrowOrderFromMapToTruck() {
        generics.pause(4);
        Actions act = new Actions(driver);
        testStepsLog("Drag and Drop Truck from the map to order.");
        generics.scrollToElement(lblEmptyTruck);
        generics.scrollToElement(lstTomorrowOrders.get(0));
        orderTitle = lstTomorrowOrderTitle.get(0).getText();
        System.out.println(orderTitle);
        act.dragAndDrop(lblEmptyTruck, lstTomorrowOrders.get(0)).build().perform();
        generics.pause(10);
    }

    public boolean verifyOrderAssigned() {
        boolean bool = false;
        System.out.println(orderTitle);
        for (WebElement webElement : lstAssignedOrders) {
            System.out.println(webElement.getText());
            if (webElement.getText().equalsIgnoreCase(orderTitle)) {
                bool = webElement.getText().equalsIgnoreCase(orderTitle);
            }
        }
        return bool;
    }

    public boolean orderStatusAssigned() {
        return driver.findElement(By.xpath("//p[text()=' " + orderTitle + " ']//ancestor::map-common-order-item" +
                "//div[contains(@class,'_assigned')]")).isDisplayed();
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

    public void searchAddress() {
        new WebDriverWait(driver, 30).until(ExpectedConditions.invisibilityOfElementLocated(
                By.xpath("//div[text()='Loading...']")));
        generics.clickOnJS(btnSearchOrder);
        generics.type(txtSearch, orderTitle.split(" ")[0]);
    }

    public boolean verifyOrderFilteredAsAssigned() {
        return driver.findElement(By.xpath("//dispatch-order-aside-header//following-sibling::map-common-group-order-list//p[contains(text(),'" + orderTitle + "')]")).isDisplayed();
    }

    public void setTomorrowDateToOrder() {
        selectOrder();
        generics.clickOn(lblOrderDate);
        generics.clickOn(driver.findElement(By.xpath("//span[@bsdatepickerdaydecorator and text()='" +
                LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd")) + "']")));
        generics.clickOnJS(btnProceed);
    }

    private void selectOrder() {
        generics.clickOn(lstAllOrders.get(0));
    }

    public void filterTomorrowOrder() {
        new WebDriverWait(driver, 20).until(ExpectedConditions.invisibilityOfAllElements(
                driver.findElements(By.xpath("//div[contains(@class,'cdk-overlay-backdrop-showing')]"))));
        btnDatePicker.click();
        generics.clickOn(driver.findElement(By.xpath("//span[contains(@aria-label,'" +
                LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("MMMM dd, yyyy")) + "')]")));
    }

    public boolean verifyAssigningVehicleToInProgressOrder() {
        return lblError.getText().equalsIgnoreCase("This operation cannot be performed.");
    }

    public void makeOrderOnHold() {
        generics.clickOnJS(btnOnHold);
        generics.clickOn(btnProceed);
    }

    public boolean isOnHoldOrderNotDisplay() {
        System.out.println(lstTodayOnHoldOrders.size());
        return lstTodayOnHoldOrders.isEmpty();
    }

    public void addTodayOnHoldOrderFromMapToTruck() {
        generics.pause(4);
        Actions act = new Actions(driver);
        testStepsLog("Drag and Drop Truck from the map to order.");
        generics.scrollToElement(lblEmptyTruck);
        generics.scrollToElement(lstTodayOnHoldOrders.get(0));
        orderTitle = lstTodayOnHoldOrderTitle.get(0).getText();
        System.out.println(orderTitle);
        act.dragAndDrop(lblEmptyTruck, lstTodayOnHoldOrders.get(0)).build().perform();
        generics.pause(4);
    }

    public void openOrderFromVehiclePane() {
        for (WebElement e : lstAssignedOrders) {
            System.out.println(e.getText() + "   " + orderTitle);
            if (e.getText().contains(orderTitle)) {
                generics.clickOnJS(e);
                generics.pause(2);
                break;
            }
        }
    }

    public void startOrder() {
        generics.pause(3);
        testStepsLog("Click on Start button to start order.");
        generics.clickOnJS(btnStart);
        generics.pause(2);
        generics.clickOnJS(btnVehicleBack);
    }

    public void addTodayERExOrderFromMapToTruck() {
        generics.pause(4);
        Actions act = new Actions(driver);
        testStepsLog("Drag and Drop Truck from the map to order.");
        generics.scrollToElement(lblEmptyTruck);
        generics.scrollToElement(lstEmptyReturnExchangeUnassignedOrdersToday.get(0));
        orderTitle = lstEmptyReturnExchangeUnassignedOrdersTodayTitle.get(0).getText();
        vehicleName = lblEmptyTruck.getText();
        System.out.println(orderTitle + vehicleName);
        act.dragAndDrop(lblEmptyTruck, lstEmptyReturnExchangeUnassignedOrdersToday.get(0)).build().perform();
        new WebDriverWait(driver, 30).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//section[contains(@class,'spinner')]")));
    }

    public void addTodayERExOrderFromMapToTruckWithVehicle() {
        generics.pause(4);
        Actions act = new Actions(driver);
        testStepsLog("Drag and Drop Truck from the map to order.");
        generics.scrollToElement(lblEmptyTruck);
        generics.scrollToElement(lstEmptyReturnExchangeUnassignedOrdersToday.get(0));
        orderTitle = lstEmptyReturnExchangeUnassignedOrdersTodayTitle.get(0).getText();
        System.out.println(orderTitle);
        act.dragAndDrop(driver.findElement(By.xpath("//div[@appoverlay='VehicleList']//span[text()='" + vehicleName + "']")),
                lstEmptyReturnExchangeUnassignedOrdersToday.get(0)).build().perform();
        new WebDriverWait(driver, 30).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//section[contains(@class,'spinner')]")));
    }

}
