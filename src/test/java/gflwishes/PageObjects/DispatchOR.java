package gflwishes.PageObjects;

import gflwishes.base.Generics;
import gflwishes.testcases.Dispatch;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DispatchOR extends Dispatch {

    private WebDriver driver;

    public DispatchOR(WebDriver baseDriver) {
        this.driver = baseDriver;
    }

    public List<WebElement> getPastDateOrders() {
        return driver.findElements(By.xpath("//dispatch-order-aside-header//" +
                "following-sibling::map-common-group-order-list//div[@class='pastDate']"));
    }

    public List<WebElement> getPastDateOrderTitles() {
        return driver.findElements(By.xpath("//dispatch-order-aside-header//following-sibling::map-common-group-order-list" +
                "//div[@class='pastDate']//map-common-date-time-view//div"));
    }

    public List<WebElement> getOrders() {
        return driver.findElements(By.xpath("//dispatch-order-aside-header//following-sibling::" +
                "map-common-group-order-list//map-common-order-item//div[text()!='ON HOLD']"));
    }

    public List<WebElement> getTodayOrders() {
        return driver.findElements(By.xpath("//dispatch-order-aside-header//following-sibling::" +
                "map-common-group-order-list//map-common-order-item/div[not(@class='pastDate')]//div[text()!='ON HOLD']"));
    }

    public List<WebElement> getTodayOrdersTitle() {
        return driver.findElements(By.xpath("//dispatch-order-aside-header//following-sibling::map-common-group-order-list" +
                "//map-common-order-item/div[not(@class='pastDate')]//div[text()!='ON HOLD']//" +
                "ancestor::map-common-order-item//p"));
    }

    public Map<Integer, List<String>> getOrderDetails(String orderName) {

        List<WebElement> directiveSign = driver.findElements(By.xpath("(//p[text()=' " + orderName + " ']//ancestor::mat-list-item)[1]" +
                "//mat-icon"));
        List<WebElement> directiveCount = driver.findElements(By.xpath("(//p[text()=' " + orderName + " ']//ancestor::mat-list-item)[1]" +
                "//mat-icon//following-sibling::span"));
        List<WebElement> orderTime = driver.findElements(By.xpath("(//p[text()=' " + orderName + " ']" +
                "//ancestor::mat-list-item)[1]//map-common-date-time-view//div"));

        Map<Integer, List<String>> order = new HashMap<>();
        List<String> details = new ArrayList<>();

        return null;
    }

    public Map<Integer, List<String>> getAllOrderDetails() {

        List<WebElement> lstAllOrders = driver.findElements(By.xpath("//dispatch-order-aside-header//following-sibling::map-common-group-order-list//map-common-order-item//p"));
        int orderCount = 1;

        Map<Integer, List<String>> order = new HashMap<>();

        for (WebElement orderName : lstAllOrders) {
            String orderDetails = orderName.getText() + "\n";

            List<WebElement> directiveSign = driver.findElements(By.xpath("//map-common-dispatch//p[text()=\" " + orderName.getText() + " \"]//ancestor::mat-list-item//mat-icon"));
            List<WebElement> directiveCount = driver.findElements(By.xpath("//map-common-dispatch//p[text()=\" " + orderName.getText() + " \"]//ancestor::mat-list-item//mat-icon//following-sibling::span"));
            WebElement orderTime;

            try {
                orderTime = driver.findElement(By.xpath("//map-common-dispatch//p[text()=\" " + orderName.getText() + " \"]//ancestor::mat-list-item//map-common-date-time-view//div"));
            }catch (Exception e){
                orderTime = driver.findElement(By.xpath("//map-common-dispatch//p[text()=\" " + orderName.getText() + " \"]//ancestor::mat-list-item//div[contains(@class,'onhold_text')]"));
            }

            WebElement orderType = driver.findElement(By.xpath("//map-common-dispatch//p[text()=\" " + orderName.getText() + " \"]//ancestor::div[contains(@class,'mat-expansion-panel-content')]//preceding-sibling::mat-expansion-panel-header//map-common-list-header"));

            List<String> details = new ArrayList<>();
            System.out.println(orderType.getText());
            orderDetails = orderDetails.concat(orderType.getText().split(" \\(")[0] + "\n");

            if (orderType.getText().contains("PICKUP")) {
                orderDetails = orderDetails.concat("u" + directiveCount.get(0).getText() + " \n");
            } else if (orderType.getText().contains("DELIVER")) {
                orderDetails = orderDetails.concat("d" + directiveCount.get(0).getText() + " \n");
            } else if (orderType.getText().contains("EMPTY AND RETURN")) {
                if (directiveCount.size() == 1)
                    orderDetails = orderDetails.concat("u" + " d" + directiveCount.get(0).getText() + " \n");
                else
                    orderDetails = orderDetails.concat("u" + directiveCount.get(0).getText() + " d" + directiveCount.get(1).getText() + " \n");
            } else if (orderType.getText().contains("EXCHANGE")) {
                String orderDir = "";
                if (directiveCount.size() == 1) {
                    for (WebElement sign : directiveSign)
                        if (sign.getText().equalsIgnoreCase("arrow_upward"))
                            orderDir = orderDir.concat("u" + " d" + directiveCount.get(0).getText() + " ");
                        else
                            orderDir = orderDir.concat("d" + " u" + directiveCount.get(0).getText() + " ");
                } else {
                    for (int sign = 0; sign < directiveSign.size(); sign++)
                        if (directiveSign.get(sign).getText().equalsIgnoreCase("arrow_upward"))
                            orderDir = orderDir.concat("u" + directiveCount.get(sign).getText() + " ");
                        else
                            orderDir = orderDir.concat("d" + directiveCount.get(sign).getText() + " ");
                }
                orderDetails = orderDetails.concat(orderDir + "\n");
            } else if (orderType.getText().contains("MOVE")) {
                orderDetails = orderDetails.concat("f" + directiveCount.get(0).getText() + " \n");
            }

            orderDetails = orderDetails.concat(orderTime.getText());

            details.add(orderDetails);

            order.put(orderCount, details);

            orderCount++;
        }

        return order;
    }

    public Map<String, List<List<String>>> getAllVehicleDetails() {

        List<WebElement> lstAllVehicles = driver.findElements(By.xpath("//dispatch-vehicle-aside-header//following-sibling::map-common-vehicle-list//map-common-vehicle-item-header//span[contains(@class,'vehicle_id_font')]"));

        Map<String, List<List<String>>> order = new HashMap<>();
        List<List<String>> orders;
        List<String> details;
        boolean isActionRequest = false;

        for (WebElement vehicleName : lstAllVehicles) {

            String vehicle = vehicleName.getText();

            if (!new Generics(driver).isPresent("//span[text()='" + vehicle + "']//" +
                    "ancestor::map-common-vehicle-item//mat-list-item//span[contains(@class,'noAssignments_text')]")) {

//                boolean isVehicleHasContainer = new Generics(driver).isPresent("" +
//                        "//span[contains(@class,'vehicle_id_font') and text()='" + vehicle + "']" +
//                        "//ancestor::map-common-vehicle-item-header//img");

                List<WebElement> lstAllOrders = driver.findElements(By.xpath("//dispatch-vehicle-aside-header//following-sibling::map-common-vehicle-list//span[contains(@class,'vehicle_id_font') and text()='" + vehicle + "']//ancestor::map-common-vehicle-item//mat-list//p[not(contains(@class,'deliveryTime'))]"));

                orders = new ArrayList<>();
                details = new ArrayList<>();

                for (WebElement orderName : lstAllOrders) {

                    if (orderName.getText().startsWith("ACTION REQUEST")) {
                        isActionRequest = true;
                        continue;
                    } else {
                        String orderDetails = orderName.getText() + "\n";

                        List<WebElement> directiveSign = driver.findElements(By.xpath("//map-common-vehicle-item//p[text()=\" " + orderName.getText() + " \"]//ancestor::map-common-order-item//mat-icon"));
                        List<WebElement> directiveCount = driver.findElements(By.xpath("//map-common-vehicle-item//p[text()=\" " + orderName.getText() + " \"]//ancestor::map-common-order-item//mat-icon//following-sibling::span"));
                        WebElement orderTime = driver.findElement(By.xpath("//map-common-vehicle-item//p[text()=\" " + orderName.getText() + " \"]//ancestor::mat-list-item//map-common-date-time-view//div"));
                        WebElement orderType = driver.findElement(By.xpath("//map-common-dispatch//p[text()=\" " + orderName.getText() + " \"]//ancestor::mat-list-item//common-order-hover-info//h3[@class='locationHeader']"));
                        WebElement orderStatus = driver.findElement(By.xpath("//map-common-dispatch//p[text()=\" " + orderName.getText() + " \"]//ancestor::mat-list-item//common-order-hover-info//span[contains(@class,'badge_text')]"));

                        System.out.println(orderType.getAttribute("innerHTML"));
                        orderDetails = orderDetails.concat(orderType.getAttribute("innerHTML") + "\n");

                        if (orderType.getAttribute("innerHTML").contains("PICKUP")) {
                            orderDetails = orderDetails.concat("u" + directiveCount.get(0).getText() + " \n");
                        } else if (orderType.getAttribute("innerHTML").contains("DELIVER")) {
                            orderDetails = orderDetails.concat("d" + directiveCount.get(0).getText() + " \n");
                        } else if (orderType.getAttribute("innerHTML").contains("EMPTY &amp; RETURN")) {
                            if (directiveCount.size() == 1) {
                                orderDetails = orderDetails.concat("xu" + " d" + directiveCount.get(0).getText() + " \n");
                            } else {
                                orderDetails = orderDetails.concat("u" + directiveCount.get(0).getText() + " d" + directiveCount.get(1).getText() + " \n");
                            }
                        } else if (orderType.getAttribute("innerHTML").contains("EXCHANGE")) {
                            String orderDir = "";
                            if (directiveCount.size() == 1) {
                                for (WebElement webElement : directiveSign)
                                    if (webElement.getText().equalsIgnoreCase("arrow_upward"))
                                        orderDir = orderDir.concat("u" + " d" + directiveCount.get(0).getText() + " ");
                                    else
                                        orderDir = orderDir.concat("d" + " u" + directiveCount.get(0).getText() + " ");
                            } else {
                                for (int sign = 0; sign < directiveSign.size(); sign++)
                                    if (directiveSign.get(sign).getText().equalsIgnoreCase("arrow_upward"))
                                        orderDir = orderDir.concat("u" + directiveCount.get(sign).getText() + " ");
                                    else
                                        orderDir = orderDir.concat("d" + directiveCount.get(sign).getText() + " ");
                            }
                            orderDetails = orderDetails.concat(orderDir + "\n");
                        } else if (orderType.getAttribute("innerHTML").contains("MOVE")) {
                            orderDetails = orderDetails.concat("f" + directiveCount.get(0).getText() + " \n");
                        }

                        orderDetails = orderDetails.concat(orderTime.getText() + "\n ");
                        orderDetails = orderDetails.concat(orderStatus.getAttribute("innerHTML"));

                        details.add(orderDetails + "\n" + "ActionRequest : " + isActionRequest);
                    }
                    isActionRequest = false;
                }

                orders.add(details);

//                if (isVehicleHasContainer)
//                    vehicle = vehicle.concat(" " + driver.findElement(By.
//                            xpath("//span[contains(@class,'vehicle_id_font') and text()='" + vehicle + "']" +
//                                    "//ancestor::map-common-vehicle-item-header//img//following-sibling::span")).getText());

                order.put(vehicle, orders);
            } else {
                order.put(vehicle, new ArrayList<>());
            }
        }
        return order;
    }
}
