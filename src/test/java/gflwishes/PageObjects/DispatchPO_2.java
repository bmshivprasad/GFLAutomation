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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DispatchPO_2 extends Dispatch {

    private WebDriver driver;
    private Generics generics;

    private static String orderTitle = "";
    public static String vehicleName = "";
    private static String SiteAddress;
    private static String lblPickUpOrderName;

    private static boolean isUpFound = false;
    private static int directive = 0;

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

    @FindBy(xpath = "//common-error//div[contains(@class,'message')]")
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
            "//map-common-order-item//p")})
    List<WebElement> lstInProgressOrders;

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

    @FindBy(xpath = "//map-common-vehicle-detailed-view//mat-expansion-panel[contains(@class,'mat-expanded')]" +
            "//mat-icon[@title='Order for this vehicle will be unassigned']")
    WebElement btnRemoveVehicle;

    @FindBy(xpath = "//i[text()='date_range']//following-sibling::span")
    WebElement btnDatePicker;

    @FindBy(xpath = "//dispatch-order-aside-header//mat-icon[text()='search']")
    WebElement btnSearchOrder;

    @FindBy(xpath = "//button//span[@dataname='PUT ON HOLD']")
    WebElement btnOnHold;

    @FindBy(xpath = "//button//span[@dataname='RESCHEDULE']")
    WebElement btnReschedule;

    @FindBy(xpath = "//button//mat-icon[text()='check']//following-sibling::div[contains(text(),'YES')]")
    WebElement btnYes;

    @FindBy(xpath = "//button//mat-icon[text()='play_circle_outline']")
    WebElement btnStart;

    @FindBy(xpath = "//mat-icon[contains(@class,'container_buttons')]")
    List<WebElement> btnEditContainer;

    @FindBy(xpath = "//input[@formcontrolname='pickupContainerName']")
    WebElement txtContainerName;

    @FindAll(value = {@FindBy(xpath = "//mat-radio-button")})
    List<WebElement> lstRadios;

    @FindBy(xpath = "//form//mat-icon[text()='check']")
    WebElement btnSaveContainerName;

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

    public void selectTodayOrder() {
        generics.clickOn(lstTodayOrders.get(0));
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
        act.clickAndHold(lblEmptyTruck)
                .moveToElement(lstTodayOrders.get(0))
                .release(lstTodayOrders.get(0))
                .build().perform();
        generics.pause(10);
    }

    public void addTodayOrderFromMapToTruck(Map<String, List<List<String>>> vehicles) {
        generics.pause(4);
        Actions act = new Actions(driver);
        testStepsLog("Drag and Drop Truck from the map to order.");

        for (Map.Entry<String, List<List<String>>> entry : vehicles.entrySet()) {
            String vehicle = entry.getKey();
            List<List<String>> value = entry.getValue();
            if (!generics.isPresent("//span[contains(@class,'vehicle_id_font') and text()='" + vehicle + "']" +
                    "//ancestor::map-common-vehicle-item-header//img//following-sibling::span") && value.isEmpty()) {
                vehicleName = vehicle;
                generics.scrollToElement(driver.findElement(By.xpath("//map-common-vehicle-item-header//" +
                        "span[contains(@class,'vehicle_id_font') and text()='" + vehicleName + "']")));
                generics.scrollToElement(lstTodayOrders.get(0));
                orderTitle = lstTodayOrderTitle.get(0).getText();
                System.out.println(orderTitle + vehicleName);
                act.dragAndDrop(lblEmptyTruck, lstTodayOrders.get(0)).build().perform();
                generics.pause(10);
                break;
            }
        }
    }

    public void addTodayOrderFromMapToTruckWithVehicle() {
        generics.pause(4);
        Actions act = new Actions(driver);
        testStepsLog("Drag and Drop Truck from the map to order.");
        generics.scrollToElement(driver.findElement(By.xpath("//div[@appoverlay='VehicleList']//span[text()='" + vehicleName + "']")));
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
        generics.scrollToElement(btnFilterOpen);
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
                LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("d")) + "']")));
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
                LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("MMMM d, yyyy")) + "')]")));
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
        generics.pause(3);
        lstAssignedOrders = driver.findElements(By.xpath("//dispatch-vehicle-aside-header//" +
                "following-sibling::map-common-vehicle-list//div[@id='order-item']//p"));
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
        generics.pause(4);
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
        WebElement lblTruck = driver.findElement(By.xpath("//map-common-vehicle-item-header//" +
                "span[contains(@class,'vehicle_id_font') and text()='" + vehicleName + "']"));
        generics.scrollToElement(lblTruck);
        generics.scrollToElement(lstEmptyReturnExchangeUnassignedOrdersToday.get(0));
        orderTitle = lstEmptyReturnExchangeUnassignedOrdersTodayTitle.get(0).getText();
        System.out.println(orderTitle);
        act.clickAndHold(lblTruck)
                .moveToElement(lstEmptyReturnExchangeUnassignedOrdersToday.get(0))
                .release(lstEmptyReturnExchangeUnassignedOrdersToday.get(0))
                .build().perform();
        new WebDriverWait(driver, 30).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//section[contains(@class,'spinner')]")));
    }

    public void addTodayInProgressOrderFromMapToTruck() {
        generics.pause(4);
        Actions act = new Actions(driver);
        testStepsLog("Drag and Drop Truck from the map to order.");
        generics.scrollToElement(lblEmptyTruck);
        generics.scrollToElement(lstInProgressOrders.get(0));
        orderTitle = lstInProgressOrders.get(0).getText();
        vehicleName = lblEmptyTruck.getText();
        System.out.println(orderTitle + vehicleName);
        act.dragAndDrop(lblEmptyTruck, lstInProgressOrders.get(0)).build().perform();
        generics.pause(10);
    }

    public boolean isInProgressOrdersNotDisplay() {
        return driver.findElements(By.xpath("//dispatch-order-aside-header//" +
                "following-sibling::map-common-group-order-list//map-common-order-item//p")).isEmpty();
    }

    public boolean verifyOnHoldAssignmentMessage() {
        return lblError.getText().equalsIgnoreCase("You cannot assign Onhold " +
                "assignments without reschedule it!");
    }

    public void addTodayERExOrderFromMapToTruck(Map<String, List<List<String>>> vehicles) {
        generics.pause(4);
        Actions act = new Actions(driver);
        testStepsLog("Drag and Drop Truck from the map to order.");

        for (Map.Entry<String, List<List<String>>> entry : vehicles.entrySet()) {
            String vehicle = entry.getKey();
            List<List<String>> value = entry.getValue();
            if (!generics.isPresent("//span[contains(@class,'vehicle_id_font') and text()='" + vehicle + "']" +
                    "//ancestor::map-common-vehicle-item-header//img//following-sibling::span") && value.isEmpty()) {
                vehicleName = vehicle;
                WebElement lblTruck = driver.findElement(By.xpath("//map-common-vehicle-item-header//" +
                        "span[contains(@class,'vehicle_id_font') and text()='" + vehicleName + "']"));
                generics.scrollToElement(lblTruck);
                generics.scrollToElement(lstEmptyReturnExchangeUnassignedOrdersToday.get(0));
                orderTitle = lstEmptyReturnExchangeUnassignedOrdersTodayTitle.get(0).getText();
                System.out.println(orderTitle + vehicleName);
                //act.dragAndDrop(lblTruck, lstEmptyReturnExchangeUnassignedOrdersToday.get(0)).build().perform();
                act.clickAndHold(lblTruck)
                        .moveToElement(lstEmptyReturnExchangeUnassignedOrdersToday.get(0))
                        .release(lstEmptyReturnExchangeUnassignedOrdersToday.get(0))
                        .build().perform();
                generics.pause(10);
                break;
            }
        }
    }

    public void moveOrderToTopInVehicle() {
        Actions act = new Actions(driver);
        WebElement lblTruck = driver.findElement(By.xpath("//map-common-vehicle-item-header//" +
                "span[contains(@class,'vehicle_id_font') and text()='" + vehicleName + "']"));
        WebElement moveOrder = driver.findElement(By.xpath("//map-common-vehicle-item//p[text()=' " + orderTitle + " ']"));
        act.clickAndHold(lblTruck).moveToElement(moveOrder).release(moveOrder).build().perform();
    }

    public boolean verifyMoveOrderTopToInProgress() {
        return lblError.getText().equalsIgnoreCase("You cannot move on top of an order with status progress.");
    }

    public boolean verifyActionRequestDisplay() {
        return driver.findElement(By.xpath("//span[contains(@class,'vehicle_id_font') and text()='" + DispatchPO_2.vehicleName + "']//" +
                "ancestor::map-common-vehicle-item//mat-list//p[contains(@class,'action_text')]")).getText().
                equalsIgnoreCase("ACTION REQUEST");
    }

    public void addPickUpDirectiveOrderToTruck(Map<Integer, List<String>> orders, Map<String, List<List<String>>> vehicles) {
        generics.pause(4);
        Actions act = new Actions(driver);
        testStepsLog("Drag and Drop Truck from the map to order.");

        for (Map.Entry<String, List<List<String>>> entry : vehicles.entrySet()) {
            String vehicle = entry.getKey();
            List<List<String>> value = entry.getValue();
            if (!generics.isPresent("//span[contains(@class,'vehicle_id_font') and text()='" + vehicle + "']" +
                    "//ancestor::map-common-vehicle-item-header//img//following-sibling::span") && value.isEmpty()) {
                vehicleName = vehicle;
                break;
            }
        }


        for (Map.Entry<Integer, List<String>> entry : orders.entrySet()) {
            System.out.println(entry.getValue());
            WebElement order = driver.findElement(By.xpath("//dispatch-order-aside-header//" +
                    "following-sibling::map-common-group-order-list//p[contains(text(),'" +
                    entry.getValue().get(0).split("\n")[0] + "')]"));
            if (entry.getValue().get(0).split("\n")[2].startsWith("u")) {
                if (entry.getValue().get(0).split("\n")[3].endsWith(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy"))) ||
                        entry.getValue().get(0).split("\n")[3].contains("ON HOLD")) {

                    generics.clickOn(order);
                    generics.clickOnJS(btnReschedule);
                    generics.clickOn(btnCurrentDate);
                    generics.clickOnJS(btnProceed);
                    generics.clickOnJS(btnCloseOrder);
                }

                WebElement lblTruck = driver.findElement(By.xpath("//map-common-vehicle-item-header//" +
                        "span[contains(@class,'vehicle_id_font') and text()='" + vehicleName + "']"));
                generics.scrollToElement(lblTruck);
                generics.scrollToElement(order);
                orderTitle = entry.getValue().get(0).split("\n")[0];
                System.out.println(orderTitle + vehicleName);
                //act.dragAndDrop(lblTruck, lstEmptyReturnExchangeUnassignedOrdersToday.get(0)).build().perform();
                act.clickAndHold(lblTruck)
                        .moveToElement(order)
                        .release(order)
                        .build().perform();
                isUpFound = true;
                directive = Integer.parseInt(entry.getValue().get(0).split("\n")[2]
                        .replaceAll("[^0-9.]+", ""));
                generics.pause(10);
                break;
            }
        }
    }

    public void addDropDirectiveOrderToTruck(Map<Integer, List<String>> orders, Map<String, List<List<String>>> vehicles) {

        generics.pause(4);
        Actions act = new Actions(driver);
        testStepsLog("Drag and Drop Truck from the map to order.");

        for (Map.Entry<Integer, List<String>> entry : orders.entrySet()) {
            System.out.println(entry.getValue());

            WebElement order = driver.findElement(By.xpath("//dispatch-order-aside-header//" +
                    "following-sibling::map-common-group-order-list//p[contains(text(),'" +
                    entry.getValue().get(0).split("\n")[0] + "')]"));

            if (entry.getValue().get(0).split("\n")[2].startsWith("d") &&
                    !entry.getValue().get(0).split("\n")[2].contains(String.valueOf(directive))) {
                if (entry.getValue().get(0).split("\n")[3].endsWith(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy"))) ||
                        entry.getValue().get(0).split("\n")[3].contains("ON HOLD")) {
                    generics.clickOn(order);
                    generics.clickOnJS(btnReschedule);
                    generics.clickOn(btnCurrentDate);
                    generics.clickOnJS(btnProceed);
                    generics.clickOnJS(btnCloseOrder);
                }

                WebElement lblTruck = driver.findElement(By.xpath("//map-common-vehicle-item-header//" +
                        "span[contains(@class,'vehicle_id_font') and text()='" + vehicleName + "']"));
                generics.scrollToElement(lblTruck);
                generics.scrollToElement(order);
                orderTitle = entry.getValue().get(0).split("\n")[0];
                System.out.println(orderTitle + vehicleName);
                //act.dragAndDrop(lblTruck, lstEmptyReturnExchangeUnassignedOrdersToday.get(0)).build().perform();
                act.clickAndHold(lblTruck)
                        .moveToElement(order)
                        .release(order)
                        .build().perform();
                generics.pause(10);
                break;
            }
        }

    }

    public void unassignOrder() {
        generics.pause(3);
        generics.clickOnJS(btnRemoveVehicle);
        generics.pause(1);
        generics.clickOnJS(btnYes);
        generics.pause(5);
    }

    public boolean verifyOrderUnAssigned() {
        generics.pause(3);
        List<String> lstOrders = new ArrayList<>();
        System.out.println(orderTitle);
        for (WebElement webElement : lstTodayAssignedOrderTitle) lstOrders.add(webElement.getText());
        System.out.println(lstOrders);
        return !lstOrders.contains(orderTitle);
    }

    public void enterPickUpContainerName() {
        generics.pause(1);
        generics.scrollToElement(btnEditContainer.get(0));
        generics.clickOnJS(btnEditContainer.get(0));
        generics.pause(2);
        if (!lstRadios.get(1).getAttribute("class").contains("mat-radio-disabled"))
            generics.type(txtContainerName, "Test_" + generics.getRandomBetween(100, 999));
        generics.clickOnJS(btnSaveContainerName);
        generics.pause(3);
    }

    public boolean verifyOrderCantUnassigned() {
        return btnRemoveVehicle.getAttribute("disabled").equalsIgnoreCase("true");
    }
}
